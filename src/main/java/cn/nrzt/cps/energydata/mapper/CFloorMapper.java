package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.CFloor;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("cfloorMapper01")
public interface CFloorMapper {
    @Select("select * from c_floor where floor_no=#{floor_no}")
    @Results(id="floorResult01"
            ,value={
             @Result( property = "floorNo", column = "floor_no" )
            ,@Result( property = "floorName", column = "floor_name" )
            ,@Result( property = "BuildingNo", column = "building_no" )
    } )
    CFloor getCFloorById(String floor_no);
}

