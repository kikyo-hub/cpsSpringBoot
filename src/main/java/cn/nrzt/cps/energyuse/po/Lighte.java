package cn.nrzt.cps.energyuse.po;
import java.math.BigDecimal;


/**
 * The persistent class for the LIGHT database table.
 * 
 */

public class Lighte {


	private String electricityConsumption;


	private String lightType;

	private String lightid;


	private String load;

	private Object monitorDate;


	private BigDecimal runState;


	public String getElectricityConsumption() {
		return this.electricityConsumption;
	}

	public void setElectricityConsumption(String electricityConsumption) {
		this.electricityConsumption = electricityConsumption;
	}

	public String getLightType() {
		return this.lightType;
	}

	public void setLightType(String lightType) {
		this.lightType = lightType;
	}

	public String getLightid() {
		return this.lightid;
	}

	public void setLightid(String lightid) {
		this.lightid = lightid;
	}

	public String getLoad() {
		return this.load;
	}

	public void setLoad(String load) {
		this.load = load;
	}

	public Object getMonitorDate() {
		return this.monitorDate;
	}

	public void setMonitorDate(Object monitorDate) {
		this.monitorDate = monitorDate;
	}

	public BigDecimal getRunState() {
		return this.runState;
	}

	public void setRunState(BigDecimal runState) {
		this.runState = runState;
	}

}