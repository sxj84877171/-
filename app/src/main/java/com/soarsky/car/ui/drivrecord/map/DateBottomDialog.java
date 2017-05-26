package com.soarsky.car.ui.drivrecord.map;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.soarsky.car.R;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DateBottomDialog extends Dialog {
    public DateBottomDialog(Context context) {
        super(context);
    }

    public DateBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }



    public static class Builder implements View.OnClickListener {
        private Context context;
        private TextView cofoirmTv;
        private int dialogWidth;
        private int dialogHeight;
        private DateBottomDialogListener listener;
        View layout;



        public Builder(Context context, int dialogWidth, int dialogHeight, DateBottomDialogListener listener) {
            this.context = context;
            this.dialogWidth = dialogWidth;
            this.dialogHeight = dialogHeight;
            this.listener = listener;
        }




        public DateBottomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DateBottomDialog dialog = new DateBottomDialog(context, R.style.diolog_violation_address);
            layout = inflater.inflate(R.layout.dialog_violation_buttom, null);
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT));
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


        private void initView() {
            cofoirmTv = (TextView) layout.findViewById(R.id.dialog_violationbottom_confirm);
            cofoirmTv.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.dialog_violationbottom_confirm:


                    break;

            }
        }
    }
}
