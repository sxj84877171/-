package com.soarsky.car.bean;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/13
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：终端指令升级
 * 该类为 TermDataBean
 */


public class TermDataBean {

    /**
     * 指令类型
     */
    private String cmdType;
    /**
     * 具体事例
     */
    private ParamsBean params;

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * 任务id
         */
        private int taskid;
        /**
         * 文件名称
         */
        private String filename;
        /**
         * 文件大小
         */
        private int filesize;
        /**
         * 版本
         */
        private String ver;
        /**
         * 类型
         */
        private String typeAndMode;
        /**
         * crc检验值
         */
        private String crc;
        /**
         * 路径
         */
        private String path;

        public int getTaskid() {
            return taskid;
        }

        public void setTaskid(int taskid) {
            this.taskid = taskid;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getTypeAndMode() {
            return typeAndMode;
        }

        public void setTypeAndMode(String typeAndMode) {
            this.typeAndMode = typeAndMode;
        }

        public String getCrc() {
            return crc;
        }

        public void setCrc(String crc) {
            this.crc = crc;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
