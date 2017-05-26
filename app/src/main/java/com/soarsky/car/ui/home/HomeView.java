package com.soarsky.car.ui.home;


import com.soarsky.car.base.BaseView;
import com.soarsky.car.ui.home.view.CheckVersion;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  检测版本更新的视图层
 */
public interface HomeView extends BaseView {

    public void showSuccess();
    public void showFail();
    public void checkSuccess(CheckVersion checkVersion);
    public void checkFail(CheckVersion checkVersion);
    public void showError(Throwable e);
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response);
    public void loadFail(Call<ResponseBody> call, Throwable t);
}
