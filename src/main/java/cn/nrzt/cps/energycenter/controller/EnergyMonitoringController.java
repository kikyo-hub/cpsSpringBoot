package cn.nrzt.cps.energycenter.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cn.nrzt.cps.energycenter.service.EnergyMonitoringService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "能源监测" })
@RestController
@RequestMapping("/device")
public class EnergyMonitoringController {
	private Logger logger = LogManager.getLogger(EnergyMonitoringController.class);
	@Autowired
	private EnergyMonitoringService service;
	
	@ApiOperation(value = "获取能源监测信息")
	@GetMapping(value = "/energySurvey")
	public WebResponse  getEnterpriseData(@RequestParam("type") String type) {
		logger.info("GetEnterpriseData:信息",type);
		return new WebResponse(service.getElecdata(type));
	}
	
	@ApiOperation(value = "删除一个能源监测信息")
	@DeleteMapping(value = "/deleteenergySurvey")
	public WebResponse deleteenergySurvey(@RequestParam("monotorId") String monotorId) {
		logger.info("delete:{}",monotorId);	 
		if (service.deleteenergySurvey(monotorId) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
}

