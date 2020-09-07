package cn.nrzt.cps.archives.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CMonitor;
import cn.nrzt.cps.archives.service.BuildingService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = { "楼栋信息" })
@RestController
@RequestMapping("/archives")
public class BuildingController {
	private Logger logger = LogManager.getLogger(BuildingController.class);
	@Autowired
	BuildingService service;
	
	
	@ApiOperation(value = "查询诊断点信息")
	@GetMapping(value = "/selectDiagnosis")
	public WebResponse selectDiagnosis(@RequestParam(value="floorNo")String floorNo,@RequestParam(value="buildingNo")String buildingNo) {
		return new WebResponse(service.selectDiagnosis(floorNo,buildingNo));
		
	}
	
	@ApiOperation(value = "增加一个诊断点")
	@PostMapping(value = "/addDiagnosis")
	public WebResponse addDiagnosis(@RequestBody JSONObject body) {
		logger.info("add:{}",body);	 
		CDiagnosis diagnosis = body.toJavaObject(CDiagnosis.class);
		if (service.addDiagnosis(diagnosis)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
	@ApiOperation(value = "修改一个诊断点")
	@PutMapping(value = "/putDiagnosis")
	public WebResponse putDiagnosis(@RequestBody JSONObject body) {
		logger.info("putDiagnosis:{}",body);	 
		CDiagnosis diagnosis = body.toJavaObject(CDiagnosis.class);
		if (service.putDiagnosis(diagnosis)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
	@ApiOperation(value = "删除一个诊断点")
	@DeleteMapping(value = "/deleteDiagnosis")
	public WebResponse deleteDiagnosis(@RequestParam(value="diagnosisId") String  diagnosisId) {
		logger.info("deleteDiagnosis:{}",diagnosisId);
		if (service.deleteDiagnosis(diagnosisId)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
	
	@ApiOperation(value = "查询监测点信息")
	@GetMapping(value = "/selectCMonitor")
	public WebResponse selectCMonitor(@RequestParam(value="floorNo")String floorNo,@RequestParam(value="buildingNo")String buildingNo) {
		return new WebResponse(service.selectCMonitor(floorNo,buildingNo));
	}
	
	@ApiOperation(value = "查询所有楼栋信息")
	@GetMapping(value = "/selectBuildingAllData")
	public WebResponse selectBuildingAllData(@RequestParam(value="buildingNo")String buildingNo) {
		return new WebResponse(service.selectBuildingAllData(buildingNo));
		
	}
	@ApiOperation(value = "增加一个监测点")
	@PostMapping(value = "/addCMonitor")
	public WebResponse addCMonitor(@RequestBody JSONObject body) {
		logger.info("addCMonitor:{}",body);
		CMonitor cmonitor = body.toJavaObject(CMonitor.class);
		if (service.addCMonitor(cmonitor)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
	@ApiOperation(value = "修改一个监测点")
	@PutMapping(value = "/putCMonitor")
	public WebResponse putCMonitor(@RequestBody JSONObject body) {
		System.out.println(body+":body");
		CMonitor cmonitor = body.toJavaObject(CMonitor.class);
		if (service.putCMonitor(cmonitor)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
	@ApiOperation(value = "删除一个监测点")
	@DeleteMapping(value = "/deleteCMonitor")
	public WebResponse deleteCMonitor(@RequestParam(value="monitorId") String  monitorId) {
		System.out.println(monitorId+":body");
		if (service.deleteCMonitor(monitorId)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
}
