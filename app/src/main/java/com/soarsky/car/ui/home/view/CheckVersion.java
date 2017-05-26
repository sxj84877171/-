package com.soarsky.car.ui.home.view;

/**
 * android_police_app
 * 作者： 王松清
 * 时间： 2016/12/13
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class CheckVersion {

    /**
     * message : 操作成功！
     * data : {"name":"车主APP（android）","id":3,"version":"gtw_00001","remark":null,"nametype":"1","fileurl":"d:/"}
     * status : 0
     */

    private String message;
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
        /**
         * name : 车主APP（android）
         * id : 3
         * version : gtw_00001
         * remark : null
         * nametype : 1
         * fileurl : d:/
         */

        private String name;
        private int id;
        private String version;
        private Object remark;
        private String nametype;
        private String fileurl;

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

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getNametype() {
            return nametype;
        }

        public void setNametype(String nametype) {
            this.nametype = nametype;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }
    }
}
