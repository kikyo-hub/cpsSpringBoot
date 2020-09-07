package cn.nrzt.cps.system.po;

import java.util.LinkedHashSet;

public class Users {
    private String id;
    private String loginName;
    private String loginPasswd;
    private String userName;
    private Integer phone;
    private Integer status;
    private String orgId;
    LinkedHashSet<Role> roles;
    LinkedHashSet<Authority> auths;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public LinkedHashSet<Role> getRoles() {
        return roles;
    }

    public void setRoles(LinkedHashSet<Role> roles) {
        this.roles = roles;
    }

    public LinkedHashSet<Authority> getAuths() {
        return auths;
    }

    public void setAuths(LinkedHashSet<Authority> auths) {
        this.auths = auths;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPasswd='" + loginPasswd + '\'' +
                ", userName='" + userName + '\'' +
                ", phone=" + phone +
                ", status=" + status +
                ", orgId='" + orgId + '\'' +
                ", roles=" + roles +
                ", auths=" + auths +
                '}';
    }
}
