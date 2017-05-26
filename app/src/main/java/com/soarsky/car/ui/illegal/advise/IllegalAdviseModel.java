package com.soarsky.car.ui.illegal.advise;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.helper.RxSchedulers;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

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
 * 程序功能：违章劝离Model<br>
 * 该类为 IllegalAdviseModel<br>
 */


public class IllegalAdviseModel implements BaseModel{


    /**
     * 图片上传
     * @param imgPath 路径
     * @return    UploadFile返回信息
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
     * 上传违章撤销申请
     * @param carnum 车牌号
     * @param ptype 类别
     * @param rtime 申请时间
     * @param stime 开始时间
     * @param imageUrl 路径
     * @return ResponseDataBean<Void> 返回参数
     */
    public Observable<ResponseDataBean<String>> uploadViolationAdvice(String carnum,String ptype,String rtime,String stime,String imageUrl){

        return api.uploadViolationAdvice(carnum,ptype,rtime,stime,imageUrl).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }

}
