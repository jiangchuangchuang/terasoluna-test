package user.management.btkoucn.domain.service.file;

import java.io.Serializable;
import java.util.Map;

import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.model.Account;



public class FileUploadOutputBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ResultMessages resultMessages;
	Map<Integer,Account> accountList;
	/**
	 * @return the resultMessages
	 */
	public ResultMessages getResultMessages() {
		return resultMessages;
	}
	/**
	 * @param resultMessages the resultMessages to set
	 */
	public void setResultMessages(ResultMessages resultMessages) {
		this.resultMessages = resultMessages;
	}
	/**
	 * @return the accountList
	 */
	public Map<Integer, Account> getAccountList() {
		return accountList;
	}
	/**
	 * @param accountList the accountList to set
	 */
	public void setAccountList(Map<Integer, Account> accountList) {
		this.accountList = accountList;
	}
}
