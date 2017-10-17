package com.weston.tools.validation.identity;
import java.util.Date;


public class IDCardInfo {
	private String province;
	private String city;	
	private String region;
	private Date birthday;
	private Gender gender;
	private int year;
	private int month;
	private int day;
	
	public String toString(){
		return "Province:"+this.province+";City:"+this.city+";Region:"+this.region+";Gender:"+this.gender.getEnName()+";Birthday:"+this.year+"-"+this.month+"-"+this.day;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}
