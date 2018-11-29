package com.aotain.iqis.constant.originalBill;


/**
 * 导出状态
 * @author yinzf
 *
 */
public enum ExportStatus {
	
	UNPROCESSED("未处理"),
	PROCESSING("正在处理"),
	PROCESS_SUCCESS("处理成功"),
	PROCESS_FAIL("处理失败");
	
	private String description;

	private ExportStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static ExportStatus valueOf(int ordinal) {
		ExportStatus[] statuses = ExportStatus.values();
        if (ordinal < 0 || ordinal >= statuses.length) {
            return null;
        }
        return statuses[ordinal];
    }
	
}
