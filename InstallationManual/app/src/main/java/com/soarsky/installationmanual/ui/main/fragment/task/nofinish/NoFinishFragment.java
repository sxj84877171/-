package com.soarsky.installationmanual.ui.main.fragment.task.nofinish;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseFragment;
import com.soarsky.installationmanual.bean.FinishTaskBean;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.nofinishitem.NoFinishItemActivity;
import com.soarsky.installationmanual.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 未完成任务界面<br>
 */

public class NoFinishFragment extends BaseFragment{
    private ListView listView;
    List<FinishTaskBean> noFinishBeenList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_no_finish;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        NoFinishListAdapter noFinishListAdapter = new NoFinishListAdapter(getActivity());
        listView.setAdapter(noFinishListAdapter);
        for (int i = 0;i<10;i++){
            FinishTaskBean finishTaskBean = new FinishTaskBean();
            finishTaskBean.setTime(TimeUtils.getCurTimeString());
            finishTaskBean.setCarNum("湘A12345");
            if(i%2==0){
                finishTaskBean.setHasRead(true);
            }else {
                finishTaskBean.setHasRead(false);
            }
            finishTaskBean.setStatus(0);
            noFinishBeenList.add(finishTaskBean);
        }
        noFinishListAdapter.setData(noFinishBeenList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NoFinishItemActivity.class);
                intent.putExtra("carNum",noFinishBeenList.get(position).getCarNum());
                startActivity(intent);
            }
        });
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
