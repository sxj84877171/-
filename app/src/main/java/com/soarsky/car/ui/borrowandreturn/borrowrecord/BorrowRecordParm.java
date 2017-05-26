package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/7
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车记录参数类
 */

public class BorrowRecordParm {


    /**
     * message : 操作成功！
     * data : [{"id":16,"owner":"13544204366","status":"1","remark":null,"model":null,"carnum":"湘A00001","ownerUser":null,"appuser":null,"etime":"2016-12-7 10:52:25","borrow":"18677887788","ctime":"2016-12-07 09:58:50","stime":"2016-12-7 10:49:22","atime":"2016-12-07 16:45:40","rtime":""},{"id":15,"owner":"13544204366","status":"1","remark":null,"model":null,"carnum":"湘A00001","ownerUser":null,"appuser":null,"etime":"2016-12-07 10:08:27","borrow":"18677887788","ctime":"2016-12-07 09:17:46","stime":"2016-12-07 10:08:25","atime":"","rtime":"2016-12-08 15:51:16"},{"id":6,"owner":"18677887788","status":"2","remark":null,"model":null,"carnum":"湘A00002","ownerUser":null,"appuser":null,"etime":"2016-12-05 17:38:03","borrow":"18674393730","ctime":null,"stime":"2016-12-05 17:38:01","atime":"2016-12-08 15:53:14","rtime":"2016-12-09 10:40:21"},{"id":4,"owner":"13544204366","status":"4","remark":null,"model":null,"carnum":"湘A00001","ownerUser":null,"appuser":null,"etime":"2016-12-08 10:00:00","borrow":"18677887788","ctime":"2016-12-05 14:44:41","stime":"2016-12-07 19:00:00","atime":"","rtime":"2016-12-07 16:41:26"}]
     * status : 0
     */

    private String message;
    private String status;
    private List<BorrowRecords> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BorrowRecords> getData() {
        return data;
    }

    public void setData(List<BorrowRecords> data) {
        this.data = data;
    }


}
