package cn.nrzt.cps.archives.device;

import nrzt.common.iptc.IPTCType;
import nrzt.iot.protocols.IProtocolObjectValue;
import nrzt.iot.protocols.ProtocolParameter;
import nrzt.iot.protocols.builder.ProtocolParameterBuilderFactory;
import nrzt.iot.protocols.result.IProtocolResult;
import nrzt.iot.protocols.selector.OADSelector;
import nrzt.ms.MSFactory;
import nrzt.ms.rwc.IIoTRWC;

public class FactoryVersion implements IDeviceWR {

	@Override
	public String read(String address) {
		ProtocolParameter pb1 = ProtocolParameterBuilderFactory.newGetRequestParameter()
				.recordSelector(new OADSelector(0x43000300)).build();
		IIoTRWC rwc = MSFactory.newIoTRWC();
		IProtocolResult result = rwc.RWC(address, IPTCType.REQUEST_RESPONSE, pb1, 30);

		String result_message = "";

		if (result == null) {
			result_message = "召测数据失败，可能终端不在线";
		} else if (result.resultValueList().size() > 0) {
			IProtocolObjectValue pov = result.resultValueList().get(0);
			result_message = pov.Value().toString();
			
		}
		return result_message;
	}

	@Override
	public String write(String address,Object value) {
		return null;
	}

}
