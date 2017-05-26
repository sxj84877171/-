package com.soarsky.car.ui.borrowandreturn;

import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailParm;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  修改借车车辆状态的业务类
 */

public class ModifyStatusParm {

    /**
     * data : {"remark":"","model":null,"appuser":null,"borrow":"13544174435","stime":"2016-12-07 10:00:00","etime":"2016-12-08 10:00:00","rtime":"2016-12-07 17:08:38","ctime":"2016-12-06 15:06:08","atime":"2016-12-08 10:14:04","carnum":"湘A00002","owner":"13566554433","status":"1","id":11}
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
         * remark :
         * model : null
         * appuser : null
         * borrow : 13544174435
         * stime : 2016-12-07 10:00:00
         * etime : 2016-12-08 10:00:00
         * rtime : 2016-12-07 17:08:38
         * ctime : 2016-12-06 15:06:08
         * atime : 2016-12-08 10:14:04
         * carnum : 湘A00002
         * owner : 13566554433
         * status : 1
         * id : 11
         */

        private String remark;
        private String model;

        private DetailParm.DataBean.AppuserBean appuser;
        private String borrow;
        private String stime;
        private String etime;
        private String rtime;
        private String ctime;
        private String atime;
        private String carnum;
        private String owner;
        private String status;
        private int id;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public DetailParm.DataBean.AppuserBean getAppuser() {
            return appuser;
        }

        public void setAppuser(DetailParm.DataBean.AppuserBean appuser) {
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

        public String getRtime() {
            return rtime;
        }

        public void setRtime(String rtime) {
            this.rtime = rtime;
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

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
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
