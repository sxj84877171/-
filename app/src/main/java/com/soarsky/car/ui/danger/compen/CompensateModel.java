package com.soarsky.car.ui.danger.compen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soarsky.car.CommonParam;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.SimpleImg;
import com.soarsky.car.bean.UploadFast;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;

import java.io.File;
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
    private LayoutInflater layoutInflater;

    /**
     * 设置上下文
     */
    public   void setContext(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }


    /**
     * 得到一个图片放到
     * @param imgurl
     */
    public View   getImageView(String imgurl, final ImagecancleCallback imagecancleCallback){
        ImageView imageShow;
        ImageView deleteImg;
        View  view=layoutInflater.inflate(R.layout.dangerimgup_img,null);
        imageShow= (ImageView) view.findViewById(R.id.showimg);
        deleteImg=(ImageView) view.findViewById(R.id.imgdelete);
        Glide.with(context)
                .load(imgurl)
                .into(imageShow);
        return view;
    }

    /**
     * 上传快赔照片
     * @param param
     * @return
     */
    public Observable<CompenstateParam> uploadFastDamager(CompenstateSendParam param){

        return Api.getInstance().service.uploadFastDamager(param.getStime(),param.getLocation(),param.getFcarnum(),param.getFidNo(),param.getFaffirm()
                ,param.getScarnum(),param.getSidNo(),param.getSaffirm()).compose(RxSchedulers.<CompenstateParam>io_main());
    }


    /**
     * 图片上传
     */

    public Observable<UploadFile> upLoadImg(String imgPath) {


        File file = new File(imgPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("imageUrl", file.getName(), requestFile);
        return com.soarsky.car.data.remote.server2.Api.getInstance().getService().uploadFile2(body).compose(RxSchedulers.<UploadFile>io_main());

    }


    /**
     * 上传照信息
     */
    public  Observable<UploadFast> upLoadFast(Map<String ,String > map){

        return Api.getInstance().service.appUploadFastImage(map).compose(RxSchedulers.<UploadFast>io_main());

    }

    /**
     * 获取样照
     */
        public  Observable<SimpleImg> getSimpleImg(){
            return  Api.getInstance().service.getSimpleImage().compose(RxSchedulers.<SimpleImg>io_main());
        }


}
