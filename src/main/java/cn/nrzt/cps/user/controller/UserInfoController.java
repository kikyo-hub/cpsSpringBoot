package cn.nrzt.cps.user.controller;

import cn.nrzt.cps.system.mapper.UsersMapper;
import cn.nrzt.cps.system.po.Authority;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.user.po.UserInfo;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags={"用户信息"})
@RestController
@RequestMapping("/user")
public class UserInfoController {
	private Logger logger = LogManager.getLogger(UserInfoController.class);
	@Autowired
	UsersMapper usersMapper;
	@ResponseBody
	@ApiOperation(value = "用户信息")
	@RequestMapping(value = "/info", method = RequestMethod.GET)	
	public String userInfo(@RequestParam(required = true) String userid) {
		logger.info("userInfo---用户ID:{} 获取权限信息",userid);
//		String[] roles =new String[] { "homepage", "device",  "data",	 "archives",  "efficiency", "user", "monitor",  "system", "eedas" };
		List<Authority> list =  usersMapper.usersAuths(userid);
		String[] roles= list.stream().map(Authority::getCode).toArray(String[]::new);
		return WebResponse.JsonResult(new UserInfo("管理员","/ztlogo64.png",roles));
	}
	
	@ApiOperation(value = "用户信息")
	@RequestMapping(value = "/info2", method = RequestMethod.GET)	
	public WebResponse userInfo2(@RequestParam(required = true) String userid) {
		logger.info("userInfo---用户ID:{} 获取权限信息",userid);	

		String[] roles =new String[] { 
									"homepage", //主页
		                            "device",   //能源设备中心
		                            "data",		//数据中心
		                            "archives", //资产档案
		                            "efficiency",//能效
		                            "user",		//用户
		                            "monitor", //监控
		                            "system",
		                            "eedas"
		                           };	
		return new WebResponse(new UserInfo("管理员","/ztlogo64.png",roles));
	}
}
