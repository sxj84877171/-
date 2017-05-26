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

public class CarNumParam {

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
        private boolean checked;
        private String plateno;
        private String vehicletype;
        private String usercharacter;
        private String vin;
        private String enginno;
        private String registerdate;
        private String issuedate;
        private String model;
        private Object phone;
        private String imageUrl;
        private String idcard;
        private String color;
        private String jilu;
        private Object sourceFile;
        private String seatNum;
        private Object ilist;
        private String status;
        private String address;
        private String name;
        private int id;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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

        public String getSeatNum() {
            return seatNum;
        }

        public void setSeatNum(String seatNum) {
            this.seatNum = seatNum;
        }

        public Object getIlist() {
            return ilist;
        }

        public void setIlist(Object ilist) {
            this.ilist = ilist;
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
    }
}
