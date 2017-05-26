package com.soarsky.car.bean;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class SimpleImg {


    /**
     * data : [{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"2","createTime":"2016-12-22 09:11:10","url":"http://192.168.100.17:8080/20161222/6b1e648ae3894c1c99203d0b31d311f0.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":3,"handler":null},{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"0","createTime":"2016-12-22 09:11:32","url":"http://192.168.100.17:8080/20161222/4f4ab6d372d746868ffab79e005b017e.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":4,"handler":null},{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"1","createTime":"2016-12-22 11:01:44","url":"http://192.168.100.17:8080/20161222/30633dcc9006480e848de50419261a0f.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":5,"handler":null},{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"0","createTime":"2016-12-22 11:01:52","url":"http://192.168.100.17:8080/20161222/15dedacff5d9456b9efd813dba6a719f.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":6,"handler":null},{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"1","createTime":"2016-12-22 11:02:03","url":"http://192.168.100.17:8080/20161222/fcb4a2fa951840aaab2a8021739de77e.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":7,"handler":null},{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"2","createTime":"2016-12-22 11:02:13","url":"http://192.168.100.17:8080/20161222/554445c9d1c0417facf9f624637d4b22.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":8,"handler":null},{"position":null,"firstCar":null,"secondCar":null,"occurredTime":null,"finalAffirm":null,"firstLicense":null,"firstPhone":null,"accidentNumber":"4","ptype":"0","createTime":"2016-12-22 11:04:44","url":"http://192.168.100.17:8080/20161222/37f7de9b4fca424cb1ab501e3fb26f95.png","firstAffirm":null,"secondPhone":null,"secondLicense":null,"secondAffirm":null,"handleResult":null,"handleTime":null,"sourceFile":null,"fstatus":null,"sstatus":null,"firstReason":null,"secondReason":null,"status":null,"id":9,"handler":null}]
     * status : 0
     * message : 操作成功！
     */

    private String status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {



        private String url;



        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


    }
}
