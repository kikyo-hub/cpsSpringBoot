package cn.nrzt.cps.system.controller;

import cn.nrzt.cps.energydata.controller.DiagnosisDataController;
import cn.nrzt.cps.system.po.Org;
import cn.nrzt.cps.system.service.OrgService;
import cn.nrzt.cps.web.PageResult;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags={" 系 统 设 置 "})
@RestController
@RequestMapping("/system/org")
public class OrgController {

    private Logger logger = LogManager.getLogger(DiagnosisDataController.class);
    @Autowired
    OrgService orgService;
    @ApiOperation(value = "获 取 企 业 信 息")
    @GetMapping(value = "/getOrgDatas")
    public WebResponse getOrgDatas(Org queryParam,Integer pageNo,Integer pageSize) {
    	if ("".equals(queryParam.getId())) queryParam.setId(null);
    	if ("".equals(queryParam.getName())) queryParam.setName(null);
    	if ("".equals(queryParam.getAddr())) queryParam.setAddr(null);
    	if ("".equals(queryParam.getContacts())) queryParam.setContacts(null);
    	if ("".equals(queryParam.getType())) queryParam.setType(null);
    	if ("".equals(queryParam.getTel())) queryParam.setTel(null);
    	PageResult<Org> list= orgService.getOrgDatas(queryParam, pageNo,pageSize);
        return new WebResponse(list);    //这是身背
    }
    @ApiOperation(value = "获 取 企 业 类 别 信 息")
    @GetMapping(value = "/getOrgTypes")
    public WebResponse getOrgTypes(){
    	return new WebResponse(orgService.getOrgTypes());
    }
    @ApiOperation(value = " 删 除 企 业 类 别 信 息 ")
    @PostMapping(value = "/delOrg")
    public WebResponse delOrg(@RequestBody List<String> ids) {
    	
    	return new WebResponse(orgService.delOrg(ids));
    }
    @ApiOperation(value = " 编 辑 企 业 类 别 信 息 ")
    @PostMapping(value = "/updateOrg")
    public WebResponse updateOrg(@RequestBody Org org) {
    	return new WebResponse(orgService.updateOrg(org));
    }
    @ApiOperation(value = " 编 辑 企 业 用 户 信 息 ")
    @GetMapping(value = "/getOrgUsers")
    public WebResponse getOrgUsers(String id){
    	return new WebResponse(orgService.getOrgUsers(id));
    }
}
