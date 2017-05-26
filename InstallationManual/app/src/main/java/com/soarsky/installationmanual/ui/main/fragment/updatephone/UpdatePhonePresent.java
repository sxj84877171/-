package com.soarsky.installationmanual.ui.main.fragment.updatephone;

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.util.SpUtil;

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
 * 该类为 修改手机号Present<br>
 */

public class UpdatePhonePresent extends BasePresenter<UpdatePhoneModel,UpdatePhoneView> {


    @Override
    public void onStart() {


    }

    /**
     * 修改手机号
     * @param idNo 身份证号
     * @param phone 电话号码
     * @param newPhone 新电话号码
     * @return
     */
    public void modifyPhone(String idNo, final String phone, String newPhone){

        mView.showProgess();
        mModel.modifyPhone(idNo,phone,newPhone).subscribe(new Subscriber<ResponseDataBean<Void>>() {
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
            public void onNext(ResponseDataBean<Void> loginParam) {
                mView.stopProgess();
                System.out.println("onNext:=============" + loginParam.toJson());
                if(loginParam.getStatus().equals("200")){
                    SpUtil.save(Constants.PHONE,phone);
                    mView.showSuccess();
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }
}


