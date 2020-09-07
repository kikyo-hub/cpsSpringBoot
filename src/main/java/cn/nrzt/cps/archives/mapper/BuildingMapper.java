package cn.nrzt.cps.archives.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import cn.nrzt.cps.archives.po.CBuilding;
import cn.nrzt.cps.archives.po.CDiagnosis;
import cn.nrzt.cps.archives.po.CMonitor;
import io.swagger.annotations.ApiOperation;

@Mapper
public interface BuildingMapper {
    @Insert("insert into C_DIAGNOSIS (DIAGNOSIS_ID,DIAGNOSIS_NAME,DIAGNOSIS_TYPE,CREATOR,CREATE_TIME,BUILDING_NO,FLOOR_NO)\r\n" + 
    		"values(#{diagnosisId,jdbcType=VARCHAR},"
    		+ "#{diagnosisName,jdbcType=NVARCHAR},"
    		+ "#{diagnosisType,jdbcType=VARCHAR},"
    		+ "#{creator,jdbcType=VARCHAR},"
    		+ "to_date(#{createTime,jdbcType=DATE},'yyyy-mm-dd'),"
    		+ "#{buildingNo,jdbcType=VARCHAR},"
    		+ "#{floorNo,jdbcType=VARCHAR}) ")
	int addDiagnosis(CDiagnosis diagnosis);
    @Select("SELECT SEQ_DIAGNOSYS_ID.NEXTVAL as DIAGNOSYS_ID FROM DUAL")
   	int getSeqDiagnosysID();
    @Update("UPDATE C_DIAGNOSIS\r\n" + 
    		"SET \r\n" + 
    		"  DIAGNOSIS_NAME=#{diagnosisName,jdbcType=NVARCHAR},\r\n" + 
    		"  DIAGNOSIS_TYPE=#{diagnosisType,jdbcType=VARCHAR},\r\n" + 
    		"  CREATOR       =#{creator,jdbcType=VARCHAR},\r\n" + 
    		"  CREATE_TIME   =to_date(#{createTime,jdbcType=DATE},'yyyy-mm-dd'),\r\n" + 
    		"  BUILDING_NO   =#{buildingNo,jdbcType=VARCHAR},\r\n" + 
    		"  FLOOR_NO      =#{floorNo,jdbcType=VARCHAR},\r\n" + 
    		"  DIAGNOSIS_DESC =#{diagnosisTypeDesc,jdbcType=VARCHAR}\r\n"+
    		"  where DIAGNOSIS_ID=#{diagnosisId,jdbcType=VARCHAR} ")
	int putDiagnosis(CDiagnosis diagnosis);
    @Delete("DELETE C_DIAGNOSIS WHERE DIAGNOSIS_ID = #{diagnosisId,jdbcType=VARCHAR}")
	int deleteDiagnosis(String  diagnosisId);
    @Insert("insert into C_MONITOR (MONITOR_ID,MONITOR_NAME,DEVICE_TYPE,EQUIP_ADDRESS,COLLECTION_RULE_ID,MONITOR_DESC,BUILDING_NO,FLOOR_NO,ROOM_NO,CREATOR,CREATE_TIME)\r\n" + 
    		"values(#{monitorId,jdbcType=VARCHAR},"
    		+ "#{monitorName,jdbcType=NVARCHAR},"
    		+ "#{deviceType,jdbcType=VARCHAR},"
    		+ "#{equipAddress,jdbcType=VARCHAR},"
    		+ "#{collectionRuleId,jdbcType=VARCHAR},"
    		+ "#{monitorDesc,jdbcType=NVARCHAR},"
    		+ "#{buildingNo,jdbcType=VARCHAR},"
    		+ "#{floorNo,jdbcType=VARCHAR},"
    		+ "#{roomNo,jdbcType=VARCHAR},"
    		+ "#{creator,jdbcType=VARCHAR},"
    		+ "to_date(#{createTime,jdbcType=DATE},'yyyy-mm-dd'))")
	int addCMonitor(CMonitor cmonitor);
    @Select("SELECT SEQ_C_MONITOR.NEXTVAL as MONITOR_ID FROM DUAL ")
    int getCmonitorID();
    @Update("UPDATE C_MONITOR\r\n" + 
    		"SET \r\n" + 
    		"  MONITOR_NAME=#{monitorName,jdbcType=NVARCHAR},\r\n" + 
    		"  DEVICE_TYPE=#{deviceType,jdbcType=VARCHAR},\r\n" + 
    		"  EQUIP_ADDRESS       =#{equipAddress,jdbcType=VARCHAR},\r\n" + 
    		"  COLLECTION_RULE_ID   =#{collectionRuleId,jdbcType=VARCHAR},\r\n" + 
    		"  MONITOR_DESC   =#{monitorDesc,jdbcType=NVARCHAR},\r\n" + 
    		"  BUILDING_NO =#{buildingNo,jdbcType=VARCHAR},\r\n" + 
    		"  FLOOR_NO      =#{floorNo,jdbcType=VARCHAR},\r\n" + 
    		"  ROOM_NO =#{roomNo,jdbcType=VARCHAR},\r\n" + 
    		"  CREATOR=#{creator,jdbcType=VARCHAR},\r\n" + 
    		"  CREATE_TIME=to_date(#{createTime,jdbcType=DATE},'yyyy-mm-dd')\r\n" + 
    		"  where MONITOR_ID=#{monitorId,jdbcType=VARCHAR}")
	int putCMonitor(CMonitor cmonitor);
    @Delete("DELETE C_MONITOR WHERE MONITOR_ID = #{monitorId,jdbcType=VARCHAR}")
	int deleteCMonitor(String monitorId);
    
    
    @ApiOperation(value = "查询楼栋诊断点信息")
    @Select(value = { "<script> select TO_CHAR(cd.CREATE_TIME,'yyyy-mm-dd')as CREATE_TIME ,cd.DIAGNOSIS_ID,cd.DIAGNOSIS_NAME,cd.DIAGNOSIS_RULE_ID,cd.DIAGNOSIS_TYPE,cd.CREATOR,cd.BUILDING_NO,cd.FLOOR_NO"
    		+ " from  C_DIAGNOSIS cd  where  1=1 "
    		+"<if test=\"buildingNo != null and '' != buildingNo\"> and cd.BUILDING_NO=#{buildingNo} </if> "
    		+"<if test=\"floorNo != null and '' != floorNo\"> and cd.FLOOR_NO=#{floorNo} </if> "
    		+ "</script>"})
    @Results(id="getdiagbisisMap", value={
    		@Result( property = "diagnosisId", column = "DIAGNOSIS_ID"),
    		@Result( property = "diagnosisName", column = "DIAGNOSIS_NAME"),
    		@Result( property = "diagnosisRuleId", column = "DIAGNOSIS_RULE_ID"),
    		@Result( property = "diagnosisType", column = "DIAGNOSIS_TYPE"),
    		@Result( property = "creator", column = "CREATOR"),
    		@Result( property = "createTime", column = "CREATE_TIME"),
    		@Result( property = "buildingNo", column = "BUILDING_NO"),
    		@Result( property = "floorNo", column = "FLOOR_NO"),
            @Result( property = "floorName", column = "FLOOR_NO",one = @One(select = "cn.nrzt.cps.archives.mapper.BuildingMapper.getfloorData")),
    		@Result(property = "diagnosisTypeDesc",column = "DIAGNOSIS_DESC")
    		
    })
	List<CDiagnosis> selectDiagnosis(String floorNo, String buildingNo);
    
