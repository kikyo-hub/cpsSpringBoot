package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.EMonitorCurve;
import cn.nrzt.cps.archives.po.EMonitorDayRead;
import cn.nrzt.cps.archives.po.EnvMonitorCurve;
import cn.nrzt.cps.energydata.po.MonitorCurve;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("emonitorCurveMapper")
public interface EMonitorCurveMapper {
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
    @Select("<script> " +
            " select max(dt) data_time " +
            " ,max(emc.monitor_id) monitor_id " +
            " ,max(emc.ua)-min(emc.ua) ua " +
            " ,max(emc.ub)-min(emc.ub) ub " +
            " ,max(emc.uc)-min(emc.uc) uc " +
            " ,max(emc.ia)-min(emc.ia) ia " +
            " ,max(emc.ib)-min(emc.ib) ib " +
            " ,max(emc.ic)-min(emc.ic) ic " +
            " ,max(emc.pa)-min(emc.pa) pa " +
            " ,max(emc.pb)-min(emc.pb) pb " +
            " ,max(emc.pc)-min(emc.pc) pc " +
            " ,max(emc.p)-min(emc.p) p " +
            " ,max(emc.pap_r)-min(emc.pap_r) papr " +
            " from e_monitor_curve emc " +
            " ,(    select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/6  dt  " +
            "       from (select '${data_day}' time from dual )  connect by level &lt;= 7) d " +
            " where emc.DATA_TIME &lt;= d.dt and emc.DATA_TIME &gt; d.dt-1/6   " +
            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')-1,'YYYY-MM-DD')   " +
            " and to_char(emc.DATA_TIME,'YYYY-MM-DD HH24:MI:SS')!=to_char(to_date('2020-06-06','YYYY-MM-DD'),'YYYY-MM-DD HH24:MI:SS') " +
            "<if test='monitor_id!=-1'> and emc.monitor_id=#{monitor_id}</if> " +
            " group by emc.monitor_id ,d.dt order by emc.monitor_id asc, d.dt asc  " +
            " </script>")
    @ResultMap("monitorCurveResult01")
    List<EMonitorCurve> getEMonitorCurveOf4HById(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);
    //  这是2个小时间隔
    // monitor_id 存在 ，单个设备的4h统计量/day ，就是一天12 条数据
    // monitor_id 不存在，就是查询所有设备的4h统计量/day, 就是每个设备12条数据
    @Select("<script>  " +
            " select max(dt) data_time " +
            " ,max(emc.monitor_id) monitor_id " +
            " ,max(emc.ua)-min(emc.ua) ua " +
            " ,max(emc.ub)-min(emc.ub) ub " +
            " ,max(emc.uc)-min(emc.uc) uc " +
            " ,max(emc.ia)-min(emc.ia) ia " +
            " ,max(emc.ib)-min(emc.ib) ib " +
            " ,max(emc.ic)-min(emc.ic) ic " +
            " ,max(emc.pa)-min(emc.pa) pa " +
            " ,max(emc.pb)-min(emc.pb) pb " +
            " ,max(emc.pc)-min(emc.pc) pc " +
            " ,max(emc.p)-min(emc.p) p " +
            " ,max(emc.pap_r)-min(emc.pap_r) papr " +
            " from e_monitor_curve emc " +
            " ,(    select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12  dt  " +
            "       from (select '${data_day}' time from dual )  connect by level &lt;= 13) d " +
            " where emc.DATA_TIME &lt;= d.dt and  emc.DATA_TIME &gt; d.dt-1/12   " +
            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')-1,'YYYY-MM-DD')   " +
            " and to_char(emc.DATA_TIME,'YYYY-MM-DD HH24:MI:SS')!=to_char(to_date('2020-06-06','YYYY-MM-DD'),'YYYY-MM-DD HH24:MI:SS') " +
            "<if test='monitor_id!=-1'> and emc.monitor_id=#{monitor_id}</if> " +
            " group by emc.monitor_id ,d.dt order by emc.monitor_id asc, d.dt asc  " +
            " </script>")
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
            " from (" +
            " select max(dt) data_time " +
            " ,max(emc.monitor_id) monitor_id " +
            " ,max(emc.ua)-min(emc.ua) ua " +
            " ,max(emc.ub)-min(emc.ub) ub " +
            " ,max(emc.uc)-min(emc.uc) uc " +
            " ,max(emc.ia)-min(emc.ia) ia " +
            " ,max(emc.ib)-min(emc.ib) ib " +
            " ,max(emc.ic)-min(emc.ic) ic " +
            " ,max(emc.pa)-min(emc.pa) pa " +
            " ,max(emc.pb)-min(emc.pb) pb " +
            " ,max(emc.pc)-min(emc.pc) pc " +
            " ,max(emc.p)-min(emc.p) p " +
            " ,max(emc.pap_r)-min(emc.pap_r) papr " +
            " from e_monitor_curve emc " +
            " ,(    select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/6  dt  " +
            "       from (select '${data_day}' time from dual )  connect by level &lt;= 7) d " +
            " where emc.DATA_TIME &lt;= d.dt and emc.DATA_TIME &gt; d.dt-1/6   " +
            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')-1,'YYYY-MM-DD')   " +
            " group by emc.monitor_id ,d.dt order by emc.monitor_id asc, d.dt asc  " +
            ")  group by data_time   order by data_time asc " +
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
            " from (" +
            " select max(dt) data_time " +
            " ,max(emc.monitor_id) monitor_id " +
            " ,max(emc.ua)-min(emc.ua) ua " +
            " ,max(emc.ub)-min(emc.ub) ub " +
            " ,max(emc.uc)-min(emc.uc) uc " +
            " ,max(emc.ia)-min(emc.ia) ia " +
            " ,max(emc.ib)-min(emc.ib) ib " +
            " ,max(emc.ic)-min(emc.ic) ic " +
            " ,max(emc.pa)-min(emc.pa) pa " +
            " ,max(emc.pb)-min(emc.pb) pb " +
            " ,max(emc.pc)-min(emc.pc) pc " +
            " ,max(emc.p)-min(emc.p) p " +
            " ,max(emc.pap_r)-min(emc.pap_r) papr " +
            " from e_monitor_curve emc " +
            " ,(    select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12  dt  " +
            "       from (select '${data_day}' time from dual )  connect by level &lt;= 13) d " +
            " where emc.DATA_TIME &lt;= d.dt and  emc.DATA_TIME &gt; d.dt-1/12   " +
            " and to_char(emc.DATA_TIME,'YYYY-MM-DD')!=to_char(to_date(#{data_day},'YYYY-MM-DD ')-1,'YYYY-MM-DD')   " +
            "<if test='monitor_id!=-1'> and emc.monitor_id=#{monitor_id}</if> " +
            " group by emc.monitor_id ,d.dt order by emc.monitor_id asc, d.dt asc  " +
            ")  group by data_time order by data_time asc" +
            " </script>")
    @ResultMap("monitorCurveResult02")
    List<EMonitorCurve> getTotalEMonitorCurveOf2H(@Param("monitor_id") String monitor_id,@Param("data_day") String data_day);

