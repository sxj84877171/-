package com.soarsky.car.ui.illegal.advise;

import android.util.Log;

import com.soarsky.car.Constants;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.uitl.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章劝离Present<br>
 * 该类为 IllegalAdvisePresent<br>
 */


public class IllegalAdvisePresent extends BasePresenter<IllegalAdviseModel,IllegalAdviseView> {

    @Override
    public void onStart() {

    }




    /**
     * 照片上传
     * @param imgPath 路径
     */
    public void uploadImg(String imgPath) {

        mModel.upLoadImg(imgPath).subscribe(new Subscriber<UploadFile>() {
            private List<String> urlList = new ArrayList<String>();
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("onError" + e.getStackTrace().toString());
            }

            @Override
            public void onNext(UploadFile uploadFile) {
                if (uploadFile.getCode().equals("0")) {

//                    mView.saveNetUri();
                }

                Log.d("TAG","upload=="+uploadFile.toJson());
                LogUtil.i("onNext" + uploadFile.toJson());

            }
        });


    }

    /**
     * 上传图片集合
     * @param paths 路径
     */
    public void uploadImg(final List<String> paths){

        mView.showProgess();

        String[] imageUrl = new String[paths.size()];
        for(int i=0,j=paths.size();i<j;i++){
            imageUrl[i]=paths.get(i);
        }
        final List<String> imageList = new ArrayList<>();
        Observable.from(imageUrl).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mModel.upLoadImg(s).subscribe(new Subscriber<UploadFile>() {
                    @Override
                    public void onCompleted() {
                        mView.stopProgess();
                        Log.d("TAG","imageList.size=="+imageList.size());
                        StringBuilder remoteUrl = new StringBuilder();
                        if(imageList.size() == paths.size()){

                            for (int i=0;i<imageList.size();i++){

                                remoteUrl.append(imageList.get(i));
                                remoteUrl.append(",");

                            }

                            mView.saveNetUri(remoteUrl.toString().substring(0,remoteUrl.toString().length()-1));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UploadFile uploadFile) {
                        mView.stopProgess();
                        imageList.add(uploadFile.getData().get(0).getFileUrl());
                        Log.d("TAG","upload=="+uploadFile.toJson());
                    }
                });
            }
        });

    }



    /**
     * 上传图片信息
     * @param carnum 车牌
     * @param ptype 类别
     * @param rtime 申请时间
     * @param stime 开始时间
     * @param imageurl 路径
     */
    public void uploadViolationAdvice(String carnum,String ptype,String rtime,String stime,String imageurl){


        mModel.uploadViolationAdvice(carnum,ptype,rtime,stime,imageurl).subscribe(new Subscriber<ResponseDataBean<String>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.uploadImgf(e.getMessage());
            }

            @Override
            public void onNext(ResponseDataBean<String> responseDataBean) {
                mView.stopProgess();

                if(Constants.REQUEST_SUCCESS.equals(responseDataBean.getStatus())){
                    mView.uploadImgSucess();
                }else {
                    mView.uploadImgf(responseDataBean.getMessage());
                }

            }
        });

    }

    /**
     * 其另种方法
     * @param paths 路径
     */
    public void uploadImg1(final List<String> paths){

        String[] imageUrl = new String[paths.size()];
        for(int i=0,j=paths.size();i<j;i++){
            imageUrl[i]=paths.get(i);
        }

        final StringBuilder stringBuilder = new StringBuilder();
        Observable.from(imageUrl)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG",stringBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG",s);
                        stringBuilder.append(s);
                    }
                });


    }



}