    @Select("select FLOOR_NAME from c_floor cf where  cf.FLOOR_NO=(select FLOOR_NO from C_DIAGNOSIS where FLOOR_NO = #{floorNo})")
    @Results(id="getfloordataMap",value= {
    		@Result(property = "floorName",column ="FLOOR_NAME"),
    })
    String getfloorData(String floorNo);
    
    
    @ApiOperation(value = "查询楼栋检测点信息")
    @Select(value = {"<script> select * from  C_MONITOR cm   where 1=1 "
    		+"<if test=\"buildingNo != null and '' != buildingNo\"> and cm.BUILDING_NO=#{buildingNo} </if> "
    		+"<if test=\"floorNo != null and '' != floorNo\"> and cm.FLOOR_NO=#{floorNo} </if> "
    		+ "</script>"})
    @Results(id="getCmonitorMap",value= {
    		@Result( property = "monitorId", column = "MONITOR_ID" )
            ,@Result( property = "monitorName", column = "MONITOR_NAME" )
            ,@Result( property = "deviceType", column = "DEVICE_TYPE" )
            ,@Result( property = "equipAddress", column = "EQUIP_ADDRESS" )
            ,@Result( property = "collectionRuleId", column = "COLLECTION_RULE_ID" )
            ,@Result( property = "monitorDesc", column = "MONITOR_DESC"),
            @Result( property = "buildingNo", column = "BUILDING_NO"),
            @Result( property = "floorNo", column = "FLOOR_NO"),
            @Result( property = "floorName", column = "MONITOR_ID",one = @One(select = "cn.nrzt.cps.archives.mapper.BuildingMapper.getfloorName")),
            @Result( property = "roomNo", column = "ROOM_NO"),
            @Result( property = "creator", column = "CREATOR"),
            @Result( property = "createTime", column = "CREATE_TIME"),
    })
	List<CMonitor> selectCMonitor(String floorNo, String buildingNo);
    
