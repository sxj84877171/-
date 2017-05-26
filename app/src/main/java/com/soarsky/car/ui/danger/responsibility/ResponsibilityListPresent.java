package com.soarsky.car.ui.danger.responsibility;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ResponsibilityListDataBean;
import com.soarsky.car.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class ResponsibilityListPresent extends BasePresenter<ResponsibilityListModel,ResponsibilityListView>{
    @Override
    public void onStart() {

    }

    /**
     * 获取快赔列表
     * @param carnum 车牌号
     */

    public void getFastList(String carnum){
        mView.showProgess();
        mModel.getFastList(carnum).subscribe(new Subscriber<ResponseDataBean<List<ResponsibilityListDataBean>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getFastListFail();
            }

            @Override
            public void onNext(ResponseDataBean<List<ResponsibilityListDataBean>> responsibilityListParam) {
                mView.stopProgess();
                mView.getFastListSuccess(responsibilityListParam);
                LogUtil.i(responsibilityListParam.toJson());
            }
        });
    }


    /**
     * 修改未读状态
     * @param carnum 车牌号
     * @param id   数据id
     */
    public void  modifyUnreadFast(String carnum, int id){
      mRxManager.add(mModel.modifyUnreadFast(carnum,id).subscribe(new Subscriber<ResponseDataBean<String>>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(ResponseDataBean<String> baseEntity) {

          }
      }));
    }


}
