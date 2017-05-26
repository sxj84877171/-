package com.soarsky.car.ui.borrowandreturn.recorddetails;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 记录详情业务类
 */

public class DetailsParm {

    /**
     * data : {"remark":null,"ownerUser":{"token":null,"userName":"zhoujun","email":null,"sex":null,"queryPwd":"","flist":null,"phone":"18684867887","createTime":"2016-12-07 15:16:53","idcard":"431002199209165039","isBind":"0","tokenStatus":"4","isFinish":"1","loginStatus":null,"lastTime":null,"mac":null,"status":"0","password":"4297f44b13955235245b2497399d7a93","name":null,"id":222},"appuser":{"token":null,"userName":"suyun","email":"","sex":null,"queryPwd":"e10adc3949ba59abbe56e057f20f883e","flist":null,"phone":"13444444444","createTime":"2016-12-07 15:26:27","idcard":"7788778877887788","isBind":"0","tokenStatus":"4","isFinish":"1","loginStatus":null,"lastTime":null,"mac":null,"status":"0","password":"e10adc3949ba59abbe56e057f20f883e","name":null,"id":218},"etime":"2016-12-08 10:00:00","ctime":"2016-12-08 18:01:50","atime":"2016-12-09 08:03:42","rtime":"","carnum":"湘A10010","borrow":"13444444444","stime":"2016-12-07 10:00:00","model":null,"owner":"18684867887","status":"1","id":63}
     * status : 0
     * message : 操作成功！
     */

    private DataBean data;
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
         * remark : null
         * ownerUser : {"token":null,"userName":"zhoujun","email":null,"sex":null,"queryPwd":"","flist":null,"phone":"18684867887","createTime":"2016-12-07 15:16:53","idcard":"431002199209165039","isBind":"0","tokenStatus":"4","isFinish":"1","loginStatus":null,"lastTime":null,"mac":null,"status":"0","password":"4297f44b13955235245b2497399d7a93","name":null,"id":222}
         * appuser : {"token":null,"userName":"suyun","email":"","sex":null,"queryPwd":"e10adc3949ba59abbe56e057f20f883e","flist":null,"phone":"13444444444","createTime":"2016-12-07 15:26:27","idcard":"7788778877887788","isBind":"0","tokenStatus":"4","isFinish":"1","loginStatus":null,"lastTime":null,"mac":null,"status":"0","password":"e10adc3949ba59abbe56e057f20f883e","name":null,"id":218}
         * etime : 2016-12-08 10:00:00
         * ctime : 2016-12-08 18:01:50
         * atime : 2016-12-09 08:03:42
         * rtime :
         * carnum : 湘A10010
         * borrow : 13444444444
         * stime : 2016-12-07 10:00:00
         * model : null
         * owner : 18684867887
         * status : 1
         * id : 63
         */

        /**
         * 拒绝原因
         */
        private Object remark;
        /**
         * 车主信息
         */
        private OwnerUserBean ownerUser;
        /**
         * 借车人信息
         */
        private AppuserBean appuser;
        /**
         * 结束时间
         */
        private String etime;
        /**
         * 创建时间
         */
        private String ctime;
        /**
         * 审批时间
         */
        private String atime;
        /**
         * 归还时间
         */
        private String rtime;
        /**
         * 车牌号
         */
        private String carnum;
        /**
         * 借车人电话
         */
        private String borrow;
        /**
         * 开始时间
         */
        private String stime;
        /**
         * 车辆类型
         */
        private String model;

        private String owner;

        private String status;
        private int id;

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public OwnerUserBean getOwnerUser() {
            return ownerUser;
        }

        public void setOwnerUser(OwnerUserBean ownerUser) {
            this.ownerUser = ownerUser;
        }

        public AppuserBean getAppuser() {
            return appuser;
        }

        public void setAppuser(AppuserBean appuser) {
            this.appuser = appuser;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getAtime() {
            return atime;
        }

        public void setAtime(String atime) {
            this.atime = atime;
        }

        public String getRtime() {
            return rtime;
        }

        public void setRtime(String rtime) {
            this.rtime = rtime;
        }

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
        }

        public String getBorrow() {
            return borrow;
        }

        public void setBorrow(String borrow) {
            this.borrow = borrow;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class OwnerUserBean {
            /**
             * token : null
             * userName : zhoujun
             * email : null
             * sex : null
             * queryPwd :
             * flist : null
             * phone : 18684867887
             * createTime : 2016-12-07 15:16:53
             * idcard : 431002199209165039
             * isBind : 0
             * tokenStatus : 4
             * isFinish : 1
             * loginStatus : null
             * lastTime : null
             * mac : null
             * status : 0
             * password : 4297f44b13955235245b2497399d7a93
             * name : null
             * id : 222
             */

            private Object token;
            private String userName;
            private Object email;
            private Object sex;
            private String queryPwd;
            private Object flist;
            private String phone;
            private String createTime;
            private String idcard;
            private String isBind;
            private String tokenStatus;
            private String isFinish;
            private Object loginStatus;
            private Object lastTime;
            private Object mac;
            private String status;
            private String password;
            private Object name;
            private int id;

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
        }

        public static class AppuserBean {
            /**
             * token : null
             * userName : suyun
             * email :
             * sex : null
             * queryPwd : e10adc3949ba59abbe56e057f20f883e
             * flist : null
             * phone : 13444444444
             * createTime : 2016-12-07 15:26:27
             * idcard : 7788778877887788
             * isBind : 0
             * tokenStatus : 4
             * isFinish : 1
             * loginStatus : null
             * lastTime : null
             * mac : null
             * status : 0
             * password : e10adc3949ba59abbe56e057f20f883e
             * name : null
             * id : 218
             */

            private Object token;
            private String userName;
            private String email;
            private Object sex;
            private String queryPwd;
            private Object flist;
            private String phone;
            private String createTime;
            private String idcard;
            private String isBind;
            private String tokenStatus;
            private String isFinish;
            private Object loginStatus;
            private Object lastTime;
            private Object mac;
            private String status;
            private String password;
            private Object name;
            private int id;

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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
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
        }
    }
}
