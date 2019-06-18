package user.management.btkoucn.app.validation;

import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class DateCheckValidator implements ConstraintValidator<DateCheck, Object> {

	private String pattern;

	private String message;

	@Override
	public void initialize(DateCheck constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value) || StringUtils.isEmpty(pattern)) {
			return true;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setLenient(false);
			format.parse(String.valueOf(value));
		} catch (Exception e) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		return true;
	}

}
