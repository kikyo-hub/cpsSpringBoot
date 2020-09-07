package cn.nrzt.cps.archives.po;

import cn.nrzt.cps.user.po.User;

import java.util.Set;


/**
 * The persistent class for the C_MONITOR database table.
 * 
 */

public class CMonitor{
	private String monitorId;
	private String monitorName;
	private String deviceType;
	private String deviceTypeName;
	private String equipAddress;
	private String collectionRuleId;
	private String collectionRuleName;
	private String deviceTypeDesc;
	private String monitorDesc;
	private String buildingNo;
	private String buildingName;
	private String floorNo;
	private String floorName;
	private Object collectionRuleR;
	
	

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public Object getCollectionRuleR() {
		return collectionRuleR;
	}

	public void setCollectionRuleR(Object collectionRuleR) {
		this.collectionRuleR = collectionRuleR;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	private String roomNo;
	private String creator;

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCollectionRuleName() {
		return collectionRuleName;
	}

	public void setCollectionRuleName(String collectionRuleName) {
		this.collectionRuleName = collectionRuleName;
	}

	public String getDeviceTypeDesc() {
		return deviceTypeDesc;
	}

	public void setDeviceTypeDesc(String deviceTypeDesc) {
		this.deviceTypeDesc = deviceTypeDesc;
	}

	private Object createTime;

	private CBuilding building;

	private CFloor floor;

	private CRoom room;

	private CEquip equip;

	private CCollectionRule collectionRule;

	private Set<EMonitorCurve> monitorCurves;

	private Set<CDiagnosis> diagnosises;

	private User user;

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getEquipAddress() {
		return equipAddress;
	}

	public void setEquipAddress(String equipAddress) {
		this.equipAddress = equipAddress;
	}

	public String getCollectionRuleId() {
		return collectionRuleId;
	}

	public void setCollectionRuleId(String collectionRuleId) {
		this.collectionRuleId = collectionRuleId;
	}

	public String getMonitorDesc() {
		return monitorDesc;
	}

	public void setMonitorDesc(String monitorDesc) {
		this.monitorDesc = monitorDesc;
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

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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

	public CBuilding getBuilding() {
		return building;
	}

	public void setBuilding(CBuilding building) {
		this.building = building;
	}

	public CFloor getFloor() {
		return floor;
	}

	public void setFloor(CFloor floor) {
		this.floor = floor;
	}

	public CRoom getRoom() {
		return room;
	}

	public void setRoom(CRoom room) {
		this.room = room;
	}

	public CEquip getEquip() {
		return equip;
	}

	public void setEquip(CEquip equip) {
		this.equip = equip;
	}

	public CCollectionRule getCollectionRule() {
		return collectionRule;
	}

	public void setCollectionRule(CCollectionRule collectionRule) {
		this.collectionRule = collectionRule;
	}

	public Set<EMonitorCurve> getMonitorCurves() {
		return monitorCurves;
	}

	public void setMonitorCurves(Set<EMonitorCurve> monitorCurves) {
		this.monitorCurves = monitorCurves;
	}

	public Set<CDiagnosis> getDiagnosises() {
		return diagnosises;
	}

	public void setDiagnosises(Set<CDiagnosis> diagnosises) {
		this.diagnosises = diagnosises;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}