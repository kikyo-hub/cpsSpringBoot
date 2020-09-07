package cn.nrzt.cps.archives.po;

import java.util.List;
import java.util.Set;


public class CRoom {
    private String roomNo;

    private String roomName;

    private String buildingNo;

    private String floorNo;
    
    private String category;
    
    private String categoryName;
    
    private Double area;
    
    private Integer employees;

    private CFloor floor;

    private List<CMonitor> monitors;

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

	public CFloor getFloor() {
		return floor;
	}

	public void setFloor(CFloor floor) {
		this.floor = floor;
	}

	public List<CMonitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<CMonitor> monitors) {
		this.monitors = monitors;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getEmployees() {
		return employees;
	}

	public void setEmployees(Integer employees) {
		this.employees = employees;
	}
	
	
    
}
