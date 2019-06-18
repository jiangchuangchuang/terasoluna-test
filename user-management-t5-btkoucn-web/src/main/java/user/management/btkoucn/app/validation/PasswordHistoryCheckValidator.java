package user.management.btkoucn.app.validation;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import user.management.btkoucn.app.user.UserForm;
import user.management.btkoucn.domain.service.authenticate.AccountUserDetails;

public class PasswordHistoryCheckValidator implements ConstraintValidator<PasswordHistoryCheck, Object> {

	private String message;
	private String formName;
	
	@Inject
    PasswordEncoder passwordEncoder;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void initialize(PasswordHistoryCheck constraintAnnotation) {
		message = constraintAnnotation.message();
		formName = constraintAnnotation.formName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if(StringUtils.isEmpty(value)) {
			return true;
		}
		
		String password = "";
		String passwordHistory = "";
		if(StringUtils.isEmpty(formName)){
			UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			AccountUserDetails userDetails = (AccountUserDetails) authenticationToken.getPrincipal();
			password = userDetails.getAccount().getPassword();
			passwordHistory = userDetails.getAccount().getPasswordHistory();
		}else {
			UserForm userForm=(UserForm)session.getAttribute(formName);
			password = userForm.getPassword();
			passwordHistory = userForm.getPasswordHistory();
		}
		
		if (passwordEncoder.matches((CharSequence) value, password)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}else if(passwordEncoder.matches((CharSequence) value, passwordHistory)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
