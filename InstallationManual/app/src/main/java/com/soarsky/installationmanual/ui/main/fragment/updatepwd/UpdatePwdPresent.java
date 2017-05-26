package com.soarsky.installationmanual.ui.main.fragment.updatepwd;

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
 * 该类为 修改密码Present<br>
 */

public class UpdatePwdPresent extends BasePresenter<UpdatePwdModel,UpdatePwdView> {


    @Override
    public void onStart() {


    }


    /**
     * 修改密码
     * @param idNo 身份证号
     * @param password 密码
     * @param conPassword 确认密码
     * @return
     */
    public void modifyPwd(String idNo,  String password, String conPassword){

        mView.showProgess();
        mModel.modifyPwd(idNo,password,conPassword).subscribe(new Subscriber<ResponseDataBean<Void>>() {
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
                    mView.showSuccess();
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }
}


