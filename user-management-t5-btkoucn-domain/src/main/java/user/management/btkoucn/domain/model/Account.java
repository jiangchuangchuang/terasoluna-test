package user.management.btkoucn.domain.model;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDate;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String birthDay;
	private String address;
	private String tenNum;
	private List<Role> roles;
	private String password;
	private String passwordHistory;
	private String loginStatus;
	private Integer loginFailedCount;
	
	private String accountUpdateTime;
	private LocalDate passwordExpired;
	
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
	 * @return the loginFailedCount
	 */
	public Integer getLoginFailedCount() {
		return loginFailedCount;
	}
	/**
	 * @param loginFailedCount the loginFailedCount to set
	 */
	public void setLoginFailedCount(Integer loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
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
	 * @return the passwordExpired
	 */
	public LocalDate getPasswordExpired() {
		return passwordExpired;
	}
	/**
	 * @param passwordExpired the passwordExpired to set
	 */
	public void setPasswordExpired(LocalDate passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

}
