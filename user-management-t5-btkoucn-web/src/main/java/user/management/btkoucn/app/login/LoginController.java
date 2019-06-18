package user.management.btkoucn.app.login;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.common.Constant;

@Controller
@RequestMapping("/login")
public class LoginController {
	
    @RequestMapping(value="/loginPage",method=RequestMethod.GET)
    public String index(Model model) {
        return "login/login";
    }
    
    @ModelAttribute
    public LoginForm setupForm() {
        return new LoginForm();
    }

    @RequestMapping(value="/dologin",method=RequestMethod.POST)
    public String login(@Validated LoginForm form, BindingResult result,Model model) {
        
        if (result.hasErrors()) {
        	return "login/login";
        }
        return "forward:/authenticate";
    }
    
    @RequestMapping(value="/login",params={"error"})
    public String loginError(Model model,@Param("error") String error, 
    		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
    		@RequestParam(value = "loginFailedCount", required = false, defaultValue = "") String loginFailedCount) { 		
    	if(!StringUtils.isEmpty(error)) {
    		if(Constant.LOGIN_ERROR_BADCREDENTIALS.equals(error)){
	        	ResultMessages message=ResultMessages.error().add("e.login.badCredentials",userId,
	        			loginFailedCount,
	        			Constant.LOGIN_FAILED_COUNT_MAX);
	        	model.addAttribute(message);
    		}else if(Constant.LOGIN_ERROR_DISABLED.equals(error)){
	        	ResultMessages message=ResultMessages.error().add("e.login.disabled",userId);
	        	model.addAttribute(message);
    		}else if(Constant.LOGIN_ERROR_LOCKED.equals(error)){
	        	ResultMessages message=ResultMessages.error().add("e.login.locked",userId);
	        	model.addAttribute(message);
    		}else if(Constant.LOGIN_ERROR_TOBELOCKED.equals(error)){
	        	ResultMessages message=ResultMessages.error().add("e.login.toBeLocked",userId,Constant.LOGIN_FAILED_COUNT_MAX);
	        	model.addAttribute(message);
    		}else {
	        	ResultMessages message=ResultMessages.error().add("e.login.failed");
	        	model.addAttribute(message);
    		}
    	}
        return "login/login"; 
    }
}