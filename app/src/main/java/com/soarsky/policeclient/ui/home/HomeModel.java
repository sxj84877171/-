package com.soarsky.policeclient.ui.home;

import android.app.Activity;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.LoginDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.Note;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.data.remote.server1.ApiF;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.ui.violation.UploadFile;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

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
 * 该类为  首页Model<br>
 */
public class HomeModel implements BaseModel {
    /**
     * 太简单了不用注释
     */
    private Activity context;
    /**
     * 太简单了不用注释
     */
    private DaoSession daoSession;
    /**
     * 太简单了不用注释
     */
    private RxDao<Note, Long> noteDao;
    /**
     * 太简单了不用注释
     */
    private RxQuery<Note> notesQuery;

    public RxQuery<Note> getNotexQuery() {
        if (notesQuery == null) {
            notesQuery = daoSession.getNoteDao().queryBuilder().rx();
        }
        return notesQuery;
    }

    public RxDao<Note, Long> getNoteDao() {
        if (noteDao == null) {
            noteDao = daoSession.getNoteDao().rx();
        }
        return noteDao;
    }


    public void setContext(Activity context) {
        this.context = context;
        daoSession = ((App) context.getApplication()).getDaoSession();
    }

    /**
     * 未使用
     * @param username
     * @param password
     * @return
     */
    public Observable<ResponseDataBean<LoginDataBean>> getFromNet(String username, String password){
        return ApiM.getInstance().service.login(username,password);
    }
    /**
     * 未使用
     * @param imgPath
     * @return
     */
    public Observable<UploadFile> upLoadImg(String imgPath) {


        File file = new File(imgPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return ApiF.getInstance().service.uploadFile(body).compose(RxSchedulers.<UploadFile>io_main());

    }

}
