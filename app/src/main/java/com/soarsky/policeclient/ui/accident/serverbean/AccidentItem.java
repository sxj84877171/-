package com.soarsky.policeclient.ui.accident.serverbean;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/24<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为根据服务器返回的id获取每一条事故数据业务类
 */

public class AccidentItem {

    /**
     * 事故分析原因
     */
    private String reason;
    /**
     * 事故分析车辆列表
     */
    private List<com.soarsky.policeclient.ui.accident.serverbean.AccidentItem.Arlist> arlist ;

    private String aaid;

    private int reasonId;
    /**
     * 事故分析图片地址
     */
    private String imageUrl;
    /**
     * 事故分析备注
     */
    private String remark;

    private String acarnum;
    /**
     * 事故分析时间
     */
    private String atime;

    private String accidentAnalysis;

    private String sourceFile;

    private String reasonid;
    /**
     * 事故分析地址
     */
    private String location;

    private int id;

    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setArlist(List<com.soarsky.policeclient.ui.accident.serverbean.AccidentItem.Arlist> arlist){
        this.arlist = arlist;
    }
    public List<com.soarsky.policeclient.ui.accident.serverbean.AccidentItem.Arlist> getArlist(){
        return this.arlist;
    }
    public void setAaid(String aaid){
        this.aaid = aaid;
    }
    public String getAaid(){
        return this.aaid;
    }
    public void setReasonId(int reasonId){
        this.reasonId = reasonId;
    }
    public int getReasonId(){
        return this.reasonId;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public String getImageUrl(){
        return this.imageUrl;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setAcarnum(String acarnum){
        this.acarnum = acarnum;
    }
    public String getAcarnum(){
        return this.acarnum;
    }
    public void setAtime(String atime){
        this.atime = atime;
    }
    public String getAtime(){
        return this.atime;
    }
    public void setAccidentAnalysis(String accidentAnalysis){
        this.accidentAnalysis = accidentAnalysis;
    }
    public String getAccidentAnalysis(){
        return this.accidentAnalysis;
    }
    public void setSourceFile(String sourceFile){
        this.sourceFile = sourceFile;
    }
    public String getSourceFile(){
        return this.sourceFile;
    }
    public void setReasonid(String reasonid){
        this.reasonid = reasonid;
    }
    public String getReasonid(){
        return this.reasonid;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    /**
     * 事故分析车辆
     */
    public static class Arlist {
        /**
         * 速度时间
         */
        private String stime1;
        /**
         * 备注
         */
        private String remark;
        /**
         * faultId
         */
        private String faultId;
        /**
         * 车牌号
         */
        private String acarnum;
        /**
         * 时间
         */
        private String atime;
        /**
         * 速度时间
         */
        private String stime2;
        /**
         * 速度时间
         */
        private String stime3;
        /**
         * 车辆类型
         */
        private String ctype;
        /**
         * 安全带
         */
        private String safeState;
        /**
         * 速度
         */
        private String s1;
        /**
         * 速度
         */
        private String s2;
        /**
         * 速度
         */
        private String s3;
        /**
         * 灯光
         */
        private String l1;
        /**
         * 灯光时间
         */
        private String ltime1;
        /**
         * 灯光
         */
        private String l2;
        /**
         * 灯光时间
         */
        private String ltime2;
        /**
         * 灯光
         */
        private String l3;
        /**
         * 灯光时间
         */
        private String ltime3;
        /**
         * 灯光
         */
        private String l4;
        /**
         * 灯光时间
         */
        private String ltime4;
        /**
         * 灯光
         */
        private String l5;
        /**
         * 灯光时间
         */
        private String ltime5;
        /**
         * 故障列表
         */
        private List<com.soarsky.policeclient.ui.accident.serverbean.AccidentItem.Flist> flist ;

        /**
         * id
         */
        private int id;

        public void setStime1(String stime1){
            this.stime1 = stime1;
        }
        public String getStime1(){
            return this.stime1;
        }
        public void setRemark(String remark){
            this.remark = remark;
        }
        public String getRemark(){
            return this.remark;
        }
        public void setFaultId(String faultId){
            this.faultId = faultId;
        }
        public String getFaultId(){
            return this.faultId;
        }
        public void setAcarnum(String acarnum){
            this.acarnum = acarnum;
        }
        public String getAcarnum(){
            return this.acarnum;
        }
        public void setAtime(String atime){
            this.atime = atime;
        }
        public String getAtime(){
            return this.atime;
        }
        public void setStime2(String stime2){
            this.stime2 = stime2;
        }
        public String getStime2(){
            return this.stime2;
        }
        public void setStime3(String stime3){
            this.stime3 = stime3;
        }
        public String getStime3(){
            return this.stime3;
        }
        public void setCtype(String ctype){
            this.ctype = ctype;
        }
        public String getCtype(){
            return this.ctype;
        }
        public void setSafeState(String safeState){
            this.safeState = safeState;
        }
        public String getSafeState(){
            return this.safeState;
        }
        public void setS1(String s1){
            this.s1 = s1;
        }
        public String getS1(){
            return this.s1;
        }
        public void setS2(String s2){
            this.s2 = s2;
        }
        public String getS2(){
            return this.s2;
        }
        public void setS3(String s3){
            this.s3 = s3;
        }
        public String getS3(){
            return this.s3;
        }
        public void setL1(String l1){
            this.l1 = l1;
        }
        public String getL1(){
            return this.l1;
        }
        public void setLtime1(String ltime1){
            this.ltime1 = ltime1;
        }
        public String getLtime1(){
            return this.ltime1;
        }
        public void setL2(String l2){
            this.l2 = l2;
        }
        public String getL2(){
            return this.l2;
        }
        public void setLtime2(String ltime2){
            this.ltime2 = ltime2;
        }
        public String getLtime2(){
            return this.ltime2;
        }
        public void setL3(String l3){
            this.l3 = l3;
        }
        public String getL3(){
            return this.l3;
        }
        public void setLtime3(String ltime3){
            this.ltime3 = ltime3;
        }
        public String getLtime3(){
            return this.ltime3;
        }
        public void setL4(String l4){
            this.l4 = l4;
        }
        public String getL4(){
            return this.l4;
        }
        public void setLtime4(String ltime4){
            this.ltime4 = ltime4;
        }
        public String getLtime4(){
            return this.ltime4;
        }
        public void setL5(String l5){
            this.l5 = l5;
        }
        public String getL5(){
            return this.l5;
        }
        public void setLtime5(String ltime5){
            this.ltime5 = ltime5;
        }
        public String getLtime5(){
            return this.ltime5;
        }
        public void setFlist(List<com.soarsky.policeclient.ui.accident.serverbean.AccidentItem.Flist> flist){
            this.flist = flist;
        }
        public List<com.soarsky.policeclient.ui.accident.serverbean.AccidentItem.Flist> getFlist(){
            return this.flist;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }

    }
    /**
     * 故障
     */
    public static class Flist {
        /**
         * 备注
         */
        private String remark;
        /**
         * 车牌号
         */
        private String acarnum;
        /**
         * 时间
         */
        private String atime;

        /**
         * id
         */
        private int id;

        public void setRemark(String remark){
            this.remark = remark;
        }
        public String getRemark(){
            return this.remark;
        }
        public void setAcarnum(String acarnum){
            this.acarnum = acarnum;
        }
        public String getAcarnum(){
            return this.acarnum;
        }
        public void setAtime(String atime){
            this.atime = atime;
        }
        public String getAtime(){
            return this.atime;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }

    }
}
