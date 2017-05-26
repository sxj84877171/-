package com.soarsky.policeclient.ui.accident.listitem;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.data.local.db.bean.accident.Accident;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.ui.accident.AccidentDialogUtil;
import com.soarsky.policeclient.ui.accident.DataNoSerializable;
import com.soarsky.policeclient.ui.accident.SelectCarListAdapter;
import com.soarsky.policeclient.ui.accident.add.IAccident;
import com.soarsky.policeclient.ui.accident.add.OnAccidentMessageResponseListener;
import com.soarsky.policeclient.ui.accident.itemcar.AccidentCarDetailActivity;
import com.soarsky.policeclient.ui.violation.BigPictureActivity;
import com.soarsky.policeclient.uitl.ToastUtil;
import com.soarsky.policeclient.server.CheckService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析每一个事故详情页面Activity<br>
 */
public class AccidentListItemActivity extends BaseActivity<AccidentListItemPresent,AccidentListItemModel> implements AccidentListItemView{
    /**
     * 参考AddAccidentActivity
     */
    private TextView headerTv;
    /**
     * 参考AddAccidentActivity
     */
    private RelativeLayout back;
    /**
     * 参考AddAccidentActivity
     */
    private TextView time;
    /**
     * 参考AddAccidentActivity
     */
    private TextView didian;
    /**
     * 参考AddAccidentActivity
     */
    private TextView yuanyin;
    /**
     * 参考AddAccidentActivity
     */
    AccidentDataBean.AccidentItemBean itemBean;
    /**
     * 参考AddAccidentActivity
     */
    private GridView images;
    /**
     * 参考AddAccidentActivity
     */
    private ListView cars;
    /**
     * 参考AddAccidentActivity
     */
    private ImageAdapter imageAdapter;
    /**
     * 参考AddAccidentActivity
     */
    private CarAdapter carAdapter;
    /**
     * 参考AddAccidentActivity
     */
    private TextView addAccidentCar;
    /**
     * 参考AddAccidentActivity
     */
    private HashMap<Car,AccidentDataBean.AccidentItemBean.AccidentCarBean> carAccidentDataBeanHashMap = new HashMap<>();
    /**
     * 参考AddAccidentActivity
     */
    private ArrayList<Car> addCars;
    /**
     * 参考AddAccidentActivity
     */
    private ListView addCarsListView;
    /**
     * 参考AddAccidentActivity
     */
    private SelectCarListAdapter selectCarListAdapter;
    /**
     * 参考AddAccidentActivity
     */
    private boolean finished = true;
    /**
     * 参考AddAccidentActivity
     */
    private boolean okClicked;
    /**
     * 参考AddAccidentActivity
     */
    private Button ok;
    /**
     * 参考AddAccidentActivity
     */
    private int accidentId;
    /**
     * 参考AddAccidentActivity
     */
    private TextView addCarTv;
    /**
     * 参考AddAccidentActivity
     */
    private boolean showDialog;
    /**
     * 参考AddAccidentActivity
     */


