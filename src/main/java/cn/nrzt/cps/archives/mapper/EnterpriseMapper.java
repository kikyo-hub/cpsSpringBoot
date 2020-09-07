package cn.nrzt.cps.archives.mapper;


import cn.nrzt.cps.archives.po.*;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface EnterpriseMapper {
	@ApiOperation(value = "查询企业信息及下面的楼栋信息")
	@Select(value = { "select t.*,p.name enterprise_typename from C_ENTERPRISE t,P_CODE p where p.code_type='enterpriseType' and t.enterprise_type=p.value" })
	@Results(id = "centerpriseMap", value ={
			@Result(column = "ENTERPRISE_ID", property = "id"),
            @Result(column = "NAME", property = "name"),
            @Result(column = "ADDRESS", property = "address"),
            @Result(column = "ENTERPRISE_TYPE", property = "enterprise_type"),
            @Result(column = "enterprise_typename", property = "enterprise_typename"),
            @Result(column = "EMPLOYEES", property = "employees"),
            @Result(column = "AREA", property = "area"),
            @Result(column = "PROFILE", property = "profile",javaType = String.class),
//            @Result(column = "CATEGORY", property = "category"),
//            @Result(column = "CREATE_DATE", property = "create_date"),
           //@Result(property = "enterpriseBuildingRels", column = "ENTERPRISE_ID",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.selectCEnterpriseBuildingRel")),
            @Result(property = "Buildings", column = "ENTERPRISE_ID",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.selectCBuildingById")),
            
    })
	LinkedHashMap<String, Object> getAllEnterprisedata();
	@ApiOperation(value = "楼栋和中间表关联")
	@Select("select * from C_BUILDING cb ,R_ENTERPRISE_BUILDING   cebr where cb.BUILDING_NO=cebr.BUILDING_NO and cebr.ENTERPRISE_ID=#{enterprise_id} order by cb.BUILDING_TIME asc")
    @Results(id="buildingResultMap"
        ,value={
             @Result( property = "buildingNo", column = "building_no" )
            ,@Result( property = "buildingAddress", column = "building_address" )
            ,@Result( property = "buildingArea", column = "building_area" )
            ,@Result( property = "buildingName", column = "building_name" )
            ,@Result( property = "floorCnt", column = "floor_cnt" )
            ,@Result( property = "buildingTime", column = "BUILDING_TIME"),
//            @Result( property = "longitude", column = "LONGITUDE"),
            @Result( property = "monitors", column = "building_no",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getCMonitor")),
            @Result( property = "building_category", column = "BUILDING_CATEGORY"),
            @Result( property = "employees", column = "EMPLOYEES"),
            @Result( property = "floors", column = "building_no",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getCfloor"))
//            @Result(property = "enterprisebuilder", column = "BUILDING_NO",one = @One(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.selectCEnterpriseBuildingRel"))
    } )
	List<CBuilding> selectCBuildingById(String enterprise_id);
	
	
	@ApiOperation(value = "根据buildingNo查询monitor")
	@Select("select t.*,p.name from C_MONITOR t,P_CODE p where p.code_type='eDeviceType' and t.device_type=p.value and t.BUILDING_NO=#{buildingNo} order by t.MONITOR_ID asc ")
	@Results(value = {
			@Result(property = "monitorId",column = "MONITOR_ID"),
			@Result(property = "monitorName",column = "MONITOR_NAME"),
			@Result(property = "deviceType",column = "DEVICE_TYPE"),
			@Result(property = "equipAddress",column = "EQUIP_ADDRESS"),
			@Result(property = "monitorDesc",column = "MONITOR_DESC"),
			@Result(property = "buildingNo",column = "BUILDING_NO"),
			@Result(property = "floorNo",column = "FLOOR_NO"),
			@Result(property = "roomNo",column = "ROOM_NO"),
			@Result(property = "deviceTypeName",column = "NAME")
	})
	List<CMonitor> getCMonitor(String buildingNo);
	
	
	@ApiOperation(value = "根据floorNo查询监测点")
	@Select("select t.*,p.name from C_MONITOR t,P_CODE p where p.code_type='eDeviceType' and t.device_type=p.value and t.FLOOR_NO=#{FLOOR_NO} order by t.MONITOR_ID asc ")
	@Results(value = {
			@Result(property = "monitorId",column = "MONITOR_ID"),
			@Result(property = "monitorName",column = "MONITOR_NAME"),
			@Result(property = "deviceType",column = "DEVICE_TYPE"),
			@Result(property = "equipAddress",column = "EQUIP_ADDRESS"),
			@Result(property = "monitorDesc",column = "MONITOR_DESC"),
			@Result(property = "buildingNo",column = "BUILDING_NO"),
			@Result(property = "floorNo",column = "FLOOR_NO"),
			@Result(property = "roomNo",column = "ROOM_NO"),
			@Result(property = "deviceTypeName",column = "NAME")
	})
	List<CMonitor> getCMonitorByFloor(String FLOOR_NO);
	
	@ApiOperation(value = "根据floorNo查询监测点")
	@Select("select t.*,p.name from C_MONITOR t,P_CODE p where p.code_type='eDeviceType' and t.device_type=p.value and t.ROOM_NO=#{ROOM_NO} order by t.MONITOR_ID asc ")
	@Results(value = {
			@Result(property = "monitorId",column = "MONITOR_ID"),
			@Result(property = "monitorName",column = "MONITOR_NAME"),
			@Result(property = "deviceType",column = "DEVICE_TYPE"),
			@Result(property = "equipAddress",column = "EQUIP_ADDRESS"),
			@Result(property = "monitorDesc",column = "MONITOR_DESC"),
			@Result(property = "buildingNo",column = "BUILDING_NO"),
			@Result(property = "floorNo",column = "FLOOR_NO"),
			@Result(property = "roomNo",column = "ROOM_NO"),
			@Result(property = "deviceTypeName",column = "NAME")
	})
	List<CMonitor> getCMonitorByRoom(String ROOM_NO);
	
	
	@ApiOperation(value = "根据buildingNo查询楼层")
	@Select("select * from C_FLOOR cfr where cfr.BUILING_NO=#{buildingNo} order by cfr.FLOOR_NAME desc ")
	@Results(value = {
			@Result(property = "floorNo",column = "FLOOR_NO", javaType = String.class),
			@Result(property = "floorName",column = "FLOOR_NAME", javaType = String.class),
			@Result(property = "buildingNo",column = "BUILING_NO", javaType = String.class),
			@Result(property = "monitors",column = "FLOOR_NO",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getCMonitorByFloor")),
			@Result(property = "rooms",column = "FLOOR_NO",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getCroom"))
	})
	List<CFloor> getCfloor(String buildingNo);
	
	@ApiOperation(value = "根据FLOOR_NO查询rooms")
	@Select("select * from C_ROOM crm where crm.FLOOR_NO=#{FLOOR_NO} order by crm.ROOM_NO ")
	@Results(value = {
			@Result(property = "roomNo",column = "ROOM_NO"),
			@Result(property = "roomName",column = "ROOM_NAME"),
			@Result(property = "monitors",column = "ROOM_NO",many = @Many(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getCMonitorByRoom"))
	})
	List<CRoom> getCroom(String FLOOR_NO);
	
	@ApiOperation(value = "查询中间表")
    @Select("select * from R_ENTERPRISE_BUILDING where BUILDING_NO=#{building_no} ")
    @Results(id="cEntepriseBuiderMap",value= {@Result( property = "relId", column = "REL_ID"),
    		@Result( property = "enterpriseId", column = "ENTERPRISE_ID"),
    		@Result( property = "buildingNo", column = "BUILDING_NO")
    })
	List<CEnterpriseBuildingRel> selectCEnterpriseBuildingRel(String building_no);
    
    @ApiOperation(value = "查询楼栋诊断点信息")
    @Select("SELECT\r\n" + 
    		" cd.DIAGNOSIS_ID,\r\n" + 
    		" max(cd.DIAGNOSIS_NAME) as DIAGNOSIS_NAME,\r\n" + 
    		" max(cd.DIAGNOSIS_DESC) as DIAGNOSIS_DESC ,\r\n" + 
    		" max(cd.DIAGNOSIS_NAME) as DIAGNOSIS_NAME,\r\n" + 
    		" max(cd.CREATE_TIME) as CREATE_TIME,\r\n" + 
    		" max(p.NAME) as DIAGNOSIS_TYPE,\r\n" + 
    		" max(cb.BUILDING_NO) as BUILDING_NO,\r\n" + 
    		" max(cb.BUILDING_TIME) as BUILDING_TIME,\r\n" + 
    		" max(cb.BUILDING_NAME) as BUILDING_NAME,\r\n" + 
    		"  max(cb.BUILDING_ADDRESS)as BUILDING_ADDRESS,\r\n" + 
    		"  max(cb.BUILDING_AREA)as BUILDING_AREA,\r\n" + 
    		"  max(cb.FLOOR_CNT)as FLOOR_CNT ,\r\n" +  
    		"  max(cb.COMPANY_NUMB)as COMPANY_NUMB ,\r\n" + 
    		"  max(cb.BUILDING_STEWARD)as BUILDING_STEWARD ,\r\n" + 
    		"  max(cb.LATITUDE) as LATITUDE,\r\n" + 
    		"  max(cb.LONGITUDE) as LONGITUDE\r\n" + 
    		"FROM C_MONITOR cm,\r\n" + 
    		"  C_BUILDING cb,\r\n" + 
    		"  C_DIAGNOSIS cd,\r\n" + 
    		"  R_DIAGNOSIS_MONITOR rdm,\r\n" + 
    		"  p_code p\r\n" + 
    		"WHERE 1             =1\r\n" + 
    		"AND cm.BUILDING_NO  =cb.BUILDING_NO\r\n" + 
    		"AND cd.DIAGNOSIS_ID =rdm.DIAGNOSIS_ID\r\n" + 
    		"AND rdm.MONITOR_ID  =cm.MONITOR_ID \r\n" + 
    		"AND p.CODE_TYPE    ='eDeviceType'\r\n" + 
    		"AND cd.DIAGNOSIS_TYPE =p.VALUE\r\n" + 
    		"and cb.BUILDING_NO=#{buildingno} \r\n" + 
    		"group by cd.DIAGNOSIS_ID  order by max(cd.CREATE_TIME) desc ")
    
    List<Map<String, Object>> getEnterprisefloor(String buildingno);
    
    @ApiOperation(value = "查询诊断点信息")
    @Select("<script>select TO_CHAR(cd.CREATE_TIME,'yyyy-mm-dd')as CREATE_TIME ,cd.DIAGNOSIS_ID,cd.DIAGNOSIS_NAME,cd.CREATOR,cd.BUILDING_NO,cd.FLOOR_NO,p.NAME as DIAGNOSIS_TYPE,cf.FLOOR_NAME "
    		+ " from  C_DIAGNOSIS cd ,p_code p, c_floor cf "
    		+ "<if test=\"buildingno != null and '' != buildingno\">where  cd.BUILDING_NO=#{buildingno} and p.CODE_TYPE='diagnosisType' and cd.DIAGNOSIS_TYPE=p.VALUE and cf.FLOOR_NO=cd.FLOOR_NO </if>"
    		+ "</script>")
    @Results(id="enterpriseDiagnosisMap", value={
    		@Result( property = "diagnosisId", column = "DIAGNOSIS_ID"),
    		@Result( property = "diagnosisName", column = "DIAGNOSIS_NAME"),
    		@Result( property = "diagnosisRuleId", column = "DIAGNOSIS_RULE_ID"),
    		@Result( property = "diagnosisType", column = "DIAGNOSIS_TYPE"),
    		@Result( property = "creator", column = "CREATOR"),
    		@Result( property = "createTime", column = "CREATE_TIME"),
    		@Result( property = "buildingNo", column = "BUILDING_NO"),
    		@Result( property = "floor", column = "FLOOR_NO",one = @One(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getfloorData"))
    		
    })
    List<CDiagnosis> getEnterpriseDiagnosis(String buildingno);
    
    @Select("select * from c_floor cf where  cf.FLOOR_NO=#{floorNo}")
    @Results(id="floordataMap",value= {
    		@Result(property = "floorNo",column ="FLOOR_NO"),
    		@Result(property = "floorName",column ="FLOOR_NAME"),
    		@Result(property = "buildingNo",column ="BUILING_NO")
    })
    List<CFloor> getfloorData(String floorNo);
    
    @ApiOperation(value = "查询楼栋检测点信息")
//    @Select("select cm.*, p.NAME as MONITOR_DESC from C_MONITOR cm,p_code p  where  cm.BUILDING_NO=#{buildingno} and p.CODE_TYPE='diagnosisType' and cm.DEVICE_TYPE=p.VALUE")
//    @Results(id="getDetectionPointMap",value= {
//    		@Result( property = "monitorId", column = "MONITOR_ID" )
//            ,@Result( property = "monitorName", column = "MONITOR_NAME" )
//            ,@Result( property = "deviceType", column = "DEVICE_TYPE" )
//            ,@Result( property = "equipAddress", column = "EQUIP_ADDRESS" )
//            ,@Result( property = "collectionRuleId", column = "COLLECTION_RULE_ID" )
//            ,@Result( property = "monitorDesc", column = "MONITOR_DESC"),
//            @Result( property = "buildingNo", column = "BUILDING_NO"),
//            @Result( property = "floorNo", column = "FLOOR_NO"),
//            @Result( property = "floorName", column = "MONITOR_ID",one = @One(select = "cn.nrzt.cps.archives.mapper.EnterpriseMapper.getfloorName")),
//            @Result( property = "roomNo", column = "ROOM_NO"),
//            @Result( property = "creator", column = "CREATOR"),
//            @Result( property = "createTime", column = "CREATE_TIME"),
//    })
    @Select("SELECT cm.MONITOR_ID,\r\n" + 
    		"  cm.MONITOR_NAME,\r\n" + 
    		"  cm.EQUIP_ADDRESS,\r\n" + 
    		"  cm.MONITOR_DESC,\r\n" + 
    		"  cm.CREATE_TIME,\r\n" + 
    		"  cm.BUILDING_NO,\r\n" + 
    		"  cb.BUILDING_NAME,\r\n" + 
    		"  cb.BUILDING_ADDRESS,\r\n" + 
    		"  cb.BUILDING_AREA,\r\n" + 
    		"  cb.FLOOR_CNT ,\r\n" + 
    		"  cb.LATITUDE,\r\n" + 
    		"  cb.LONGITUDE,\r\n" + 
    		"  p.NAME AS DEVICE_TYPE_DESC ,\r\n" + 
    		"  cf.FLOOR_NAME\r\n" + 
    		"FROM C_MONITOR cm,\r\n" + 
    		"  C_BUILDING cb,\r\n" + 
    		"  C_FLOOR cf,\r\n" + 
    		"  p_code p\r\n" + 
    		"WHERE 1            =1\r\n" + 
    		"AND cm.BUILDING_NO =cb.BUILDING_NO\r\n" + 
    		"AND cf.FLOOR_NO    =cm.FLOOR_NO\r\n" + 
    		"AND p.CODE_TYPE    ='diagnosisType'\r\n" + 
    		"AND cm.DEVICE_TYPE =p.VALUE\r\n" + 
    		"AND cb.BUILDING_NO =#{buildingno} order by cm.CREATE_TIME desc ")
    List<Map<String, Object>> getDetectionPoint(String buildingno);
    
    @Select("select FLOOR_NAME from c_floor cf where  cf.FLOOR_NO=(select FLOOR_NO from C_MONITOR where MONITOR_ID = #{monitorId})")
    @Results(id="floornameMap",value= {
    		@Result(property = "floorName",column ="FLOOR_NAME"),
    })
    String getfloorName(String monitorId);
    
    @Select("SELECT SEQ_BUILDING_NO.NEXTVAL as building_no FROM DUAL")
	int getSeq();
    
    @Insert("insert into C_BUILDING (BUILDING_NO, BUILDING_ADDRESS, BUILDING_AREA, \r\n" + 
    		"      BUILDING_NAME, FLOOR_CNT, BUILDING_TIME, BUILDING_CATEGORY,EMPLOYEES)\r\n" + 
    		"    values (#{buildingNo,jdbcType=VARCHAR},"
    		+ "#{buildingAddress,jdbcType=NVARCHAR},"
    		+ "#{buildingArea,jdbcType=INTEGER},"
    		+ "#{buildingName,jdbcType=NVARCHAR},"
    		+ "#{floorCnt,jdbcType=INTEGER},"
    		+ " to_date(#{buildingTime,jdbcType=DATE},'yyyy-mm-dd'),"
    		+ "#{building_category,jdbcType=VARCHAR},"
//    		+ "#{latitude,jdbcType=VARCHAR}"
    		+ "#{employees})")
	int addCollection(CBuilding cbuilding);
	
    
    @Delete(value = { "DELETE C_BUILDING WHERE BUILDING_NO = #{buildingNo,jdbcType=VARCHAR}" })
    int deleteCollection(String buildingNo);
    
    @Delete(value = { "DELETE R_ENTERPRISE_BUILDING WHERE BUILDING_NO = #{buildingNo,jdbcType=VARCHAR}" })
    int delete(String buildingNo);
    
    @Update("UPDATE C_BUILDING\r\n" + 
    		"SET \r\n" + 
    		"  BUILDING_ADDRESS=#{buildingAddress,jdbcType=NVARCHAR},\r\n" + 
    		"  BUILDING_AREA   =#{buildingArea,jdbcType=INTEGER},\r\n" + 
    		"  BUILDING_NAME   =#{buildingName,jdbcType=NVARCHAR},\r\n" + 
    		"  FLOOR_CNT       =#{floorCnt,jdbcType=INTEGER},\r\n" + 
    		"  BUILDING_TIME   =to_date(#{buildingTime,jdbcType=DATE},'yyyy-mm-dd'),\r\n" + 
    		"  BUILDING_CATEGORY       =#{building_category,jdbcType=VARCHAR},\r\n" + 
//    		"  EMPLOYEES        =#{latitude,jdbcType=VARCHAR},\r\n" + 
//    		"  BUILDING_STEWARD=#{buildingSteward,jdbcType=VARCHAR},\r\n" + 
    		"  EMPLOYEES    =#{employees} where BUILDING_NO=#{buildingNo,jdbcType=VARCHAR} ")
	int updateCollection(CBuilding building);
    
    @Update(" UPDATE C_ENTERPRISE ce\r\n" + 
    		"SET \r\n" + 
    		"  ce.NAME   =#{name,jdbcType=NVARCHAR},\r\n" + 
    		"  ce.ADDRESS=#{address,jdbcType=NVARCHAR},\r\n" + 
    		"  ce.ENTERPRISE_TYPE   =#{enterprise_type,jdbcType=VARCHAR},\r\n" + 
    		"  ce.EMPLOYEES       =#{employees,jdbcType=VARCHAR},\r\n" + 
    		"  ce.AREA   =#{area,jdbcType=VARCHAR},\r\n" + 
    		"  ce.PROFILE      =#{profile}\r\n" + 
    		"  where ENTERPRISE_ID=#{id,jdbcType=VARCHAR}")
	int updateCustomerData(CEnterprise enterprise);
    
    @Insert("insert into R_ENTERPRISE_BUILDING (REL_ID,ENTERPRISE_ID,BUILDING_NO)values(#{relId,jdbcType=NVARCHAR},"
    		+ "#{enterpriseId,jdbcType=NVARCHAR},#{buildingNo,jdbcType=NVARCHAR}) ")
	int addCenBuilding(CEnterpriseBuildingRel enbuilding);
    
    @Select("SELECT SEQ_REL_ID.NEXTVAL as relId FROM DUAL")
   	int getSeqRelId();
    
    @Select("SELECT *\r\n" + 
    		"FROM C_BUILDING cb,\r\n" + 
    		"  R_ENTERPRISE_BUILDING reb,\r\n" + 
    		"  C_ENTERPRISE ce\r\n" + 
    		"WHERE 1             =1\r\n" + 
    		"AND ce.ENTERPRISE_ID=reb.ENTERPRISE_ID\r\n" + 
    		"AND reb.BUILDING_NO =cb.BUILDING_NO\r\n" + 
    		"AND ce.ENTERPRISE_ID=#{enterpriseid}")
    @ResultMap("buildingResultMap")
	List<CBuilding> getManyEnterprisefloor(String enterpriseid);
//**************************************************************************************************
    @Select("select * from c_enterprise where enterprise_id=#{enterprise_id} ")
    @Results(id="enterpriseResult01"
            ,value={
            @Result( property = "id", column = "enterprise_id" )
            ,@Result( property = "address", column = "address" )
            ,@Result( property = "name", column = "name" )
    } )
    CEnterprise getCEnterpriseById(String  enterprise_id);
    
    @Select("SELECT * FROM c_floor where 1=1 and BUILING_NO = #{builingNo,jdbcType = VARCHAR} order by FLOOR_NAME")
    @Results({
    	@Result(column = "FLOOR_NO", property = "floorNo", javaType = String.class),
    	@Result(column = "FLOOR_NAME", property = "floorName", javaType = String.class),
    	@Result(column = "BUILING_NO", property = "buildingNo", javaType = String.class)
    })
	List<CFloor> getFloorRoomForBuild(String builingNo);
    
    @Select("SELECT * FROM c_room r order by ROOM_NAME")
    @Results({
    	@Result(column = "ROOM_NO", property = "roomNo", javaType = String.class),
    	@Result(column = "ROOM_NAME", property = "roomName", javaType = String.class),
    	@Result(column = "FLOOR_NO", property = "floorNo", javaType = String.class)
    })
	List<CRoom> getAllRoom();
    
    //插入楼层信息
    @Insert("insert into C_FLOOR (FLOOR_NO,FLOOR_NAME,BUILING_NO) values (#{floorNo},#{floorName},#{buildingNo})")
    @SelectKey(statement= {"select SEQ_C_DIAGNOSIS_MONITOR_REL.nextval from dual"},keyProperty="floorNo", before = true, resultType = String.class)
    int insertFloor(CFloor cFloor);
    
    //修改楼层信息
    @Update("<script>"
    		+ "update C_FLOOR set FLOOR_NAME=#{floorName} where FLOOR_NO=#{floorNo} "
    		+ "</script>")
    int updateFloor(CFloor cFloor);
    
    //删除楼层信息
    @Delete("<script>"
    		+ "delete from C_FLOOR where FLOOR_NO=#{floorNo} "
    		+ "</script>")
    int deleteFloor(String floorNo);
    
    //插入房间信息
    @Insert("insert into C_ROOM (ROOM_NO,ROOM_NAME,FLOOR_NO,CATEGORY,AREA,EMPLOYEES) values (#{roomNo},#{roomName},#{floorNo},#{category},#{area},#{employees})")
    @SelectKey(statement= {"select SEQ_C_DIAGNOSIS_MONITOR_REL.nextval from dual"},keyProperty="roomNo", before = true, resultType = String.class)
    int insertRoom(CRoom cRoom);
    
    //修改房间信息
    @Update("<script>"
    		+ "update C_ROOM set ROOM_NAME=#{roomName},CATEGORY=#{category},AREA=#{area},EMPLOYEES=#{employees} where ROOM_NO=#{roomNo} "
    		+ "</script>")
    int updateRoom(CRoom cRoom);
    
    //删除房间信息
    @Delete("<script>"
    		+ "delete from C_ROOM where ROOM_NO=#{roomNo} "
    		+ "</script>")
    int deleteRoom(String roomNo);
}