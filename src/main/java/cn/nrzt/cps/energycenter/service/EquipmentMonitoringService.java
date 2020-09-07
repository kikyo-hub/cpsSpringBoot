package cn.nrzt.cps.energycenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.nrzt.cps.archives.po.EDiagnosisCurve;
import cn.nrzt.cps.energycenter.mapper.EquipmentMonitoringMapper;
import cn.nrzt.cps.energycenter.po.SMonitorRun;


@Service
public class EquipmentMonitoringService {
	@Autowired
	private EquipmentMonitoringMapper mapper;
	public List<EDiagnosisCurve> getAllLidata(String date,String monitorid) {
		return mapper.getAllLidata(date,monitorid);
	}
	public List<SMonitorRun> getAlllitable(String bulidingno, String floor, String status, String equipstatus) {
		return mapper.getAlllitable(bulidingno,floor,status,equipstatus);
	}
	public List<SMonitorRun> getAlllit(int type) {
		return mapper.getAlllit(type);
	};
}
