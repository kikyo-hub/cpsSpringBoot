package cn.nrzt.cps.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.user.mapper.AuthMapper;
import cn.nrzt.cps.user.po.Auth;

@Service
public class UserService {
	
	 @Autowired
    private AuthMapper mappper;
    
    public Auth authUser(String name,String password){ 
    	return mappper.userLogin(name, password);
    }
}
