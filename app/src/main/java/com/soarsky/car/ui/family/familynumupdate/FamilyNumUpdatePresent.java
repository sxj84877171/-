package com.soarsky.car.ui.family.familynumupdate;

import android.util.Log;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.home.fragment.personal.QueryFamilyParam;
import com.soarsky.car.ui.home.fragment.personal.QueryFamilySendParam;

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

public class FamilyNumUpdatePresent extends BasePresenter<FamilyNumUpdateModel,FamilyNumUpdateView>{

    @Override
    public void onStart() {

        mModel.setContext(context);
    }



    /***
     * 插入亲情号码数据
     * @param familyNum
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

    /***
     * 清空亲情表所有的数据
     */
    public void deleteAll(){
        mView.showProgess();

        mRxManager.add(mModel.deleteAll().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
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

    /**
     * 查询所有的亲情号码
     */
    public void getAllFamilyNumList(){
        mView.showProgess();

        mRxManager.add(mModel.getAllFamilyNumList().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FamilyNum>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showFails();
            }

            @Override
            public void onNext(List<FamilyNum> familyNa) {
                mView.stopProgess();
                mView.showSuccess(familyNa);

            }
        }));
    }

}
