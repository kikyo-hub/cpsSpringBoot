package cn.nrzt.cps.dictionary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.nrzt.cps.dictionary.service.PCodeService;
import cn.nrzt.cps.web.SelectableResult;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

@Api(tags = { "系统字典信息" })
@RestController
@RequestMapping("/dict/equip")
public class DictionaryController {
	private Logger logger = LogManager.getLogger(DictionaryController.class);

	@Autowired
	private PCodeService service;

	@ApiOperation(value = "获取所有设备类型")
	@GetMapping(value = "/type")
	public WebResponse getEquipType() {
		logger.info("getEquipType");
		return new WebResponse(new SelectableResult(service.equipType()));
	}

	@ApiOperation(value = "获取所有设备型号")
	@GetMapping(value = "/mode")
	public WebResponse getEquipMode() {
		logger.info("getEquipMode");
		return new WebResponse(new SelectableResult(service.equipMode()));
	}

	@ApiOperation(value = "获取所有设备规约类型")
	@GetMapping(value = "/protocol")
	public WebResponse getProtocolType() {
		logger.info("getAllProtocolType");
		return new WebResponse(new SelectableResult(service.protocolCode()));
	}

	@ApiOperation(value = "获取所有设备通信方式")
	@GetMapping(value = "/commmode")
	public WebResponse getCommMode() {
		logger.info("getAllCommMode");
		return new WebResponse(new SelectableResult(service.commMode()));
	}
	
	@ApiOperation(value = "获取所有用能设备类型")
	@GetMapping(value = "/eDeviceType")
	public WebResponse getEDeviceType() {
		logger.info("getEDeviceType");
		return new WebResponse(new SelectableResult(service.eDeviceType()));
	}
	
	@ApiOperation(value = "获取所有诊断点类型")
	@GetMapping(value = "/diagnosisType")
	public WebResponse getDiagnosisType() {
		logger.info("getDiagnosisType");
		return new WebResponse(new SelectableResult(service.getDiagnosisType()));
	}
	
	@ApiOperation(value = "获取所有诊断规则类型")
	@GetMapping(value = "/diagnosisRuleType")
	public WebResponse getiagnosisRuleType() {
		logger.info("getiagnosisRuleType");
		return new WebResponse(new SelectableResult(service.getDagnosisRuleType()));
	}
	
	@ApiOperation(value = "获取所有企业类型")
	@GetMapping(value = "/enterpriseType")
	public WebResponse getEnterpriseType() {
		logger.info("getEnterpriseType");
		return new WebResponse(new SelectableResult(service.getEnterpriseType()));
	}

}
