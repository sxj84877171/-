package com.soarsky.car.ui.roadside.list.order;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/24
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：故障救援详细信息
 * 该类为
 */


public class RoadSideListOrderParam {


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
         * 电话
         */
        private String phone;
        private String createTime;
        /**
         * 费用
         */
        private String cost;
        /**
         * 公司
         */
        private String company;
        /**
         * 距离
         */
        private String distance;
        /**
         * 到达时间
         */
        private String reachTime;
        /**
         * 服务项目
         */
        private String serviceItems;
        /**
         * 公司电话
         */
        private String companyPhone;
        /**
         * 车牌号
         */
        private String carNumber;
        /**
         * 车辆类型
         */
        private String carType;
        /**
         * 状态
         */
        private String status;
        /**
         * 地址
         */
        private String address;
        /**
         * id
         */
        private int id;

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

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getReachTime() {
            return reachTime;
        }

        public void setReachTime(String reachTime) {
            this.reachTime = reachTime;
        }

        public String getServiceItems() {
            return serviceItems;
        }

        public void setServiceItems(String serviceItems) {
            this.serviceItems = serviceItems;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
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
