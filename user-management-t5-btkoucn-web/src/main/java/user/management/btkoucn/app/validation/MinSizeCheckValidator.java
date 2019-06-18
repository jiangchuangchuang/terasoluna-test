package user.management.btkoucn.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class MinSizeCheckValidator implements ConstraintValidator<MinSizeCheck, Object> {

	private int min;
	
	private String message;

	@Override
	public void initialize(MinSizeCheck constraintAnnotation) {
		message = constraintAnnotation.message();
		min = constraintAnnotation.min();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			return true;
		}
		if(String.valueOf(value).length() < min){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
