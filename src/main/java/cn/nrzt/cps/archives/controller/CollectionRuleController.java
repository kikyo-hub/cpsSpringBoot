package cn.nrzt.cps.archives.controller;

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

import cn.nrzt.cps.archives.po.CCollectionRule;
import cn.nrzt.cps.archives.service.CollectionRuleService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"采集规则库"})
@RestController
@RequestMapping("/archives/collectionRule")
public class CollectionRuleController {
	private Logger logger = LogManager.getLogger(CollectionRuleController.class);
	@Autowired
	private CollectionRuleService service;
	
	@ApiOperation(value = "按页获取采集规则明细")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public WebResponse getCollectionRule(@RequestParam int pageNo, @RequestParam int pageSize) {
		logger.info("getAll,page ={}");
		return new WebResponse(service.getAll(pageNo, pageSize));
	}
	
	@ApiOperation(value = "获取所有采集规则下拉框")
	@RequestMapping(value = "/allSelect", method = RequestMethod.GET)
	public WebResponse getCollectionAllSelect() {
		logger.info("getAll,page ={}");
		return new WebResponse(service.getCollectionAllSelect());
	}
	
	@ApiOperation(value = "更新采集规则")
	@PutMapping("updatebyID")
	
	public WebResponse updatebyID(@RequestBody CCollectionRule cCollectionRule) {
		logger.info("updatebyID");
		return new WebResponse(service.updatebyID(cCollectionRule));
	}
	
	@ApiOperation(value = "删除采集规则")
	@DeleteMapping("delete/{collectionRuleId}")
	public WebResponse deletebyID(@PathVariable("collectionRuleId")Integer collectionRuleId) {
		logger.info("deletebyID");
		return new WebResponse(service.deletebyID(collectionRuleId));
	}
	
	@ApiOperation(value = "插入采集规则")
	@PostMapping("instert")
	public WebResponse inster(@RequestBody CCollectionRule cCollectionRule) {
		logger.info("instert");
		return new WebResponse(service.inster(cCollectionRule));
	}
	
	@ApiOperation(value = "获取采集规则编号")
	@GetMapping("getcollectionRuleNo")
	public WebResponse getCollection() {
		logger.info("getCollection");
		return new WebResponse(service.getCollection());
	}
}
