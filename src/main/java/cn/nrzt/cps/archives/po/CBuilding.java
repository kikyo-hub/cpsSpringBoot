package cn.nrzt.cps.archives.po;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the C_BUILDING database table.
 * 
 */
public class CBuilding{
	
	
	@Override
	public String toString() {
		return "CBuilding [buildingNo=" + buildingNo + ", buildingAddress=" + buildingAddress + ", buildingArea="
				+ buildingArea + ", buildingName=" + buildingName + ", floorCnt=" + floorCnt + ", buildingTime="
				+ buildingTime + ", longitude=" + longitude + ", latitude=" + latitude + ", buildingSteward="
				+ buildingSteward + ", companyNumb=" + companyNumb + ", enterprise=" + enterprise
				+ ", enterprisebuilder=" + enterprisebuilder + ", building_category=" + building_category
				+ ", employees=" + employees + ", floors=" + floors + ", monitors=" + monitors + ", diagnosis="
				+ diagnosis + "]";
	}

	private String buildingNo;

	private String buildingAddress;

	private Double buildingArea;

	private String buildingName;

	private Integer floorCnt;
	
	private Object buildingTime;
	
	private String longitude;
	
	private String latitude;
	
	private String buildingSteward;
	
	private Integer companyNumb;

	private CEnterprise enterprise;
	
    private CEnterpriseBuildingRel enterprisebuilder;
    
    private String building_category;
    
    private Integer employees;
    
	private LinkedHashSet<CFloor> floors;

	

	private List<CMonitor> monitors;
	
	private Set<CDiagnosis>diagnosis;
	

	public String getBuilding_category() {
		return building_category;
	}

	public void setBuilding_category(String building_category) {
		this.building_category = building_category;
	}

	public Integer getEmployees() {
		return employees;
	}

	public void setEmployees(Integer employees) {
		this.employees = employees;
	}

	public Object getBuildingTime() {
		return buildingTime;
	}

	public void setBuildingTime(Object buildingTime) {
		this.buildingTime = buildingTime;
	}
    
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getBuildingSteward() {
		return buildingSteward;
	}

	public void setBuildingSteward(String buildingSteward) {
		this.buildingSteward = buildingSteward;
	}

	public Integer getCompanyNumb() {
		return companyNumb;
	}

	public void setCompanyNumb(Integer companyNumb) {
		this.companyNumb = companyNumb;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public CEnterpriseBuildingRel getEnterprisebuilder() {
		return enterprisebuilder;
	}

	public void setEnterprisebuilder(CEnterpriseBuildingRel enterprisebuilder) {
		this.enterprisebuilder = enterprisebuilder;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public Double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getFloorCnt() {
		return floorCnt;
	}

	public void setFloorCnt(Integer floorCnt) {
		this.floorCnt = floorCnt;
	}

	public CEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(CEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public LinkedHashSet<CFloor> getFloors() {
		return floors;
	}

	public void setFloors(LinkedHashSet<CFloor> floors) {
		this.floors = floors;
	}

	public List<CMonitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<CMonitor> monitors) {
		this.monitors = monitors;
	}

	public Set<CDiagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Set<CDiagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}
	
}