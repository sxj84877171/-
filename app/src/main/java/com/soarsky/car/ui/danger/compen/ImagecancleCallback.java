package com.soarsky.car.ui.danger.compen;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能： 删除按钮点击事件监听
 * 该类为 回调接口
 */


public interface   ImagecancleCallback {

    /**
     * 删除图片
     * @param position 第几张
     * @param type 图片类型
     */
    void delete(int position,int type);

    /**
     *  添加图片
     */
    void add();
}
