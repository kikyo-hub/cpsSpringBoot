package cn.nrzt.cps.energydata.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.energydata.service.itemsService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "分项用能" })
@RestController
@RequestMapping("/Items")
public class itemsController {
	private Logger logger = LogManager.getLogger(itemsController.class);
	@Autowired
	private itemsService service;
	@ApiOperation(value = "获取电梯日数据")
	@GetMapping(value= "/metertodaydata")
	public WebResponse  getmeterTodaydata(@RequestParam("todaydata") String todaydata, @RequestParam("adress") String adress) {
		logger.info("todaydata:信息"+todaydata+":"+adress);
		return new WebResponse(service.getmeterTodaydata(todaydata, adress));
	}
	@ApiOperation(value = "获取设备类型")
	@GetMapping(value= "/meters")
	public WebResponse  getmeter(@RequestParam("type") String type) {
		logger.info("type:信息"+type);
		return new WebResponse(service.getmeter(type));
	}
	@ApiOperation(value = "获取用电日排行")
	@GetMapping(value= "/meterTodayrank")
	public WebResponse  getmeterTodayrank(@RequestParam("time1") String time1,
										  @RequestParam("adress") String adress) {
		logger.info("time1:信息"+time1+":"+adress);
		return new WebResponse(service.getmeterTodayrank(time1, adress));
	}
	@ApiOperation(value = "获取用电周数据")
	@GetMapping(value= "/meterWeekdata")
	public WebResponse  getmeterWeekdata(@RequestParam("monday") String monday,@RequestParam("sunday") String sunday,
										 @RequestParam("adress") String adress) {
		logger.info("monday:信息"+monday+":"+sunday+":"+adress);
		return new WebResponse(service.getmeterWeekdata(monday, sunday, adress));
	}
	@ApiOperation(value = "获取用电周排行")
	@GetMapping(value= "/materweekRank")
	public WebResponse  getmaterweekRank(@RequestParam("monday") String monday,@RequestParam("sunday") String sunday,
										 @RequestParam("adress") String adress) {
		logger.info("monday:信息"+monday+":"+sunday+":"+adress);
		return new WebResponse(service.getmaterweekRank(monday, sunday, adress));
	}
}
