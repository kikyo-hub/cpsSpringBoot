package cn.nrzt.cps.archives.po;


import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the E_DIAGNOSIS_CURVE database table.
 * 
 */

public class EDiagnosisCurve{
	private String diagnosisId;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd hh:mm:ss")//数据库导出页面时json格式化
	private Object dataTime;

	private BigDecimal f;

	private BigDecimal fa;

	private BigDecimal fb;

	private BigDecimal fc;

	private BigDecimal i0;

	private BigDecimal ia;

	private BigDecimal ib;

	private BigDecimal ic;

	private BigDecimal p;

	private BigDecimal pa;

	private BigDecimal papE;

	private BigDecimal papR;
	
	private BigDecimal pap;

	private BigDecimal pb;

	private BigDecimal pc;

	private BigDecimal q;

	private BigDecimal qa;

	private BigDecimal qb;

	private BigDecimal qc;

	private BigDecimal ua;

	private BigDecimal ub;

	private BigDecimal uc;

	private CDiagnosis diagnosis;

	public String getMonitorId() {
		return diagnosisId;
	}

	public void setMonitorId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Object getDataTime() {
		return dataTime;
	}

	public void setDataTime(Object dataTime) {
		this.dataTime = dataTime;
	}






	public BigDecimal getF() {
		return f;
	}

	public void setF(BigDecimal f) {
		this.f = f;
	}

	public BigDecimal getFa() {
		return fa;
	}

	public void setFa(BigDecimal fa) {
		this.fa = fa;
	}

	public BigDecimal getFb() {
		return fb;
	}

	public void setFb(BigDecimal fb) {
		this.fb = fb;
	}

	public BigDecimal getFc() {
		return fc;
	}

	public void setFc(BigDecimal fc) {
		this.fc = fc;
	}

	public BigDecimal getI0() {
		return i0;
	}

	public void setI0(BigDecimal i0) {
		this.i0 = i0;
	}

	public BigDecimal getIa() {
		return ia;
	}

	public void setIa(BigDecimal ia) {
		this.ia = ia;
	}

	public BigDecimal getIb() {
		return ib;
	}

	public void setIb(BigDecimal ib) {
		this.ib = ib;
	}

	public BigDecimal getIc() {
		return ic;
	}

	public void setIc(BigDecimal ic) {
		this.ic = ic;
	}

	public BigDecimal getP() {
		return p;
	}

	public void setP(BigDecimal p) {
		this.p = p;
	}

	public BigDecimal getPa() {
		return pa;
	}

	public void setPa(BigDecimal pa) {
		this.pa = pa;
	}

	public BigDecimal getPapE() {
		return papE;
	}

	public void setPapE(BigDecimal papE) {
		this.papE = papE;
	}

	public BigDecimal getPapR() {
		return papR;
	}

	public void setPapR(BigDecimal papR) {
		this.papR = papR;
	}

	public BigDecimal getPb() {
		return pb;
	}

	public void setPb(BigDecimal pb) {
		this.pb = pb;
	}

	public BigDecimal getPc() {
		return pc;
	}

	public void setPc(BigDecimal pc) {
		this.pc = pc;
	}



	public BigDecimal getQ() {
		return q;
	}

	public void setQ(BigDecimal q) {
		this.q = q;
	}

	public BigDecimal getQa() {
		return qa;
	}

	public void setQa(BigDecimal qa) {
		this.qa = qa;
	}

	public BigDecimal getQb() {
		return qb;
	}

	public void setQb(BigDecimal qb) {
		this.qb = qb;
	}

	public BigDecimal getQc() {
		return qc;
	}

	public void setQc(BigDecimal qc) {
		this.qc = qc;
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

	public CDiagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(CDiagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public BigDecimal getPap() {
		return pap;
	}

	public void setPap(BigDecimal pap) {
		this.pap = pap;
	}
	
	
	
}