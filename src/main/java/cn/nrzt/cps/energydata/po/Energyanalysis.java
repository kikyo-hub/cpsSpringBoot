package cn.nrzt.cps.energydata.po;

import java.math.BigDecimal;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the E_MONITOR_CURVE_ENERGY database table.
 * 
 */

public class Energyanalysis {
	private String monitorAddress ;
	private String monitorName ;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd hh:mm:ss")//数据库导出页面时json格式化
	private Object dataTime;
	private BigDecimal yesterday;
	private BigDecimal today;
	private BigDecimal monitorday;
	private BigDecimal tpAp;
	private BigDecimal pAp ;
	private BigDecimal spAp1;
	private BigDecimal spAp2;
	private int devicetype;

	public String getMonitorAddress() {
		return monitorAddress;
	}

	public void setMonitorAddress(String monitorAddress) {
		this.monitorAddress = monitorAddress;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public Object getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public BigDecimal getpAp() {
		return pAp;
	}

	public void setpAp(BigDecimal pAp) {
		this.pAp = pAp;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public BigDecimal getTpAp() {
		return tpAp;
	}

	public void setTpAp(BigDecimal tpAp) {
		this.tpAp = tpAp;
	}

	public BigDecimal getSpAp1() {
		return spAp1;
	}

	public void setSpAp1(BigDecimal spAp1) {
		this.spAp1 = spAp1;
	}

	public BigDecimal getSpAp2() {
		return spAp2;
	}

	public void setSpAp2(BigDecimal spAp2) {
		this.spAp2 = spAp2;
	}

	public BigDecimal getYesterday() {
		return yesterday;
	}

	public void setYesterday(BigDecimal yesterday) {
		this.yesterday = yesterday;
	}

	public BigDecimal getToday() {
		return today;
	}

	public void setToday(BigDecimal today) {
		this.today = today;
	}

	public BigDecimal getMonitorday() {
		return monitorday;
	}

	public void setMonitorday(BigDecimal monitorday) {
		this.monitorday = monitorday;
	}
}
