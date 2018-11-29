package com.aotain.common.webService;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by leeji on 2017/7/6.
 */
public class CommandWebService implements ICommandWebService {
    @Override
    public String command(String userName, String password, int dataType, int dataClass, String command, int compressionFormat) {
    	return null;
    }
}
