package com.weston.tools.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanUtil {
	public static void setProperties(Object bean, Map<String, Object> props) {
		Class<? extends Object> claz = bean.getClass();
		setProperties(bean, claz, props);
	}

	private static void setProperties(Object bean, Class<? extends Object> claz, Map<String, Object> props) {
		Field[] fields = claz.getDeclaredFields();
		int propSize = props.keySet().size();
		int propCount = 0;
		for (Field field : fields) {
			String fieldNm = field.getName();
			if (props.containsKey(fieldNm)) {
				try {
					PropertyDescriptor pd = new PropertyDescriptor(fieldNm, claz);
					Method mt = pd.getWriteMethod();
					mt.invoke(bean, props.get(fieldNm));
					propCount++;
				} catch (IntrospectionException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if(propSize != propCount){
			System.out.println("Field wasn't all seted");
		}
	}
}
