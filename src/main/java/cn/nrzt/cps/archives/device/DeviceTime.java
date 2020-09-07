package cn.nrzt.cps.archives.device;

import java.text.SimpleDateFormat;
import java.util.Date;

import nrzt.common.iptc.IPTCType;
import nrzt.iot.protocols.IProtocolObjectValue;
import nrzt.iot.protocols.ProtocolParameter;
import nrzt.iot.protocols.builder.ProtocolParameterBuilderFactory;
import nrzt.iot.protocols.datatype.DateTimeS;
import nrzt.iot.protocols.objectvalue.ProtocolDARValue;
import nrzt.iot.protocols.result.IProtocolResult;
import nrzt.iot.protocols.selector.OADSelector;
import nrzt.ms.MSFactory;
import nrzt.ms.rwc.IIoTRWC;

/**
 * 设备时间
 * @author joe.han 2020年8月14日
 *
 */
public class DeviceTime implements IDeviceWR {
	
	@Override
	public String read(String address) {
		ProtocolParameter pb3 = ProtocolParameterBuilderFactory.newGetRequestParameter()
				.recordSelector(new OADSelector(0x40000200)).build();
		IIoTRWC rwc = MSFactory.newIoTRWC();
		IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb3, 30);
		String result_message = "召测数据失败，可能终端不在线";
		if (result == null)
			return result_message;
		if (result.resultValueList().size() > 0) {
			IProtocolObjectValue pov = result.resultValueList().get(0);
			DateTimeS dt = (DateTimeS) pov.Value();
			long diff = new Date().getTime() - dt.toMillisSecond();
			result_message = "终端时间为：" + dt.toString() + "		";
			if (Math.abs(diff) > 30 * 1000) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				result_message += "@系统时间为：" + df.format(new Date()) + " 时间差为：" + diff / 1000 + "秒  请设置为系统时间";
			}
		}
		return result_message;
	}

	@Override
	public String write(String address,Object value) {
		ProtocolParameter pb22 = ProtocolParameterBuilderFactory.newSetRequestParameter()
				.addDataObject(0x40000200, DateTimeS.Now()).build();
		IIoTRWC rwc = MSFactory.newIoTRWC();
		IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb22, 30);

		if (result.resultValueList().size() > 0) {
			IProtocolObjectValue pov = result.resultValueList().get(0);
			if(pov instanceof ProtocolDARValue)
			{
				ProtocolDARValue pv  =(ProtocolDARValue) pov;
				return pv.Dar().Desc();
			}
		}
		return "设置时钟数据失败，可能终端不在线";
	}

}
