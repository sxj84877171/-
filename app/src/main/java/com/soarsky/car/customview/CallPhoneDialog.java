package com.soarsky.car.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.soarsky.car.R;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：拨打报警电话弹框
 * 该类为 自定义dialog
 */


public class CallPhoneDialog extends Dialog implements View.OnClickListener {


    private Context context;

    /**
     * 取消按钮
     */
    private Button cancle;
    /**
     * 确认按钮
     */
    private Button ok;

    public CallPhoneDialog(Context context) {
        super(context, R.style.diolog_violation_address);
        this.context=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_callphone);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据

    }

    /**
     * 初始化
     */
    private void initView() {
        cancle = (Button) findViewById(R.id.cancle);
        ok = (Button) findViewById(R.id.ok);
        cancle.setOnClickListener(this);
        ok.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                Intent intent = new Intent();

            //系统默认的action，用来打开默认的电话界面
                intent.setAction(Intent.ACTION_CALL);

            //需要拨打的号码
                intent.setData(Uri.parse("tel:110"));
                context.startActivity(intent);
                dismiss();
                break;
            case R.id.cancle:
                this.dismiss();
                break;
        }
    }
}
