package user.management.btkoucn.domain.common;

import org.springframework.beans.factory.annotation.Value;

public class Constant {

	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER"; 
	public static final String LOGIN_STATUS_INIT = "INIT";
	public static final String LOGIN_STATUS_ACTV = "ACTV"; 
	public static final String LOGIN_STATUS_DEL = "DEL";
	public static final String LOGIN_STATUS_LOCKED = "LOCKED";
	public static final String LOGIN_ERROR_LOCKED = "Locked";
	public static final String LOGIN_ERROR_BADCREDENTIALS = "badCredentials";
	public static final String LOGIN_ERROR_TOBELOCKED = "toBeLocked";
	public static final String LOGIN_ERROR_USERNAMENOTFOUND = "usernameNotFound";
	public static final String LOGIN_ERROR_DISABLED = "Disabled";
	public static final String LOGIN_ERROR_FAILED = "failed";

	public static int PASSWORD_MONTHS;	

	/**
	 * @param pASSWORD_MONTHS the pASSWORD_MONTHS to set
	 */
	@Value("${passwordMonths}")
	public void setPASSWORD_MONTHS(int pASSWORD_MONTHS) {
		PASSWORD_MONTHS = pASSWORD_MONTHS;
	}

	public static int LOGIN_FAILED_COUNT_MAX;

	/**
	 * @param lOGIN_FAILED_COUNT_MAX the lOGIN_FAILED_COUNT_MAX to set
	 */
	@Value("${loginFailedCountMax}")
	public void setLOGIN_FAILED_COUNT_MAX(int lOGIN_FAILED_COUNT_MAX) {
		LOGIN_FAILED_COUNT_MAX = lOGIN_FAILED_COUNT_MAX;
	}
	
	public static int PAGE_SIZE=0;
	/**
	 * @param pAGE_SIZE the pAGE_SIZE to set
	 */
	@Value("${pageSize}")
	public void setPAGE_SIZE(int pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}
	
	public static String LOG_PATH="";

	/**
	 * @param lOG_PATH the lOG_PATH to set
	 */
	@Value("${logPath}")
	public void setLOG_PATH(String lOG_PATH) {
		LOG_PATH = lOG_PATH;
	}
}
