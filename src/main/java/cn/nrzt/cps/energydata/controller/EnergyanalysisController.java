package cn.nrzt.cps.energydata.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.energydata.service.EnergyanalysisService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "总能分析" })
@RestController
@RequestMapping("/Energyanalysis")
   public class EnergyanalysisController {
	private Logger logger = LogManager.getLogger(EnergyanalysisController.class);
	@Autowired
	private EnergyanalysisService service;
	@ApiOperation(value = "获取监测点日数据")
	@GetMapping(value= "/todaydata")
	public WebResponse  getTodaydata(@RequestParam("todaydata") String todaydata, @RequestParam("Yesterdaydata") String Yesterdaydata, @RequestParam("Beforedaydata") String Beforedaydata) {
		logger.info("todaydata:信息"+todaydata+":"+Yesterdaydata+":"+Beforedaydata);
		return new WebResponse(service.getTodaydata(todaydata, Yesterdaydata, Beforedaydata));
	}
	@ApiOperation(value = "获取监测点排行")
	@GetMapping(value= "/monitorRank")
	public WebResponse  getmonitorRank(@RequestParam("today") String today, @RequestParam("Yesterday") String Yesterday) {
		logger.info("todaydata:信息"+today+":"+Yesterday);
		return new WebResponse(service.getmonitorRank(today, Yesterday));
	}
	@ApiOperation(value = "获取监测点")
	@GetMapping(value= "/monitorweekData")
	public WebResponse  getmonitorweekData(@RequestParam("begindate") String begindate,@RequestParam("enddate") String enddate) {
		logger.info("begindate:信息"+begindate+":"+enddate);
		return new WebResponse(service.getmonitorweekData(begindate, enddate));
	}
	@ApiOperation(value = "获取监测点周数据")
	@GetMapping(value= "/monitorweekDc")
	public WebResponse  getmonitorweekDc(@RequestParam("monday") String monday,@RequestParam("sunday") String sunday) {
		logger.info("begindate:信息"+monday+":"+sunday);
		return new WebResponse(service.getmonitorweekDc(monday, sunday));
	}
	@ApiOperation(value = "获取诊断点日数据")
	@GetMapping(value= "/diagnosisdayData")
	public WebResponse  getdiagnosisdayData(@RequestParam("today") String today,@RequestParam("yesterday") String yesterday) {
		logger.info("begindate:信息"+today+":"+yesterday);
		return new WebResponse(service.getdiagnosisdayData(today, yesterday));
	}
	@ApiOperation(value = "获取诊断点排行")
	@GetMapping(value= "/diagnosisdayRank")
	public WebResponse  getdiagnosisdayRank(@RequestParam("today") String today,@RequestParam("yesterday") String yesterday) {
		logger.info("begindate:信息"+today+":"+yesterday);
		return new WebResponse(service.getdiagnosisdayRank(today, yesterday));
	}
	@ApiOperation(value = "获取诊断点周数据")
	@GetMapping(value= "/diagnosisweekData")
	public WebResponse  getdiagnosisweekData(@RequestParam("monday") String monday,@RequestParam("sunday") String sunday) {
		logger.info("begindate:信息"+monday+":"+sunday);
		return new WebResponse(service.getdiagnosisweekData(monday, sunday));
	}

}
