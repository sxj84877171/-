package com.soarsky.installationmanual.ui.completeuserinfo;

import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.bean.VeAdBean;

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
 * 该类为 完善用户信息Present<br>
 */

public class CompleteUserInfoPresent extends BasePresenter<CompleteUserInfoModel,CompleteUserInfoView> {


    @Override
    public void onStart() {


    }
    /**
     * 完善个人资料
     * @param sex 性别
     * @param username 姓名
     * @param itemCode 地区代码
     * @param carId 车管所id
     * @param pointId 安装点id
     * @return
     */
    public void perfectUser(String sex, String username, String itemCode, String carId, String pointId){

        mView.showProgess();
        mModel.perfectUser(sex,username,itemCode,carId,pointId).subscribe(new Subscriber<ResponseDataBean<Void>>() {
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


