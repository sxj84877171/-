package com.soarsky.installationmanual.ui.brandlistnext;

import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.BrandItemNextBean;
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
 * 该类为 品牌系列下的款式Present<br>
 */

public class BrandListNextPresent extends BasePresenter<BrandListNextModel,BrandListNextView> {


    @Override
    public void onStart() {


    }
    /**
     * 获取品牌系列下的款式
     * @param brand 品牌
     * @param audi 系列
     * @return
     */
    public void getBrandListNext(final String brand,final String audi){

        mView.showProgess();
        mModel.getBrandListNext(brand,audi).subscribe(new Subscriber<ResponseDataBean<List<BrandItemNextBean>>>() {
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
            public void onNext(ResponseDataBean<List<BrandItemNextBean>> listResponseDataBean) {
                mView.stopProgess();
                if(listResponseDataBean.getStatus().equals("200")){
                    mView.showData(listResponseDataBean.getData());
                }else{
                    mView.showFail(listResponseDataBean.getMessage());
                }
            }
        });
    }


}


