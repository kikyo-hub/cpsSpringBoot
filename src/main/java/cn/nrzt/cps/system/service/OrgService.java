package cn.nrzt.cps.system.service;

import cn.nrzt.cps.energydata.po.MonitorCurveEnergy;
import cn.nrzt.cps.system.mapper.OrgMapper;
import cn.nrzt.cps.system.po.Org;
import cn.nrzt.cps.web.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.Map;

@Service
public class OrgService {
    @Autowired
    OrgMapper orgMapper;
    public PageResult<Org> getOrgDatas(Org org, Integer pageNo, Integer pageSize) {
    	PageHelper.startPage(pageNo, pageSize);
    	List<Org> list = orgMapper.getOrgDatas( org);
    	PageResult<Org> pages= new PageResult<Org>(list);
        return  pages;
    }
    
    public List<Map<String,String>> getOrgTypes(){
    	return orgMapper.getOrgTypes();
    }
    @Transactional
    public int delOrg(List<String> ids) {
    	int count=0;
    	if(ids==null || ids.size() ==0 ) return 0;
    	
    	for(String id:ids) {
    		count += orgMapper.delOrg(id);
    	}
    	return count==ids.size()? 1:0;
    	
    }
    @Transactional
    public int updateOrg(Org org) {
    	int count=0;
    	if(org==null) return 0 ;
    	if(org.getId()==null || "".equals(org.getId())) {
    		count = orgMapper.createOrg(org);
    	}else {
    		count = orgMapper.editOrg(org);
    	}
    	return count;
    }
    public List<Map<String,String>> getOrgUsers(String id){
    	return orgMapper.getOrgUsers(id);
    }
}
