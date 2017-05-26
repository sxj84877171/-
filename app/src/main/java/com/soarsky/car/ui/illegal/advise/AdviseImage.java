package com.soarsky.car.ui.illegal.advise;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/3/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：其实体<br>
 * 该类为 AdviseImage<br>
 */


public class AdviseImage {

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
