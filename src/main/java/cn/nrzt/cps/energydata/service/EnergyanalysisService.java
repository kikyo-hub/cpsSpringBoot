package cn.nrzt.cps.energydata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.energydata.mapper.EnergyanalysisMapper;
import cn.nrzt.cps.energydata.po.Energyanalysis;
import cn.nrzt.cps.energydata.po.Energydio;



@Service
public class EnergyanalysisService {
	@Autowired
	private EnergyanalysisMapper mapper;
	public List<Energyanalysis> getTodaydata(String todaydata, String Yesterdaydata, String Beforedaydata) {
		return mapper.getTodaydata(todaydata, Yesterdaydata, Beforedaydata);
	}
	public List<Energyanalysis> getmonitorRank(String today, String Yesterday) {
		return mapper.getmonitorRank(today, Yesterday);
	}
	public List<Energyanalysis> getmonitorweekData(String begindate, String enddate) {
		return mapper.getmonitorweekData(begindate, enddate);
	}
	public List<Energyanalysis> getmonitorweekDc(String monday, String sunday) {
		return mapper.getmonitorweekDc(monday, sunday);
	}
	public List<Energydio> getdiagnosisdayData(String today, String yesterday) {
		return mapper.getdiagnosisdayData(today, yesterday);
	}
	public List<Energydio> getdiagnosisdayRank(String today, String yesterday) {
		return mapper.getdiagnosisdayRank(today, yesterday);
	}
	public List<Energydio> getdiagnosisweekData(String monday, String sunday) {
		return mapper.getdiagnosisweekData(monday, sunday);
	}
}
