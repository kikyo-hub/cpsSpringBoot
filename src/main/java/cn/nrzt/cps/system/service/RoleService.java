package cn.nrzt.cps.system.service;

import cn.nrzt.cps.system.mapper.RoleMapper;
import cn.nrzt.cps.system.po.Authority;
import cn.nrzt.cps.system.po.Role;
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
public class RoleService {
    @Autowired
	RoleMapper roleMapper;
    public PageResult<Role> getRoles(Role role, Integer pageNo, Integer pageSize) {
    	PageHelper.startPage(pageNo, pageSize);
    	List<Role> list = roleMapper.getRoles(role);
    	PageResult<Role> pages= new PageResult<Role>(list);
        return  pages;
    }

    @Transactional
    public int delRole(List<String> ids) {
    	int count=0;
    	if(ids==null || ids.size() ==0 ) return 0;
    	for(String id:ids) {
			int refs = roleMapper.getRoleUserCount(id);
			count += refs > 0 ? 0 : roleMapper.delRole(id);
    	}
    	return count > 0? 1:0;
    }

    @Transactional
    public int updateRole(Role role) {
    	int count=0;
    	if(role==null) return 0 ;
		if(role.getGenTime() ==null || "".equals(role.getGenTime())){
			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            role.setGenTime(date);
		}
    	if(role.getId()==null || "".equals(role.getId())) {
    		count += roleMapper.createRole(role);
    	}else {
    		count += roleMapper.editRole(role);
    	}
        if(role.getAuths() !=null && role.getAuths().size()>0) {
        	count += roleMapper.distAuths(role.getAuths(),role.getId());

        }
    	return count;
    }
	@Transactional
	public int distAuths(Role role){
		int count=0;
    	if(role==null || role.getAuths() ==null || role.getAuths().size()<=0) return 0;
    	count += roleMapper.distAuths(role.getAuths(),role.getId());
		return count;
	}

    public List<Map<String,String>> getRoleUsers(String id){
    	return roleMapper.getRoleUsers(id);
    }
}
