package com.soarsky.policeclient.ui.record;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.RecordViolationDataBean;
import com.soarsky.policeclient.ui.record.fragment.CheckCarFragment;
import com.soarsky.policeclient.ui.record.fragment.ElectronicFragment;

import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  历史记录页面FragmentPagerAdapter类<br>
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<RecordViolationDataBean> recordViolationParamList;
    private List<RecordViolationDataBean> recordAuditParamList;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 标题
     */
    String[] mTitles = new String[]{"电子罚单","稽查车辆"};

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setRecordAuditParamList(List<RecordViolationDataBean> recordAuditParamList) {
        this.recordAuditParamList = recordAuditParamList;
//        notifyDataSetChanged();
    }

    public void setRecordViolationParamList(List<RecordViolationDataBean> recordViolationParamList) {
        this.recordViolationParamList = recordViolationParamList;
//        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        if (position == 1) {
            return new CheckCarFragment();
        }

        return new ElectronicFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(mTitles[position]);
        return view;
    }
}
