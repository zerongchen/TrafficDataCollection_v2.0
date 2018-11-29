package com.aotain.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;

public class RSACache {
		
	private static final long sCacheTtl = 24*60*60;
	private static KeyPair keyPair;
	private static long expireTime;
	
	public RSACache() throws Exception{
		if(keyPair == null){
			String FilePath = this.getClass().getResource("/").getPath()+"private.key";
			if(System.getProperty("os.name").toLowerCase().indexOf("window")>=0)
				FilePath = FilePath.replaceFirst("/", "").replace("%20", " ");
			FileInputStream fis = new FileInputStream(FilePath);
			keyPair = RSA.getKeyPair(fis);
		}
		expireTime = System.currentTimeMillis() + sCacheTtl; 
    }  
	
	public static KeyPair getRSACache() {
		try{
		    if ( keyPair != null) {
		        if (expireTime > System.currentTimeMillis()){
		        	new RSACache();
		        }
		    }else new RSACache();
		    return keyPair;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
}
