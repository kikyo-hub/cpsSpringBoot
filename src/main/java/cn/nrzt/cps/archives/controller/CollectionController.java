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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.archives.service.EquipService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"采集规则"})
@RestController
@RequestMapping("/archives/collect")
public class CollectionController {
	private Logger logger = LogManager.getLogger(CollectionController.class);	
	@Autowired
	private EquipService service;
	
	@ApiOperation(value = "获取所有采集规则")
	@GetMapping(value = "/all")
	public WebResponse getAllCollection() {	
		logger.info("getAll");	 
		return new WebResponse(service.getAll());	   
	}
	
	@ApiOperation(value = "按页获取采集规则")
	@RequestMapping(value = "/page/{pageNo},{pageNum}", method = RequestMethod.GET)
	public WebResponse getCollectionByPage(@RequestParam int pageNo,@RequestParam int pageNum) {	
		logger.info("getByPage,pageNo ={}",pageNo);	 		
		
		return new WebResponse(service.getPage(pageNo, pageNum,"","","","","","",""));
	}
	
	@ApiOperation(value = "获取一个采集规则")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public WebResponse getCollection(@RequestParam String id) {
		logger.info("get:{}",id);	 
	    	 
		return new WebResponse(service.getByEquipId(id)) ; 
	}
	
	@ApiOperation(value = "增加一个采集规则")
	@PostMapping(value = "/add")
	public WebResponse addCollection(@RequestBody String body) {
		
		return new WebResponse() ; 
	}
	
	@ApiOperation(value = "删除一个采集规则")
	@DeleteMapping(value = "/{id}")
	public WebResponse deleteCollection(@RequestParam String id) {
		logger.info("delete:{}",id);	 
	    	 
		return new WebResponse() ; 
	}
	
	@ApiOperation(value = "更新一个采集规则")
	@PutMapping(value = "/{id}")
	public WebResponse updateCollection(@RequestBody String body) {
	    	 
		return new WebResponse() ; 
	}
}
