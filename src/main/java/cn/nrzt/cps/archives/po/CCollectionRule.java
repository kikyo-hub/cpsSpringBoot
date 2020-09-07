package cn.nrzt.cps.archives.po;

import java.util.List;
import java.util.Set;

/**
 * The persistent class for the C_COLLECTION_RULE database table.
 * 
 */
public class CCollectionRule {
	private Integer collectionRuleId;

	private String ruleName;

	private String collectionType;

	private Integer collectionInterval;

	private String startTime;

	private String endTime;

	private String runningPeriod;

	private String collectionMode;

	private String saveTimeFlag;

	private String ruleDesc;
	
	private String collectionRuleNo;

	private Set<CMonitor> monitors;
	
	private List<CDataId> cDataIds;
	
	private List<String> dataIds;
	
	public List<String> getDataIds() {
		return dataIds;
	}

	public void setDataIds(List<String> dataIds) {
		this.dataIds = dataIds;
	}

	public List<CDataId> getcDataIds() {
		return cDataIds;
	}

	public void setcDataIds(List<CDataId> cDataIds) {
		this.cDataIds = cDataIds;
	}

	public Integer getCollectionRuleId() {
		return collectionRuleId;
	}

	public void setCollectionRuleId(Integer collectionRuleId) {
		this.collectionRuleId = collectionRuleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public Integer getCollectionInterval() {
		return collectionInterval;
	}

	public void setCollectionInterval(Integer collectionInterval) {
		this.collectionInterval = collectionInterval;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCollectionMode() {
		return collectionMode;
	}

	public void setCollectionMode(String collectionMode) {
		this.collectionMode = collectionMode;
	}

	public String getRunningPeriod() {
		return runningPeriod;
	}

	public void setRunningPeriod(String runningPeriod) {
		this.runningPeriod = runningPeriod;
	}

	public String getCollectionRuleNo() {
		return collectionRuleNo;
	}

	public void setCollectionRuleNo(String collectionRuleNo) {
		this.collectionRuleNo = collectionRuleNo;
	}

	public Set<CMonitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(Set<CMonitor> monitors) {
		this.monitors = monitors;
	}

	public String getSaveTimeFlag() {
		return saveTimeFlag;
	}

	public void setSaveTimeFlag(String saveTimeFlag) {
		this.saveTimeFlag = saveTimeFlag;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

}