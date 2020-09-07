package cn.nrzt.cps.archives.mapper;

import cn.nrzt.cps.archives.po.CEquip;

import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CEquipMapper {
//	@Insert("INSERT INTO C_EQUIP(EQUIP_ID,COMM_ADDRESS,EQUIP_TYPE,COMM_MODE,PROTOCOL_TYPE,EQUIP_MODE,UP_EQUIP_ID,EQUIP_NAME) VALUES(#{equipId,jdbcType=NUMERIC},#{commAddress,jdbcType=VARCHAR},#{equipType,jdbcType=VARCHAR},#{commMode,jdbcType=VARCHAR},#{protocolType,jdbcType=VARCHAR},#{equipMode,jdbcType=VARCHAR},#{upEquipId,jdbcType=VARCHAR},#{equipName,jdbcType=VARCHAR})")
	@Insert("INSERT INTO C_EQUIP" + 
			"  (EQUIP_ID," + 
			"   COMM_ADDRESS," + 
			"   EQUIP_TYPE," + 
			"   COMM_MODE," + 
			"   PROTOCOL_TYPE," + 
			"   EQUIP_MODE," + 
			"   UP_EQUIP_ID," + 
			"   ASSET_NO," + 
			"   PT," + 
			"   CT," + 
			"   CONFIG_NO," + 
			"   EQUIP_NAME)" + 
			"VALUES" + 
			"  (#{equipId,jdbcType = NUMERIC}," + 
			"   #{commAddress,jdbcType = VARCHAR}," + 
			"   #{equipType,jdbcType = VARCHAR}," + 
			"   #{commMode,jdbcType = VARCHAR}," + 
			"   #{protocolType,jdbcType = VARCHAR}," + 
			"   #{equipMode,jdbcType = VARCHAR}," + 
			"   #{upEquipId,jdbcType = VARCHAR}," + 
			"   #{assetNo,jdbcType = VARCHAR}," + 
			"   #{pt,jdbcType = VARCHAR}," + 
			"   #{ct,jdbcType = VARCHAR}," + 
			"   #{configNo,jdbcType = NUMERIC}," + 
			"   #{equipName,jdbcType = VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "equipId") // 添加该行，id值将自动添加到Measure
	@ResultMap("equipMap")
	int saveEquip(CEquip equip);

	@Select(value = { "<script> SELECT C.*," + 
			"       CM.NAME AS COMM_MODE_DESC," + 
			"       ET.NAME AS EQUIP_TYPE_DESC," + 
			"       PT.NAME AS PROTOCOL_TYPE_DESC" + 
			"  FROM C_EQUIP C, P_CODE CM, P_CODE ET, P_CODE PT" + 
			" WHERE 1 = 1" + 
			"   AND ET.VALUE(+) = C.EQUIP_TYPE" + 
			"   AND ET.CODE_TYPE = 'equip_type'" +
			"   AND CM.VALUE(+) = C.COMM_MODE" + 
			"   AND CM.CODE_TYPE = 'commMode'" + 
			"   AND PT.VALUE(+) = C.PROTOCOL_TYPE" + 
			"   AND PT.CODE_TYPE = 'commProtCode'" + 
			" <if test=\"commAddress != null and '' != commAddress\"> and C.COMM_ADDRESS = #{commAddress} </if> "+
			" <if test=\"equipType != null and '' != equipType\"> and C.EQUIP_TYPE = #{equipType} </if> "+
			" <if test=\"commMode != null and '' != commMode\"> and C.COMM_MODE = #{commMode} </if> "+
			" <if test=\"protocolType != null and '' != protocolType\"> and C.PROTOCOL_TYPE = #{protocolType} </if> "+
			" <if test=\"equipName != null and '' != equipName\"> and C.EQUIP_NAME = #{equipName} </if> "+
			" <if test=\"upEquipId != null and '' != upEquipId\"> and C.UP_EQUIP_ID = #{upEquipId} </if> "+
			" ORDER BY UP_EQUIP_ID,CONFIG_NO ASC" + 
			" </script>"})
	@Results(id = "equipMap", value = { @Result(column = "EQUIP_ID", property = "equipId", javaType = String.class),
			@Result(column = "COMM_ADDRESS", property = "commAddress", javaType = String.class),
			@Result(column = "EQUIP_TYPE", property = "equipType", javaType = String.class),
			@Result(column = "COMM_MODE", property = "commMode", javaType = String.class),
			@Result(column = "PROTOCOL_TYPE", property = "protocolType", javaType = String.class),
			@Result(column = "EQUIP_MODE", property = "equipMode", javaType = String.class),
			@Result(column = "UP_EQUIP_ID", property = "upEquipId", javaType = String.class),
			@Result(column = "EQUIP_NAME", property = "equipName", javaType = String.class),
			@Result(column = "COMM_MODE_DESC", property = "commModeDesc", javaType = String.class),
			@Result(column = "EQUIP_TYPE_DESC", property = "equipTypeDesc", javaType = String.class),
			@Result(column = "PROTOCOL_TYPE_DESC", property = "protocolTypeDesc", javaType = String.class),
			@Result(column = "EQUIP_MODE_DESC", property = "equipModeDesc", javaType = String.class),
			@Result(column = "CT", property = "ct", javaType = String.class),
			@Result(column = "CONFIG_NO", property = "configNo", javaType = int.class),
			@Result(column = "PT", property = "pt", javaType = String.class),
			@Result(column = "MACTH_RESULT", property = "macthResult", javaType = String.class)
//            @Result(column = "sex", property = "userSex"),
//            @Result(column = "birthday", property = "userBirthday"),
//            @Result(column = "address", property = "userAddress"),
//            @Result(column = "id", property = "accounts",
//            many = @Many(select = "com.llb.dao.AccountMapper.findAccountByUid", fetchType = FetchType.LAZY)
//        )
	})
	List<CEquip> getAll(String commAddress,String equipType,String equipMode,String equipName,String commMode,String protocolType,String upEquipId);

	@Select(value = { "SELECT * FROM C_EQUIP where EQUIP_ID =#{equipid}" })
	@ResultMap("equipMap")
	CEquip getById(String equipid);

	@Select(value = { "SELECT * FROM C_EQUIP where EQUIP_ID =#{equipid}" })
	@ResultMap("equipMap")
	List<CEquip> filter();

	@Select(value = { "SELECT C_EQUIP_SEQ.NEXTVAL as EQUIP_ID FROM DUAL" })
	int getSeq();

	@Delete(value = { "DELETE C_EQUIP WHERE EQUIP_ID =#{ID,jdbcType=VARCHAR}" })
	int deleteEquip(String id);

	@Update(value = { "UPDATE C_EQUIP" + 
			"   SET COMM_ADDRESS = #{commAddress,jdbcType=VARCHAR}," + 
			"       EQUIP_ID = #{commAddress,jdbcType=VARCHAR}," + 
			"       EQUIP_TYPE = #{equipType,jdbcType=VARCHAR}," + 
			"       COMM_MODE = #{commMode,jdbcType=VARCHAR}," + 
			"       PROTOCOL_TYPE = #{protocolType,jdbcType=VARCHAR}," + 
			"       EQUIP_MODE = #{equipMode,jdbcType=VARCHAR}," + 
			"       UP_EQUIP_ID = #{upEquipId,jdbcType=VARCHAR}," + 
			"       PT = #{pt,jdbcType=VARCHAR}," + 
			"       CT = #{ct,jdbcType=VARCHAR}," + 
			"       CONFIG_NO = #{configNo,jdbcType=NUMERIC}," + 
			"       EQUIP_NAME = #{equipName,jdbcType=VARCHAR}" + 
			" WHERE EQUIP_ID = #{equipId,jdbcType=NUMERIC}" })
	@ResultMap("equipMap")
	int updateEquip(CEquip equip);
	
	@Select(value = "SELECT E.*,CM.NAME AS COMM_MODE_DESC,ET.NAME AS EQUIP_TYPE_DESC,PT.NAME AS PROTOCOL_TYPE_DESC FROM C_EQUIP E,C_MONITOR M,P_CODE CM,P_CODE ET,P_CODE PT where 1=1" + 
			" and CM.VALUE(+) = E.COMM_MODE" + 
			" and CM.CODE_TYPE = 'commMode'" + 
			" and ET.VALUE(+) = E.EQUIP_TYPE" + 
			" and ET.CODE_TYPE = 'equip_type'" + 
			" and PT.VALUE(+) = E.PROTOCOL_TYPE" + 
			" and PT.CODE_TYPE = 'commProtCode'" + 
			" and E.COMM_ADDRESS=M.EQUIP_ADDRESS(+)" + 
			" and M.EQUIP_ADDRESS IS NULL" + 
			" and E.Up_Equip_Id IS not NULL "
			)
	@ResultMap("equipMap")
	List<CEquip> getAllEquipNotM();


//*********************************************************************************************
	@Select(" <script>" +
			" select * from c_equip " + //where comm_address=#{equip_address}
			" <if test='equip_address!=-1'> where comm_address=#{equip_address}</if> " +
			" </script> ")
	@Results(id="equipResult01"
			,value={
			@Result( property = "equipId", column = "equip_id" )
			,@Result( property = "commAddress", column = "comm_address" )
			,@Result( property = "equipType", column = "equip_type" )
			,@Result( property = "commMode", column = "comm_mode" )
			,@Result( property = "protocolType", column = "protocol_type" )
			,@Result( property = "equipMode", column = "equip_mode" )
			,@Result( property = "upEquipId", column = "up_equip_id" )
			,@Result( property = "equipName", column = "equip_name" )
			,@Result( property = "assetNo", column = "asset_no" )
	} )
	CEquip getCEquipByAddress(@Param("equip_address") String  equip_address);


	@Select(" <script>" +
			" select *  " +
			" from c_equip " +
			" where comm_address in ( " +
			"   select EQUIP_ADDRESS from c_monitor  " +
			"       <if test='monitor_id!=-1'> where monitor_id=#{monitor_id}</if> " +
			" )"+
			" </script> ")
	@ResultMap("equipResult01")
	CEquip getCEquipByMonitorId(@Param("monitor_id") String  monitor_id);
	
    @Select(" <script>" +
			" select *  " +
			" from c_equip " +
			"  where EQUIP_TYPE='200' "+ 
			" </script> ")
    @ResultMap("equipResult01")
	List<CEquip> getEquipdata();

	@Select(value = "SELECT c.equip_id,c.equip_name FROM c_equip c where 1=1 and c.equip_type='201' or c.equip_type='200'")
	@Results(value ={
            @Result(column = "EQUIP_NAME", property = "key",javaType = String.class),
            @Result(column = "EQUIP_NAME", property = "display",javaType = String.class),
            @Result(column = "EQUIP_ID", property = "value")
    })
	List<Map<String, Object>> getallUpEquipS();
	
	@Select(value = "SELECT e.CONFIG_NO,\r\n" + 
			"  e.COMM_ADDRESS,\r\n" + 
			"  ET.NAME AS EQUIP_TYPE,\r\n" + 
			"  PT.NAME AS PROTOCOL_TYPE,\r\n" + 
			"  CM.NAME AS COMM_MODE,\r\n" + 
			"  e.ASSET_NO,\r\n" + 
			"  e.PT,\r\n" + 
			"  e.CT\r\n" + 
			"FROM C_EQUIP e,\r\n" + 
			"  P_CODE CM,\r\n" + 
			"  P_CODE ET,\r\n" + 
			"  P_CODE PT\r\n" + 
			"WHERE 1=1\r\n" + 
			"and CM.VALUE(+) = E.COMM_MODE\r\n" + 
			"			 and CM.CODE_TYPE = 'commMode'\r\n" + 
			"			 and ET.VALUE(+) = E.EQUIP_TYPE\r\n" + 
			"			 and ET.CODE_TYPE = 'equip_type'\r\n" + 
			"			 and PT.VALUE(+) = E.PROTOCOL_TYPE\r\n" + 
			"			 and PT.CODE_TYPE = 'commProtCode'")
	List<Map<String, Object>> getallEquip();
	
	@Update(value = "update C_equip set MACTH_RESULT =#{state} where COMM_ADDRESS=#{address}")
	int updateEquipState(String state,String address);
	
	@Update(value = "update c_equip set UP_EQUIP_ID = #{upEquipId,jdbcType=VARCHAR} where EQUIP_ID = #{equipId,jdbcType=VARCHAR}")
	int updateUpEquip(CEquip equip);
    @Select(value={"<script> SELECT C.*," + 
			"       CM.NAME AS COMM_MODE_DESC," + 
			"       ET.NAME AS EQUIP_TYPE_DESC," + 
			"       PT.NAME AS PROTOCOL_TYPE_DESC" + 
			"  FROM C_EQUIP C, P_CODE CM, P_CODE ET, P_CODE PT" + 
			" WHERE 1 = 1" + 
			"   AND ET.VALUE(+) = C.EQUIP_TYPE" + 
			"   AND ET.CODE_TYPE = 'equip_type'" +
			"   AND CM.VALUE(+) = C.COMM_MODE" + 
			"   AND CM.CODE_TYPE = 'commMode'" + 
			"   AND PT.VALUE(+) = C.PROTOCOL_TYPE" + 
			"   AND PT.CODE_TYPE = 'commProtCode'" + 
			" <if test=\"commAddress != null and '' != commAddress\"> and C.UP_EQUIP_ID = #{address} </if> "+
			" ORDER BY C.MACTH_RESULT desc " + 
			" </script>"})
    @ResultMap("equipMap")
	List<CEquip> getAllSubordinateEquipment(String address);
    
    @Select(value={"<script> SELECT C.*," + 
			"       CM.NAME AS COMM_MODE_DESC," + 
			"       ET.NAME AS EQUIP_TYPE_DESC," + 
			"       PT.NAME AS PROTOCOL_TYPE_DESC" + 
			"  FROM C_EQUIP C, P_CODE CM, P_CODE ET, P_CODE PT" + 
			" WHERE 1 = 1" + 
			"   AND ET.VALUE(+) = C.EQUIP_TYPE" + 
			"   AND ET.CODE_TYPE = 'equip_type'" +
			"   AND CM.VALUE(+) = C.COMM_MODE" + 
			"   AND CM.CODE_TYPE = 'commMode'" + 
			"   AND PT.VALUE(+) = C.PROTOCOL_TYPE" + 
			"   AND PT.CODE_TYPE = 'commProtCode'" + 
			" <if test=\"commAddress != null and '' != commAddress\"> and C.UP_EQUIP_ID = #{address} </if> "+
			" ORDER BY COMM_ADDRESS " + 
			" </script>"})
    @ResultMap("equipMap")
	List<?> getAllLowerEquipment(String address);

}
