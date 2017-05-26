package com.soarsky.car.ui.borrowandreturn.borrow;

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
 * 该类为  校验车牌号与车主手机号业务bean类
 */

public class CheckBorrowCar {

    /**
     * data : {"time":"2016-11-17","userName":null,"carnumber":"湘A00001","isa":"1","terminalnumber":"158000000000","utime":"2016-11-24 08:53:48","istatus":"0","toname":"0","bo":"1","warrant":"","isbcontent":"0","isv":"1","ottime":"5","isacontent":"0","otime":"6","userId":"160","status":"0","id":26}
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
         * time : 2016-11-17
         * userName : null
         * carnumber : 湘A00001
         * isa : 1
         * terminalnumber : 158000000000
         * utime : 2016-11-24 08:53:48
         * istatus : 0
         * toname : 0
         * bo : 1
         * warrant :
         * isbcontent : 0
         * isv : 1
         * ottime : 5
         * isacontent : 0
         * otime : 6
         * userId : 160
         * status : 0
         * id : 26
         */

        private String time;
        private Object userName;
        private String carnumber;
        private String isa;
        private String terminalnumber;
        private String utime;
        private String istatus;
        private String toname;
        private String bo;
        private String warrant;
        private String isbcontent;
        private String isv;
        private String ottime;
        private String isacontent;
        private String otime;
        private String userId;
        private String status;
        private int id;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public String getCarnumber() {
            return carnumber;
        }

        public void setCarnumber(String carnumber) {
            this.carnumber = carnumber;
        }

        public String getIsa() {
            return isa;
        }

        public void setIsa(String isa) {
            this.isa = isa;
        }

        public String getTerminalnumber() {
            return terminalnumber;
        }

        public void setTerminalnumber(String terminalnumber) {
            this.terminalnumber = terminalnumber;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public String getIstatus() {
            return istatus;
        }

        public void setIstatus(String istatus) {
            this.istatus = istatus;
        }

        public String getToname() {
            return toname;
        }

        public void setToname(String toname) {
            this.toname = toname;
        }

        public String getBo() {
            return bo;
        }

        public void setBo(String bo) {
            this.bo = bo;
        }

        public String getWarrant() {
            return warrant;
        }

        public void setWarrant(String warrant) {
            this.warrant = warrant;
        }

        public String getIsbcontent() {
            return isbcontent;
        }

        public void setIsbcontent(String isbcontent) {
            this.isbcontent = isbcontent;
        }

        public String getIsv() {
            return isv;
        }

        public void setIsv(String isv) {
            this.isv = isv;
        }

        public String getOttime() {
            return ottime;
        }

        public void setOttime(String ottime) {
            this.ottime = ottime;
        }

        public String getIsacontent() {
            return isacontent;
        }

        public void setIsacontent(String isacontent) {
            this.isacontent = isacontent;
        }

        public String getOtime() {
            return otime;
        }

        public void setOtime(String otime) {
            this.otime = otime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

