package cn.nrzt.cps.archives.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.archives.po.CDiagnosisRule;
import cn.nrzt.cps.archives.service.DiagnosisRuleService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"诊断规则库"})
@RestController
@RequestMapping("/archives/diagnosisRule")
public class DiagnosisRuleController {
	private Logger logger = LogManager.getLogger(DiagnosisRuleController.class);
	@Autowired
	private DiagnosisRuleService service;
	
	@ApiOperation(value = "按页获取诊断分析点明细")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public WebResponse getDiagnosisByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		logger.info("getAll,page ={}");
		return new WebResponse(service.getAll(pageNo,pageSize));
	}
	
	@ApiOperation(value = "插入诊断规则")
	@PostMapping(value = "/insert")
	public WebResponse insert(@RequestBody CDiagnosisRule cDiagnosisRule) {
		logger.info("insert");
		WebResponse webResponse = new WebResponse(service.insert(cDiagnosisRule));
		return webResponse;
	}
	
	@ApiOperation(value = "删除诊断规则")
	@DeleteMapping("delete/{diagnosisRuleId}")
	public WebResponse deletebyID(@PathVariable("diagnosisRuleId")String diagnosisRuleId) {
		logger.info("deletebyID");
		return new WebResponse(service.deletebyID(diagnosisRuleId));
	}
	
	@ApiOperation(value = "更新诊断规则")
	@PutMapping("updatebyID")
	public WebResponse updatebyID(@RequestBody CDiagnosisRule cDiagnosisRule) {
		logger.info("updatebyID");
		return new WebResponse(service.updatebyID(cDiagnosisRule));
	}
	
}
