package com.weston.tools.util;

import java.util.List;

public class ListUtil {
	public static <T> boolean isEmpty(List<T> lis){
		if(null == lis || lis.size()==0){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ListUtil.isEmpty(null));
	}
}
