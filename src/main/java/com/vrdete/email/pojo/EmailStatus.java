package com.vrdete.email.pojo;

import com.vrdete.email.configuration.EmailConfig;
import lombok.Data;

/**
 * @author Lozt
 */
@Data
public class EmailStatus {

    private String from;

    private String to;

    private Integer status;

    private Integer errorCode;

    private String errorMsg;

    private EmailConfig emailConfig;

}
