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

import com.alibaba.fastjson.JSONObject;

import cn.nrzt.cps.archives.po.CMonitor;
import cn.nrzt.cps.archives.service.MonitorService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api()
@Api(tags = { "监控点档案" })
@RestController
@RequestMapping("/archives/monitor")
public class MonitorController {
	private Logger logger = LogManager.getLogger(MonitorController.class);
	@Autowired
	private MonitorService service;

	@ApiOperation(value = "获取所有监控点明细")
	@GetMapping(value = "/all")
	public WebResponse getAllMonitor() {
		logger.info("getAll");
		return new WebResponse();
//		return new WebResponse(service.getAll());
	}

	@ApiOperation(value = "按页获取监控点明细")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public WebResponse getMonitorByPage(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String DeviceType, @RequestParam String monitorName, @RequestParam String upEquipId) {
		logger.info("getAll,page ={}", pageNo);

		return new WebResponse(service.getAll(pageNo,pageSize,DeviceType,monitorName,upEquipId));
	}
	
	@ApiOperation(value = "按页获取未绑定监控点")
	@RequestMapping(value = "/pageUnbind", method = RequestMethod.GET)
	public WebResponse getMonitorUnbind(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String diagnosisId, @RequestParam String upEquipId) {
		logger.info("getAll,page ={}", pageNo);
		return new WebResponse(service.findUnbind(pageNo,pageSize,diagnosisId,upEquipId));
	}
	
	@ApiOperation(value = "按页获取已绑定监控点")
	@RequestMapping(value = "/pageBinding", method = RequestMethod.GET)
	public WebResponse getMonitorBindingYet(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String diagnosisId, @RequestParam String upEquipId) {
		logger.info("getAll,page ={}", pageNo);
		return new WebResponse(service.findBindingYet(pageNo,pageSize,diagnosisId,upEquipId));
	}

//	@ApiOperation(value = "获取一个监控点明细")
//	@RequestMapping(value = "/get", method = RequestMethod.GET)
//	public WebResponse getMonitor(@RequestParam String id) {
//		logger.info("get:{}",id);	 
//	    	 
//		return new WebResponse(service.getByEquipId(id)) ; 
//	}

	@ApiOperation(value = "增加一个监控点")
	@PostMapping(value = "/add")
	public WebResponse addMonitor(@RequestBody JSONObject body) {
		CMonitor monitor=body.toJavaObject(CMonitor.class);
		if (service.saveMonitor(monitor)<=0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}

	@ApiOperation(value = "删除一个监控点")
	@DeleteMapping(value = "/{id}")
	public WebResponse deleteMonitor(@PathVariable String id) {
		logger.info("delete:{}", id);
		if (service.deleteMonitor(id) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}

	@ApiOperation(value = "更新一个监控点")
	@PutMapping(value = "/")
	public WebResponse updateMonitor(@RequestBody JSONObject body) {
		CMonitor monitor = body.toJavaObject(CMonitor.class);
		if (service.updateMonitor(monitor) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}

	@ApiOperation(value = "设备绑定到监控点")
	@PostMapping(value = "/bind")
	public WebResponse bindEquip(@RequestParam("monitorid") String monitorid, @RequestParam("equipid") String equipid) {

		return new WebResponse();
	}

	@ApiOperation(value = "解绑监控点设备")
	@RequestMapping(value = "/release", method = RequestMethod.GET)
	public WebResponse releaseEquip(@RequestParam("monitorid") String monitorid) {
		if (service.releaseEquip(monitorid)<=0) {
			return new WebResponse(WebResponse.FAILED);
		}else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "获取获取所有位置选择框")
	@RequestMapping(value = "/getAddress", method = RequestMethod.GET)
	public WebResponse getAddress() {
		return new WebResponse(service.getAddress());
	}
	
	@ApiOperation(value = "获取楼栋")
	@RequestMapping(value = "/getBuild", method = RequestMethod.GET)
	public WebResponse getBuild() {
		return new WebResponse(service.getBuild());
	}
	
	@ApiOperation(value = "获取楼栋")
	@RequestMapping(value = "/getBuildSelect", method = RequestMethod.GET)
	public WebResponse getBuildSelect() {
		return new WebResponse(service.getBuildSelect());
	}
	
	@ApiOperation(value = "获取楼层")
	@RequestMapping(value = "/getFloor", method = RequestMethod.GET)
	public WebResponse getFloor(@RequestParam("builingNo") String builingNo) {
		return new WebResponse(service.getFloor(builingNo));
	}
	
	@ApiOperation(value = "获取房间")
	@RequestMapping(value = "/getRoom", method = RequestMethod.GET)
	public WebResponse getRoom(@RequestParam("floorNo") String floorNo) {
		return new WebResponse(service.getRoom(floorNo));
	}
}