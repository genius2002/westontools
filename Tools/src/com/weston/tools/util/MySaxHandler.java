package com.weston.tools.util;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.weston.cmd.menu.Command;
import com.weston.cmd.menu.IAction;
import com.weston.cmd.menu.Menu;
import com.weston.cmd.menu.Parameter;

public class MySaxHandler extends DefaultHandler {
	private IAction currentMenu = null;
	private IAction currentCommand = null;
//	private Parameter currentparamenter = null;
	private IAction top = null;

	public MySaxHandler(Menu base) {
		this.top = base;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("menu")) {
			if (null == currentMenu) {
				currentMenu = top;
			} else {
				Menu menu = new Menu();
				menu.setParent(currentMenu);
				currentMenu.getChildren().add(menu);
				currentMenu = menu;
			}

			if (null != attributes && attributes.getLength() > 0) {
				int size = attributes.getLength();
				for (int i = 0; i < size; i++) {
					String name = attributes.getQName(i);
					String value = attributes.getValue(i);
					setField(currentMenu, name, value);
				}
			}
		} else if (qName.equalsIgnoreCase("command")) {
			Command cmd = new Command();
			if (null != currentMenu) {
				currentMenu.getChildren().add(cmd);
				cmd.setParent(currentMenu);
			}
			currentCommand = cmd;
			if (null != attributes && attributes.getLength() > 0) {
				int size = attributes.getLength();
				for (int i = 0; i < size; i++) {
					String name = attributes.getQName(i);
					String value = attributes.getValue(i);
					// System.out.println("[" + name + ":" + value + "]");
					setField(currentCommand, name, value);
				}
			}
		} else if (qName.equalsIgnoreCase("parameter")) {
			Parameter param = new Parameter();
			if (null != currentCommand) {
				((Command) currentCommand).getParameters().add(param);
			}
			if (null != attributes && attributes.getLength() > 0) {
				int size = attributes.getLength();
				for (int i = 0; i < size; i++) {
					String name = attributes.getQName(i);
					String value = attributes.getValue(i);
					// System.out.println("[" + name + ":" + value + "]");
					setField(param, name, value);
				}
			}

		}
	}

	private void setField(Object obj, String fieldName, Object fieldValue) {
		try {
			Class<?> clasz = obj.getClass();
			Field field = getField(clasz, fieldName);
			if (null != field) {
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, clasz);
				Method m = pd.getWriteMethod();
				if (null != m) {
					m.invoke(obj, fieldValue);
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Field getField(Class<?> clasz, String fieldName) {
		Field field = null;
		try {
			field = clasz.getDeclaredField(fieldName);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		if (null == field) {
			Class<?> suprClz = clasz.getSuperclass();
			if (null != suprClz && !suprClz.equals(Object.class)) {
				field = getField(suprClz, fieldName);
			}
		}
		return field;
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("menu")) {
			if (null != currentMenu.getParent()) {
				currentMenu = currentMenu.getParent();
			}
		} else if (qName.equalsIgnoreCase("command")) {
			if (null != currentCommand) {
				currentCommand = null;
			}
		} else if (qName.equalsIgnoreCase("parameter")) {

		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// System.out.println(new String(ch) + start + length);
	}

}