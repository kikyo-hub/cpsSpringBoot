package cn.nrzt.cps.energydata.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.nrzt.cps.energydata.po.MonitorCurveEnergy;
import cn.nrzt.cps.energydata.po.MonitorCurveEnvironment;
import cn.nrzt.cps.energydata.po.MonitorDayEnergy;

@Mapper
public interface MonitorPointDetailedMapper {

//	@Select("SELECT c.monitor_id,c.monitor_name,c.device_type,e.* FROM c_monitor c,(SELECT t.*" + 
//			"  FROM (SELECT t.dev_address," + 
//			"               t.data_time," + 
//			"               t.ua," + 
//			"               t.ub," + 
//			"               t.uc," + 
//			"               t.ia," + 
//			"               t.ib," + 
//			"               t.ic," + 
//			"               t.pa," + 
//			"               t.pb," + 
//			"               t.pc," + 
//			"               t.pap_r" + 
//			"          FROM e_monitor_curve_energy t) t," + 
//			"       (SELECT a.dev_address, MAX(a.data_time) as DATA_TIME" + 
//			"          FROM e_monitor_curve_energy a" + 
//			"         group by a.dev_address) a" + 
//			" where t.dev_address(+) = a.dev_address" + 
//			"   and t.data_time(+) = a.DATA_TIME) e where c.monitor_id=e.dev_address(+) and c.device_type in ('1','3') order by c.device_type desc,e.DATA_TIME desc")
	@Select("SELECT c.monitor_id, c.monitor_name, c.device_type, e.*" + 
			"  FROM c_monitor c," + 
			"       (SELECT t.*" + 
			"          FROM (SELECT t.dev_address," + 
			"                       t.data_time," + 
			"                       t.ua," + 
			"                       t.ub," + 
			"                       t.uc," + 
			"                       t.ia as ia," + 
			"                       t.ib as ib," + 
			"                       t.ic as ic," + 
			"                       t.pa as pa," + 
			"                       t.pb as pb," + 
			"                       t.pc as pc," + 
			"                       t.pap_r as pap_r" + 
			"                  FROM e_monitor_curve_energy t, c_equip e" + 
			"                 where t.dev_address = e.equip_id(+)) t," + 
			"               (SELECT a.dev_address, MAX(a.data_time) as DATA_TIME" + 
			"                  FROM e_monitor_curve_energy a" + 
			"                 group by a.dev_address) a" + 
			"         where t.dev_address(+) = a.dev_address" + 
			"           and t.data_time(+) = a.DATA_TIME) e" + 
			" where c.monitor_id = e.dev_address(+)" + 
			"   and c.device_type in ('1', '3')" + 
			" order by c.device_type desc, e.DATA_TIME desc")
	@Results(id = "MonitorCurveEnergyMap", value = { @Result(column = "MONITOR_ID", property = "monitorId", javaType = String.class),
			@Result(column = "MONITOR_NAME", property = "monitorName", javaType = String.class),
			@Result(column = "DATA_TIME", property = "dataTime", javaType = String.class),
			@Result(column = "UA", property = "ua", javaType = Double.class),
			@Result(column = "UB", property = "ub", javaType = Double.class),
			@Result(column = "UC", property = "uc", javaType = Double.class),
			@Result(column = "IA", property = "ia", javaType = Double.class),
			@Result(column = "IB", property = "ib", javaType = Double.class),
			@Result(column = "IC", property = "ic", javaType = Double.class),
			@Result(column = "PA", property = "pa", javaType = Double.class),
			@Result(column = "PB", property = "pb", javaType = Double.class),
			@Result(column = "PC", property = "pc", javaType = Double.class),
			@Result(column = "PAP_R", property = "papR", javaType = Double.class)
	})
	List<MonitorCurveEnergy> getMonitorCurveEnergy();
	
	@Select("SELECT c.monitor_id,c.monitor_name,e.* FROM c_monitor c,(SELECT t.*" + 
			"  FROM (SELECT t.dev_address," + 
			"               t.data_time," + 
			"               t.TEMPERATURE," + 
			"               t.HUMIDITY," + 
			"               t.CO2," + 
			"               t.ILLUMINANCE" + 
			"          FROM e_monitor_curve_environment t) t," + 
			"       (SELECT a.dev_address, MAX(a.data_time) as DATA_TIME" + 
			"          FROM e_monitor_curve_environment a" + 
			"         group by a.dev_address) a" + 
			" where t.dev_address(+) = a.dev_address" + 
			"   and t.data_time(+) = a.DATA_TIME) e where c.monitor_id=e.dev_address(+) and c.device_type in ('5')")
	@Results(id = "MonitorCurveEnvironmentMap", value = { @Result(column = "MONITOR_ID", property = "monitorId", javaType = String.class),
			@Result(column = "MONITOR_NAME", property = "monitorName", javaType = String.class),
			@Result(column = "DATA_TIME", property = "dataTime", javaType = String.class),
			@Result(column = "TEMPERATURE", property = "temperature", javaType = String.class),
			@Result(column = "HUMIDITY", property = "humidity", javaType = String.class),
			@Result(column = "CO2", property = "co2", javaType = String.class),
			@Result(column = "ILLUMINANCE", property = "illuminance", javaType = String.class)
	})
	List<MonitorCurveEnvironment> getMonitorCurveEnvironment();

