package cn.nrzt.cps.archives.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CMonitor;

@Mapper
@Repository
public interface CDiagnosisMapper {
	
	@Select(value = { "<script>SELECT D.*, P.NAME AS DIAGNOSIS_TYPE_DESC" + 
			"  FROM C_DIAGNOSIS D, P_CODE P" + 
			" WHERE 1 = 1" + 
			"   AND D.DIAGNOSIS_TYPE = P.VALUE(+)" + 
			"   AND P.CODE_TYPE = 'diagnosisType'"
			+ " <if test=\"diagnosisId != null and '' != diagnosisId\"> and D.DIAGNOSIS_ID = #{diagnosisId} </if> "
			+ " <if test=\"diagnosisType != null and '' != diagnosisType\"> and D.DIAGNOSIS_TYPE = #{diagnosisType} </if> "
			+ " order by d.DIAGNOSIS_NAME"
			+ " </script>"})
	@Results(id = "diagnosisMap", value = { @Result(column = "DIAGNOSIS_ID", property = "diagnosisId", javaType = String.class),
			@Result(column = "DIAGNOSIS_NAME", property = "diagnosisName", javaType = Object.class),
			@Result(column = "DIAGNOSIS_RULE_ID", property = "diagnosisRuleId", javaType = String.class),
			@Result(column = "DIAGNOSIS_TYPE", property = "diagnosisType", javaType = String.class),
			@Result(column = "CREATOR", property = "creator", javaType = String.class),
			@Result(column = "CREATE_TIME", property = "createTime", javaType = Object.class),
			@Result(column = "DIAGNOSIS_RULE_DESC", property = "diagnosisRuleDesc", javaType = Object.class),
			@Result(column = "DIAGNOSIS_TYPE_DESC", property = "diagnosisTypeDesc", javaType = String.class)
	})
	List<CDiagnosis> getAll(String diagnosisId,String diagnosisType);
	
//	@Select(value = "SELECT M.MONITOR_NAME FROM C_MONITOR M,C_DIAGNOSIS_MONITOR_REL DM WHERE M.MONITOR_ID=DM.MONITOR_ID(+) AND DM.DIAGNOSIS_ID=#{rowSelectId,jdbcType=VARCHAR}")
//	List<Map<String, Object>> getAllPermissions(int rowSelectId);
	
	@Select(value = "SELECT DM.DIAGNOSIS_ID,M.* FROM C_MONITOR M,R_DIAGNOSIS_MONITOR DM WHERE M.MONITOR_ID=DM.MONITOR_ID(+) order by M.MONITOR_NAME")
	List<Map<String, Object>> getMDrel();
	
	@Delete(value = { "DELETE C_DIAGNOSIS WHERE DIAGNOSIS_ID = #{diagnosisId,jdbcType=VARCHAR}" })
	int deleteDiagnosis(String diagnosisId);

	@Insert("INSERT INTO C_DIAGNOSIS" + 
			"	(DIAGNOSIS_ID, DIAGNOSIS_NAME, DIAGNOSIS_TYPE, CREATE_TIME)" + 
			"VALUES" + 
			"	(#{diagnosisId,jdbcType=VARCHAR}," + 
			"	#{diagnosisName,jdbcType=VARCHAR}," + 
			"	#{diagnosisType,jdbcType=VARCHAR}," + 
			"	sysdate)")
	@ResultMap("diagnosisMap")
	int saveDiagnosis(CDiagnosis diagnosis);
	
	@Update(value = { "UPDATE C_DIAGNOSIS" + 
			"   SET	DIAGNOSIS_NAME= #{diagnosisName,jdbcType=VARCHAR}," + 
			"		DIAGNOSIS_TYPE= #{diagnosisType,jdbcType=VARCHAR}" + 
			" WHERE DIAGNOSIS_ID = #{diagnosisId,jdbcType=VARCHAR}" })
	@ResultMap("diagnosisMap")
	int updateDiagnosis(CDiagnosis diagnosis);
	
	@Select(value = { "SELECT SEQ_C_DIAGNOSIS.NEXTVAL as DIAGNOSIS_ID FROM DUAL" })
	int getSeq();
	
