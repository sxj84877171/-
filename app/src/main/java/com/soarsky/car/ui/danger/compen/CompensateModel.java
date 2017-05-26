package com.soarsky.car.ui.danger.compen;

import android.content.Context;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CompenstateParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.SimpleImgBean;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.helper.RxSchedulers;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CompensateModel implements BaseModel {

    private Context context;

    /**
     * 设置上下文
     */
    public   void setContext(Context context){
        this.context=context;
    }



    /**
     * 上传快赔照片
     * @param param CompenstateSendParam对象
     * @return ResponseDataBean<CompenstateParam>对象
     */
    public Observable<ResponseDataBean<CompenstateParam>> uploadFastDamager(CompenstateSendParam param){

        return api.uploadFastDamager(param.getStime(),param.getLocation(),param.getFcarnum(),param.getFidNo(),param.getFaffirm()
                ,param.getScarnum(),param.getSidNo(),param.getSaffirm()).compose(RxSchedulers.<ResponseDataBean<CompenstateParam>>io_main());
    }


    /**
     * 图片上传
     * @param imgPath 图片路径
     * @return UploadFile对象
     */

    public Observable<UploadFile> upLoadImg(String imgPath) {


        File file = new File(imgPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("imageUrl", file.getName(), requestFile);
        return api.uploadFile2(body).compose(RxSchedulers.<UploadFile>io_main());

    }


    /**
     * 上传照信息
     * @param map 图片路径的map
     * @return ResponseDataBean对象
     */


    public  Observable<ResponseDataBean> upLoadFast(Map<String ,String > map){

        return api.appUploadFastImage(map).compose(RxSchedulers.<ResponseDataBean>io_main());

    }

    /**
     * 获取样照
     * @return 照片集合
     */
    public  Observable<ResponseDataBean<List<SimpleImgBean>>> getSimpleImg(){
            return  api.getSimpleImage().compose(RxSchedulers.<ResponseDataBean<List<SimpleImgBean>>>io_main());
        }


}
