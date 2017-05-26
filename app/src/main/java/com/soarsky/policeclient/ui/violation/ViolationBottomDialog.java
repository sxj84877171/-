package com.soarsky.policeclient.ui.violation;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
 * 该类为 开电子罚单车牌号选择对话框<br>
 */

public class ViolationBottomDialog extends Dialog {

    public ViolationBottomDialog(Context context) {
        super(context);
    }

    public ViolationBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }



    public static class Builder implements View.OnClickListener {
        private Context context;
        private TextView cofoirmTv;
        private ListView listView;
        private TextView selectCarTv;
        private int dialogWidth;
        private int dialogHeight;
        private ViolationBottomDialogListener listener;
        View layout;
        private List<String> list;
        private int type;
        private String selectedItem;

        private View.OnClickListener onClickListener;

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public Builder(Context context, int dialogWidth, int dialogHeight, ViolationBottomDialogListener listener, List<String> list, int type) {
            this.context = context;
            this.dialogWidth = dialogWidth;
            this.dialogHeight = dialogHeight;
            this.listener = listener;
            this.list = list;
            this.type = type;
        }




        public ViolationAddressDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ViolationAddressDialog dialog = new ViolationAddressDialog(context, R.style.diolog_violation_address);
            layout = inflater.inflate(R.layout.dialog_violation_buttom, null);

            ImageView imageView = (ImageView) layout.findViewById(R.id.dialog_violation_arrowl);
            if(type==1){
                imageView.setVisibility(View.VISIBLE);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener!=null){
                        Builder.this.onClickListener.onClick(v);
                    }

                }
            });
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);

            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);

            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = dialogWidth;
            lp.height = dialogHeight;
            dialogWindow.setAttributes(lp);
            initView();
            return dialog;

        }
        private ViolationCarAdapter violationCarAdapter;

        public void setData(List<String> list){
            violationCarAdapter.setList(list);
        }

        private void initView() {
            cofoirmTv = (TextView) layout.findViewById(R.id.dialog_violationbottom_confirm);
            cofoirmTv.setOnClickListener(this);
            listView = (ListView) layout.findViewById(R.id.dialog_violationbottom_listview);
            selectCarTv = (TextView) layout.findViewById(R.id.selectCarTv);
            violationCarAdapter = new ViolationCarAdapter(context,list,type);
            listView.setAdapter(violationCarAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LogUtil.e("weikai","sss");
                    if(type==1){
                        LogUtil.e("we","1");
                        selectCarTv.setText(CarUtil.fromSsidToCarNum(list.get(position).toString()));
                    }else {
                        LogUtil.e("we","2");
                        selectCarTv.setText(list.get(position).toString());
                    }
                    selectedItem = list.get(position).toString();
                    ColorStateList csl=(ColorStateList)context.getResources().getColorStateList(R.color.dialog_b_tv);
                    selectCarTv.setTextColor(csl);
                }
            });

        }



        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.dialog_violationbottom_confirm:

                    if(context.getString(R.string.dialog_b_chice).equals(selectedItem) || selectedItem==null) {
                        ToastUtil.show(context,context.getString(R.string.dialog_b_chice));
                    }else {
                        listener.confirmClick(selectedItem);
                    }
                    break;

            }
        }
    }
}
