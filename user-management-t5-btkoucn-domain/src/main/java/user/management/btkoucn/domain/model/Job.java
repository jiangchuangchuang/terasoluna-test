package user.management.btkoucn.domain.model;

import java.io.Serializable;

public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer jobSeq;
	private String jobName;
	private String returnValue;
	private String jobType;
	private String updateTime;
	private String addTime;
	/**
	 * @return the jobSeq
	 */
	public Integer getJobSeq() {
		return jobSeq;
	}
	/**
	 * @param jobSeq the jobSeq to set
	 */
	public void setJobSeq(Integer jobSeq) {
		this.jobSeq = jobSeq;
	}
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * @return the returnValue
	 */
	public String getReturnValue() {
		return returnValue;
	}
	/**
	 * @param returnValue the returnValue to set
	 */
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	

}
