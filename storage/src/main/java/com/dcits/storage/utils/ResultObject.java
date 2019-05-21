package com.dcits.storage.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回信息类
 * @author xieds
 * @date 2019/3/5 9:29
 * @updater xieds
 * @updatedate 2019/3/5 9:29
 */
@ApiModel(value = "com.dcits.supervision.utils.ResultObject", description = "返回信息类")
public class ResultObject {

    @ApiModelProperty(value = "是否成功，true：成功，false：失败")
    private boolean success = true;
    @ApiModelProperty(value = "提示信息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private Object data;
    @ApiModelProperty(value = "错误字段")
    private String errorfield;

    private ResultObject() {
    }

    /**
     * 类初始化
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static ResultObject createInstance() {
        return createInstance(true);
    }

    public static ResultObject createInstance(boolean success) {
        return createInstance(success, (String)null);
    }

    public static ResultObject createInstance(boolean success, String message) {
        return createInstance(success, message, (Object)null);
    }

    public static ResultObject createInstance(boolean success, String message, Object data) {
        return createInstance(success, message, data, (String)null);
    }

    public static ResultObject createInstance(boolean success, String message, Object data, String errorfield) {
        ResultObject ro = new ResultObject();
        ro.setData(data);
        ro.setSuccess(success);
        if (message != null && !"".equals(message.trim())) {
            ro.setMessage(message);
        }

        ro.setErrorfield(errorfield);
        return ro;
    }

    /**
     * 是否成功
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (success) {
            if (isBlank(this.message)) {
                this.setMessage("成功");
            }
        } else if (isBlank(this.message)) {
            this.setMessage("失败");
        }

    }

    /**
     * 字符串是否为空
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    private static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorfield() {
        return this.errorfield;
    }

    public void setErrorfield(String errorfield) {
        this.errorfield = errorfield;
    }
}
