package com.soarsky.policeclient.ui.blacklist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.uitl.SpUtil;
import com.soarsky.policeclient.uitl.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  黑名单列表<br>
 */


public class BlackListActivity extends BaseActivity<BlackListPresent, BlackListModel> implements BlackListView, View.OnClickListener {
    /**
     * 黑名单列表gridview
     */
    private GridView bl_gridview;
    /**
     * 黑名单更新时间
     */
    private TextView time;
    /**
     * 返回键
     */
    private ImageView blacklist_back;
    /**
     * 数据适配器
     */
    private BlackListAdapter adapter;
    /**
     * 黑名单集合
     */
    private List<BlackCar> mlist = new ArrayList<>();

    private int position;


    @Override
    public int getLayoutId() {
        return R.layout.activity_blacklist;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.getBlackCar();
    }

    @Override
    public void initView() {
        blacklist_back = (ImageView) findViewById(R.id.blacklist_back);
        blacklist_back.setOnClickListener(this);
        time = (TextView) findViewById(R.id.time);
        String timeStr = null;
        if(TextUtils.isEmpty(SpUtil.get(SpUtil.get(Constants.CONS_USERNAME)+Constants.BLACKLISTUPDATETIME))){
            timeStr = "暂未更新";
        }else {
            timeStr = TimeUtils.date2String(TimeUtils.string2Date(SpUtil.get(SpUtil.get(Constants.CONS_USERNAME)+Constants.BLACKLISTUPDATETIME),TimeUtils.DEFAULT_SDF2));
        }

        time.setText(timeStr);
        bl_gridview = (GridView) findViewById(R.id.bl_gridview);
        adapter = new BlackListAdapter(BlackListActivity.this);
        bl_gridview.setAdapter(adapter);
        //给黑名单车辆增加点击事件查看车辆详情
        bl_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ToastUtil.show(getActivity(),"我被点击了");
                if(i!=mlist.size()){
                    Intent intent=new Intent();
                    intent.setClass(BlackListActivity.this, BlackListCarDetailsActivity.class);//从一个activity跳转到另一个activity
                    intent.putExtra("carNum",mlist.get(i).getCarnum());//给intent添加额外数据，key为“carNum”,key值为"Intent Demo"
                    intent.putExtra("ptype",mlist.get(i).getPlatetype());
                    intent.putExtra("reason",mlist.get(i).getReason());
                    position = i;
                    startActivityForResult(intent,1);
                }
            }
        });
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blacklist_back:
                finish();  //关闭页面
                break;
        }
    }

    /**
     * 显示黑名单列表
     * @param list 黑名单列表
     */
    public void showBlackList(List<BlackCar> list) {
        mlist = list;
        if (adapter != null) {
            adapter.setBlackCarList(mlist); //将黑名单传给适配器
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            mlist.get(position).setReason("已处理");
            App.getApp().getDaoSession().getBlackCarDao().update(mlist.get(position));
            adapter.setBlackCarList(mlist);
        }
    }
}
