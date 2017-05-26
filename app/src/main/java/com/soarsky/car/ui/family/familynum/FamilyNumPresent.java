package com.soarsky.car.ui.family.familynum;

import android.util.Log;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.data.local.db.bean.FamilyNum;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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

public class FamilyNumPresent extends BasePresenter<FamilyNumModel,FamilyNumView>{

    @Override
    public void onStart() {

        mModel.setContext(context);
    }

    /**
     * 亲情号码设置
     * @param param
     */
    public void setFirendPhone(FamilyNumSendParam param){

        mView.showProgess();
        mModel.setFirendPhone(param).subscribe(new Subscriber<FamilyNumParam>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showError();

            }

            @Override
            public void onNext(FamilyNumParam param) {

                mView.stopProgess();
                mView.showSuccess(param);

            }
        });

    }

    /***
     * 插入亲情号码表数据
     * @param familyNum
     */
    public void insertFamilyNum(FamilyNum familyNum){
        mView.showProgess();
        mRxManager.add(mModel.insertFamilyNum(familyNum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FamilyNum>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.insertFail();
            }

            @Override
            public void onNext(FamilyNum familyNum) {
                mView.stopProgess();
                Log.d("TAA","++success"+familyNum.toJson());
                mView.insertSuccess();

            }
        }));
    }


    public void getFamilyNumList(String carnum){

        mView.showProgess();
        mRxManager.add(mModel.getFamilyNumList(carnum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FamilyNum>>() {
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
            }
        }));
    }


}
