package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.CRoom;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("croomMapper01")
public interface CRoomMapper {
    @Select("select * from c_room where room_no=#{room_no}")
    @Results(id="roomResult01"
            ,value={
            @Result( property = "roomNo", column = "room_no" )
            ,@Result( property = "roomName", column = "room_name" )
            ,@Result( property = "buildingNo", column = "building_no" )
            ,@Result( property = "floorNo", column = "floor_no" )
    } )
    CRoom getCRoomById(String room_no);
}
