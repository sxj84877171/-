package com.soarsky.car.ui.illegal.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.ViolationDealIlist;
import com.soarsky.car.server.bluetooth.BlueToothScan;
import com.soarsky.car.server.wifi.Scan;
import com.soarsky.car.ui.illegal.IllegalManageActivity;
import com.soarsky.car.ui.illegal.advise.IllegalAdviseActivity;
import com.soarsky.car.ui.illegal.electronic.IllegalElectronicActivity;
import com.soarsky.car.ui.illegal.fragment.IllegalUpdateCallBack;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章列表的适配<br>
 * 该类为 IllegalListAdapter<br>
 */


public class IllegalListAdapter extends BaseAdapter{
    /**
     * 上下文
     */
    private Context context;
    /**
     * 数据源
     */
    private List<ViolationDealIlist> list;
    /**
     * inflater
     */
    private LayoutInflater inflater;

    private IllegalUpdateCallBack callBack;

    /**
     * 其构造函数
     * @param context 文本
     * @param list 集合
     */
    public IllegalListAdapter(Context context,List<ViolationDealIlist> list,IllegalUpdateCallBack callBack){
        super();
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        IllegalListAdapter.ViewHolder holder = null;
        if(view == null){

            holder = new IllegalListAdapter.ViewHolder();

            view = inflater.inflate(R.layout.violation_deal_item_list3, null);

            holder.dealAddresstv = (TextView) view.findViewById(R.id.dealAddresstv);
            holder.dealCenttv = (TextView) view.findViewById(R.id.dealCenttv);
            holder.dealMoneytv = (TextView) view.findViewById(R.id.dealMoneytv);
            holder.dealReasontv = (TextView) view.findViewById(R.id.dealReasontv);
            holder.dealTimetv = (TextView) view.findViewById(R.id.dealTimetv);
            holder.dealbtn = (Button) view.findViewById(R.id.dealbtn);

            view.setTag(holder);

        }else {

            holder = (IllegalListAdapter.ViewHolder) view.getTag();
        }

        setData(holder,i);

        return view;
    }

    private String roundDate(String date){
        if(date.trim() .endsWith(":")){
            if(date.length() >= 10){
                date = date+"00";
            }
        }
        return  date ;
    }

