package com.fengz.personal.fourweeks.business1.model.entity;

/**
 * 检测App版本
 */
public class CheckVersionBean {

    /**
     * is_upgrade : true
     * version : 1.0.1
     * description : test
     * type : 1  1:普通升级  2:强制升级
     * url : http://xxx.com/xxx.apk
     */

    private boolean canUp;
//    private String version;
    private String upContent;
    private String type;
    private String upUrl;
    private String platform;

    /**
     * 都是普通登录
     * @return
     */
    public String getType() {
        return "1";
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCanUp() {
        return canUp;
    }

    public void setCanUp(boolean canUp) {
        this.canUp = canUp;
    }

    public String getUpContent() {
        return upContent;
    }

    public void setUpContent(String upContent) {
        this.upContent = upContent;
    }

    public String getUpUrl() {
        return upUrl;
    }

    public void setUpUrl(String upUrl) {
        this.upUrl = upUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
