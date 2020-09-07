package cn.nrzt.cps.energydata.po;

import java.math.BigDecimal;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

public class Items {
	private String equipName ;
	private String equipId ;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd hh:mm:ss")//数据库导出页面时json格式化
	private Object dataTime;
	private BigDecimal ua;
	private BigDecimal ub;
	private BigDecimal uc;
	private BigDecimal pap_r;

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public Object getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	
	public BigDecimal getUa() {
		return ua;
	}

	public void setUa(BigDecimal ua) {
		this.ua = ua;
	}

	public BigDecimal getUb() {
		return ub;
	}

	public void setUb(BigDecimal ub) {
		this.ub = ub;
	}

	public BigDecimal getUc() {
		return uc;
	}

	public void setUc(BigDecimal uc) {
		this.uc = uc;
	}

	public BigDecimal getPap_r() {
		return pap_r;
	}

	public void setPap_r(BigDecimal pap_r) {
		this.pap_r = pap_r;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	
}
