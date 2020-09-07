package cn.nrzt.cps.archives.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.archives.mapper.AirMapper;
import cn.nrzt.cps.archives.po.EDiagnosisCurve;
import cn.nrzt.cps.energycenter.po.SMonitorRun;


@Service
public class AirService {
	@Autowired
	private AirMapper mapper;
	public List<EDiagnosisCurve> getAllAirdata(String date,String monitorid) {
		return mapper.getAllAirdata(date,monitorid);
	}
	public List<SMonitorRun>getAllairtable(String bulidingno, String floor, String status, String equipstatus) {
		
		return mapper.getAllairtable(bulidingno,floor,status,equipstatus);
	}
	public List<SMonitorRun> getAllair(int type) {
		return mapper.getAllair(type);
	}
}