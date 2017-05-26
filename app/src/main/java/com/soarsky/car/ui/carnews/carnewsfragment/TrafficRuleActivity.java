package com.soarsky.car.ui.carnews.carnewsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.carnews.ArticleDetailsActivity;
import com.soarsky.car.uitl.SPUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为资讯--交通法规<br>
 */

public class TrafficRuleActivity extends BaseActivity<CarCarePresent,CarCareModel> implements CarCareView, View.OnClickListener {
    /**
     * 返回按钮
     */
    private LinearLayout closeLay;

    /**
     *标题
     */
    private TextView titleTv;

    private ListView listView;

    /**
     * 资讯集合
     */
    private List<AutomotiveInfo> mList;

    /**
     * 资讯列表数据适配器
     */
    private CarCareFragmentAdapter adapter;

    /**
     * 创建一个存储id的集合
     */

    private List<String> idList = new ArrayList<String>();

    /**
     * 将资讯id存储在本地的key
     */
    private final String IDS = "id";

    @Override
    public int getLayoutId() {
        return R.layout.activity_traffic3;
    }

    @Override
    public void initView() {
        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText(getString(R.string.traffic_rule));

        closeLay = (LinearLayout) findViewById(R.id.closeLay);
        closeLay.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.lv_car_news);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.closeLay:
                finish();
                break;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String string = SPUtils.getString(mContext, IDS, "");
        String[] idArray = string.split("#");
        idList.clear();
        //将idsArray转换成集合
        for(int i=0;i<idArray.length;i++){
            idList.add(idArray[i]);
        }
        mPresenter.getAutomotiveList(Constants.TYPE_TRAFFIC_RULE);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void showError(Throwable e) {
        ToastUtil.show(TrafficRuleActivity.this,R.string.no_connet_internet);
    }

    @Override
    public void showSuccess(ResponseDataBean<List<AutomotiveInfo>> listResponseDataBean) {
        mList = listResponseDataBean.getData();
        if (mList == null){
            ToastUtil.show(mContext ,R.string.no_this_news);
        }else {

        for(int i=0;i<mList.size();i++){
            if(idList.contains(mList.get(i).getId())){
                //此条服务端返回的新闻是已读的
                mList.get(i).setRead(true);
            }else{
                //此条服务端返回的新闻是未读的
                mList.get(i).setRead(false);
            }
        }
        adapter = new CarCareFragmentAdapter(TrafficRuleActivity.this);
        adapter.setData(mList);
        listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SpUtil.save(Constants.READ,String.valueOf(mList.get(i).getId()));

                if (!mList.get(i).isRead()){
                    mList.get(i).setRead(true);
                    String ids = SpUtil.get(IDS);
                    if(TextUtils.isEmpty(SpUtil.get(IDS))){

                        SpUtil.save(IDS,String.valueOf(mList.get(i).getId()));
                    }else{
                        SpUtil.save(IDS,SpUtil.get(IDS)+"#"+String.valueOf(mList.get(i).getId()));
                    }
                    adapter.setData(mList);
                }

                Intent intent = new Intent();
                AutomotiveInfo info =mList.get(i);
                Gson gson = new Gson();
                SpUtil.save("info",gson.toJson(info));
                intent.setClass(mContext, ArticleDetailsActivity.class);
                intent.putExtra("info",info);
                intent.putExtra("aid",mList.get(i).getId());
                startActivity(intent);

            }
        });
    }
}
