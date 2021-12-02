package com.vrdete.email.constant;

import java.io.Serializable;

/**
 * @author fu
 */
public interface IResultCode extends Serializable {
    String getMessage();

    String getCode();
}
