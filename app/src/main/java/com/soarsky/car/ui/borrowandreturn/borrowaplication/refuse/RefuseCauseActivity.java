package com.soarsky.car.ui.borrowandreturn.borrowaplication.refuse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.borrowandreturn.ModifyStatusParm;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationMedol;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationPresent;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationView;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordActivity;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  填写借车拒绝原因页面
 */

public class RefuseCauseActivity extends BaseActivity<BorrowAplicationPresent,BorrowAplicationMedol> implements BorrowAplicationView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private ImageView backView;
    private ImageView pull;
    private Boolean isPull = false;
    private ListView listView;
    private RefuseCauseAdapter adapter;
    private Button bt_sure;
    private TextView textCause;
    private EditText et_other_cause;
    private String remark;
    private String carNum;
    private String name;

    /**
     * 返回要展示的布局文件
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_refuse_cause;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.borrow_aplication);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        pull = (ImageView) findViewById(R.id.pull);
        pull.setOnClickListener(this);

        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(this);

        textCause = (TextView) findViewById(R.id.textCause);
        et_other_cause = (EditText) findViewById(R.id.et_other_cause);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new RefuseCauseAdapter(RefuseCauseActivity.this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 点击事件触发的方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
                finish();
                break;
            case R.id.pull:
                if(isPull == false){
                    pull.setImageResource(R.mipmap.up_pull);
                    listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(adapter);
                    isPull = true;
                }else{
                    pull.setImageResource(R.mipmap.down_pull);
                    listView.setVisibility(View.GONE);
                    isPull = false;
                }
                break;
            case R.id.bt_sure:
                remark = textCause.getText().toString()+et_other_cause.getText().toString();

                mPresenter.refuse(remark);
                break;
        }
    }

    /**
     * 系统的onStart方法
     */
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mModel.setBid(bundle.getInt("id"));
        mModel.setCarnum(bundle.getString("Num"));
        mModel.setUsername(bundle.getString("name"));

    }

    @Override
    public void showError() {
        ToastUtil.show(RefuseCauseActivity.this,R.string.toast_error);
    }

    /**
     * 拒绝成功后触发的方法
     * @param modifyStatusParm
     */
    @Override
    public void showSuccess(ModifyStatusParm modifyStatusParm) {
        finish();
        startActivity(new Intent(RefuseCauseActivity.this, BorrowRecordActivity.class));
    }
    /**
     * 拒绝失败后触发的方法
     * @param modifyStatusParm
     */
    @Override
    public void showFail(ModifyStatusParm modifyStatusParm) {
        ToastUtil.show(RefuseCauseActivity.this,modifyStatusParm.getMessage());

    }

    @Override
    public void agree(ModifyStatusParm modifyStatusParm) {

    }

    @Override
    public void showSuccess(DetailsParm detailParm) {

    }

    @Override
    public void showFail(DetailsParm detailParm) {

    }

    @Override
    public void showError(Throwable e) {

    }
}
