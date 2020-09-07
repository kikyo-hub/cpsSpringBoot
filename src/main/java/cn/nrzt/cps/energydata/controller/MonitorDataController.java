package cn.nrzt.cps.energydata.controller;

import cn.nrzt.cps.archives.po.*;
import cn.nrzt.cps.energydata.po.MonitorCurve;
import cn.nrzt.cps.energydata.service.MonitorDataService;
import cn.nrzt.cps.util.StringUtils;
import cn.nrzt.cps.web.WebController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Api(tags={"监控点数据"})
@RestController
@RequestMapping("/monitor/data")
public class MonitorDataController extends WebController{
	private Logger logger = LogManager.getLogger(MonitorDataController.class);	
	@Autowired
	MonitorDataService monitorDataService;
	
//	@ApiOperation(value = "按页获取监控点数据")
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public WebResponse getMonitorDataByPage(@RequestParam int pageNo) {
//		logger.info("getByPage,pageNo ={}",pageNo);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按监测点标识获取所有数据")
//	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
//	public WebResponse getMonitorDataByMonitor(@RequestParam("id") String monitorid) {
//		logger.info("getByPage,monitorid ={}",monitorid);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按监测点标识获取某一日的所有数据")
//	@RequestMapping(value = "/id/{id}/date/{date}", method = RequestMethod.GET)
//	public WebResponse getSingleMonitorDataByDate(@RequestParam String monitorid,@RequestParam Date date) {
//		logger.info("getByPage,monitorid ={},date ={}",monitorid,date);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按时间获取监控点数据")
//	@RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
//	public WebResponse getMonitorDataByDate(@RequestParam Date date) {
//		logger.info("getByDate,date ={}",date);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按时间区间获取监控点数据")
//	@RequestMapping(value = "/date/{startdate},{enddate}", method = RequestMethod.GET)
//	public WebResponse getMonitorDataByTimeInterval(@RequestParam Date startdate,@RequestParam Date enddate) {
//		logger.info("getByDate,startdate ={},enddate ={}",startdate,enddate);
//
//		return new WebResponse();
//	}
//************************************ c_monitor *************************************************************************
	@ApiOperation(value = "查询监测点"
			,notes = "monitor_id :若选填，查询单个监测点;否则，所有监测点;")
	@GetMapping("/getMonitors")
	public List<CMonitor> getCMonitors(
	        @RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id){
        monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		return monitorDataService.getCMonitors(monitor_id);
	};
	
	@ApiOperation(value = "查询用电监测点"
			,notes = "monitor_id :若选填，查询单个监测点;否则，所有监测点;")
	@GetMapping("/getCMonitorsOfElectric")
	public List<CMonitor> getCMonitorsOfElectric(
	        @RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id){
        monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		return monitorDataService.getCMonitorsOfElectric(monitor_id);
	};
	
	
	@ApiOperation(value = "查询diagnosis_id监测点"
			,notes = "diagnosis_id :若选填，查询诊断点下的监测点;否则，所有监测点;")
	@GetMapping("/getCMonitorsByDiagnosisId")
	public List<CMonitor> getCMonitorsByDiagnosisId(
			@RequestParam(value="diagnosis_id",required = false,defaultValue = "-1")String diagnosis_id){
		diagnosis_id = StringUtils.isEmpty(diagnosis_id)?"-1":diagnosis_id;
		return monitorDataService.getCMonitorsByDiagnosisId(diagnosis_id);
	}
//****************************** e_monitor_curve ********************************************************
	@ApiOperation(value = "根据monitor_id查询日曲线"
			,notes = "monitor_id: 若选填，单个设备日曲线 ，就是一天96条数据;" +
			"否则，就是查询所有设备日曲线, 就是每个设备96条数据;")
	@GetMapping("/getEMonitorCurveById")
	public  List<EMonitorCurve> getEMonitorCurveById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1") String monitor_id,  @RequestParam(value="data_day") String data_day){
        monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		logger.info("getByPage,monitor_id ={}",monitor_id+":"+data_day);	
		return monitorDataService.getEMonitorCurveById(monitor_id,data_day);
	};

	@ApiOperation(value = "根据monitor_id查询4h的统计量/day"
			,notes = "monitor_id  : 若选填，单个设备的4h统计量/day ，就是一天6 条数据;" +
			"否则， 就是查询所有设备的4h统计量/day, 就是每个设备6条数据;")
	@GetMapping("/getEMonitorCurveOf4HById")
	public  List<EMonitorCurve> getEMonitorCurveOf4HById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1") String monitor_id,  @RequestParam(value="data_day") String data_day){
        monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
	    data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		return monitorDataService.getEMonitorCurveOf4HById(monitor_id,data_day);
	};

	@ApiOperation(value = "查询所有监测点2h的统计量/day"
			,notes = "monitor_id : 若选填，单个设备的4h统计量/day ，就是一天12 条数据;" +
			"否则，就是查询所有设备的4h统计量/day, 就是每个设备12条数据;")
	@GetMapping("/getEMonitorCurveOf2HById")
	public List<EMonitorCurve> getEMonitorCurveOf2HById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
        monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
	    data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorCurve> list = monitorDataService.getEMonitorCurveOf2HById(monitor_id,data_day);
		return list;
	}
	@ApiOperation(value = "查询所有设备的4h统计量"
			,notes = "monitor_id 不必选字段，保留字段")
	@GetMapping("/getTotalEMonitorCurveOf4H")
	public List<EMonitorCurve> getTotalEMonitorCurveOf4H(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorCurve> list = monitorDataService.getTotalEMonitorCurveOf4H(monitor_id,data_day);
		return list;
	}
	@ApiOperation(value = "查询所有设备的2h统计量"
			,notes = "monitor_id 不必选字段，保留字段")
	@GetMapping("/getTotalEMonitorCurveOf2H")
	public List<EMonitorCurve> getTotalEMonitorCurveOf2H(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorCurve> list = monitorDataService.getTotalEMonitorCurveOf2H(monitor_id,data_day);
		return list;
	}
