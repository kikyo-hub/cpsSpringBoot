package cn.nrzt.cps.energydata.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import cn.nrzt.cps.energydata.po.Energyanalysis;
import cn.nrzt.cps.energydata.po.Energydio;

@Mapper
@Repository
public interface EnergyanalysisMapper {
	@Select(value = {" select a.PAP_R as yesterday,\r\n" + 
			"       b.PAP_R as today,\r\n" + 
			"       d.PAP_R as beforday,\r\n" + 
			"       c.monitor_id,\r\n" + 
			"       c.monitor_name,\r\n" + 
			"       nvl(b.PAP_R - a.PAP_R, 0) *p.ct*p.pt as day,\r\n" + 
			"       nvl(a.PAP_R - d.PAP_R, 0) *p.ct*p.pt as yesday,\r\n" + 
			"       p.ct, p.pt\r\n" + 
			"  from c_monitor c\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and trunc(e.data_time) = to_date(#{Yesterdaydata}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) a\r\n" + 
			"    on (c.monitor_id = a.DEV_ADDRESS)\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and trunc(e.data_time) = to_date(#{todaydata}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) b\r\n" + 
			"    on (c.monitor_id = b.DEV_ADDRESS)\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and trunc(e.data_time) = to_date(#{Beforedaydata}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) d\r\n" + 
			"    on (c.monitor_id = d.DEV_ADDRESS)\r\n" + 
			"  left join (c_equip) p on (p.equip_id = c.monitor_id)\r\n" + 
			" where 1 = 1\r\n" + 
			"   and c.device_type != 5order by MONITOR_NAME"})
	//TO_CHAR(t.data_time,'yyyy-mm-dd hh24:mi:ss') AS DATA_TIME
	@Results(id = "getTodaydataMap", value = {
			@Result(column ="dev_address",property = "monitorAddress"),
			@Result(column ="monitor_name",property = "monitorName"),
			@Result(column ="yesday",property = "yesterday",javaType = BigDecimal.class),
			@Result(column ="day",property = "monitorday",javaType = BigDecimal.class),
	})
	List<Energyanalysis>getTodaydata(String todaydata, String Yesterdaydata, String Beforedaydata);
	
	@Select(value = {"select a.PAP_R as yesterday,\r\n" + 
			"       b.PAP_R as today,\r\n" + 
			"       c.monitor_id,\r\n" + 
			"       c.monitor_name,\r\n" + 
			"       nvl(b.PAP_R - a.PAP_R, 0) *p.ct*p.pt as day,\r\n" + 
			"        p.ct, p.pt\r\n" + 
			"  from c_monitor c\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and trunc(e.data_time) = to_date(#{Yesterday}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) a\r\n" + 
			"    on (c.monitor_id = a.DEV_ADDRESS)\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and trunc(e.data_time) = to_date(#{today}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) b\r\n" + 
			"    on (c.monitor_id = b.DEV_ADDRESS)\r\n" + 
			"    left join (c_equip) p on (p.equip_id = c.monitor_id)\r\n" + 
			" where 1 = 1\r\n" + 
			"   and c.device_type != 5\r\n" + 
			" order by day DESC"})
	@Results(id = "getmonitorRankMap", value = {
			@Result(column ="dev_address",property = "monitorAddress"),
			@Result(column ="monitor_name",property = "monitorName"),
			@Result(column ="yesterday",property = "yesterday",javaType = BigDecimal.class),
			@Result(column ="today",property = "today",javaType = BigDecimal.class),
			@Result(column ="day",property = "monitorday",javaType = BigDecimal.class),
	})
	List<Energyanalysis>getmonitorRank(String today, String Yesterday);
	
