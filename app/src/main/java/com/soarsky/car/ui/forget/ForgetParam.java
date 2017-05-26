package com.soarsky.car.ui.forget;

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

public class ForgetParam {


    /**
     * token : null
     * userName : yu
     * idcard : 430723199011012215
     * queryPwd :
     * isBind : 0
     * tokenStatus : 4
     * isFinish : 1
     * loginStatus : null
     * lastTime : null
     * mac : null
     * sex : null
     * email : null
     * flist : null
     * phone : 13544204367
     * createTime : 2016-12-06 14:03:42
     * name : null
     * id : 214
     * status : 0
     * password : zZ0dY3
     */

    private DataBean data;
    /**
     * data : {"token":null,"userName":"yu","idcard":"430723199011012215","queryPwd":"","isBind":"0","tokenStatus":"4","isFinish":"1","loginStatus":null,"lastTime":null,"mac":null,"sex":null,"email":null,"flist":null,"phone":"13544204367","createTime":"2016-12-06 14:03:42","name":null,"id":214,"status":"0","password":"zZ0dY3"}
     * message : 操作成功！
     * status : 0
     */

    private String message;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        private Object token;
        private String userName;
        private String idcard;
        private String queryPwd;
        private String isBind;
        private String tokenStatus;
        private String isFinish;
        private Object loginStatus;
        private Object lastTime;
        private Object mac;
        private Object sex;
        private Object email;
        private Object flist;
        private String phone;
        private String createTime;
        private Object name;
        private int id;
        private String status;
        private String password;

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getQueryPwd() {
            return queryPwd;
        }

        public void setQueryPwd(String queryPwd) {
            this.queryPwd = queryPwd;
        }

        public String getIsBind() {
            return isBind;
        }

        public void setIsBind(String isBind) {
            this.isBind = isBind;
        }

        public String getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(String tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public String getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(String isFinish) {
            this.isFinish = isFinish;
        }

        public Object getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(Object loginStatus) {
            this.loginStatus = loginStatus;
        }

        public Object getLastTime() {
            return lastTime;
        }

        public void setLastTime(Object lastTime) {
            this.lastTime = lastTime;
        }

        public Object getMac() {
            return mac;
        }

        public void setMac(Object mac) {
            this.mac = mac;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getFlist() {
            return flist;
        }

        public void setFlist(Object flist) {
            this.flist = flist;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
