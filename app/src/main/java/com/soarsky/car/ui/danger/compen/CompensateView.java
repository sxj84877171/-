package com.soarsky.car.ui.danger.compen;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.CompenstateParam;
import com.soarsky.car.bean.ResponseDataBean;

import java.util.List;

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
 * 程序功能：
 * 该类为
 */


public interface CompensateView extends BaseView {

    /**
     *上传快赔信息成功
     * @param param
     */
    public void uploadFastDamagerSuccess(ResponseDataBean<CompenstateParam> param);
    /**
     *上传快赔信息失败
     */
    public void uploadFastDamagerFail();

    /**
     * 验证提交的数据是否为空
     * @return
     */



    /**
     * 保存返回的网络路径
     */
    public void  saveNetUri(int index, String url,boolean  upStatus);




    /**
     * 上传快照成功
     */
    public  void uploadFastImgSucess();

    /**
     * 上传快照失败
     */
    public  void uploadFastImgf();


    /**
     * 图片上传中
     */
    public  void upLoadImgIng();

}
