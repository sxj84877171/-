package com.soarsky.policeclient.ui.violation;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.soarsky.policeclient.R;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单地址选择对话框<br>
 */

public class ViolationAddressDialog extends Dialog{

    private Context context;
    public ViolationAddressDialog(Context context) {
        super(context);
    }

    public ViolationAddressDialog(Context context, int themeResId) {

        super(context, themeResId);
    }

    /**
     * 构建器
     */
    public static class Builder implements View.OnClickListener {
        private Context context;
        private TextView cofoirmTv;
        private EditText addressEt;
        private String address ;
        private ViolationAddressDialogListener violationAddressDiolagListener;
        View layout;


        public Builder(Context context, ViolationAddressDialogListener violationAddressDiolagListener) {
            this.context = context;
            this.violationAddressDiolagListener = violationAddressDiolagListener;
        }


        public ViolationAddressDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ViolationAddressDialog dialog = new ViolationAddressDialog(context, R.style.diolog_violation_address);
            layout = inflater.inflate(R.layout.dialog_violation_address, null);
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            initView();
            return dialog;

        }
        /**
         * 初始化布局
         */

        private void initView() {
            cofoirmTv = (TextView) layout.findViewById(R.id.dialog_violation_confrm);
            addressEt = (EditText) layout.findViewById(R.id.dialog_violation_address);
            // add by Elvis  如果已经初始化地址，则自动填充当前地址
            if(address != null){
                addressEt.setText(address);
            }
            cofoirmTv.setOnClickListener(this);
        }

        /**
         * add by Elvis
         * 添加初始化地址函数
         * @param address
         */
        public void setAddress(String address){
            this.address = address ;
            if(addressEt != null){
                addressEt.setText(address);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_violation_confrm:
                    String address = addressEt.getText().toString();

                    violationAddressDiolagListener.clickConfirm(address);
                    break;


            }
        }
    }


}
