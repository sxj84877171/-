package com.soarsky.car.ui.license.pwd;

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
 * 该类为 设置查询密码后台返回的bean
 */

public class LicensePwdParam {


    /**
     * message : 操作成功！
     * data : {"name":null,"id":41,"userName":"sy","token":null,"status":"0","password":"e10adc3949ba59abbe56e057f20f883e","email":null,"sex":null,"phone":"13544204367","createTime":"2016-11-10 11:27:22","idcard":"430723199011012212","queryPwd":"e10adc3949ba59abbe56e057f20f883e","flist":null,"isBind":"0","tokenStatus":"4","isFinish":"1","loginStatus":null,"lastTime":null,"mac":null}
     * status : 0
     */

    private String message;
    /**
     * name : null
     * id : 41
     * userName : sy
     * token : null
     * status : 0
     * password : e10adc3949ba59abbe56e057f20f883e
     * email : null
     * sex : null
     * phone : 13544204367
     * createTime : 2016-11-10 11:27:22
     * idcard : 430723199011012212
     * queryPwd : e10adc3949ba59abbe56e057f20f883e
     * flist : null
     * isBind : 0
     * tokenStatus : 4
     * isFinish : 1
     * loginStatus : null
     * lastTime : null
     * mac : null
     */

    private DataBean data;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        private Object name;
        private int id;
        private String userName;
        private Object token;
        private String status;
        private String password;
        private Object email;
        private Object sex;
        private String phone;
        private String createTime;
        private String idcard;
        private String queryPwd;
        private Object flist;
        private String isBind;
        private String tokenStatus;
        private String isFinish;
        private Object loginStatus;
        private Object lastTime;
        private Object mac;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
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

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
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

        public Object getFlist() {
            return flist;
        }

        public void setFlist(Object flist) {
            this.flist = flist;
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
    }
}
