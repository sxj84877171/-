package com.soarsky.car.ui.home;


import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.ResponseDataBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  检测版本更新的P层<br>
 */
public class HomePresent extends BasePresenter<HomeModel, HomeView> {


    public void backUserName(String userName){

    }

    public void isDriver(){

    }

    /**
     * 检测版本号
     * @param type 类型0，车主，1警务通
     */
    public void checkVersion(String type){
        mView.showProgess();
        mModel.checkVersion(type).subscribe(new Subscriber<ResponseDataBean<CheckVersionBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<CheckVersionBean> checkVersion) {
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
     * @param url   资源文件
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
