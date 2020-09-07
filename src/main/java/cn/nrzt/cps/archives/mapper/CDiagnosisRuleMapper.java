package cn.nrzt.cps.archives.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import cn.nrzt.cps.archives.po.CDiagnosisRule;
import cn.nrzt.cps.archives.po.CDiagnosisRuleDetail;

@Mapper
@Repository
public interface CDiagnosisRuleMapper {
	
	//查询诊断规则
	@Select(value = { "SELECT * FROM C_DIAGNOSIS_RULE"})
	@Results(id = "DiagnosisRuleMap", value = { @Result(column = "DIAGNOSIS_RULE_ID", property = "diagnosisRuleId", javaType = String.class),
			@Result(column = "DIAGNOSIS_NAME", property = "diagnosisName", javaType = String.class),
			@Result(column = "DIAGNOSIS_TYPE", property = "diagnosisType", javaType = String.class),
//			@Result(column = "CALC_MODE",property = "calcMode"),
			@Result(column = "DESCRIPTION",property = "description"),
			@Result(column = "EXPR",property = "expr"),
			@Result(column = "CALC_PERIOD_TYPE",property = "calcPeriodType"),
			@Result(column = "CALC_INTERVAL",property = "calcInterval"),
			@Result(column = "DIAGNOSIS_RULE_ID",property = "cDiagnosisRuleDetails",many = @Many(select = "cn.nrzt.cps.archives.mapper.CDiagnosisRuleDetailMapper.getCDiagnosisRuleDetails"))
	})
	List<CDiagnosisRule> getAll();
	
	//根据诊断规则id查询诊断规则细节id
	@Select(value = {"<script>"
			+ "select DIAGNOSIS_RULE_DETAIL_ID from C_DIAGNOSIS_RULE_DETAIL where DIAGNOSIS_RULE_ID=#{DIAGNOSIS_RULE_ID}"
			+ "</script>"})
	List<String> getDetailId(String DIAGNOSIS_RULE_ID);
	
	//插入诊断规则
	@Insert({"<script>"
			+ "insert into C_DIAGNOSIS_RULE(DIAGNOSIS_RULE_ID,DIAGNOSIS_NAME,DIAGNOSIS_TYPE,DESCRIPTION,CALC_PERIOD_TYPE,CALC_INTERVAL)"
			+ "values(#{diagnosisRuleId},#{diagnosisName},#{diagnosisType},#{description},#{calcPeriodType},#{calcInterval})"
			+ "</script>"})
	@SelectKey(statement= {"select C_EQUIP_SEQ.nextval from dual"},keyProperty="diagnosisRuleId", before = true, resultType = String.class)
	int insert(CDiagnosisRule cDiagnosisRule);
	
	//插入诊断规则细节
	@Insert({"<script>"
			+ "insert into C_DIAGNOSIS_RULE_DETAIL(DIAGNOSIS_RULE_ID,DATA_TYPE,CALC_MODE,DATA_ID)"
			+ "values(#{diagnosisRuleId},#{dataType},#{calcMode},#{dataId})"
			+ "</script>"})
//	@SelectKey(statement= {"select C_EQUIP_SEQ.nextval from dual"},keyProperty="diagnosisRuleDetailId", before = true, resultType = String.class)
	int insertDetail(CDiagnosisRuleDetail cDiagnosisRuleDetail);
	
	//插入关联表
	@Insert({"<script>"
			+ "insert into R_DIAGNOSIS_DETAIL_DATAID(REL_ID,DIAGNOSIS_RULE_DETAIL_ID,DATA_ID) values(#{REL_ID},#{DIAGNOSIS_RULE_DETAIL_ID},#{DATA_ID})"
			+ "</script>"})
	@SelectKey(statement= {"select C_EQUIP_SEQ.nextval from dual"},keyProperty="REL_ID", before = true, resultType = String.class)
	int insertDataId(@Param("DIAGNOSIS_RULE_DETAIL_ID")String DIAGNOSIS_RULE_DETAIL_ID,@Param("DATA_ID")String DATA_ID);
	
	//删除诊断规则
	@Delete({"<script>"
			+ "delete from C_DIAGNOSIS_RULE where DIAGNOSIS_RULE_ID=#{DIAGNOSIS_RULE_ID}"
			+ "</script>"})
	int delete(String DIAGNOSIS_RULE_ID);
	
	//删除诊断规则细节
	@Delete({"<script>"
			+ "delete from C_DIAGNOSIS_RULE_DETAIL where DIAGNOSIS_RULE_ID=#{DIAGNOSIS_RULE_ID}"
			+ "</script>"})
	int deleteDetail(String DIAGNOSIS_RULE_ID);
	
	//删除关联表
	@Delete({"<script>"
			+ "delete from R_DIAGNOSIS_DETAIL_DATAID where DIAGNOSIS_RULE_DETAIL_ID=#{DIAGNOSIS_RULE_DETAIL_ID}"
			+ "</script>"})
	int deletedataId(String DIAGNOSIS_RULE_DETAIL_ID);
	
	//更新诊断规则
	@Update({"<script>"
			+ "update C_DIAGNOSIS_RULE set DIAGNOSIS_NAME=#{diagnosisName},DIAGNOSIS_TYPE=#{diagnosisType},"
			+ "DESCRIPTION=#{description},CALC_PERIOD_TYPE=#{calcPeriodType},CALC_INTERVAL=#{calcInterval} where DIAGNOSIS_RULE_ID=#{diagnosisRuleId}"
			+ "</script>"})
	int updatebyID(CDiagnosisRule cDiagnosisRule);
}
