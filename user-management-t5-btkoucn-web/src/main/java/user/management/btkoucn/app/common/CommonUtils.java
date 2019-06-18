package user.management.btkoucn.app.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.app.user.UserForm;
import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.Role;
import user.management.btkoucn.domain.service.authenticate.AccountUserDetails;

public class CommonUtils {

	public static List<String> convertRolesToStringList(List<Role> target) {
		List<String> roleList = new ArrayList<String>();
		if (target == null || target.size() == 0) {
			return roleList;
		}
		for (Role t : target) {
			roleList.add(t.getRole());
		}
		return roleList;
	}

	public static List<Role> convertStringToRolesList(List<String> target,
			UserForm form) {
		List<Role> roleList = new ArrayList<Role>();
		if (target == null || target.size() == 0) {
			return roleList;
		}
		for (String t : target) {
			Role role = new Role();
			role.setRole(t);
			role.setUserId(form.getUserId());
			roleList.add(role);
		}
		return roleList;
	}

	/**
	 * パスワードの有効期限の判断
	 * 
	 * @param lastDate
	 *            パスワードの有効期限
	 * @return true:無効　false:有効
	 */
	public static boolean passwordIsExpired(LocalDate lastDate) {
		LocalDate currentDate = LocalDate.now();
		if (currentDate.isAfter(lastDate)) {
			return true;
		}
		return false;
	}

	/*
	 * ログイン済ユーザの情報が変更された場合、 セッションに保存した権限を更新する
	 */
	public static void reloadUserAuthority(Account account) {
		SecurityContext securityContext = (SecurityContext) SecurityContextHolder
				.getContext();
		Authentication authentication = securityContext.getAuthentication();
		UserDetails userDetails = new AccountUserDetails(account);

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
				userDetails, authentication.getCredentials(),
				userDetails.getAuthorities());
		result.setDetails(authentication.getDetails());
		securityContext.setAuthentication(result);
	}

	/*
	 * ログイン済ユーザの情報が削除された場合、 セッションに保存した権限を削除する
	 */
	public static void deleteUserAuthority() {
		SecurityContext securityContext = (SecurityContext) SecurityContextHolder
				.getContext();
		Authentication authentication = securityContext.getAuthentication();
		authentication.setAuthenticated(false);
		securityContext.setAuthentication(authentication);
	}

	/**
	 * 権限リストが変更したか判断
	 * 
	 * @param role1
	 *            変更前
	 * @param role2
	 *            変更後
	 * @return true:変更　false:未変更
	 */
	public static boolean rolesIsChanged(List<Role> role1, List<Role> role2) {
		if (role1.size() != role2.size()) {
			// IsChanged
			return true;
		}

		int sameCount1 = 0;
		for (Role r1 : role1) {
			for (Role r2 : role2) {
				if (r1.getRole().equals(r2.getRole())) {
					sameCount1++;
					break;
				}
			}
		}
		if (sameCount1 != role1.size()) {
			// IsChanged
			return true;
		}

		// no Changed
		return false;
	}

	/**
	 * 一括登録ログ出力
	 * 
	 * @param resultMessages
	 *            　出力メッセージの内容
	 * @param messageSource
	 *            メッセージソース
	 * @param filePath
	 *            処理ファイル名
	 * @return　出力ファイル名
	 */
	public static String writeMessageToFile(ResultMessages resultMessages,
			MessageSource messageSource, String filePath) {
		try {
			File targetFile = createLogFile();
			FileWriter fw = new FileWriter(targetFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("アップロードファイル：「" + filePath + "」");
			bw.newLine();
			bw.write("の一括登録のエラー状況を確認してください。");
			for (ResultMessage resultMessage : resultMessages) {
				String messageStr = messageSource.getMessage(
						resultMessage.getCode(), resultMessage.getArgs(),
						Locale.getDefault());
				bw.newLine();
				bw.write(messageStr);
			}
			bw.flush();
			bw.close();
			String fileName = targetFile.getAbsolutePath();
			targetFile = null;
			return fileName;
		} catch (IOException e) {
			return "";
		}
	}

	public static File createLogFile() throws IOException {
		LocalDateTime currentDate = LocalDateTime.now();
		String fileName = "アップロード一括登録エラー状況_";
		String dirPath = Constant.LOG_PATH;
		if (dirPath.endsWith("\\")) {
			dirPath = dirPath.substring(0, dirPath.length() - 1);
		}
		File folder = new File(dirPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		fileName = fileName + currentDate.toString("yyyyMMddHHmmssSSS");
		File targetFile = new File(dirPath + "\\" + fileName + ".log");
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				throw e;
			}
		}
		return targetFile;
	}
	
	public static String getFileName(String filePath){
		String fileName = "";
		int last=filePath.lastIndexOf("\\");
		fileName = filePath.substring(last + 1);
		return fileName;
	}

}
