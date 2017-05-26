package com.soarsky.car.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.FamilyNumIlistBean;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.server.wifi.WifiTransfer;
import com.soarsky.car.ui.carchange.CarChangeActivity;
import com.soarsky.car.ui.carnews.ArticleDetailsActivity;
import com.soarsky.car.ui.carnews.mycolection.MyColectionActivity;
import com.soarsky.car.ui.home.main.MainModel;
import com.soarsky.car.ui.home.main.MainPresent;
import com.soarsky.car.ui.home.main.MainView;
import com.soarsky.car.ui.license.DrivLicenseActivity;
import com.soarsky.car.ui.newfamilynum.NewFamilyNumActivity;
import com.soarsky.car.ui.setting.SettingActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.soarsky.car.Constants.READ;
import static com.soarsky.car.ConstantsUmeng.COLLECTION;
import static com.soarsky.car.ConstantsUmeng.FAMILY_NUM;
import static com.soarsky.car.ConstantsUmeng.ID_CARD;
import static com.soarsky.car.ConstantsUmeng.SERVICE;
import static com.soarsky.car.ConstantsUmeng.SWITCH_CAR;
import static com.soarsky.car.ConstantsUmeng.VOICE_SETTING;

/**
 * Andriod_Car_App
 * 作者： 魏凯
 * 时间： 2017/5/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */
public class NewMineFragment extends BaseFragment<MainPresent, MainModel> implements MainView, View.OnClickListener {
    private int[] imgs = { R.drawable.kefu,R.drawable.qinqing, R.drawable.yinliang, R.drawable.shoucang, R.drawable.bangzhushouce, R.drawable.qiehuan, R.drawable.yuedu, R.drawable.guanyu,  R.drawable.bangzhu};
    private String[] texts = { "驾驶证号", "亲情号码", "音量设置", "我的收藏", "使用手册","切换车辆", "最后阅读" , "关于APP", "帮助与客服"};
    private GridView gridView;
    private App app;
    private TextView idCardNum;

