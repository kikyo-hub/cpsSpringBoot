package cn.nrzt.cps.archives.po;

import java.util.List;

/**
 * The persistent class for the C_DIAGNOSIS_RULE database table.
 * 
 */
public class CDiagnosisRule {
	private String diagnosisRuleId;
	private String diagnosisName;
	private String diagnosisType;
	private String calcMode;
	private String expr;
	private String description;
	private String calcPeriodType;
	private String calcInterval;
	private List<CDataId> cDataIds;
	private List<String> dataIds;
	private List<CDiagnosisRuleDetail> cDiagnosisRuleDetails;
	
	public List<CDiagnosisRuleDetail> getcDiagnosisRuleDetails() {
		return cDiagnosisRuleDetails;
	}
	public void setcDiagnosisRuleDetails(List<CDiagnosisRuleDetail> cDiagnosisRuleDetails) {
		this.cDiagnosisRuleDetails = cDiagnosisRuleDetails;
	}
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
	public String getDiagnosisRuleId() {
		return diagnosisRuleId;
	}
	public void setDiagnosisRuleId(String diagnosisRuleId) {
		this.diagnosisRuleId = diagnosisRuleId;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDiagnosisType() {
		return diagnosisType;
	}
	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
	}
	public String getCalcMode() {
		return calcMode;
	}
	public void setCalcMode(String calcMode) {
		this.calcMode = calcMode;
	}
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCalcPeriodType() {
		return calcPeriodType;
	}
	public void setCalcPeriodType(String calcPeriodType) {
		this.calcPeriodType = calcPeriodType;
	}
	public String getCalcInterval() {
		return calcInterval;
	}
	public void setCalcInterval(String calcInterval) {
		this.calcInterval = calcInterval;
	}
	

	
}