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

import cn.nrzt.cps.archives.po.CEquip;
import cn.nrzt.cps.archives.service.EquipService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"监控计量设备档案"})
//@Api()
@RestController
@RequestMapping("/archives/equip")
public class EquipController {
	private Logger logger = LogManager.getLogger(EquipController.class);
	@Autowired
	private EquipService service;

	@ApiOperation(value = "新增设备")
	@PostMapping(value = "/Excel")
	public WebResponse addEquipForExcel(@RequestBody JSONObject body) {
		CEquip equip = body.toJavaObject(CEquip.class);
		if (service.saveEquipForE(equip) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "更新上级设备")
	@PostMapping(value = "/updateUpEquip")
	public WebResponse updateUpEquip(@RequestBody JSONObject body) {
		CEquip equip = body.toJavaObject(CEquip.class);
		if (service.updateUpEquip(equip) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "获取所有可用的上级级设备")
	@GetMapping(value = "/availableupper")
	public WebResponse getAllAvailableUpDevice(@RequestParam String id) {
		logger.info("getAllAvailableUpDevice");

		return new WebResponse(service.getAll());
	}

	@ApiOperation(value = "获取所有设备明细")
	@GetMapping(value = "/all")
	public WebResponse getAllEquip() {
		logger.info("getAll");
		return new WebResponse(service.getAll());
	}
	
	@ApiOperation(value = "获取所有上级设备下拉框")
	@GetMapping(value = "/allUpEquipS")
	public WebResponse getallUpEquipS() {
		logger.info("getallUpEquipS");
		return new WebResponse(service.getallUpEquipS());
	}

	@ApiOperation(value = "按页获取设备明细")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public WebResponse getEquipsByPage(@RequestParam int pageNo, @RequestParam int pageSize,
			@RequestParam String commAddress, @RequestParam String equipType, @RequestParam String equipMode,@RequestParam String equipName,@RequestParam String commMode,@RequestParam String protocolType,@RequestParam String upEquipId) {
		logger.info("getByPage,pageNo ={}", pageNo);
		return new WebResponse(service.getPage(pageNo, pageSize, commAddress, equipType, equipMode, equipName,commMode,protocolType,upEquipId));
	}

	@ApiOperation(value = "根据条件过滤设备明细")
	@GetMapping(value = "/filter")
	public WebResponse filterEquips(@RequestParam(required = false, name = "equipType") int equipType,
			@RequestParam(required = false, name = "equipName") int equipName) {
		logger.info("filterEquips:equipType ={},equipName={}", equipType, equipName);

		return new WebResponse(service.filterEquip(equipType));
	}

	@ApiOperation(value = "获取一个设备明细")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse getEquip(@PathVariable String id) {
		logger.info("getEquip:{}", id);

		return new WebResponse(service.getByEquipId(id));
	}
    
	@ApiOperation(value = "获取所有type为01设备")
	@RequestMapping(value = "/getEquipdata", method = RequestMethod.GET)
	public WebResponse getEquipdata() {
		logger.info("getEquip:{} type01");

		return new WebResponse(service.getEquipdata());
	}

	@ApiOperation(value = "增加一个设备")
	@PostMapping(value = "/")
	public WebResponse addEquip(@RequestBody JSONObject body) {
		CEquip equip = body.toJavaObject(CEquip.class);
		if (service.saveEquip(equip) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}

	@ApiOperation(value = "删除一个设备")
	@DeleteMapping(value = "/{id}")
	public WebResponse deleteEquip(@PathVariable String id) {
		logger.info("delete:{}", id);
		if (service.deleteEquip(id) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}

	@ApiOperation(value = "更新一个设备")
	@PutMapping(value = "/")
	public WebResponse updateEquip(@RequestBody JSONObject body) {
		CEquip equip = body.toJavaObject(CEquip.class);
		if (service.updateEquip(equip) <= 0) {
			return new WebResponse(WebResponse.FAILED);
		} else {
			return new WebResponse(WebResponse.SuccessJsonResult());
		}
	}
	
	@ApiOperation(value = "按页获取未绑定监测点设备明细")
	@RequestMapping(value = "/getPEquipNotM", method = RequestMethod.GET)
	public WebResponse getPEquipNotM(@RequestParam int pageNo, @RequestParam int pageSize) {
		logger.info("getByPage,pageNo ={}", pageNo);
		return new WebResponse(service.getPEquipNotM(pageNo, pageSize));
	}
	
	@ApiOperation(value = "按页获取所有设备明细")
	@RequestMapping(value = "/getAllSubordinateEquipment", method = RequestMethod.GET)
	public WebResponse getAllSubordinateEquipment(@RequestParam int pageNo, @RequestParam int pageSize,@RequestParam ("commAddress")String address) {
		logger.info("getByPage,pageNo ={}", pageNo,pageSize);
		return new WebResponse(service.getAllSubordinateEquipment(pageNo, pageSize,address));
	}
    
	@ApiOperation(value = "按页获取所有下级设备")
	@RequestMapping(value = "/getAllLowerEquipment", method = RequestMethod.GET)
	public WebResponse getAllLowerEquipment(@RequestParam ("commAddress")String address) {
		logger.info("getByPage,pageNo ={}", address);
		return new WebResponse(service.getAllLowerEquipment(address));
	}
}
