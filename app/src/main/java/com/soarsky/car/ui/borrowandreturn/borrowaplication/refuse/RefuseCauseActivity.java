package com.soarsky.car.ui.borrowandreturn.borrowaplication.refuse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ModifyStatusInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationMedol;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationPresent;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationView;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordActivity;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  填写借车拒绝原因页面<br>
 */

public class RefuseCauseActivity extends BaseActivity<BorrowAplicationPresent,BorrowAplicationMedol> implements BorrowAplicationView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 下拉展开拒绝原因列表
     */
    private ImageView pull;
    /**
     * 是否展开列表，默认为展开
     */
    private Boolean isPull = false;
    /**
     * 原因列表
     */
    private ListView listView;
    /**
     * 原因列表数据适配器类
     */
    private RefuseCauseAdapter adapter;
    /**
     * 确定拒绝按钮
     */
    private Button bt_sure;
    /**
     * 拒绝理由
     */
    private TextView textCause;
    /**
     * 编辑其他拒绝理由
     */
    private EditText et_other_cause;
    /**
     * 拒绝理由
     */
    private String remark;
    /**
     * 原因列表的布局
     */
    private RelativeLayout rl_cause;

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

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        pull = (ImageView) findViewById(R.id.pull);
        pull.setOnClickListener(this);

        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(this);

        textCause = (TextView) findViewById(R.id.textCause);
        et_other_cause = (EditText) findViewById(R.id.et_other_cause);

        rl_cause = (RelativeLayout) findViewById(R.id.rl_cause);
        rl_cause.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new RefuseCauseAdapter(RefuseCauseActivity.this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pull.setImageResource(R.mipmap.down_pull);
                listView.setVisibility(View.GONE);
                isPull = false;
                TextView textView = (TextView) view.findViewById(R.id.textCause);
                textCause.setText(textView.getText().toString());
                ToastUtil.show(RefuseCauseActivity.this,textView.getText().toString());


            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 点击事件触发的方法
     * @param view 点击的控件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.rl_cause:
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
     * @param modifyStatusParm  状态参数信息
     */
    @Override
    public void showsSuccess(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {
        finish();
        startActivity(new Intent(RefuseCauseActivity.this, BorrowRecordActivity.class));
    }
    /**
     * 拒绝失败后触发的方法
     * @param modifyStatusParm
     */
    @Override
    public void showsFail(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {
        ToastUtil.show(RefuseCauseActivity.this,modifyStatusParm.getMessage());

    }

    @Override
    public void agree(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {

    }

    @Override
    public void showSuccess(ResponseDataBean<DetailsInfo> detailParm) {

    }

    @Override
    public void showFail(ResponseDataBean<DetailsInfo> detailParm) {

    }

    @Override
    public void showError(Throwable e) {

    }
}
