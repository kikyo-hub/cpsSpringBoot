package cn.nrzt.cps.archives.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.nrzt.cps.archives.po.CDataId;

@Mapper
@Repository("CDataIDMapper")
public interface CDataIDMapper {

	@Select("<script>"
			+ "select * from C_DATAID  where data_id in (select data_id from r_collection_dataid where collection_rule_id=#{collection_rule_id})"
			+ "</script>")
	@Results(value = {
			@Result(property = "dataid",column = "data_id")
			,@Result(property = "datatype",column = "id_type")
			,@Result(property = "dataname",column = "id_name")
	})
	List<CDataId> getCDataIds(String collection_rule_id);
	
	
	@Select("<script>"
			+ "select * from C_DATAID  where data_id in (select data_id from r_diagnosis_data_id where DIAGNOSIS_RULE_ID=#{DIAGNOSIS_RULE_ID})"
			+ "</script>")
	@Results(value = {
			@Result(property = "dataid",column = "data_id")
			,@Result(property = "datatype",column = "id_type")
			,@Result(property = "dataname",column = "id_name")
	})
	List<CDataId> getCDataByDia(String DIAGNOSIS_RULE_ID);
	
	@Select("<script>"
			+ "select * from C_DATAID  where data_id = #{dataId}"
			+ "</script>")
	@Results(value = {
			@Result(property = "dataid",column = "data_id")
			,@Result(property = "datatype",column = "id_type")
			,@Result(property = "dataname",column = "id_name")
	})
	List<CDataId> getCDataByDiaDetail(String dataId);
	
	
	@Select("<script>"
			+ "select * from C_DATAID"
			+ "</script>")
	@Results(value = {
			@Result(property = "dataid",column = "data_id")
			,@Result(property = "datatype",column = "id_type")
			,@Result(property = "dataname",column = "id_name")
	})
	List<CDataId> getallCDataIds();
}
