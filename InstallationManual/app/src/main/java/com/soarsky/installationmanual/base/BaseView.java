package com.soarsky.installationmanual.base;

/**
 * 安装手册<br>
 * 作者： 孙向锦<br>
 * 时间： 2017/02/09<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * View 基类，负责整个MVP架构中V逻辑<br>
 */
public interface BaseView {
    /**
     * 显示进度条
     */
    public void showProgess();

    /**
     * 关闭进度条
     */
    public void stopProgess();

    /**
     * 网络请求失败的调用方法
     */
    public void showFail();

    /**
     * 网络请求成功的调用方法
     */
    public void showSuccess();

    /**
     * 提示用户的错误信息
     * @param o
     */
    public void showFail(String o);

}
