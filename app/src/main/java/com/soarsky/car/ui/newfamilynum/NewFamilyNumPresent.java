package com.soarsky.car.ui.newfamilynum;

import android.util.Log;

import com.soarsky.car.App;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class NewFamilyNumPresent extends BasePresenter<NewFamilyNumModel,NewFamilyNumView>{

    public App app;
    @Override
    public void onStart() {
        app = App.getApp();
        mModel.setContext(context);

    }

    /**
     * 根据车牌号获取列表
     * @param carnum 车牌号
     */
    public void getLocalFamilyNumList(String carnum){

        mView.showProgess();
        mRxManager.add(mModel.getLocalFamilyNumList(carnum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FamilyNum>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
            }

            @Override
            public void onNext(List<FamilyNum> familyNa) {
                mView.stopProgess();
                mView.getLocalFamilyNumData(familyNa);
            }
        }));
    }


    /***
     * 清空亲情表所有的数据
     */
    public void deleteAll(){
        mView.showProgess();

        mRxManager.add(mModel.deleteAll().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
                QueryFamilySendParam p = new QueryFamilySendParam();
                p.setPhone(app.getCommonParam().getOwerPhone());
                p.setUsername(app.getCommonParam().getUserName());
                p.setCarnum(app.getCommonParam().getCarNum());
                queryFirendPhone(p);
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();

            }

            @Override
            public void onNext(Void aVoid) {
                mView.stopProgess();
            }
        }));
    }


    /***
     * 获取亲情号码
     * @param param 入参
     */
    public void queryFirendPhone(QueryFamilySendParam param){
        mView.showProgess();
        mModel.queryFirendPhone(param).subscribe(new Subscriber<ResponseDataBean<QueryFamilyBean>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showFamilyFail();
            }

            @Override
            public void onNext(ResponseDataBean<QueryFamilyBean> param) {

                mView.stopProgess();
                mView.showFamilySuccess(param);
            }
        });
    }

    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号
     */
    public void insertFamilyNum(FamilyNum familyNum){

        mRxManager.add(mModel.insertFamilyNum(familyNum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FamilyNum>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(FamilyNum familyNum) {
                Log.d("TAA","+++success!"+familyNum.toJson());
            }
        }));

    }

}
