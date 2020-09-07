package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.EDiagnosisCurve;
import cn.nrzt.cps.archives.po.EDiagnosisDayRead;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ediagnosisCurveMapper")
public interface EDiagnosisCurveMapper {
    //日曲线
    // diagnosis_id 存在 ，单个设备日曲线 ，就是一天96条数据
    // diagnosis_id 不存在，就是查询所有设备日曲线, 就是每个设备96条数据
    @Select(" <script>" +
            " select " +
            " s.diagnosis_id, s.data_time,s.ia,s.ib,s.ic,s.ua,s.ub,s.uc,s.pa,s.pb,s.pc,s.p,(s.pap_r - lag(s.pap_r,1) over(order by s.data_time)) pap " +
            " from E_DIAGNOSIS_CURVE s" +
            " where to_date(to_char(s.data_time,'YYYY-MM-DD'),'YYYY-MM-DD')=to_date(#{data_day},'YYYY-MM-DD') " +
            " <if test='diagnosis_id!=-1'> and s.diagnosis_id=#{diagnosis_id}</if> " +
            " order by s.data_time asc " +
            " </script>")
    @Results(id="diagnosisCurveResult01"
            ,value={
            @Result( property = "diagnosisId", column = "diagnosis_id" )
            ,@Result( property = "dataTime", column = "data_time" )
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
            ,@Result( property = "papR", column = "pap_r" )
            ,@Result( property = "pap", column = "pap" )
    } )
    List<EDiagnosisCurve> getEDiagnosisCurveById(@Param("diagnosis_id") String diagnosis_id, @Param("data_day") String data_day);

    //指定日期的上一个周末
    //  trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1
    //指定日期的下一个周一
    //  trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7
    // 一个星期七天每一天的用量
    // diagnosis_id 存在 ，单个设备的周数据 ，就是 7 条数据
    // diagnosis_id 不存在，就是查询所有设备的周数据, 就是每个设备 7 条数据
    @Select(" <script>" +
            " select * from ( " +
            " select diagnosis_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e) rape" +
            " from e_diagnosis_day_read  " +
            " where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            " <if test='diagnosis_id!=-1'> and diagnosis_id=#{diagnosis_id}</if> " +
            //" and diagnosis_id=#{diagnosis_id}" +
            " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
            "       order by diagnosis_id asc,data_time asc" +
            " ) " +
            "where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " order by diagnosis_id asc,data_time asc" +
            " </script>")
    @Results(id="diagnosisDayReadResult01"
            ,value={
            @Result( property = "diagnosisId", column = "diagnosis_id" )
            ,@Result( property = "dataTime", column = "data_time" )
            ,@Result( property = "papE", column = "pape" )
            ,@Result( property = "papR", column = "papr" )
            ,@Result( property = "rapE", column = "rape" )
    } )
    List<EDiagnosisDayRead> getEDiagnosisDayReadOfWeekById(@Param("diagnosis_id") String diagnosis_id, @Param("data_day") String data_day);

    // diagnosis_id 存在 ，单个设备的周总量 ，就是 1 条数据
    // diagnosis_id 不存在，就是查询所有设备的周总量, 就是每个设备 1 条数据
    @Select(" <script>" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(diagnosis_id)   diagnosis_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +
            " select diagnosis_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e) rape" +
            " from e_diagnosis_day_read  " +
            " where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
            " <if test='diagnosis_id!=-1'> and diagnosis_id=#{diagnosis_id}</if> " +
            //" and diagnosis_id=#{diagnosis_id}" +
            " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
            "       order by diagnosis_id asc,data_time asc" +
            " ) " +
            "where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " group by  diagnosis_id  " +
//            " order by diagnosis_id asc,data_time asc" +
            " </script>")
    @ResultMap( "diagnosisDayReadResult01")
    List<EDiagnosisDayRead> getTotalEDiagnosisDayReadOfWeek(@Param("diagnosis_id") String diagnosis_id, @Param("data_day") String data_day);
    // 所有设备的日总量
    // diagnosis_id 字段没有处理
    @Select(" <script>" +
            " select   " +
            " max(data_time) data_time  " +
            " ,sum(papr)  papr,sum(pape)  papr,sum(rape)  papr " +
            " from (" +
            " select   " +
            " max(data_time)  data_time " +
            " ,max(diagnosis_id)   diagnosis_id " +
            " ,sum(papr) papr  ,sum(pape) pape ,sum(rape) rape  " +
            " from ( " +
            " select diagnosis_id,data_time" +
            " ,abs( (lag(pap_r,1,0) over(order by data_time))-pap_r) papr" +
            " ,abs( (lag(pap_e,1,0) over(order by data_time))-pap_e) pape" +
            " ,abs( (lag(rap_e,1,0) over(order by data_time))-rap_e) rape" +
            " from e_diagnosis_day_read  " +
            " where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy') " +
//                    " <if test='diagnosis_id!=-1'> and diagnosis_id=#{diagnosis_id}</if> " +
            " and (    to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') - 1 ,'YYYY-MM-DD') " +
            "       or to_char(data_time,'YYYY-MM-DD') = to_char(trunc(to_date(#{data_day},'YYYY-MM-DD'),'iw') + 7 ,'YYYY-MM-DD')  ) " +
            "       order by diagnosis_id asc,data_time asc" +
            " ) " +
            "where to_char(data_time,'yyyy')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'yyyy')  " +
            " and  to_char(data_time,'iW')=to_char(to_date(#{data_day},'YYYY-MM-DD'),'iw')  " +
            " group by  diagnosis_id  " +
            //            " order by diagnosis_id asc,data_time asc" +
            ") " +
            " </script>")
    @Results(id="diagnosisDayReadResult02"
            ,value={
//            @Result( property = "diagnosisId", column = "diagnosis_id" )
            @Result( property = "dataTime", column = "data_time"  )
            ,@Result( property = "papE", column = "pape" )
            ,@Result( property = "papR", column = "papr" )
            ,@Result( property = "rapE", column = "rape" )
    } )
    List<EDiagnosisDayRead> getAllTotalEDiagnosisDayReadOfWeek(@Param("diagnosis_id") String diagnosis_id, @Param("data_day") String data_day);
//    求所有诊断点任意两小时间隔的总用电量
	
