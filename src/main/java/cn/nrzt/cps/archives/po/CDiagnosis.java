package cn.nrzt.cps.archives.po;

import cn.nrzt.cps.user.po.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistent class for the C_DIAGNOSIS database table.
 * 
 */

public class CDiagnosis {

	private String diagnosisId;

	private String diagnosisName;

	private String diagnosisRuleId;
	
	private Object diagnosisRuleList;
	
	private Object monitorDMRids;

	public Object getMonitorDMRids() {
		return monitorDMRids;
	}
	public void setMonitorDMRids(Object monitorDMRids) {
		this.monitorDMRids = monitorDMRids;
	}
	private String diagnosisType;

	private String creator;

	private Object createTime;
	
	private String buildingNo;
	
	private String floorNo;
	
	private String diagnosisTypeDesc;

	private Object electro;
	
	private Object environment;

	private Object air;
	
	private String diagnosisRuleDesc;
	
	private Set<CFloor> floor;
	
	private String floorName;
	
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public Set<CFloor> getFloor() {
		return floor;
	}
	public void setFloor(Set<CFloor> floor) {
		this.floor = floor;
	}
	public Object getDiagnosisRuleList() {
		return diagnosisRuleList;
	}
	public void setDiagnosisRuleList(Object diagnosisRuleList) {
		this.diagnosisRuleList = diagnosisRuleList;
	}
	public String getDiagnosisRuleDesc() {
		return diagnosisRuleDesc;
	}
	public void setDiagnosisRuleDesc(String diagnosisRuleDesc) {
		this.diagnosisRuleDesc = diagnosisRuleDesc;
	}
	public Object getAir() {
		return air;
	}
	public void setAir(Object air) {
		this.air = air;
	}
	public Object getElectro() {
		return electro;
	}
	public void setElectro(Object electro) {
		this.electro = electro;
	}
	public Object getEnvironment() {
		return environment;
	}
	public void setEnvironment(Object environment) {
		this.environment = environment;
	}
	private Set<EDiagnosisCurve> diagnosisCurves;

	private Set<CMonitor> monitor;
	// 创建者
	private User user;
	public String getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDiagnosisRuleId() {
		return diagnosisRuleId;
	}
	public void setDiagnosisRuleId(String diagnosisRuleId) {
		this.diagnosisRuleId = diagnosisRuleId;
	}
	public String getDiagnosisType() {
		return diagnosisType;
	}
	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Object getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Object createTime) {
		this.createTime = createTime;
	}
	

	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getDiagnosisTypeDesc() {
		return diagnosisTypeDesc;
	}
	public void setDiagnosisTypeDesc(String diagnosisTypeDesc) {
		this.diagnosisTypeDesc = diagnosisTypeDesc;
	}
	public Set<EDiagnosisCurve> getDiagnosisCurves() {
		return diagnosisCurves;
	}
	public void setDiagnosisCurves(Set<EDiagnosisCurve> diagnosisCurves) {
		this.diagnosisCurves = diagnosisCurves;
	}
	public Set<CMonitor> getMonitor() {
		return monitor;
	}
	public void setMonitor(Set<CMonitor> monitor) {
		this.monitor = monitor;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}