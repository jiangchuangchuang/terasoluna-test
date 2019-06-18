package user.management.btkoucn.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {
	private String field;

	private String confirmField;

	private String message;

	@Override
	public void initialize(Confirm constraintAnnotation) {
		field = constraintAnnotation.field();
		confirmField = constraintAnnotation.confirmField();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Object fieldValue = beanWrapper.getPropertyValue(field);
		Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField);
		if(StringUtils.isEmpty(fieldValue) || StringUtils.isEmpty(confirmFieldValue)) {
			return true;
		}
		boolean matched = ObjectUtils.nullSafeEquals(fieldValue,
				confirmFieldValue);
		if (matched) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(field).addConstraintViolation();
			return false;
		}
	}

}
