package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.CMonitor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("monitorDataMapper")
public interface MonitorDataMapper {
    //monitor_id 存在 ，查询单个监测点
    //monitor_id 不存在 ，查询所有监测点
    @Select("<script> select * from c_monitor " +
            "<if test='monitor_id!=-1'> where monitor_id=#{monitor_id}</if> " +
            "</script>")
    @Results(id="monitorResult01"
        ,value={
             @Result( property = "monitorId", column = "monitor_id" )
            ,@Result( property = "monitorName", column = "monitor_name" )
            ,@Result( property = "monitorDesc", column = "monitor_desc" )
            ,@Result( property = "createTime", column = "create_time" )
            ,@Result( property = "deviceType", column = "device_type" )
            ,@Result( property = "equipAddress", column = "equip_address" )
            ,@Result( property = "buildingNo", column = "building_no" )
//            ,@Result( property = "buildingName", column = "building_no",one = @One(select = "cn.nrzt.cps.archives.mapper.BuildingMapper.getBuildingName") )
            ,@Result( property = "floorNo", column = "floor_no" )
            ,@Result( property = "collectionRuleName", column = "collection_rule_id",one = @One(select = "cn.nrzt.cps.archives.mapper.BuildingMapper.getCollectRuleName") )
            ,@Result( property = "roomNo", column = "room_no" )
            ,@Result( property = "creator", column = "creator" )
//            ,@Result( property = "building", column = "building_no", one = @One(select="cn.nrzt.cps.archives.mapper.BuildingMapper.getCBuildingById"))
//            ,@Result( property = "floor", column = "floor_no", one = @One(select="cn.nrzt.cps.energydata.mapper.CFloorMapper.getCFloorById"))
//            ,@Result( property = "room", column = "room_no", one = @One(select="cn.nrzt.cps.energydata.mapper.CRoomMapper.getCRoomById"))
            ,@Result( property = "equip", column = "equip_address", one = @One(select="cn.nrzt.cps.archives.mapper.CEquipMapper.getCEquipByAddress"))
            ,@Result( property = "collectionRule", column = "collection_rule_id", one = @One(select="cn.nrzt.cps.archives.mapper.CCollectionRuleMapper.getCCollectionRuleById"))
            ,@Result( property = "user", column = "creator", one = @One(select="cn.nrzt.cps.user.mapper.UserMapper.getUserById"))
        } )
    List<CMonitor> getCMonitors(@Param("monitor_id") String monitor_id);
    
    @Select("<script> select * from c_monitor where DEVICE_TYPE in (1,2,3)" +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            "</script>")
    @ResultMap("monitorResult01")
    List<CMonitor> getCMonitorsOfElectric(@Param("monitor_id") String monitor_id);

    @Select("<script> select * from c_monitor " +
            "<if test='diagnosis_id!=-1'> where monitor_id in (select monitor_id from R_DIAGNOSIS_MONITOR where diagnosis_id =#{diagnosis_id} )</if> " +
            "</script>")
    @ResultMap("monitorResult01")
    List<CMonitor> getCMonitorsByDiagnosisId(@Param("diagnosis_id") String diagnosis_id);

}
