package com.soarsky.policeclient.bean;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2017/2/24<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为车辆详情<br>
 */

public class CarDetailsDataBean {
    /**
     * 车辆详情地址
     */
    private String address;
    /**
     * 姓名
     */
    private String name;
    /**
     * 车辆详情id
     */
    private int id;
    /**
     * 车辆详情状态
     */
    private String status;
    /**
     * 记录
     */
    private String jilu;
    /**
     * 资源文件
     */
    private String sourceFile;
    /**
     * 车架号
     */
    private String vin;
    private String vehicletype;
    /**
     * 车身颜色
     */
    private String color;
    private String plateno;
    /**
     * 年检日期
     */
    private String issuedate;
    /**
     * 注册日期
     */
    private String registerdate;
    /**
     * 车辆类型
     */
    private String usercharacter;
    /**
     * 发动机编号
     */
    private String enginno;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 身份证号码
     */
    private String idcard;
    /**
     * 车辆类型
     */
    private String model;
    /**
     * address : 岳麓大道信息港
     * id : 149
     * drivers : 430624198610114416
     * status : 1
     * imageUrl : null
     * stime : null
     * monery : 100
     * cent : 0
     * ino : 1344
     * inf : 违章停车
     * grade : 1
     * carnum : 湘A00001
     * opuser : zse
     */

    private List<IlistBean> ilist;

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

    public String getJilu() {
        return jilu;
    }

    public void setJilu(String jilu) {
        this.jilu = jilu;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getUsercharacter() {
        return usercharacter;
    }

    public void setUsercharacter(String usercharacter) {
        this.usercharacter = usercharacter;
    }

    public String getEnginno() {
        return enginno;
    }

    public void setEnginno(String enginno) {
        this.enginno = enginno;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<IlistBean> getIlist() {
        return ilist;
    }

    public void setIlist(List<IlistBean> ilist) {
        this.ilist = ilist;
    }

    public static class IlistBean {
        /**
         * 车辆详情地址
         */
        private String address;
        /**
         * 车辆详情id
         */
        private int id;
        /**
         * 驾驶员
         */
        private String drivers;
        /**
         * 状态
         */
        private String status;
        /**
         * 车辆详情图片服务器地址
         */
        private String imageUrl;
        /**
         * 车辆详情时间
         */
        private String stime;
        /**
         * 车辆详情价格
         */
        private String monery;
        /**
         * 车辆详情分数
         */
        private String cent;

        private String ino;
        /**
         * 违章原因
         */
        private String inf;
        /**
         * 等级
         */
        private String grade;
        /**
         * 车牌号
         */
        private String carnum;
        private String opuser;

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

        public String getIno() {
            return ino;
        }

        public void setIno(String ino) {
            this.ino = ino;
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
    }
}
