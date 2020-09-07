package cn.nrzt.cps.archives.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.nrzt.cps.config.ApplicationContextProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import cn.nrzt.cps.archives.service.EquipService;
import nrzt.common.iptc.IPTCType;
import nrzt.iot.protocols.ProtocolParameter;
import nrzt.iot.protocols.builder.ProtocolParameterBuilderFactory;
import nrzt.iot.protocols.result.IProtocolResult;
import nrzt.iot.protocols.selector.OADSelector;
import nrzt.ms.MSFactory;
import nrzt.ms.rwc.IIoTRWC;

@Component
public class MeterArchives implements IDeviceWR {
	private Logger logger = LogManager.getLogger(getClass());
	@Override
	public String read(String address) {
		ProtocolParameter pb1 = ProtocolParameterBuilderFactory.newGetRequestParameter()
				.recordSelector(new OADSelector(0x60000200)).build();
		IIoTRWC rwc = MSFactory.newIoTRWC();
		IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb1, 60);
        List<String> statelist=null;
		String result_message = "";
		if (result == null) {
			result_message = "召测数据失败，可能终端不在线";
		} else {			
			result_message = result.toJSONDescription();	
			//System.out.println(result_message);
			List<Map<String, Object>> jsonlist=getjson(result_message);
			EquipService spring	= ApplicationContextProvider.getBean(EquipService.class);
			List<Map<String, Object>> list=spring.getallEquip();
			//System.out.println("listzzzzzzzzzzzzzz"+list);
			result_message="更新成功";
			for (int i = 0; i < jsonlist.size(); i++) {
				logger.info(jsonlist.get(i));
				for (int j = 0; j < list.size(); j++) {
					if(!jsonlist.get(i).get("COMM_ADDRESS").equals(list.get(j).get("COMM_ADDRESS"))) {
						continue;
					}
					statelist=	mapCompar(jsonlist.get(i),list.get(j));
					if(statelist.get(0).equals("true")) {
						int success=spring.updateEquipState("成功", list.get(j).get("COMM_ADDRESS").toString());
						if(success<0) {
						  logger.info("更新失败");
						  result_message="更新失败";
						  break;
						}
					}else{
						int success=spring.updateEquipState(statelist.get(1), list.get(j).get("COMM_ADDRESS").toString());
						if(success<0) {
						  logger.info("更新失败");
						  result_message="更新失败";
						  break;
						}
					}
					
				}
			}
		}
		return result_message;
	}

	@Override
	public String write(String address,Object value) {
		return null;
	}
	
	
	private List<String> mapCompar(Map<String, Object> map,Map<String, Object> map2) {
		List<String> list=new ArrayList<String>();
		//boolean isChange = true;
		for (Entry<String, Object> entry1 : map.entrySet()) {
			String m1value = entry1.getValue() == null ? "" :  entry1.getValue().toString();
			String m2value = map2.get(entry1.getKey()) == null ? "" :  map2.get(entry1.getKey()).toString();
			if (!m1value.equals(m2value)) {
				//isChange = false;
				list.add("false");
				list.add(m1value);
				
			}else {
				list.add("true");
			}
		}
		return list;
	} 
	
	public List<Map<String, Object>>  getjson(String result_message) {
		List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
		JSONObject jo = new JSONObject(new String(result_message));
		JSONArray spuValue = jo.getJSONArray("数据内容");
		JSONObject configmsg = spuValue.getJSONObject(0);
		spuValue= configmsg.getJSONArray("采集档案配置单元");
		logger.info(spuValue);
		for (int i = 0; i < spuValue.length(); i++) {
			Map<String, Object>map=new HashMap<String, Object>();
			JSONObject c=spuValue.getJSONObject(i);
			JSONObject extend=c.getJSONObject("扩展信息 ");
			JSONObject basic=c.getJSONObject("基本信息");
			map.put("CONFIG_NO",c.get("配置序号").toString());
			map.put("COMM_ADDRESS",basic.get("通信地址").toString());
			if(basic.get("用户类型 ").toString().equals("100")){
				map.put("EQUIP_TYPE","分支线路检测器");
			}else if(basic.get("用户类型 ").toString().equals("11")) {
				map.put("EQUIP_TYPE","三相表");
			}else if(basic.get("用户类型 ").toString().equals("101")) {
				map.put("EQUIP_TYPE","环境变送器");
			}else {
				map.put("EQUIP_TYPE","");
			}
            if(basic.get("规约类型").toString().equals("2")) {
            	map.put("PROTOCOL_TYPE","DL/T645-07");
            }else if(basic.get("规约类型").toString().equals("3")) {
            	map.put("PROTOCOL_TYPE","DL/T698");
            }else if(basic.get("规约类型").toString().equals("4")) {
            	map.put("PROTOCOL_TYPE","CJ/T 188-2004");
            }else if(basic.get("规约类型").toString().equals("1")) {
            	map.put("PROTOCOL_TYPE","DL/T645-97");
            }
			if(basic.get("端口").toString().equals("0xF2090201")) {
				map.put("COMM_MODE","载波通信");
			}
			map.put("ASSET_NO",extend.get("资产号").toString());
			map.put("PT",extend.get("PT").toString());
			map.put("CT",extend.get("CT").toString());
			list.add(map);
		}
		return list;
	}
}
