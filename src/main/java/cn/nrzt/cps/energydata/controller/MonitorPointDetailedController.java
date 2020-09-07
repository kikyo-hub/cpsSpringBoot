package cn.nrzt.cps.energydata.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.nrzt.cps.energydata.service.MonitorPointDetailedService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"监控点详细数据"})
@RestController
@RequestMapping("/monitor/MonitorPointDetailed")
public class MonitorPointDetailedController {
	private Logger logger = LogManager.getLogger(MonitorDataController.class);

	@Autowired
	private MonitorPointDetailedService service;
	
	@ApiOperation(value = "用能监测点电力数据")
	@GetMapping("/getMonitorCurveEnergy")
	public WebResponse getMonitorCurveEnergy(@RequestParam int pageNo, @RequestParam int pageSize){
		return new WebResponse(service.getMonitorCurveEnergy(pageNo,pageSize));
	};
	
	@ApiOperation(value = "用能监测点环境数据")
	@GetMapping("/getMonitorCurveEnvironment")
	public WebResponse getMonitorCurveEnvironment(@RequestParam int pageNo, @RequestParam int pageSize){
		return new WebResponse(service.getMonitorCurveEnvironment(pageNo,pageSize));
	};
	
	@ApiOperation(value = "获取监测点树")
	@GetMapping("/getMonitorTree")
	public WebResponse getMonitorTree(){
		return new WebResponse(service.getMonitorTree());
	};
	
	@ApiOperation(value = "获取单个日冻结数据")
	@GetMapping("/getMonitorDayEnergy")
	public WebResponse getMonitorDayEnergy(@RequestParam int pageNo, @RequestParam int pageSize,@RequestParam String devAddress){
		return new WebResponse(service.getMonitorDayEnergy(pageNo,pageSize,devAddress));
	};
}
