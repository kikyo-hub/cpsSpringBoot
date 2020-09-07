package cn.nrzt.cps.system.service;

import cn.nrzt.cps.system.mapper.RoleMapper;
import cn.nrzt.cps.system.mapper.UsersMapper;
import cn.nrzt.cps.system.po.Authority;
import cn.nrzt.cps.system.po.Role;
import cn.nrzt.cps.system.po.Users;
import cn.nrzt.cps.web.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {
    @Autowired
	UsersMapper usersMapper;

	public PageResult<Users> getUsers(Users queryParam, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Users> list = usersMapper.getUsers(queryParam);
		PageResult<Users> pages= new PageResult<Users>(list);
		return pages;

	}

	public Exception delUsers(List<String> ids) {
		int count=0;
		count += usersMapper.delUsers(ids);
		return null;
	}

	public int updateUsers(Users users) {
		int count=0;
		if(users==null) return 0 ;

		if(users.getId()==null || "".equals(users.getId())) {
			count += usersMapper.createUsers(users);
		}else {
			count += usersMapper.editUsers(users);
		}
		if(users.getRoles() !=null && users.getRoles().size()>0) {
			count += usersMapper.distRoles(users.getRoles(),users.getId());
		}
		return count;
	}

	@Transactional
	public int distRoles(Users user){
		int count=0;
		if(user==null || user.getRoles() ==null || user.getRoles().size()<=0) return 0;
		count += usersMapper.distRoles(user.getRoles(),user.getId());
		return count;
	}

	public List<Role> getRoles(){
		return usersMapper.getRoles();
	};
}
