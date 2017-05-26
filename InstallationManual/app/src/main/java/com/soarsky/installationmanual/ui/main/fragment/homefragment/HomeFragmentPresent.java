package com.soarsky.installationmanual.ui.main.fragment.homefragment;

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.BrandBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;

import java.util.List;

import rx.Subscriber;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/27<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 首页Present<br>
 */


public class HomeFragmentPresent extends BasePresenter<HomeFragmentModel,HomeFragmentView>{

    @Override
    public void onStart() {

    }
    /**
     * 获取所有车辆品牌
     */
    public void getBrands(){

        mView.showProgess();
        mModel.getBrands().subscribe(new Subscriber<ResponseDataBean<List<BrandBean>>>() {
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
            public void onNext(ResponseDataBean<List<BrandBean>> listResponseDataBean) {
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
