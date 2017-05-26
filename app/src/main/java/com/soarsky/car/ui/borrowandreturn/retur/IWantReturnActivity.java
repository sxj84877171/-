package com.soarsky.car.ui.borrowandreturn.retur;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ReturnCarInfo;
import com.soarsky.car.ui.borrowandreturn.retur.returnsuccess.ReturnSuccessActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  我要还车页面<br>
 */

public class IWantReturnActivity extends BaseActivity<IWantReturnPresent,IWantReturnModel> implements IWantReturnView, View.OnClickListener {
    /**
     * 借入的车辆列表
     */
    private ListView lv_return;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private ImageView backView;
    /**
     * 借入的车辆列表数据适配器
     */
    private ReturnAdapter returnAdapter;
    /**
     * 还车按钮
     */
    private Button bt_return;
    /**
     * 获取全局变量的实例
     */
    private App app;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 无可还车辆时显示的布局
     */
    private RelativeLayout rl_no_car;
    /**
     * 有可还车辆时显示的布局
     */
    private LinearLayout ll_have_car;

    @Override
    public int getLayoutId() {
        return R.layout.i_want_return;
    }

    @Override
    public void initView() {
        app = (App) getApplication();

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.i_return_button);
        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        bt_return = (Button) findViewById(R.id.bt_return);
        bt_return.setOnClickListener(this);

        rl_no_car = (RelativeLayout) findViewById(R.id.rl_no_car);
        ll_have_car = (LinearLayout) findViewById(R.id.ll_have_car);

        lv_return = (ListView) findViewById(R.id.lv_return);
        returnAdapter = new ReturnAdapter(IWantReturnActivity.this);
        lv_return.setAdapter(returnAdapter);
        lv_return.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                returnAdapter.setSelectedPosition(position);
                returnAdapter.notifyDataSetInvalidated();

            }
        });


    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.record(app.getCommonParam().getOwerPhone(),app.getCommonParam().getUserName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
                finish();
                break;
            case R.id.backLay:
                finish();
                break;
            case R.id.bt_return:
                //startActivity(new Intent(IWantReturnActivity.this, CarReturnActivity.class));
                /**
                 * 新增一个是否选择车俩的验证 何明辉
                 */
                if(returnAdapter.getSelectedPosition()==-1){
                    ToastUtil.show(IWantReturnActivity.this,R.string.please_choce);
                }else{
                if(returnAdapter.getChooseBorrowRecords() != null) {
                    ReturnCarDialog.Builder builder = new ReturnCarDialog.Builder(IWantReturnActivity.this);
                    builder.setTitle(R.string.dialog_ti);
                    builder.setMessage(getString(R.string.suo) + returnAdapter.getChooseBorrowRecords().getCarnum());
                    builder.setPositiveButton(R.string.back_sure, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            //设置你的操作事项
                            mPresenter.returnCar("" + returnAdapter.getChooseBorrowRecords().getId(), returnAdapter.getChooseBorrowRecords().getCarnum());

                        }
                    });

                    builder.setNegativeButton(R.string.back_cancel,
                            new android.content.DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();

                                }
                            });

                    builder.create().show();
                }else {
                    //无需显示  王松清
                    //ToastUtil.show(IWantReturnActivity.this,R.string.car_id);
                }
                }
                break;
        }
    }

    @Override
    public void showError(Throwable e) {
        ToastUtil.show(IWantReturnActivity.this,e.getMessage());
    }

    @Override
    public void returnSuccess(ResponseDataBean<ReturnCarInfo> returnCarParm) {
        Intent intent=new Intent();
        intent.setClass(IWantReturnActivity.this, ReturnSuccessActivity.class);
        intent.putExtra("bId",returnCarParm.getData().getId());
        startActivity(intent);
    }

    @Override
    public void returnFail(ResponseDataBean<ReturnCarInfo> returnCarParm) {
        ToastUtil.show(IWantReturnActivity.this,getString(R.string.return_car_fail));
    }

    @Override
    public void showSuccess(List<BorrowRecords> ret) {
        if(ret != null ) {

            if(ret.size() == 0){
                ll_have_car.setVisibility(View.GONE);
                rl_no_car.setVisibility(View.VISIBLE);
            }else {
                returnAdapter.setList(ret);
                ll_have_car.setVisibility(View.VISIBLE);
                rl_no_car.setVisibility(View.GONE);
            }
        }
    }




    @Override
    public void showFail(ResponseDataBean<List<BorrowRecords>> borrowRecordParm) {
        ToastUtil.show(IWantReturnActivity.this,R.string.getcar_fail);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setOwerNum(app.getCommonParam().getOwerPhone());

    }

}
