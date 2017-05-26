package com.soarsky.car.ui.borrowandreturn.borrowaplication.aplicationrefuse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.borrowandreturn.borrow.IWantBorrowActivity;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车请求被拒绝
 */

public class AplicationRefuseActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private ImageView backView;
    /**
     * 拒绝原因
     */
    private TextView text_cause;
    /**
     * 再次借车按钮
     */
    private Button bt_borrow_again;
    /**
     * 确定按钮
     */
    private Button bt_sure;

    /**
     * 返回要展示的布局文件
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.aplication_refuse;
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

        text_cause = (TextView) findViewById(R.id.text_cause);

        bt_borrow_again = (Button) findViewById(R.id.bt_borrow_again);
        bt_borrow_again.setOnClickListener(this);

        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text_cause.setText(bundle.get("remark")==null? "":bundle.get("remark").toString());
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
            case R.id.bt_borrow_again:
                finish();
                startActivity(new Intent(AplicationRefuseActivity.this, IWantBorrowActivity.class));
                break;
            case R.id.bt_sure:
                finish();
                break;
        }
    }
}
