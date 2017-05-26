package com.soarsky.policeclient.ui.violation;

import android.app.Activity;
import android.content.Context;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.bean.ViolationDataBean;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.data.local.db.dao.ViolationDao;
import com.soarsky.policeclient.data.map.baidu.BaiduPositioning;
import com.soarsky.policeclient.data.map.design.OnPositioningResponse;
import com.soarsky.policeclient.data.map.design.Positioning;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.server.design.IScan;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单的Model<br>
 */

public class ViolationMode implements BaseModel {

    /**
     * 参见其他的model
     */
    private Context context;
    /**
     * 参见其他的model
     */
    private DaoSession daoSession;
    /**
     * 参见其他的model
     */
    private RxDao<Violation, Long> violationDao;
    /**
     * 参见其他的model
     */
    private RxQuery<Violation> violationQuery;



    public void setContext(Activity context) {
        this.context = context;
        daoSession = ((App) context.getApplication()).getDaoSession();
        getViolationDao();
    }

    /**
     * 开启定位
     * @param onPositioningResponse 定位到的监听回调
     */
    public void startPositioning(OnPositioningResponse onPositioningResponse) {
        Positioning positioning = new BaiduPositioning();
        positioning.startPositioning(onPositioningResponse);
    }


    /**
     * 获取未上传的电子罚单
     * @return
     */
    public RxQuery<Violation> getViolationQuery() {
        if (violationQuery == null) {
          violationQuery = daoSession.getViolationDao().queryBuilder().where(ViolationDao.Properties.Status.eq(0)).distinct().rx();
        }

        return violationQuery;
    }


    /**
     * 获取未上传的电子罚单
     * @return
     */
    public  Observable<List<Violation>> getListViolation(){

        return getViolationQuery().list();

    }

    /**
     * 参见其他的model
     */
    public RxDao<Violation, Long> getViolationDao() {
        if (violationDao == null) {
            violationDao = daoSession.getViolationDao().rx();
        }
        return violationDao;
    }


    /**
     * 插入数据
     * @param violation
     */
    public Observable<Violation>  insertViolation(Violation violation){
        return  violationDao.insert(violation);
    }


    /**
     * 更新数据
     * @param violation
     */
    public void UpdateViolation(Violation violation){
        violationDao.update(violation).subscribe(new Subscriber<Violation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Violation violation) {

            }
        });
    }




    // 违章上传

    /**
     * 违章上传
     * @param map
     * @return
     */
    public Observable<ResponseDataBean<ViolationDataBean>> updateViolationMessage(Map<String,String> map){

        return  ApiM.getInstance().service.sendViolation(map).compose(RxSchedulers.<ResponseDataBean<ViolationDataBean>>io_main());
    }


    /**
     * 图片上传demo
     * @param imgPath
     */
//    public Observable<UploadFile > upLoadImg(String imgPath) {
//
//        File file = new File(imgPath);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//        return ApiM.getInstance().service.uploadFile(body).compose(RxSchedulers.<UploadFile>io_main());
//
//    }


    /**
     * 扫描功能
     */
    private IScan scan ;

    /**
     * 获取扫描到的车辆列表
     * @return 扫描到的车辆列表
     */
    public List<Car> getScanedCarList(){
        List<Car> list = new ArrayList<>();
        if(scan != null){
            list = scan.getScanedCarList();
        }

        if(list==null){
            return new ArrayList<>();
        }
        return list;
    }

    public void setScan(IScan scan) {
        this.scan = scan;
    }

}
