package com.sxj.carloan.net;

import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.LoanQuery;
import com.sxj.carloan.bean.ProductBean;
import com.sxj.carloan.bean.QueryMonth;
import com.sxj.carloan.bean.ResultListBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.bean.VersionInfo;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.util.FileObject;

import java.io.File;
import java.util.List;
import java.util.Map;
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

    public ServerBean PageWorkLocal(final String pageSize, final String offset, final String desc) {
        Object o = FileObject.getObject("PageWork$" + pageSize + "$" + offset + "$" + desc);
        if (o != null) {
            try {
                ServerBean serverBean = (ServerBean) o;
                return serverBean;
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public Observable<FuncResponseBean> insert(ServerBean.RowsBean rowsBean) {
        return Api.getInstance().getService().InsertTable(rowsBean.getCase_type_id_1() + "",
                rowsBean.getCust_name_tmp(), rowsBean.getCust_sex(),
                rowsBean.getCust_iden(), rowsBean.getCust_marriage_id() + "",
                rowsBean.getCust_mobile(), rowsBean.getIf_gcr_id() + "",
                rowsBean.getRoom_type_id() + "", rowsBean.getCust_address(),
                rowsBean.getHome_visit_date(), rowsBean.getCar_type() + "",
                rowsBean.getDeal_price() + "", rowsBean.getCredit_years() + "",
                rowsBean.getInstallment_type_id_1() + "", rowsBean.getUser_id_ywy() + "",
                rowsBean.getDate_ywy(), rowsBean.getCase_state_id() + "").compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> insertBean(ServerBean.RowsBean rowsBean) {
        return Api.getInstance().getService().InsertTable(rowsBean).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    /**
     * 业务员上传的车辆图片
     * @param id
     * @return
     */
    public Observable<FuncResponseBean> getCarPhotoName(int id){
        return Api.getInstance().getService().GetCarPhotoName(id).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> FileExisted(String filename){
        return Api.getInstance().getService().FileExisted(filename).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> YwyPhotoOk(int id,String caseID){
        return Api.getInstance().getService().YwyPhotoOk(id,caseID).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    /**
     * 调查员上传的图片
     * @param id
     * @return
     */
    public Observable<FuncResponseBean> GetDcyPhotoName(int id){
        return Api.getInstance().getService().GetDcyPhotoName(id).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> insertBean(Map rowsBean) {
        return Api.getInstance().getService().InsertTable(rowsBean).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> update(ServerBean.RowsBean rowsBean) {
        return Api.getInstance().getService().UpdateTable(rowsBean).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

    public Observable<FuncResponseBean> update(Map rowsBean) {
        return Api.getInstance().getService().UpdateTable(rowsBean).compose(ApiServiceModel.<FuncResponseBean>io_main());
    }

//    public Observable<FuncResponseBean> update(ServerBean.RowsBean rowsBean) {
//        return Api.getInstance().getService().UpdateTable(rowsBean.getId() + "", rowsBean.getCase_type_id_1() + "",
//                rowsBean.getCust_name_tmp(), rowsBean.getCust_sex(),
//                rowsBean.getCust_iden(), rowsBean.getCust_marriage_id() + "",
//                rowsBean.getCust_mobile(), rowsBean.getIf_gcr_id() + "",
//                rowsBean.getRoom_type_id() + "", rowsBean.getCust_address(),
//                rowsBean.getHome_visit_date(), rowsBean.getCar_type() + "",
//                rowsBean.getDeal_price() + "", rowsBean.getCredit_years() + "",
//                rowsBean.getInstallment_type_id_1() + "", rowsBean.getUser_id_ywy() + "",
//                rowsBean.getDate_ywy(), rowsBean.getCase_state_id() + ""
//        ).compose(ApiServiceModel.<FuncResponseBean>io_main());
//    }

    public Call<ResponseBody> uploadIdPhoto(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}_1.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);


        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 申请正面照片：photo/ywy/{case_id}/1_1.jpg
     *
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanShenFengzhengZhengmian(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/1_1.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 申请背面照片：photo/ywy/{case_id}_1_2.jpg
     *
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanShenFengzhengFanmian(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/1_2.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 副总签名照：photo/ywy/{case_id}_2.jpg
     *
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanFuzong(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/1_3.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 总经理签名照：photo/ywy/{case_id}_3.jpg
     *
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanZongjingli(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/1_4.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     * 车辆照片：photo/car/{case_id}_年月日时分秒{index}.jpg
     *
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanCheLiang(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String time = DateUtil.getImageDate();
        String filename = "photo/{case_id}/2_{time}{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public String getRandom() {
        int index = new Random().nextInt() % 10;
        if(index < 0){
            index = -index;
        }
        return "" + index ;
    }

    /**
     * 调查员上传照片：photo/dcy/{ case_id }_{照片类别}d_年月日时分秒{index}.jpg
     * 例如：1_1d_08171015220.jpg
     *
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanDiaoChayuan1(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String time = DateUtil.getImageDate();
        String filename = "photo/{case_id}/3_1d{time}{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan2(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String time = DateUtil.getImageDate();
        String filename = "photo/{case_id}/3_2d{time}{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan3(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/3_3d{time}{rn}.jpg";
        String time = DateUtil.getImageDate();
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    /**
     <option value='1'>家庭住址照</option>
     <option value='2'>工作单位照</option>
     <option value='3'>银行流水照</option>
     <option value='4'>面签照</option>
     <option value='5'>产调照</option>
     <option value='6'>证件照</option>
     * @param case_id
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanDiaoChayuan4(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/3_4d{time}{rn}.jpg";
        String time = DateUtil.getImageDate();
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan5(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/3_5d{time}{rn}.jpg";
        String time = DateUtil.getImageDate();
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> shangchuanDiaoChayuan6(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/3_6d{time}{rn}.jpg";
        String time = DateUtil.getImageDate();
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }



    /**
     * 调查员上传照片：photo/dcy/{ case_id }_{照片类别}d_年月日时分秒{index}.jpg
     * 例如：1_1d_08171015220.jpg
     *
     * @param file
     * @return
     */
    public Call<ResponseBody> shangchuanDiaoChayuan(String case_id, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String time = DateUtil.getImageDate();
        String filename = "photo/{case_id}/3_1d{time}{rn}.jpg";
        filename = filename.replace("{case_id}", case_id + "");
        filename = filename.replace("{time}", time);
        filename = filename.replace("{rn}", "" + getRandom());
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }


    public Observable<FuncResponseBean> uploadPhoto(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/1_1.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().uploadImageFile(filename, body);
    }

    public Call<ResponseBody> uploadManagerPhoto(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/1_2.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }

    public Call<ResponseBody> uploadManager3Photo(ServerBean.RowsBean rowsBean, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String filename = "photo/{case_id}/3_3.jpg";
        filename = filename.replace("{case_id}", rowsBean.getId() + "");
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        return Api.getInstance().getService().updateLoadImageFile(filename, body);
    }


    /**
     * 获取所有产品
     */
    public Observable<ResultListBean<ProductBean>> queryProduct(final String pageSize, final String offset, final String desc) {
        return Api.getInstance().getService().queryProduct(pageSize, offset, desc).compose(ApiServiceModel.<ResultListBean<ProductBean>>io_main()).map(new Func1<ResultListBean<ProductBean>, ResultListBean<ProductBean>>() {
            @Override
            public ResultListBean<ProductBean> call(ResultListBean<ProductBean> o) {
                FileObject.saveObject("queryProduct" + pageSize + "$" + offset + "$" + desc, o);
                return o;
            }
        });
    }

    public Observable<ResultListBean<LoanQuery>> s(String bgn_date,String end_date){
        String sql = "select count(case when date_ywy between '" + bgn_date + "' and '" + end_date + "' then 1 end) as ywy_case_num," +
                " count(case when date_ywy_qk between '" + bgn_date + "' and '" + end_date + "' then 1 end) as ywy_qk_num," +
                " count(case when date_zhengxin_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as zhengxin_num," +
                " count(case when date_baoxian_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as baoxian_num," +
                " count(case when date_dispatch_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as dispatch_num," +
                " count(case when date_dcy_yw between '" + bgn_date + "' and '" + end_date + "' then 1 end) as dcy_num," +
                " count(case when date_ds_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as ds_num," +
                " count(case when date_fk between '" + bgn_date + "' and '" + end_date + "' then 1 end) as fk_num," +
                " count(case when date_loan_cw_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as loan_cw_num " +
                " from t_case" ;
        return Api.getInstance().getService().searchTableBySQL(sql).compose(ApiServiceModel.<ResultListBean<LoanQuery>>io_main());
    }


    public Observable<ResultListBean<VersionInfo>> getNewVersionInfo(){
        return Api.getInstance().getService().getNewVersionInfo().compose(ApiServiceModel.<ResultListBean<VersionInfo>>io_main());
    }

    public Observable<ResultListBean<LoanQuery>> searchTableBySQL(String sql){
        return Api.getInstance().getService().searchTableBySQL(sql).compose(ApiServiceModel.<ResultListBean<LoanQuery>>io_main());
    }

    public Observable<ResultListBean<QueryMonth>> searchTableBySQLByMonth(String sql){
        return Api.getInstance().getService().searchTableBySQLByMonth(sql).compose(ApiServiceModel.<ResultListBean<QueryMonth>>io_main());
    }
}
