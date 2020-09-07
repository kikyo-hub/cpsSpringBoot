package cn.nrzt.cps.energydiagnosis.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import cn.nrzt.cps.energydiagnosis.controller.ReportController;
import cn.nrzt.cps.energydiagnosis.service.ReportService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "报表接口" })
@RestController
@RequestMapping("/exception")
public class ReportController {
	private Logger logger = LogManager.getLogger(ReportController.class);
	
	@Autowired
	private ReportService service;
	
	@ApiOperation(value = "获取监测点类型数量")
	@GetMapping(value = "/getmonitornum")
	public WebResponse  getMonitorNum() {
		logger.info("getMonitorNum:信息");
		return new WebResponse(service.getMonitorNum());
	}
}
