package com.jxut.util;

public class StringUtil {
	public static boolean empty(Object obj){ //ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
		boolean flag = true;//true ÊÇ¿ÕµÄ
		if(obj != null && !"".equals(obj.toString())){
			flag = false;
		}
		return flag;
		
	}

}
