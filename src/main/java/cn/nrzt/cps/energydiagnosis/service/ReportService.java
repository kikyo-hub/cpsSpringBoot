package cn.nrzt.cps.energydiagnosis.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.nrzt.cps.energydiagnosis.mapper.ReportMapper;
import cn.nrzt.cps.energydiagnosis.po.Report;

@Service
public class ReportService {
	
	@Autowired
	private ReportMapper mapper;
	public List<Report>getMonitorNum() {
		return mapper.getMonitorNum();
	}
}
