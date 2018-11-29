package com.aotain.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	
	public static String getMD5Code(String source) { 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(source.getBytes()); 
			byte b[] = md.digest(); 
			int i; 
			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if (i < 0) 
					i += 256; 
				if (i < 16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			} 
			return buf.toString(); 
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		return "";
	}
	
	public static void main(String agrs[]) { 
		System.out.println(getMD5Code("123456"));
		System.exit(0);
	} 
}
