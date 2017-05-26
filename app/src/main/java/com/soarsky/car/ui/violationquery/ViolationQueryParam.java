package com.soarsky.car.ui.violationquery;

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

public class ViolationQueryParam {


    /**
     * dealCount : 6
     * countMoney : 600
     * countCent : 0
     * ilist : [{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":149},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":150},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-11 12:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":151},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-11 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":152},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":153},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 11:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":154}]
     * lastTime : 2016-11-11 12:00:00
     */

    private DataBean data;
    /**
     * data : {"dealCount":6,"countMoney":600,"countCent":0,"ilist":[{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":149},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":150},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-11 12:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":151},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-11 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":152},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 10:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":153},{"ino":"1344","carnum":"湘A00001","opuser":"zse","imageUrl":"http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg","stime":"2016-11-10 11:00:00","inf":"违章停车","grade":"1","monery":"100","cent":"0","drivers":"430624198610114416","status":"1","address":"岳麓大道信息港","id":154}],"lastTime":"2016-11-11 12:00:00"}
     * status : 0
     * message : 操作成功！
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
        private int dealCount;
        private int countMoney;
        private int countCent;
        private String lastTime;
        /**
         * ino : 1344
         * carnum : 湘A00001
         * opuser : zse
         * imageUrl : http://192.168.100.17:8080/20161112/ae246cf38c014328a13bdb356d40c338.jpg
         * stime : 2016-11-10 10:00:00
         * inf : 违章停车
         * grade : 1
         * monery : 100
         * cent : 0
         * drivers : 430624198610114416
         * status : 1
         * address : 岳麓大道信息港
         * id : 149
         */

        private List<IlistBean> ilist;

        public int getDealCount() {
            return dealCount;
        }

        public void setDealCount(int dealCount) {
            this.dealCount = dealCount;
        }

        public int getCountMoney() {
            return countMoney;
        }

        public void setCountMoney(int countMoney) {
            this.countMoney = countMoney;
        }

        public int getCountCent() {
            return countCent;
        }

        public void setCountCent(int countCent) {
            this.countCent = countCent;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public List<IlistBean> getIlist() {
            return ilist;
        }

        public void setIlist(List<IlistBean> ilist) {
            this.ilist = ilist;
        }

        public static class IlistBean {
            private String ino;
            private String carnum;
            private String opuser;
            private String imageUrl;
            private String stime;
            private String inf;
            private String grade;
            private String monery;
            private String cent;
            private String drivers;
            private String status;
            private String address;
            private int id;

            public String getIno() {
                return ino;
            }

            public void setIno(String ino) {
                this.ino = ino;
            }

            public String getCarnum() {
                return carnum;
            }

            public void setCarnum(String carnum) {
                this.carnum = carnum;
            }

            public String getOpuser() {
                return opuser;
            }

            public void setOpuser(String opuser) {
                this.opuser = opuser;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getStime() {
                return stime;
            }

            public void setStime(String stime) {
                this.stime = stime;
            }

            public String getInf() {
                return inf;
            }

            public void setInf(String inf) {
                this.inf = inf;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getMonery() {
                return monery;
            }

            public void setMonery(String monery) {
                this.monery = monery;
            }

            public String getCent() {
                return cent;
            }

            public void setCent(String cent) {
                this.cent = cent;
            }

            public String getDrivers() {
                return drivers;
            }

            public void setDrivers(String drivers) {
                this.drivers = drivers;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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
