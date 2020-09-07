package cn.nrzt.cps.archives.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nrzt.cps.archives.mapper.BuildingMapper;
import cn.nrzt.cps.archives.po.CBuilding;
import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CMonitor;

@Service
public class BuildingService {
    @Autowired BuildingMapper mapper;
    @Transactional(rollbackFor = Exception.class)
	public int addDiagnosis(CDiagnosis diagnosis) {
    	int seqrelid = mapper.getSeqDiagnosysID();
    	diagnosis.setDiagnosisId((seqrelid+""));
		return mapper.addDiagnosis(diagnosis);
	}
    @Transactional(rollbackFor = Exception.class)
	public int putDiagnosis(CDiagnosis diagnosis) {
		return mapper.putDiagnosis(diagnosis);
	}
    @Transactional(rollbackFor = Exception.class)
	public int deleteDiagnosis(String  diagnosisId) {
		// TODO Auto-generated method stub
		return mapper.deleteDiagnosis(diagnosisId);
	}
    @Transactional(rollbackFor = Exception.class)
	public int addCMonitor(CMonitor cmonitor) {
    	int monitorid=mapper.getCmonitorID();
    	cmonitor.setMonitorId((monitorid+""));
		return mapper.addCMonitor(cmonitor);
	}
    @Transactional(rollbackFor = Exception.class)
	public int putCMonitor(CMonitor cmonitor) {
		
		return mapper.putCMonitor(cmonitor);
	}
    @Transactional(rollbackFor = Exception.class)
	public int deleteCMonitor(String monitorId) {
		return mapper.deleteCMonitor(monitorId);
	}
	public List<CDiagnosis> selectDiagnosis(String floorNo, String buildingNo) {

		return mapper.selectDiagnosis(floorNo,buildingNo);
	}
	public List<CMonitor> selectCMonitor(String floorNo, String buildingNo) {
		
		return mapper.selectCMonitor(floorNo,buildingNo);
	}
	public List<CBuilding> selectBuildingAllData(String buildingNo) {
		return mapper.selectBuildingAllData(buildingNo);
	}
    
}
