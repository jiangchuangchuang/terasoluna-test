package user.management.btkoucn.domain.service.account;


import user.management.btkoucn.domain.model.Account;

public interface AccountSharedService {
	Account findOne(String username);
}
