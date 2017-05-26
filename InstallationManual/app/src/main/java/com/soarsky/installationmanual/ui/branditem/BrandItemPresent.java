package com.soarsky.installationmanual.ui.branditem;

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.BrandBean;
import com.soarsky.installationmanual.bean.BrandItemBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.ui.completeuserinfo.location.AreaView;

import java.util.List;

import rx.Subscriber;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 品牌下的子系列Present<br>
 */

public class BrandItemPresent extends BasePresenter<BrandItemModel,BrandItemView> {


    @Override
    public void onStart() {


    }
    /**
     * 根据品牌获取对应的子系列列表
     * @param brand 品牌
     */
    public void getBrandItem(String brand){

        mView.showProgess();
        mModel.getBrandItem(brand).subscribe(new Subscriber<ResponseDataBean<List<BrandItemBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                System.out.println("onError:" + Thread.currentThread().getId());
                mView.showFail();
            }

            @Override
            public void onNext(ResponseDataBean<List<BrandItemBean>> listResponseDataBean) {
                mView.stopProgess();
                if(listResponseDataBean.getStatus().equals(Constants.STATUS)){

                    mView.showData(listResponseDataBean.getData());
                }else{
                    mView.showFail(listResponseDataBean.getMessage());
                }
            }
        });
    }
}


