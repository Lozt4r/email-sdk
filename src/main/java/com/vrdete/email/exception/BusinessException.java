package com.vrdete.email.exception;

import com.vrdete.email.constant.IResultCode;

/**
 * @author fu
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException(IResultCode responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(IResultCode responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
