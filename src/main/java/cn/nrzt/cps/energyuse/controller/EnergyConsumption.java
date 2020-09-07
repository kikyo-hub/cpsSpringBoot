package cn.nrzt.cps.energyuse.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.energydata.controller.DiagnosisDataController;
import cn.nrzt.cps.energyuse.service.EnergyConsumptionService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"分项用能"})
@RestController
@RequestMapping("/energyconsumption")
public class EnergyConsumption {
	private Logger logger = LogManager.getLogger(DiagnosisDataController.class);	
	@Autowired
	EnergyConsumptionService service;
	@ApiOperation(value = "获取电能信息")
	@GetMapping(value = "/electric")
	public WebResponse  electric(@RequestParam("monitor_date") String monitor_date) {
		logger.info("electric:信息 "+monitor_date);
		return new WebResponse(service.electric(monitor_date));
	}
}
