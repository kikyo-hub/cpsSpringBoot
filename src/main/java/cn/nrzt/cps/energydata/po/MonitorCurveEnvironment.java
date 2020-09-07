package cn.nrzt.cps.energydata.po;

public class MonitorCurveEnvironment {
	private String monitorId;
	private String monitorName;
//	private String dev_Address;
	private String dataTime;
	private String temperature;
	private String humidity;
	private String co2;
	private String illuminance;
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
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getCo2() {
		return co2;
	}
	public void setCo2(String co2) {
		this.co2 = co2;
	}
	public String getIlluminance() {
		return illuminance;
	}
	public void setIlluminance(String illuminance) {
		this.illuminance = illuminance;
	}
	
	
}
