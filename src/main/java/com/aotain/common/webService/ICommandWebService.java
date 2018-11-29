package com.aotain.common.webService;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**
 * Created by leeji on 2017/7/6.
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ICommandWebService {
    public String command (@WebParam(name = "userName")String userName, @WebParam(name = "password")String password,
                           @WebParam(name = "dataType")int dataType, @WebParam(name = "dataClass")int dataClass,
                           @WebParam(name = "command")String command, @WebParam(name = "compressionFormat")int compressionFormat);
}
