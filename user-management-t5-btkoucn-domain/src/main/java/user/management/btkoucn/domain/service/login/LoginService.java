package user.management.btkoucn.domain.service.login;

public interface LoginService {
	void updateLoginStutas(String userId);

	Long updateLoginFailedCount(String userId);

	Long updateLoginFailedLocked(String userId);
}
