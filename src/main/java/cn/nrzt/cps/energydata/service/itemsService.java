package cn.nrzt.cps.energydata.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.energydata.mapper.itemsMapper;
import cn.nrzt.cps.energydata.po.Items;

@Service
public class itemsService {
	@Autowired
	private itemsMapper mapper;
	public List<Items> getmeterTodaydata(String todaydata, String adress) {
		return mapper.getmeterTodaydata(todaydata, adress);
	}
	public List<Map<String, Object>> getmeter(String type) {
		return mapper.getmeter(type);
	}
	public List<Items> getmeterTodayrank(String time1, String adress) {
		return mapper.getmeterTodayrank(time1, adress);
	}
	public List<Items> getmeterWeekdata(String monday, String sunday, String adress) {
		return mapper.getmeterWeekdata(monday, sunday, adress);
	}
	public List<Items> getmaterweekRank(String monday, String sunday, String adress) {
		return mapper.getmaterweekRank(monday, sunday, adress);
	}
}
