package user.management.btkoucn.domain.service.password;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.repository.account.AccountRepository;

@Service
@Transactional
public class PasswordServiceImpl implements PasswordService {

	@Inject
	AccountRepository accountRepository;
	
	@Inject
    PasswordEncoder passwordEncoder;
	
	@Override
	public void updatePassword(String userId, String password) {
		LocalDate currentDate= LocalDate.now();
		LocalDate passwordExpired=currentDate.plusMonths(Constant.PASSWORD_MONTHS);
		Account account= new Account();
		account.setUserId(userId);
		account.setPassword(passwordEncoder.encode(password));
		account.setPasswordExpired(passwordExpired);
		long result= accountRepository.updatePassword(account);
		if(result != 1){
			throw new BusinessException(ResultMessages.error().add("e.password.001",""));
		}
	}

}
