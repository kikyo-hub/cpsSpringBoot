package cn.nrzt.cps.energycenter.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import cn.nrzt.cps.archives.po.EDiagnosisCurve;
import cn.nrzt.cps.energycenter.po.SMonitorRun;



@Mapper
@Repository
public interface EquipmentMonitoringMapper {
	@Select(value = { "select edc.DIAGNOSIS_ID,TO_CHAR(edc.DATA_TIME,'yyyy-mm-dd hh24:mi:ss') as DATA_TIME,edc.P,edc.PA,edc.PB,edc.PC,edc.UA,edc.UB,edc.UC,"
			+ " edc.IA,edc.IB,edc.IC,edc.I0,edc.Q,edc.QA,edc.QB,edc.QC "
			+ "from E_DIAGNOSIS_CURVE edc where 1=1 and TO_CHAR(edc.DATA_TIME,'yyyy-mm-dd')=#{date} and edc.DIAGNOSIS_ID=#{paramid} " })
	@Results(id = "ediagnosiscurvemap", value ={
            @Result(column = "DIAGNOSIS_ID", property = "monitorId",javaType = String.class),
            @Result(column = "DATA_TIME", property = "dataTime", javaType = Object.class),
            @Result(column = "UA", property = "ua",javaType = BigDecimal.class),
            @Result(column = "UB", property = "ub",javaType = BigDecimal.class),
            @Result(column = "UC", property = "uc",javaType = BigDecimal.class),
            @Result(column = "IA", property = "ia",javaType = BigDecimal.class),
            @Result(column = "IB", property = "ib",javaType = BigDecimal.class),
            @Result(column = "IC", property = "ic",javaType = BigDecimal.class),
            @Result(column = "I0", property = "i0",javaType = BigDecimal.class),
            @Result(column = "P", property = "p"  ,javaType = BigDecimal.class),
            @Result(column = "PA", property = "pa",javaType = BigDecimal.class),
            @Result(column = "PB", property = "pb",javaType = BigDecimal.class),
            @Result(column = "PC", property = "pc",javaType = BigDecimal.class),
            @Result(column = "Q", property = "q",javaType = BigDecimal.class),
            @Result(column = "QA", property = "qa",javaType = BigDecimal.class),
            @Result(column = "QB", property = "qb",javaType = BigDecimal.class),
            @Result(column = "QC", property = "qc",javaType = BigDecimal.class),
            @Result(column = "AN_IA", property = "anIa",javaType = BigDecimal.class),
            @Result(column = "AN_IB", property = "anIb",javaType = BigDecimal.class),
            @Result(column = "AN_IC", property = "anIc",javaType = BigDecimal.class),
            @Result(column = "AN_UABA", property = "anUaba",javaType = BigDecimal.class),
            @Result(column = "AN_UB", property = "anUb",javaType = BigDecimal.class),
            @Result(column = "AN_UCBC", property = "anUcbc",javaType = BigDecimal.class),
            @Result(column = "PAP_E", property = "papE",javaType = BigDecimal.class),
            @Result(column = "PRP_E", property = "prpE",javaType = BigDecimal.class),
            @Result(column = "RAP_E", property = "rapE",javaType = BigDecimal.class),
            @Result(column = "RRP_E", property = "rrpE",javaType = BigDecimal.class),
            @Result(column = "PAP_R", property = "papR",javaType = BigDecimal.class),
            @Result(column = "PRP_R", property = "prpR",javaType = BigDecimal.class),
            @Result(column = "RAP_R", property = "rapR",javaType = BigDecimal.class),
            @Result(column = "RRP_R", property = "rrpR",javaType = BigDecimal.class),
            @Result(column = "F", property = "f",javaType = BigDecimal.class),
            @Result(column = "FA", property = "fa",javaType = BigDecimal.class),
            @Result(column = "FB", property = "fb",javaType = BigDecimal.class),
            @Result(column = "FC", property = "fc",javaType = BigDecimal.class),
    })
	List<EDiagnosisCurve> getAllLidata(String date,String paramid);
	
	
    @Select(value = {"<script>SELECT c.*,\r\n" + 
    		"       s.equip_status,\r\n" + 
    		"       s.switch_status,\r\n" + 
    		"       b.building_name,\r\n" + 
    		"       f.floor_name,\r\n" + 
    		"       r.room_name,\r\n" + 
    		"       p.name\r\n" + 
    		"  FROM c_monitor c\r\n" + 
    		"  left join S_MONITOR_RUN s\r\n" + 
    		"    on (c.monitor_id = s.monitor_id)\r\n" + 
    		"  left join c_building b\r\n" + 
    		"    on (c.building_no = b.building_no)\r\n" + 
    		"  left join c_floor f\r\n" + 
    		"    on (f.floor_no = c.floor_no)\r\n" + 
    		"  left join c_room r\r\n" + 
    		"    on (r.room_no = c.room_no)\r\n" + 
    		"  left join p_code p\r\n" + 
    		"    on (c.device_type = p.value)\r\n" + 
    		" where p.code_type = 'eDeviceType'"
    		+"<if test=\"bulidingno != null and '' != bulidingno\"> AND c.BUILDING_NO=#{bulidingno} </if> "
    		+"<if test=\"floor != null and '' != floor\"> AND c.FLOOR_NO=#{floor} </if> "
    		+"<if test=\"status != null and '' != status\"> AND s.SWITCH_STATUS =#{status} </if> "
    		+"<if test=\"equipstatus != null and '' != equipstatus\"> AND s.EQUIP_STATUS =#{equipstatus} </if> "
    		+"AND c.DEVICE_TYPE         =2" 
    		+"</script>"})
    @Results(id ="getTempEnergyEquipMap",value={
    		@Result(column ="MONITOR_ID",property = "monotorId"),
        	@Result(column ="MONITOR_NAME",property = "monotorName"),
        	@Result(column ="DEVICE_TYPE",property = "deviceType"),
        	@Result(column ="NAME",property = "Name"),
        	@Result(column ="EQUIP_ADDRESS",property = "equipAddress"),
        	@Result(column ="BUILDING_NO",property = "buildingNo"),
	    	@Result(column ="FLOOR_NO", property = "floorNO"),
	    	@Result(column = "ROOM_NO", property = "roomNo"),
        	@Result(column = "EQUIP_STATUS", property = "equipStatus"),
        	@Result(column = "SWITCH_STATUS", property = "switchStatus"),
        	@Result(column = "BUILDING_NAME", property = "buildingName"),
        	@Result(column = "FLOOR_NAME", property = "floorName"),
        	@Result(column = "ROOM_NAME", property = "roomName"),
    	
    })
	List<SMonitorRun> getAlllitable(String bulidingno, String floor, String status, String equipstatus);
    
