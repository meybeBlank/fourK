package com.fengz.personal.fourweeks.business1.model.entity;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：用户Bean
 */
public class UserModel {

    /**
     * key : 00d91e8e0cca2b76f515926a36db68f5
     * phone : 13511111112
     * name : null
     * passwd : 123654
     * createTime : 2019-01-18 17:05:57
     */
    private String key;
    private String phone;
    private Object name;
    private String passwd;
    private String createTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
