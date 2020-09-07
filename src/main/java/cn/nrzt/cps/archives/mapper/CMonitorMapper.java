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

import cn.nrzt.cps.archives.po.CMonitor;

@Mapper
@Repository
public interface CMonitorMapper {
	@Insert("INSERT INTO C_MONITOR(EQUIP_ID,COMM_ADDRESS) VALUES(#{equipId},#{commAddress})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")//添加该行，id值将自动添加到Measure
	int saveEquip(CMonitor monitor);

	@Select(" <script>" + 
			"SELECT m.*, dt.name as DEVICE_TYPE_DESC" + 
			"  FROM c_monitor m, p_code dt, c_equip e" + 
			" where 1 = 1" + 
			"   and m.device_type = dt.value(+)" + 
			"   and m.equip_address = e.comm_address(+)" + 
			"   and dt.code_type = 'eDeviceType'" +
			" <if test=\"DeviceType != null and '' != DeviceType and DeviceType != -1\"> and m.DEVICE_TYPE = #{DeviceType} </if> " +
			" <if test=\"monitorName != null and '' != monitorName\"> and m.MONITOR_NAME like '%' || #{monitorName} || '%' </if> " +
			" <if test=\"upEquipId != null and '' != upEquipId and '-1' != upEquipId\"> and e.up_equip_id = #{upEquipId} </if> " +
			"   order by m.MONITOR_NAME" +
			" </script> ")
	@Results(id = "monitorMap", value = { @Result(column = "MONITOR_ID", property = "monitorId", javaType = String.class),
			@Result(column = "MONITOR_NAME", property = "monitorName", javaType = Object.class),
			@Result(column = "DEVICE_TYPE", property = "deviceType", javaType = String.class),
			@Result(column = "EQUIP_ADDRESS", property = "equipAddress", javaType = String.class),
			@Result(column = "COLLECTION_RULE_ID", property = "collectionRuleId", javaType = String.class),
			@Result(column = "MONITOR_DESC", property = "monitorDesc", javaType = Object.class),
			@Result(column = "BUILDING_NO", property = "buildingNo", javaType = String.class),
			@Result(column = "FLOOR_NO", property = "floorNo", javaType = String.class),
			@Result(column = "ROOM_NO", property = "roomNo", javaType = String.class),
			@Result(column = "CREATOR", property = "creator", javaType = String.class),
			@Result(column = "RULE_NAME", property = "collectionRuleName", javaType = String.class),
			@Result(column = "DEVICE_TYPE_DESC", property = "deviceTypeDesc", javaType = String.class),
			@Result(column = "CREATE_TIME", property = "createTime", javaType = Object.class)
//            @Result(column = "sex", property = "userSex"),
//            @Result(column = "birthday", property = "userBirthday"),
//            @Result(column = "address", property = "userAddress"),
//            @Result(column = "id", property = "accounts",
//            many = @Many(select = "com.llb.dao.AccountMapper.findAccountByUid", fetchType = tFetchType.LAZY)
//        )
	})
	List<CMonitor> getAll(String DeviceType,String monitorName,String upEquipId);

	@Select(value = { "SELECT * FROM C_MONITOR where EQUIP_ID =#{equipid}" })
	@ResultMap("monitorMap")
	CMonitor getById(String equipid);

	@Delete(value = { "DELETE C_MONITOR WHERE MONITOR_ID = #{ID,jdbcType=VARCHAR}" })
	int deleteMonitor(String id);
	
	@Insert("INSERT INTO C_MONITOR" + 
			"	(MONITOR_ID, MONITOR_NAME, DEVICE_TYPE, EQUIP_ADDRESS, MONITOR_DESC, BUILDING_NO, FLOOR_NO, ROOM_NO, CREATOR, CREATE_TIME)" + 
			"VALUES" + 
			"	(#{monitorId,jdbcType=VARCHAR}," + 
			"	#{monitorName,jdbcType=VARCHAR}," + 
			"	#{deviceType,jdbcType=VARCHAR}," + 
			"	#{equipAddress,jdbcType=VARCHAR}," + 
			"	#{monitorDesc,jdbcType=VARCHAR}," + 
			"	#{buildingNo,jdbcType=VARCHAR}," + 
			"	#{floorNo,jdbcType=NUMERIC}," + 
			"	#{roomNo,jdbcType=NUMERIC}," + 
			"	#{creator,jdbcType=VARCHAR}," + 
			"	sysdate)")
//	@Options(useGeneratedKeys = true, keyProperty = "id")//添加该行，id值将自动添加到Measure
	@ResultMap("monitorMap")
	int saveMonitor(CMonitor monitor);

	
	@Update(value = { "UPDATE C_MONITOR" + 
			"   SET	MONITOR_NAME= #{monitorName,jdbcType=VARCHAR}," + 
			"		DEVICE_TYPE= #{deviceType,jdbcType=VARCHAR}," + 
			"		EQUIP_ADDRESS= #{equipAddress,jdbcType=VARCHAR}," + 
			"		MONITOR_DESC= #{monitorDesc,jdbcType=VARCHAR}," + 
			"		BUILDING_NO= #{buildingNo,jdbcType=VARCHAR}," + 
			"		FLOOR_NO= #{floorNo,jdbcType=NUMERIC}," + 
			"		ROOM_NO= #{roomNo,jdbcType=NUMERIC}," + 
			"		CREATOR= #{creator,jdbcType=VARCHAR}," + 
			"		MONITOR_ID= #{equipAddress,jdbcType=VARCHAR}" + 
			" WHERE MONITOR_ID = #{monitorId,jdbcType=VARCHAR}" })
	@ResultMap("monitorMap")
	int updateMonitor(CMonitor monitor);

