package user.management.btkoucn.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class NotEmptySizeCheckValidator implements ConstraintValidator<NotEmptySizeCheck, Object> {

	private int min;
	
	private int max;
	
	private String message;

	@Override
	public void initialize(NotEmptySizeCheck constraintAnnotation) {
		message = constraintAnnotation.message();
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			return true;
		}
		if((String.valueOf(value).length() < min
				|| String.valueOf(value).length() > max)){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
