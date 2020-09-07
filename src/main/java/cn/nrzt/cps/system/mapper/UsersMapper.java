package cn.nrzt.cps.system.mapper;

import cn.nrzt.cps.system.po.Authority;
import cn.nrzt.cps.system.po.Role;
import cn.nrzt.cps.system.po.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashSet;
import java.util.List;

@Repository
public interface UsersMapper {
    @Select(value = {" <script>" +
            " select * from P_USER <where> " +
            "  <if test='loginName!= null '>  LOGIN_NAME like '%'||#{loginName}||'%' </if> " +
            "  <if test='userName!= null '> and  USER_NAME like '%'||#{userName}||'%' </if> " +
            "  <if test='phone!= null '> and  PHONE_NO like '%'||#{phone}||'%' </if> " +
            "  <if test='orgId!= null '> and ORG_ID like '%'||#{orgId}||'%' </if> " +
            "  <if test='status!= null '> and USER_STATUS like '%'||#{status}||'%' </if> " +
            " </where>  " +
            " </script>"})
    @Results(value={
            @Result( column = "USER_ID", property = "id" )
            ,@Result( column = "LOGIN_NAME", property = "loginName" )
            ,@Result( column = "LOGIN_PASSWORD", property = "loginPasswd" )
            ,@Result( column = "USER_NAME", property = "userName" )
            ,@Result( column = "PHONE_NO", property = "phone" )
            ,@Result( column = "USER_STATUS", property = "status" )
            ,@Result( column = "ORG_ID", property = "orgId" )
            ,@Result( column = "USER_ID",property = "roles",  many=@Many(select="cn.nrzt.cps.system.mapper.UsersMapper.usersRoles"))
            ,@Result( column = "USER_ID",property = "auths",  many=@Many(select="cn.nrzt.cps.system.mapper.UsersMapper.usersAuths"))
    } )
    public List<Users> getUsers(Users users);
    

    @Insert(value = {" <script>" +
            " insert into P_USER (USER_ID,LOGIN_NAME,LOGIN_PASSWORD,USER_NAME,PHONE_NO,USER_STATUS,ORG_ID) " +
            " values (#{id,jdbcType=VARCHAR},#{loginName,jdbcType=VARCHAR},#{loginPasswd,jdbcType=VARCHAR}," +
            " #{userName,jdbcType=VARCHAR},#{phone,jdbcType=NUMERIC},#{status,jdbcType=NUMERIC},#{orgId,jdbcType=VARCHAR}) " +
            " </script>"}) 
    @SelectKey(statement= {"select SEQ_P_USER.nextval from dual"},keyProperty="id", before = true, resultType = String.class)
    public int createUsers(Users users);


    @Update(value = {" <script>" +
            " update P_USER set USER_STATUS=#{status,jdbcType=NUMERIC}  where USER_ID=#{id,jdbcType=VARCHAR}" +
            " </script>"})
    public int editUsers(Users users);
    

    @Delete(value = {" <script>" +
            " delete from P_USER where USER_ID in " +
            " <foreach collection='ids' index='index' item='item'  open='('  separator=','  close=')' >" +
            " #{item}" +
            " </foreach>  "+
            " </script>"})
    public int delUsers(@Param("ids") List<String> ids);

    //  获取用户权限
    @Select(value = {" <script>" +
            " select distinct * from r_role_menu where role_id in (select  role_id from r_user_role where user_id =#{user_id} )" +
            " </script>"})
    @Results(value={  @Result( column = "menu_id", property = "code" ) } )
    public List<Authority> usersAuths(String user_id);

    //  获取用户角色
    @Select(value = {" <script>" +
            " select * from p_role where role_id in (select  role_id from r_user_role where user_id =#{user_id} )" +
            " </script>"})
    @Results(value={
            @Result( column = "role_id", property = "id" )
            ,@Result( column = "role_name", property = "name" )
            ,@Result( column = "role_code", property = "code" )
            ,@Result( column = "gen_time", property = "genTime" )
            ,@Result( column = "role_id",property = "auths",  one = @One(select="cn.nrzt.cps.system.mapper.RoleMapper.getRoleAuths"))
    } )
    public List<Role> usersRoles(String user_id);

    // 分 配 角 色
    @Update(value = {" <script>" +
            " begin " +
            " delete from r_user_role where user_id=#{user_id}; " +
            " <foreach collection='roles' index='index' item='role'  open='insert all'  separator=''  close='select * from dual;' >" +
            "   into  r_user_role (user_id, role_id) values (#{user_id,jdbcType=VARCHAR},#{role.id,jdbcType=VARCHAR}) " +
            " </foreach>  " +
            " end; " +
        " </script>"})
    public int distRoles(@Param("roles") LinkedHashSet<Role> roles, @Param("user_id") String user_id);
    @Select(value = {" <script>" +
            "select * from p_role " +
            " </script>"})
    @Results(value={
            @Result( column = "role_id", property = "id" )
            ,@Result( column = "role_name", property = "name" )
            ,@Result( column = "role_code", property = "code" )
            ,@Result( column = "gen_time", property = "genTime" )
    } )
    public List<Role> getRoles();

}
