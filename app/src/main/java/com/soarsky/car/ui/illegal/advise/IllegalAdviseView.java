package com.soarsky.car.ui.illegal.advise;

import com.soarsky.car.base.BaseView;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章劝离回调接口<br>
 * 该类为 IllegalAdviseView<br>
 */


public interface IllegalAdviseView extends BaseView{

    /**
     * 保存返回的网络路径
     */
    public void  saveNetUri(String remoteUrl);

    /**
     * 上传成功
     */
    public  void uploadImgSucess();

    /**
     * 上传失败
     */
    public  void uploadImgf(String message);

}
