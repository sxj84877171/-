package com.soarsky.car.ui.danger.compen;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CompensateImage {

    /**
     * 图片路径
     */
    private String  imgUrl;

    /**
     * 是否显示删除图标 0不显示 1显示
     */
    private int showDelete;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getShowDelete() {
        return showDelete;
    }

    public void setShowDelete(int showDelete) {
        this.showDelete = showDelete;
    }
}
