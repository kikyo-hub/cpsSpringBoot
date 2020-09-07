package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.EMonitorCurve;
import cn.nrzt.cps.archives.po.EMonitorDayRead;
import cn.nrzt.cps.archives.po.EnvMonitorCurve;
import cn.nrzt.cps.energydata.po.MonitorCurve;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
/**
 * 统计函数的模板方法
 * @author admin
 *
 */
//@Repository("emonitorCurveMapper")
public interface EnergeCurveMapper2 {
	
	
		final String MonitorCurveOf4HById = " select d.dt data_time "+ 				
	            " ,emc.DEV_ADDRESS  monitor_id " +
	            " ,sum( max(emc.ua)-min(emc.ua) ) over(PARTITION BY d.dt) ua " +
	            " ,sum( max(emc.ub)-min(emc.ub) ) over(PARTITION BY d.dt) ub " +
	            " ,sum( max(emc.uc)-min(emc.uc) ) over(PARTITION BY d.dt) uc " +
	            " ,sum( max(emc.ia)-min(emc.ia) ) over(PARTITION BY d.dt) ia " +
	            " ,sum( max(emc.ib)-min(emc.ib) ) over(PARTITION BY d.dt) ib " +
	            " ,sum( max(emc.ic)-min(emc.ic) ) over(PARTITION BY d.dt) ic " +
	            " ,sum( max(emc.pa)-min(emc.pa) ) over(PARTITION BY d.dt) pa " +
	            " ,sum( max(emc.pb)-min(emc.pb) ) over(PARTITION BY d.dt) pb " +
	            " ,sum( max(emc.pc)-min(emc.pc) ) over(PARTITION BY d.dt) pc " +	            
	            " ,sum( max(emc.p)-min(emc.p) ) over(PARTITION BY d.dt) p " +
	            " ,sum( max(emc.pap_r)-min(emc.pap_r) ) over(PARTITION BY d.dt) papr " +	        	           
	            " from e_monitor_curve emc " +
	            " ,( select to_date(time,'YYYY-MM-DD HH24:MI:SS')+(level-2)/6  dt  from (select '${data_day}' time from dual )  connect by level &lt;= 9 ) d " +
	            " where emc.DATA_TIME &lt;= d.dt and emc.DATA_TIME &gt; d.dt-1/6   " +
	            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')-1,'YYYY-MM-DD') " + 
	            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')+1,'YYYY-MM-DD') " +
	            " and to_char(emc.DATA_TIME,'YYYY-MM-DD HH24:MI:SS')!=to_char(to_date(#{data_day},'YYYY-MM-DD'),'YYYY-MM-DD HH24:MI:SS') " +
	            " <if test='monitor_id!=-1'> and emc.monitor_id=#{monitor_id}</if> "+ 
	            " group by emc.DEV_ADDRESS , d.dt " +
	            "order by emc.DEV_ADDRESS asc ,d.dt asc ";
		
