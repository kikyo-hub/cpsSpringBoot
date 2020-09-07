package cn.nrzt.cps.archives.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import cn.nrzt.cps.archives.mapper.CCollectionRuleMapper;
import cn.nrzt.cps.archives.po.CCollectionRule;
import cn.nrzt.cps.web.PageResult;

@Service
public class CollectionRuleService {

	@Autowired
	private CCollectionRuleMapper mapper;

	public PageResult<CCollectionRule> getAll(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CCollectionRule> list = mapper.getAll();
		return new PageResult<CCollectionRule>(list);
	}

	@Transactional
	public int updatebyID(CCollectionRule cCollectionRule) {
		mapper.deletedataId(cCollectionRule.getCollectionRuleId());
		for (String dataId : cCollectionRule.getDataIds()) {
			mapper.insertIddata(cCollectionRule.getCollectionRuleId(), dataId);
		}
		int i = mapper.updatebyID(cCollectionRule);
		return i;
	}
	
	@Transactional
	public int deletebyID(Integer collectionRuleId) {
		mapper.deletebyID(collectionRuleId);
		int i = mapper.deletedataId(collectionRuleId);
		return i;
	}
	@Transactional
	public int inster(CCollectionRule cCollectionRule) {
		int i = mapper.inster(cCollectionRule);
		for (String dataId : cCollectionRule.getDataIds()) {
			mapper.insertIddata(cCollectionRule.getCollectionRuleId(), dataId);
		}
		return i;
	}

	public List<Map<String, Object>> getCollectionAllSelect() {
		List<Map<String, Object>> list = mapper.getCollectionAllSelect();
		for (Map<String, Object> m : list) {
			m.put("value",m.get("COLLECTION_RULE_ID")+"");
			m.put("key",m.get("RULE_NAME")+"");
			m.put("display",m.get("RULE_NAME")+"");
		}
		return list;
	}
	
	public List<String> getCollection() {
		return mapper.getCollection();
	}
}
