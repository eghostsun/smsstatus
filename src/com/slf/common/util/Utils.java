package com.slf.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Utils {

	
	public static String bytesToOrcHex(String src,String key)
	{
		String result = "";
		Hex hex = new Hex();
		String sign = new String(hex.encode(src.getBytes()));
		sign += key;
		byte temp[] = null;
		try {
			temp = KeyedDigestMD5.getKeyedDigest(hex.decode(sign.getBytes()),"".getBytes());
			for (int i=0; i<temp.length; i++){
				result+=Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
		}
		
		return result.toUpperCase();
	}
}
