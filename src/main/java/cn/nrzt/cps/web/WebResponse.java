package cn.nrzt.cps.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class WebResponse {
	
	public final static WebResponse USER_NO_EXIST =new WebResponse(1,"用户不存在");
	public final static WebResponse USER_PASSWORD_ERROR=new WebResponse(2,"用户名密码错");
	
	public final static WebResponse FAILED =new WebResponse(100, "数据处理失败");
	public final static WebResponse EXCEPTION =new WebResponse(101, "数据处理中发生异常");

	public static String SuccessJsonResult() 
	{
		return new WebResponse().toJsonString();
	}
	
	public static String JsonResult(Object value) {
		return new WebResponse(value).toJsonString();
	}

	public static String JsonResult(int code,String error) {
		return new WebResponse(code,error).toJsonString();
	}	

	private String   			  message 	="";
	private LocalDateTime		  timestamp =LocalDateTime.now();
	private Object   			  result 	=new String();
	private int      			  code		=0;
	
	public WebResponse() {}

	public WebResponse(int code,String error) {
		this.code =code;
		this.message =error;	
	}
	
	public WebResponse(Exception e) {
		this.code =EXCEPTION.code;
		this.message =EXCEPTION.message +":" +e.getMessage();
	}
	
	public WebResponse(Object value) {
		if(value ==null)
		{
			this.code 	 =FAILED.getCode();
			this.message =FAILED.getMessage();
		}
		else {
			this.result =value;
		}		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public Object  getResult(){
		return this.result;
	}
	
	public void  setResult(Object result){
		this.result =result;
	}
	
//	public void put(String key,Object value) {
//		this.result.put(key, value);
//	}
	
	public String toJsonString() {
		JSONObject outJson =new JSONObject();
	    outJson.put("message", message);
	    outJson.put("code", code);
	    outJson.put("timestamp", timestamp);
	    outJson.put("result", result);

	    return outJson.toJSONString();
	}
	
	public Map<String, Object>  toResultMap(){
		Map<String, Object>   map 	=new HashMap<>();
		map.put("message", message);
		map.put("code", code);
		map.put("timestamp", timestamp);
		map.put("result", result);
		return map;
	}
	
}
