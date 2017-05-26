package com.soarsky.car.ui.home.main;


import com.soarsky.car.CommonParam;
import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.server.wifi.WifiTransfer;

import java.net.Socket;
import java.util.List;

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
public interface MainView extends BaseView {
    /**
     * 获取亲情号成功
     * @param param 返回参数
     */
    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param);

    /**
     * 获取亲情号失败
     */
    public void showFamilyFail();

    /**
     * 清空亲情号表的所有数据成功
     */
    public void deleteAllSuccess();

    /**
     * 清空亲情号表的所有数据失败
     */
    public void deleteAllFail();

    /**
     * 违章查询失败
     * @param e 异常
     */
    public void showError(Throwable e);

    /**
     * 违章查询成功
     * @param violationDealParam 违章信息参数
     */
    public void showSuccess(ResponseDataBean<ViolationDealInfo> violationDealParam);

    /**
     *获取驾驶证成功
     * @param param 参数
     */
    public void showLicenseSuccess(ResponseDataBean<DrivingLicenseInfo> param);

    /**
     * 获取驾驶失败
     */
    public void showLicenseError();

    /**
     *根据车牌号获取终端信息成功
     * @param index 第几个
     * @param taerminalInfoParm 对应的终端信息
     */
    public void getTerminalInfoSuccess( int  index ,ResponseDataBean<TerminalInfoParam> taerminalInfoParm);
    /**
     *根据车牌号获取终端信息失败
     */
    public void getTerminalInfoFail();

    /**
     * 图片上传成功
     * @param uploadFile 文件上传的参数
     */
    public void uploadImgSuccess(UploadFile uploadFile);

    /**
     * 图片上传失败
     */
    public void uploadImgFail();

    /**
     * 上传图片信息成功
     * @param stringResponseDataBean 返回参数
     */
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean);

    /**
     * 上传图片信息失败
     */
    public void modifyAppImageFail();


    /**
     * 链接终端成功
     */
    public void connectSuccess(final WifiTransfer transfer, Socket socket);
    /**
     * 链接失败
     */
    public void connectFail();

    /**
     * 检测版本号成功的回调函数
     * @param checkVersion 版本检测业务参数
     */
    public  void checkSuccess(ResponseDataBean<CheckVersionBean> checkVersion);

    /**
     * 检测版本号失败的回调函数
     * @param checkVersion 版本检测业务参数
     */
    public  void checkFail(ResponseDataBean<CheckVersionBean> checkVersion);

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

    /**
     * 初始化数据
     * @param commonParam
     */
    public void initViewData(CommonParam commonParam);

    /**
     * 获取车的行驶证列表
     * @param carNumParam
     */
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam);

}
