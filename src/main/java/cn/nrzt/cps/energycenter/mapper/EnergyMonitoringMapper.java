package cn.nrzt.cps.energycenter.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import cn.nrzt.cps.energycenter.po.SMonitorRun;
import io.swagger.annotations.ApiOperation;

@Mapper
public interface EnergyMonitoringMapper {
	@Select(value = { "SELECT s.*, p.name,c.EQUIP_ADDRESS,c.floor_no,c.DEVICE_TYPE,c.MONITOR_NAME " + 
			"  FROM S_MONITOR_RUN s " + 
			"  left join C_MONITOR c " + 
			"    on (s.monitor_id = c.monitor_id) " + 
			"  left join p_code p " + 
			"    on (c.device_type = p.value) " + 
			" where 1 = 1 " + 
			"   and p.code_type = 'eDeviceType' " + 
			"   and c.DEVICE_TYPE = #{type,jdbcType=VARCHAR}"})
	@Results(id = "cengersurveyMap", value ={
			@Result(column = "MONITOR_ID", property = "monotorId"),
            @Result(column = "MONITOR_NAME", property = "equipName"),
            @Result(column = "NAME", property = "typeName"),
            @Result(column = "DEVICE_TYPE", property = "type"),
            @Result(column = "SWITCH_STATUS", property = "equipStatus"),
            @Result(column = "EQUIP_STATUS", property = "switchStatus"),
            @Result(column = "EQUIP_ADDRESS", property = "equipAddress"),
            @Result(column = "FLOOR_NO", property = "floorNO")
            
    })
	List<SMonitorRun> getElecdata(String type);
	
	@Delete(value = { "DELETE S_MONITOR_RUN WHERE MONITOR_ID = #{monotorId}" })
    int deleteenergySurvey(String monotorId);
}
