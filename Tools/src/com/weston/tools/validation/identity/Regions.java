package com.weston.tools.validation.identity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Regions {
	private HashMap<String, String> data = new HashMap<String, String>();
	private static Regions instance = null;

	public synchronized static Regions getInstance() {
		if (instance == null) {
			instance = new Regions();
		}
		return instance;
	}

	private Regions() {
		init();
	}

	private void init() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this
					.getClass().getClassLoader()
					.getResourceAsStream("adminReg.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] lin = line.split(",");
				data.put(lin[0], lin[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName(String code) {
		if (null != data) {
			return data.get(code);
		}
		return "";
	}

	public String getProvince(String code) {
		String codeStr = code.toString();
		String nCodeStr = codeStr.substring(0, 2);
		if (null != data) {
			return data.get(nCodeStr + "0000");
		}
		return "";
	}

	public String getCity(String code) {
		String codeStr = code.toString();
		String nCodeStr = codeStr.substring(0, 4);
		if (null != data) {
			return data.get(nCodeStr + "00");
		}
		return "";
	}

	public String getRegion(String code) {
		if (null != data) {
			return data.get(code);
		}
		return "";
	}
}