    /**
     * 设置按钮
     */
    private ImageView setting;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_mine;
    }

    @Override
    public void initView(View view) {

        app = (App) getActivity().getApplication();

        idCardNum = (TextView) view.findViewById(R.id.UserNameTv);
        String idNum = app.getCommonParam().getIdNo();
        if (idNum.length() != 0){
        String s1 = idNum.substring(0,5);
        String s2 = idNum.substring(14);
        idCardNum.setText(s1+"******"+s2);
        }
        setting = (ImageView) view.findViewById(R.id.setting);
        setting.setOnClickListener(this);

        gridView = (GridView) view.findViewById(R.id.gridView);
        List<Map<String, Object>> lisItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("img", imgs[i]);
            listem.put("text", texts[i]);
            lisItems.add(listem);
        }
        gridView.setAdapter(new SimpleAdapter(getActivity(),lisItems,R.layout.home_gridview_item,new String[] { "img", "text" },
                new int[] {R.id.img,R.id.text}));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (0 == position){//驾驶证号
                    MobclickAgent.onEvent(getActivity(), ID_CARD);
                    //驾驶证详情

                    if (SpUtil.get(Constants.LICESE_NUM) != null && !SpUtil.get(Constants.LICESE_NUM).equals("")) {

                        startActivity(new Intent(getActivity(), DrivLicenseActivity.class));
//                startActivity(new Intent(MainActivity.this, LicenseValidationActivity.class));
                    } else {
                        new AlertDialog.Builder(mContext).setTitle(R.string.driver_confirm_title)
                                .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 点击“返回”后的操作,这里不设置没有任何操作
                                    }
                                }).show();
                    }

                }else if (1 == position){//亲情号码
                    MobclickAgent.onEvent(getActivity(), FAMILY_NUM);
                    if (SpUtil.get(Constants.CAR_NUM) != null && !SpUtil.get(Constants.CAR_NUM).equals("")) {
//                        mPresenter.deleteAll();
                        Intent intent = new Intent(getActivity(),NewFamilyNumActivity.class);
                        intent.putExtra("flag",1);
                        startActivity(intent);
                    } else {
                        new AlertDialog.Builder(mContext).setTitle(R.string.family_confirm_title)
                                .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 点击“返回”后的操作,这里不设置没有任何操作
                                    }
                                }).show();
                    }

                }else if (2 == position){//音量设置
                    MobclickAgent.onEvent(getActivity(), VOICE_SETTING);
                    if (app.isConfirmDriver()) {
                        startActivity(new Intent(getActivity(), VoiceSettingActivity.class));
                    } else {
                        new AlertDialog.Builder(mContext).setTitle(R.string.main_confirm_title)
                                .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 点击“返回”后的操作,这里不设置没有任何操作
                                    }
                                }).show();
                    }
                }else if (3 == position){//我的收藏

                    MobclickAgent.onEvent(getActivity(),COLLECTION);
                    startActivity(new Intent(getActivity(), MyColectionActivity.class));
                }else if (4 == position){//使用手册


                } else if (5 == position){//切换车辆
                    MobclickAgent.onEvent(getActivity(), SWITCH_CAR);
                    startActivity(new Intent(getActivity(), CarChangeActivity.class));
                } else if (6 == position){//最后阅读
                    String read = SpUtil.get(READ);
                    if (read.equals("")){
                        ToastUtil.show(mContext,getString(R.string.no_latest));
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(mContext, ArticleDetailsActivity.class);
                        intent.putExtra("aid",Integer.parseInt(read));
                        startActivity(intent);
                    }
                } else if (7 == position){//关于app


                } else if (8 == position){//客服与帮助
                    MobclickAgent.onEvent(getActivity(), SERVICE);
                    //客服

//                new AlertDialog.Builder(mContext).setTitle(R.string.kefu)
//                        .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // 点击“返回”后的操作,这里不设置没有任何操作
//                            }
//                        }).show();

                }
            }
        });

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param) {
        if (param != null) {
            if (Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
                if (param.getData().getIlist().size() > 0) {
                    //插入数据库
                    insertFamilyNum(param.getData().getIlist());
                    List<FamilyNumIlistBean> _list = new ArrayList<FamilyNumIlistBean>();
//                  剔除车主号码
                    for (FamilyNumIlistBean bean : param.getData().getIlist()) {
                        if (bean.getIsOwner() == 0) {
                            _list.add(bean);
                        }
                    }

                    Intent it = new Intent();
                    it.setClass(getActivity(), NewFamilyNumActivity.class);
                    startActivity(it);

                } else {
                    Intent it = new Intent();
                    it.setClass(getActivity(), NewFamilyNumActivity.class);
                    startActivity(it);
                }
            } else {
                ToastUtil.show(getActivity(), param.getMessage());

            }
        }
    }

    /**
     * 获取亲情号失败
     */
    @Override
    public void showFamilyFail() {
        ToastUtil.show(getActivity(), R.string.throwable);
    }

    /**
     * 清空亲情号表的所有数据成功
     */
    @Override
    public void deleteAllSuccess() {
        QueryFamilySendParam p = new QueryFamilySendParam();
        p.setPhone(app.getCommonParam().getOwerPhone());
        p.setUsername(app.getCommonParam().getUserName());
        p.setCarnum(app.getCommonParam().getCarNum());
        mPresenter.queryFirendPhone(p);
    }

    @Override
    public void deleteAllFail() {
        ToastUtil.show(getActivity(), R.string.familynum_change_fail);
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showSuccess(ResponseDataBean<ViolationDealInfo> violationDealParam) {

    }

    @Override
    public void showLicenseSuccess(ResponseDataBean<DrivingLicenseInfo> param) {

    }

    @Override
    public void showLicenseError() {

    }

    @Override
    public void getTerminalInfoSuccess(int index, ResponseDataBean<TerminalInfoParam> taerminalInfoParm) {

    }

    @Override
    public void getTerminalInfoFail() {

    }

    @Override
    public void uploadImgSuccess(UploadFile uploadFile) {

    }

    @Override
    public void uploadImgFail() {

    }

    @Override
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean) {

    }

    @Override
    public void modifyAppImageFail() {

    }

    @Override
    public void connectSuccess(WifiTransfer transfer, Socket socket) {

    }

    @Override
    public void connectFail() {

    }

    @Override
    public void checkSuccess(ResponseDataBean<CheckVersionBean> checkVersion) {

    }

    @Override
    public void checkFail(ResponseDataBean<CheckVersionBean> checkVersion) {

    }

    @Override
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void loadFail(Call<ResponseBody> call, Throwable t) {

    }

    @Override
    public void initViewData(CommonParam commonParam) {

    }

    @Override
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {

    }

    /**
     * 插入数据
     *
     * @param list 数据源
     */
    private void insertFamilyNum(List<FamilyNumIlistBean> list) {
        for (FamilyNumIlistBean bean : list) {
            FamilyNum familyNum = new FamilyNum();
            familyNum.setPhone(bean.getPhone());
            familyNum.setUsername(app.getCommonParam().getUserName());
            familyNum.setCarnum(app.getCommonParam().getCarNum());
            familyNum.setIs_owner(bean.getIsOwner());
            familyNum.setSstate(1);
            familyNum.setTstate(0);
            mPresenter.insertFamilyNum(familyNum);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }
}
