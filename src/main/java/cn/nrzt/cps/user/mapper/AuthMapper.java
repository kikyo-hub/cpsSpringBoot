package cn.nrzt.cps.user.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import cn.nrzt.cps.user.po.Auth;

@Mapper
public interface AuthMapper {
	@Select(" select * from p_user pu where pu.LOGIN_NAME=#{username} and pu.LOGIN_PASSWORD=#{password}")
	@Results({
		@Result(property = "userid",  column = "USER_ID", javaType = String.class),
		@Result(property = "token",  column = "LOGIN_PASSWORD", javaType = String.class)
	})
	public Auth userLogin(String username,String password);
}
