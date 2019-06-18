package user.management.btkoucn.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class MaxSizeCheckValidator implements ConstraintValidator<MaxSizeCheck, Object> {

	private int max;
	
	private String message;

	@Override
	public void initialize(MaxSizeCheck constraintAnnotation) {
		message = constraintAnnotation.message();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			return true;
		}
		if(String.valueOf(value).length() > max){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
