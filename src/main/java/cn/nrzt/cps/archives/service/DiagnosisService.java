package cn.nrzt.cps.archives.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;

import cn.nrzt.cps.archives.mapper.CDiagnosisMapper;
import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CEquip;
import cn.nrzt.cps.archives.po.CMonitor;
import cn.nrzt.cps.web.PageResult;

@Service
public class DiagnosisService {

	@Autowired
	private CDiagnosisMapper mapper;

	public PageResult<CDiagnosis> getAll(int pageNo, int pageSize, Integer rowSelectId, String diagnosisId,
			String diagnosisType) {
		PageHelper.startPage(pageNo, pageSize);
		List<CDiagnosis> list = mapper.getAll(diagnosisId, diagnosisType);
		List<Map<String, Object>> MDR = mapper.getMDrel();
		List<Map<String, Object>> RDMR = mapper.getRDMR();
//		diagnosisRuleList
		for (CDiagnosis c : list) {
			String dId=c.getDiagnosisId();
			List<Map<String, Object>> tempElectro=new ArrayList<Map<String,Object>>();//3分支
			List<Map<String, Object>> tempEnvironment=new ArrayList<Map<String,Object>>();//4环境
			List<Map<String, Object>> tempAir=new ArrayList<Map<String,Object>>();//1空调
			for (Map<String, Object> mapMDR : MDR) {
				if (dId.equals(mapMDR.get("DIAGNOSIS_ID")+"")&&"3".equals(mapMDR.get("DEVICE_TYPE")+"")) {
					tempElectro.add(mapMDR);
				}
				if (dId.equals(mapMDR.get("DIAGNOSIS_ID")+"")&&"5".equals(mapMDR.get("DEVICE_TYPE")+"")) {
					tempEnvironment.add(mapMDR);
				}
				if (dId.equals(mapMDR.get("DIAGNOSIS_ID")+"")&&"1".equals(mapMDR.get("DEVICE_TYPE")+"")) {
					tempAir.add(mapMDR);
				}
			}
			List<Map<String, Object>> tempDiagnosisRuleList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> mapRDMR : RDMR) {
				if ((mapRDMR.get("DIAGNOSIS_ID")+"").equals(dId)) {
					Map<String, Object> m=new HashMap<String, Object>();
					m.put("value", mapRDMR.get("VALUE")+"");
					m.put("key", mapRDMR.get("NAME")+"");
					m.put("display", mapRDMR.get("NAME")+"");
					tempDiagnosisRuleList.add(m);
				}
			}
			c.setElectro(tempElectro);
			c.setEnvironment(tempEnvironment);
			c.setAir(tempAir);
			c.setDiagnosisRuleList(tempDiagnosisRuleList);
		}
		return new PageResult<CDiagnosis>(list);
	}

	@SuppressWarnings("unchecked")
	public int saveDiagnosis(CDiagnosis diagnosis) {
		int seq = mapper.getSeq();
		diagnosis.setDiagnosisId((seq + ""));
		List<String> lll=(List<String>) diagnosis.getDiagnosisRuleList();
		for (String diagnosisRuleId : lll) {
			mapper.saveDiagnosisAndDrule(diagnosis.getDiagnosisId(),diagnosisRuleId);
		}
		List<String> monitorDMRids=(List<String>) diagnosis.getMonitorDMRids();
		for (Object monitorDMRid : monitorDMRids) {
			mapper.AddDiagnosisMonRel(diagnosis.getDiagnosisId(), monitorDMRid);
		}
		return mapper.saveDiagnosis(diagnosis);
	}

	public int deleteDiagnosis(String id) {
		mapper.clearDiagnosisAndDrule(id);
		return mapper.deleteDiagnosis(id);
	}

	@SuppressWarnings("unchecked")
	public int updateDiagnosis(CDiagnosis diagnosis) {
		mapper.clearDiagnosisAndDrule(diagnosis.getDiagnosisId());
		List<String> lll=(List<String>) diagnosis.getDiagnosisRuleList();
		for (String diagnosisRuleId : lll) {
			mapper.saveDiagnosisAndDrule(diagnosis.getDiagnosisId(),diagnosisRuleId);
		}
		
		mapper.deleteDMrel(diagnosis.getDiagnosisId());
		List<String> monitorDMRids=(List<String>) diagnosis.getMonitorDMRids();
		for (Object monitorDMRid : monitorDMRids) {
			mapper.AddDiagnosisMonRel(diagnosis.getDiagnosisId(), monitorDMRid);
		}
		return mapper.updateDiagnosis(diagnosis);
	}

	public int bandingDMr(String diagnosisId, List<?> monitorIds) {
		for (Object monitorId : monitorIds) {
			mapper.AddDiagnosisMonRel(diagnosisId, monitorId);
		}
		return 1;
	}

	public int updateDMrel(String diagnosisId, List<?> monitorIds) {
		mapper.deleteDMrel(diagnosisId);
		for (Object monitorId : monitorIds) {
			mapper.AddDiagnosisMonRel(diagnosisId, monitorId);
		}
		return 1;
	}

	public List<?> getDiagnosisRuleSelect() {
		return mapper.getDiagnosisRuleSelect();
	}

	public PageResult<CMonitor> getAllMonitorPointPage(int pageNo, int pageSize,
			String deviceType,String upEquipId,String monitorId,String monitorName) {
		PageHelper.startPage(pageNo, pageSize);
		List<CMonitor> list=mapper.getAllMonitorPointPage(deviceType,upEquipId,monitorId,monitorName);
		return new PageResult<CMonitor>(list);
	}
}
