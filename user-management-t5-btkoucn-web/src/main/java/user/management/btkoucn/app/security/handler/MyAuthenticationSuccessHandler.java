package user.management.btkoucn.app.security.handler;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import user.management.btkoucn.app.common.CommonUtils;
import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.service.authenticate.AccountUserDetails;
import user.management.btkoucn.domain.service.login.LoginService;

public class MyAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {


	@Inject
	LoginService loginService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				AccountUserDetails userDetails = (AccountUserDetails) principal;
				if(Constant.LOGIN_STATUS_INIT.equals(userDetails.getAccount().getLoginStatus())){
					// status is init
					response.sendRedirect(request.getContextPath()+ "/password/update");
				}else if(CommonUtils.passwordIsExpired(userDetails.getAccount().getPasswordExpired())){
					// password is expired
					response.sendRedirect(request.getContextPath()+ "/password/update");
				}else {
		    		loginService.updateLoginStutas(userDetails.getAccount().getUserId());
		    		response.sendRedirect(request.getContextPath()+ "/home");
		    	}
			}
		}

	}

}
