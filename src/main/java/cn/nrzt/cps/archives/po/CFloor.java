package cn.nrzt.cps.archives.po;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CFloor {
    @Override
	public String toString() {
		return "CFloor [floorNo=" + floorNo + ", floorName=" + floorName + ", buildingNo=" + buildingNo + ", isLeaf="
				+ isLeaf + ", Building=" + Building + ", roomList=" + roomList + ", rooms=" + rooms + ", monitors="
				+ monitors + "]";
	}

	private String floorNo;

    private String floorName;

    private String buildingNo;

    private String isLeaf;

    private CBuilding Building;
    
    private Object roomList;

    




	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public Object getRoomList() {
		return roomList;
	}

	public void setRoomList(Object roomList) {
		this.roomList = roomList;
	}

	private LinkedHashSet<CRoom> rooms;

    private List<CMonitor> monitors;

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}


	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public CBuilding getBuilding() {
		return Building;
	}

	public void setBuilding(CBuilding building) {
		Building = building;
	}

	public LinkedHashSet<CRoom> getRooms() {
		return rooms;
	}

	public void setRooms(LinkedHashSet<CRoom> rooms) {
		this.rooms = rooms;
	}

	public List<CMonitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<CMonitor> monitors) {
		this.monitors = monitors;
	}
    
}
