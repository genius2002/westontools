package com.weston.tools.util;

public class StringUtil {

	private StringUtil() {
	}

	public static boolean isEmpty(String param) {
		if (null == param || param.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEqual(String sour, String targ) {
		boolean result = true;
		if (null == sour || null == targ) {
			result = false;
			return result;
		} else {
			return sour.equals(targ);
		}
	}

	public static boolean isEqualIgnoreCase(String sour, String targ) {
		boolean result = true;
		if (null == sour || null == targ) {
			result = false;
			return result;
		} else {
			return sour.equalsIgnoreCase(targ);
		}
	}
	
	public static String byte2HexStr(byte b){
		return Long.toHexString(b&0xff);
	}
	
	public static String byte2BinaryStrr(byte b){
		return Long.toBinaryString(b&0xff);
	}
}
