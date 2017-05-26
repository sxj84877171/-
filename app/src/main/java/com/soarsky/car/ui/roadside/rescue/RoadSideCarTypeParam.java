package com.soarsky.car.ui.roadside.rescue;

import java.util.List;

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
 * 程序功能：
 * 该类为
 */


public class RoadSideCarTypeParam {


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
        /**
         * 时间
         */
        private String createTime;
        /**
         *
         */
        private String dcode;
        /**
         * 车名
         */
        private String dtext;
        /**
         * 级别
         */
        private String dlevel;
        private String dvalue;
        private String parent;
        private String id;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDcode() {
            return dcode;
        }

        public void setDcode(String dcode) {
            this.dcode = dcode;
        }

        public String getDtext() {
            return dtext;
        }

        public void setDtext(String dtext) {
            this.dtext = dtext;
        }

        public String getDlevel() {
            return dlevel;
        }

        public void setDlevel(String dlevel) {
            this.dlevel = dlevel;
        }

        public String getDvalue() {
            return dvalue;
        }

        public void setDvalue(String dvalue) {
            this.dvalue = dvalue;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
