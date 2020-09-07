package cn.nrzt.cps.archives.po;

/**
 * The persistent class for the C_DIAGNOSIS_MONITOR_REL database table.
 * 
 */
public class CDiagnosisMonitorRel  {
	private String diagnosisId;

	private String monitorId;

	private String relId;

	public CDiagnosisMonitorRel() {
	}

	public String getDiagnosisId() {
		return this.diagnosisId;
	}

	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getMonitorId() {
		return this.monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public String getRelId() {
		return this.relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

}