	@Insert(value = "INSERT INTO R_DIAGNOSIS_MONITOR" + 
			"(REL_ID, DIAGNOSIS_ID, MONITOR_ID)" + 
			"VALUES" + 
			"(SEQ_C_DIAGNOSIS_MONITOR_REL.NEXTVAL, #{diagnosisId,jdbcType=VARCHAR}, #{monitorId,jdbcType=VARCHAR})")
	int AddDiagnosisMonRel(String diagnosisId,Object monitorId);
	
	@Delete(value = "DELETE R_DIAGNOSIS_MONITOR " + 
			" WHERE diagnosis_id = #{diagnosisId,jdbcType=VARCHAR}")
	int deleteDMrel(String diagnosisId);

	@Select(value = "SELECT r.diagnosis_id,c.diagnosis_rule_id as value,c.diagnosis_name as name FROM R_DIAGNOSIS_RULE r,c_diagnosis_rule c where r.diagnosis_rule_id=c.diagnosis_rule_id(+)")
	List<Map<String, Object>> getRDMR();

	@Select(value = "SELECT DIAGNOSIS_RULE_ID,DIAGNOSIS_NAME FROM c_diagnosis_rule")
	@Results(value ={
			@Result(column = "DIAGNOSIS_RULE_ID", property = "value"),
			@Result(column = "DIAGNOSIS_NAME", property = "key",javaType = String.class),
			@Result(column = "DIAGNOSIS_NAME", property = "display",javaType = String.class)
	})
	List<Map<String, Object>> getDiagnosisRuleSelect();

	@Insert(value = "insert into r_diagnosis_rule " + 
			"  (rel_id, diagnosis_id, diagnosis_rule_id) " + 
			" values " + 
			"  (SEQ_R_DIAGNOSIS_MONITOR_RULE.Nextval, #{diagnosisId,jdbcType=VARCHAR}, #{diagnosisRuleId,jdbcType=VARCHAR})")
	int saveDiagnosisAndDrule(String diagnosisId, String diagnosisRuleId);
	
	@Delete(value = "delete r_diagnosis_rule where DIAGNOSIS_ID = #{diagnosisId,jdbcType=VARCHAR}")
	int clearDiagnosisAndDrule(String diagnosisId);

	@Select(value = { "<script>SELECT c.name as DEVICE_TYPE_DESC,e.up_equip_id,m.* FROM c_monitor m,p_code c,c_equip e where 1=1 and m.device_type=c.value(+) and c.code_type='eDeviceType' and m.monitor_id=e.equip_id"
			+ " <if test=\"deviceType != null and '' != deviceType\"> and m.device_type= #{deviceType} </if> "
			+ " <if test=\"upEquipId != null and '' != upEquipId\"> and e.up_equip_id= #{upEquipId} </if> "
			+ " <if test=\"monitorId != null and '' != monitorId\"> and m.monitor_id like '%' || #{monitorId} || '%' </if> "
			+ " <if test=\"monitorName != null and '' != monitorName\"> and m.monitor_name like '%' || #{monitorName} || '%' </if> "
			+ " order by m.device_type,m.monitor_name "
			+ " </script>"})
	@Results(value = { @Result(column = "DEVICE_TYPE_DESC", property = "deviceTypeDesc", javaType = String.class),
			@Result(column = "MONITOR_ID", property = "monitorId", javaType = Object.class),
			@Result(column = "MONITOR_NAME", property = "monitorName", javaType = String.class),
			@Result(column = "DEVICE_TYPE", property = "deviceType", javaType = String.class),
			@Result(column = "EQUIP_ADDRESS", property = "equipAddress", javaType = String.class),
			@Result(column = "MONITOR_DESC", property = "monitorDesc", javaType = String.class),
			@Result(column = "BUILDING_NO", property = "buildingNo", javaType = String.class),
			@Result(column = "FLOOR_NO", property = "floorNo", javaType = String.class),
			@Result(column = "ROOM_NO", property = "roomNo", javaType = String.class),
			@Result(column = "CREATOR", property = "creator", javaType = String.class),
			@Result(column = "CREATE_TIME", property = "createTime", javaType = String.class)
	})
	List<CMonitor> getAllMonitorPointPage(String deviceType,String upEquipId,String monitorId,String monitorName);
}
