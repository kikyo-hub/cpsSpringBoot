package cn.nrzt.cps.archives.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.archives.service.AirService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "设备检测" })
@RestController
@RequestMapping("/airconditionsystem")
public class AirController<WebRsponse> {
	private Logger logger = LogManager.getLogger(AirController.class);
	@Autowired
	private AirService service;
	@ApiOperation(value = "获取空调日冻结信息")
	@GetMapping(value= "/airdata")
	public WebResponse  getAllAirdata(@RequestParam("airdata") String date,@RequestParam("airmonitor") String monitorid) {
		logger.info("airdata:信息"+date+":"+monitorid);
		return new WebResponse(service.getAllAirdata(date,monitorid));
	}
	@ApiOperation(value = "获取空调信息 ")
	@GetMapping(value = "/airtable")
	public WebResponse  airtable(@RequestParam("bulidingno") String bulidingno ,@RequestParam("floor") String floor,
			@RequestParam("status") String status ,@RequestParam("equipstatus") String equipstatus 	) {
		logger.info("lidata:信息 "+bulidingno+":"+floor);
		return new WebResponse(service.getAllairtable(bulidingno,floor,status,equipstatus));
	}
	@ApiOperation(value = "全部空调")
	@GetMapping(value = "/air")
	public WebResponse   air(@RequestParam("type") int type) {
		logger.info("air:信息"+type);
		return new WebResponse(service.getAllair(type));	
	}
}
