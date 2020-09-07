package cn.nrzt.cps.energydiagnosis.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import cn.nrzt.cps.energydiagnosis.po.Report;

@Mapper
@Repository
public interface ReportMapper {
	@Select(value = { "<script>select b.value,b.deviceTypeNum,p.name\r\n" + 
			"  from (select a.value, count(*) as deviceTypeNum\r\n" + 
			"          from (select cm.*, p.code_type, p.value, p.name\r\n" + 
			"                  from C_MONITOR cm, P_CODE p\r\n" + 
			"                 where 1 = 1\r\n" + 
			"                   and p.code_type = 'eDeviceType'\r\n" + 
			"                   and cm.DEVICE_TYPE = p.value) a\r\n" + 
			"         group by a.value) b,\r\n" + 
			"       P_CODE p\r\n" + 
			" where b.value = p.value\r\n" + 
			"   and p.code_type = 'eDeviceType'" 
			+ "</script>"})
	@Results(id = "monitornumMap", value ={
			@Result(column = "DEVICETYPENUM", property = "deviceTypeNum",javaType = String.class),
            @Result(column = "NAME", property = "deviceType",javaType = String.class)
    })
	 List<Report> getMonitorNum();
}
