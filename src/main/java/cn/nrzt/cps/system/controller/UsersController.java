package cn.nrzt.cps.system.controller;

import cn.nrzt.cps.energydata.controller.DiagnosisDataController;
import cn.nrzt.cps.system.mapper.UsersMapper;
import cn.nrzt.cps.system.po.Role;
import cn.nrzt.cps.system.po.Users;
import cn.nrzt.cps.system.service.RoleService;
import cn.nrzt.cps.system.service.UsersService;
import cn.nrzt.cps.util.CredentialUtil;
import cn.nrzt.cps.web.PageResult;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={" 系 统 设 置 "})
@RestController
@RequestMapping("/system/user")
public class UsersController {

    private Logger logger = LogManager.getLogger(DiagnosisDataController.class);
    @Autowired
    UsersService usersService;

    @ApiOperation(value = "获 取 用 户 信 息")
    @GetMapping(value = "/getUsers")
    public WebResponse getUsers(Users queryParam, Integer pageNo, Integer pageSize) {
        String passwd=CredentialUtil.getMD5(queryParam.getLoginPasswd());
        queryParam.setLoginPasswd(passwd);
    	PageResult<Users> list= usersService.getUsers(queryParam, pageNo,pageSize);
        return new WebResponse(list);    //这是身背
    }

    @ApiOperation(value = " 删 除 用 户 信 息 ")
    @PostMapping(value = "/delUsers")
    public WebResponse delUsers(@RequestBody List<String> ids) {
    	return new WebResponse(usersService.delUsers(ids));
    }

    @ApiOperation(value = " 编 辑 用 户 信 息 ")
    @PostMapping(value = "/updateUsers")
    public WebResponse updateUsers(@RequestBody Users users) {
        String passwd=CredentialUtil.getMD5(users.getLoginPasswd());
        users.setLoginPasswd(passwd);
    	return new WebResponse(usersService.updateUsers(users));
    }

    @ApiOperation(value = " 分 配 角 色")
    @PostMapping(value = "/distRoles")
    public WebResponse distRoles(@RequestBody Users users){
        String passwd=CredentialUtil.getMD5(users.getLoginPasswd());
        users.setLoginPasswd(passwd);
        return new WebResponse(usersService.distRoles(users));
    }

    @ApiOperation(value = " 获 取 角 色 信 息")
    @GetMapping(value = "/getRoles")
    public WebResponse getRoles(){
        return new WebResponse(usersService.getRoles());
    }
}
