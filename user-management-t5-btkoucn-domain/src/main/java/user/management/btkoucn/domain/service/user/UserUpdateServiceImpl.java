package user.management.btkoucn.domain.service.user;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
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
public class UserUpdateServiceImpl implements UserUpdateService {

	@Inject
	AccountRepository accountRepository;
	
	@Inject
    PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void updateUser(Account account) {
		// lock check
		Long checkResult=accountRepository.checkUpdateTime(account.getUserId(), account.getAccountUpdateTime());
		if (checkResult == null || checkResult == 0){
        	throw new BusinessException(ResultMessages.error().add("e.user.update.lock.failed",account.getUserId()));
		}
		
		// update account
		try {
			if (!StringUtils.isEmpty(account.getPassword())){
				account.setPassword(passwordEncoder.encode(account.getPassword()));
				LocalDate currentDate= LocalDate.now();
				LocalDate passwordExpired=currentDate.plusMonths(Constant.PASSWORD_MONTHS);
				account.setPasswordExpired(passwordExpired);
			}
			Long updateAccountResult=accountRepository.updateAccount(account);
			if (updateAccountResult == null || updateAccountResult == 0){
	        	throw new BusinessException(ResultMessages.error().add("e.user.update.account.failed",account.getUserId()));
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add("e.user.update.account.failed",account.getUserId()));
		}
		
		// update role
		try {
			// delete all roles
			accountRepository.deleteRole(account.getUserId());
			for ( Role role: account.getRoles()) {
				// update
				Long roleInsert=accountRepository.insertRole(role.getUserId(),role.getRole());
				if (roleInsert == null || roleInsert == 0){
		        	throw new BusinessException(ResultMessages.error().add("e.user.update.role.failed",account.getUserId()));
				}
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add("e.user.update.role.failed",account.getUserId()));
		}
		

	}

}
