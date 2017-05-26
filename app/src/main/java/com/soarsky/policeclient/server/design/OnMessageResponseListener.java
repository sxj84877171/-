package com.soarsky.policeclient.server.design;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 消息回调接口<br>
 */
public interface OnMessageResponseListener {
    /**
     * 接收到字符串消息
     * @param s 驾驶证号也就是身份证号
     */
    void onSuccess(String s);

    /**
     * 获取数据失败
     * @param s 获取数据失败的消息
     */
    void onFail(String s);

}
