package cn.nrzt.cps.system.service;

import cn.nrzt.cps.system.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DictService {
    @Autowired
	DictMapper dictMapper;
	public List<Map<String,Object>> selectDicts(){
		return dictMapper.selectDicts();
	};
	@Transactional
	public int deleteDicts(Map<String, Object> sort, List<Map<String, Object>> code) {
		int delSort = 0,delCode = 0 ;
		if(sort!=null && sort.size() != 0 ){
			delSort += dictMapper.deleteSort(sort);
		}
		if(code!=null && code.size() != 0 ){
			for (Map<String, Object> map: code) {
				delCode += dictMapper.deleteCode(map);
			}
		}
		int del = delSort + delCode;
		int len= code.size() + (sort!=null && sort.size() != 0? 1:0);
		return  del == len?1:0;
	}
	@Transactional
	public int updateDicts(  Map<String, Object> dict) {
		if( dict==null || dict.size() == 0 ) return 0;
		String level = String.valueOf( dict.get("level") );
		if( level.equals("0")){
			if(dict.get("key") == null ||"".equals(dict.get("key")) ){
				return dictMapper.insertSort(dict);
			}else{
				return dictMapper.updateSort(dict);
			}
		}else {
			if(dict.get("key") == null||"".equals(dict.get("key")) ){
				return dictMapper.insertCode(dict);
			}else{
				return dictMapper.updateCode(dict);
			}

		}
	}
}
