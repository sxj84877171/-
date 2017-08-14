package com.sxj.bank.bean;

/**
 * Created by admin on 2017/7/1.
 */

public class FuncResponseBean {

    /**
     * {"success":"YES","message":"A"}
     */

    private String success ;

    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

}
