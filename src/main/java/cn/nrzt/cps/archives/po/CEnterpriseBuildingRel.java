package cn.nrzt.cps.archives.po;

/**
 * The persistent class for the C_ENTERPRISE_BUILDING_REL database table.
 * 
 */
public class CEnterpriseBuildingRel{

	private Object buildingNo;

	private Object enterpriseId;

	private Object relId;

	public CEnterpriseBuildingRel() {
	}

	public Object getBuildingNo() {
		return this.buildingNo;
	}

	public void setBuildingNo(Object buildingNo) {
		this.buildingNo = buildingNo;
	}

	public Object getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Object enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Object getRelId() {
		return this.relId;
	}

	public void setRelId(Object relId) {
		this.relId = relId;
	}

}