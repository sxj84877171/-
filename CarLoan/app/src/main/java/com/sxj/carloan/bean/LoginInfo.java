package com.sxj.carloan.bean;

/**
 * Created by admin on 2017/6/25.
 */

public class LoginInfo{
    private String username;
    private String password;
    private String token ;
    private String user_id ;
    private String user_name ;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
