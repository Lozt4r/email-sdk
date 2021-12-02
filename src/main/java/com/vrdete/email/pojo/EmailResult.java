package com.vrdete.email.pojo;


import com.vrdete.email.constant.IResultCode;
import com.vrdete.email.constant.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fu
 */
@Data
public class EmailResult implements Serializable {

    /**
     * 状态码: 成功、失败、或其他待定异常
     */
    private String ret;

    /**
     * 提示信息
     */
    private String code;

    private List<EmailStatus> dataList;

    public EmailResult ret(String ret) {
        this.ret = ret;
        return this;
    }

    public EmailResult code(String code) {
        this.code = code;
        return this;
    }
    public EmailResult dataList(List<EmailStatus> statusList) {
        this.dataList = statusList;
        return this;
    }
}
