package user.management.btkoucn.domain.service.login;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.management.btkoucn.domain.repository.account.AccountRepository;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Inject
	AccountRepository accountRepository;
	
	@Override
	public void updateLoginStutas(String userId) {
		accountRepository.updateLoginStutas(userId);
	}

	@Override
	@Transactional
	public Long updateLoginFailedCount(String userId) {
		return accountRepository.updateLoginFailedCount(userId);
	}

	@Override
	@Transactional
	public Long updateLoginFailedLocked(String userId) {
		return accountRepository.updateLoginFailedLocked(userId);
	}

}
