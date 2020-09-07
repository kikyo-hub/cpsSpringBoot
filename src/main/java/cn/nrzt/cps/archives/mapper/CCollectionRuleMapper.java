package cn.nrzt.cps.archives.mapper;

import cn.nrzt.cps.archives.po.CCollectionRule;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CCollectionRuleMapper {
	//查询采集规则
	@Select(value = { "SELECT COLLECTION_RULE_ID,RULE_NAME,COLLECTION_TYPE,COLLECTION_INTERVAL,TO_CHAR(START_TIME,'yyyy-mm-dd hh24:mi:ss')as START_TIME,"
			+ "TO_CHAR(END_TIME,'yyyy-mm-dd hh24:mi:ss')as END_TIME,RUNNING_PERIOD,COLLECTION_MODE,"
			+ "SAVE_TIME_FLAG,RULE_DESC,COLLECTION_RULE_NO FROM C_COLLECTION_RULE order by COLLECTION_RULE_NO ASC"})
	@Results(id = "CollectionRuleMap", value = { @Result(column = "COLLECTION_RULE_ID", property = "collectionRuleId", javaType = Integer.class),
			@Result(column = "RULE_NAME", property = "ruleName", javaType = Object.class),
			@Result(column = "COLLECTION_TYPE", property = "collectionType", javaType = String.class),
			@Result(column = "COLLECTION_INTERVAL", property = "collectionInterval", javaType = Integer.class),
			@Result(column = "START_TIME", property = "startTime", javaType = String.class),
			@Result(column = "END_TIME", property = "endTime", javaType = String.class),
			@Result(column = "RUNNING_PERIOD", property = "runningPeriod", javaType = String.class),
			@Result(column = "COLLECTION_MODE", property = "collectionMode", javaType = String.class),
			@Result(column = "SAVE_TIME_FLAG", property = "saveTimeFlag", javaType = String.class),
			@Result(column = "RULE_DESC", property = "ruleDesc", javaType = String.class),
			@Result(column = "COLLECTION_RULE_NO",property = "collectionRuleNo", javaType = String.class),
			@Result(column = "COLLECTION_RULE_ID",property = "cDataIds",many = @Many(select = "cn.nrzt.cps.archives.mapper.CDataIDMapper.getCDataIds"))
	})
	List<CCollectionRule> getAll();
	
	//更新采集规则
	@Update({"<script> update C_COLLECTION_RULE set RULE_NAME=#{ruleName},COLLECTION_TYPE=#{collectionType},COLLECTION_INTERVAL=#{collectionInterval},"
			+ "START_TIME=to_date(#{startTime},'yyyy-mm-dd hh24:mi:ss'),END_TIME=to_date(#{endTime},'yyyy-mm-dd hh24:mi:ss'),COLLECTION_MODE=#{collectionMode},SAVE_TIME_FLAG=#{saveTimeFlag},RULE_DESC=#{ruleDesc},"
			+ "COLLECTION_RULE_NO=#{collectionRuleNo} "
			+ "where COLLECTION_RULE_ID=#{collectionRuleId}</script>"})
	int updatebyID(CCollectionRule cCollectionRule);
	
	//删除采集规则
	@Delete({"<script>"
			+ "delete from C_COLLECTION_RULE where COLLECTION_RULE_ID=#{collectionRuleId}"
			+ "</script>"})
	int deletebyID(Integer collectionRuleId);
	
	//删除采集规则关联表
	@Delete({"<script>"
			+ "delete from r_collection_dataid where COLLECTION_RULE_ID=#{collectionRuleId}"
			+ "</script>"})
	int deletedataId(Integer collectionRuleId);
	
	//插入采集规则
	@Insert({"<script>"
			+ "insert into C_COLLECTION_RULE(COLLECTION_RULE_ID,RULE_NAME,COLLECTION_TYPE,COLLECTION_INTERVAL,START_TIME,END_TIME"
			+ ",RUNNING_PERIOD,COLLECTION_MODE,SAVE_TIME_FLAG,RULE_DESC,COLLECTION_RULE_NO) values (#{collectionRuleId},#{ruleName},#{collectionType},#{collectionInterval}"
			+ ",to_date(#{startTime},'yyyy-mm-dd hh24:mi:ss'),to_date(#{endTime},'yyyy-mm-dd hh24:mi:ss'),#{runningPeriod},#{collectionMode},#{saveTimeFlag},#{ruleDesc},#{collectionRuleNo})"
			+ "</script>"})
	@SelectKey(statement= {"select C_EQUIP_SEQ.nextval from dual"},keyProperty="collectionRuleId", before = true, resultType = Integer.class)
	int inster(CCollectionRule cCollectionRule);
	
	//插入关联表
	@Insert({"<script>"
			+ "insert into r_collection_dataid(REL_ID,COLLECTION_RULE_ID,DATA_ID) values (#{REL_ID},#{COLLECTION_RULE_ID},#{DATA_ID})"
			+ "</script>"})
	@SelectKey(statement= {"select C_EQUIP_SEQ.nextval from dual"},keyProperty="REL_ID", before = true, resultType = Integer.class)
	int insertIddata(@Param("COLLECTION_RULE_ID")Integer COLLECTION_RULE_ID,@Param("DATA_ID")String DATA_ID);
	
	
	@Select(value = "SELECT * FROM c_collection_rule")
	List<Map<String, Object>> getCollectionAllSelect();
//***************************************************************************************************
	@Select("select * from c_collection_rule where collection_rule_id=#{collection_rule_id}")
	@Results(id="collectionRuleResult01"
			,value={
			@Result( property = "collectionRuleId", column = "collection_rule_id" )
			,@Result( property = "ruleName", column = "rule_name" )
			,@Result( property = "collectionType", column = "collection_type" )
			,@Result( property = "collectionInterval", column = "collection_interval" )
			,@Result( property = "startTime", column = "start_time" )
			,@Result( property = "endTime", column = "end_time" )
			,@Result( property = "runningPeriod", column = "running_period" )
			,@Result( property = "collectionMode", column = "collection_mode" )
			,@Result( property = "saveTimeFlag", column = "save_time_flag" )
			,@Result( property = "ruleDesc", column = "rule_desc" )

	} )
	CCollectionRule getCCollectionRuleById(String collection_rule_id);
	

	// 查询采集规则编号
	@Select(value = {"select COLLECTION_RULE_NO from C_COLLECTION_RULE  order by COLLECTION_RULE_NO ASC"})
	List<String> getCollection();
}