		final String MonitorCurveOf2HById = " select d.dt data_time "+ 				
	            " ,emc.DEV_ADDRESS  monitor_id " +
	            " ,sum( max(emc.ua)-min(emc.ua) ) over(PARTITION BY d.dt) ua " +
	            " ,sum( max(emc.ub)-min(emc.ub) ) over(PARTITION BY d.dt) ub " +
	            " ,sum( max(emc.uc)-min(emc.uc) ) over(PARTITION BY d.dt) uc " +
	            " ,sum( max(emc.ia)-min(emc.ia) ) over(PARTITION BY d.dt) ia " +
	            " ,sum( max(emc.ib)-min(emc.ib) ) over(PARTITION BY d.dt) ib " +
	            " ,sum( max(emc.ic)-min(emc.ic) ) over(PARTITION BY d.dt) ic " +
	            " ,sum( max(emc.pa)-min(emc.pa) ) over(PARTITION BY d.dt) pa " +
	            " ,sum( max(emc.pb)-min(emc.pb) ) over(PARTITION BY d.dt) pb " +
	            " ,sum( max(emc.pc)-min(emc.pc) ) over(PARTITION BY d.dt) pc " +	            
	            " ,sum( max(emc.p)-min(emc.p) ) over(PARTITION BY d.dt) p " +
	            " ,sum( max(emc.pap_r)-min(emc.pap_r) ) over(PARTITION BY d.dt) papr " +	
	            " from e_monitor_curve emc " +
	            " ,( select to_date(time,'YYYY-MM-DD HH24:MI:SS')+(level-2)/12 dt  from (select '${data_day}' time from dual )  connect by level &lt;= 15 ) d " +
	            " where emc.DATA_TIME &lt;= d.dt and emc.DATA_TIME &gt; d.dt-1/12  " +
	            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')-1,'YYYY-MM-DD') " + 
	            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')+1,'YYYY-MM-DD') " +
	            " and to_char(emc.DATA_TIME,'YYYY-MM-DD HH24:MI:SS')!=to_char(to_date(#{data_day},'YYYY-MM-DD'),'YYYY-MM-DD HH24:MI:SS') " +
	            " <if test='monitor_id!=-1'> and emc.monitor_id=#{monitor_id}</if> " +       
	            " group by emc.DEV_ADDRESS , d.dt " +
	            " order by emc.DEV_ADDRESS asc ,d.dt asc ";
		
		
	
//******************************************* e_monitor_curve **************************************************************
    //日曲线
    // monitor_id 存在 ，单个设备日曲线 ，就是一天96条数据
    // monitor_id 不存在，就是查询所有设备日曲线, 就是每个设备96条数据
    @Select(" <script>  " +
            " select " +
            " s.monitor_id, s.data_time,s.ia,s.ib,s.ic,s.ua,s.ub,s.uc,s.pa,s.pb,s.pc,s.p,s.pap_r " +
            " from E_MONITOR_CURVE_ENERGY s " +
            //" full join env_monitor_curve env on s.monitor_id = env.monitor_id" +
            " where  to_date(to_char(s.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD')" +
            " <if test='monitor_id!=-1'> and s.monitor_id=#{monitor_id}</if> " +
            " order by s.monitor_id desc " +
            " </script> ")
    @Results(id="monitorCurveResult01"
            ,value={
             @Result( property = "monitorId", column = "monitor_id" )
            ,@Result( property = "dataTime", column = "data_time"  )
            ,@Result( property = "ia", column = "ia" )
            ,@Result( property = "ib", column = "ib" )
            ,@Result( property = "ic", column = "ic" )
            ,@Result( property = "ua", column = "ua" )
            ,@Result( property = "ub", column = "ub" )
            ,@Result( property = "uc", column = "uc" )
            ,@Result( property = "pa", column = "pa" )
            ,@Result( property = "pb", column = "pb" )
            ,@Result( property = "pc", column = "pc" )
            ,@Result( property = "p", column = "p" )
            ,@Result( property = "papR", column = "PAP_R" )
            ,@Result( property = "cEquip", column = "monitor_id", one = @One(select="cn.nrzt.cps.archives.mapper.CEquipMapper.getCEquipByMonitorId"))
            //,@Result( property = "cEquip", column = "{monitor_id = monitor_id,data_day=data_time}", one = @One(select="cn.nrzt.cps.energydata.mapper.EMonitorCurveMapper.getEnvMonitorCurveById"))
    } )
    List<EMonitorCurve> getEMonitorCurveById(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);

    //  这是4个小时间隔
    //  select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/6  dt
    //  from (select '${data_day}' time from dual )  connect by level <= 7
    // monitor_id 存在 ，单个设备的4h统计量/day ，就是一天6 条数据
    // monitor_id 不存在，就是查询所有设备的4h统计量/day, 就是每个设备6条数据
    @Select("<script> " +MonitorCurveOf4HById + " </script>")
    @ResultMap("monitorCurveResult01")
    List<EMonitorCurve> getEMonitorCurveOf4HById(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);
    //  这是2个小时间隔
    // monitor_id 存在 ，单个设备的4h统计量/day ，就是一天12 条数据
    // monitor_id 不存在，就是查询所有设备的4h统计量/day, 就是每个设备12条数据
    @Select("<script>  " +MonitorCurveOf2HById + " </script>")
    @ResultMap("monitorCurveResult01")
    List<EMonitorCurve> getEMonitorCurveOf2HById(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);