    /**
     * 数据源的实例化
     * @param holder  ViewHolder
     * @param position 第几个
     */
    public void setData(IllegalListAdapter.ViewHolder holder, final int position){

        holder.dealTimetv.setText(""+list.get(position).getStime()==null?"":roundDate(list.get(position).getStime()));
        holder.dealAddresstv.setText(""+list.get(position).getAddress()==null?"":list.get(position).getAddress());
        holder.dealMoneytv.setText(""+list.get(position).getMonery()== null?"":list.get(position).getMonery());
        holder.dealCenttv.setText(""+list.get(position).getCent()== null?"":list.get(position).getCent());
        holder.dealReasontv.setText(""+list.get(position).getInf()==null?"":list.get(position).getInf());

        if(Constants.NO_REVOKE.equals(list.get(position).getSign())){
            if (Constants.REVOKEING.equals(list.get(position).getStatus())){
//                holder.dealbtn.setBackgroundColor(context.getResources().getColor(R.color.grey));
                holder.dealbtn.setText(R.string.revoking);
            }else if(Constants.REVOKED.equals(list.get(position).getStatus())){
//                holder.dealbtn.setBackgroundColor(context.getResources().getColor(R.color.grey));
                holder.dealbtn.setText(R.string.revoked);
            }else {
//                holder.dealbtn.setBackgroundColor(context.getResources().getColor(R.color.new_bg_color));
                holder.dealbtn.setText(R.string.pay_money1);
            }
        }else if(Constants.CAN_REVOKE.equals(list.get(position).getSign())){
//            holder.dealbtn.setBackgroundColor(context.getResources().getColor(R.color.new_bg_color));
            holder.dealbtn.setText(R.string.revoke_application);
        }

        holder.dealbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Constants.NO_REVOKE.equals(list.get(position).getSign())) {

                    if(Constants.REVOKEING.equals(list.get(position).getStatus())||"6".equals(list.get(position).getStatus())){

                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setMessage(context.getString(R.string.illegal_tip));
                        builder.setTitle(context.getString(R.string.uncaughttip));
                        builder.setPositiveButton(context.getString(R.string.uncaughtsure), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent in = new Intent();
                                in.setClass(context, IllegalElectronicActivity.class);
                                IllegalInfo info = new IllegalInfo();
                                info.setDriverNo(list.get(position).getDrivers() == null ? "" : list.get(position).getDrivers());
                                info.setAddress(list.get(position).getAddress() == null ? "" : list.get(position).getAddress());
                                info.setCent(list.get(position).getCent() == null ? "" : list.get(position).getCent());
                                info.setFee(list.get(position).getMonery() == null ? "" : list.get(position).getMonery());
                                info.setIllegalReason(list.get(position).getInf() == null ? "" : list.get(position).getInf());
                                info.setTime(list.get(position).getStime() == null ? "" : list.get(position).getStime());
                                info.setCarNum(list.get(position).getCarnum() == null ? "" : list.get(position).getCarnum());
                                info.setPlateType(list.get(position).getPlateType() == null ? "" : list.get(position).getPlateType());
                                info.setDocumentNo(list.get(position).getDocNumber() == null ? "" : list.get(position).getDocNumber());
                                in.putExtra("flag", 1);
                                in.putExtra("info", info);
                                context.startActivity(in);

                            }
                        });
                        builder.setNegativeButton(context.getString(R.string.uncaughtcancle), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                }else if(Constants.CAN_REVOKE.equals(list.get(position).getSign())) {
                    //申请撤销
                    List<String> car_list = new ArrayList<String>();
                    List<BluetoothIBridgeDevice> devices = BlueToothScan.getInstance(context).getCarDeviceList();

                    List<String> cars_list =  new ArrayList<String>();

                    List<Car> wificarlist = Scan.getInstance(context).getCarList();
                    if(wificarlist != null) {
                        for (Car car : wificarlist) {
                            cars_list.add(car.getCarNum());
                        }
                    }

                    if(devices != null) {
                        for (BluetoothIBridgeDevice device : devices) {
                            car_list.add(device.getDeviceName().substring(3, 10));
                            Log.d("TAG", "carnum==" + device.getDeviceName().substring(3, 10));
                        }
                    }
                    Log.d("TAG", "bean.name==" + list.get(position).getCarnum());

                    String stime = list.get(position).getStime();
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long disTime = TimeUtils.getIntervalTime(simpleDateFormat.format(date), stime, ConstUtils.MIN);

                    if (disTime < 10) {
                        if (car_list.contains(list.get(position).getCarnum())||cars_list.contains(list.get(position).getCarnum())) {
                            Intent intent = new Intent(context, IllegalAdviseActivity.class);
                            intent.putExtra("carnum", list.get(position).getCarnum());
                            intent.putExtra("time", list.get(position).getStime());
                            intent.putExtra("ptype", list.get(position).getPlateType());
                            context.startActivity(intent);
                        } else {
                            new android.support.v7.app.AlertDialog.Builder(context).setTitle(R.string.move_car_tip)
                                    .setPositiveButton(R.string.dialog_text, new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 点击“确认”后的操作

                                        }
                                    })
                                    .show();
                        }
                    }else {
//                        Intent i = new Intent(context,IllegalManageActivity.class);
//                        context.startActivity(i);
                        new android.support.v7.app.AlertDialog.Builder(context).setTitle(R.string.illegal_out_time)
                                .setPositiveButton(R.string.dialog_text, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 点击“确认”后的操作
                                        callBack.illegalUpdateCallBack(""+list.get(position).getId());

                                    }
                                }).setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“取消”后的操作

                            }
                        })
                                .show();
                    }
                }
            }
        });
    }


    public class ViewHolder{
        /**
         * 时间
         */
        TextView dealTimetv;
        /**
         * 地址
         */
        TextView dealAddresstv;
        /**
         * 原因
         */
        TextView dealReasontv;
        /**
         * 分
         */
        TextView dealCenttv;
        /**
         * 钱
         */
        TextView dealMoneytv;
        /**
         * 处理
         */
        Button dealbtn;

    }
}
