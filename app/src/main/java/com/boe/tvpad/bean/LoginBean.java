package com.boe.tvpad.bean;

public class LoginBean {


    /**
     * name : 才健
     * state : s
     * message : 登录成功
     * padToken : YII9eRpknXVh3poAUSkDbg==
     * changePassword : 0
     */

    private String name;
    private String state;
    private String message;
    private String padToken;
    private String changePassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPadToken() {
        return padToken;
    }

    public void setPadToken(String padToken) {
        this.padToken = padToken;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }
}
