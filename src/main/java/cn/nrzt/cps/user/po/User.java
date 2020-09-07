package cn.nrzt.cps.user.po;

import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CEnterprise;
import cn.nrzt.cps.archives.po.CMonitor;

import java.util.Set;

public class User {
    private  String userId;

    private  String userName;

    private  String loginName;

    private  String loginPassWord;

    private String phnoeNo;

    private Integer userStatus;

    private String enterpriseId;

    private CEnterprise enterprise;

    private Set<CMonitor> monitors;

    private Set<CDiagnosis> diagnosises;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassWord() {
        return loginPassWord;
    }

    public void setLoginPassWord(String loginPassWord) {
        this.loginPassWord = loginPassWord;
    }

    public String getPhnoeNo() {
        return phnoeNo;
    }

    public void setPhnoeNo(String phnoeNo) {
        this.phnoeNo = phnoeNo;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public CEnterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(CEnterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Set<CMonitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(Set<CMonitor> monitors) {
        this.monitors = monitors;
    }

    public Set<CDiagnosis> getDiagnosises() {
        return diagnosises;
    }

    public void setDiagnosises(Set<CDiagnosis> diagnosises) {
        this.diagnosises = diagnosises;
    }
}
