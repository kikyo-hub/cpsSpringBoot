package cn.nrzt.cps.energyuse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.energyuse.mapper.TotalEnergyMapper;

@Service
public class TotalEnergyService {

	@Autowired
	TotalEnergyMapper mapper;
	
	public Map<String, Object> getHomeInfo() {
		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("buildingNum", mapper.getBuilding());
//		map.put("monitorNum", mapper.getMonitor());
//		map.put("diagnosisNum", mapper.getDiagnosis());
//		map.put("totalElectric", "66");//总用电量
//		map.put("totalWater", "77");//总用水量
//		map.put("totalGas", "88");//总用气量
//		map.put("totalHot", "99");//总用热量
		Random r = new Random(1);
		
		Map<String, Object> electric=new HashMap<String, Object>();
		electric.put("now", r.nextInt(10)*10);
		electric.put("yesterday", r.nextInt(10)*10);
		electric.put("week", r.nextInt(10)*70);
		electric.put("month", r.nextInt(10)*300);
		
		Map<String, Object> water=new HashMap<String, Object>();
		water.put("now", r.nextInt(10)*10);
		water.put("yesterday", r.nextInt(10)*10);
		water.put("week", r.nextInt(10)*70);
		water.put("month", r.nextInt(10)*300);
		
		Map<String, Object> gas=new HashMap<String, Object>();
		gas.put("now", r.nextInt(10)*10);
		gas.put("yesterday", r.nextInt(10)*10);
		gas.put("week", r.nextInt(10)*70);
		gas.put("month", r.nextInt(10)*300);
		
		Map<String, Object> hot=new HashMap<String, Object>();
		hot.put("now", r.nextInt(10)*10);
		hot.put("yesterday", r.nextInt(10)*10);
		hot.put("week", r.nextInt(10)*70);
		hot.put("month", r.nextInt(10)*300);
		
		map.put("electric", electric);
		map.put("water", water);
		map.put("gas", gas);
		map.put("hot", hot);
		
		List<Map<String, Object>> diagnosis=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 12; i++) {
			Map<String, Object> m=new HashMap<String, Object>();
			m.put("x", "诊断点"+(i+1));
			m.put("y", r.nextInt(100));
			diagnosis.add(m);
		}
		map.put("diagnosis", diagnosis);
		
		List<Map<String, Object>> monitor=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 12; i++) {
			Map<String, Object> m=new HashMap<String, Object>();
			m.put("x", "监测点"+(i+1));
			m.put("y", r.nextInt(100));
			monitor.add(m);
		}
		map.put("monitor", monitor);
		
		List<Map<String, Object>> diagnosisRank=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 7; i++) {
			Map<String, Object> m=new HashMap<String, Object>();
			m.put("name", "诊断点"+(i+1));
			m.put("total", r.nextInt(10)+(100-i)*10);
			diagnosisRank.add(m);
		}
		map.put("diagnosisRank", diagnosisRank);
		
		List<Map<String, Object>> monitorRank=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 7; i++) {
			Map<String, Object> m=new HashMap<String, Object>();
			m.put("name", "监测点"+(i+1));
			m.put("total", r.nextInt(10)+(100-i)*10);
			monitorRank.add(m);
		}
		map.put("monitorRank", monitorRank);
		return map;
	}

}