	/*
	 * select distinct dt,sum(pap) over(PARTITION BY dt) pap from （select distinct
	 * dd.dt, t.DIAGNOSIS_ID, --t.data_time,t.pap_r, --(max(t.pap_r) over (PARTITION
	 * BY t.DEV_ADDRESS,dd.dt )) max, --(min(t.pap_r) over (PARTITION BY
	 * t.DEV_ADDRESS,dd.dt )) min, ((max(t.pap_r) over (PARTITION BY
	 * t.DIAGNOSIS_ID,dd.dt )) - (min(t.pap_r) over (PARTITION BY
	 * t.DIAGNOSIS_ID,dd.dt )) ) pap from e_diagnosis_curve t , ( select * from
	 * (select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12 dt from (select
	 * '2020-08-09' time from dual) connect by level<= 13 ) d where d.dt <=
	 * to_date('2020-08-10','YYYY-MM-DD HH24:MI:SS') ) dd where t.data_time <= dd.dt
	 * and t.data_time>= (dd.dt-1/12) order by dt, DIAGNOSIS_ID）order by dt
	 */
	 
  
//求任意诊断点任意两小时间隔的总用电量
	/*
	 * select distinct dt,sum(pap) over(PARTITION BY dt) pap from （select distinct
	 * dd.dt, t.DIAGNOSIS_ID, --t.data_time,t.pap_r, --(max(t.pap_r) over (PARTITION
	 * BY t.DEV_ADDRESS,dd.dt )) max, --(min(t.pap_r) over (PARTITION BY
	 * t.DEV_ADDRESS,dd.dt )) min, ((max(t.pap_r) over (PARTITION BY
	 * t.DIAGNOSIS_ID,dd.dt )) - (min(t.pap_r) over (PARTITION BY
	 * t.DIAGNOSIS_ID,dd.dt )) ) pap from e_diagnosis_curve t , ( select * from
	 * (select to_date(time,'YYYY-MM-DD HH24:MI:SS') +(level-1)/12 dt from (select
	 * '2020-08-09' time from dual) connect by level<= 13 ) d where d.dt <=
	 * to_date('2020-08-10','YYYY-MM-DD HH24:MI:SS') ) dd where
	 * t.DIAGNOSIS_ID=#{DIAGNOSIS_ID} and t.data_time <= dd.dt and t.data_time>=
	 * (dd.dt-1/12) order by dt, DIAGNOSIS_ID）order by dt
	 */
  
  //查询某个诊断点任意天的用电量
	/*
	 * select * from (select t.DIAGNOSIS_ID,t.data_time,t.pap_r,(t.pap_r -
	 * lag(t.pap_r,1) over(PARTITION BY t.DIAGNOSIS_ID order by t.data_time
	 * asc))pap,rownum as rom from (select * from E_DIAGNOSIS_DAY order by
	 * data_time) t where t.data_time<= to_date('2020-08-11','YYYY-MM-DD
	 * HH24:MI:SS') and t.data_time>= to_date('2020-08-10','YYYY-MM-DD
	 * HH24:MI:SS')-1 and t.DIAGNOSIS_ID=10000039 order by rom) where rom>1
	 */
  
  //查询所有诊断点任意天的用电量
	/*
	 * select * from (select data_time,pap,rownum rom from(select distinct
	 * d.data_time, sum(pap) over(PARTITION BY d.data_time) pap from (select
	 * t.DIAGNOSIS_ID,t.data_time,t.pap_r,(t.pap_r - lag(t.pap_r,1) over(PARTITION
	 * BY t.DIAGNOSIS_ID order by t.data_time asc))pap from (select * from
	 * E_DIAGNOSIS_DAY order by data_time) t where t.data_time<=
	 * to_date('2020-08-11','YYYY-MM-DD HH24:MI:SS') and t.data_time>=
	 * to_date('2020-08-10','YYYY-MM-DD HH24:MI:SS')-1 )d order by d.data_time) )
	 * where rom>1
	 */
}
