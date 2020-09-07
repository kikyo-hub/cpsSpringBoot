package cn.nrzt.cps.archives.po;

public class EnvMonitorCurve {
    private String monitorId;

    private Object dataTime;

    private String temperature;

    private String humidity;

    private String co2;

    private String illuminance;

    private CMonitor monitor;

    private CEquip cEquip;

    @Override
    public String toString() {
        return "EnvMonitorCurve{" +
                "monitorId='" + monitorId + '\'' +
                ", dataTime=" + dataTime +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", co2='" + co2 + '\'' +
                ", illuminance='" + illuminance + '\'' +
                ", monitor=" + monitor +
                ", cEquip=" + cEquip +
                '}';
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public void setDataTime(Object dataTime) {
        this.dataTime = dataTime;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public void setIlluminance(String illuminance) {
        this.illuminance = illuminance;
    }

    public void setMonitor(CMonitor monitor) {
        this.monitor = monitor;
    }

    public void setcEquip(CEquip cEquip) {
        this.cEquip = cEquip;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public Object getDataTime() {
        return dataTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getCo2() {
        return co2;
    }

    public String getIlluminance() {
        return illuminance;
    }

    public CMonitor getMonitor() {
        return monitor;
    }

    public CEquip getcEquip() {
        return cEquip;
    }
}
