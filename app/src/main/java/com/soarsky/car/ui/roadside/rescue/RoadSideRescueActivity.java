package com.soarsky.car.ui.roadside.rescue;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideCarTypeParam;
import com.soarsky.car.bean.RoadSideRescueInfo;
import com.soarsky.car.ui.roadside.RoadSideDialogListener;
import com.soarsky.car.ui.roadside.dialog.RoadSideBottomDialog;
import com.soarsky.car.ui.roadside.order.RoadSideOrderActivity;
import com.soarsky.car.uitl.ToastUtil;
import com.soarsky.car.uitl.ValidatorUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import static com.soarsky.car.ConstantsUmeng.ROADSIDE_APPLY_BTN;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：申请救援<br>
 * 该类为 RoadSideRescueActivity<br>
 */


public class RoadSideRescueActivity extends BaseActivity<RoadSideRescuePresent,RoadSideRescueModel> implements RoadSideRescueView,View.OnClickListener,BDLocationListener {

    /**
     * 车牌号编辑框
     */
    private EditText road_side_rescue_carnumEt;
    /**
     * 车辆类型布局
     */
    private LinearLayout road_side_cartype_Lay;
    /**
     * 车辆类型编辑框
     */
    private TextView road_side_rescue_cartypeTv;
    /**
     * 服务项目布局
     */
    private LinearLayout road_side_sever_Lay;
    /**
     * 服务项目编辑框
     */
    private TextView road_side_rescue_severTv;
    /**
     * 当前位置
     */
    private EditText road_side_rescue_positionEt;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 地图图层
     */
    private MapView roadSideMapView;
    /**
     * 地图
     */
    private BaiduMap baiduMap;
    /**
     *申请救援
     */
    private Button roadSideApplyBtn;

    /**
     *声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 对话框
     */
    private RoadSideBottomDialog.Builder builder;

