package com.soarsky.car.ui.danger.start;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.customview.CallPhoneDialog;
import com.soarsky.car.ui.danger.compen.CompensateActivity;
import com.soarsky.car.ui.danger.responsibility.ResponsibilityListActivty;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class DangerStartActivity extends BaseActivity<DangerStartPresent,DangerStartModel> implements DangerStartView,View.OnClickListener{

    private TextView messageCount;
    private TextView titleTv;
     /**
     * 返回按钮
     */
    private ImageView backView;

    private LinearLayout backLay;
    private TextView bar_num;

    private App app;

    private static final String TAG ="DangerStartActivity";


    private CallPhoneDialog callPhoneDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dangerpolice;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);
        findViewById(R.id.start_compensate).setOnClickListener(this);
        findViewById(R.id.start_responsibility).setOnClickListener(this);
        findViewById(R.id.start_phone).setOnClickListener(this);
        messageCount= (TextView) findViewById(R.id.bar_num);
        titleTv= (TextView) findViewById(R.id.topicTv);
        titleTv.setText(R.string.preservearam);

 		bar_num = (TextView) findViewById(R.id.bar_num);
        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getUnreadFastCount(app.getCommonParam().getCarNum()==null?"":app.getCommonParam().getCarNum());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void getUnreadFastCountSuccess(DangerStartParam dangerStartParam) {

        if(dangerStartParam != null){
            if("0".equals(dangerStartParam.getStatus())){

                if(dangerStartParam.getData() == 0){
                    bar_num.setVisibility(View.GONE);
                }else {
                    bar_num.setVisibility(View.VISIBLE);
                    bar_num.setText(""+dangerStartParam.getData());
                }

            }else {
                ToastUtil.show(this,dangerStartParam.getMessage());
            }
        }
    }

    @Override
    public void getUnreadFastCountFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_compensate:
                startActivity(new Intent(this, CompensateActivity.class));
            break;
            case R.id.start_responsibility:
                startActivity(new Intent(this, ResponsibilityListActivty.class));
                break;
            case R.id.start_phone:
                if(callPhoneDialog==null){
                    callPhoneDialog=new CallPhoneDialog(this);
                }
                callPhoneDialog.show();

                break;
            case R.id.backView:
            case R.id.backLay:
                finish();
                break;
        }
    }
}
