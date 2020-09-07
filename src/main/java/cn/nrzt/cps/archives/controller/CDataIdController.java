package cn.nrzt.cps.archives.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.nrzt.cps.archives.service.CDataIdService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "C_DATAID" })
@RestController
@RequestMapping("/dataid")
public class CDataIdController {

	@Autowired
	private CDataIdService service;
	
	@ApiOperation(value = "查询C_DATAID信息")
	@GetMapping(value = "/getall")
	public WebResponse getallCDataIds() {
		return new WebResponse(service.getallCDataIds());
		
		
		
		//kii
		
	}
}