    //查询所有设备的4h统计量 ：
    // monitor_id 字段没有处理
    @Select("<script> " +
            " select   " +
            " data_time  " +
            " ,sum(ua)  ua,sum(ub)  ub,sum(uc)  uc " +
            " ,sum(ia)  ia,sum(ib)  ib,sum(ic)  ic " +
            " ,sum(pa)  pa,sum(pb)  pb,sum(pc)  pc " +
            " ,sum(p)  p " +
            " ,sum(papr)  papr " +
            " from (" +MonitorCurveOf4HById + ")  "+ 
            " group by data_time   order by data_time asc " +
            " </script>")
    @Results(id="monitorCurveResult02"
            ,value={
//            @Result( property = "monitorId", column = "monitor_id" )
            @Result( property = "dataTime", column = "data_time"  )
            ,@Result( property = "ia", column = "ia" )
            ,@Result( property = "ib", column = "ib" )
            ,@Result( property = "ic", column = "ic" )
            ,@Result( property = "ua", column = "ua" )
            ,@Result( property = "ub", column = "ub" )
            ,@Result( property = "uc", column = "uc" )
            ,@Result( property = "pa", column = "pa" )
            ,@Result( property = "pb", column = "pb" )
            ,@Result( property = "pc", column = "pc" )
            ,@Result( property = "p", column = "p" )
            ,@Result( property = "papR", column = "papr" )
            //,@Result( property = "cEquip", column = "monitor_id", one = @One(select="cn.nrzt.cps.energydata.mapper.CEquipMapper.getCEquipByMonitorId"))
    } )
    List<EMonitorCurve> getTotalEMonitorCurveOf4H(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);
    //查询所有设备的2h统计量 ：
    // monitor_id 字段没有处理
    @Select("<script>  " +
            " select   " +
            " data_time  " +
            " ,sum(ua)  ua,sum(ub)  ub,sum(uc)  uc " +
            " ,sum(ia)  ia,sum(ib)  ib,sum(ic)  ic " +
            " ,sum(pa)  pa,sum(pb)  pb,sum(pc)  pc " +
            " ,sum(p)  p " +
            " ,sum(papr)  papr " +
            " from (" + MonitorCurveOf2HById + ")  group by data_time order by data_time asc" +
            " </script>")
    @ResultMap("monitorCurveResult02")
    List<EMonitorCurve> getTotalEMonitorCurveOf2H(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);

//    @InsertProvider(type= EMonitorCurveProvider.class ,method="addEMonitorCurve")
//    int addEMonitorCurve(@Param("emonitorCurve")EMonitorCurve emonitorCurve);

    //******************************************** e_monitor_day_read *************************************************************************************
    final String DayReadOfDay = " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1) over( PARTITION BY DEV_ADDRESS order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1) over( PARTITION BY DEV_ADDRESS order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1) over( PARTITION BY DEV_ADDRESS order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +           
            " and (    to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') + 1 ,'YYYY-MM-DD')  "+ 
            " ) " +
            " <if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " order by monitor_id asc,data_time asc";
    
