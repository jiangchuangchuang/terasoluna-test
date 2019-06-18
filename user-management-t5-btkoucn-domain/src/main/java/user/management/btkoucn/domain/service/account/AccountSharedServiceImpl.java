package user.management.btkoucn.domain.service.account;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.repository.account.AccountRepository;


@Service
public class AccountSharedServiceImpl implements AccountSharedService {
	@Inject
    AccountRepository accountRepository;

    @Transactional(readOnly=true)
    @Override
    public Account findOne(String username) {
        Account account = accountRepository.findOne(username);
        return account;
    }

}
