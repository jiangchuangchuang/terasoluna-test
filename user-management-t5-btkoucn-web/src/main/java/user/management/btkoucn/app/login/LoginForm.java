package user.management.btkoucn.app.login;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    @NotEmpty
    private String userId;

	@NotEmpty
    private String password;

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
 

}
