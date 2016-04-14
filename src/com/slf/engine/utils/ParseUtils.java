package com.slf.engine.utils;

public class ParseUtils {

	public static StringBuffer parseSmsSms(String s,String add[])
	{
		if(null == s)
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		String str[] = s.split("\\{");
		
		for(int i = 0; i < str.length; i++)
		{
			if(i == 0)
			{
				buffer.append(str[i]);
			}else{
				buffer.append(str[i].substring(str[i].indexOf("}") + 1, str[i].length()));
			}
			if(i < str.length -1)
			{
				int len = Integer.valueOf(str[i+1].substring(0, str[i+1].indexOf("}")));
				if(i < add.length)
				{
					buffer.append(add[i].substring(0, len));
				}
			}
		}
		
		return buffer;
	}
}
