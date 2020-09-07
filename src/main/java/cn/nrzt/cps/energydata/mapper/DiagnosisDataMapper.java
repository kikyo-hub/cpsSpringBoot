package cn.nrzt.cps.energydata.mapper;

import cn.nrzt.cps.archives.po.CDiagnosis;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("diagnosisDataMapper")
public interface DiagnosisDataMapper {
    //monitor_id 存在 ，查询单个监测点
    //monitor_id 不存在 ，查询所有监测点
    @Select(" <script>" +
            " select * from c_diagnosis " +
            " <if test='diagnosis_id!=-1'> where diagnosis_id=#{diagnosis_id}</if> " +
            " </script>")
    @Results(id="diagnosisResult01"
            ,value={
            @Result( property = "diagnosisId", column = "diagnosis_id" )
            ,@Result( property = "diagnosisName", column = "diagnosis_name" )          
            ,@Result( property = "diagnosisType", column = "diagnosis_type" )
            ,@Result( property = "creator", column = "creator" )
            ,@Result( property = "user", column = "creator", one = @One(select="cn.nrzt.cps.user.mapper.UserMapper.getUserById"))
            ,@Result( property = "monitor", column = "diagnosis_id", many = @Many(select="cn.nrzt.cps.energydata.mapper.MonitorDataMapper.getCMonitorsByDiagnosisId"))
    } )
    List<CDiagnosis> getCDiagnosises(@Param("diagnosis_id") String diagnosis_id);


}
