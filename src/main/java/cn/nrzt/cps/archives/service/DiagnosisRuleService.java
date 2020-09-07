package cn.nrzt.cps.archives.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import cn.nrzt.cps.archives.mapper.CDiagnosisRuleMapper;
import cn.nrzt.cps.archives.po.CDiagnosisRule;
import cn.nrzt.cps.archives.po.CDiagnosisRuleDetail;
import cn.nrzt.cps.web.PageResult;

@Service
public class DiagnosisRuleService {

	@Autowired
	private CDiagnosisRuleMapper mapper;
	
	public PageResult<CDiagnosisRule> getAll(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CDiagnosisRule> list = mapper.getAll();
		for (CDiagnosisRule cDiagnosisRule : list) {
			for (CDiagnosisRuleDetail CDiagnosisRuleDetail : cDiagnosisRule.getcDiagnosisRuleDetails()) {
				switch (CDiagnosisRuleDetail.getDataType()) {
				case 0:
					CDiagnosisRuleDetail.setDataTypeName("曲线数据");
					break;
                case 1:
                	CDiagnosisRuleDetail.setDataTypeName("日数据");
					break;
                case 2:
                	CDiagnosisRuleDetail.setDataTypeName("月数据");
                	break;
				}
				switch (CDiagnosisRuleDetail.getCalcMode()) {
				case 0:
					CDiagnosisRuleDetail.setCalcModeName("累加和");
					break;

				case 1:
					CDiagnosisRuleDetail.setCalcModeName("平均值");
					break;
				}
			}
		}
		return new PageResult<CDiagnosisRule>(list);
	}
	
	//插入诊断规则
	@Transactional
//	public int insert(CDiagnosisRule cDiagnosisRule) {
//		int i = mapper.insert(cDiagnosisRule);
//		for (CDiagnosisRuleDetail cDiagnosisRuleDetail : cDiagnosisRule.getcDiagnosisRuleDetails()) {
//			cDiagnosisRuleDetail.setDiagnosisRuleId(cDiagnosisRule.getDiagnosisRuleId());
//			mapper.insertDetail(cDiagnosisRuleDetail);
//			for (String dataId : cDiagnosisRuleDetail.getDataIds()) {
//				mapper.insertDataId(cDiagnosisRuleDetail.getDiagnosisRuleDetailId(), dataId);
//			}
//		}
//				return i;
//	}
	public int insert(CDiagnosisRule cDiagnosisRule) {
		int i = mapper.insert(cDiagnosisRule);
		for (CDiagnosisRuleDetail cDiagnosisRuleDetail : cDiagnosisRule.getcDiagnosisRuleDetails()) {
			cDiagnosisRuleDetail.setDiagnosisRuleId(cDiagnosisRule.getDiagnosisRuleId());
			mapper.insertDetail(cDiagnosisRuleDetail);
		}
		return i;
	}
	
	//删除诊断规则
	@Transactional
	public int deletebyID(String DIAGNOSIS_RULE_ID) {
//		List<String> list = mapper.getDetailId(DIAGNOSIS_RULE_ID);
//		for (String detailId : list) {
//			mapper.deletedataId(detailId);
//		}
		mapper.deleteDetail(DIAGNOSIS_RULE_ID);
		int i = mapper.delete(DIAGNOSIS_RULE_ID);
		return i;
	}
	
	
	//更新诊断规则
	@Transactional
//	public int updatebyID(CDiagnosisRule cDiagnosisRule) {
//		//删除
//		List<String> list = mapper.getDetailId(cDiagnosisRule.getDiagnosisRuleId());
//		for (String detailId : list) {
//			mapper.deletedataId(detailId);
//		}
//		mapper.deleteDetail(cDiagnosisRule.getDiagnosisRuleId());
//		//更新
//		int i = mapper.updatebyID(cDiagnosisRule);
//		//插入
//		for (CDiagnosisRuleDetail cDiagnosisRuleDetail : cDiagnosisRule.getcDiagnosisRuleDetails()) {
//			cDiagnosisRuleDetail.setDiagnosisRuleId(cDiagnosisRule.getDiagnosisRuleId());
//			mapper.insertDetail(cDiagnosisRuleDetail);
//			for (String dataId : cDiagnosisRuleDetail.getDataIds()) {
//				mapper.insertDataId(cDiagnosisRuleDetail.getDiagnosisRuleDetailId(), dataId);
//			}
//		}
//		return i;
//	}
	public int updatebyID(CDiagnosisRule cDiagnosisRule) {
		int i = mapper.updatebyID(cDiagnosisRule);
		mapper.deleteDetail(cDiagnosisRule.getDiagnosisRuleId());
		for (CDiagnosisRuleDetail cDiagnosisRuleDetail : cDiagnosisRule.getcDiagnosisRuleDetails()) {
			cDiagnosisRuleDetail.setDiagnosisRuleId(cDiagnosisRule.getDiagnosisRuleId());
			mapper.insertDetail(cDiagnosisRuleDetail);
		}
		return i;
	}
}
