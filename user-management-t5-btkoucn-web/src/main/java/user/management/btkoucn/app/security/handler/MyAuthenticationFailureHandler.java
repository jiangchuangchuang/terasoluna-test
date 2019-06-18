package user.management.btkoucn.app.security.handler;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.service.account.AccountSharedService;
import user.management.btkoucn.domain.service.authenticate.exception.MyUsernameNotFoundException;
import user.management.btkoucn.domain.service.login.LoginService;

public class MyAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

    @Inject
    AccountSharedService accountSharedService;
    
	@Inject
	LoginService loginService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String userId = request.getParameter("userId");
		// set failed message
		String status = Constant.LOGIN_ERROR_FAILED;
		if (exception instanceof BadCredentialsException) {
			if(!StringUtils.isEmpty(userId)) {
				status = Constant.LOGIN_ERROR_BADCREDENTIALS;
				// login_failed_count + 1
				loginService.updateLoginFailedCount(userId);
				
				// ログイン失敗回数の取得
				Account account = accountSharedService.findOne(userId);
				
				Integer loginFailedCount=account.getLoginFailedCount();

				// login_failed_count = max の場合：locked
				if (Constant.LOGIN_FAILED_COUNT_MAX == loginFailedCount){
					loginService.updateLoginFailedLocked(userId);
					status = Constant.LOGIN_ERROR_TOBELOCKED;
				}
				response.sendRedirect(request.getContextPath()+ "/login/login?error=" + status + "&userId=" + userId + "&loginFailedCount=" + String.valueOf(loginFailedCount));
			}else {
				response.sendRedirect(request.getContextPath()+ "/login/login?error=" + status);
			}
			
		}else if (exception.getCause() instanceof MyUsernameNotFoundException) {
			response.sendRedirect(request.getContextPath()+ "/login/login?error=" + Constant.LOGIN_ERROR_USERNAMENOTFOUND + "");
		}else if (exception instanceof LockedException){
			response.sendRedirect(request.getContextPath()+ "/login/login?error=" + Constant.LOGIN_ERROR_LOCKED + "&userId=" + userId);
		}else if (exception instanceof DisabledException){
			response.sendRedirect(request.getContextPath()+ "/login/login?error=" + Constant.LOGIN_ERROR_DISABLED + "&userId=" + userId);			
		}
		else {
			response.sendRedirect(request.getContextPath()+ "/login/login?error=" + status);
		}

	}

}
