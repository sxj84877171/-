package com.soarsky.car.ui.home;


import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.home.view.CheckVersion;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  检测版本更新的P层
 */
public class HomePresent extends BasePresenter<HomeModel, HomeView> {


    public void backUserName(String userName){

    }

    public void isDriver(){

    }
    public void checkVersion(String type){
        mView.showProgess();
        mModel.checkVersion(type).subscribe(new Subscriber<CheckVersion>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(CheckVersion checkVersion) {
                mView.stopProgess();
                if (checkVersion.getStatus().equals(SUCCESS_FLAG)){
                    mView.checkSuccess(checkVersion);
                }else {
                    mView.checkFail(checkVersion);
                }
            }
        });
    }

    /**
     * 下载文件
     * @param url
     */
    public  void  loadFile(String url){


        mView.showProgess();
        mModel.loadFile(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mView.stopProgess();
                mView.loadSuccess(call,response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.stopProgess();
                mView.loadFail(call,t);
            }
        });
    }

    @Override
    public void onStart() {

    }




    public  void   photo(){

    }





}
