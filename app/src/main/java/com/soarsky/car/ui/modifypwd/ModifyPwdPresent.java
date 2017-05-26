package com.soarsky.car.ui.modifypwd;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ModifyPwdInfo;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：修改密码present<br>
 * 该类为 ModifyPwdPresent<br>
 */

public class ModifyPwdPresent  extends BasePresenter<ModifyPwdModel,ModifyPwdView>{
    @Override
    public void onStart() {

    }

    /**
     * 修改密码请求
     * @param username 用户
     * @param currentpwd 以前密码
     * @param nowpwd 新密码
     */
    public void modifyPwd(String username,String currentpwd,String nowpwd){

        mView.showProgess();
        mRxManager.add(mModel.modifyPwd(username,currentpwd,nowpwd).subscribe(new Subscriber<ResponseDataBean<ModifyPwdInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showonError();
            }

            @Override
            public void onNext(ResponseDataBean<ModifyPwdInfo> modifyPwdParam) {
                mView.stopProgess();
                mView.showData(modifyPwdParam);
            }
        }));
    }

}
