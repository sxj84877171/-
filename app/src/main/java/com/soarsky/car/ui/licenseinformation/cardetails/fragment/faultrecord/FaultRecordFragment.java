package com.soarsky.car.ui.licenseinformation.cardetails.fragment.faultrecord;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.FaultRecordInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.usecarrecord.FaultRecordExpandadapter;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：故障记录页面<br>
 * 该类为<br>
 */

public class FaultRecordFragment extends BaseFragment<FaultRecordFragmentPresent,FaultRecordFragmentModel> implements FaultRecordFragmentView,View.OnClickListener {

    private ExpandableListView eListView;

    private Context mContext;

    private App app;

    private FaultRecordExpandadapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_fault_record3;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void initView(View view) {
        app = (App) mContext.getApplicationContext();
        eListView = (ExpandableListView) view.findViewById(R.id.expandablelistview);
        eListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getFault(app.getCommonParam().getCarNum());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }
    /**
     * 获取故障列表成功
     * @param param
     */
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
    /**
     * 获取故障列表失败
     */
    @Override
    public void getFaultFail() {

//        ToastUtil.show(mContext,R.string.throwable);
    }

    @Override
    public void onClick(View view) {

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
