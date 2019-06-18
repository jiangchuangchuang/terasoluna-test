package user.management.btkoucn.domain.model;

import java.io.Serializable;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String role;
	private String roleUpdateTime;
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
	 * @return the roleUpdateTime
	 */
	public String getRoleUpdateTime() {
		return roleUpdateTime;
	}
	/**
	 * @param roleUpdateTime the roleUpdateTime to set
	 */
	public void setRoleUpdateTime(String roleUpdateTime) {
		this.roleUpdateTime = roleUpdateTime;
	}
	
}
