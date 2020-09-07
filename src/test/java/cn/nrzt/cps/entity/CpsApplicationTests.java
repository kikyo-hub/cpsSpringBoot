package cn.nrzt.cps.entity;

import cn.nrzt.cps.application.CpsApplication;
import cn.nrzt.cps.archives.controller.EnterpriseController;
import cn.nrzt.cps.archives.mapper.CCollectionRuleMapper;
import cn.nrzt.cps.archives.mapper.EnterpriseMapper;
import cn.nrzt.cps.archives.po.CCollectionRule;
import cn.nrzt.cps.energydata.mapper.MonitorDataMapper;

import cn.nrzt.cps.energydata.controller.DiagnosisDataController;
import cn.nrzt.cps.energydata.controller.MonitorDataController;
import cn.nrzt.cps.energydata.mapper.EDiagnosisCurveMapper;
import cn.nrzt.cps.energydata.mapper.EMonitorCurveMapper;
//import cn.nrzt.cps.monitor.mapper.monitorMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpsApplication.class)
@WebAppConfiguration
public class CpsApplicationTests {
    @Before public void init() {
        System.out.println("开始测试-----------------");
    }
    @After public void after() {  System.out.println("测试结束-----------------");  }
    @Autowired
    CCollectionRuleMapper cCollectionRuleMapper ;
    @Autowired
    MonitorDataController mController;
    @Autowired
    DiagnosisDataController dController;
    @Autowired
    EMonitorCurveMapper emonitorCurveMapper;
    @Autowired
    EnterpriseMapper en;

    @Test
    public void context() {
System.out.println("---------\n"+ en.getAllEnterprisedata());
//    	CCollectionRule cCollectionRule = new CCollectionRule();
//    	cCollectionRule.setCollectionRuleId(100002);
//    	cCollectionRule.setCollectionMode("00");
//    	cCollectionRule.setRuleName("123");
//    	cCollectionRule.setCollectionInterval(1);
//    	cCollectionRule.setCollectionType("1");collectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleIdcollectionRuleId
//    	
//    	cCollectionRule.setRuleDesc("11122");      
//    	cCollectionRule.setSaveTimeFlag("12");
//      System.out.println(cCollectionRuleMapper.inster(cCollectionRule));

//        System.out.println(monitorDataMapper.getCMonitors().get(0).getMonitorCurves().size());


//        List<EMonitorCurve> cm=emonitorCurveMapper.getEMonitorCurveById("10000015","2020-06-01");
//        List<EMonitorCurve> cm=emonitorCurveMapper.getEMonitorCurveOf4HById("10000015","2020-06-01");
//        List<EMonitorCurve> cm=emonitorCurveMapper.getEMonitorCurveOf2HById("10000015","2020-06-01");
//        List<EMonitorCurve> cm=emonitorCurveMapper.getTotalEMonitorCurveOf4H("10000015","2020-06-01");
//        List<EMonitorCurve> cm=emonitorCurveMapper.getTotalEMonitorCurveOf2H("10000015","2020-06-01");

//        List<EMonitorDayRead> cm=emonitorCurveMapper.getEMonitorDayReadOfDayById("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getEMonitorDayReadOfWeekById("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getEMonitorDayReadOfMonthById("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getTotalEMonitorDayReadOfDay("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getTotalEMonitorDayReadOfWeek("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getTotalEMonitorDayReadOfMonth("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getAllTotalEMonitorDayReadOfDay("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getAllTotalEMonitorDayReadOfWeek("10000015","2020-06-01");
//        List<EMonitorDayRead> cm=emonitorCurveMapper.getAllTotalEMonitorDayReadOfMonth("10000015","2020-06-01");
//        List<EDiagnosisCurve> cm= ediagnosisCurveMapper.getEDiagnosisCurveById("10000015","2020-06-01");
//        List<EDiagnosisDayRead> cm= ediagnosisCurveMapper.getEDiagnosisDayReadOfWeekById("10000015","2020-06-01");
//        List<EDiagnosisDayRead> cm= ediagnosisCurveMapper.getTotalEDiagnosisDayReadOfWeek("10000015","2020-06-01");
//        List<EDiagnosisDayRead> cm= ediagnosisCurveMapper.getAllTotalEDiagnosisDayReadOfWeek("10000015","2020-06-01");
//        System.out.println("----------数据----------"+cm );
//        System.out.println("----------数据长度----------"+cm.size() );

    }


}
