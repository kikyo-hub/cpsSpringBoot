package cn.nrzt.cps.energyuse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.nrzt.cps.energyuse.po.Lighte;
@Mapper
public interface EnergyConsumptionMapper {
    @Select("select COUNT(1),SUM(LOAD) as LOAD,SUM(ELECTRICITY_CONSUMPTION)AS ELECTRICITY_CONSUMPTION from LIGHT where LIGHT_TYPE='1' and RUN_STATE='0' and TO_CHAR(MONITOR_DATE,'yyyy-mm-dd') =#{date} ")
    @Results(id="electricMap",value={
    		@Result(column = "LIGHTID", property = "lightid",javaType = String.class),
    		@Result(column = "LIGHT_TYPE", property = "lightType",javaType = String.class),
    		@Result(column = "RUN_STATE", property = "runState",javaType = String.class),
    		@Result(column = "MONITOR_DATE", property = "monitorDate",javaType = String.class),
    		@Result(column = "ELECTRICITY_CONSUMPTION", property = "electricityConsumption",javaType = String.class),
    		@Result(column = "LOAD", property = "load",javaType = String.class)
    })
	List<Lighte> electric(String date);

}
