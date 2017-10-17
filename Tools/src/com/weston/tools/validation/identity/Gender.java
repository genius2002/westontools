package com.weston.tools.validation.identity;

public enum Gender {
	Male("男","Male","01"),Female("女","Female","00");
	private String cnName;
	private String enName;
	private String code;
	private Gender(String cnName,String enName,String code){
		this.cnName=cnName;
		this.enName=enName;
		this.code=code;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
