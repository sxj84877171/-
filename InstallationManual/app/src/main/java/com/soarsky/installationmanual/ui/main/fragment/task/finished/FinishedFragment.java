package com.soarsky.installationmanual.ui.main.fragment.task.finished;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseFragment;
import com.soarsky.installationmanual.bean.FinishTaskBean;
import com.soarsky.installationmanual.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 完成的任务的页面<br>
 */

public class FinishedFragment extends BaseFragment{
    /*private ListView listView;*/
    RecyclerView listView;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_finished;
    }

    @Override
    public void initView(View view) {
        listView = (RecyclerView) view.findViewById(R.id.listView);
        FinishedListAdapter finishedListAdapter = new FinishedListAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        listView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        listView.setAdapter(finishedListAdapter);
        List<FinishTaskBean> finishedBeenList = new ArrayList<>();
        for (int i = 0;i<10;i++){
            FinishTaskBean finishTaskBean = new FinishTaskBean();
            finishTaskBean.setFenPeiShiJian("分配时间："+TimeUtils.getCurTimeString()+"1");
            finishTaskBean.setKaiShiShiJian("开始时间："+TimeUtils.getCurTimeString()+"2");
            finishTaskBean.setWanChengShiJian("完成时间："+TimeUtils.getCurTimeString()+"3");
            finishTaskBean.setCarNum("湘A12345");
            if(i%2==0){
                finishTaskBean.setFinishStatus(0);
            }else {
                finishTaskBean.setFinishStatus(1);
            }
            finishedBeenList.add(finishTaskBean);
        }
        finishedListAdapter.setData(finishedBeenList);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void showSuccess() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        toolbar.setVisibility(View.GONE);
    }
}
