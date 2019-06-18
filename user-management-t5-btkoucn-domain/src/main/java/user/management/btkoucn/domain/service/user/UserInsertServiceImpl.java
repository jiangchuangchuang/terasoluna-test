package user.management.btkoucn.domain.service.user;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.Role;
import user.management.btkoucn.domain.repository.account.AccountRepository;

@Service
@Transactional
public class UserInsertServiceImpl implements UserInsertService {

	@Inject
	AccountRepository accountRepository;
	
	@Inject
    PasswordEncoder passwordEncoder;
	
	@Override
	public void insertUser(Account account) {
		// lock check
		Long checkResult=accountRepository.checkAccountExist(account.getUserId());
		if (checkResult != null && checkResult > 0){
        	throw new BusinessException(ResultMessages.error().add("e.user.insert.lock.failed",account.getUserId()));
		}
		
		// insert account
		try {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			LocalDate currentDate= LocalDate.now();
			LocalDate passwordExpired=currentDate.plusMonths(Constant.PASSWORD_MONTHS);
			account.setPasswordExpired(passwordExpired);
			Long insertAccountResult=accountRepository.insertAccount(account);
			if (insertAccountResult == null || insertAccountResult == 0){
	        	throw new BusinessException(ResultMessages.error().add("e.user.insert.account.failed",account.getUserId()));
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add("e.user.insert.account.failed",account.getUserId()));
		}
		
		// insert role
		try {
			for ( Role role: account.getRoles()) {
				// check exist
				Long roleExist=accountRepository.checkRoleExist(role.getUserId(),role.getRole());
				if (roleExist != null && roleExist > 0){
					throw new BusinessException(ResultMessages.error().add("e.user.insert.lock.failed",account.getUserId()));
				}
				// insert
				Long roleInsert=accountRepository.insertRole(role.getUserId(),role.getRole());
				if (roleInsert == null || roleInsert == 0){
		        	throw new BusinessException(ResultMessages.error().add("e.user.insert.role.failed",account.getUserId()));
				}
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add("e.user.insert.role.failed",account.getUserId()));
		}
		

	}

}
