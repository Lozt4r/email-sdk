package com.vrdete.email.configuration;

/**
 * @author Lozt
 */
public class SenderConfig {

    private String host = "smtp.qq.com";

    private String port = "25";

    private boolean debug = false;

    private boolean useSsl = false;

    private final String username;

    private final String password;


    public SenderConfig(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SenderConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public SenderConfig setUseSsl(boolean useSsl) {
        this.useSsl = useSsl;
        return this;
    }

    public String getHost() {
        return host;
    }

    public boolean isUseSsl() {
        return useSsl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public SenderConfig setPort(String port) {
        this.port = port;
        return this;
    }

    public boolean isDebug() {
        return debug;
    }

    public SenderConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

}