    private int id;
    private void initData(){
        Intent in = getIntent();
        id = in.getIntExtra("id",0);
        if(id!=0){
            showProgress();
            mPresenter.getDataFromServer(id+"");
        }else {
            itemBean = DataNoSerializable.getDataNoSerializable().getAccidentItemBean();
            accidentId = in.getIntExtra("accidentId",0);
            initDataToView();
        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, CheckService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        ok = (Button) findViewById(R.id.ok);
        ok.setVisibility(View.GONE);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okClicked = true;
                showProgress();
                if(finished){
                    saveData();
                }
            }
        });
        addCarTv = (TextView) findViewById(R.id.addCarTv);
        addCarTv.setVisibility(View.GONE);
        time = (TextView) findViewById(R.id.time);
        headerTv = (TextView) findViewById(R.id.headerTv);
        didian = (TextView) findViewById(R.id.position);
        images = (GridView) findViewById(R.id.images);
        imageAdapter = new ImageAdapter();
        images.setAdapter(imageAdapter);
        cars = (ListView) findViewById(R.id.carListView);
        carAdapter = new CarAdapter();
        cars.setAdapter(carAdapter);
        addCarsListView = (ListView) findViewById(R.id.addCarListView);
        selectCarListAdapter = new SelectCarListAdapter(this);
        addCarsListView.setAdapter(selectCarListAdapter);
        yuanyin = (TextView) findViewById(R.id.reson);
        back = (RelativeLayout) findViewById(R.id.listIconLay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                finish();
            }
        });
        headerTv.setText("事故详情");
        cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(itemBean.getAccidentCarBeanList().get(position).getCar().isHasData()){
                    Intent in = new Intent(AccidentListItemActivity.this, AccidentCarDetailActivity.class);
                    AccidentDataBean.AccidentItemBean.AccidentCarBean carBean = itemBean.getAccidentCarBeanList().get(position);
                    DataNoSerializable.getDataNoSerializable().setAccidentCarBean(carBean);
                    startActivity(in);
                }else {
                    showCarNoDataDialog();
                }

            }
        });
        addAccidentCar = (TextView) findViewById(R.id.addAccidentCar);
        addAccidentCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccidentDialogUtil.showSelectCarTypeDialog(AccidentListItemActivity.this,addCars);
            }
        });
        initData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }


    private void clearData(){
        DataNoSerializable.getDataNoSerializable().setCar(null);
        DataNoSerializable.getDataNoSerializable().setCars(null);
    }
    /**
     * 参考AddAccidentActivity
     */
    private IAccident iAccident;
    /**
     * 参考AddAccidentActivity
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iAccident = (IAccident) ((CheckService.LocalBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
/*            ok.setVisibility(View.VISIBLE);*/
            if(requestCode == 0){
                addCars = DataNoSerializable.getDataNoSerializable().getCars();
                if(addCars.size()==0){
                    addCarTv.setVisibility(View.GONE);
                    ok.setVisibility(View.GONE);
                }else {
                    addCarTv.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.VISIBLE);
                }
                selectCarListAdapter.setData(addCars);
                iAccident.startAccident(addCars,onAccidentMessageResponseListener);
            }else if(requestCode == 2){
                addCarTv.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                Car car = DataNoSerializable.getDataNoSerializable().getCar();
                boolean has = car.getBlueName()==null?false:true;
                if(!has){
                    car.setHasRead(true);
                }
                if(addCars==null){
                    addCars = new ArrayList<>();
                }
                addCars.add(car);
                selectCarListAdapter.setData(addCars);
                if(has){
                    iAccident.startAccident(addCars,onAccidentMessageResponseListener);
                }

            }
        }

    }
    /**
     * 参考AddAccidentActivity
     */
    private void showCarNoDataDialog(){
        new AlertDialog.Builder(this).setTitle("该车辆智能终端损坏或被破坏暂无车辆信息").setIcon(R.drawable.carnodataicon)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    /**
     * 参考AddAccidentActivity
     */
    private OnAccidentMessageResponseListener onAccidentMessageResponseListener = new OnAccidentMessageResponseListener() {
        @Override
        public void onResponse(final AccidentDataBean.AccidentItemBean.AccidentCarBean accidentDataBean) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    selectCarListAdapter.notifyDataSetChanged();
                    carAccidentDataBeanHashMap.put(accidentDataBean.getCar(),accidentDataBean);
                }
            });

        }

        @Override
        public void onFinished() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    selectCarListAdapter.notifyDataSetChanged();
                    finished = true;
                    if(okClicked){
                        saveData();
                    }
                }
            });

        }

        @Override
        public void onUnFinished() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finished = false;
                    selectCarListAdapter.notifyDataSetChanged();
                }
            });

        }
    };

    /**
     * 参考AddAccidentActivity
     */
    private void saveData(){
        if(addCars!=null && addCars.size()!=0){
            List<AccidentDataBean.AccidentItemBean.AccidentCarBean> accidentItemBeenList;
            if(itemBean.getAccidentCarBeanList()!=null){
                accidentItemBeenList = itemBean.getAccidentCarBeanList();
            }else {
                accidentItemBeenList = new ArrayList<>();
            }
            for (Car car:addCars) {
                if(carAccidentDataBeanHashMap.get(car)==null){
                    AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean = new AccidentDataBean.AccidentItemBean.AccidentCarBean();
                    accidentCarBean.setCar(car);
                    accidentItemBeenList.add(accidentCarBean);
                }else {
                    accidentItemBeenList.add(carAccidentDataBeanHashMap.get(car));
                }
            }
            itemBean.setAccidentCarBeanList(accidentItemBeenList);
            //Accident accident = new Accident();
            Accident accident = null;
            if(accidentId!=0){
                accident = App.getApp().getDaoSession().getAccidentDao().load((long)accidentId);
            }else {
                accident = new Accident();
                accident.setIsUpdate(true);
                itemBean.setId(id);
            }
            Gson gson = new Gson();
            accident.setHasUpload(false);
            accident.setData(gson.toJson(itemBean));
            App.getApp().getDaoSession().getAccidentDao().insertOrReplace(accident);
        }
        dismissProgress();
        showSuccessDialog();
    }
    /**
     * 参考AddAccidentActivity
     */
    public void showSuccessDialog(){
        showDialog = true;
        new AlertDialog.Builder(this).setTitle("消息已上传成功").setIcon(R.drawable.carnodataicon).setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBack();
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        if(!showDialog){
            clearData();
            finish();
        }
    }
    /**
     * 参考AddAccidentActivity
     */
    private void onBack(){
        if(okClicked){
            Intent intent = new Intent();
            DataNoSerializable.getDataNoSerializable().setUpdateAccidentItemBean(itemBean);
            setResult(RESULT_OK,intent);
        }
        clearData();
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_accident_list_item;
    }

    @Override
    public void initView() {

    }

    private void initDataToView(){
        time.setText(itemBean.getTime());
        didian.setText(itemBean.getWeizhi());
        yuanyin.setText(itemBean.getYuanyin());
        imageAdapter.setData(itemBean.getImages());
        carAdapter.setData(itemBean.getAccidentCarBeanList());
    }

    @Override
    public void onError() {
        dismissProgress();
        ToastUtil.show(this,"网络连接失败");
    }

    @Override
    public void onNoData() {
        dismissProgress();
        ToastUtil.show(this,"暂无数据");
    }

    @Override
    public void showData(AccidentDataBean.AccidentItemBean data) {
        dismissProgress();
        itemBean = data;
        initDataToView();
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }
    /**
     * 参考AddAccidentActivity
     */
    public class ImageAdapter extends BaseAdapter {


        public void setData(List<AccidentDataBean.AccidentItemBean.Image> data) {
            this.images = data;
            for (AccidentDataBean.AccidentItemBean.Image image:images){
                imageStrs.add(image.getServerPath());
            }
            notifyDataSetChanged();
        }
        private List<AccidentDataBean.AccidentItemBean.Image> images = new ArrayList<>();
        private ArrayList<String> imageStrs = new ArrayList<>();
        @Override
        public int getCount() {
            if(images!=null){
                return images.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view1;
            ViewHolder holder;
            if (null == convertView) {
                // 如果view为空，则表示第一次显示该条目，需要创建一个view
                view1 = View.inflate(AccidentListItemActivity.this, R.layout.accidentimageviewitem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.showImg = (ImageView) view1.findViewById(R.id.showimg);
                //holder.delImg = (ImageView) view1.findViewById(R.id.imgdelete);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = convertView;
                holder = (ViewHolder) view1.getTag();
            }
            if(images.get(position).getMyPath()!=null){
                Glide.with(AccidentListItemActivity.this).load(images.get(position).getMyPath()).bitmapTransform(new RoundedCornersTransformation(AccidentListItemActivity.this,5, 0,
                        RoundedCornersTransformation.CornerType.ALL)).into(holder.showImg);
            }else {
                Glide.with(AccidentListItemActivity.this).load(images.get(position).getServerPath()).bitmapTransform(new RoundedCornersTransformation(AccidentListItemActivity.this,5, 0,
                        RoundedCornersTransformation.CornerType.ALL)).into(holder.showImg);
            }

            holder.showImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AccidentListItemActivity.this,BigPictureActivity.class);
                    intent.putExtra("type","2");
                    intent.putStringArrayListExtra("pics", imageStrs);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });
            return view1;
        }

        private class ViewHolder {
            public ImageView showImg;
        }

    }
    /**
     * 参考AddAccidentActivity
     */
    public class CarAdapter extends BaseAdapter {


        public void setData(List<AccidentDataBean.AccidentItemBean.AccidentCarBean> data) {
            this.data = data;
            notifyDataSetChanged();
        }
        private List<AccidentDataBean.AccidentItemBean.AccidentCarBean> data = new ArrayList<>();
        @Override
        public int getCount() {
            if(data!=null){
                return data.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view1;
            ViewHolder holder;
            if (null == convertView) {
                // 如果view为空，则表示第一次显示该条目，需要创建一个view
                view1 = View.inflate(AccidentListItemActivity.this, R.layout.accidentcardetailitem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.has = (ImageView) view1.findViewById(R.id.has);
                holder.carNum = (TextView) view1.findViewById(R.id.carNum);
                holder.type = (TextView) view1.findViewById(R.id.type);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = convertView;
                holder = (ViewHolder) view1.getTag();
            }
            if(data.get(position).getCar().isHasData()){
                holder.has.setImageResource(R.drawable.zhengchang_xiugai);
                holder.type.setText(data.get(position).getType());

            }else {
                holder.has.setImageResource(R.drawable.yichang_xiugai);
            }
            holder.carNum.setText(data.get(position).getCar().getCarNum());
            return view1;
        }

        private class ViewHolder {
            public ImageView has;
            public TextView carNum;
            public TextView type;
        }

    }

}
