/*
 * TextProcessor.java
 *
 */
package com.aotain.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class IOUtil {
	 public static void writeToTxt(List<HashMap<String, Object>> contentList,String fileName,String timestamp){
	        File file = new File(fileName);
	        fileExists(file);
	        FileWriter fw = null;
	        BufferedWriter writer = null;
	        try {
	            fw = new FileWriter(file);
	            writer = new BufferedWriter(fw);
	            String bizIds [] = LocalConfig.getInstance().getBizIDs().split(",");
	            if (bizIds.length > 0) {
	            	for(int j = 0; j < bizIds.length;j++) {
	            		int tmepCount = 0;
	            		String contentLine = "";
	            		for(int i = 0; i < contentList.size(); i++){
	    	    			HashMap<String, Object> result = (HashMap<String, Object>) contentList.get(i);
	    	    			if(bizIds[j].equals(result.get("BIZ_ID").toString())) {
		            			tmepCount += 1;
		            			contentLine += result.get("HOSTNAME").toString() + " ";
			    				contentLine += result.get("BIZ_ID").toString() + "-up" + " ";
			    				contentLine += result.get("R_STATTIME").toString() + " ";
			    				contentLine += result.get("FLOW_UP").toString();
			    				writer.write(contentLine);
				                writer.newLine();//换行
				                contentLine = "";
				                contentLine += result.get("HOSTNAME").toString() + " ";
			    				contentLine += result.get("BIZ_ID").toString() + "-dn" + " ";
			    				contentLine += result.get("R_STATTIME").toString() + " ";
			    				contentLine += result.get("FLOW_DN").toString();
			    				writer.write(contentLine);
				                writer.newLine();//换行
		            		}
	            		}
	            		if(tmepCount == 0) {
	            			contentLine += LocalConfig.getInstance().getHostName() + " ";
		    				contentLine += bizIds[j] + "-up" + " ";
		    				contentLine += timestamp + " ";
		    				contentLine += "0";
		    				writer.write(contentLine);
			                writer.newLine();//换行
			                contentLine = "";
			                contentLine += LocalConfig.getInstance().getHostName() + " ";
		    				contentLine += bizIds[j] + "-dn" +  " ";
		    				contentLine += timestamp + " ";
		    				contentLine += "0";
		    				writer.write(contentLine);
			                writer.newLine();//换行
	            		}
	            	}
	            	writer.flush();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                writer.close();
	                fw.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	  // 判断文件是否存在 存在删除重新创建
      public static void fileExists(File file) {
    	  if (file.exists() && file.isFile()) {
    		  file.delete();
              System.out.println("file exists");
          } else {
	          System.out.println("file not exists, create it ...");
	          try {
	             file.createNewFile();
	          } catch (IOException e) {
	               // TODO Auto-generated catch block
	              e.printStackTrace();
	          }
	       }
      }
}