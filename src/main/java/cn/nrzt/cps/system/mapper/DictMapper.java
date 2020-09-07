package cn.nrzt.cps.system.mapper;

import cn.nrzt.cps.system.po.Org;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DictMapper {
    @Select(" <script>" +
            " select s.*,0 as \"level\"  from p_code_sort s "+
            " </script> ")
    @Results(id = "dictMap",
            value = {
                    @Result(column = "CODE_SORT_ID", property = "key", javaType = String.class),
                    @Result(column = "NAME", property = "title", javaType = String.class),
                    @Result(column = "MAINT_TYPE_CODE", property = "type_code", javaType = String.class),
                    @Result(column = "MAINT_ORG_NO", property = "org_no", javaType = String.class),
                    @Result(column = "VN", property = "vn", javaType = String.class),
                    @Result(column = "CODE_TYPE", property = "code_type", javaType = String.class),
                    @Result(column = "VALID_FLAG", property = "valid_flag", javaType = String.class),
                    @Result(column = "EFFECT_DATE", property = "effect_date", javaType = String.class),
                    @Result(column = "level", property = "level", javaType = String.class),
                    @Result(column = "CODE_TYPE", property = "children",  one = @One(select="cn.nrzt.cps.system.mapper.DictMapper.selectDictitems"))
            })
    List<Map<String,Object>> selectDicts();

    @Select(" <script>" +
            " select c.*,1 as \"level\" from p_code c where c.code_type=#{code_type}"+
            " </script> ")
    @Results(id = "dictDetailsMap",
            value = {
                    @Result(column = "CODE_ID", property = "key", javaType = String.class),
                    @Result(column = "CODE_SORT_ID", property = "sort_id", javaType = String.class),
                    @Result(column = "P_CODE", property = "p_code", javaType = String.class),
                    @Result(column = "CODE_TYPE", property = "code_type", javaType = String.class),
                    @Result(column = "ORG_NO", property = "org_no", javaType = String.class),
                    @Result(column = "VALUE", property = "value", javaType = String.class),
                    @Result(column = "NAME", property = "title", javaType = String.class),
                    @Result(column = "DISP_SN", property = "disp_sn", javaType = String.class),
                    @Result(column = "CONTENT1", property = "content1", javaType = String.class),
                    @Result(column = "CONTENT2", property = "content2", javaType = String.class),
                    @Result(column = "level", property = "level", javaType = String.class),
            })
    List<Map<String,Object>> selectDictitems(@Param("CODE_TYPE")String code_type);
    @Delete(" <script>" +
            " delete from P_CODE_SORT where CODE_SORT_ID=#{key}" +
            " </script> ")
    public int deleteSort(Map<String, Object> sort);
    @Delete(" <script>" +
            " delete from P_CODE where CODE_ID =#{key}" +
            " </script> ")
    public int deleteCode(Map<String, Object> code);
    @Update(" <script>" +
            " update P_CODE_SORT set NAME=#{title}, CODE_TYPE=#{code_type}," +
            " MAINT_TYPE_CODE=#{type_code},VN=#{vn},VALID_FLAG=#{valid_flag}," +
            " EFFECT_DATE=to_date(#{effect_date},'YYYY-MM-DD HH24:MI:SS') " +
            " where CODE_SORT_ID=#{key}" +
            " </script> ")
    public int updateSort(Map<String, Object> dict);
    @Update(" <script>" +
            " update P_CODE set CODE_TYPE=#{code_type},VALUE=#{value},NAME=#{title},DISP_SN=#{disp_sn},CONTENT1=#{content1}" +
            " where CODE_ID=#{key}"  +
            " </script> ")
    public int updateCode(Map<String, Object> dict);
    @Insert(" <script>" +
            " insert into P_CODE_SORT (CODE_SORT_ID,NAME,CODE_TYPE,MAINT_TYPE_CODE,VN,VALID_FLAG,EFFECT_DATE)" +
            " values(#{key},#{title},#{code_type},#{type_code},#{vn},#{valid_flag}," +
            " to_date(#{effect_date},'YYYY-MM-DD HH24:MI:SS')) " +
            " </script> ")
    @SelectKey(statement= {"select SEQ_P_CODE_SORT.nextval from dual"},keyProperty="key", before = true, resultType = String.class)
    int insertSort(Map<String, Object> dict);

    @Insert(" <script>" +
            " insert into P_CODE (CODE_ID,CODE_SORT_ID,CODE_TYPE,VALUE,NAME,DISP_SN,CONTENT1)" +
            " values(#{key},#{sort_id},#{code_type},#{value},#{title},#{disp_sn},#{content1}) " +
            " </script> ")
    @SelectKey(statement= {"select SEQ_P_CODE.nextval from dual"},keyProperty="key", before = true, resultType = String.class)
    int insertCode(Map<String, Object> dict);
}
