package cn.nrzt.cps.energydata.po;

import cn.nrzt.cps.archives.po.CEquip;
import cn.nrzt.cps.archives.po.CMonitor;

import java.math.BigDecimal;

public class MonitorCurve {
    private String dev_address;

    private Object dataTime;

    private String temperature;

    private String humidity;

    private String co2;

    private String illuminance;

    private BigDecimal anIa;

    private BigDecimal anIb;

    private BigDecimal anIc;

    private BigDecimal anUaba;

    private BigDecimal anUb;

    private BigDecimal anUcbc;

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

    private BigDecimal pb;

    private BigDecimal pc;

    private BigDecimal prpE;

    private BigDecimal prpR;

    private BigDecimal q;

    private BigDecimal qa;

    private BigDecimal qb;

    private BigDecimal qc;

    private BigDecimal rapE;

    private BigDecimal rapR;

    private BigDecimal rrpE;

    private BigDecimal rrpR;

    private BigDecimal ua;

    private BigDecimal ub;

    private BigDecimal uc;

    private CMonitor monitor;

    private CEquip cEquip;

    @Override
    public String toString() {
        return "MonitorCurve{" +
                "dev_address='" + dev_address + '\'' +
                ", dataTime=" + dataTime +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", co2='" + co2 + '\'' +
                ", illuminance='" + illuminance + '\'' +
                ", anIa=" + anIa +
                ", anIb=" + anIb +
                ", anIc=" + anIc +
                ", anUaba=" + anUaba +
                ", anUb=" + anUb +
                ", anUcbc=" + anUcbc +
                ", f=" + f +
                ", fa=" + fa +
                ", fb=" + fb +
                ", fc=" + fc +
                ", i0=" + i0 +
                ", ia=" + ia +
                ", ib=" + ib +
                ", ic=" + ic +
                ", p=" + p +
                ", pa=" + pa +
                ", papE=" + papE +
                ", papR=" + papR +
                ", pb=" + pb +
                ", pc=" + pc +
                ", prpE=" + prpE +
                ", prpR=" + prpR +
                ", q=" + q +
                ", qa=" + qa +
                ", qb=" + qb +
                ", qc=" + qc +
                ", rapE=" + rapE +
                ", rapR=" + rapR +
                ", rrpE=" + rrpE +
                ", rrpR=" + rrpR +
                ", ua=" + ua +
                ", ub=" + ub +
                ", uc=" + uc +
                ", monitor=" + monitor +
                ", cEquip=" + cEquip +
                '}';
    }

    public String getdev_address() {
        return dev_address;
    }

    public void setdev_address(String dev_address) {
        this.dev_address = dev_address;
    }

    public Object getDataTime() {
        return dataTime;
    }

    public void setDataTime(Object dataTime) {
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

    public BigDecimal getAnIa() {
        return anIa;
    }

    public void setAnIa(BigDecimal anIa) {
        this.anIa = anIa;
    }

    public BigDecimal getAnIb() {
        return anIb;
    }

    public void setAnIb(BigDecimal anIb) {
        this.anIb = anIb;
    }

    public BigDecimal getAnIc() {
        return anIc;
    }

    public void setAnIc(BigDecimal anIc) {
        this.anIc = anIc;
    }

    public BigDecimal getAnUaba() {
        return anUaba;
    }

    public void setAnUaba(BigDecimal anUaba) {
        this.anUaba = anUaba;
    }

    public BigDecimal getAnUb() {
        return anUb;
    }

    public void setAnUb(BigDecimal anUb) {
        this.anUb = anUb;
    }

    public BigDecimal getAnUcbc() {
        return anUcbc;
    }

    public void setAnUcbc(BigDecimal anUcbc) {
        this.anUcbc = anUcbc;
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

    public BigDecimal getPrpE() {
        return prpE;
    }

    public void setPrpE(BigDecimal prpE) {
        this.prpE = prpE;
    }

    public BigDecimal getPrpR() {
        return prpR;
    }

    public void setPrpR(BigDecimal prpR) {
        this.prpR = prpR;
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

    public BigDecimal getRapE() {
        return rapE;
    }

    public void setRapE(BigDecimal rapE) {
        this.rapE = rapE;
    }

    public BigDecimal getRapR() {
        return rapR;
    }

    public void setRapR(BigDecimal rapR) {
        this.rapR = rapR;
    }

    public BigDecimal getRrpE() {
        return rrpE;
    }

    public void setRrpE(BigDecimal rrpE) {
        this.rrpE = rrpE;
    }

    public BigDecimal getRrpR() {
        return rrpR;
    }

    public void setRrpR(BigDecimal rrpR) {
        this.rrpR = rrpR;
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

    public CMonitor getMonitor() {
        return monitor;
    }

    public void setMonitor(CMonitor monitor) {
        this.monitor = monitor;
    }

    public CEquip getcEquip() {
        return cEquip;
    }

    public void setcEquip(CEquip cEquip) {
        this.cEquip = cEquip;
    }
}
