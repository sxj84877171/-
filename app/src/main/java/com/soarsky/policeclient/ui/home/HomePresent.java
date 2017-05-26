package com.soarsky.policeclient.ui.home;


import android.util.Log;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.LoginDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.Note;
import com.soarsky.policeclient.ui.violation.UploadFile;
import com.soarsky.policeclient.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  首页Present <br>
 */
public class HomePresent extends BasePresenter<HomeModel, HomeView> {

    /**
     * 未使用
     */
    public void getNotes() {
        mView.showProgess();
        mRxManager.add(mModel.getNotexQuery().list().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                mView.stopProgess();
                mView.showResult(notes);
            }
        }));
    }

    /**
     * 未使用
     */
    public void addNote(Note note) {
        mView.showProgess();
        mRxManager.add(mModel.getNoteDao().insert(note).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Note>() {
            @Override
            public void call(Note note) {
                mView.stopProgess();
                mView.showSuccess();
            }
        }));
    }
    /**
     * 未使用
     */
    public void deleteNote(Note note) {
        mView.showProgess();
        mRxManager.add(mModel.getNoteDao().delete(note).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mView.showSuccess();
            }
        }));
    }


    @Override
    public void onStart() {
        mModel.setContext(context);
        mRxManager.add(mModel.getNotexQuery().list().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                mView.showResult(notes);
            }
        }));
    }
    /**
     * 未使用
     */
    public void f(){
        mRxManager.add(mModel.getFromNet("","").observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ResponseDataBean<LoginDataBean>>() {
            @Override
            public void call(ResponseDataBean<LoginDataBean> s) {
                mView.showSuccess();
            }
        }));
    }
    /**
     * 未使用
     */
    public void uploadImg(String imgPath) {
        mView.showProgess();
        mModel.upLoadImg(imgPath).subscribe(new Subscriber<UploadFile>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.uploadImgFail();
                LogUtil.i("onError" + e.getStackTrace().toString());
            }

            @Override
            public void onNext(UploadFile uploadFile) {
                mView.stopProgess();
                mView.uploadImgSuccess(uploadFile);
                LogUtil.d("TAG",uploadFile.toJson());
                LogUtil.i("onNext" + uploadFile.toJson());

            }
        });
    }

}
