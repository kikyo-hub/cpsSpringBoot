package cn.nrzt.cps.archives.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import cn.nrzt.cps.archives.mapper.CEquipMapper;
import cn.nrzt.cps.archives.po.CEquip;
import cn.nrzt.cps.web.PageResult;

@Service
public class EquipService {
	@Autowired
	private CEquipMapper mapper;

	public int saveEquip(CEquip equip) {
		equip.setEquipId(equip.getCommAddress());
		equip.setAssetNo(equip.getCommAddress());
		return mapper.saveEquip(equip);
	}

	public List<CEquip> getAll() {

		return mapper.getAll("","","","","","","");
	}

	public PageResult<CEquip> getPage(int pageNo, int pageSize,String commAddress,String equipType,String equipMode,String equipName,String commMode,String protocolType,String upEquipId) {
		PageHelper.startPage(pageNo, pageSize);
		List<CEquip> list = mapper.getAll(commAddress,equipType,equipMode,equipName,commMode,protocolType,upEquipId);
		// 取分页信息
		return new PageResult<CEquip>(list);
	}

	public CEquip getByEquipId(String equipid) {

		return mapper.getById(equipid);
	}

	public PageResult<CEquip> filterEquip(int equipType) {
		PageHelper.startPage(0, 10);
		List<CEquip> list = mapper.getAll("","","","","","","");
		return new PageResult<CEquip>(list);
	}

	public int deleteEquip(String id) {

		return mapper.deleteEquip(id);
	}

	public int updateEquip(CEquip equip) {
		
		return mapper.updateEquip(equip);
	}
	
	public PageResult<CEquip> getPEquipNotM(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CEquip> list = mapper.getAllEquipNotM();
		// 取分页信息
		return new PageResult<CEquip>(list);
	}

	public int saveEquipForE(CEquip equip) {
		CEquip e=new CEquip();
		String commAddress=equip.getCommAddress();
		String equipType=equip.getEquipType();
		String protocolType=equip.getProtocolType();
		String commMode=equip.getCommMode();
		String equipName=equip.getEquipName();
		String upEquipId=equip.getUpEquipId();
		int configNo=equip.getConfigNo();
		String pt=equip.getPt();
		String ct=equip.getCt();
		e.setCommAddress(commAddress);
		e.setEquipType(equipType.substring(equipType.indexOf("（")+1,equipType.indexOf("）")));
		e.setProtocolType(protocolType.substring(protocolType.indexOf("（")+1,protocolType.indexOf("）")));
		e.setCommMode(commMode.substring(commMode.indexOf("（")+1,commMode.indexOf("）")));
		e.setEquipName(equipName);
		e.setUpEquipId(upEquipId);
		e.setEquipId(commAddress);
		e.setAssetNo(commAddress);
		e.setConfigNo(configNo);
		e.setPt(pt);
		e.setCt(ct);
		return mapper.saveEquip(e);
	}

	public List<CEquip> getEquipdata() {
		
		return mapper.getEquipdata();
	}
	
	public List<?> getallUpEquipS() {
		return mapper.getallUpEquipS();
	}

	public int updateUpEquip(CEquip equip) {
		return mapper.updateUpEquip(equip);
	}
	public int updateEquipState(String state,String address){
		return mapper.updateEquipState(state,address);
	}
	public PageResult<CEquip> getAllSubordinateEquipment(int pageNo, int pageSize, String address) {
		PageHelper.startPage(pageNo, pageSize);
		List<CEquip> list = mapper.getAllSubordinateEquipment(address);
		return new PageResult<CEquip>(list);
	}

	public List<?> getAllLowerEquipment(String address) {
		// TODO Auto-generated method stub
		return mapper.getAllLowerEquipment(address);
	}

	public List<Map<String, Object>> getallEquip() {
		return mapper.getallEquip();
	}

	
}
