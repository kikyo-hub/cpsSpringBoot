package cn.nrzt.cps.energycenter.po;

import java.io.Serializable;


/**
 * The persistent class for the TEMP_ENERGY_EQUIP database table.
 * 
 */

public class SMonitorRun implements Serializable {
	private static final long serialVersionUID = 1L;

	private String buildingNo;

	private String monotorId;
	
	private String equipAddress;

	private String monotorName;
	
	private String Name;
	
	private String typeName;
	
	private String equipName;

	private int switchStatus;
	
	private int equipStatus;

	private int floor;

	private int type;
	
	private int deviceType;
	
	private int floorNO;
	
	private int roomNo;
	
	private String buildingName;
	
	private String floorName;
	
	private String roomName;

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public SMonitorRun() {
	}

	public String getMonotorId() {
		return monotorId;
	}

	public void setMonotorId(String monotorId) {
		this.monotorId = monotorId;
	}

	public String getEquipAddress() {
		return equipAddress;
	}

	public void setEquipAddress(String equipAddress) {
		this.equipAddress = equipAddress;
	}
	
	public String getBuildingNo() {
		return this.buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public int getEquipStatus() {
		return equipStatus;
	}

	public void setEquipStatus(int equipStatus) {
		this.equipStatus = equipStatus;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFloorNO() {
		return floorNO;
	}

	public void setFloorNO(int floorNO) {
		this.floorNO = floorNO;
	}

	public String getMonotorName() {
		return monotorName;
	}

	public void setMonotorName(String monotorName) {
		this.monotorName = monotorName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getSwitchStatus() {
		return switchStatus;
	}

	public void setSwitchStatus(int switchStatus) {
		this.switchStatus = switchStatus;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


   
}