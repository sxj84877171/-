package com.soarsky.car.ui.home;


import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.LoginInfo;
import com.soarsky.car.bean.ResponseDataBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  检测版本更新的视图层<br>
 */
public interface HomeView extends BaseView {
    /**
     * 显示成功的回调
     */
    public void showSuccess();
    /**
     * 显示失败的回调
     */
    public void showFail();

    /**
     * 检测版本号成功的回调
     * @param checkVersion  版本信息
     */
    public void checkSuccess(ResponseDataBean<CheckVersionBean> checkVersion);
    /**
     * 检测版本号失败的回调
     * @param checkVersion  版本信息
     */
    public void checkFail(ResponseDataBean<CheckVersionBean> checkVersion);

    /**
     * 网络错误
     * @param e  错误信息
     */
    public void showError(Throwable e);

    /**
     * 下载安装文件成功
     * @param call
     * @param response
     */
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response);

    /**
     * 下载安装文件失败
     * @param call
     * @param t
     */
    public void loadFail(Call<ResponseBody> call, Throwable t);


}
