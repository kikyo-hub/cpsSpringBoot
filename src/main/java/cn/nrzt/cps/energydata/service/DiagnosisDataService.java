package cn.nrzt.cps.energydata.service;

import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.EDiagnosisCurve;
import cn.nrzt.cps.archives.po.EDiagnosisDayRead;
import cn.nrzt.cps.energydata.mapper.DiagnosisDataMapper;
import cn.nrzt.cps.energydata.mapper.EDiagnosisCurveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisDataService {
	@Autowired
	DiagnosisDataMapper diagnosisDataMapper;
	@Autowired
	EDiagnosisCurveMapper ediagnosisCurveMapper;

	public List<CDiagnosis> getCDiagnosises(String diagnosis_id) {
		return diagnosisDataMapper.getCDiagnosises(diagnosis_id);
	}

	public List<EDiagnosisCurve> getEDiagnosisCurveById(String diagnosis_id, String data_day) {
		return ediagnosisCurveMapper.getEDiagnosisCurveById(diagnosis_id,data_day);
	}

	public List<EDiagnosisDayRead> getEDiagnosisDayReadOfWeekById(String diagnosis_id, String data_day) {
		return  ediagnosisCurveMapper.getEDiagnosisDayReadOfWeekById(diagnosis_id,data_day);
	}

	public List<EDiagnosisDayRead> getTotalEDiagnosisDayReadOfWeek(String diagnosis_id, String data_day) {
		return  ediagnosisCurveMapper.getTotalEDiagnosisDayReadOfWeek(diagnosis_id,data_day);
	}

	public List<EDiagnosisDayRead> getAllTotalEDiagnosisDayReadOfWeek(String diagnosis_id, String data_day) {
		return  ediagnosisCurveMapper.getAllTotalEDiagnosisDayReadOfWeek(diagnosis_id,data_day);
	}
}
