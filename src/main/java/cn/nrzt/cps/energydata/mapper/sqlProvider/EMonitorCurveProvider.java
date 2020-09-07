package cn.nrzt.cps.energydata.mapper.sqlProvider;

import cn.nrzt.cps.archives.po.EMonitorCurve;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class EMonitorCurveProvider {

    public String  addEMonitorCurve(@Param("emonitorCurve") EMonitorCurve emonitorCurve){

        return new SQL(){{
            INSERT_INTO("E_MONITOR_CURVE");
            VALUES("monitor_id",emonitorCurve.getMonitorId());
            VALUES("data_time",emonitorCurve.getDataTime().toString());
            VALUES("ia",emonitorCurve.getIa().toString());
            VALUES("ib",emonitorCurve.getIb().toString());
            VALUES("ic",emonitorCurve.getIc().toString());
            VALUES("ua",emonitorCurve.getUa().toString());
            VALUES("ub",emonitorCurve.getUb().toString());
            VALUES("uc",emonitorCurve.getUc().toString());
            VALUES("pa",emonitorCurve.getPa().toString());
            VALUES("pb",emonitorCurve.getPb().toString());
            VALUES("pc",emonitorCurve.getPc().toString());
            VALUES("p",emonitorCurve.getP().toString());
        }}.toString();
    }
}
