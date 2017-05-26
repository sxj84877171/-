package com.soarsky.car.ui.roadside.list.order;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.soarsky.car.R;
import com.soarsky.car.ui.roadside.RoadSideDialogListener;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：取消订单的dialog
 * 该类为 RoadSideListOrderDialog
 */


public class RoadSideListOrderDialog extends Dialog{
    /**
     * 上下文本
     */
    private Context context;

    /**
     * 其构造函数
     * @param context
     * @param themeResId
     */
    public RoadSideListOrderDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder implements View.OnClickListener{

        private Context context;

        private Button orderCancleBtn;

        private Button orderSureBtn;

        private RoadSideListOrderListener listener;

        View layout;

        public Builder(Context context,RoadSideListOrderListener listener){
            this.context = context;
            this.listener = listener;
        }

        /**
         * 创建一个dialog
         * @return
         */
        public RoadSideListOrderDialog create(){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RoadSideListOrderDialog dialog = new RoadSideListOrderDialog(context, R.style.diolog_violation_address);

            layout = inflater.inflate(R.layout.dialog_road_side_order,null);
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);

            initView();

            return dialog;
        }

        /**
         * 初始化控件
         */
        public void initView(){

            orderCancleBtn = (Button) layout.findViewById(R.id.orderCancleBtn);
            orderCancleBtn.setOnClickListener(this);

            orderSureBtn = (Button) layout.findViewById(R.id.orderSureBtn);
            orderSureBtn.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.orderCancleBtn:
                    listener.selectedOnClick(0);
                    break;
                case R.id.orderSureBtn:
                    listener.selectedOnClick(1);
                    break;
            }
        }
    }

}
