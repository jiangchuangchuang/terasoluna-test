package user.management.btkoucn.domain.service.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.repository.account.AccountRepository;

@Service
@Transactional
public class UserDeleteServiceImpl implements UserDeleteService {

	@Inject
	AccountRepository accountRepository;
	
	@Override
	public void deleteUser(Account account) {
		// lock check
		Long checkResult=accountRepository.checkUpdateTime(account.getUserId(), account.getAccountUpdateTime());
		if (checkResult == null || checkResult == 0){
        	throw new BusinessException(ResultMessages.error().add("e.user.delete.lock.failed",account.getUserId()));
		}
		
		// delete account
		try {
			Long deleteAccountResult=accountRepository.deleteAccount(account.getUserId());
			if (deleteAccountResult == null || deleteAccountResult == 0){
	        	throw new BusinessException(ResultMessages.error().add("e.user.delete.account.failed",account.getUserId()));
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add("e.user.delete.account.failed",account.getUserId()));
		}
		
		// delete role
		try {
			// delete
			Long roleDelete=accountRepository.updateRoleAllEnabled(account.getUserId());
			if (roleDelete == null || roleDelete == 0){
	        	throw new BusinessException(ResultMessages.error().add("e.user.delete.role.failed",account.getUserId()));
			}
		} catch (Exception e) {
			throw new BusinessException(ResultMessages.error().add("e.user.delete.role.failed",account.getUserId()));
		}
		

	}

}
