package cn.nrzt.cps.energyuse.mapper;

import org.apache.ibatis.annotations.Select;

public interface TotalEnergyMapper {

	@Select("SELECT COUNT(1) AS BUILDING FROM C_BUILDING")
	int getBuilding();

	@Select("SELECT COUNT(1) AS MONITOR FROM C_MONITOR")
	int getMonitor();

	@Select("SELECT COUNT(1) AS DIAGNOSIS FROM C_DIAGNOSIS")
	int getDiagnosis();
}
