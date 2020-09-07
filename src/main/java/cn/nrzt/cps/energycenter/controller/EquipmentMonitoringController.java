package cn.nrzt.cps.energycenter.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.energycenter.service.EquipmentMonitoringService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "设备监测" })
@RestController
@RequestMapping("/lightsystem")
public class EquipmentMonitoringController {
	private Logger logger = LogManager.getLogger(EquipmentMonitoringController.class);
	@Autowired
	private EquipmentMonitoringService service;
	@ApiOperation(value = "获取照明日冻结信息")
	@GetMapping(value = "/lidata")
	public WebResponse  lidata(@RequestParam("lightdate") String date,@RequestParam("lightmonitor") String monitorid) {
		logger.info("lidata:信息 "+date+":"+monitorid);
		return new WebResponse(service.getAllLidata(date,monitorid));
	}
	@ApiOperation(value = "获取照明信息")
	@GetMapping(value = "/litable")
	public WebResponse  litable(@RequestParam("bulidingno") String bulidingno ,@RequestParam("floor") String floor,
			@RequestParam("status") String status ,@RequestParam("equipstatus") String equipstatus 	) {
		logger.info("lidata:信息 "+bulidingno+":"+floor);
		return new WebResponse(service.getAlllitable(bulidingno,floor,status,equipstatus));
	}
	@ApiOperation(value = "获取照明")
	@GetMapping(value = "/lit")
	public WebResponse   air(@RequestParam("type") int type) {
		logger.info("air:信息"+type);
		return new WebResponse(service.getAlllit(type));	
	}
}
