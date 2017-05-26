package com.soarsky.installationmanual.ui.completeuserinfo.location;

import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;

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
 * 该类为 选择地区Present<br>
 */

public class AreaPresent extends BasePresenter<AreaModel,AreaView> {


    @Override
    public void onStart() {


    }

    /**
     * 获取所有省份信息
     * @param code 发送给后台的标识码
     * @param flag 发送给后台的标识码
     */
    public void getAera(final String code,final String flag){

        mView.showProgess();
        mModel.getAera(code,flag).subscribe(new Subscriber<ResponseDataBean<List<AreaItemBean>>>() {
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
            public void onNext(ResponseDataBean<List<AreaItemBean>> loginParam) {
                mView.stopProgess();
                System.out.println("onNext:=============" + loginParam.toJson());
                if(loginParam.getStatus().equals("200")){
                    mView.showSuccess(loginParam.getData());
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }


}


