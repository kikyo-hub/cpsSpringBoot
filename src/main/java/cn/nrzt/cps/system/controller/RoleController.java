package cn.nrzt.cps.system.controller;

import cn.nrzt.cps.energydata.controller.DiagnosisDataController;
import cn.nrzt.cps.system.po.Role;
import cn.nrzt.cps.system.service.RoleService;
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
@RequestMapping("/system/role")
public class RoleController {

    private Logger logger = LogManager.getLogger(DiagnosisDataController.class);
    @Autowired
    RoleService roleService;
    @ApiOperation(value = "获 取 角 色 信 息")
    @GetMapping(value = "/getRoles")
    public WebResponse getRoles(Role queryParam,Integer pageNo,Integer pageSize) {
    	if ("".equals(queryParam.getId())) queryParam.setId(null);
    	if ("".equals(queryParam.getName())) queryParam.setName(null);
    	if ("".equals(queryParam.getCode())) queryParam.setCode(null);
        if ("".equals(queryParam.getGenTime())) queryParam.setGenTime(null);
//    	if ("".equals(queryParam.getAuths())) queryParam.setAuths(null);
    	PageResult<Role> list= roleService.getRoles(queryParam, pageNo,pageSize);
        return new WebResponse(list);    //这是身背
    }

    @ApiOperation(value = " 删 除 角 色 类 别 信 息 ")
    @PostMapping(value = "/delRole")
    public WebResponse delRole(@RequestBody List<String> ids) {
    	
    	return new WebResponse(roleService.delRole(ids));
    }
    @ApiOperation(value = " 编 辑 角 色 类 别 信 息 ")
    @PostMapping(value = "/updateRole")
    public WebResponse updateRole(@RequestBody Role role) {
    	return new WebResponse(roleService.updateRole(role));
    }
    @ApiOperation(value = " 获 取 角 色 用 户 信 息 ")
    @GetMapping(value = "/getRoleUsers")
    public WebResponse getRoleUsers(String id){
    	return new WebResponse(roleService.getRoleUsers(id));
    }

    @ApiOperation(value = " 给 角 色 赋 权 ")
    @PostMapping(value = "/distAuths")
    public WebResponse distAuths(@RequestBody Role role){
        return new WebResponse(roleService.distAuths(role));
    }
}
