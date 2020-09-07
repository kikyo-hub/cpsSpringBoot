package cn.nrzt.cps.archives.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import cn.nrzt.cps.archives.mapper.CMonitorMapper;
import cn.nrzt.cps.archives.po.CMonitor;
import cn.nrzt.cps.web.PageResult;

@Service
public class MonitorService {
	@Autowired
	private CMonitorMapper mapper;

	public PageResult<CMonitor> getAll(int pageNo, int pageSize,String DeviceType, String monitorName,
			String upEquipId) {
		PageHelper.startPage(pageNo, pageSize);
		List<CMonitor> list = mapper.getAll(DeviceType, monitorName, upEquipId);
		for (CMonitor cMonitor : list) {
			String monitorId = cMonitor.getMonitorId();
			List<Map<String, Object>> rCollectRuleList = mapper.getRCollectRule(monitorId);
			cMonitor.setCollectionRuleR(rCollectRuleList);
		}
		return new PageResult<CMonitor>(list);
	}

	public int deleteMonitor(String id) {
		mapper.clearMonAndCollRule(id);
		return mapper.deleteMonitor(id);
	}

	@SuppressWarnings("unchecked")
	public int saveMonitor(CMonitor monitor) {
//		int seq = mapper.getSeq();
		monitor.setMonitorId(monitor.getEquipAddress());
		List<String> lll = (List<String>) monitor.getCollectionRuleR();
		for (String collectionRuleId : lll) {
			mapper.saveMonAndCollRule(monitor.getMonitorId(), collectionRuleId);
		}
		return mapper.saveMonitor(monitor);
	}

	@SuppressWarnings("unchecked")
	public int updateMonitor(CMonitor monitor) {
		mapper.clearMonAndCollRule(monitor.getMonitorId());
		List<String> lll = (List<String>) monitor.getCollectionRuleR();
		for (String collectionRuleId : lll) {
			mapper.saveMonAndCollRule(monitor.getEquipAddress(), collectionRuleId);
		}
		return mapper.updateMonitor(monitor);
	}

	public int releaseEquip(String monitorid) {
		return mapper.releaseEquip(monitorid);
	}

	public List<?> getBuild() {
		List<Map<String, Object>> list = mapper.getBuild();
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("value", map.get("VALUE"));
			mm.put("label", map.get("LABEL"));
			if (map.get("ISLEAF").equals("0")) {
				mm.put("isLeaf", false);
			}
			l.add(mm);
		}
		return l;
	}

	public List<?> getFloor(String builingNo) {
		List<Map<String, Object>> list = mapper.getFloor(builingNo);
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("value", map.get("VALUE"));
			mm.put("label", map.get("LABEL"));
			if (map.get("ISLEAF").equals("0")) {
				mm.put("isLeaf", false);
			}
			l.add(mm);
		}
		return l;
	}

	public List<?> getRoom(String floorNo) {
		List<Map<String, Object>> list = mapper.getRoom(floorNo);
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("value", map.get("VALUE"));
			mm.put("label", map.get("LABEL"));
			l.add(mm);
		}
		return l;
	}

	public PageResult<CMonitor> findUnbind(int pageNo, int pageSize, String diagnosisId,String upEquipId) {
		PageHelper.startPage(pageNo, pageSize);
		List<CMonitor> list = mapper.findNoBMonitor(diagnosisId,upEquipId);
		return new PageResult<CMonitor>(list);
	}

	public PageResult<CMonitor> findBindingYet(int pageNo, int pageSize, String diagnosisId,String upEquipId) {
		PageHelper.startPage(pageNo, pageSize);
		List<CMonitor> list = mapper.findYetBMonitor(diagnosisId,upEquipId);
		return new PageResult<CMonitor>(list);
	}

	public List<?> getBuildSelect() {
		List<Map<String, Object>> list = mapper.getBuild();
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("value", map.get("VALUE"));
			mm.put("key", map.get("LABEL"));
			mm.put("display", map.get("LABEL"));
			l.add(mm);
		}
		return l;
	}

	public List<Map<String, Object>> getAddress() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> addressB = mapper.getAddressB();
		List<Map<String, Object>> addressF = mapper.getAddressF();
		List<Map<String, Object>> addressR = mapper.getAddressR();
		for (Map<String, Object> mB : addressB) {
			Map<String, Object> lsMB = new HashMap<String, Object>();
			String bNo = mB.get("BUILDING_NO") + "";
			lsMB.put("value", bNo);
			lsMB.put("label", mB.get("BUILDING_NAME"));
			List<Map<String, Object>> listFloor = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> mF : addressF) {
				String mFBNo = mF.get("BUILING_NO") + "";
				if (mFBNo.equals(bNo)) {
					Map<String, Object> lsMF = new HashMap<String, Object>();
					String fNo = mF.get("FLOOR_NO") + "";
					lsMF.put("value", fNo);
					lsMF.put("label", mF.get("FLOOR_NAME"));
					List<Map<String, Object>> listRoom = new ArrayList<Map<String, Object>>();
					for (Map<String, Object> mR : addressR) {
						String mRFNo = mR.get("FLOOR_NO") + "";
						if (mRFNo.equals(fNo)) {
							Map<String, Object> lsMR = new HashMap<String, Object>();
							lsMR.put("value", mR.get("ROOM_NO"));
							lsMR.put("label", mR.get("ROOM_NAME"));
							listRoom.add(lsMR);
						}
					}
					if (listRoom.size() > 0) {
						lsMF.put("children", listRoom);
					}
					listFloor.add(lsMF);
				}
			}
			if (listFloor.size() > 0) {
				lsMB.put("children", listFloor);
			}
			list.add(lsMB);
		}
		return list;
	}
}
