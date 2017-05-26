package com.soarsky.car.ui.login;

import java.util.List;

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

public class LoginParam {


    private DataBean data;
    /**
     * 状态
     */
    private String status;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * 用户名
         */
        private String userName;
        /**
         * 验证码
         */
        private String token;
        /**
         * 创建时间
         */
        private String createTime;
        /**
         * 是否绑定智能终端
         */
        private String isBind;
        /**
         * 令牌状态
         */
        private String tokenStatus;
        private String isFinish;
        /**
         * 登录状态
         */
        private String loginStatus;
        private Object lastTime;
        private Object mac;
        /**
         * 查询密码
         */
        private String queryPwd;
        /**
         * ID身份证
         */
        private String idcard;
        private Object email;
        /**
         * 手机
         */
        private String phone;
        /**
         * 性别
         */
        private Object sex;
        private String status;
        /**
         * 密码
         */
        private String password;
        private Object name;
        private int id;

        public String getQueryPwd() {
            return queryPwd;
        }

        public void setQueryPwd(String queryPwd) {
            this.queryPwd = queryPwd;
        }

        /**
         * terminalId : 26
         * phone : 13955667788
         * id : 36
         */


        private List<FlistBean> flist;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
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

        public List<FlistBean> getFlist() {
            return flist;
        }

        public void setFlist(List<FlistBean> flist) {
            this.flist = flist;
        }

        public static class FlistBean {
            private int terminalId;
            private String phone;
            private int id;

            public int getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(int terminalId) {
                this.terminalId = terminalId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
