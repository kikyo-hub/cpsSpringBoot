package cn.nrzt.cps.archives.device;

import nrzt.common.iptc.IPTCType;
import nrzt.iot.protocols.IProtocolObjectValue;
import nrzt.iot.protocols.ProtocolParameter;
import nrzt.iot.protocols.builder.ProtocolParameterBuilderFactory;
import nrzt.iot.protocols.datatype.Null;
import nrzt.iot.protocols.result.IProtocolResult;
import nrzt.ms.MSFactory;
import nrzt.ms.rwc.IIoTRWC;

/**
 * 设备复位
 * @author joe.han 2020年8月14日
 *
 */
public class DeviceReset implements IDeviceWR {

	@Override
	public String read(String address) {
		return null;
	}

	@Override
	public String write(String address, Object value) {
		String result_message = "";
		if ("硬件初始化".equals(value)) {
			ProtocolParameter pb = ProtocolParameterBuilderFactory.newActionRequestParameter()
					.addDataObject(0x43000100, new Null()).build();
			IIoTRWC rwc = MSFactory.newIoTRWC();
			IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb, 5);
			if (result == null) {
				result_message = "硬件初始化失败，可能终端不在线";
			} else if (result.resultValueList().size() > 0) {
				IProtocolObjectValue pov = result.resultValueList().get(0);
				result_message = pov.Value().toString();
//				logger.warn(result_message + ":复位");
			}
		} else if ("数据初始化".equals(value)) {
			ProtocolParameter pb = ProtocolParameterBuilderFactory.newActionRequestParameter()
					.addDataObject(0x43000300, new Null()).build();
			IIoTRWC rwc = MSFactory.newIoTRWC();
			IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb, 5);
			if (result == null) {
				result_message = "数据初始化失败，可能终端不在线";
			} else if (result.resultValueList().size() > 0) {
				IProtocolObjectValue pov = result.resultValueList().get(0);
				result_message = pov.Value().toString();
//				logger.warn(result_message + ":复位");
			}
		} else if ("参数及数据初始化".equals(value)) {
			ProtocolParameter pb = ProtocolParameterBuilderFactory.newActionRequestParameter()
					.addDataObject(0x43000400, new Null()).build();
			IIoTRWC rwc = MSFactory.newIoTRWC();
			IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb, 5);
			if (result == null) {
				result_message = "参数及数据初始化失败，可能终端不在线";
			} else if (result.resultValueList().size() > 0) {
				IProtocolObjectValue pov = result.resultValueList().get(0);
				result_message = pov.Value().toString();
//				logger.warn(result_message + ":参数及数据初始化");
			}
		}
		
		return result_message;
	}

}
