package cn.nrzt.cps.energydata.service;

import cn.nrzt.cps.archives.mapper.CEquipMapper;
import cn.nrzt.cps.archives.po.*;
import cn.nrzt.cps.energydata.mapper.EMonitorCurveMapper;
import cn.nrzt.cps.energydata.mapper.MonitorDataMapper;
import cn.nrzt.cps.energydata.po.MonitorCurve;

import org.apache.catalina.core.ApplicationContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorDataService {
	@Autowired
	MonitorDataMapper mapper;
	@Autowired
	EMonitorCurveMapper emonitorCurveMapper;
	@Autowired
	CEquipMapper cequipMapper;
//*********************************** c_monitor ************************************************************************
	public List<CMonitor> getCMonitorsOfElectric(String monitor_id){
		return mapper.getCMonitorsOfElectric(monitor_id);
	};

	
	public List<CMonitor> getCMonitors(String monitor_id){
		return mapper.getCMonitors(monitor_id);
	};
	
	
	public List<CMonitor> getCMonitorsByDiagnosisId(String diagnosis_id){
		return mapper.getCMonitorsByDiagnosisId(diagnosis_id);
	};
//******************************* e_monitor_curve ******************************************************************************
    public List<EMonitorCurve> getEMonitorCurveById(String monitor_id, String data_day) {
		return emonitorCurveMapper.getEMonitorCurveById(monitor_id,data_day);
    }

	public List<EMonitorCurve> getEMonitorCurveOf4HById(String monitor_id, String data_day) {
		List<EMonitorCurve> list = emonitorCurveMapper.getEMonitorCurveOf4HById(monitor_id,data_day);
		for (EMonitorCurve e: list) {//去除指定日期上一日的数据

		}
		return list;
	}

	public List<EMonitorCurve> getEMonitorCurveOf2HById(String monitor_id, String data_day) {
		List<EMonitorCurve> list = emonitorCurveMapper.getEMonitorCurveOf2HById(monitor_id,data_day);
		for (EMonitorCurve e: list) {//去除指定日期上一日的数据

		}
		return list;
	}

	public List<EMonitorCurve> getTotalEMonitorCurveOf4H(String monitor_id, String data_day) {
		List<EMonitorCurve> list = emonitorCurveMapper.getTotalEMonitorCurveOf4H(monitor_id,data_day);
		for (EMonitorCurve e: list) {//去除指定日期上一日的数据

		}
		return list;
	}

	public List<EMonitorCurve> getTotalEMonitorCurveOf2H(String monitor_id, String data_day) {
		List<EMonitorCurve> list = emonitorCurveMapper.getTotalEMonitorCurveOf2H(monitor_id,data_day);
		for (EMonitorCurve e: list) {//去除指定日期上一日的数据

		}
		return list;
	}


//************************************** e_monitor_day_read ***********************************************************************

	public List<EMonitorDayRead> getEMonitorDayReadOfDayById(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getEMonitorDayReadOfDayById(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getEMonitorDayReadOfWeekById(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getEMonitorDayReadOfWeekById(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getEMonitorDayReadOfMonthById(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getEMonitorDayReadOfMonthById(monitor_id,data_day);
		return list;
	}



	public List<EMonitorDayRead> getTotalEMonitorDayReadOfDay(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getTotalEMonitorDayReadOfDay(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getTotalEMonitorDayReadOfWeek(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getTotalEMonitorDayReadOfWeek(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getTotalEMonitorDayReadOfMonth(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getTotalEMonitorDayReadOfMonth(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getAllTotalEMonitorDayReadOfDay(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getAllTotalEMonitorDayReadOfDay(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getAllTotalEMonitorDayReadOfWeek(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getAllTotalEMonitorDayReadOfWeek(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getAllTotalEMonitorDayReadOfMonth(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getAllTotalEMonitorDayReadOfMonth(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getEMonitorDayReadOfDay2(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getEMonitorDayReadOfDay2(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getEMonitorDayReadOfWeek2(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getEMonitorDayReadOfWeek2(monitor_id,data_day);
		return list;
	}

	public List<EMonitorDayRead> getEMonitorDayReadOfMonth2(String monitor_id, String data_day) {
		List<EMonitorDayRead> list = emonitorCurveMapper.getEMonitorDayReadOfMonth2(monitor_id,data_day);
		return list;
	}
//************************************** c_equip ****************************************************************************
	public CEquip getCEquipByAddress(String equip_address) {
		CEquip list = cequipMapper.getCEquipByAddress(equip_address);
		return list;
	}
	public CEquip getCEquipByMonitorId(String monitor_id) {
		CEquip list = cequipMapper.getCEquipByMonitorId(monitor_id);
		return list;
	}
//******************************************** env_monitor_curve **********************************************************************
	public List<EnvMonitorCurve> getEnvMonitorCurveById(String monitor_id, String data_day) {
		List<EnvMonitorCurve> list = emonitorCurveMapper.getEnvMonitorCurveById(monitor_id,data_day);
		return list;
	}

	public List<MonitorCurve> getMonitorCurveById(String DEV_ADDRESS, String data_day) {
		List<MonitorCurve> list = emonitorCurveMapper.getMonitorCurveById(DEV_ADDRESS,data_day);
		return list;
	}
	
	public List<EMonitorCurve> getPaprBy2H(String DEV_ADDRESS, String data_day) {
		List<EMonitorCurve> list = emonitorCurveMapper.getPaprBy2H(DEV_ADDRESS,data_day);
		return list;
	}
	
	public List<EMonitorCurve> getPaprBy2HDay( String DEV_ADDRESS, String start_data_day, String end_data_day, String level){
		List<EMonitorCurve> list = emonitorCurveMapper.getPaprBy2HDay(DEV_ADDRESS, start_data_day, end_data_day, level);
		return list;
	}
	
	public List<EMonitorCurve> getPaprByDay(String DEV_ADDRESS,String start_data_day,String end_data_day){
		List<EMonitorCurve> list = emonitorCurveMapper.getPaprByDay(DEV_ADDRESS, start_data_day, end_data_day);
		return list;
	}
	
	public List<EMonitorCurve> getAllPaprBy2H(String start_data_day,String end_data_day,String level){
		List<EMonitorCurve> list = emonitorCurveMapper.getAllPaprBy2H(start_data_day, end_data_day, level);
		return list;
	}
	
	public List<EMonitorCurve> getAllPapr(String start_data_day,String end_data_day){
		List<EMonitorCurve> list = emonitorCurveMapper.getAllPapr(start_data_day, end_data_day);
		return list;
	}
}
