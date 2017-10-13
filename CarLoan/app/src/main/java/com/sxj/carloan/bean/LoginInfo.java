package com.sxj.carloan.bean;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/25.
 */

public class LoginInfo implements Serializable{

    public static final String ADMIN = "1" ;
    public static final String YWY = "2" ;
    public static final String DCY = "7";
    public static final String ZJL = "12" ;
    //yhmq1	yhmq1	123456	银行面签
    public static final String YHMQ = "20" ;

    private String username;
    private String password;
    private String token ;
    private String user_id ;
    private String user_name ;
    private String role ;


    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public boolean isDcy() {
        return DCY.equals(role);
    }

    public boolean isYwy(){
        return YWY.equals(role);
    }

    public boolean isAdmin(){
        return ADMIN.equals(role);
    }

    public boolean isZJL(){
        return ZJL.equals(role);
    }

    public boolean isYHMQ(){
        return YHMQ.equals(role);
    }

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
