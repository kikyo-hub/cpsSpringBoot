package cn.nrzt.cps.archives.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.nrzt.cps.archives.device.DeviceReset;
import cn.nrzt.cps.archives.device.DeviceTime;
import cn.nrzt.cps.archives.device.FactoryVersion;
import cn.nrzt.cps.archives.device.Issue;
import cn.nrzt.cps.archives.device.MeterArchives;
import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CEquip;
import cn.nrzt.cps.archives.service.EquipService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nrzt.common.TimeIntervalUnit;
import nrzt.common.iot.ProtocolType;
import nrzt.common.iptc.IPTCType;
import nrzt.common.util.ZTUtil;
import nrzt.iot.protocols.IDataType;
import nrzt.iot.protocols.ProtocolParameter;
import nrzt.iot.protocols.builder.ProtocolParameterBuilderFactory;
import nrzt.iot.protocols.datatype.DateTimeS;
import nrzt.iot.protocols.datatype.MS;
import nrzt.iot.protocols.datatype.TI;
import nrzt.iot.protocols.datatype.Tsa;
import nrzt.iot.protocols.result.IProtocolResult;
import nrzt.iot.protocols.selector.ColumnFilter;
import nrzt.iot.protocols.selector.QuerySelector;
import nrzt.iot.protocols.selector.RecordSelector7;
import nrzt.iot.protocols.selector.RelationColumn;
import nrzt.ms.MSFactory;
import nrzt.ms.rwc.IIoTRWC;

@Api(tags = { "能源控制器管理" })
@RestController
@RequestMapping("/archives")
public class EnergyControlManager {
	@Autowired
	EquipService service;
	private Logger logger = LogManager.getLogger(getClass());
	// 召测版本信息
	@RequestMapping(value = "/callversion", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ApiOperation(value = "版本召测")
	public String callversion(@RequestParam("address") String address) {
		logger.info("召测{}版本信息", address);	
		return new FactoryVersion().read(address);
	}
	
	// 召测档案信息
	@RequestMapping(value = "/callmeter", method = RequestMethod.GET)
	@ApiOperation(value = "电表档案召测")
	public WebResponse callMeterArchives(@RequestParam("address") String address) {
		logger.info("召测{}电表档案信息",address);	
	   String result=new MeterArchives().read(address);
	   if(result.equals("更新成功")) {
		   return  new WebResponse(service.getAllSubordinateEquipment(1, 6,address));
	   }else if(result.equals("召测数据失败，可能终端不在线")){
		   return new WebResponse("召测数据失败，可能终端不在线"); 
	   }else {
		   return new WebResponse("匹配失败");
	   }
		
	}

	@RequestMapping(value = "/call_time", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ApiOperation(value = "召测时间")
	public String call_Time(@RequestParam("address") String address) {
		logger.info("召测设备{}时间", address);	
		return new DeviceTime().read(address);
	}

	// 设置终端的时间
	@RequestMapping(value = "/set_time", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ApiOperation(value = "设置时间")
	public String set_Time( @RequestParam("address") String address) {
		logger.info("设置{}设备时间" , address);	
		return new DeviceTime().write(address,null);
	}

	// 复位
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	@ApiOperation(value = "远程复位")
	public WebResponse reset(@RequestParam("address") String address,@RequestParam("radiomsg") String radiomsg) {
		logger.info("设备{}远程复位" ,address);	
		return new WebResponse(new DeviceReset().write(address, radiomsg));
	}

	@RequestMapping(value = "/getCurveIMsg", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ApiOperation(value = "召测电流曲线")
	public String getCurveIMsg(String address, String meteradd, String call_starttime, String call_endtime) {
		DateTimeS start = new DateTimeS(call_starttime);
		DateTimeS end = new DateTimeS(call_endtime);
		ProtocolParameter pb1 = ProtocolParameterBuilderFactory.newGetRequestParameter()
				.recordSelector(
						new QuerySelector(0x60120300,
								new RecordSelector7(start, end, new TI(TimeIntervalUnit.TU_MINUTE, 15),
										new MS((byte) 1)),
								new ColumnFilter(0x202A0200), new ColumnFilter(0x60400200),
								new ColumnFilter(0x60410200), new ColumnFilter(0x60420200),
								new ColumnFilter(0x50020200, new RelationColumn(0x20210200, 0x20010200))))
				.build();
		IIoTRWC rwc = MSFactory.newIoTRWC();
		IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb1, 30);
		String result_message = "";
		if (result == null) {
			return "召测数据失败，可能终端不在线";
		} else {
			result_message = result.toJSONDescription();
			return result_message;
		}
	}

	@RequestMapping(value = "/getDailyFreeze", method = RequestMethod.GET, produces = "text/html;charset=UTF-8;")
	@ApiOperation(value = "冻结数据161和163") // 冻结数据161
	public String getDailyFreeze(String address, String meteradd, String callstarttime, String callendtime) {
		// 判断时间是否为空，不为空则取传入的时间调用接口
		if ("".equals(callstarttime.trim()) || "".equals(callendtime.trim())) {
			ProtocolParameter pb1 = ProtocolParameterBuilderFactory.newGetRequestParameter()
					.recordSelector(new QuerySelector(0x60120300,
							new RecordSelector7(DateTimeS.LastDay0Hour(), new DateTimeS(),
									new MS((byte) 3, new Tsa(meteradd))),
							new ColumnFilter(0x202A0200), new ColumnFilter(0x60400200), new ColumnFilter(0x60410200),
							new ColumnFilter(0x60420200),
							new ColumnFilter(0x50040200, new RelationColumn(0x20210200, 0x00100200, 0x00200200))))
					.build();
			IIoTRWC rwc = MSFactory.newIoTRWC();
			IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb1, 30);
			String result_message = "";
			if (result == null) {
				return "召测数据失败，可能终端不在线";
			} else {
				result_message = result.toJSONDescription();
				// System.out.println(result.toJSONDescription());
				return result_message;
			}
		} else {
			DateTimeS dt = new DateTimeS(callstarttime);
			DateTimeS dt1 = new DateTimeS(callendtime);
			ProtocolParameter pb1 = ProtocolParameterBuilderFactory.newGetRequestParameter()
					.recordSelector(new QuerySelector(0x60120300,
							new RecordSelector7(dt, dt1, new MS((byte) 3, new Tsa(meteradd))),
							new ColumnFilter(0x202A0200), new ColumnFilter(0x60400200), new ColumnFilter(0x60410200),
							new ColumnFilter(0x60420200),
							new ColumnFilter(0x50040200, new RelationColumn(0x20210200, 0x00100200, 0x00200200))))
					.build();
			// new ProtocolParameterBuilder(RequestType.GET).setRecordSelector(new
			// OADSelector(0x43000300)) .build();
			IIoTRWC rwc = MSFactory.newIoTRWC();
			IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb1, 30);
			String result_message = "";
			if (result == null) {
				return "召测数据失败，可能终端不在线";
			} else {
				result_message = result.toJSONDescription();
				// System.out.println(result.toJSONDescription());
				return result_message;
			}

		}

	}
	// 召测档案信息
	@RequestMapping(value = "/issue", method = RequestMethod.POST)
	@ApiOperation(value = "电表档案下发")
      public  WebResponse Issue(@RequestBody JSONObject body) {
		CEquip ce = body.toJavaObject(CEquip.class);
		String address = ce.getUpEquipId();
		String result=new Issue().write(address, ce);
  	   return new WebResponse(result);
     }
    


}
