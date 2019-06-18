package user.management.btkoucn.app.password;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import user.management.btkoucn.app.validation.Confirm;
import user.management.btkoucn.app.validation.MinSizeCheck;
import user.management.btkoucn.app.validation.NumberLetterCheck;
import user.management.btkoucn.app.validation.PasswordHistoryCheck;

@Confirm(field = "password",confirmField="passwordConfirm")
public class PasswordForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	@NotEmpty
	@MinSizeCheck(min=6)
	@NumberLetterCheck
	@PasswordHistoryCheck
	private String password;
	
	@NotEmpty
	@MinSizeCheck(min=6)
	@NumberLetterCheck
	private String passwordConfirm;
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

}
