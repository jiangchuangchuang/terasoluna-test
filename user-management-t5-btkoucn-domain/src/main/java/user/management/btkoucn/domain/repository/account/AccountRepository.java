package user.management.btkoucn.domain.repository.account;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.AccountSearchCondition;

public interface AccountRepository {
	 Account findOne(String userId);
	 
	 long updateLoginStutas(String userId);
	 
	 long updatePassword(Account account);
	 
	 List<Account> findByCondition(@Param("user")AccountSearchCondition accountSearchCondition,
			 @Param("pageable")Pageable pageable);
	 
	 Long countByCondition(AccountSearchCondition accountSearchCondition);
	 
	 Long checkUpdateTime(@Param("userId") String userId,
			 @Param("accountUpdateTime") String accountUpdateTim);

	 Long updateAccount(@Param("account") Account account);

	 Long checkAccountExist(@Param("userId") String userId);
	 
	 Long insertAccount(Account account);
	 
	 Long deleteAccount(@Param("userId") String userId);

	 Long updateRoleAllEnabled(@Param("userId") String userId);
	 
	 Long insertRole(@Param("userId") String userId,
			 @Param("role") String role);

	 Long deleteRole(@Param("userId") String userId);

	 Long checkRoleExist(@Param("userId") String userId,
			 @Param("role") String role);

	 Long updateLoginFailedCount(@Param("userId") String userId);

	 Long updateLoginFailedLocked(@Param("userId") String userId);
}
