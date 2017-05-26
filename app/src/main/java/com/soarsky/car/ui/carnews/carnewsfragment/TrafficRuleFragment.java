package com.soarsky.car.ui.carnews.carnewsfragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.carnews.ArticleDetailsActivity;
import com.soarsky.car.ui.carnews.carnewssearch.SearchActivity;
import com.soarsky.car.uitl.SPUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  汽车资讯--交通法规页面<br>
 */

public class TrafficRuleFragment extends BaseFragment<CarCarePresent,CarCareModel> implements CarCareView ,View.OnClickListener {
    /**
     * 资讯列表
     */
    private ListView listView;
    /**
     * 资讯列表数据适配器
     */
    private CarCareFragmentAdapter adapter;
    /**
     * 最后阅读
     */
    private ImageView latest_read;
    /**
     * 搜索框
     */
    private LinearLayout ll_search;
    /**
     * 将资讯id存储在本地的key
     */
    private final String IDS = "id";
    /**
     * 最后阅读标识
     */
    private final String READ = "lastRead";
    /**
     * 资讯的集合
     */
    private List<AutomotiveInfo> mList;
    /**
     * 创建一个存储id的集合
     */
    private List<String> idList = new ArrayList<String>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_car_care;
    }

    @Override
    public void initView(View view) {

        SpUtil.init(mContext);

        latest_read = (ImageView) view.findViewById(R.id.latest_read);
        latest_read.setOnClickListener(this);

        ll_search = (LinearLayout) view.findViewById(R.id.ll_search);
        ll_search.setOnClickListener(this);

        listView = (ListView) view.findViewById(R.id.lv_car_news);


    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.latest_read:
                String read = SpUtil.get(READ);
                if (read.equals("")){
                    ToastUtil.show(mContext,getString(R.string.no_latest));
                }else {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ArticleDetailsActivity.class);
                    intent.putExtra("aid",Integer.parseInt(read));
                    startActivity(intent);
                }


                break;
            case R.id.ll_search:
                Intent i = new Intent();
                i.setClass(mContext, SearchActivity.class);
                i.putExtra("type", Constants.TYPE_TRAFFIC_RULE);
                startActivity(i);

                break;
        }
    }

    /**
     * 获取汽车资讯列表失败
     * @param e 错误参数
     */
    @Override
    public void showError(Throwable e) {
        ToastUtil.show(getActivity(),R.string.no_connet_internet);
    }

    /**
     * 获取汽车资讯列表成功的回调函数
     * @param listResponseDataBean
     */
    @Override
    public void showSuccess(ResponseDataBean<List<AutomotiveInfo>> listResponseDataBean) {
        mList = listResponseDataBean.getData();
        if (mList == null){
            ToastUtil.show(mContext ,getString(R.string.no_this_news));
        }

        for(int i=0;i<mList.size();i++){
            if(idList.contains(mList.get(i).getId())){
                //此条服务端返回的新闻是已读的
                mList.get(i).setRead(true);
            }else{
                //此条服务端返回的新闻是未读的
                mList.get(i).setRead(false);
            }
        }
        adapter = new CarCareFragmentAdapter(getActivity());
        adapter.setData(mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpUtil.save(READ,String.valueOf(mList.get(i).getId()));

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

    @Override
    public void onStart() {
        super.onStart();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
