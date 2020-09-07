package cn.nrzt.cps.archives.po;

import java.util.List;

public class CDiagnosisRuleDetail {

	
//	private String diagnosisRuleDetailId;
	private Integer dataType;
	private Integer calcMode;
	private String calcModeName;
	private String dataTypeName;
	private String expr;
	private String diagnosisRuleId;
	private String dataId;
	
	private List<CDataId> cDataIds;
	private List<String> dataIds;
	
	
	public String getCalcModeName() {
		return calcModeName;
	}
	public void setCalcModeName(String calcModeName) {
		this.calcModeName = calcModeName;
	}
	public String getDataTypeName() {
		return dataTypeName;
	}
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
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
//	public String getDiagnosisRuleDetailId() {
//		return diagnosisRuleDetailId;
//	}
//	public void setDiagnosisRuleDetailId(String diagnosisRuleDetailId) {
//		this.diagnosisRuleDetailId = diagnosisRuleDetailId;
//	}
	public Integer getDataType() {
		return dataType;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public Integer getCalcMode() {
		return calcMode;
	}
	public void setCalcMode(Integer calcMode) {
		this.calcMode = calcMode;
	}
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	public String getDiagnosisRuleId() {
		return diagnosisRuleId;
	}
	public void setDiagnosisRuleId(String diagnosisRuleId) {
		this.diagnosisRuleId = diagnosisRuleId;
	}
	
	
}
