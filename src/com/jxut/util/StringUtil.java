package com.jxut.util;

public class StringUtil {
	public static boolean empty(Object obj){ //�ж��ַ����Ƿ�Ϊ��
		boolean flag = true;//true �ǿյ�
		if(obj != null && !"".equals(obj.toString())){
			flag = false;
		}
		return flag;
		
	}

}
