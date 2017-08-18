package com.sxj.carloan.net;

import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.FileObject;

import java.io.File;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * CarLoan
 * 作者： Elvis
 * 时间： 2017/6/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ApiServiceModel {

    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public Observable<LoginBack> UserLogin(String username, String password) {
        return Api.getInstance().getService().UserLogin(username, password).compose(ApiServiceModel.<LoginBack>io_main()).map(new Func1<LoginBack, LoginBack>() {
            @Override
            public LoginBack call(LoginBack o) {
                // save
                return o;
            }
        });
    }

    public Observable<ServerBean> PageWork(final String pageSize, final String offset, final String desc) {
        return Api.getInstance().getService().PageWork(pageSize, offset, desc).compose(ApiServiceModel.<ServerBean>io_main()).map(new Func1<ServerBean, ServerBean>() {
            @Override
            public ServerBean call(ServerBean o) {
                FileObject.saveObject("PageWork$" + pageSize + "$" + offset + "$" + desc, o);
                return o;
            }
        });
    }

    public ServerBean PageWorkLocal(final String pageSize, final String offset, final String desc){
        Object o = FileObject.getObject("PageWork$" + pageSize + "$" + offset + "$" + desc);
        if(o != null){
            try {
                ServerBean serverBean = (ServerBean) o;
                return serverBean;
            }catch (Exception ex){

            }
        }
        return null;
    }

    public Observable<FuncResponseBean> insert(ServerBean.RowsBean rowsBean) {
        return Api.getInstance().getService().InsertTable(rowsBean.getCase_type_id() + "",
                rowsBean.getCust_name_tmp(), rowsBean.getCust_sex(),
                rowsBean.getCust_iden(), rowsBean.getCust_marriage_id() + "",
                rowsBean.getCust_mobile(), rowsBean.getIf_gcr_id() + "",
                rowsBean.getRoom_type_id() + "", rowsBean.getCust_address(),
                rowsBean.getHome_visit_date(), rowsBean.getCar_type() + "",
                rowsBean.getDeal_price() + "", rowsBean.getCredit_years() + "",
                rowsBean.getInstallment_type_id() + "", rowsBean.getUser_id_ywy() + "",
                rowsBean.getDate_ywy(), rowsBean.getCase_state_id() + "").compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> insertBean(ServerBean.RowsBean rowsBean) {
        return Api.getInstance().getService().InsertTable(rowsBean).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> update(ServerBean.RowsBean rowsBean) {
        return Api.getInstance().getService().UpdateTable(rowsBean.getId() + "", rowsBean.getCase_type_id() + "",
                rowsBean.getCust_name_tmp(), rowsBean.getCust_sex(),
                rowsBean.getCust_iden(), rowsBean.getCust_marriage_id() + "",
                rowsBean.getCust_mobile(), rowsBean.getIf_gcr_id() + "",
                rowsBean.getRoom_type_id() + "", rowsBean.getCust_address(),
                rowsBean.getHome_visit_date(), rowsBean.getCar_type() + "",
                rowsBean.getDeal_price() + "", rowsBean.getCredit_years() + "",
                rowsBean.getInstallment_type_id() + "", rowsBean.getUser_id_ywy() + "",
                rowsBean.getDate_ywy(), rowsBean.getCase_state_id() + ""
        ).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Call<ResponseBody> uploadIdPhoto(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_1.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);


        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 申请正面照片：photo/ywy/{case_id}_1_1.jpg
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanShenFengzhengZhengmian(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_1_1.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 申请背面照片：photo/ywy/{case_id}_1_2.jpg
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanShenFengzhengmian(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_1_1.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 副总签名照：photo/ywy/{case_id}_2.jpg
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanFuzong(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_2.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 总经理签名照：photo/ywy/{case_id}_3.jpg
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanZongjingli(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_2.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 车辆照片：photo/car/{case_id}_年月日时分秒{index}.jpg
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanCheLiang(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/car/{case_id}_{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 调查员上传照片：photo/dcy/{ case_id }_{照片类别}d_年月日时分秒{index}.jpg
     * 例如：1_1d_08171015220.jpg
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanDiaoChayuan1(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/dcy/{case_id}_1d{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan2(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/dcy/{case_id}_2d{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan3(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/dcy/{case_id}_3d{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan4(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/dcy/{case_id}_4d{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan5(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/dcy/{case_id}_5d{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 调查员上传照片：photo/dcy/{ case_id }_{照片类别}d_年月日时分秒{index}.jpg
     * 例如：1_1d_08171015220.jpg
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanDiaoChayuan(String case_id,String time, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/dcy/{case_id}_1d{time}_{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}",time);
        filename = filename.replace("{rn}","" + new Random().nextInt());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }


    public Observable<FuncResponseBean> uploadPhoto(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_1.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().uploadImageFile(filename, body);
    }

    public Call<ResponseBody> uploadManagerPhoto(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_2.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> uploadManager3Photo(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/ywy/{case_id}_3.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }
}
