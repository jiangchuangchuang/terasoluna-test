package user.management.btkoucn.app.user;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import user.management.btkoucn.app.common.CommonUtils;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.AccountSearchCondition;
import user.management.btkoucn.domain.service.account.AccountSharedService;
import user.management.btkoucn.domain.service.authenticate.AccountUserDetails;
import user.management.btkoucn.domain.service.user.UserDeleteService;
import user.management.btkoucn.domain.service.user.UserInsertService;
import user.management.btkoucn.domain.service.user.UserSearchService;
import user.management.btkoucn.domain.service.user.UserUpdateService;

@Controller
@RequestMapping("user")
@SessionAttributes(types = { UserForm.class })
@TransactionTokenCheck("user")
public class UserController {

	@Inject
	UserSearchService userSearchService;

	@Inject
	UserUpdateService userUpdateService;

	@Inject
	UserDeleteService userDeleteService;

	@Inject
	UserInsertService userInsertService;
	
	@Inject
	Mapper beanMapper;
	
	@Inject
	AccountSharedService accountSharedService;
	
	@ModelAttribute
    public UserForm setupForm() {
        return new UserForm();
    }
	
	@Inject
	UserFormValidator userFormValidator;
	
    @InitBinder("userForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }
    
	@RequestMapping(value="/search",params="form",method=RequestMethod.GET)
    public String search(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
    	return "redirect:/user/searchPage";
    }
	
	@RequestMapping(value="/searchPage",method=RequestMethod.GET)
    public String searchPage() {
    	return "user/search";
    }

	@RequestMapping(value="/search",params="back",method=RequestMethod.GET)
    public String searchBack(Model model){
    	return "user/search";
    }
	
    @RequestMapping(value="/search")
    public String searchDo(@Validated({UserForm.Search.class,Default.class}) UserForm form, BindingResult result,Model model,
    		@PageableDefault(size=5) Pageable pageable) {
    	
    	if(result.hasErrors()){
    		return "user/search";
    	}
    	try {
    		AccountSearchCondition accountSearchCondition = beanMapper.map(form, AccountSearchCondition.class);
    		Page<Account> page = userSearchService.searchUsers(accountSearchCondition, pageable);
    		model.addAttribute("page", page);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
            return "user/search";
		}
    	model.addAttribute(form);
    	return "user/searchResult";
    }
    
	@RequestMapping(value="/update",params="userId",method=RequestMethod.GET)
    public String update(Model model,String userId) {
		Account account = accountSharedService.findOne(userId);
		UserForm userForm =beanMapper.map(account,UserForm.class);
		userForm.setRoleList(CommonUtils.convertRolesToStringList(userForm.getRoles()));
		model.addAttribute("userForm",userForm);
    	return "user/update";
    }
	
    @RequestMapping(value="/update",params="confirm",method=RequestMethod.POST)
    @TransactionTokenCheck(value="/update",type=TransactionTokenType.BEGIN)
    public String updateConfirm(@Validated({UserForm.Update.class,Default.class}) UserForm form, BindingResult result,Model model) {
    	
    	if(result.hasErrors()){
    		return "user/update";
    	}
    	model.addAttribute("userForm",form);
    	return "user/updateConfirm";
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @TransactionTokenCheck(value="/update",type=TransactionTokenType.IN)
    public String updateDo(@ModelAttribute("userForm") UserForm form, Model model
    		,RedirectAttributes redirectAttributes,SessionStatus sessionStatus) {
   
    	try {
    		Account account = beanMapper.map(form, Account.class);
    		account.setRoles(CommonUtils.convertStringToRolesList(form.getRoleList(), form));
    		userUpdateService.updateUser(account);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
            return "user/update";
		}
    	sessionStatus.setComplete();
    	redirectAttributes.addFlashAttribute("userId", form.getUserId());
    	return "redirect:/user/update?complete";
    }
    
    @RequestMapping(value="/update",params="complete",method=RequestMethod.GET) 
    public String updateComplete(Model model,@AuthenticationPrincipal AccountUserDetails userDetails) {
    	
    	Map<String,Object> modelMap=model.asMap();
    	String userId= "";
    	if(modelMap.containsKey("userId")){
    		userId = (String)modelMap.get("userId");
    	}
    	ResultMessages messages=ResultMessages.info().add("i.user.update.success", userId);
    	if(userId.equals(userDetails.getAccount().getUserId())){
    		Account account = accountSharedService.findOne(userId);
    		boolean rolesIsChanged = CommonUtils.rolesIsChanged(userDetails.getAccount().getRoles(),account.getRoles());
    		if(!userDetails.getAccount().getUsername().equals(account.getUsername())
    			|| !userDetails.getAccount().getPassword().equals(account.getPassword())
    			|| rolesIsChanged){
    			CommonUtils.reloadUserAuthority(account);
    			if(rolesIsChanged){
    				messages.add(ResultMessage.fromCode("i.user.changed", new Object[]{}));
    			}
    		}
    	}
    	model.addAttribute(messages);
    	return "user/updateComplete";
    }
    
    @RequestMapping(value="/update",params="redo",method=RequestMethod.POST)
    public String updateRedo(@ModelAttribute("userForm") UserForm form, Model model) {
    	return "user/update";
    }
    

	@RequestMapping(value="/delete",params="userId",method=RequestMethod.GET)
    public String delete(Model model,String userId) {
		Account account = accountSharedService.findOne(userId);
		UserForm userForm =beanMapper.map(account,UserForm.class);
		userForm.setRoleList(CommonUtils.convertRolesToStringList(userForm.getRoles()));
		model.addAttribute("userForm",userForm);
    	return "user/delete";
    }
	
    @RequestMapping(value="/delete",params="confirm",method=RequestMethod.POST)
    @TransactionTokenCheck(value="/delete",type=TransactionTokenType.BEGIN)
    public String deleteConfirm(@ModelAttribute("userForm") UserForm form,Model model) {
    	return "user/deleteConfirm";
    }
    
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @TransactionTokenCheck(value="/delete",type=TransactionTokenType.IN)
    public String deleteDo(@ModelAttribute("userForm") UserForm form, Model model
    		,RedirectAttributes redirectAttributes,SessionStatus sessionStatus) {
   
    	try {
    		Account account = beanMapper.map(form, Account.class);
    		account.setRoles(CommonUtils.convertStringToRolesList(form.getRoleList(), form));
    		userDeleteService.deleteUser(account);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
            return "user/delete";
		}
    	sessionStatus.setComplete();
    	redirectAttributes.addFlashAttribute("userId", form.getUserId());
    	
    	return "redirect:/user/delete?complete";
    }
    
    @RequestMapping(value="/delete",params="complete",method=RequestMethod.GET) 
    public String deleteComplete(Model model,@AuthenticationPrincipal AccountUserDetails userDetails){
    	Map<String,Object> modelMap=model.asMap();
    	String userId= "";
    	if(modelMap.containsKey("userId")){
    		userId = (String)modelMap.get("userId");
    	}
    	ResultMessages messages=ResultMessages.info().add("i.user.delete.success", userId);
    	if(userId.equals(userDetails.getAccount().getUserId())){
    		CommonUtils.deleteUserAuthority();
        	messages.add(ResultMessage.fromCode("i.user.deleted", new Object[]{}));
    	}
    	model.addAttribute(messages);
    	return "user/deleteComplete";
    }
    
    @RequestMapping(value="/delete",params="redo",method=RequestMethod.POST)
    public String deleteRedo(@ModelAttribute("userForm") UserForm form, Model model) {
    	return "user/delete";
    }
    

	@RequestMapping(value="/insert",params="form",method=RequestMethod.GET)
    public String insert(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/user/insertPage";
    }
	
	@RequestMapping(value="/insertPage",method=RequestMethod.GET)
    public String insertPage(Model model) {
    	return "user/insert";
    }
	
    @RequestMapping(value="/insert",params="confirm",method=RequestMethod.POST)
    @TransactionTokenCheck(value="/insert",type=TransactionTokenType.BEGIN)
    public String insertConfirm(@Validated({UserForm.Insert.class,Default.class}) UserForm form, BindingResult result,Model model) {
    	
    	if(result.hasErrors()){
    		return "user/insert";
    	}
    	model.addAttribute("userForm",form);
    	return "user/insertConfirm";
    }
    
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    @TransactionTokenCheck(value="/insert",type=TransactionTokenType.IN)
    public String insertDo(@ModelAttribute("userForm") UserForm form, Model model
    		,RedirectAttributes redirectAttributes,SessionStatus sessionStatus) {
   
    	try {
    		Account account = beanMapper.map(form, Account.class);
    		account.setRoles(CommonUtils.convertStringToRolesList(form.getRoleList(), form));
    		userInsertService.insertUser(account);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
            return "user/insert";
		}

    	sessionStatus.setComplete();
    	redirectAttributes.addFlashAttribute("userId", form.getUserId());
    	
    	return "redirect:/user/insert?complete";
    }
    
    @RequestMapping(value="/insert",params="complete",method=RequestMethod.GET) 
    public String insertComplete(Model model){
    	Map<String,Object> modelMap=model.asMap();
    	String userId= "";
    	if(modelMap.containsKey("userId")){
    		userId = (String)modelMap.get("userId");
    	}    	
    	model.addAttribute(ResultMessages.info().add("i.user.insert.success", userId));
    	return "user/insertComplete";
    }
    
    @RequestMapping(value="/insert",params="redo",method=RequestMethod.POST)
    public String insertRedo(@ModelAttribute("userForm") UserForm form, Model model) {
    	return "user/insert";
    }
}
