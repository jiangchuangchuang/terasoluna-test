package user.management.btkoucn.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class OnlySizeCheckValidator implements ConstraintValidator<OnlySizeCheck, Object> {

	private int length;
	
	private String message;

	@Override
	public void initialize(OnlySizeCheck constraintAnnotation) {
		message = constraintAnnotation.message();
		length = constraintAnnotation.length();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			return true;
		}
		if(String.valueOf(value).length() != length){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
