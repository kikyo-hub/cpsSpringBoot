package cn.nrzt.cps.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {
	private BeanUtil() {}
	
	@SuppressWarnings("unchecked")
	public  static <T> T toObject(Class<T> clazz,Map<String,Object> map) {
		if (map == null) {
			return null;
		}		
		try {
			Object obj = clazz.newInstance();
			BeanUtils.populate(obj, map);
			return (T) obj;
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  static Map<String, Object>  fromObject(Object obj) {
		Map<String,Object> map=new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
