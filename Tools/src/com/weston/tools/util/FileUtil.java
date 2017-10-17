package com.weston.tools.util;

import java.io.File;

public class FileUtil {
	public static String getSubFix(File file) {
		String fileName = file.getName();
		if (fileName.split("\\.").length > 1) {
			return fileName.split("\\.")[1];
		} else {
			return null;
		}
	}
}