//***************************************** e_monitor_day_read *******************************************************************
	@ApiOperation(value = "查询指定日期数据",notes = "monitor_id : 若选填，单个设备的日数据 ，就是 1 条数据;" +
			"否则，就是查询所有设备的日数据, 就是每个设备 1 条数据;")
	@GetMapping("/getEMonitorDayReadOfDayById")
	public List<EMonitorDayRead> getEMonitorDayReadOfDayById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getEMonitorDayReadOfDayById(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期周数据",notes = "monitor_id : 若选填，单个设备的周数据 ，就是 7 条数据;" +
			"否则，就是查询所有设备的周数据, 就是每个设备 7 条数据;")
	@GetMapping("/getEMonitorDayReadOfWeekById")
	public List<EMonitorDayRead> getEMonitorDayReadOfWeekById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
        monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
	    data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getEMonitorDayReadOfWeekById(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期月数据",notes = "monitor_id : 若选填，单个设备的周数据 ，就是 30 条数据;" +
			"否则，就是查询所有设备的周数据, 就是每个设备 30 条数据;")
	@GetMapping("/getEMonitorDayReadOfMonthById")
	public List<EMonitorDayRead> getEMonitorDayReadOfMonthById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getEMonitorDayReadOfMonthById(monitor_id,data_day);
		return list;
	}


	@ApiOperation(value = "查询指定日期统计量",notes = "monitor_id : 若选填，单个设备的日总量 ，就是 1 条数据;" +
			"否则，就是查询所有设备的日总量, 就是每个设备 1 条数据;")
	@GetMapping("/getTotalEMonitorDayReadOfDay")
	public List<EMonitorDayRead> getTotalEMonitorDayReadOfDay(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getTotalEMonitorDayReadOfDay(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期周统计量",notes = "monitor_id : 若选填，单个设备的周总量 ，就是 1 条数据;" +
			"否则，就是查询所有设备的周总量, 就是每个设备 1 条数据;")
	@GetMapping("/getTotalEMonitorDayReadOfWeek")
	public List<EMonitorDayRead> getTotalEMonitorDayReadOfWeek(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getTotalEMonitorDayReadOfWeek(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期月统计量",notes = "monitor_id : 若选填，单个设备的月总量 ，就是 1 条数据;" +
			"否则，就是查询所有设备的月总量, 就是每个设备 1 条数据;")
	@GetMapping("/getTotalEMonitorDayReadOfMonth")
	public List<EMonitorDayRead> getTotalEMonitorDayReadOfMonth(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getTotalEMonitorDayReadOfMonth(monitor_id,data_day);
		return list;
	}
	@ApiOperation(value = " 所有设别的日总量",notes = "monitor_id 不必选字段，保留字段")
	@GetMapping("/getAllTotalEMonitorDayReadOfDay")
	public List<EMonitorDayRead> getAllTotalEMonitorDayReadOfDay(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getAllTotalEMonitorDayReadOfDay(monitor_id,data_day);
		return list;
	}
	@ApiOperation(value = " 所有设别的周总量",notes = "monitor_id 不必选字段，保留字段")
	@GetMapping("/getAllTotalEMonitorDayReadOfWeek")
	public List<EMonitorDayRead> getAllTotalEMonitorDayReadOfWeek(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getAllTotalEMonitorDayReadOfWeek(monitor_id,data_day);
		return list;
	}
	@ApiOperation(value = " 所有设别的月总量",notes = "monitor_id 不必选字段，保留字段")
	@GetMapping("/getAllTotalEMonitorDayReadOfMonth")
	public List<EMonitorDayRead> getAllTotalEMonitorDayReadOfMonth(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getAllTotalEMonitorDayReadOfMonth(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期数据2",notes = "monitor_id : 若选填，单个设备的日数据 ，就是 1 条数据;" +
			"否则，就是查询所有设备的日数据, 就是所有设备 1 条数据;")
	@GetMapping("/getEMonitorDayReadOfDay2")
	public List<EMonitorDayRead> getEMonitorDayReadOfDay2(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getEMonitorDayReadOfDay2(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期周数据2",notes = "monitor_id : 若选填，单个设备的周数据 ，就是 7 条数据;" +
			"否则，就是查询所有设备的周数据, 就是所有设备共 7 条数据;")
	@GetMapping("/getEMonitorDayReadOfWeek2")
	public List<EMonitorDayRead> getEMonitorDayReadOfWeek2(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getEMonitorDayReadOfWeek2(monitor_id,data_day);
		return list;
	}

	@ApiOperation(value = "查询指定日期月数据2",notes = "monitor_id : 若选填，单个设备的周数据 ，就是 30 条数据;" +
			"否则，就是查询所有设备的周数据, 就是所有设备共 30 条数据;")
	@GetMapping("/getEMonitorDayReadOfMonth2")
	public List<EMonitorDayRead> getEMonitorDayReadOfMonth2(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EMonitorDayRead> list = monitorDataService.getEMonitorDayReadOfMonth2(monitor_id,data_day);
		return list;
	}
//*********************************** c_equip ***********************************************************
	@ApiOperation(value = "查询监控点对应的设备",notes = " ")
	@GetMapping("/getCEquipByAddress")
	public CEquip getCEquipByAddress(
			@RequestParam(value="equip_address",required = false,defaultValue = "-1")String equip_address) {
		equip_address = StringUtils.isEmpty(equip_address)?"-1":equip_address;
		CEquip list = monitorDataService.getCEquipByAddress(equip_address);
		return list;
	}
	@ApiOperation(value = "查询监控点对应的设备",notes = " ")
	@GetMapping("/getCEquipByMonitorId")
	public CEquip getCEquipByMonitorId(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		CEquip list = monitorDataService.getCEquipByMonitorId(monitor_id);
		return list;

	}
//**************************************** env_monitor_curve ******************************************************
	@ApiOperation(value = "根据monitor_id查询环境日曲线"
			,notes = "monitor_id: 若选填，单个设备日曲线 ，就是一天96条数据;" +
			"否则，就是查询所有设备日曲线, 就是每个设备96条数据;")
	@GetMapping("/getEnvMonitorCurveById")
	public List<EnvMonitorCurve> getEnvMonitorCurveById(
			@RequestParam(value="monitor_id",required = false,defaultValue = "-1")String monitor_id, String data_day) {
		monitor_id = StringUtils.isEmpty(monitor_id)?"-1":monitor_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<EnvMonitorCurve> list = monitorDataService.getEnvMonitorCurveById(monitor_id,data_day);
		return list;
	}
	@ApiOperation(value = "根据DEV_ADDRESS查询环境日曲线(包含 电压、电流、电能、温湿度、二氧化碳)"
			,notes = "DEV_ADDRESS: 若选填，单个设备日曲线 ，就是一天96条数据;" +
			"否则，就是查询所有设备日曲线, 就是每个设备96条数据;")
	@GetMapping("/getMonitorCurveById")
	public List<MonitorCurve> getMonitorCurveById(String DEV_ADDRESS, String data_day) {
		DEV_ADDRESS = StringUtils.isEmpty(DEV_ADDRESS)?"-1":DEV_ADDRESS;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		List<MonitorCurve> list = monitorDataService.getMonitorCurveById(DEV_ADDRESS,data_day);
		return list;
	}
	
	@ApiOperation(value = "根据DEV_ADDRESS查询2h的papr数据")
	@GetMapping("/getPaprBy2H")
	public List<EMonitorCurve> getPaprBy2H(String DEV_ADDRESS, String data_day) {
		logger.info("getPaprBy2H");
		return monitorDataService.getPaprBy2H(DEV_ADDRESS, data_day);
	}
	
	@ApiOperation(value = "根据DEV_ADDRESS查询任意时间段2h的papr数据")
	@GetMapping("/getPaprBy2HDay")
	public List<EMonitorCurve> getPaprBy2HDay(String DEV_ADDRESS, String start_data_day, String end_data_day, String level){
		logger.info("getPaprBy2HDay");
		return monitorDataService.getPaprBy2HDay(DEV_ADDRESS, start_data_day, end_data_day, level);
	}
	
	@ApiOperation(value = "根据DEV_ADDRESS查询任意天数的papr数据")
	@GetMapping("/getPaprByDay")
	public List<EMonitorCurve> getPaprByDay(String DEV_ADDRESS,String start_data_day,String end_data_day){
		logger.info("getPaprByDay");
		return monitorDataService.getPaprByDay(DEV_ADDRESS, start_data_day, end_data_day);
	}
	
	@ApiOperation(value = "查询任意时间段内两小时的所有监测点的总用电量")
	@GetMapping("/getAllPaprBy2H")
	public List<EMonitorCurve> getAllPaprBy2H(String start_data_day,String end_data_day,String level){
		logger.info("getAllPaprBy2H");
		return monitorDataService.getAllPaprBy2H(start_data_day, end_data_day, level);
	}
	
	@ApiOperation(value = "查询任意时间段总用电量")
	@GetMapping("/getAllPapr")
	public List<EMonitorCurve> getAllPapr(String start_data_day,String end_data_day){
		logger.info("getAllPapr");
		return monitorDataService.getAllPapr(start_data_day, end_data_day);
	}
}
