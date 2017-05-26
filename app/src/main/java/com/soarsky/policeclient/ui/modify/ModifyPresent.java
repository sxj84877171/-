package com.soarsky.policeclient.ui.modify;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  修改密码Present<br>
 */

public class ModifyPresent extends BasePresenter<ModifyMode,ModifyView>{
    private ModifyMode model;

    @Override
    public void onStart() {

    }

    public void setModel(ModifyMode model) {
        this.model = model;
    }

    /**
     * 修改密码
     * @param username 用户名
     * @param currentpwd 旧密码
     * @param nowpwd 新密码
     * @return
     */
    public void reset(String username,String currentpwd,String nowpwd){
        mModel.modifyPwd(username,currentpwd,nowpwd).subscribe(new Subscriber<ResponseDataBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
            }

            @Override
            public void onNext(ResponseDataBean modifyParam) {
                if (Constants.STATUS.equals(modifyParam.getStatus()) ){
                    mView.showSuccess(modifyParam);
                }else {
                    mView.showFail(modifyParam.getMessage());
                }

            }
        });
    }

}
