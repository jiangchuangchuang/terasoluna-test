package user.management.btkoucn.domain.service.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.common.CommonUtils;
import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.Role;
import user.management.btkoucn.domain.repository.account.AccountRepository;
import user.management.btkoucn.domain.repository.job.JobRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FileUploadServiceImpl implements FileUploadService {

	@Inject
	PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public FileUploadOutputBean fileUpload(InputStream fileStream,
			String filePath) {
		FileUploadOutputBean output = new FileUploadOutputBean();

		ResultMessages messages = ResultMessages.error();
		Map<Integer, Account> accountList = new HashMap<Integer, Account>();

		// load file
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(fileStream, Charset.forName("utf-8")));

		String rowInfo = "";
		int rowNumber = 0;
		try {
			while ((rowInfo = bufferedReader.readLine()) != null) {
				rowNumber++;
				String[] fields = rowInfo.split(",");
				if (fields.length != 7) {
					messages.add(ResultMessage.fromCode("e.file.check.length",
							new Object[] { rowNumber }));
					continue;
				}
				Account account = new Account();
				boolean hasError = fieldsToAccount(account, fields, messages,
						rowNumber);
				if (hasError) {
					continue;
				} else {
					// 登録可のAccountリストを用意する
					accountList.put(rowNumber, account);
				}
			}
		} catch (IOException e) {
			throw new BusinessException(ResultMessages.error().add(
					"e.file.read.failed", filePath));
		}

		output.setResultMessages(messages);
		output.setAccountList(accountList);
		return output;
	}

	/**
	 * Account作成
	 * 
	 * @param account
	 * @param fields
	 * @param messages
	 * @param rowNumber
	 * @return false:[no err] true:[has err]
	 */
	private boolean fieldsToAccount(Account account, String[] fields,
			ResultMessages messages, int rowNumber) {
		boolean hasError = false;
		account = new Account();
		account.setUserId(fields[0]);
		if (StringUtils.isEmpty(account.getUserId())
				|| !account.getUserId().matches("[0-9]{4}")) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.userId",
					new Object[] { rowNumber }));
		}
		account.setUsername(fields[1]);
		if (StringUtils.isEmpty(account.getUsername())
				|| StringUtils.isEmpty(account.getUsername().trim())) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.username",
					new Object[] { rowNumber }));
		} else {
			account.setUsername(fields[1].trim());
		}
		account.setBirthDay(fields[2]);
		if (StringUtils.isEmpty(account.getBirthDay())
				|| !CommonUtils
						.checkformat(account.getBirthDay(), "yyyy/MM/dd")) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.birthDay",
					new Object[] { rowNumber }));
		}
		account.setAddress(fields[3]);
		if (StringUtils.isEmpty(account.getAddress())
				|| StringUtils.isEmpty(account.getAddress().trim())) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.address",
					new Object[] { rowNumber }));
		} else {
			account.setAddress(fields[3].trim());
		}
		account.setTenNum(fields[4]);
		if (StringUtils.isEmpty(account.getTenNum())
				|| !account.getTenNum().matches("[0-9]*")
				|| account.getTenNum().length() > 11
				|| account.getTenNum().length() < 10) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.tenNum",
					new Object[] { rowNumber }));
		}
		account.setPassword(fields[5]);
		if (StringUtils.isEmpty(account.getPassword())
				|| !account.getPassword().matches("[a-zA-Z0-9]*")
				|| account.getPassword().length() < 6) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.password",
					new Object[] { rowNumber }));
		} else {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			LocalDate currentDate = LocalDate.now();
			LocalDate passwordExpired = currentDate
					.plusMonths(Constant.PASSWORD_MONTHS);
			account.setPasswordExpired(passwordExpired);
		}

		// role
		if (StringUtils.isEmpty(fields[6]) || fields[6].split(" ").length > 2) {
			hasError = true;
			messages.add(ResultMessage.fromCode("e.file.check.roles",
					new Object[] { rowNumber }));
		} else {
			List<Role> roles = new ArrayList<Role>();
			String[] roleStrings = fields[6].split(" ");
			for (int i = 0; i < roleStrings.length; i++) {
				if (StringUtils.isEmpty(roleStrings[i])
						|| (!Constant.ROLE_ADMIN.equals(roleStrings[i]) && !Constant.ROLE_USER
								.equals(roleStrings[i]))) {
					hasError = true;
					messages.add(ResultMessage.fromCode("e.file.check.roles",
							new Object[] { rowNumber }));
				} else {
					Role role = new Role();
					role.setRole(roleStrings[i]);
					roles.add(role);
				}
			}
			account.setRoles(roles);
		}

		return hasError;
	}

	@Inject
	AccountRepository accountRepository;

	@Transactional
	@Override
	public void insertUser(Account account, ResultMessages messages,
			int rowNumber) {
		boolean hasError = false;

		try {
			// lock check
			Long checkResult = accountRepository.checkAccountExist(account
					.getUserId());
			if (checkResult != null && checkResult > 0) {
				messages.add(ResultMessage.fromCode(
						"e.file.insert.userId.exist", new Object[] { rowNumber,
								account.getUserId() }));
				hasError = true;
			}

			// insert account
			Long insertAccountResult = accountRepository.insertAccount(account);
			if (insertAccountResult == null || insertAccountResult == 0) {
				messages.add(ResultMessage.fromCode(
						"e.file.insert.account.failed", new Object[] {
								rowNumber, account.getUserId() }));
				hasError = true;
			}

			// insert role
			for (Role role : account.getRoles()) {
				// check exist
				Long roleExist = accountRepository.checkRoleExist(
						role.getUserId(), role.getRole());
				if (roleExist != null && roleExist > 0) {
					messages.add(ResultMessage.fromCode(
							"e.file.insert.userId.exist", new Object[] {
									rowNumber, account.getUserId()}));
					hasError = true;
				}
				// insert
				Long roleInsert = accountRepository.insertRole(
						role.getUserId(), role.getRole());
				if (roleInsert == null || roleInsert == 0) {
					messages.add(ResultMessage.fromCode(
							"e.file.insert.role.failed", new Object[] {
									rowNumber, account.getUserId()}));
					hasError = true;
				}
			}
			if (hasError) {
				throw new BusinessException(ResultMessages.error().add(
						"e.file.insert.account.failed", account.getUserId()));
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add(
					"e.file.insert.account.failed", account.getUserId()));
		}

	}

	@Inject
	JobRepository jobRepository;

	@Override
	public void insertJob(String jobStutas, String jobName) {
		String jobType = "";
		switch (jobStutas) {
		case "0":
			jobType = "正常終了";
			break;
		case "1":
			jobType = "異常終了";
			break;
		case "2":
			jobType = "警告終了";
			break;
		default:
			break;
		}
		jobRepository.insertJob(jobName, jobStutas, jobType);
	}

}