	@Select(value = {"  select  a.past8 as weekdate,b.SPAP1 from (select (trunc((trunc(next_day(sysdate - 7, 1))) + Rownum - 8, 'dd')) past8\r\n" + 
			"          from dual\r\n" + 
			"        connect by rownum <= 8) a left join ( SELECT e.data_time, sum(e.pap_r *p.ct*p.pt) as spAp1\r\n" + 
			"               FROM c_monitor m left join( e_monitor_day_energy )e\r\n" + 
			"               on ( m.monitor_id = e.dev_address)\r\n" + 
			"               left join (c_equip) p on(p.equip_id = m.monitor_id)\r\n" + 
			"              where 1 = 1\r\n" + 
			"                and trunc(e.data_time) between to_date(#{begindate}, 'yyyy-mm-dd') and\r\n" + 
			"                    to_date(#{enddate}, 'yyyy-mm-dd')\r\n" + 
			"                and m.device_type != 5\r\n" + 
			"              group by data_time  order by data_time ) b on (b.data_time = a.PAST8) order by PAST8"})
	@Results(id = "MonitorweekDataMap", value= {
			@Result(column ="weekdate",property = "dataTime"),
			@Result(column ="spAp1",property = "monitorday",javaType = BigDecimal.class),
	})
	List<Energyanalysis>getmonitorweekData(String begindate, String enddate);
	
	@Select(value = {" select a.PAP_R as monday,\r\n" + 
			"       b.PAP_R as sunday,\r\n" + 
			"       c.monitor_id,\r\n" + 
			"       c.monitor_name,\r\n" + 
			"       nvl(b.PAP_R - a.PAP_R, 0) *p.ct*p.pt as week,\r\n" + 
			"        p.ct, p.pt\r\n" + 
			"  from c_monitor c\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and e.data_time = to_date(#{monday}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) a\r\n" + 
			"    on (c.monitor_id = a.DEV_ADDRESS)\r\n" + 
			"  left join (select e.dev_address, e.data_time, e.pap_r, c.monitor_name\r\n" + 
			"               from c_monitor c\r\n" + 
			"               left join(e_monitor_day_energy e)\r\n" + 
			"                 on (c.monitor_id = e.dev_address)\r\n" + 
			"              where 1 = 1and\r\n" + 
			"              c.device_type != 5\r\n" + 
			"                and e.data_time = to_date(#{sunday}, 'yyyy-mm-dd')\r\n" + 
			"              order by DEV_ADDRESS) b\r\n" + 
			"    on (c.monitor_id = b.DEV_ADDRESS)\r\n" + 
			"     left join (c_equip) p on (p.equip_id = c.monitor_id)\r\n" + 
			" where 1 = 1\r\n" + 
			"   and c.device_type != 5 order by MONITOR_NAME"})
	@Results(id = "getmonitorweekDcMap", value = {
			@Result(column ="dev_address",property = "monitorAddress"),
			@Result(column ="monitor_name",property = "monitorName"),
			@Result(column ="week",property = "monitorday",javaType = BigDecimal.class),
	})
	List<Energyanalysis>getmonitorweekDc(String monday, String sunday);
	
	@Select(value = "   select d.diagnosis_id,\r\n" + 
			"          d.diagnosis_name,\r\n" + 
			"          nvl((a.pap_r - b.pap_r),0) as today\r\n" + 
			"     from c_diagnosis d\r\n" + 
			"     left join (select e.pap_r, c.diagnosis_id\r\n" + 
			"                  from c_diagnosis c\r\n" + 
			"                  left join(e_diagnosis_day) e\r\n" + 
			"                    on (c.diagnosis_id = e.diagnosis_id)\r\n" + 
			"                 where trunc(e.data_time) =\r\n" + 
			"                       to_date(#{today}, 'yyyy-mm-dd')) a\r\n" + 
			"       on (a.diagnosis_id = d.diagnosis_id)\r\n" + 
			"     left join (select e.pap_r, c.diagnosis_id\r\n" + 
			"                  from c_diagnosis c\r\n" + 
			"                  left join(e_diagnosis_day) e\r\n" + 
			"                    on (c.diagnosis_id = e.diagnosis_id)\r\n" + 
			"                 where trunc(e.data_time) =\r\n" + 
			"                       to_date(#{yesterday}, 'yyyy-mm-dd')) b\r\n" + 
			"       on (b.diagnosis_id = d.diagnosis_id)\r\n" + 
			"    where 1 = 1\r\n" + 
			"    order by d.diagnosis_name")
	@Results(id = "getdiagnosisdayDataMap", value = {
			@Result(column ="diagnosis_id",property = "diagnosisId"),
			@Result(column ="diagnosis_name",property = "diagnosisName"),
			@Result(column ="today",property = "diagnosistoday"),
	})
    List<Energydio>getdiagnosisdayData(String today, String yesterday);
	
