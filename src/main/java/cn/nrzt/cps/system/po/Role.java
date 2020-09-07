package cn.nrzt.cps.system.po;

import java.util.Date;
import java.util.LinkedHashSet;

public class Role {
    private String id;
    private String name;
    private String genTime;
    private String code;
    LinkedHashSet<Authority> auths;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenTime() {
        return genTime;
    }

    public void setGenTime(String genTime) {
        this.genTime = genTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LinkedHashSet<Authority> getAuths() {
        return auths;
    }

    public void setAuths(LinkedHashSet<Authority> auths) {
        this.auths = auths;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genTime=" + genTime +
                ", code='" + code + '\'' +
                ", auths=" + auths +
                '}';
    }
}
