package com.soarsky.car.ui.illegal.park;

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
import com.soarsky.car.R;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.ViolationDealIlist;
import com.soarsky.car.server.bluetooth.BlueToothScan;
import com.soarsky.car.server.wifi.Scan;
import com.soarsky.car.ui.illegal.advise.IllegalAdviseActivity;
import com.soarsky.car.ui.illegal.fragment.IllegalUpdateCallBack;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/15
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class IllegalParkAdapter extends BaseAdapter{

    private Context context;

    private List<ViolationDealIlist> list;

    private LayoutInflater inflater;

    private IllegalUpdateCallBack callBack;

    public IllegalParkAdapter(Context context,List<ViolationDealIlist> list ,IllegalUpdateCallBack callBack){

        this.context = context;
        this.list = list;
        this.callBack = callBack;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.illegal_park_item,null);
            holder.parkAddresstv = (TextView)convertView.findViewById(R.id.parkAddresstv);
            holder.parkApplybtn = (Button) convertView.findViewById(R.id.parkApplybtn);
            holder.parkReasontv = (TextView) convertView.findViewById(R.id.parkReasontv);
            holder.parkRemindTimetv = (TextView) convertView.findViewById(R.id.parkRemindTimetv);
            holder.parkTimetv = (TextView) convertView.findViewById(R.id.parkTimetv);

            convertView.setTag(holder);

        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        setData(holder,position);

        return convertView;
    }


    public void setData(ViewHolder holder,final int position){

        holder.parkTimetv.setText(""+list.get(position).getStime()==null?"":roundDate(list.get(position).getStime()));
        holder.parkReasontv.setText(""+list.get(position).getInf()==null?"":list.get(position).getInf());
        holder.parkAddresstv.setText(""+list.get(position).getAddress()==null?"":list.get(position).getAddress());
        holder.parkRemindTimetv.setText("约9分钟");

        holder.parkApplybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

    }


    private String roundDate(String date){
        if(date.trim() .endsWith(":")){
            if(date.length() >= 10){
                date = date+"00";
            }
        }
        return  date ;
    }


    public class ViewHolder{

        TextView parkTimetv;

        TextView parkAddresstv;

        TextView parkReasontv;

        TextView parkRemindTimetv;

        Button parkApplybtn;

    }
}
