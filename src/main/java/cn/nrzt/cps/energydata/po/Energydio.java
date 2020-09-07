package cn.nrzt.cps.energydata.po;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Energydio {
	private String diagnosisId ;
	private String diagnosisName ;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd hh:mm:ss")//数据库导出页面时json格式化
	private Object dataTime;
	private BigDecimal diagnosistoday;

	public String getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public BigDecimal getDiagnosistoday() {
		return diagnosistoday;
	}

	public void setDiagnosistoday(BigDecimal diagnosistoday) {
		this.diagnosistoday = diagnosistoday;
	}
	public Object getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
}
