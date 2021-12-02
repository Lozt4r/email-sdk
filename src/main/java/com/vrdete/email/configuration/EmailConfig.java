package com.vrdete.email.configuration;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author Lozt
 */
public class EmailConfig {

    private String nickname;

    private String title;

    private Map<String, Object> contextMap;

    private List<File> fileList;

    private List<File> imgList;

    private List<String> target;

    public EmailConfig(String nickname, String title, Map<String, Object> contextMap, List<String> target) {
        this.nickname = nickname;
        this.title = title;
        this.contextMap = contextMap;
        this.target = target;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getContextMap() {
        return contextMap;
    }

    public void setContextMap(Map<String, Object> contextMap) {
        this.contextMap = contextMap;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public EmailConfig setFileList(List<File> fileList) {
        this.fileList = fileList;
        return this;
    }

    public List<File> getImgList() {
        return imgList;
    }

    public EmailConfig setImgList(List<File> imgList) {
        this.imgList = imgList;
        return this;
    }

    public List<String> getTarget() {
        return target;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

}
