package com.aotain.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.aotain.tdc.constant.FileType;

/**
 * 文件类型判断工具类
 * @author yinzf
 * @createtime 2014年11月13日 下午6:39:50
 */
public class FileTypeValidateUtils {

	/**
	 * 将文件头转换成16进制字符串 
	 * @param src 字节数组
	 * @return 16进制字符串 
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 得到文件头(16进制字符串 )
	 * @param filePath 文件路径
	 * @return 16进制字符串 
	 * @throws IOException
	 */
	private static String getFileContent(String filePath) throws IOException {
		byte[] b = new byte[28];
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			inputStream.read(b, 0, 28);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
		return bytesToHexString(b);
	}
	
	private static String getFileContent(InputStream input){
		byte[] bytes = new byte[28];
		try {
			IOUtils.readFully(input, bytes, 0, 28);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytesToHexString(bytes);
	}

	/**
	 * 判断文件类型
	 * @param filePath 文件路径
	 * @return 文件类型
	 * @throws IOException
	 */
	public static FileType getType(String filePath) throws IOException {
		String fileHead = getFileContent(filePath);
		if (StringUtils.isBlank(fileHead)) {
			return null;
		}
		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}
	
	/**
	 *  判断文件类型
	 * @param inputStream 文件输入流
	 * @return 文件类型
	 */
	public static FileType getType(InputStream input){
		String fileHead = getFileContent(input);
		if(StringUtils.isBlank(fileHead)){
			return null;
		}
		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}
	
	/**
	 *  判断文件类型
	 * @param input 文件输入流
	 * @param fileTypes 文件类型
	 * @return
	 */
	public static FileType getType(InputStream input, FileType[] fileTypes){
		String fileHead = getFileContent(input);
		if(StringUtils.isBlank(fileHead)){
			return null;
		}
		fileHead = fileHead.toUpperCase();
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}
	
	/**
     * 获取文件类型
     * @param input 输入流
     * @return
     */
    public static FileType getFileType(InputStream input){
    	FileType fileType = null;
		try {
	    	input.mark(1024);
			fileType = FileTypeValidateUtils.getType(input);
			input.reset();
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
		return fileType;
    }

	public static void main(String args[]) throws Exception {
		System.out.println(FileTypeValidateUtils.getType("d:\\test2.xlsx"));
	}
}