	@Select(value = "SELECT SEQ_C_MONITOR.NEXTVAL FROM DUAL")
	int getSeq();

	@Update(value = "UPDATE C_MONITOR SET EQUIP_ADDRESS = NULL WHERE MONITOR_ID = #{monitorid,jdbcType=VARCHAR}")
	int releaseEquip(String monitorid);

//	@Select(value = "SELECT BUILDING_NO AS value,BUILDING_NAME AS label,ISLEAF AS ISLEAF FROM C_BUILDING")
	@Select(value = "SELECT DISTINCT (BUILDING_NO) AS VALUE, BUILDING_NAME AS LABEL, ISLEAF" + 
			"  FROM (SELECT B.BUILDING_NO," + 
			"               B.BUILDING_NAME," + 
			"               CASE" + 
			"                 WHEN F.FLOOR_NO IS NULL THEN" + 
			"                  '1'" + 
			"                 ELSE" + 
			"                  '0'" + 
			"               END AS ISLEAF" + 
			"          FROM C_BUILDING B, C_FLOOR F" + 
			"         WHERE B.BUILDING_NO = F.BUILING_NO(+))" + 
			" ORDER BY BUILDING_NO")
	List<Map<String, Object>> getBuild();
	
//	@Select(value = "SELECT FLOOR_NO AS value,FLOOR_NAME AS label,ISLEAF FROM C_FLOOR WHERE BUILING_NO = #{builingNo,jdbcType=VARCHAR}")
	@Select(value = "SELECT DISTINCT (FLOOR_NO) AS VALUE, FLOOR_NAME AS LABEL, ISLEAF" + 
			"  FROM (SELECT F.FLOOR_NO," + 
			"               F.FLOOR_NAME," + 
			"               CASE" + 
			"                 WHEN R.FLOOR_NO IS NULL THEN" + 
			"                  '1'" + 
			"                 ELSE" + 
			"                  '0'" + 
			"               END AS ISLEAF" + 
			"          FROM C_FLOOR F, C_ROOM R" + 
			"         WHERE F.BUILING_NO = #{builingNo,jdbcType=VARCHAR}" + 
			"           AND F.FLOOR_NO = R.FLOOR_NO(+))" + 
			" ORDER BY FLOOR_NO")
	List<Map<String, Object>> getFloor(String builingNo);
	
	@Select(value = "SELECT ROOM_NO AS value,ROOM_NAME AS label FROM C_ROOM WHERE FLOOR_NO = #{floorNo,jdbcType=VARCHAR}")
	List<Map<String, Object>> getRoom(String floorNo);
	
	@Select(value = " <script>" +
			"SELECT * FROM c_monitor m,c_equip e WHERE m.MONITOR_ID NOT IN (SELECT MONITOR_ID FROM R_DIAGNOSIS_MONITOR WHERE 1=1 "+ 
//			"and DIAGNOSIS_ID = #{diagnosisId, jdbcType = VARCHAR}" +
			" <if test=\"diagnosisId != null and diagnosisId != '' and diagnosisId != -1\"> and DIAGNOSIS_ID = #{diagnosisId} </if> " +
			") and m.monitor_id=e.EQUIP_ID(+)" + 
//			"and e.up_equip_id=?" + 
			" <if test=\"upEquipId != null and upEquipId != '' and upEquipId != -1\"> and e.up_equip_id= #{upEquipId} </if> " +
			"order by m.MONITOR_NAME" +
			" </script> ")
	@ResultMap("monitorMap")
	List<CMonitor> findNoBMonitor(String diagnosisId,String upEquipId);
	
	@Select(" <script>" + 
			"SELECT m.* FROM R_DIAGNOSIS_MONITOR r, c_monitor m,c_equip e WHERE r.monitor_id = m.monitor_id(+) and m.monitor_id=e.equip_id(+) AND r.diagnosis_id = #{diagnosisId, jdbcType = VARCHAR}" + 
//			"and e.up_equip_id = #{up_equip_id, jdbcType = VARCHAR}\r\n" + 
			" <if test=\"upEquipId != null and upEquipId != '' and upEquipId != -1\"> and e.up_equip_id = #{upEquipId} </if> " +
			"order by m.MONITOR_NAME" +
			" </script> ")
	@ResultMap("monitorMap")
	List<CMonitor> findYetBMonitor(String diagnosisId,String upEquipId);

	@Select("SELECT r.MONITOR_ID,r.collection_rule_id,c.rule_name FROM R_MONITOR_COLLECTION_RULE r,c_collection_rule c where r.collection_rule_id=c.collection_rule_id(+) and MONITOR_ID = #{monitorId}")
	List<Map<String, Object>> getRCollectRule(String monitorId);

	@Delete("delete from R_MONITOR_COLLECTION_RULE where MONITOR_ID = #{monitorId,jdbcType=VARCHAR}")
	int clearMonAndCollRule(String monitorId);

	@Insert(value = "insert into R_MONITOR_COLLECTION_RULE" + 
			"  (REL_ID, MONITOR_ID, COLLECTION_RULE_ID)" + 
			"values" + 
			"  (SEQ_R_MONITOR_COLLECTION_RULE.Nextval, #{monitorId,jdbcType=VARCHAR}, #{collectionRuleId,jdbcType=VARCHAR})")
	int saveMonAndCollRule(String monitorId, String collectionRuleId);

	@Select("SELECT * FROM c_building order by BUILDING_NO")
	List<Map<String, Object>> getAddressB();
	@Select("SELECT * FROM c_floor order by FLOOR_NO")
	List<Map<String, Object>> getAddressF();
	@Select("SELECT * FROM c_room order by ROOM_NO")
	List<Map<String, Object>> getAddressR();

}
