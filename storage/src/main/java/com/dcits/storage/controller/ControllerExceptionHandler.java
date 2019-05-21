package com.dcits.storage.controller;

import com.dcits.storage.utils.MessageInfo;
import com.dcits.storage.utils.ResultObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局捕获异常
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 异常处理
     * @param ex 异常类
     * @return ResultObject 结果信息类
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultObject exceptionHandler(Exception ex) {
        ResultObject resultObject = ResultObject.createInstance();
        String messageError = ex.getMessage();
        resultObject.setSuccess(false);
        if(messageError.contains("JSON parse error")) {
            resultObject.setMessage(messageError.substring(messageError.lastIndexOf("[\"")+2,messageError.lastIndexOf("\"]")) + MessageInfo.COLUMN_TYPE_ERROR);
        }
        else if(messageError.contains("com.mysql.jdbc.MysqlDataTruncation")) {
            resultObject.setMessage(messageError.split("'")[1] + MessageInfo.COLUMN_TOO_LONG);
        }
        else if(messageError.contains("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {
            resultObject.setMessage(messageError.split("'")[1] + MessageInfo.COLUMN_NOT_NULL);
        }
        else {
            resultObject.setMessage(MessageInfo.HANDLE_EXCEPTION);
        }
        return resultObject;
    }
}
