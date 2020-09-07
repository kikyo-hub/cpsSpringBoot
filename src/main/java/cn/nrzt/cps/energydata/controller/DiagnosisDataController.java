package cn.nrzt.cps.energydata.controller;

import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.EDiagnosisCurve;
import cn.nrzt.cps.archives.po.EDiagnosisDayRead;
import cn.nrzt.cps.energydata.service.DiagnosisDataService;
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

@Api(tags={"诊断分析点数据"})
@RestController
@RequestMapping("/diagnosis/data")
public class DiagnosisDataController extends WebController{
	private Logger logger = LogManager.getLogger(DiagnosisDataController.class);	
	@Autowired
	DiagnosisDataService diagnosisDataService;
	
//	@ApiOperation(value = "按页获取监控点数据")
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public WebResponse getDiagnosisDataByPage(@RequestParam int pageNo) {
//		logger.info("getByPage,pageNo ={}",pageNo);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按监测点标识获取所有数据")
//	@RequestMapping(value = "/page", method = RequestMethod.GET)
//	public WebResponse getDiagnosisDataByMonitor(@RequestParam String monitorid) {
//		logger.info("getByPage,monitorid ={}",monitorid);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按监测点标识获取某一日的所有数据")
//	@RequestMapping(value = "/datepage", method = RequestMethod.GET)
//	public WebResponse getSingleDiagnosisDataByDate(@RequestParam String monitorid,@RequestParam Date date) {
//		logger.info("getByPage,monitorid ={},date ={}",monitorid,date);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按时间获取监控点数据")
//	@RequestMapping(value = "/date", method = RequestMethod.GET)
//	public WebResponse getDiagnosisDataByDate(@RequestParam Date date) {
//		logger.info("getByDate,date ={}",date);
//
//		return new WebResponse();
//	}
//
//	@ApiOperation(value = "按时间区间获取监控点数据")
//	@RequestMapping(value = "/dateintervalpage", method = RequestMethod.GET)
//	public WebResponse getDiagnosisDataByTimeInterval(@RequestParam Date startdate,@RequestParam Date enddate) {
//		logger.info("getByDate,startdate ={},enddate ={}",startdate,enddate);
//
//		return new WebResponse();
//	}
//**************************************************************************************************
	@ApiOperation(value = "查询所有诊断点",notes = "diagnosis_id：若填选，查询单个诊断点；" +
			"否则，查询所有诊断点")
	@GetMapping("/getCDiagnosises")
	public List<CDiagnosis> getCDiagnosises(
			@RequestParam(value="diagnosis_id",required = false,defaultValue = "-1")String diagnosis_id){
		diagnosis_id = StringUtils.isEmpty(diagnosis_id)?"-1":diagnosis_id;
		return diagnosisDataService.getCDiagnosises(diagnosis_id);
	};
	@ApiOperation(value = "根据diagnosis_id查询日数据",notes = "diagnosis_id：若填选，单个设备日曲线 ，就是一天96条数据；" +
			"否则，就是查询所有设备日曲线, 就是每个设备96条数据")
	@GetMapping("/getEDiagnosisCurveById")
	public  List<EDiagnosisCurve> getEDiagnosisCurveById(
			@RequestParam(value="diagnosis_id") String diagnosis_id,  @RequestParam(value="data_day") String data_day){
		diagnosis_id = StringUtils.isEmpty(diagnosis_id)?"-1":diagnosis_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		return diagnosisDataService.getEDiagnosisCurveById(diagnosis_id,data_day);
	};

	@ApiOperation(value = "查询指定日期周数据",notes = "diagnosis_id：若填选，单个设备的周数据 ，就是 7 条数据；" +
			"否则，就是查询所有设备的周数据, 就是每个设备 7 条数据")
	@GetMapping("/getEDiagnosisDayReadOfWeekById")
	public  List<EDiagnosisDayRead> getEDiagnosisDayReadOfWeekById(
			@RequestParam(value="diagnosis_id") String diagnosis_id,  @RequestParam(value="data_day") String data_day){
		diagnosis_id = StringUtils.isEmpty(diagnosis_id)?"-1":diagnosis_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		return diagnosisDataService.getEDiagnosisDayReadOfWeekById(diagnosis_id,data_day);
	};

	@ApiOperation(value = "查询指定日期周数据",notes = "diagnosis_id：若填选，单个设备的周总量 ，就是 1 条数据；" +
			"否则，就是查询所有设备的周总量, 就是每个设备 1 条数据")
	@GetMapping("/getTotalEDiagnosisDayReadOfWeek")
	public  List<EDiagnosisDayRead> getTotalEDiagnosisDayReadOfWeek(
			@RequestParam(value="diagnosis_id") String diagnosis_id,  @RequestParam(value="data_day") String data_day){
		diagnosis_id = StringUtils.isEmpty(diagnosis_id)?"-1":diagnosis_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		return diagnosisDataService.getTotalEDiagnosisDayReadOfWeek(diagnosis_id,data_day);
	};

	@ApiOperation(value = "所有设别的日总量",notes = "diagnosis_id:保留字段")
	@GetMapping("/getAllTotalEDiagnosisDayReadOfWeek")
	public  List<EDiagnosisDayRead> getAllTotalEDiagnosisDayReadOfWeek(
			@RequestParam(value="diagnosis_id") String diagnosis_id,  @RequestParam(value="data_day") String data_day){
		diagnosis_id = StringUtils.isEmpty(diagnosis_id)?"-1":diagnosis_id;
		data_day = StringUtils.isEmpty(data_day)? LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")):data_day;
		return diagnosisDataService.getAllTotalEDiagnosisDayReadOfWeek(diagnosis_id,data_day);
	};
}
