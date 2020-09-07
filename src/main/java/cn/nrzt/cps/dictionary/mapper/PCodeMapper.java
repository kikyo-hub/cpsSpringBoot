package cn.nrzt.cps.dictionary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import cn.nrzt.cps.web.Selectable;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PCodeMapper {
	@Select(value = { "SELECT * FROM P_CODE WHERE CODE_TYPE =#{codeType}" })
	@Results(id = "pcode", value ={
            @Result(column = "NAME", property = "key", 		javaType = String.class),
            @Result(column = "NAME", property = "display", 	javaType = String.class),
            @Result(column = "VALUE", property = "value")
    })
	List<Selectable> findPCode(String codeType);	
	
	@Select(value = { "SELECT * FROM P_CODE WHERE CODE_TYPE =#{codeType} order by value" })
	@ResultMap("pcode")
	List<Selectable> findPCode2(String codeType);

}
