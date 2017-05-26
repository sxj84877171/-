package com.soarsky.policeclient.ui.login;

import com.soarsky.policeclient.base.BaseView;
import com.soarsky.policeclient.bean.CheckVersionDataBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

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
 * 该类为  登录View <br>
 */

public interface LoginView extends BaseView {
    /**
     * 参考其他View
     * @param o
     */
    public void showSuccess(Object o);
    /**
     * 参考其他View
     * @param o
     */
    public void showFail(Object o);
    /**
     * 参考其他View
     */
    public void checkSuccess(CheckVersionDataBean checkVersion);
    /**
     * 参考其他View
     */
    public void checkFail(CheckVersionDataBean checkVersion);
    /**
     * 参考其他View
     */
    public void showError(Throwable e);
    /**
     * 参考其他View
     */
    public void showFail();
    /**
     * 参考其他View
     */
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response);
    /**
     * 参考其他View
     */
    public void loadFail(Call<ResponseBody> call, Throwable t);


}
