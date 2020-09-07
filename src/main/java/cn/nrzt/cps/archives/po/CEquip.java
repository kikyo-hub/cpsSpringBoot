package cn.nrzt.cps.archives.po;

/**
 * The persistent class for the C_EQUIP database table.
 * 
 */
public class CEquip{

	private String equipId;

	private String commAddress;

	private String equipType;

	private String commMode;

	private String protocolType;

	private String equipMode;

	private String upEquipId;

	private String equipName;

	private String assetNo;

	private String commModeDesc;
	private String equipTypeDesc;
	private String protocolTypeDesc;
	private String equipModeDesc;

	private String ct;
	private String pt;
	private int configNo;
	private String macthResult;
	
	
	public int getConfigNo() {
		return configNo;
	}

	public void setConfigNo(int configNo) {
		this.configNo = configNo;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	private CMonitor monitor;

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getCommAddress() {
		return commAddress;
	}

	public void setCommAddress(String commAddress) {
		this.commAddress = commAddress;
	}

	public String getEquipType() {
		return equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public String getCommMode() {
		return commMode;
	}

	public void setCommMode(String commMode) {
		this.commMode = commMode;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getEquipMode() {
		return equipMode;
	}

	public void setEquipMode(String equipMode) {
		this.equipMode = equipMode;
	}

	public String getUpEquipId() {
		return upEquipId;
	}

	public void setUpEquipId(String upEquipId) {
		this.upEquipId = upEquipId;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getCommModeDesc() {
		return commModeDesc;
	}

	public void setCommModeDesc(String commModeDesc) {
		this.commModeDesc = commModeDesc;
	}

	public String getEquipTypeDesc() {
		return equipTypeDesc;
	}

	public void setEquipTypeDesc(String equipTypeDesc) {
		this.equipTypeDesc = equipTypeDesc;
	}

	public String getProtocolTypeDesc() {
		return protocolTypeDesc;
	}

	public void setProtocolTypeDesc(String protocolTypeDesc) {
		this.protocolTypeDesc = protocolTypeDesc;
	}

	public String getEquipModeDesc() {
		return equipModeDesc;
	}

	public void setEquipModeDesc(String equipModeDesc) {
		this.equipModeDesc = equipModeDesc;
	}

	public CMonitor getMonitor() {
		return monitor;
	}

	public void setMonitor(CMonitor monitor) {
		this.monitor = monitor;
	}

	public String getMacthResult() {
		return macthResult;
	}

	public void setMacthResult(String macthResult) {
		this.macthResult = macthResult;
	}
     

}