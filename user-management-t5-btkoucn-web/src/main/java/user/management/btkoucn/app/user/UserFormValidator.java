package user.management.btkoucn.app.user;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.terasoluna.gfw.common.codelist.CodeList;

import user.management.btkoucn.app.user.UserForm.Insert;
import user.management.btkoucn.app.user.UserForm.Update;

@Component
public class UserFormValidator implements SmartValidator {

	@Inject
    @Named("CL_ROLES")
    CodeList roleCodeList;
	
	@Inject
    MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, new Object[] {});
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        if (ArrayUtils.contains(validationHints, Insert.class) ||
        		ArrayUtils.contains(validationHints, Update.class)) {

    		if (errors.hasFieldErrors("roleList")) {
    			return;
    		}

    		UserForm form = (UserForm) target;
    		List<String> roleList = form.getRoleList();
    		
    		if (roleList == null || roleList.size()==0){
    			 errors.rejectValue("roleList",
    	                   "userForm.role.required",new Object[]{messageSource.getMessage("userForm.role", null, Locale.getDefault())},"");
    		}
    		for (String t : roleList) {
				if(!roleCodeList.asMap().containsKey(t)){
					errors.rejectValue("roleList",
	    	                   "userForm.role.notExist",new Object[]{messageSource.getMessage("userForm.role", null, Locale.getDefault())},"");
					break;
				}
			}
    		 
        }
    }

}
