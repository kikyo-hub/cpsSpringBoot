package cn.nrzt.cps.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.nrzt.cps.user.po.Auth;
import cn.nrzt.cps.user.service.UserService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"用户认证"})
@RestController
@RequestMapping("/auth")
public class AuthController {
	private Logger logger = LogManager.getLogger(AuthController.class);	
	@Autowired
	UserService service;
	
	@ResponseBody
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String login(@RequestBody JSONObject json) {
		String userName =json.getString("username");
		String password =json.getString("password").toUpperCase();
	    logger.info("login--用户:{},密码:{} 登陆验证",userName,password);
	    
	    Auth auth = this.service.authUser(userName, password);
	    	 
		return new WebResponse(auth).toJsonString();	   
	}
	
	@ResponseBody
	@ApiOperation(value = "退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String UserLoginOut(@RequestBody JSONObject json){
		String userName =json.getString("username");
		logger.info("------------用户:{}退出中--------------",userName);
		return WebResponse.SuccessJsonResult();
	}
}