	@Select(value = "   select d.diagnosis_id,\r\n" + 
			"          d.diagnosis_name,\r\n" + 
			"          nvl((a.pap_r - b.pap_r),0) as today\r\n" + 
			"     from c_diagnosis d\r\n" + 
			"     left join (select e.pap_r, c.diagnosis_id\r\n" + 
			"                  from c_diagnosis c\r\n" + 
			"                  left join(e_diagnosis_day) e\r\n" + 
			"                    on (c.diagnosis_id = e.diagnosis_id)\r\n" + 
			"                 where trunc(e.data_time) =\r\n" + 
			"                       to_date(#{today}, 'yyyy-mm-dd')) a\r\n" + 
			"       on (a.diagnosis_id = d.diagnosis_id)\r\n" + 
			"     left join (select e.pap_r, c.diagnosis_id\r\n" + 
			"                  from c_diagnosis c\r\n" + 
			"                  left join(e_diagnosis_day) e\r\n" + 
			"                    on (c.diagnosis_id = e.diagnosis_id)\r\n" + 
			"                 where trunc(e.data_time) =\r\n" + 
			"                       to_date(#{yesterday}, 'yyyy-mm-dd')) b\r\n" + 
			"       on (b.diagnosis_id = d.diagnosis_id)\r\n" + 
			"    where 1 = 1\r\n" + 
			"    order by TODAY")
	@Results(id = "getdiagnosisdayRankMap",value = {
			@Result(column ="diagnosis_id",property = "diagnosisId"),
			@Result(column ="diagnosis_name",property = "diagnosisName"),
			@Result(column ="today",property = "diagnosistoday",javaType = BigDecimal.class),
	})
	List<Energydio>getdiagnosisdayRank(String today, String yesterday);
	
	@Select(value = {"select a.PAST8 as weekday, nvl(b.pap_r, 0) as pap_r\r\n" + 
			"  from (select (trunc((trunc(next_day(sysdate - 7, 1))) + Rownum - 8, 'dd')) past8\r\n" + 
			"          from dual\r\n" + 
			"        connect by rownum <= 8) a\r\n" + 
			"  left join (select e.data_time, sum(e.pap_r) as pap_r\r\n" + 
			"               from c_diagnosis c\r\n" + 
			"               left join(e_diagnosis_day) e\r\n" + 
			"                 on (e.diagnosis_id = c.diagnosis_id)\r\n" + 
			"              where 1 = 1\r\n" + 
			"                and trunc(e.data_time) between\r\n" + 
			"                    to_date(#{monday}, 'yyyy-mm-dd') and\r\n" + 
			"                    to_date(#{sunday}, 'yyyy-mm-dd')\r\n" + 
			"              group by data_time) b\r\n" + 
			"    on (b.data_time = a.PAST8)\r\n" + 
			" order by weekday"})
	@Results(id = "diagnosisweekDataMap",value = {
			@Result(column ="weekday",property = "dataTime"),
			@Result(column ="pap_r",property = "diagnosistoday",javaType = BigDecimal.class),
	})
	List<Energydio>getdiagnosisweekData(String monday, String sunday);
}
