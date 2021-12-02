package com.vrdete.email.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class MyAuthenticator extends Authenticator {
    String userName = "";
    String password = "";
    public MyAuthenticator() {

    }
    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    @Override
    protected PasswordAuthentication getPasswordAuthentication () {
        return new PasswordAuthentication(userName, password);
    }
}