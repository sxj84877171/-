package com.soarsky.car.ui.home.fragment.personal;

import java.io.Serializable;
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

public class QueryFamilyParam implements Serializable{


    /**
     * message : 操作成功！
     * data : {"carnum":"湘A00001","ilist":[{"id":28,"status":"0","isOwner":0,"terminalId":26,"phone":"15877887788"},{"id":29,"status":"0","isOwner":0,"terminalId":26,"phone":"13888888888"},{"id":30,"status":"0","isOwner":0,"terminalId":26,"phone":"13677889966"}]}
     * status : 0
     */

    private String message;
    /**
     * carnum : 湘A00001
     * ilist : [{"id":28,"status":"0","isOwner":0,"terminalId":26,"phone":"15877887788"},{"id":29,"status":"0","isOwner":0,"terminalId":26,"phone":"13888888888"},{"id":30,"status":"0","isOwner":0,"terminalId":26,"phone":"13677889966"}]
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

    public static class DataBean implements Serializable{

        private String carnum;
        /**
         * id : 28
         * status : 0
         * isOwner : 0
         * terminalId : 26
         * phone : 15877887788
         */

        private List<IlistBean> ilist;

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
        }

        public List<IlistBean> getIlist() {
            return ilist;
        }

        public void setIlist(List<IlistBean> ilist) {
            this.ilist = ilist;
        }

        public static class IlistBean implements Serializable{
            private int id;
            private String status;
            private int isOwner;
            private int terminalId;
            private String phone;

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

            public int getIsOwner() {
                return isOwner;
            }

            public void setIsOwner(int isOwner) {
                this.isOwner = isOwner;
            }

            public int getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(int terminalId) {
                this.terminalId = terminalId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
