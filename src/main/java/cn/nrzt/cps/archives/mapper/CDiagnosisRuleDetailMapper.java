package cn.nrzt.cps.archives.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import cn.nrzt.cps.archives.po.CDiagnosisRuleDetail;

@Mapper
public interface CDiagnosisRuleDetailMapper {

	@Select({"<script>"
			+ "select * from C_DIAGNOSIS_RULE_DETAIL where DIAGNOSIS_RULE_ID=#{diagnosis_rule_id}"
			+ "</script>"})
	 @Results(value = {@Result(column = "DIAGNOSIS_RULE_ID",property = "diagnosisRuleId")
	,@Result(column = "DATA_TYPE",property = "dataType")
	,@Result(column = "CALC_MODE",property = "calcMode")
	,@Result(column = "EXPR",property = "expr")
	,@Result(column = "DATA_ID",property = "dataId")
	,@Result(column = "DATA_ID",property = "cDataIds",many = @Many(select = "cn.nrzt.cps.archives.mapper.CDataIDMapper.getCDataByDiaDetail"))
//	,@Result(column = "DIAGNOSIS_RULE_DETAIL_ID",property = "cDataIds",many = @Many(select = "cn.nrzt.cps.archives.mapper.CDataIDMapper.getCDataByDiaDetail"))
	 })
	List<CDiagnosisRuleDetail> getCDiagnosisRuleDetails(String diagnosis_rule_id);
}
