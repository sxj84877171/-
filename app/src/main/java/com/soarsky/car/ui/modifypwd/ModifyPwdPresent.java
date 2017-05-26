package com.soarsky.car.ui.modifypwd;

import com.soarsky.car.base.BasePresenter;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ModifyPwdPresent  extends BasePresenter<ModifyPwdModel,ModifyPwdView>{
    @Override
    public void onStart() {

    }

    /**
     * 修改密码请求
     * @param username
     * @param currentpwd
     * @param nowpwd
     */
    public void modifyPwd(String username,String currentpwd,String nowpwd){

        mView.showProgess();
        mRxManager.add(mModel.modifyPwd(username,currentpwd,nowpwd).subscribe(new Subscriber<ModifyPwdParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showonError();
            }

            @Override
            public void onNext(ModifyPwdParam modifyPwdParam) {
                mView.stopProgess();
                mView.showData(modifyPwdParam);
            }
        }));
    }

}