//    @InsertProvider(type= EMonitorCurveProvider.class ,method="addEMonitorCurve")
//    int addEMonitorCurve(@Param("emonitorCurve")EMonitorCurve emonitorCurve);

    //******************************************** e_monitor_day_read *************************************************************************************

    // 每一天的用量
    // 一天的用量
    // monitor_id 存在 ，单个设备的日数据 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的日数据, 就是每个设备 1 条数据
    @Select("<script>" +
            " select * from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " and (    to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') + 1 ,'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd')  " +
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
            " select * from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) " +
            " where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getEMonitorDayReadOfWeekById(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // 一个月三十天每一天的用量
    // monitor_id 存在 ，单个设备的周数据 ，就是 30 条数据
    // monitor_id 不存在，就是查询所有设备的周数据, 就是每个设备 30 条数据
    @Select("<script>" +
            " select * from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id = #{monitor_id} </if> " +
            " and (    to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(ADD_MONTHS(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') , 1 ),'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
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
            " from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " and (    to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') + 1 ,'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
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
            " from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
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
            " from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id = #{monitor_id} </if> " +
            " and (    to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(ADD_MONTHS(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') , 1 ),'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm')  " +
            " group by  monitor_id  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getTotalEMonitorDayReadOfMonth(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    // 所有设别的日总量
    // monitor_id 字段没有处理
    @Select("<script>" +
            " select   " +
            " max(data_time) data_time  " +
            " ,sum(papr)  papr,sum(pape)  papr,sum(rape)  papr " +
            " from (" +
                " select   " +
                " max(data_time)  data_time " +
                " ,max(monitor_id)   monitor_id " +
                " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
                " from ( " +
                    " select monitor_id,data_time" +
                    " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
                    " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
                    " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
                    " from e_monitor_day_read  " +
                    " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
                    //" <if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
                    " and (    to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd') " +
                    "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') - 1 ,'YYYY-MM-DD') " +
                    "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') + 1 ,'YYYY-MM-DD')  ) " +
                    "       order by monitor_id asc,data_time asc" +
                    " ) " +
                "  where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
                " and  to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd')  " +
                " group by  monitor_id  " +
                //" order by monitor_id asc,data_time asc" +
            ")" +
            " </script>")
    @Results(id="monitorDayReadResult02"
            ,value={
//            @Result( property = "monitorId", column = "monitor_id" )
             @Result( property = "dataTime", column = "data_time"  )
            ,@Result( property = "papE", column = "pape" )
            ,@Result( property = "papR", column = "papr" )
            ,@Result( property = "rapE", column = "rape" )
//            ,@Result( property = "cEquip", column = "monitor_id", one = @One(select="cn.nrzt.cps.energydata.mapper.CEquipMapper.getCEquipByMonitorId"))
    } )
    List<EMonitorDayRead> getAllTotalEMonitorDayReadOfDay (@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // 所有设别的周总量
    // monitor_id 字段没有处理
    @Select("<script>" +
            " select   " +
            " max(data_time) data_time  " +
            " ,sum(papr)  papr,sum(pape)  papr,sum(rape)  papr " +
            " from (" +
                " select   " +
                " max(data_time)  data_time " +
                " ,max(monitor_id)   monitor_id " +
                " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
                " from ( " +
                    " select monitor_id,data_time" +
                    " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
                    " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
                    " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
                    " from e_monitor_day_read  " +
                    " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
                    //" <if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
                    " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
                    "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
                    "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
                    "       order by monitor_id asc,data_time asc" +
                    " ) " +
                " where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
                " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
                " group by  monitor_id  " +
                //" order by monitor_id asc,data_time asc" +
            ") " +
            "</script>")
    @ResultMap("monitorDayReadResult02"  )
    List<EMonitorDayRead> getAllTotalEMonitorDayReadOfWeek(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // 所有设别的月总量
    // monitor_id 字段没有处理
    @Select("<script>" +
            " select   " +
            " max(data_time) data_time  " +
            " ,sum(papr)  papr,sum(pape)  papr,sum(rape)  papr " +
            " from (" +
                " select   " +
                " max(data_time)  data_time " +
                " ,max(monitor_id)   monitor_id " +
                " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
                " from ( " +
                    " select monitor_id,data_time" +
                    " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
                    " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
                    " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
                    " from e_monitor_day_read  " +
                    " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
                    //" <if test='monitor_id!=-1'> and monitor_id = #{monitor_id} </if> " +
                    " and (    to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm') " +
                    "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') - 1 ,'YYYY-MM-DD') " +
                    "       or to_char(data_time,'YYYY-MM-DD') = to_char(ADD_MONTHS(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') , 1 ),'YYYY-MM-DD')  ) " +
                    "       order by monitor_id asc,data_time asc" +
                    " ) " +
                " where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
                " and  to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm')  " +
                " group by  monitor_id  " +
                //" order by monitor_id asc,data_time asc" +
            " )" +
            "</script>")
    @ResultMap("monitorDayReadResult02")
    List<EMonitorDayRead> getAllTotalEMonitorDayReadOfMonth(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);


    // monitor_id 存在 ，单个设备的日总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的日总量, 就是每个设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " and (    to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'ddd') + 1 ,'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'ddd')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'ddd')  " +
            " group by  data_time  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01" )
    List<EMonitorDayRead> getEMonitorDayReadOfDay2(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    // monitor_id 存在 ，单个设备的周总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的周总量, 就是每个设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id=#{monitor_id}</if> " +
            " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " group by  data_time  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01"  )
    List<EMonitorDayRead> getEMonitorDayReadOfWeek2(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);
    // monitor_id 存在 ，单个设备的月总量 ，就是 1 条数据
    // monitor_id 不存在，就是查询所有设备的月总量, 就是每个设备 1 条数据
    @Select("<script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(monitor_id)   monitor_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +
            " select monitor_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r ) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e ) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e ) rape" +
            " from e_monitor_day_read  " +
            " where  to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            "<if test='monitor_id!=-1'> and monitor_id = #{monitor_id} </if> " +
            " and (    to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(ADD_MONTHS(trunc(to_date(#{data_day},'YYYY-MM-DD'),'mm') , 1 ),'YYYY-MM-DD')  ) " +
            "       order by monitor_id asc,data_time asc" +
            " ) where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'mm')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'mm')  " +
            " group by  data_time  " +
            //" order by monitor_id asc,data_time asc" +
            " </script>")
    @ResultMap("monitorDayReadResult01")
    List<EMonitorDayRead> getEMonitorDayReadOfMonth2(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    //*********************************** env_monitor_curve ****************************************************
    // env_monitor_curve 日曲线
    // monitor_id 存在 ，单个设备日曲线 ，就是一天96条数据
    // monitor_id 不存在，就是查询所有设备日曲线, 就是每个设备96条数据
    @Select(" <script>  " +
            " select " +
            " s.monitor_id, s.data_time,s.temperature,s.humidity,s.co2,s.illuminance " +
            " from E_MONITOR_ENVIRONMENT_CURVE s" +
            " where  to_date(to_char(s.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD')" +
            " <if test='monitor_id!=-1'> and s.monitor_id=#{monitor_id}</if> " +
            " order by s.monitor_id desc " +
            " </script> ")
    @Results(id="env"
            ,value={
            @Result( property = "monitorId", column = "monitor_id" )
            ,@Result( property = "dataTime", column = "data_time"  )
            ,@Result( property = "temperature", column = "temperature" )
            ,@Result( property = "humidity", column = "humidity" )
            ,@Result( property = "co2", column = "co2" )
            ,@Result( property = "illuminance", column = "illuminance" )
            ,@Result( property = "cEquip", column = "monitor_id", one = @One(select="cn.nrzt.cps.archives.mapper.CEquipMapper.getCEquipByMonitorId"))
    } )
    List<EnvMonitorCurve> getEnvMonitorCurveById(@Param("monitor_id") String monitor_id, @Param("data_day") String data_day);

    //日曲线
    // monitor_id 存在 ，单个设备日曲线 ，就是一天96条数据(电+环境)
    // monitor_id 不存在，就是查询所有设备日曲线, 就是每个设备96条数据
    // 乘80倍比
    @Select(" <script> select * from " +
            " (select " +
            " NVL(s.DEV_ADDRESS,env.DEV_ADDRESS) DEV_ADDRESS,NVL(s.data_time,env.data_time) data_time" +
            " ,s.ia,s.ib,s.ic,s.ua,s.ub,s.uc,s.pa,s.pb,s.pc,s.p,(s.PAP_R - lag(s.pap_r,1) over(order by s.DATA_TIME)) as pap, ROWNUM as rom " +
            " ,env.temperature,env.humidity,env.co2,env.illuminance " +
            " from (select e.DEV_ADDRESS,e.data_time,e.ia*c.ct ia,e.ib*c.ct ib,e.ic*c.ct ic,e.ua*c.pt ua,e.ub*c.pt ub,e.uc*c.pt uc,e.pa*c.ct*c.pt pa,e.pb*c.ct*c.pt pb,e.pc*c.ct*c.pt pc,e.p*c.ct*c.pt p,e.pap_r*c.ct*c.pt pap_r " + 
            "              from E_MONITOR_CURVE_ENERGY e left join C_EQUIP c on e.dev_address=c.comm_address order by  data_time) s " +
            " full join E_MONITOR_CURVE_ENVIRONMENT env on s.DEV_ADDRESS = env.DEV_ADDRESS  " +
            " and s.data_time = env.data_time " +
            " where  (" +
                " to_date(to_char(s.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD')" +
                " or to_date(to_char(env.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD')" +
            " )" +
            " <if test='DEV_ADDRESS!=-1'> and (s.DEV_ADDRESS=#{DEV_ADDRESS} or env.DEV_ADDRESS=#{DEV_ADDRESS})</if> " +
            " order by data_time asc,s.DEV_ADDRESS desc,env.DEV_ADDRESS desc) where rom>1" +
            " </script> ")
    @Results(id="e_env_monitorCurveResult01"
            ,value={
            @Result( property = "dev_address", column = "dev_address" )
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
            ,@Result( property = "papR", column = "pap" )
            ,@Result( property = "temperature", column = "temperature" )
            ,@Result( property = "humidity", column = "humidity" )
            ,@Result( property = "co2", column = "co2" )
            ,@Result( property = "illuminance", column = "illuminance" )
            ,@Result( property = "cEquip", column = "DEV_ADDRESS", one = @One(select="cn.nrzt.cps.archives.mapper.CEquipMapper.getCEquipByMonitorId"))
    } )
    List<MonitorCurve> getMonitorCurveById(@Param("DEV_ADDRESS") String DEV_ADDRESS, @Param("data_day") String data_day);
     // 不乘80倍比
//    @Select(" <script> select * from " +
//            " (select " +
//            " NVL(s.DEV_ADDRESS,env.DEV_ADDRESS) DEV_ADDRESS,NVL(s.data_time,env.data_time) data_time" +
//            " ,s.ia,s.ib,s.ic,s.ua,s.ub,s.uc,s.pa,s.pb,s.pc,s.p,(s.PAP_R - lag(s.pap_r,1,0) over(order by s.DATA_TIME)) as pap, ROWNUM as rom " +
//            " ,env.temperature,env.humidity,env.co2,env.illuminance " +
//            " from (select * " + 
//            "              from E_MONITOR_CURVE_ENERGY e  order by  data_time) s " +
//            " full join E_MONITOR_CURVE_ENVIRONMENT env on s.DEV_ADDRESS = env.DEV_ADDRESS  " +
//            " and s.data_time = env.data_time " +
//            " where  (" +
//                " to_date(to_char(s.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD')" +
//                " or to_date(to_char(env.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD')" +
//            " )" +
//            " <if test='DEV_ADDRESS!=-1'> and (s.DEV_ADDRESS=#{DEV_ADDRESS} or env.DEV_ADDRESS=#{DEV_ADDRESS})</if> " +
//            " order by data_time asc,s.DEV_ADDRESS desc,env.DEV_ADDRESS desc) where rom>1" +
//            " </script> ")
//    @Results(id="e_env_monitorCurveResult01"
//            ,value={
//            @Result( property = "dev_address", column = "dev_address" )
//            ,@Result( property = "dataTime", column = "data_time"  )
//            ,@Result( property = "ia", column = "ia" )
//            ,@Result( property = "ib", column = "ib" )
//            ,@Result( property = "ic", column = "ic" )
//            ,@Result( property = "ua", column = "ua" )
//            ,@Result( property = "ub", column = "ub" )
//            ,@Result( property = "uc", column = "uc" )
//            ,@Result( property = "pa", column = "pa" )
//            ,@Result( property = "pb", column = "pb" )
//            ,@Result( property = "pc", column = "pc" )
//            ,@Result( property = "p", column = "p" )
//            ,@Result( property = "papR", column = "pap" )
//            ,@Result( property = "temperature", column = "temperature" )
//            ,@Result( property = "humidity", column = "humidity" )
//            ,@Result( property = "co2", column = "co2" )
//            ,@Result( property = "illuminance", column = "illuminance" )
//            ,@Result( property = "cEquip", column = "DEV_ADDRESS", one = @One(select="cn.nrzt.cps.archives.mapper.CEquipMapper.getCEquipByMonitorId"))
//    } )
//    List<MonitorCurve> getMonitorCurveById(@Param("DEV_ADDRESS") String DEV_ADDRESS, @Param("data_day") String data_day);
    
    //查询两小时间隔用电量
    // 乘以倍比
    @Select("<script>"
    		+ "select  max(emc.DEV_ADDRESS) dev,(max(emc.pap_r)-min(emc.pap_r)) papr,d.dt" + 
    		" from ( select e.DEV_ADDRESS,e.data_time,e.ia*c.ct*c.pt ia,e.ib*c.ct*c.pt ib,e.ic*c.ct*c.pt ic,e.ua,e.ub,e.uc,e.pa*c.ct*c.pt pa,e.pb*c.ct*c.pt pb,e.pc*c.ct*c.pt pc,e.p*c.ct*c.pt p,e.pap_r*c.ct*c.pt pap_r " + 
    		"              from E_MONITOR_CURVE_ENERGY e left join C_EQUIP c on e.dev_address=c.comm_address order by  data_time) emc" + 
    		"    ,(    select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12  dt" + 
    		"          from (select #{data_day} time from dual )  connect by level &lt;= 13) d" + 
    		" where emc.DATA_TIME &lt;= d.dt and emc.DATA_TIME &gt;= d.dt-1/12" + 
    		"   and emc.DEV_ADDRESS=#{DEV_ADDRESS}  group by d.dt" + 
    		"   order by d.dt"
    		+ "</script>")
    @Results(id = "PaprMap",value = {
    		@Result(property = "monitorId", column = "dev")
    		,@Result(property = "dataTime",column = "dt")
    		,@Result(property = "papR",column = "papr")
    })
    List<EMonitorCurve> getPaprBy2H(@Param("DEV_ADDRESS") String DEV_ADDRESS, @Param("data_day") String data_day);
    
    //查询任意时间段两小时间隔用电量
    // 乘以倍比
    @Select("<script>"
    		+ "select  max(emc.DEV_ADDRESS) dev,(max(emc.pap_r)-min(emc.pap_r)) papr,dd.dt" + 
    		" from ( select e.DEV_ADDRESS,e.data_time,e.ia*c.ct*c.pt ia,e.ib*c.ct*c.pt ib,e.ic*c.ct*c.pt ic,e.ua,e.ub,e.uc,e.pa*c.ct*c.pt pa,e.pb*c.ct*c.pt pb,e.pc*c.ct*c.pt pc,e.p*c.ct*c.pt p,e.pap_r*c.ct*c.pt pap_r " + 
    		"              from E_MONITOR_CURVE_ENERGY e left join C_EQUIP c on e.dev_address=c.comm_address order by  data_time) emc" + 
    		"    ,( select * from  (select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12  dt" + 
    		"          from (select #{start_data_day} time from dual)  connect by level &lt;= #{level} ) d where d.dt &lt;= to_date(#{end_data_day},'YYYY-MM-DD HH24:MI:SS') )dd" + 
    		" where emc.DATA_TIME &lt;= dd.dt and emc.DATA_TIME &gt;= dd.dt-1/12" + 
    		"   and emc.DEV_ADDRESS=#{DEV_ADDRESS}  group by dd.dt" + 
    		"   order by dd.dt"
    		+ "</script>")
    @Results(value = {
    		@Result(property = "monitorId", column = "dev")
    		,@Result(property = "dataTime",column = "dt",javaType = Object.class)
    		,@Result(property = "papR",column = "papr",javaType = BigDecimal.class) 
    })
    List<EMonitorCurve> getPaprBy2HDay(@Param("DEV_ADDRESS") String DEV_ADDRESS, @Param("start_data_day") String start_data_day,@Param("end_data_day") String end_data_day,@Param("level") String level);
    
    //
//    select distinct dt,max(pap) over(PARTITION BY dt,dev_address) from
//    (
//    select 
//     dd.dt,t.DEV_ADDRESS,
//    --t.data_time,t.pap_r,
//    --(max(t.pap_r) over (PARTITION BY t.DEV_ADDRESS,dd.dt )) max,
//    --(min(t.pap_r) over (PARTITION BY t.DEV_ADDRESS,dd.dt )) min,
//     ((max(t.pap_r) over (PARTITION BY t.DEV_ADDRESS,dd.dt )) - (min(t.pap_r) over (PARTITION BY t.DEV_ADDRESS,dd.dt )) ) pap
//    from E_MONITOR_CURVE_ENERGY t
//    ,
//    (
//    select * from  (select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12  dt 
//              from (select '2020-08-09' time from dual)  connect by level<= 13 ) d where d.dt <= to_date('2020-08-10','YYYY-MM-DD HH24:MI:SS')
//              ) dd
//              where t.data_time <= dd.dt and t.data_time>= (dd.dt-1/12) order by  DEV_ADDRESS,dt,data_time 
//              ) order by dt
    
    
    // 按日查询用电量
    // 乘以倍比
    @Select("<script>"
    		+ "select * from(select rownum as rom, t.dev_address,(t.data_time-1) data_time,(t.pap_r - lag(t.pap_r,1) over(order by t.DATA_TIME)) as pap  from (select e.dev_address,e.data_time,e.pap_r*c.ct*c.pt pap_r from E_MONITOR_DAY_ENERGY e left join C_EQUIP c on e.dev_address=c.comm_address order by data_time) t where dev_address=#{DEV_ADDRESS} and data_time &gt;= to_date(#{start_data_day},'YYYY-MM-DD HH24:MI:SS') and data_time &lt;= to_date(#{end_data_day},'YYYY-MM-DD HH24:MI:SS')+1)" + 
    		"where rom>1"+
    		"</script>")
    @Results(value = {
    		@Result(property = "monitorId", column = "DEV_ADDRESS")
    		,@Result(property = "dataTime",column = "DATA_TIME",javaType = Object.class)
    		,@Result(property = "papR",column = "PAP",javaType = BigDecimal.class)
    })
    List<EMonitorCurve> getPaprByDay(@Param("DEV_ADDRESS") String DEV_ADDRESS, @Param("start_data_day") String start_data_day,@Param("end_data_day") String end_data_day);
    
    //查询任意时间段内两小时的所有监测点的总用电量
    // 乘以倍比
    @Select("<script>"
    		+ " select dt,sum(papr) sum_papr from ( select DEV_ADDRESS,dd.dt,(max(emc.pap_r)-min(emc.pap_r)) papr from " + 
    		"   (select * from  " + 
    		"   (select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12  dt from " + 
    		"   (select #{start_data_day} time from dual)  connect by level &lt;= #{level} ) d where d.dt &lt;= to_date(#{end_data_day},'YYYY-MM-DD HH24:MI:SS') ) dd , ( select e.DEV_ADDRESS,e.data_time,e.ia*c.ct*c.pt ia,e.ib*c.ct*c.pt ib,e.ic*c.ct*c.pt ic,e.ua,e.ub,e.uc,e.pa*c.ct*c.pt pa,e.pb*c.ct*c.pt pb,e.pc*c.ct*c.pt pc,e.p*c.ct*c.pt p,e.pap_r*c.ct*c.pt pap_r " + 
    		"              from E_MONITOR_CURVE_ENERGY e left join C_EQUIP c on e.dev_address=c.comm_address order by  data_time) emc" + 
    		"           where emc.DATA_TIME &lt;= dd.dt and emc.DATA_TIME &gt;= dd.dt-1/12" + 
    		"            group by dd.dt,DEV_ADDRESS" + 
    		"   order by dd.dt) group by dt order by dt"
    		+ "</script>")
    @Results(value = {
    		@Result(property = "dataTime",column = "dt"),
    		@Result(property = "papR",column = "SUM_PAPR")
    })
    List<EMonitorCurve> getAllPaprBy2H(String start_data_day,String end_data_day,String level);
    
    
  //查询任意天数的所有监测点的总用电量
    // 乘以倍比
    // 日期-1
    @Select("<script> select * from "
    		+ "(select data_time,papr,rownum rom from  "
    		+ "(select distinct (data_time-1) data_time ,sum(papr) over ( PARTITION BY data_time ) papr  "
    		+ "from ("
    		+ "select DEV_ADDRESS,data_time"
    		+ ", (pap_r - lag(pap_r,1) over(PARTITION BY DEV_ADDRESS order by DEV_ADDRESS asc ,data_time asc)) papr "
    		+ "from (select e.dev_address,e.data_time,e.pap_r*c.ct*c.pt pap_r from E_MONITOR_DAY_ENERGY e left join C_EQUIP c on e.dev_address=c.comm_address order by data_time) "
    		+ "where  (data_time BETWEEN to_date(#{start_data_day},'YYYY-MM-DD HH24:MI:SS') AND to_date(#{end_data_day},'YYYY-MM-DD HH24:MI:SS')+1 )"
    		+ ") order by data_time) ) where rom>1"
    		+ "</script>")
    @Results(value = {
    		@Result(property = "dataTime",column = "data_time"),
    		@Result(property = "papR",column = "papr")
    })
    List<EMonitorCurve> getAllPapr(String start_data_day,String end_data_day);
}


