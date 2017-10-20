package com.weston.cmd.menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Command extends BaseAction implements ICommand {
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private String clasz;
	private String method;

	@Override
	public String fireCommand(String command) {
		//System.out.println("Command [" + command + "] fired @"+ this.getParent().getName());
		if (null != clasz && clasz.length() > 0) {
			try {
				Class<?> clz = Class.forName(clasz);
				Object obj = clz.newInstance();
				Class<?>[] paramTypes = new Class[parameters.size()];
				Object[] paramValues = new Object[parameters.size()];
				for (int i = 0; i < parameters.size(); i++) {
					Parameter param = parameters.get(i);
//					String name = param.getName();
					String type = param.getType();
					String value = param.getValue();
					String format = param.getFormat();
					Class<?> paramClass = Class.forName(type);
					paramTypes[i] = paramClass;
					Object paramValue = getValue(value, paramClass, format);
					paramValues[i] = paramValue;
				}
				Method mth = clz.getMethod(method, paramTypes);
				mth.invoke(obj, paramValues);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Object getValue(String value, Class<?> paramClass, String format) {
		if (null == paramClass || paramClass == java.lang.String.class) {
			return value;
		} else if (paramClass == java.lang.Integer.class) {
			return Integer.valueOf(value);
		} else {
			return null;
		}
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public String getClasz() {
		return clasz;
	}

	public void setClasz(String clasz) {
		this.clasz = clasz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
