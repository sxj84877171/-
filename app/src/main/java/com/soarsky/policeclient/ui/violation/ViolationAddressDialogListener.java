package com.soarsky.policeclient.ui.violation;


/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单地址选择对话框监听器<br>
 */

public interface ViolationAddressDialogListener {

    /**
     * 对话框点击确认按钮
     * @param str 选择的字符串
     */
    public void clickConfirm(String  str);

}
