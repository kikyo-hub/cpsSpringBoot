package cn.nrzt.cps.energycenter.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nrzt.cps.energycenter.mapper.EnergyMonitoringMapper;
import cn.nrzt.cps.energycenter.po.SMonitorRun;
import io.swagger.annotations.ApiOperation;



@Service
public class EnergyMonitoringService {
	@Autowired
	private EnergyMonitoringMapper mapper;
	@ApiOperation(value = "查询能源监测信息")
	public List<SMonitorRun>  getElecdata(String type) {
		return mapper.getElecdata(type);
	}
	
	@ApiOperation(value = "删除一个能源监测信息")
	@Transactional(rollbackFor = Exception.class)
	public int deleteenergySurvey(String monotorId) {
		return mapper.deleteenergySurvey(monotorId);
	}
}