    @Select("select FLOOR_NAME from c_floor cf where  cf.FLOOR_NO=(select FLOOR_NO from C_MONITOR where MONITOR_ID = #{monitorId})")
    @Results(id="getfloornameMap",value= {
    		@Result(property = "floorName",column ="FLOOR_NAME"),
    })
    String getfloorName(String monitorId);
    
    
    //查询楼栋名称
    @Select("select building_name from C_BUILDING where BUILDING_NO = #{building_no}")
    @Results(value= {
    		// building_name映射buildingNamename不必对应实体类
    		@Result(property = "buildingNamename",column ="building_name"),
    })
    String getBuildingName(String building_no);
    
    // 查询采集规则名称
    @Select("select RULE_NAME from C_COLLECTION_RULE where COLLECTION_RULE_ID = #{collection_rule_id}")
    @Results(value= {
    		@Result(property = "ruleName",column ="RULE_NAME"),
    })
    String getCollectRuleName(String collection_rule_id);
//*****************************************************************************************
	/**
	 *
	 * @param building_no
	 * @return
	 * @Author: wdp
	 */
	@Select("<script>" +
			" select b.*,r.enterprise_id from c_building b,R_ENTERPRISE_BUILDING r " +
			" where b.BUILDING_NO=r.BUILDING_NO and  b.building_no=#{building_no} " +
			" </script>")
	@Results(id="buildingResult01"
			,value={
			@Result( property = "buildingNo", column = "building_no" )
			,@Result( property = "buildingAddress", column = "building_address" )
			,@Result( property = "buildingArea", column = "building_area" )
			,@Result( property = "buildingName", column = "building_name" )
			,@Result( property = "floorCnt", column = "floor_cnt" )
			,@Result( property = "enterprise", column = "enterprise_id", one = @One(select="cn.nrzt.cps.archives.mapper.EnterpriseMapper.getEnterpriseDiagnosis"))
	} )
	CBuilding getCBuildingById(String building_no);
	
	
	@Select("<script>select * from  C_BUILDING cb  <if test=\"buildingNo != null and '' != buildingNo\"> where  cb.building_no=#{buildingNo} </if> "
			+ "  order by  cb.building_no asc </script>")
	 @Results(id="getbuildingResultMap",value= {
	    		@Result( property = "buildingNo", column = "building_no" )
	            ,@Result( property = "buildingAddress", column = "building_address" )
	            ,@Result( property = "buildingArea", column = "building_area" )
	            ,@Result( property = "buildingName", column = "building_name" )
	            ,@Result( property = "floorCnt", column = "floor_cnt" )
	            ,@Result( property = "buildingTime", column = "BUILDING_TIME"),
	            @Result( property = "longitude", column = "LONGITUDE"),
	            @Result( property = "latitude", column = "LATITUDE"),
	            @Result( property = "buildingSteward", column = "BUILDING_STEWARD"),
	            @Result( property = "companyNumb", column = "COMPANY_NUMB")})
	List<CBuilding> selectBuildingAllData(String buildingNo);
}