    private Dialog dialog;
    /**
     * 车辆类型list
     */
    private List<String> carType_list = new ArrayList<String>();
    /**
     * 服务商list
     */
    private List<String> sever_list = new ArrayList<String>();
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG = "RoadSideRescueActivity";


    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_rescue;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsideapply));

        road_side_rescue_carnumEt = (EditText) findViewById(R.id.road_side_rescue_carnumEt);
        road_side_rescue_carnumEt.setText(app.getCommonParam().getCarNum()==null?"":app.getCommonParam().getCarNum());

        road_side_cartype_Lay = (LinearLayout) findViewById(R.id.road_side_cartype_Lay);
        road_side_cartype_Lay.setOnClickListener(this);

        road_side_rescue_cartypeTv = (TextView) findViewById(R.id.road_side_rescue_cartypeTv);
        road_side_rescue_cartypeTv.setOnClickListener(this);
        road_side_sever_Lay = (LinearLayout) findViewById(R.id.road_side_sever_Lay);
        road_side_sever_Lay.setOnClickListener(this);

        road_side_rescue_severTv = (TextView) findViewById(R.id.road_side_rescue_severTv);
        road_side_rescue_severTv.setOnClickListener(this);

        road_side_rescue_positionEt = (EditText) findViewById(R.id.road_side_rescue_positionEt);

        roadSideApplyBtn = (Button) findViewById(R.id.roadSideApplyBtn);
        roadSideApplyBtn.setOnClickListener(this);

        roadSideMapView = (MapView) findViewById(R.id.roadSideMapView);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        baiduMap = roadSideMapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        initLocation();
        mLocationClient.start();
        // 设置为一般地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mPresenter.getCarType();
    }


    @Override
    protected void onResume() {
        super.onResume();
        roadSideMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        roadSideMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        roadSideMapView.onDestroy();
        roadSideMapView = null;
    }

    /**
     * 配置定位SDK参数
     */
    private void initLocation(){

        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        int span=1000;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.roadSideApplyBtn:
//                mPresenter.gotoRoadSideOrder();
                MobclickAgent.onEvent(RoadSideRescueActivity.this,ROADSIDE_APPLY_BTN);
                if (mPresenter.verifyData()){
                    RoadSideRescueSendParam param = new RoadSideRescueSendParam();
                    param.setCarnnum(road_side_rescue_carnumEt.getText().toString());
                    param.setCtype(road_side_rescue_cartypeTv.getText().toString());
                    param.setLocation(road_side_rescue_positionEt.getText().toString());
                    param.setServerItem(road_side_rescue_severTv.getText().toString());
                    param.setPhone(app.getCommonParam().getOwerPhone()== null?"":app.getCommonParam().getOwerPhone());
                    mPresenter.uploadResouse(param);
                }
                break;
            case R.id.road_side_cartype_Lay:
            case R.id.road_side_rescue_cartypeTv:
                //有弹框，无需向用户提示          王松清
                //ToastUtil.show(this,"road_side_cartype_Lay");
                mPresenter.showCarTypeDialog(carType_list);
                break;
            case R.id.road_side_sever_Lay:
            case R.id.road_side_rescue_severTv:
                //有弹框，无需向用户提示          王松清
                //ToastUtil.show(this,"road_side_sever_Lay");
                mPresenter.showSeverDialog(sever_list);
                break;
        }
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || roadSideMapView == null)
            return;
        if(isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius()).direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();

            road_side_rescue_positionEt.setText(bdLocation.getAddrStr());

            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //设置地图中心点以及缩放级别
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);
            baiduMap.animateMapStatus(u);
            //设置定位数据
            baiduMap.setMyLocationData(locData);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.carlocation);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(ll)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            baiduMap.addOverlay(option);
            isFirstLoc = false;
        }
    }

    /**
     * 跳转确认订单界面
     * @param roadSideRescueParam  订单信息
     */
    @Override
    public void gotoRoadSideOrder(ResponseDataBean<RoadSideRescueInfo> roadSideRescueParam) {

        RescueParam p = new RescueParam();
        p.setCarNum(road_side_rescue_carnumEt.getText().toString());
        p.setCarPosition(road_side_rescue_positionEt.getText().toString());
        p.setCarType(road_side_rescue_cartypeTv.getText().toString());
        p.setPhone(app.getCommonParam().getOwerPhone()==null?"":app.getCommonParam().getOwerPhone());
        p.setStartTime(roadSideRescueParam.getData().getCreateTime());
        p.setReachTime(roadSideRescueParam.getData().getReachTime());
        p.setSeverFee(roadSideRescueParam.getData().getCost() == null?"0":roadSideRescueParam.getData().getCost());
        p.setDistance(roadSideRescueParam.getData().getDistance());
        p.setSeverPhone(roadSideRescueParam.getData().getCompanyPhone());
        p.setCompany(roadSideRescueParam.getData().getCompany());
        p.setType(roadSideRescueParam.getData().getServiceItems());
        Intent i = new Intent();
        i.setClass(this, RoadSideOrderActivity.class);
        i.putExtra("rescueParam",p);
        startActivity(i);
        finish();
    }
    /**
     * 车辆类型对话框
     * @param _list 车辆类型
     */
    @Override
    public void showCarTypeDialog(List<String> _list) {

        if(_list.size() == 0) {
            String[] data = getResources().getStringArray(R.array.dialog_car_type);
            for (String d : data) {
                _list.add(d);
            }
        }
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        builder = new RoadSideBottomDialog.Builder(this, width, height / 3, new RoadSideDialogListener() {
            @Override
            public void confirmClick(Object o) {
                if(o != null) {
                    road_side_rescue_cartypeTv.setText((String) o);
                }
                dialog.dismiss();
            }
        },_list,0);

        dialog = builder.create();
        dialog.show();

    }
    /**
     * 服务对话框
     * @param _list 集合
     */
    @Override
    public void showSeverDialog(List<String> _list) {


        if(_list.size() == 0) {
            String[] data = getResources().getStringArray(R.array.dialog_sever_type);
            for (String d : data) {
                _list.add(d);
            }
        }

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        builder = new RoadSideBottomDialog.Builder(this, width, height / 3, new RoadSideDialogListener() {
            @Override
            public void confirmClick(Object o) {
                if(o != null) {
                    road_side_rescue_severTv.setText((String) o);
                }
                dialog.dismiss();
            }
        },_list,1);
        dialog = builder.create();
        dialog.show();
    }
    /**
     * 获取车辆类型成功
     * @param roadSideCarTypeParam 参数
     */
    @Override
    public void getCarTypeSuccess(ResponseDataBean<List<RoadSideCarTypeParam>> roadSideCarTypeParam) {

        carType_list.clear();
        sever_list.clear();

        if(roadSideCarTypeParam != null){

            if(Constants.REQUEST_SUCCESS.equals(roadSideCarTypeParam.getStatus())){

                List<RoadSideCarTypeParam> list = roadSideCarTypeParam.getData();
                for (RoadSideCarTypeParam bean:list){
                    carType_list.add(bean.getDtext());
                }

            }else {
                //将后台的message转换成我们自己的内容显示给用户               王松清
                ToastUtil.show(this,R.string.get_car_type_fail);
            }
        }

    }

    /**
     * 获取车辆类型失败
     */
    @Override
    public void getCarTypeFail() {

        ToastUtil.show(this,R.string.throwable);
    }
    /**
     * 上传救援申请成功
     * @param roadSideRescueParam 上传救援申请成功
     */
    @Override
    public void uploadResouseSuccess(ResponseDataBean<RoadSideRescueInfo> roadSideRescueParam) {

        if(roadSideRescueParam != null){
            if(Constants.REQUEST_SUCCESS.equals(roadSideRescueParam.getStatus())){
                mPresenter.gotoRoadSideOrder(roadSideRescueParam);
            }else {
                ToastUtil.show(this,roadSideRescueParam.getMessage());
            }
        }

    }
    /**
     * 上传救援申请失败
     */
    @Override
    public void uploadResouseFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    /**
     * 验证输入字符
     * @return boolean true 通过反之
     */
    @Override
    public boolean verifyData() {

        boolean flag = false;

        if(TextUtils.isEmpty(road_side_rescue_carnumEt.getText().toString())|| !ValidatorUtils.validatorCarNo(road_side_rescue_carnumEt.getText().toString())){
            ToastUtil.show(this,R.string.roadsidecarnumempty);
            flag = false;
            return false;
        }else {
            flag = true;
        }
        if(TextUtils.isEmpty(road_side_rescue_cartypeTv.getText().toString())){
            ToastUtil.show(this,R.string.roadsidecartypeempty);
            flag = false;
            return false;
        }else {
            flag = true;
        }
        if (TextUtils.isEmpty(road_side_rescue_severTv.getText().toString())){
            ToastUtil.show(this,R.string.roadsideseverempty);
            flag = false;
            return false;
        }else {
            flag = true;
        }
        if(TextUtils.isEmpty(road_side_rescue_positionEt.getText().toString())){
            ToastUtil.show(this,R.string.roadsidepositionempty);
            flag = false;
            return false;
        }else {
            flag = true;
        }
        return flag;
    }
}
