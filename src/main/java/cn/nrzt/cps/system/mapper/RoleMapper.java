package cn.nrzt.cps.system.mapper;

import cn.nrzt.cps.system.po.Authority;
import cn.nrzt.cps.system.po.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {
    @Select(value = {" <script>" +
            "  select role_id,role_name,role_code,to_char(gen_time,'YYYY-MM-DD HH24:MI:SS') gen_time from p_role <where> " +
            "  <if test='name!= null '>  role_name like '%'||#{name}||'%' </if> " +
            "  <if test='code!= null '> and  role_code like '%'||#{code}||'%' </if> " +
            " </where>  " +
            " </script>"})
    @Results(value={
            @Result( column = "role_id", property = "id" )
            ,@Result( column = "role_name", property = "name" )
            ,@Result( column = "role_code", property = "code" )
            ,@Result( column = "gen_time", property = "genTime" )
            ,@Result( column = "role_id",property = "auths",  many=@Many(select="cn.nrzt.cps.system.mapper.RoleMapper.getRoleAuths"))
    } )
    public List<Role> getRoles(Role role);

    @Select(value = {" <script>" +
            "  select * from r_role_menu where role_id=#{role_id}" +
            " </script>"})
    @Results(value={  @Result( column = "menu_id", property = "code" ) } )
    public List<Authority> getRoleAuths(String role_id);

    
    @Insert(value = {" <script>" +
            " insert into p_role (role_id,role_name,role_code,gen_time) " +
            " values (#{id,jdbcType=VARCHAR},#{name,jdbcType=VARCHAR}," +
            " #{code,jdbcType=VARCHAR},to_date(#{genTime},'YYYY-MM-DD HH24:MI:SS')) " +
            " </script>"}) 
    @SelectKey(statement= {"select SEQ_P_ROLE.nextval from dual"},keyProperty="id", before = true, resultType = String.class)
    public int createRole(Role role);

    @Update(value = {" <script>" +
            " update p_role set  role_name=#{name,jdbcType=VARCHAR},role_code=#{code,jdbcType=VARCHAR}," +
            " gen_time=to_date(#{genTime},'YYYY-MM-DD HH24:MI:SS')  " +
            " where ROLE_ID=#{id,jdbcType=VARCHAR}" +
            " </script>"})
    public int editRole(Role role);

    @Update(value = {" <script> " +
            " begin " +
            " delete from r_role_menu where role_id=#{role_id}; " +
            " <foreach collection='auths' index='index' item='auth'  open='insert all'  separator=''  close='select * from dual;' >" +
            "   into  r_role_menu (ROLE_ID,MENU_ID) values (  #{role_id,jdbcType=VARCHAR}, #{auth.code,jdbcType=VARCHAR}) " +
            " </foreach>  " +
            " end; " +
            " </script>"})
    public int distAuths(@Param("auths") LinkedHashSet<Authority> auths, @Param("role_id") String role_id);
    
    @Delete(value = {" <script>" +
            " begin" +
            " delete from p_role where role_id=#{id};" +
            " delete from r_role_menu where role_id=#{id};" +
            " end;" +
            " </script>"})
    public int delRole(String id);

    @Select(value = {" <script>" +
            " select  count(u.user_id) from r_user_role r, p_user u where u.user_id = r.user_id and r.role_id =#{id}" +
            " </script>"})
    public Integer getRoleUserCount(String id);
    
    @Select(value = {" <script>" +
            " select  u.* from r_user_role r, p_user u where u.user_id = r.user_id and r.role_id =#{id}" +
            " </script>"})
    @Results(value={
            @Result( column = "LOGIN_NAME", property = "login_name" )
            ,@Result( column = "USER_NAME", property = "user_name" )
            ,@Result( column = "PHONE_NO", property = "phone" )
            ,@Result( column = "USER_STATUS", property = "user_status" )
    } )
    public List<Map<String,String>> getRoleUsers(String id);
}
