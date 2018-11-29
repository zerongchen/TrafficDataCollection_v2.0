package com.aotain.iqis.constant.uploadDownload;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 文件类型
 * @author yinzf 
 * @createtime 2015年1月5日 下午12:39:33
 */
public enum AttaFileType {
	
    DOC("文档" ,"docs"),
    VIDEO("视频", "videos"),
    OTHER("其他", "others");
	
	private String type;
	
    private String dirName;
    
	public String getType() {
		return type;
	}

	public String getDirName() {
		return dirName;
	}

	private AttaFileType(String type, String dirName) {
		this.type = type;
		this.dirName = dirName;
	}
	
	public static AttaFileType getByDirName(String dirName) {
		AttaFileType fileType = null;
        for(AttaFileType item: values()){
            if (item.getDirName().equals(dirName)){
            	fileType = item;
                break;
            }
        }
        return fileType;
    }
	
	public static AttaFileType getByType(String type) {
		AttaFileType fileType = null;
        for(AttaFileType item: values()){
            if (item.getType().equals(type)){
            	fileType = item;
                break;
            }
        }
        return fileType;
    }
	
	public static List<KeyValueDTO> getAttaFileTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(AttaFileType item : AttaFileType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(item.getDirName());
			dto.setValue(item.getType());
			dtoes.add(dto);
		}
		return dtoes;
	}
}
