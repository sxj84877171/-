package com.soarsky.car.ui.borrowandreturn.borrow;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车参数类
 */

public class BorrowParm {

    /**
     * data : null
     * status : 1
     * message : 数据库暂无该车信息！
     */

    private Object data;
    private String status;
    private String message;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
}
