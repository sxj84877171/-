package com.sxj.bank.net;

import com.sxj.bank.bean.FuncResponseBean;
import com.sxj.bank.bean.ServerBean;
import com.sxj.bank.util.FileObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;
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
