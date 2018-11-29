package com.aotain.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	private File file=null;
	private String outDir;
	private String inDir;
	private String outFileNmae;
	private String inFileName;
	private int outWidth=100;
	private int outHeight=100;
	private boolean proportion = true;//是否等比缩放
	public ImageUtil() {
		this.outDir = null;
		this.inDir = null;
		this.outFileNmae = null;
		this.inFileName = null;
		this.outWidth = 100;
		this.outHeight = 100;
	}
	public String getOutDir() {
		return outDir;
	}
	public void setOutDir(String outDir) {
		this.outDir = outDir;
	}
	public String getInDir() {
		return inDir;
	}
	public void setInDir(String inDir) {
		this.inDir = inDir;
	}
	public String getOutFileNmae() {
		return (outFileNmae+inFileName.substring(inFileName.indexOf(".")));
	}
	public void setOutFileNmae(String outFileNmae) {
		this.outFileNmae = outFileNmae;
	}
	public String getInFileName() {
		return inFileName;
	}
	public void setInFileName(String inFileName) {
		this.inFileName = inFileName;
	}
	public int getOutWidth() {
		return outWidth;
	}
	public void setOutWidth(int outWidth) {
		this.outWidth = outWidth;
	}
	public int getOutHeight() {
		return outHeight;
	}
	public void setOutHeight(int outHeight) {
		this.outHeight = outHeight;
	}
	public void setWidthAndHeight(int width, int height) {    
        this.outWidth = width;   
        this.outHeight = height;    
    }  

	public long getPicSize(String path){
		file=new File(path);
		return file.length();
	}
	
	public String propressPic(){
		file=new File(this.getInDir()+"\\"+this.getInFileName());

		if(!file.exists()){
			return "";
		}
		try {
			Image img=ImageIO.read(file);
			if(img.getWidth(null)==-1){
				return "no";
			}else{
				int newWidth;
				int newHeight;
				if(this.proportion==true){
					double rate1=img.getWidth(null)/outWidth+0.1;
					double rate2=img.getHeight(null)/outHeight+0.1;
					double rate=rate1>rate2?rate1:rate2;
					if((int)rate<1){
						newWidth=img.getWidth(null);
						newHeight=img.getHeight(null);						
					}else{
						newWidth=(int) (img.getWidth(null)/rate);
						newHeight=(int) (img.getHeight(null)/rate);						
					}
				}else{
					newWidth=outWidth;
					newHeight=outHeight;
				}
				
				BufferedImage bi=new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				bi.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out=new FileOutputStream(outDir+"\\"+getOutFileNmae());
				ImageIO.write(bi, "jpg", out);
				//JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
				//encoder.encode(bi);
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}
	public String propressPic(String inDir,String outDir,String inFileName,String outFileName){
		this.inDir=inDir;
		this.inFileName=inFileName;
		this.outDir=outDir;
		this.outFileNmae=outFileName;
		return propressPic();
	}
	
	public String propressPic(String inDir,String outDir,String inFileName,String outFileName,int width,int height,boolean pg){
		this.inDir=inDir;
		this.inFileName=inFileName;
		this.outDir=outDir;
		this.outFileNmae=outFileName;
		setWidthAndHeight(width,height);
		this.proportion = pg;
		return propressPic();
	}
}
