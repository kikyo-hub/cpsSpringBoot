package cn.nrzt.cps.user.po;

public class Auth {
	private String userid;
    private String token;
    
    public Auth() {}
    
    public Auth(String userid,String token) {}
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
    
}
