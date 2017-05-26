package com.soarsky.car.ui.car;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：机动车获取的bean
 * 该类为
 */

public class CarParam {


    /**
     * ilist : null
     * plateno : 湘A00001
     * vehicletype : 0
     * usercharacter : 私营
     * vin : v456423
     * registerdate : 2016-10-12
     * enginno : e4564521
     * issuedate : 2016-11-01
     * color : 白色
     * imageUrl : http://192.168.100.17:8080/20161111/3dd5804090364bda901951d03fcc9628.jpg
     * jilu : 新车
     * sourceFile : null
     * idcard : 443322556655889966
     * model : 0
     * status : 0
     * address : 麓谷大道
     * name : allen
     * id : 4
     */
    /**
     * 机动车信息的实体类
     */
    private DataBean data;
    /**
     * data : {"ilist":null,"plateno":"湘A00001","vehicletype":"0","usercharacter":"私营","vin":"v456423","enginno":"e4564521","registerdate":"2016-10-12","issuedate":"2016-11-01","color":"白色","imageUrl":"http://192.168.100.17:8080/20161111/3dd5804090364bda901951d03fcc9628.jpg","jilu":"新车","sourceFile":null,"idcard":"443322556655889966","model":"0","status":"0","address":"麓谷大道","name":"allen","id":4}
     * status : 0
     * message : 操作成功！
     */
    /**
     * 机动车的状态 “0”--成功 "1"--失败
     */
    private String status;
    /**
     * 获取的失败的详情
     */
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
        private Object ilist;
        /**
         * 车牌号
         */
        private String plateno;
        /**
         * 车辆类型
         */
        private String vehicletype;

        private String usercharacter;
        /**
         * 车辆识别代码
         */
        private String vin;
        /**
         * 发动机编号
         */
        private String enginno;
        /**
         * 注册日期
         */
        private String registerdate;
        /**
         * 发证日期
         */
        private String issuedate;
        /**
         * 车身颜色
         */
        private String color;
        /**
         * 照片地址
         */
        private String imageUrl;
        /**
         * 记录
         */
        private String jilu;

        private String clearDate;

        private Object sourceFile;
        /**
         *身份证号
         */
        private String idcard;
        /**
         * 品牌型号
         */
        private String model;
        /**
         * 状态
         */
        private String status;
        /**
         * 住址
         */
        private String address;
        /**
         * 所属人
         */
        private String name;

        private int id;

        public Object getIlist() {
            return ilist;
        }

        public void setIlist(Object ilist) {
            this.ilist = ilist;
        }

        public String getPlateno() {
            return plateno;
        }

        public void setPlateno(String plateno) {
            this.plateno = plateno;
        }

        public String getVehicletype() {
            return vehicletype;
        }

        public void setVehicletype(String vehicletype) {
            this.vehicletype = vehicletype;
        }

        public String getUsercharacter() {
            return usercharacter;
        }

        public void setUsercharacter(String usercharacter) {
            this.usercharacter = usercharacter;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getEnginno() {
            return enginno;
        }

        public void setEnginno(String enginno) {
            this.enginno = enginno;
        }

        public String getRegisterdate() {
            return registerdate;
        }

        public void setRegisterdate(String registerdate) {
            this.registerdate = registerdate;
        }

        public String getIssuedate() {
            return issuedate;
        }

        public void setIssuedate(String issuedate) {
            this.issuedate = issuedate;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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

        public String getClearDate() {
            return clearDate;
        }

        public void setClearDate(String clearDate) {
            this.clearDate = clearDate;
        }
    }
}
