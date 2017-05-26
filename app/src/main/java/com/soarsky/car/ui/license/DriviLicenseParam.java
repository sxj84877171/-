package com.soarsky.car.ui.license;

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

public class DriviLicenseParam {


    /**
     * message : 操作成功！
     * data : {"address":"湖南长沙","name":"allen","id":8,"status":"0","validStartDate":"2016-07-14","validityPeriod":"3","sex":"M","phoneId":"4","idcard":"430624198802101142","jilu":"","sourceFile":null,"num":"430624198802101142","guoji":"中国","fileNum":"hn11024","birthday":"1988-02-10","initData":"2016-07-13","drivingType":"C1","imgurl":"http://192.168.100.17:8080/20161117/3bedab8d320145df9f31db913e18eca9.jpg"}
     * status : 0
     */

    private String message;
    /**
     * address : 湖南长沙
     * name : allen
     * id : 8
     * status : 0
     * validStartDate : 2016-07-14
     * validityPeriod : 3
     * sex : M
     * phoneId : 4
     * idcard : 430624198802101142
     * jilu :
     * sourceFile : null
     * num : 430624198802101142
     * guoji : 中国
     * fileNum : hn11024
     * birthday : 1988-02-10
     * initData : 2016-07-13
     * drivingType : C1
     * imgurl : http://192.168.100.17:8080/20161117/3bedab8d320145df9f31db913e18eca9.jpg
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
        private String address;
        private String name;
        private int id;
        private String status;
        private String validStartDate;
        private String validityPeriod;
        private String sex;
        private String phoneId;
        private String idcard;
        private String jilu;
        private Object sourceFile;
        private String num;
        private String guoji;
        private String fileNum;
        private String birthday;
        private String initData;
        private String drivingType;
        private String imgurl;

        /**
         * 驾驶证扣分
         */
        private String score  = "0";

        public void setScore(String score) {
            this.score = score;
        }

        public String getScore() {
            return score;
        }

        /**
         * 驾驶证行驶的距离
         */
        private String distance = "0KM";

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getDistance() {
            return distance;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
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

        public String getValidStartDate() {
            return validStartDate;
        }

        public void setValidStartDate(String validStartDate) {
            this.validStartDate = validStartDate;
        }

        public String getValidityPeriod() {
            return validityPeriod;
        }

        public void setValidityPeriod(String validityPeriod) {
            this.validityPeriod = validityPeriod;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhoneId() {
            return phoneId;
        }

        public void setPhoneId(String phoneId) {
            this.phoneId = phoneId;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getJilu() {
            return jilu;
        }

        public void setJilu(String jilu) {
            this.jilu = jilu;
        }

        public Object getSourceFile() {
            return sourceFile;
        }

        public void setSourceFile(Object sourceFile) {
            this.sourceFile = sourceFile;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getGuoji() {
            return guoji;
        }

        public void setGuoji(String guoji) {
            this.guoji = guoji;
        }

        public String getFileNum() {
            return fileNum;
        }

        public void setFileNum(String fileNum) {
            this.fileNum = fileNum;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getInitData() {
            return initData;
        }

        public void setInitData(String initData) {
            this.initData = initData;
        }

        public String getDrivingType() {
            return drivingType;
        }

        public void setDrivingType(String drivingType) {
            this.drivingType = drivingType;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
