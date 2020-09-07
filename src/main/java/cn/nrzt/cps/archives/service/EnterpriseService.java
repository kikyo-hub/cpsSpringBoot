package cn.nrzt.cps.archives.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import cn.nrzt.cps.archives.mapper.EnterpriseMapper;
import cn.nrzt.cps.archives.po.CBuilding;
import cn.nrzt.cps.archives.po.CEnterprise;
import cn.nrzt.cps.archives.po.CEnterpriseBuildingRel;
import cn.nrzt.cps.archives.po.CFloor;
import cn.nrzt.cps.archives.po.CRoom;
import cn.nrzt.cps.web.PageResult;



@Service
public class EnterpriseService{
	@Autowired
	private EnterpriseMapper mapper;
	public LinkedHashMap<String, Object>  getAllEnterprisedata() {
		return mapper.getAllEnterprisedata();
	}
	
	public List<Map<String, Object>> getEnterprisefloor(String buildingno) {
		return mapper.getEnterprisefloor(buildingno);
	}
	
	public List<Map<String, Object>> getDetectionPoint(String buildingno) {
		return mapper.getDetectionPoint(buildingno);
	}
	@Transactional(rollbackFor = Exception.class)
	public int addCollection(CBuilding equip, JSONObject body) {
		CEnterpriseBuildingRel enbuilding=body.toJavaObject(CEnterpriseBuildingRel.class);
		int seqrelid = mapper.getSeqRelId();
		int seq = mapper.getSeq();
		enbuilding.setRelId((seqrelid+""));
		enbuilding.setBuildingNo(seq+"");
		equip.setBuildingNo((seq + ""));
		if(mapper.addCenBuilding(enbuilding)<=0) {
			throw new RuntimeException("插入失败");
		}
		return mapper.addCollection(equip);
	}
	@Transactional(rollbackFor = Exception.class)
	public int deleteCollection(String id) {
		mapper.delete(id);
		return mapper.deleteCollection(id);
	}
	@Transactional(rollbackFor = Exception.class)
	public int updateCollection(CBuilding building) {
		// TODO Auto-generated method stub
		return mapper.updateCollection(building);
	}
	@Transactional(rollbackFor = Exception.class)
	public int updateCustomerData(CEnterprise enterprise) {
		return mapper.updateCustomerData(enterprise);
	}

	public List<CBuilding> getManyEnterprisefloor(String enterpriseid) {
		
		return mapper.getManyEnterprisefloor(enterpriseid);
	}

	public PageResult<CFloor> getFloorRoomForBuild(int pageNo, int pageSize,String builingNo) {
		PageHelper.startPage(pageNo, pageSize);
		List<CFloor> floorList=mapper.getFloorRoomForBuild(builingNo);
		List<CRoom> roomList=mapper.getAllRoom();
		for (CFloor cFloor : floorList) {
			List<Map<String, Object>> roomListT=new ArrayList<Map<String,Object>>();
			String floorNo = cFloor.getFloorNo();
			for (CRoom cRoom : roomList) {
				String roomFNo = cRoom.getFloorNo();
				if (roomFNo.equals(floorNo)) {
					Map<String, Object> m=new HashMap<String, Object>();
					m.put("roomNo", cRoom.getRoomNo());
					m.put("roomName", cRoom.getRoomName());
					roomListT.add(m);
				}
			}
			if (roomListT.size()>0) {
				cFloor.setRoomList(roomListT);
			}
		}
		return new PageResult<CFloor>(floorList);
	}

	public int insertFloor(CFloor cFloor) {
		return mapper.insertFloor(cFloor);
	}
	
	public int updateFloor(CFloor cFloor) {
		return mapper.updateFloor(cFloor);
	}
	
	public int deleteFloor(String floorNo) {
		return mapper.deleteFloor(floorNo);
	}
	
	public int insertRoom(CRoom cRoom) {
		return mapper.insertRoom(cRoom);
	}
	
	public int updateRoom(CRoom cRoom) {
		return mapper.updateRoom(cRoom);
	}
	
	public int deleteRoom(String roomNo) {
		return mapper.deleteRoom(roomNo);
	}
}
