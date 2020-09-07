package cn.nrzt.cps.energyuse.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.nrzt.cps.energydata.controller.DiagnosisDataController;
import cn.nrzt.cps.energyuse.service.TotalEnergyService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"总能分析"})
@RestController
@RequestMapping("/TotalEnergySis")
public class TotalEnergyController {

	@Autowired
	TotalEnergyService service;
	private Logger logger = LogManager.getLogger(DiagnosisDataController.class);
	
	@ApiOperation(value = "获取主页信息")
	@GetMapping(value = "/getHomeInfo")
	public WebResponse  getHomeInfo() {
		logger.info("getHomeInfo");
		return new WebResponse(service.getHomeInfo());
	}
}
