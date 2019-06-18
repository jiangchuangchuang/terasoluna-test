package user.management.btkoucn.app.password;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import user.management.btkoucn.domain.service.authenticate.AccountUserDetails;
import user.management.btkoucn.domain.service.password.PasswordService;

@Controller
@RequestMapping("password")
public class PasswordController {

	@Inject
	PasswordService passwordService;
	
	@ModelAttribute
    public PasswordForm setupForm() {
        return new PasswordForm();
    }
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
    @TransactionTokenCheck(type=TransactionTokenType.BEGIN)
    public String update(Model model) {
    	
    	return "password/password";
    }
	
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @TransactionTokenCheck(type=TransactionTokenType.IN)
    public String update(@Validated PasswordForm form, BindingResult result,Model model,
    		@AuthenticationPrincipal AccountUserDetails userDetails) {
    	
    	if(result.hasErrors()){
    		return "password/password";
    	}
    	try {
        	passwordService.updatePassword(form.getUserId(), form.getPassword());
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
            return "password/password";
		}
    	
    	return "redirect:/password/update?complete";
    }
    
    @RequestMapping(value="/update",params="complete",method=RequestMethod.GET) 
    public String updateComplete(Model model) {
    	model.addAttribute(ResultMessages.info().add("i.password.001", ""));
    	return "password/passwordComplete";
    }
}
