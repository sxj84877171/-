package com.soarsky.car.ui.illegal.query;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：切换车辆密码验证present<br>
 * 该类为 IllegalQueryPresent<br>
 */


public class IllegalQueryPresent extends BasePresenter<IllegalQueryModel,IllegalQueryView>{

    @Override
    public void onStart() {

    }
    /**
     * 是否可见
     */
    public void isVisiblePwd(){

        mView.isVisiblePwd();
    }

    /**
     * 验证查询密码
     * @param carnum 车牌号
     * @param qwt 密码
     */
    public void getIlleagaInfo(String carnum,String qwt){

        mView.showProgess();
        mModel.getIlleagaInfo(carnum,qwt).subscribe(new Subscriber<ResponseDataBean<Object>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getIlleagaInfoFail();
            }

            @Override
            public void onNext(ResponseDataBean<Object> illegalQueryParam) {
                mView.stopProgess();
                mView.getIlleagaInfoSuccess(illegalQueryParam);
            }
        });
    }

}
