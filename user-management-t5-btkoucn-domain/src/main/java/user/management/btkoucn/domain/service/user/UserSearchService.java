package user.management.btkoucn.domain.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.AccountSearchCondition;

public interface UserSearchService {
	Page<Account> searchUsers(AccountSearchCondition accountSearchCondition,Pageable pageable);
}
