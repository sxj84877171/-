package com.soarsky.car.ui.borrowandreturn.retur;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/7
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  我要还车的参数类
 */

public class ReturnCarParm {

    /**
     * data : {"appuser":null,"borrow":"13544174435","stime":"2016-12-07 10:00:00","etime":"2016-12-08 10:00:00","ctime":"2016-12-06 15:06:08","carnum":"湘A00002","ownerUser":null,"model":null,"atime":"2016-12-08 10:43:27","rtime":"2016-12-19 12:45:08","remark":"","owner":"13566554433","status":"2","id":11}
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
         * appuser : null
         * borrow : 13544174435
         * stime : 2016-12-07 10:00:00
         * etime : 2016-12-08 10:00:00
         * ctime : 2016-12-06 15:06:08
         * carnum : 湘A00002
         * ownerUser : null
         * model : null
         * atime : 2016-12-08 10:43:27
         * rtime : 2016-12-19 12:45:08
         * remark :
         * owner : 13566554433
         * status : 2
         * id : 11
         */

        private Object appuser;
        private String borrow;
        private String stime;
        private String etime;
        private String ctime;
        private String carnum;
        private Object ownerUser;
        private String model;
        private String atime;
        private String rtime;
        private String remark;
        private String owner;
        private String status;
        private int id;

        public Object getAppuser() {
            return appuser;
        }

        public void setAppuser(Object appuser) {
            this.appuser = appuser;
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

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
        }

        public Object getOwnerUser() {
            return ownerUser;
        }

        public void setOwnerUser(Object ownerUser) {
            this.ownerUser = ownerUser;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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
    }
}
