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

import cn.nrzt.cps.archives.po.CBuilding;
import cn.nrzt.cps.archives.po.CEnterprise;
import cn.nrzt.cps.archives.po.CFloor;
import cn.nrzt.cps.archives.po.CRoom;
import cn.nrzt.cps.archives.service.EnterpriseService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "企业信息" })
@RestController
@RequestMapping("/archives")
public class EnterpriseController {
	private Logger logger = LogManager.getLogger(EnterpriseController.class);
	@Autowired
	private EnterpriseService service;
	
	@ApiOperation(value = "获取企业信息")
	@GetMapping(value = "/customer")
	public WebResponse  getEnterpriseData() {
		logger.info("GetEnterpriseData:信息");
		return new WebResponse(service.getAllEnterprisedata());
	}
	
	@ApiOperation(value = "获取楼栋（诊断点）信息")
	@GetMapping(value = "/custome")
	public WebResponse  getEnterprisefloor(@RequestParam("buildingno") String buildingno) {
		 return new WebResponse(service.getEnterprisefloor(buildingno));
	}
	
	@ApiOperation(value = "获取多个楼栋（诊断点）信息")
	@GetMapping(value = "/manycustome")
	public WebResponse  getManyEnterprisefloor(@RequestParam("enterpriseid") String enterpriseid) {
		 return new WebResponse(service.getManyEnterprisefloor(enterpriseid));
	}
	//Detection point
	@ApiOperation(value = "获取检测点信息")
	@GetMapping(value = "/detectionPoint")
	public WebResponse  getDetectionPoint(@RequestParam("buildingno") String buildingno) {
		return new WebResponse(service.getDetectionPoint(buildingno));
	}
	
	@ApiOperation(value = "增加一个楼栋信息")
	@PostMapping(value = "/add")
	public WebResponse addCollection(@RequestBody JSONObject body) {
		System.out.println(body+":body");
		CBuilding equip = body.toJavaObject(CBuilding.class);
		if (service.addCollection(equip,body)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
		
	}
	
	@ApiOperation(value = "删除一个楼栋信息")
	@DeleteMapping(value = "/deletebuildingno")
	public WebResponse deleteCollection(@RequestParam("id") String id) {
		logger.info("delete:{}",id);	 
		if (service.deleteCollection(id) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "更新一个楼栋信息")
	@PutMapping(value = "/putbuildingno")
	public WebResponse updateCollection(@RequestBody JSONObject body) {
		String time=body.getString("buildingTime").substring(0, 10);
		body.put("buildingTime", time);
		CBuilding equip = body.toJavaObject(CBuilding.class);
		logger.info("put:{}",body);	 
		if (service.updateCollection(equip) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value="更新企业信息")
	@PutMapping(value="/putCustomerData")
	public WebResponse updateCustomerData(@RequestBody JSONObject body) {
		CEnterprise enterprise = body.toJavaObject(CEnterprise.class);
		logger.info("enterprise:{}",body);	 
		if (service.updateCustomerData(enterprise) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation("用楼栋获取楼层房间信息")
	@GetMapping("/getFloorRoomForBuild")
	public WebResponse getFloorRoomForBuild(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String builingNo) {
		return new WebResponse(service.getFloorRoomForBuild(pageNo,pageSize,builingNo));
	}
	
	@ApiOperation("增加楼层信息")
	@PutMapping("/insertFloor")
	public WebResponse insertFloor(@RequestBody CFloor cFloor) {
		return new WebResponse(service.insertFloor(cFloor));
	}
	
	@ApiOperation("更新楼层信息")
	@PutMapping("/updateFloor")
	public WebResponse updateFloor(@RequestBody CFloor cFloor) {
		return new WebResponse(service.updateFloor(cFloor));
	}
	
	@ApiOperation("删除楼层信息")
	@DeleteMapping("/deleteFloor")
	public WebResponse deleteFloor(@RequestParam String floorNo) {
		return new WebResponse(service.deleteFloor(floorNo));
	}
	
	@ApiOperation("增加房间信息")
	@PutMapping("/insertRoom")
	public WebResponse insertRoom(@RequestBody CRoom cRoom) {
		return new WebResponse(service.insertRoom(cRoom));
	}
	
	@ApiOperation("更新房间信息")
	@PutMapping("/updateRoom")
	public WebResponse updateRoom(@RequestBody CRoom cRoom) {
		return new WebResponse(service.updateRoom(cRoom));
	}
	
	@ApiOperation("删除房间信息")
	@DeleteMapping("/deleteRoom")
	public WebResponse deleteRoom(@RequestParam String roomNo) {
		return new WebResponse(service.deleteRoom(roomNo));
	}

}
