package cn.nrzt.cps.user.po;

public class UserInfo {
	private String name;
	private String avatar;
	private String[] roles =new String[] {};
	
	public UserInfo() {}
	
	public UserInfo(String name,String ico,String[] roles) {
		this.name =name;
		this.avatar =ico;
		this.roles =roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String ico) {
		this.avatar = ico;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