	@Select("SELECT * FROM c_monitor where 1=1 and DEVICE_TYPE != 5 order by MONITOR_NAME")
	@Results({ 
		@Result(column = "MONITOR_ID", property = "key", javaType = String.class),
		@Result(column = "MONITOR_NAME", property = "title", javaType = String.class)
	})
	List<Map<String, Object>> getMonitorTree();
	

//	@Select("SELECT a.*,to_char(a.data_time-1, 'yyyy-mm-dd') as last_data_time,to_char(a.data_time, 'yyyy-mm-dd') as this_data_time FROM e_monitor_day_energy a where a.DEV_ADDRESS = #{devAddress,jdbcType = VARCHAR} order by DATA_TIME desc")
//	@Select("SELECT (case" + 
//			"         when m.device_type = '1' then" + 
//			"          a.pap_r * 5" + 
//			"         when m.device_type = '3' then" + 
//			"          a.pap_r * 80" + 
//			"         else" + 
//			"          a.pap_r" + 
//			"       end) as T_pap_r," + 
//			"       a.*," + 
//			"       to_char(a.data_time - 1, 'yyyy-mm-dd') as last_data_time," + 
//			"       to_char(a.data_time, 'yyyy-mm-dd') as this_data_time" + 
//			"  FROM e_monitor_day_energy a, c_monitor m" + 
//			" where 1 = 1" + 
//			"   and a.dev_address = m.monitor_id(+)" + 
//			"   and a.DEV_ADDRESS = #{devAddress, jdbcType = VARCHAR}" + 
//			" order by DATA_TIME desc")
	@Select("SELECT a.pap_r * e.ct * e.pt as T_pap_r," + 
			"       a.*," + 
			"       to_char(a.data_time - 1, 'yyyy-mm-dd') as last_data_time," + 
			"       to_char(a.data_time, 'yyyy-mm-dd') as this_data_time" + 
			"  FROM e_monitor_day_energy a, c_monitor m,c_equip e" + 
			" where 1 = 1" + 
			"   and a.dev_address = m.monitor_id(+)" + 
			"   and a.dev_address = e.equip_id(+)" + 
			"   and a.DEV_ADDRESS = #{devAddress, jdbcType = VARCHAR}" + 
			" order by DATA_TIME desc")
	@Results({
		@Result(column = "DEV_ADDRESS", property = "devAddress", javaType = String.class),
		@Result(column = "DATA_TIME", property = "dataTime", javaType = String.class),
		@Result(column = "COLLECT_TIME", property = "collectTime", javaType = String.class),
		@Result(column = "PAP_R", property = "papR", javaType = BigDecimal.class),
		@Result(column = "PAP_R1", property = "papR1", javaType = BigDecimal.class),
		@Result(column = "PAP_R2", property = "papR2", javaType = BigDecimal.class),
		@Result(column = "PAP_R3", property = "papR3", javaType = BigDecimal.class),
		@Result(column = "PAP_R4", property = "papR4", javaType = BigDecimal.class),
		@Result(column = "RAP_R", property = "rapR", javaType = BigDecimal.class),
		@Result(column = "RAP_R1", property = "rapR1", javaType = BigDecimal.class),
		@Result(column = "RAP_R2", property = "rapR2", javaType = BigDecimal.class),
		@Result(column = "RAP_R3", property = "rapR3", javaType = BigDecimal.class),
		@Result(column = "RAP_R4", property = "rapR4", javaType = BigDecimal.class),
		@Result(column = "PAP_E", property = "papE", javaType = BigDecimal.class),
		@Result(column = "PAP_E1", property = "papE1", javaType = BigDecimal.class),
		@Result(column = "PAP_E2", property = "papE2", javaType = BigDecimal.class),
		@Result(column = "PAP_E3", property = "papE3", javaType = BigDecimal.class),
		@Result(column = "PAP_E4", property = "papE4", javaType = BigDecimal.class),
		@Result(column = "RAP_E", property = "rapE", javaType = BigDecimal.class),
		@Result(column = "RAP_E1", property = "rapE1", javaType = BigDecimal.class),
		@Result(column = "RAP_E2", property = "rapE2", javaType = BigDecimal.class),
		@Result(column = "RAP_E3", property = "rapE3", javaType = BigDecimal.class),
		@Result(column = "RAP_E4", property = "rapE4", javaType = BigDecimal.class),
		@Result(column = "LAST_DATA_TIME", property = "lastDataTime", javaType = String.class),
		@Result(column = "THIS_DATA_TIME", property = "thisDataTime", javaType = String.class),
		@Result(column = "T_PAP_R", property = "tPapR", javaType = BigDecimal.class)
	})
	List<MonitorDayEnergy> getMonitorDayEnergy(String devAddress);

}