    @Select(value = {"<script>SELECT c.*,\r\n" + 
			    		"       s.equip_status,\r\n" + 
			    		"       s.switch_status,\r\n" + 
			    		"       b.building_name,\r\n" + 
			    		"       f.floor_name,\r\n" + 
			    		"       r.room_name,\r\n" + 
			    		"       p.name\r\n" + 
			    		"  FROM c_monitor c\r\n" + 
			    		"  left join S_MONITOR_RUN s\r\n" + 
			    		"    on (c.monitor_id = s.monitor_id)\r\n" + 
			    		"  left join c_building b\r\n" + 
			    		"    on (c.building_no = b.building_no)\r\n" + 
			    		"  left join c_floor f\r\n" + 
			    		"    on (f.floor_no = c.floor_no)\r\n" + 
			    		"  left join c_room r\r\n" + 
			    		"    on (r.room_no = c.room_no)\r\n" + 
			    		"  left join p_code p\r\n" + 
			    		"    on (c.device_type = p.value)\r\n" + 
			    		" where p.code_type = 'eDeviceType'\r\n"
						+"<if test=\"type != null and '' != type\"> AND c.DEVICE_TYPE=#{type} </if> "
						+"</script>"})
    @Results(id ="getAlllitMap",value={
        	@Result(column ="MONITOR_ID",property = "monotorId"),
        	@Result(column ="MONITOR_NAME",property = "monotorName"),
        	@Result(column ="DEVICE_TYPE",property = "deviceType"),
        	@Result(column ="NAME",property = "Name"),
        	@Result(column ="EQUIP_ADDRESS",property = "equipAddress"),
        	@Result(column ="BUILDING_NO",property = "buildingNo"),
	    	@Result(column ="FLOOR_NO", property = "floorNO"),
	    	@Result(column = "ROOM_NO", property = "roomNo"),
        	@Result(column = "EQUIP_STATUS", property = "equipStatus"),
        	@Result(column = "SWITCH_STATUS", property = "switchStatus"),
        	@Result(column = "BUILDING_NAME", property = "buildingName"),
        	@Result(column = "FLOOR_NAME", property = "floorName"),
        	@Result(column = "ROOM_NAME", property = "roomName"),
        	
        })
    List<SMonitorRun> getAlllit(int type);
}
