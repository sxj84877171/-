package com.soarsky.car.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.soarsky.car.R;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/27
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：出险报警提示dialog
 * 该类为  自定义dialog
 */


public class PointMessageDialog extends Dialog {

    /**
     *
     */
    private TextView message1;
    private TextView message2;
    private TextView message3;

    private OnBtnClickLisenter onBtnClickLisenter;

    private String strMessage1;
    private String strMessage2;
    private String strMessage3;

    public PointMessageDialog(Context context, String strmessge1, String strmessge2, String strmessge3) {
        super(context, R.style.diolog_violation_address);
        this.strMessage1 = strmessge1;
        this.strMessage2 = strmessge2;
        this.strMessage3 = strmessge3;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_accident_upload_success);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据

    }

    private void initView() {
        message1 = (TextView) findViewById(R.id.message1);
        message2 = (TextView) findViewById(R.id.message2);
        message3 = (TextView) findViewById(R.id.message3);

        if (!TextUtils.isEmpty(strMessage1)) {
            message1.setText(strMessage1);
        }

        if (!TextUtils.isEmpty(strMessage2)) {

            message2.setText(strMessage3);
        } else {
            message2.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(strMessage3)) {
            message3.setText(strMessage3);
        } else {
            message3.setVisibility(View.GONE);
        }
        findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClickLisenter.onClick();
            }
        });
    }


    /**
     * 设置点击时间监听
     * @param onBtnClickLisenter
     */
    public void setOnBtnClickLisenter(OnBtnClickLisenter onBtnClickLisenter) {
        this.onBtnClickLisenter = onBtnClickLisenter;
    }

    /**
     * 点击回调接口
     */
    public interface OnBtnClickLisenter {

        void onClick();
    }

}
