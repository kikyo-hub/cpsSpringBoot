package cn.nrzt.cps.user.mapper;

import cn.nrzt.cps.user.po.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	List<Map<String, Object>> findUserRole(String userId);	
	List<Map<String, Object>> findUserFatherMenu(String roleId);
	List<Map<String, Object>> findUserChildMenu(String roleId);
	List<Map<String, Object>> findUser03Menu(String roleId);

	@Select("select * from p_user where user_id=#{user_id}")
	@Results(id="userResult01"
			,value={
			@Result( property = "userId", column = "user_id" )
			,@Result( property = "userName", column = "user_name" )
			,@Result( property = "loginName", column = "login_name" )
			,@Result( property = "phnoeNo", column = "phnoe_no" )
			,@Result( property = "userStatus", column = "user_status" )
			,@Result( property = "enterpriseId", column = "enterprise_id" )
	} )
	User getUserById(String user_id);
}
