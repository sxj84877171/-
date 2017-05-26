package com.soarsky.policeclient.bean;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/18<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  修改密码参数类<br>
 */

public class ModifSendParm {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 旧密码
     */
    private String userPwd;
    /**
     * 新密码
     */
    private String newPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
