package cn.nrzt.cps.system.mapper;

import cn.nrzt.cps.system.po.Org;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrgMapper {
    @Select(value = {" <script>" +
            " select * from P_ORG <where> " +
            "  <if test='name!= null '>  org_name like '%'||#{name}||'%' </if> " +
            "  <if test='addr!= null '> and  org_addr like '%'||#{addr}||'%' </if> " +
            "  <if test='contacts!= null '> and  contacts like '%'||#{contacts}||'%' </if> " +
            "  <if test='tel!= null '> and tel like '%'||#{tel}||'%' </if> " +
            "  <if test='type!= null '> and org_type like '%'||#{type}||'%' </if> " +
            " </where>  " +
            " </script>"})
    @Results(value={
            @Result( column = "org_id", property = "id" )
            ,@Result( column = "org_name", property = "name" )
            ,@Result( column = "org_addr", property = "addr" )
            ,@Result( column = "contacts", property = "contacts" )
            ,@Result( column = "tel", property = "tel" )
            ,@Result( column = "org_type", property = "type" )
    } )
    public List<Org> getOrgDatas(Org org);
    
    @Select(value = {" <script>" +
            " select * from p_code where code_type='enterpriseType'" +
            " </script>"})
    @Results(value={
            @Result( column = "code_id", property = "id" )
            ,@Result( column = "code_sort_id", property = "sort_id" )
            ,@Result( column = "code_type", property = "type" )
            ,@Result( column = "value", property = "value" )
            ,@Result( column = "name", property = "name" )
            ,@Result( column = "content1", property = "content1" )
    } )
    public List<Map<String,String>> getOrgTypes();
    
    @Insert(value = {" <script>" +
            " insert into p_org (org_id,org_type,org_name,org_addr,contacts,tel) " +
            " values (#{id,jdbcType=VARCHAR},#{type,jdbcType=VARCHAR},#{name,jdbcType=VARCHAR}," +
            " #{addr,jdbcType=VARCHAR},#{contacts,jdbcType=VARCHAR},#{tel,jdbcType=VARCHAR}) " +
            " </script>"}) 
    @SelectKey(statement= {"select SEQ_P_ORG.nextval from dual"},keyProperty="id", before = true, resultType = String.class)
    public int createOrg(Org org);
    @Update(value = {" <script>" +
            " update p_org set org_type=#{type,jdbcType=VARCHAR}, org_name=#{name,jdbcType=VARCHAR}," +
            " org_addr=#{addr,jdbcType=VARCHAR},contacts=#{contacts,jdbcType=VARCHAR}," +
            " tel=#{tel,jdbcType=VARCHAR}  where org_id=#{id,jdbcType=VARCHAR}" +
            " </script>"})
    public int editOrg(Org org);
    
    @Delete(value = {" <script>" +
            " delete from p_org where org_id=#{id}" +
            " </script>"})
    public int delOrg(String id);
    
    @Select(value = {" <script>" +
            " select * from p_user where org_id=#{id}" +
            " </script>"})
    @Results(value={
            @Result( column = "LOGIN_NAME", property = "login_name" )
            ,@Result( column = "USER_NAME", property = "user_name" )
            ,@Result( column = "PHONE_NO", property = "phone" )
            ,@Result( column = "USER_STATUS", property = "user_status" )
    } )
    public List<Map<String,String>> getOrgUsers(String id);
}
