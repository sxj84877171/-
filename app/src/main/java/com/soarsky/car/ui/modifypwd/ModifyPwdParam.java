package com.soarsky.car.ui.modifypwd;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ModifyPwdParam {

    private String status;
    private String message;
    /**
     * id : 16
     * token : null
     * username : zss
     * password : 698d51a19d8a121ce581499d7b701668
     * status : 0
     * createTime : 2016-11-14 14:40:10
     * idcard : 42012322222222222
     * email : null
     * tokenStatus : 4
     * loginStatus : null
     * isFinish : 1
     * phone : 13888888888
     * mac : null
     * lastTime : null
     */

    private DataBean data;

    public ModifyPwdParam(String status,String message){
        this.status=status;
        this.message=message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        private String id;
        private Object token;
        private String username;
        private String password;
        private String status;
        private String createTime;
        private String idcard;
        private Object email;
        private String tokenStatus;
        private Object loginStatus;
        private String isFinish;
        private String phone;
        private Object mac;
        private Object lastTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(String tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public Object getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(Object loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(String isFinish) {
            this.isFinish = isFinish;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getMac() {
            return mac;
        }

        public void setMac(Object mac) {
            this.mac = mac;
        }

        public Object getLastTime() {
            return lastTime;
        }

        public void setLastTime(Object lastTime) {
            this.lastTime = lastTime;
        }
    }
}
