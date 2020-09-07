package cn.nrzt.cps.system.controller;

import cn.nrzt.cps.system.service.DictService;
import cn.nrzt.cps.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags={" 系 统 设 置 "})
@RestController
@RequestMapping("/system/dict")
public class DictController {
    @Autowired
    DictService dictService;

    @GetMapping("/selectDicts")
    @ApiOperation(value = "查询字典")
    public List<Map<String,Object>> selectDicts(){
        return dictService.selectDicts();
    }

    @PostMapping("/deleteDicts")
    @ApiOperation(value = "删除字典")
    public WebResponse deleteDicts(@RequestBody Map<String,Object> dict){
        Map<String,Object> sort = null;
        List<Map<String,Object>> code = null;
        if(dict.get("sort") !=null && !"".equals( dict.get("sort"))  ){
            sort = (Map<String,Object>)dict.get("sort");
        }
        if( dict.get("code") !=null && !"".equals( dict.get("code")) ){
            code = (List<Map<String,Object>>)dict.get("code");
        }

        int i=  dictService.deleteDicts(sort,code);
        return new WebResponse( i>0?"success":"error");
    }

    @PostMapping("/updateDicts")
    @ApiOperation(value = "修改字典")
    public WebResponse updateDicts(@RequestBody Map<String,Object> dict){
        dict = (Map<String,Object>)dict.get("dict");
        int i= dictService.updateDicts(dict);
        return new WebResponse(  i>0?"success":"error" );
    }
}
