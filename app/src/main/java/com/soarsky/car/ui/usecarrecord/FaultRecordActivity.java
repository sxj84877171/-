package com.soarsky.car.ui.usecarrecord;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.FaultRecordInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为故障记录<br>
 */

public class FaultRecordActivity extends BaseActivity<RecordPresent,RecordModel> implements RecordView,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    private LinearLayout backLay;
    private App app;
    private ExpandableListView eListView;
    private FaultRecordExpandadapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_fault_record3;
    }

    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText("故障记录");

        app = (App) getApplicationContext();

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        eListView = (ExpandableListView) findViewById(R.id.expandablelistview);
        eListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }

    @Override
    public void getCarRecoredsListSuccess(ResponseDataBean<List<UseCarRecordParam>> useCarRecordParam) {

    }

    @Override
    public void getCarRecoredsListFail() {

    }

    @Override
    public void getFaultSuccess(ResponseDataBean<List<FaultRecordDataBean>> param) {
        if (param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){
                packageFaultRecordData(param.getData());
            }else {
                ToastUtil.show(mContext,getString(R.string.get_data_fail));
            }
        }
    }

    @Override
    public void getFaultFail() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getFault(app.getCommonParam().getCarNum());
    }

    /**
     * 封装故障数据
     */
    private void packageFaultRecordData(List<FaultRecordDataBean> data){

        List<FaultRecordInfo> list = new ArrayList<FaultRecordInfo>();
//      去除相同月份
        List<String> _list = new ArrayList<String>();
        for(FaultRecordDataBean bean: data){

            String month = bean.getAtime().substring(0,7);
            if(!(_list.contains(month))){
                _list.add(month);
            }
        }
//      根据唯一的月份，封装数据
        for (String m:_list){

            FaultRecordInfo info = new FaultRecordInfo();
            info.setMonth(m);
            //这个月份的所有数据
            List<FaultRecordDataBean> beanList = new ArrayList<FaultRecordDataBean>();

            for (FaultRecordDataBean bean: data){
                if (bean.getAtime().contains(m)){
                    beanList.add(bean);
                }
            }
            info.setData(beanList);
            list.add(info);
        }

        adapter = new FaultRecordExpandadapter(mContext,list);

        eListView.setAdapter(adapter);

        eListView.setGroupIndicator(null);

        for(int i = 0; i < adapter.getGroupCount(); i++){

            eListView.expandGroup(i);

        }

    }
}
