package cn.nrzt.cps.energydata.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.nrzt.cps.energydata.mapper.MonitorPointDetailedMapper;
import cn.nrzt.cps.energydata.po.MonitorCurveEnergy;
import cn.nrzt.cps.energydata.po.MonitorCurveEnvironment;
import cn.nrzt.cps.energydata.po.MonitorDayEnergy;
import cn.nrzt.cps.web.PageResult;

@Service
public class MonitorPointDetailedService {

	@Autowired
	private MonitorPointDetailedMapper mapper;

	public PageResult<MonitorCurveEnergy> getMonitorCurveEnergy(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<MonitorCurveEnergy> list = mapper.getMonitorCurveEnergy();
		return new PageResult<MonitorCurveEnergy>(list);
	}

	public PageResult<MonitorCurveEnvironment> getMonitorCurveEnvironment(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<MonitorCurveEnvironment> list = mapper.getMonitorCurveEnvironment();
		return new PageResult<MonitorCurveEnvironment>(list);
	}

	public List<?> getMonitorTree() {
		List<Map<String, Object>> list = mapper.getMonitorTree();
		for (Map<String, Object> map : list) {
			map.put("icon", "cluster");
		}
		return list;
	}

	public PageResult<MonitorDayEnergy> getMonitorDayEnergy(int pageNo, int pageSize, String devAddress) {
		PageHelper.startPage(pageNo, pageSize);
		List<MonitorDayEnergy> list = mapper.getMonitorDayEnergy(devAddress);
		for (MonitorDayEnergy monitorDayEnergy : list) {
			BigDecimal thisDayPapR = monitorDayEnergy.gettPapR();
			String lastDataTime = monitorDayEnergy.getLastDataTime();
			for (MonitorDayEnergy monitorDayEnergy2 : list) {
				String thisDataTime = monitorDayEnergy2.getThisDataTime();
				if (thisDataTime.equals(lastDataTime)) {
					BigDecimal lastPap = monitorDayEnergy2.gettPapR();
					monitorDayEnergy2.setThisDayPapR(thisDayPapR.subtract(lastPap));
				}
			}
		}
		return new PageResult<MonitorDayEnergy>(list);
	}
}
