package cn.nrzt.cps.dictionary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.dictionary.mapper.PCodeMapper;
import cn.nrzt.cps.web.Selectable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PCodeService {

	@Autowired
	private PCodeMapper mappper;

	/**
	 * 设备类型
	 * 
	 * @return
	 */
	public List<Selectable> equipType() {
		return mappper.findPCode("equip_type");
	}

	/**
	 * 设备型号
	 * 
	 * @return
	 */
	public List<Selectable> equipMode() {
		return mappper.findPCode("wiringMode");
	}

	/**
	 * 协议
	 * 
	 * @return
	 */
	public List<Selectable> protocolCode() {
		return mappper.findPCode("commProtCode");
	}

	/**
	 * 通信方式
	 * 
	 * @return
	 */
	public List<Selectable> commMode() {
		return mappper.findPCode("commMode");
	}

	/**
	 * 用能设备类型
	 * 
	 * @return
	 */
	public List<Selectable> eDeviceType() {
		return mappper.findPCode("eDeviceType");
	}
	
	/**
	 * 诊断点类型
	 * 
	 * @return
	 */
	public List<Selectable> getDiagnosisType() {
		return mappper.findPCode("diagnosisType");
	}
	
	 /**
	 * 诊断规则类型
	 * 
	 * @return
	 */
	public List<Selectable> getDagnosisRuleType() {
		return mappper.findPCode2("diagnosisRule");
	}

	
	 /**
	 * 企业类型
	 * 
	 * @return
	 */
	public List<Selectable> getEnterpriseType() {
		return mappper.findPCode2("enterpriseType");
	}



}
