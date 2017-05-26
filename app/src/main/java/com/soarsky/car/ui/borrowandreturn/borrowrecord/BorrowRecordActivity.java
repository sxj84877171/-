package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.ui.borrowandreturn.borrow.AgreeBorrowActivity;
import com.soarsky.car.ui.borrowandreturn.borrow.SubmitedAplicationActivity;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.BorrowAplicationActivity;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.aplicationrefuse.AplicationRefuseActivity;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess.BorrowSuccessActivity;
import com.soarsky.car.ui.borrowandreturn.recorddetails.RecordDetailsActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  借车记录页面<br>
 */

public class BorrowRecordActivity extends BaseActivity<BorrowRecordPresent,BorrowRecordMedol> implements BorrowRecordView ,View.OnClickListener{
    /**
     * 筛选
     */
    private TextView tv_right;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 借车记录列表
     */
    private ListView lv_record;
    /**
     * 借车记录数据适配器
     */
    private BorrowRecordAdapter adapter;
    /**
     * 全局实例
     */
    private App app;
    /**
     * 借车记录的集合
     */
    private List<BorrowRecords> list = null;

    /**
     * 返回要展示的布局
     * @return 布局文件的id
     */
    @Override
    public int getLayoutId() {
        return R.layout.borrow_record;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.borrow_car_and_return_right);

        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setText(R.string.shuaixuan);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        lv_record = (ListView) findViewById(R.id.lv_record);

        mPresenter.setOwenPhoneNum(App.getApp().getCommonParam().getOwerPhone());

        adapter = new BorrowRecordAdapter(BorrowRecordActivity.this);


        lv_record.setAdapter(adapter);
        lv_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /**
                 *  0 申请
                 *  1 申请通过
                 *  2 已还车
                 *  3
                 *  4 拒绝
                 */
                Intent intent=new Intent();
                intent.putExtra("ownerPhone",list.get(i).getOwner());
                intent.putExtra("borrowPhone",list.get(i).getBorrow());
                intent.putExtra("carType", list.get(i).getModel());
                intent.putExtra("bId",list.get(i).getId());
                intent.putExtra("carNum",list.get(i).getCarnum());
                intent.putExtra("startTime",list.get(i).getStime());
                intent.putExtra("endTime",list.get(i).getEtime());
                intent.putExtra("remark",list.get(i).getRemark());
                intent.putExtra("rTime",list.get(i).getRtime());
                intent.putExtra("ownercarNum",app.getCommonParam().getCarNum());
                if (list.get(i).getStatus().equals("0")){


                    if(app.getCommonParam().getOwerPhone().equals(list.get(i).getBorrow())){
                        intent.setClass(BorrowRecordActivity.this, SubmitedAplicationActivity.class);//从一个activity跳转到另一个activity
                    }else{
                        intent.setClass(BorrowRecordActivity.this, BorrowAplicationActivity.class);//从一个activity跳转到另一个activity
                    }
                }else if (list.get(i).getStatus().equals("1")){

                    if (app.getCommonParam().getOwerPhone().equals(list.get(i).getBorrow())){
                        intent.setClass(BorrowRecordActivity.this, BorrowSuccessActivity.class);//从一个activity跳转到另一个activity
                    }else {
                        intent.setClass(BorrowRecordActivity.this, AgreeBorrowActivity.class);
                    }

                } else if (list.get(i).getStatus().equals("2")){

                    intent.setClass(BorrowRecordActivity.this, RecordDetailsActivity.class);//从一个activity跳转到另一个activity

                }else if (list.get(i).getStatus().equals("3")){

                }else if (list.get(i).getStatus().equals("4")){

                    intent.setClass(BorrowRecordActivity.this, AplicationRefuseActivity.class);//从一个activity跳转到另一个activity


                }
                startActivity(intent);
            }
        });
    }

    /**
     * 启动当前页面时调用的方法
     * @param savedInstanceState  保存信息的bundle对象
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();

    }

    /**
     * onCreate之后执行的方法
     */
    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.getRecord(app.getCommonParam().getOwerPhone(), app.getCommonParam().getUserName());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.tv_right:

                break;
        }
    }

    @Override
    public void showSuccess(List<BorrowRecords> list) {
        this.list = list ;
        adapter.setList(list);
        adapter.setAppPhone(app.getCommonParam().getOwerPhone());
//        insertBorrowRecord(list);
    }

    @Override
    public void showFail(ResponseDataBean<List<BorrowRecords>> borrowRecordParm) {
        //将后台的message转换成我们自己的内容显示给用户               王松清
        ToastUtil.show(BorrowRecordActivity.this,R.string.get_recoed_list_fail);
    }

    @Override
    public void showError() {
        ToastUtil.show(BorrowRecordActivity.this,R.string.no_connet_internet);
    }

    @Override
    public void insertFail() {

    }

    @Override
    public void insertSuccess() {

    }

    /**
     * 插入数据库
     * @param list 参数列表
     */
    private void insertBorrowRecord(List<BorrowRecords> list){
        for (BorrowRecords mList :list){
            Tborrow tborrow = new Tborrow();
           tborrow.setOwner(mList.getOwner());
            tborrow.setBorrower(mList.getAppuser());
            tborrow.setCarnum(mList.getCarnum());
            tborrow.setStarttime(mList.getStime());
            tborrow.setEndtime(mList.getEtime());
            tborrow.setBacktime(mList.getStime());
            tborrow.setStatus(Integer.parseInt(mList.getStatus()));
            tborrow.setSstate(0);
            tborrow.setValue(Constants.LATEST_READ);
            tborrow.setAuthcode(app.getCommonParam().getAuthCode());
            mPresenter.insertBorrowRecord(tborrow);
        }
    }
}
