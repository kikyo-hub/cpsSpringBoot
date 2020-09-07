package cn.nrzt.cps.archives.device;

import cn.nrzt.cps.archives.po.CEquip;
import nrzt.common.iot.ProtocolType;
import nrzt.common.iptc.IPTCType;
import nrzt.common.util.ZTUtil;
import nrzt.iot.protocols.IDataType;
import nrzt.iot.protocols.IProtocolObjectValue;
import nrzt.iot.protocols.ProtocolParameter;
import nrzt.iot.protocols.builder.ProtocolParameterBuilderFactory;
import nrzt.iot.protocols.constant.Baudrate;
import nrzt.iot.protocols.constant.PhaseLine;
import nrzt.iot.protocols.datatype.Array;
import nrzt.iot.protocols.datatype.Enumeration;
import nrzt.iot.protocols.datatype.LongUnsigned;
import nrzt.iot.protocols.datatype.OAD;
import nrzt.iot.protocols.datatype.OctetString;
import nrzt.iot.protocols.datatype.Structure;
import nrzt.iot.protocols.datatype.Tsa;
import nrzt.iot.protocols.datatype.Unsigned;
import nrzt.iot.protocols.result.IProtocolResult;
import nrzt.ms.MSFactory;

public class Issue implements IDeviceWR{

	public String read(String address) {
		return null;
		
	}

	@Override
	public String write(String address, Object value) {
	     CEquip ce=	(CEquip) value;
		String resultmsg="召测数据失败，可能终端不在线";
		IDataType[] meters =new IDataType[1];	
		int no =0,pn =1;
 	  Structure basic =new Structure(
				new Tsa(ce.getCommAddress()),
				Baudrate.BPS9600,
				new Enumeration(ProtocolType.OOP_698_45.Id()),
				new OAD(toComOad(ce.getCommMode())),
				new OctetString((byte)0),
				new Unsigned((byte) 4),
				new Unsigned((byte) 12),
				PhaseLine.Single,
				new LongUnsigned(2200),
				new LongUnsigned(1500)
		);
		Object pnv =null;//m.get("METER_REG_SN");			
		int curpn =(pnv ==null?pn++:ZTUtil.toInteger(pnv));
		meters[0] =new Structure(					
				new LongUnsigned(curpn),
				basic,
				new Structure(
						new Tsa(""),//采集器
						new OctetString((byte)0),
						new LongUnsigned(1),
						new LongUnsigned(1)
						),
				new Array()
		);
			ProtocolParameter pp = ProtocolParameterBuilderFactory
					.newActionRequestParameter()
					.addDataObject(0x60008000, new Array(meters))
					.build();
			IProtocolResult result=MSFactory.newIoTRWC().RWC(address, IPTCType.REQUEST_RESPONSE, pp, 5);
			if(result==null) {
				resultmsg="召测数据失败，可能终端不在线";
			} else if (result.resultValueList().size() > 0) {
				IProtocolObjectValue pov = result.resultValueList().get(0);
				resultmsg = pov.Value().toString();
				if(resultmsg.equals("0")) {
					resultmsg="下发成功";
				}
			}
		return resultmsg;
	}
    private static  long  toComOad(String dt) {
		if("02".equals(dt))
		{//RS485
			return 0xF2010201;
		}
		else if("03".equals(dt))
		{//载波
			return 0xF2090201;
		}
		return 0xF2010201;
	}
}


