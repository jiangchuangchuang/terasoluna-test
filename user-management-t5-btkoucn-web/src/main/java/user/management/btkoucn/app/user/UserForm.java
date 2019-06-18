package user.management.btkoucn.app.user;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.terasoluna.gfw.common.codelist.ExistInCodeList;

import user.management.btkoucn.app.validation.Confirm;
import user.management.btkoucn.app.validation.DateCheck;
import user.management.btkoucn.app.validation.MaxSizeCheck;
import user.management.btkoucn.app.validation.MinSizeCheck;
import user.management.btkoucn.app.validation.NotEmptySizeCheck;
import user.management.btkoucn.app.validation.NumberCheck;
import user.management.btkoucn.app.validation.NumberLetterCheck;
import user.management.btkoucn.app.validation.OnlySizeCheck;
import user.management.btkoucn.app.validation.PasswordHistoryCheck;
import user.management.btkoucn.domain.model.Role;

@Confirm(field="password",confirmField="passwordConfirm",groups={UserForm.Insert.class,UserForm.Update.class})
public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static interface Insert {};
	public static interface Update {};
	public static interface Search {};
	
	@NotEmpty(groups={Insert.class})
	@NumberCheck(groups={Insert.class,Search.class})
	@OnlySizeCheck(length=4,groups={Insert.class,Search.class})
	private String userId;

	@NotEmpty(groups={Insert.class,Update.class})
	@MaxSizeCheck(max=30,groups={Insert.class,Search.class,Update.class})
	private String username;

	@NotEmpty(groups={Insert.class,Update.class})
	@DateCheck(pattern="yyyy/MM/dd",groups={Insert.class,Search.class,Update.class})
	private String birthDay;

	@NotEmpty(groups={Insert.class,Update.class})
	@MaxSizeCheck(max=60,groups={Insert.class,Search.class,Update.class})
	private String address;
	

	@NotEmpty(groups={Insert.class,Update.class})
	@NotEmptySizeCheck(min=10,max=11,groups={Insert.class,Search.class,Update.class})
	@NumberCheck(groups={Insert.class,Search.class,Update.class})
	private String tenNum;

	@ExistInCodeList(codeListId="CL_ROLES",groups={Search.class})
	private String role;
	
	@ExistInCodeList(codeListId="CL_LOGINSTATUS",groups={Search.class})
	private String loginStatus;

	private List<Role> roles;
	
	private List<String> roleList;
	
	@NotEmpty(groups={Insert.class})
	@MinSizeCheck(min=6,groups={Insert.class,Update.class})
	@NumberLetterCheck(groups={Insert.class,Update.class})
	@PasswordHistoryCheck(formName="userForm",groups={Update.class})
	private String password;
	
	@NotEmpty(groups={Insert.class})
	@MinSizeCheck(min=6,groups={Insert.class,Update.class})
	@NumberLetterCheck(groups={Insert.class,Update.class})
	private String passwordConfirm;

	private String passwordHistory;
	private String accountUpdateTime;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the tenNum
	 */
	public String getTenNum() {
		return tenNum;
	}
	/**
	 * @param tenNum the tenNum to set
	 */
	public void setTenNum(String tenNum) {
		this.tenNum = tenNum;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the loginStatus
	 */
	public String getLoginStatus() {
		return loginStatus;
	}
	/**
	 * @param loginStatus the loginStatus to set
	 */
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	/**
	 * @return the roleList
	 */
	public List<String> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	/**
	 * @return the accountUpdateTime
	 */
	public String getAccountUpdateTime() {
		return accountUpdateTime;
	}
	/**
	 * @param accountUpdateTime the accountUpdateTime to set
	 */
	public void setAccountUpdateTime(String accountUpdateTime) {
		this.accountUpdateTime = accountUpdateTime;
	}
	/**
	 * @return the passwordHistory
	 */
	public String getPasswordHistory() {
		return passwordHistory;
	}
	/**
	 * @param passwordHistory the passwordHistory to set
	 */
	public void setPasswordHistory(String passwordHistory) {
		this.passwordHistory = passwordHistory;
	}


}
