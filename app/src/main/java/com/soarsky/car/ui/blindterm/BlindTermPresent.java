package com.soarsky.car.ui.blindterm;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.BlindTermQueryPwdInfo;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.car.CarSendParam;

import rx.Subscriber;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：绑定终端接口的封装 <br>
 * 该类为 BlindTermPresent <br>
 */


public class BlindTermPresent extends BasePresenter<BlindTermModel,BlindTermView>{

    @Override
    public void onStart() {

    }


    /**
     * 绑定智能终端
     * @param carnum 车牌
     * @param username 用户名
     * @param vCode 验证码
     */
    public void bindIllegal(String carnum,String username,String vCode,String phone,String code,String ptype){
        mView.showProgess();
        mModel.bindIllegal(carnum,username,vCode,phone,code,ptype).subscribe(new Subscriber<ResponseDataBean<String>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.bindIllegalFail();
            }

            @Override
            public void onNext(ResponseDataBean<String> param) {
                mView.stopProgess();
                mView.bindIllegalSuccess(param);
            }
        });
    }

    /**
     * 获取验证码
     * @param phone 电话
     * @param ctype 类型
     */
    public void sendsms(String phone,String ctype){

        mView.showProgess();
        mModel.sendsms(phone,ctype).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.sendSmsFail();
            }

            @Override
            public void onNext(ResponseDataBean<Void> bean) {
                mView.stopProgess();
                mView.sendSmsSuccess(bean);
            }
        });

    }


    public void bindTermianl(String carnum,String phone,String vin,String vcode,String username){
        mView.showProgess();
        mModel.bindTermianl(carnum,phone,vin,vcode,username).subscribe(new Subscriber<ResponseDataBean<String>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.bindIllegalFail();
            }

            @Override
            public void onNext(ResponseDataBean<String> stringResponseDataBean) {
                mView.stopProgess();
                mView.bindIllegalSuccess(stringResponseDataBean);
            }
        });
    }

}
