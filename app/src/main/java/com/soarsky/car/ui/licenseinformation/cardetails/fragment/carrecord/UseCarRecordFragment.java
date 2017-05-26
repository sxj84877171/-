package com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;
import com.soarsky.car.ui.usecarrecord.UseCarRecordAdapter;
import com.soarsky.car.uitl.ToastUtil;

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
 * 程序功能：<br>
 * 该类为  用车记录<br>
 */

public class UseCarRecordFragment extends BaseFragment<UseCarRecordFragmentPresent,UseCarRecordFragmentModel> implements UseCarRecordFragmentView,View.OnClickListener{

    private Context mContext;

    private ListView carRecordListView;

    private UseCarRecordAdapter adapter;

    private App app;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_use_car_record3;
    }

    @Override
    public void initView(View view) {
        app = (App) mContext.getApplicationContext();
        carRecordListView = (ListView) view.findViewById(R.id.carRecordListView);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getCarRecoredsList(app.getCommonParam().getCarNum());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void getCarRecoredsListSuccess(ResponseDataBean<List<UseCarRecordParam>> useCarRecordParam) {

        if(useCarRecordParam != null){

            if(Constants.REQUEST_SUCCESS.equals(useCarRecordParam.getStatus())){
                adapter = new UseCarRecordAdapter(mContext,useCarRecordParam.getData());
                carRecordListView.setAdapter(adapter);
            }else {
                //王松清
                ToastUtil.show(mContext,R.string.get_data_fail);
            }
        }
    }

    @Override
    public void getCarRecoredsListFail() {

//        ToastUtil.show(mContext,R.string.throwable);
    }

    @Override
    public void onClick(View view) {

    }
}
