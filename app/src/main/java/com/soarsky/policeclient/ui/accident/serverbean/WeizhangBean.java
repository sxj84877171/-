package com.soarsky.policeclient.ui.accident.serverbean;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 违章bean<br>
 */
public class WeizhangBean {
    /**
     * 违章列表
     */
    private List<WeizhangItem> ilist;

    public List<WeizhangItem> getIlist() {
        return ilist;
    }

    public void setIlist(List<WeizhangItem> ilist) {
        this.ilist = ilist;
    }

    /**
     * 每一个违章数据
     */
    public static class WeizhangItem{
        /**
         * 违章数据
         */
        private String inf;
        /**
         * 违章时间
         */
        private String stime;
        /**
         * 违章地址
         */
        private String address;

        public String getInf() {
            return inf;
        }

        public void setInf(String inf) {
            this.inf = inf;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
