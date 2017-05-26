package com.soarsky.car.ui.family.familynumconfirm;

import android.util.Log;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.FamilyNumConfirmSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.bean.FamilyNumSendParam;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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
 * 程序功能：更改亲情号present <br>
 * 该类为 FamilyNumConfirmPresent <br>
 */

public class FamilyNumConfirmPresent extends BasePresenter<FamilyNumConfirmModel,FamilyNumConfirmView>{

    @Override
    public void onStart() {

        mModel.setContext(context);
    }

    /**
     * 更改亲情号码
     * @param param 更改亲情号的参数
     */
    public void updateFirendPhone(FamilyNumConfirmSendParam param){

        mView.showProgess();
        mModel.updateFirendPhone(param).subscribe(new Subscriber<ResponseDataBean>() {
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
            public void onNext(ResponseDataBean familyNumConfirmParam) {

                mView.stopProgess();
                mView.showSuccess(familyNumConfirmParam);
            }
        });

    }

    /***
     * 设置亲情号
     * @param param 设置亲情号的参数
     */
    public void setFirendPhone(FamilyNumSendParam param){

        mView.showProgess();
        mModel.setFirendPhone(param).subscribe(new Subscriber<ResponseDataBean>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.setFamilyNumFail();
            }

            @Override
            public void onNext(ResponseDataBean familyNumParam) {
                mView.stopProgess();
                mView.setFamilyNumSuccess(familyNumParam);
            }
        });

    }

    /***
     * 修改亲情号码表
     * @param familyNum 修改亲情号的参数
     */
    public void updateFamilyNumData(FamilyNum familyNum){

        mView.showProgess();

        mRxManager.add(mModel.updateFamilyNumData(familyNum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FamilyNum>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.stopProgess();
                mView.updateFamilyNumFail();
            }

            @Override
            public void onNext(FamilyNum familyNum) {
                mView.stopProgess();
                mView.updateFamilyNumSuccess(familyNum);
                Log.d("TAA",familyNum.toJson());
            }
        }));
    }

    /**
     * 从亲情表获取亲情列表
     * @param carnum 车牌号
     * @param phone 手机号
     */
    public void getFamilyNumListByCarNumAndPhone(String carnum,String phone){

        mView.showProgess();
        mRxManager.add(mModel.getFamilyNumListByCarNumAndPhone(carnum,phone).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FamilyNum>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getFamilyNumListByCarNumAndPhoneFail();
            }

            @Override
            public void onNext(List<FamilyNum> familyNa) {
                mView.stopProgess();
                mView.getFamilyNumListByCarNumAndPhoneSuccess(familyNa);

            }
        }));
    }

    /**
     * 插入亲情号码数据
     * @param familyNum 亲情号信息参数
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

                mView.insertFamilyNumFail();
            }

            @Override
            public void onNext(FamilyNum familyNum) {

                mView.stopProgess();
                Log.d("TAG","++success"+familyNum.toJson());
                mView.insertFamilyNumSuccess();
            }
        }));

    }


}