    final String DayReadOfWeek = " select monitor_id,data_time" +
            " ,abs( pap_r - lag(pap_r,1) over( PARTITION BY DEV_ADDRESS order by data_time) ) papr" +
            " ,abs( pap_e - lag(pap_e,1) over( PARTITION BY DEV_ADDRESS order by data_time) ) pape" +
            " ,abs( rap_e - lag(rap_e,1) over( PARTITION BY DEV_ADDRESS order by data_time) ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +           
            " and  (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       	or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       	or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  "+  
            " ) " +
            " <if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " order by monitor_id asc,data_time asc";
    final String DayReadOfMonth = " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1) over( PARTITION BY DEV_ADDRESS order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1) over( PARTITION BY DEV_ADDRESS order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1) over( PARTITION BY DEV_ADDRESS order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +           
            " and (    to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(ADD_MONTHS(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') , 1 ),'YYYY-MM-DD')  "+ 
            ") " +
            " <if test='monitor_id!=-1'> and monitor_id = #{monitor_id} </if> " +
            " order by monitor_id asc,data_time asc";
    // 每一天的用量
    // 一天的用量
    // monitor_id 存在 ，单个设备的日数据 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的日数据, 就是每个设备 1 条数据
    @Select("<script>" +
            " select * from ( " + DayReadOfDay+  " ) where where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')   " + 
            " and  to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd')   " +
            " order by monitor_id asc,data_time asc" +
            " </script>")
    @Results(id="monitorDayReadResult01"
            ,value={
            @Result( property = "monitorId", column = "monitor_id" )
            ,@Result( property = "dataTime", column = "data_time"  )
            ,@Result( property = "papE", column = "pape" )
            ,@Result( property = "papR", column = "papr" )
            ,@Result( property = "rapE", column = "rape" )
            ,@Result( property = "cEquip", column = "monitor_id", one = @One(select="cn.nrzt.cps.archives.mapper.CEquipMapper.getCEquipByMonitorId"))
    } )
    List<EMonitorDayRead> getEMonitorDayReadOfDayById(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // 一个星期七天每一天的用量
    // monitor_id 存在 ，单个设备的周数据 ，就是 7 条数据
    // monitor_id 不存在，就是查询所有设备的周数据, 就是每个设备 7 条数据
    @Select("<script>" +
            " select * from ( " + DayReadOfWeek+  " )  where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getEMonitorDayReadOfWeekById(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // 一个月三十天每一天的用量
    // monitor_id 存在 ，单个设备的周数据 ，就是 30 条数据
    // monitor_id 不存在，就是查询所有设备的周数据, 就是每个设备 30 条数据
    @Select("<script>" +
            " select * from ( " +DayReadOfMonth+ " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm')  " +
            " order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getEMonitorDayReadOfMonthById(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    // monitor_id 存在 ，单个设备的日总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的日总量, 就是每个设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " + DayReadOfDay +" ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd')  " +
            " group by  monitor_id  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01" )
    List<EMonitorDayRead> getTotalEMonitorDayReadOfDay(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    // monitor_id 存在 ，单个设备的周总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的周总量, 就是每个设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +   DayReadOfWeek   + " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " group by  monitor_id  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01"  )
    List<EMonitorDayRead> getTotalEMonitorDayReadOfWeek(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // monitor_id 存在 ，单个设备的月总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的月总量, 就是每个设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from (  " +   DayReadOfMonth   + " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm')  " +
            " group by  monitor_id  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getTotalEMonitorDayReadOfMonth(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    
    // monitor_id 存在 ，单个设备的日总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的日总量, 就是所有设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " + DayReadOfDay +" ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd')  " +
            " group by  data_time  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01" )
    List<EMonitorDayRead> getTotalEMonitorDayReadOfDay2(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    // monitor_id 存在 ，单个设备的周总量 ，就是 7 条数据
    // monitor_id 不存在，就是查询所有设备的周总量, 就是所有设备 7 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +   DayReadOfWeek   + " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " group by  data_time  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01"  )
    List<EMonitorDayRead> getTotalEMonitorDayReadOfWeek2(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // monitor_id 存在 ，单个设备的月总量 ，就是 30 条数据
    // monitor_id 不存在，就是查询所有设备的月总量, 就是所有设备 30 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from (  " +   DayReadOfMonth   + " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm')  " +
            " group by  data_time  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getTotalEMonitorDayReadOfMonth2(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

}

