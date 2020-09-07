package cn.nrzt.cps.archives.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.archives.mapper.CDataIDMapper;
import cn.nrzt.cps.archives.po.CDataId;

@Service
public class CDataIdService {

	@Autowired 
	private CDataIDMapper mapper;
	
	public List<CDataId> getallCDataIds(){
		return mapper.getallCDataIds();
}
}