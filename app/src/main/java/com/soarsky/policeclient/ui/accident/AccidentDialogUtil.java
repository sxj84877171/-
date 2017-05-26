package com.soarsky.policeclient.ui.accident;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.ui.accident.add.AddAccidentActivity;
import com.soarsky.policeclient.ui.accident.selectCar.EnterCarNumActivity;
import com.soarsky.policeclient.ui.accident.selectCar.SelectCarActivity;

import java.util.ArrayList;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 选择车辆的对话框<br>
 */
public class AccidentDialogUtil {
    /**
     * 显示选择车辆的对话框
     * @param context 上下文
     * @param selectCar 已经选择的车辆列表
     */
    public static void showSelectCarTypeDialog(final Activity context, final ArrayList<Car> selectCar){
        final AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.select_car_type_dialog, null);
        mDialogBuilder.setView(root);
        final AlertDialog alertDialog = mDialogBuilder.create();
        root.findViewById(R.id.shoudong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(context,EnterCarNumActivity.class);
                context.startActivityForResult(intent,2);
            }
        });
        root.findViewById(R.id.fujin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(context,SelectCarActivity.class);
                context.startActivityForResult(intent,0);
            }
        });
        final Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        dialogWindow.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialogWindow.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= 0x00001000;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                dialogWindow.getDecorView().setSystemUiVisibility(uiOptions);
            }
        });

        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        alertDialog.show();


    }


}
