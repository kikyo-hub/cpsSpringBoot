package cn.nrzt.cps.archives.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.service.DiagnosisService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"诊断分析点档案"})
//@Api()
@RestController
@RequestMapping("/archives/diagnosis")
public class DiagnosisController {
	private Logger logger = LogManager.getLogger(DiagnosisController.class);
	@Autowired
	private DiagnosisService service;

	@ApiOperation(value = "按页获取诊断分析点明细")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public WebResponse getDiagnosisByPage(@RequestParam int pageNo, @RequestParam int pageSize,
			@RequestParam Integer rowSelectId,@RequestParam String diagnosisId,@RequestParam String diagnosisType) {
		logger.info("getAll,page ={}", pageNo);
		return new WebResponse(service.getAll(pageNo, pageSize, rowSelectId,diagnosisId,diagnosisType));
	}
	
	@ApiOperation(value = "获取诊断规则选项")
	@GetMapping(value = "/getDiagnosisRuleSelect")
	public WebResponse getDiagnosisRuleSelect() {
		logger.info("getDiagnosisRuleSelect,page ={}");
		return new WebResponse(service.getDiagnosisRuleSelect());
	}
	
	@ApiOperation(value = "增加一个诊断分析点")
	@PostMapping(value = "/")
	public WebResponse addDiagnosis(@RequestBody JSONObject body) {
		CDiagnosis cDiagnosis = body.toJavaObject(CDiagnosis.class);
//		List<?> monitorIds =body.getJSONArray("monitorIds");
		if (service.saveDiagnosis(cDiagnosis) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "删除一个诊断分析点")
	@DeleteMapping(value = "/{id}")
	public WebResponse deleteDiagnosis(@PathVariable String id) {
		logger.info("delete:{}", id);
		if (service.deleteDiagnosis(id) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "更新一个诊断分析点")
	@PutMapping(value = "/")
	public WebResponse updateDiagnosis(@RequestBody JSONObject body) {
		CDiagnosis cDiagnosis = body.toJavaObject(CDiagnosis.class);
		if (service.updateDiagnosis(cDiagnosis) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "诊断点绑定监测点")
	@RequestMapping(value = "/bandingDM", method = RequestMethod.POST)
	public WebResponse bandingDMr(@RequestBody JSONObject json) {
		String diagnosisId = json.getString("diagnosisId");
		List<?> monitorIds =json.getJSONArray("monitorIds");
		if (service.bandingDMr(diagnosisId,monitorIds) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "诊断点解绑监测点")
	@RequestMapping(value = "/unbindDM", method = RequestMethod.POST)
	public WebResponse unbindDMr(@RequestBody JSONObject json) {
		String diagnosisId = json.getString("diagnosisId");
		List<?> monitorIds =json.getJSONArray("monitorIds");
		if (service.updateDMrel(diagnosisId,monitorIds) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation("获取所有监测点分页")
	@RequestMapping(value = "/getAllMonitorPointPage", method = RequestMethod.GET)
	public WebResponse getAllMonitorPointPage(@RequestParam int pageNo, @RequestParam int pageSize,
			@RequestParam String deviceType,@RequestParam String upEquipId,@RequestParam String monitorId,@RequestParam String monitorName) {
		return new WebResponse(service.getAllMonitorPointPage(pageNo,pageSize,deviceType,upEquipId,monitorId,monitorName));
	}
	
//	@ApiOperation(value = "获取所有诊断分析点明细")
//	@GetMapping(value = "/all")
//	public WebResponse getAllDiagnosis() {
//		logger.info("getAll");
//		return new WebResponse(service.getAll());
//	}

//	@ApiOperation(value = "获取一个诊断分析点明细")
//	@RequestMapping(value = "/get", method = RequestMethod.GET)
//	public WebResponse getDiagnosis(@RequestParam String id) {
//		logger.info("get:{}", id);
//
//		return new WebResponse(service.getByEquipId(id));
//	}

//	@ApiOperation(value = "增加一个诊断分析点")
//	@PostMapping(value = "/add")
//	public WebResponse addDiagnosis(@RequestBody String body) {
//
//		return new WebResponse();
//	}

//	@ApiOperation(value = "删除一个诊断分析点")
//	@DeleteMapping(value = "/{id}")
//	public WebResponse deleteDiagnosis(@RequestParam String id) {
//		logger.info("delete:{}", id);
//
//		return new WebResponse();
//	}

//	@ApiOperation(value = "更新一个诊断分析点")
//	@PutMapping(value = "/{id}")
//	public WebResponse updateDiagnosis(@RequestBody String body) {
//
//		return new WebResponse();
//	}

//	@ApiOperation(value = "监测点绑定到诊断分析点")
//	@PostMapping(value = "/bind/monitor")
//	public WebResponse bindMonitor(@RequestParam("monitorid") String monitorid,
//			@RequestParam("equipid") String equipid) {
//
//		return new WebResponse();
//	}

//	@ApiOperation(value = "从诊断分析点删除一个监测点")
//	@PostMapping(value = "/deletemonitor/")
//	public WebResponse deleteMonitor(@RequestParam("monitorid") String monitorid,
//			@RequestParam("equipid") String equipid) {
//
//		return new WebResponse();
//	}
}
