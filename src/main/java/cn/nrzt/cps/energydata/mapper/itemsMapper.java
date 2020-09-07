package cn.nrzt.cps.energydata.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import cn.nrzt.cps.energydata.po.Items;

@Mapper
@Repository
public interface itemsMapper {
	@Select(" SELECT (PAP_R * q.ct * q.pt) as PAP_R,\r\n" + 
			"       (e.ua * q.pt) as ua,\r\n" + 
			"       (e.ub * q.pt) as ub,\r\n" + 
			"       (e.uc * q.pt) as uc,\r\n" + 
			"       e.data_time,\r\n" + 
			"       e.dev_address,\r\n" + 
			"       q.equip_name\r\n" + 
			"  FROM (SELECT PAP_R - LAG(PAP_R, 1, 0) OVER(ORDER BY ROWNUM) PAP_R,\r\n" + 
			"               ROWNUM RN,\r\n" + 
			"               e.data_time,\r\n" + 
			"               e.dev_address,\r\n" + 
			"               e.ua,\r\n" + 
			"               e.ub,\r\n" + 
			"               e.uc\r\n" + 
			"          FROM (select e.dev_address, e.pap_r, e.data_time, e.ua, e.ub, e.uc\r\n" + 
			"                  from e_monitor_curve_energy e\r\n" + 
			"                 where e.dev_address = #{adress} and\r\n" + 
			"                 trunc(e.data_time) =\r\n" + 
			"                       to_date(#{todaydata}, 'yyyy-mm-dd')\r\n" + 
			"                 order by data_time) e) e\r\n" + 
			"  left join c_equip q\r\n" + 
			"    on (q.equip_id = e.dev_address)\r\n" + 
			" WHERE RN > 1" + 
			"")
	@Results(id = "getmeterTodaydataMap", value = {
			@Result(column ="equip_name",property = "equipName"),
			@Result(column ="data_time",property = "dataTime"),
			@Result(column ="ua",property = "ua",javaType = BigDecimal.class),
			@Result(column ="ub",property = "ub",javaType = BigDecimal.class),
			@Result(column ="uc",property = "uc",javaType = BigDecimal.class),
			@Result(column ="PAP_R",property = "pap_r",javaType = BigDecimal.class),
	})
   List<Items>getmeterTodaydata(String todaydata, String adress);
   
	@Select("select m.monitor_id, m.monitor_name\r\n" + 
			"  from c_monitor m\r\n" + 
			"  left join p_code p\r\n" + 
			"    on (p.value = m.device_type)\r\n" + 
			" where 1 = 1\r\n" + 
			"   and p.code_type = 'eDeviceType'\r\n" + 
			"   and p.name = #{type}\r\n" + 
			" order by MONITOR_NAME")
	@Results({
		@Result(column ="monitor_name",property = "label"),
		@Result(column ="monitor_id",property = "value"),
	})
	List<Map<String, Object>>getmeter(String type);
	
	@Select("SELECT (PAP_R * q.ct * q.pt) as PAP_R,\r\n" + 
			"       (e.ua * q.pt) as ua,\r\n" + 
			"       (e.ub * q.pt) as ub,\r\n" + 
			"       (e.uc * q.pt) as uc,\r\n" + 
			"       e.data_time,\r\n" + 
			"       e.dev_address,\r\n" + 
			"       q.equip_name\r\n" + 
			"  FROM (SELECT PAP_R - LAG(PAP_R, 1, 0) OVER(ORDER BY ROWNUM) PAP_R,\r\n" + 
			"               ROWNUM RN,\r\n" + 
			"               e.data_time,\r\n" + 
			"               e.dev_address,\r\n" + 
			"               e.ua,\r\n" + 
			"               e.ub,\r\n" + 
			"               e.uc\r\n" + 
			"          FROM (select e.dev_address,e.pap_r, e.data_time, e.ua, e.ub, e.uc\r\n" + 
			"                  from e_monitor_curve_energy e\r\n" + 
			"                 where e.dev_address = #{adress} and\r\n" + 
			"                 trunc(e.data_time) =\r\n" + 
			"                       to_date(#{time1}, 'yyyy-mm-dd')\r\n" + 
			"                 order by data_time) e) e\r\n" + 
			"  left join c_equip q\r\n" + 
			"    on (q.equip_id = e.dev_address)\r\n" + 
			" WHERE RN > 1 order by PAP_R DESC")
	@Results(id = "getmeterTodayrankMap",value={
			@Result(column ="equip_name",property = "equipName"),
			@Result(column ="dev_address",property = "equipId"),
			@Result(column ="data_time",property = "dataTime"),
			@Result(column ="PAP_R",property = "pap_r",javaType = BigDecimal.class),
		})
	List<Items>getmeterTodayrank(String time1, String adress);
	
	@Select(value = " SELECT (PAP_R * q.ct * q.pt) as PAP_R,\r\n" + 
			"       e.data_time,\r\n" + 
			"       e.dev_address,\r\n" + 
			"       q.equip_name\r\n" + 
			"  FROM (SELECT PAP_R - LAG(PAP_R, 1, 0) OVER(ORDER BY ROWNUM) PAP_R,\r\n" + 
			"               ROWNUM RN,\r\n" + 
			"               e.data_time,\r\n" + 
			"               e.dev_address\r\n" + 
			"          FROM (select e.dev_address,e.pap_r, e.data_time\r\n" + 
			"                  from e_monitor_day_energy e\r\n" + 
			"                 where e.dev_address = #{adress} and\r\n" + 
			"                 trunc(e.data_time) between to_date(#{monday}, 'yyyy-mm-dd') and\r\n" + 
			"                       to_date(#{sunday}, 'yyyy-mm-dd')\r\n" + 
			"                 order by data_time) e)e\r\n" + 
			"                   left join c_equip q\r\n" + 
			"    on (q.equip_id = e.dev_address)\r\n" + 
			" WHERE RN > 1 ")
	@Results(id = "getmeterWeekdataMap", value = {
			@Result(column ="data_time",property = "dataTime"),
			@Result(column ="PAP_R",property = "pap_r",javaType = BigDecimal.class),	
	})
	List<Items>getmeterWeekdata(String monday, String sunday, String adress);
	
	@Select(value = " SELECT (PAP_R * q.ct * q.pt) as PAP_R,\r\n" + 
			"       e.data_time,\r\n" + 
			"       e.dev_address,\r\n" + 
			"       q.equip_name\r\n" + 
			"  FROM (SELECT PAP_R - LAG(PAP_R, 1, 0) OVER(ORDER BY ROWNUM) PAP_R,\r\n" + 
			"               ROWNUM RN,\r\n" + 
			"               e.data_time,\r\n" + 
			"               e.dev_address\r\n" + 
			"          FROM (select e.dev_address,e.pap_r, e.data_time\r\n" + 
			"                  from e_monitor_day_energy e\r\n" + 
			"                 where e.dev_address = #{adress} and\r\n" + 
			"                 trunc(e.data_time) between to_date(#{monday}, 'yyyy-mm-dd') and\r\n" + 
			"                       to_date(#{sunday}, 'yyyy-mm-dd')\r\n" + 
			"                 order by data_time) e)e\r\n" + 
			"                   left join c_equip q\r\n" + 
			"    on (q.equip_id = e.dev_address)\r\n" + 
			" WHERE RN > 1 order by PAP_R ")
	@Results(id = "getmaterweekRankMap", value = {
			@Result(column ="data_time",property = "dataTime"),
			@Result(column ="PAP_R",property = "pap_r",javaType = BigDecimal.class),	
	})
	List<Items>getmaterweekRank(String monday, String sunday, String adress);
}
