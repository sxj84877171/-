package com.soarsky.car.ui.illegal.fragment.driver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealIlist;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.ui.illegal.adapter.IllegalListAdapter;
import com.soarsky.car.ui.illegal.detail.IllegalDetailActivity;
import com.soarsky.car.ui.illegal.fragment.IllegalUpdateCallBack;
import com.soarsky.car.uitl.DateUtil;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.soarsky.car.Constants.NO_REVOKE;
import static com.soarsky.car.Constants.REVOKED;
import static com.soarsky.car.Constants.REVOKEING;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：根据驾驶证获取违章信息<br>
 * 该类为 IllegalDriverFragment<br>
 */


public class IllegalDriverFragment extends BaseFragment<IllegalDriverFragmentPresent,IllegalDriverFragmentModel> implements IllegalDriverFragmentView,View.OnClickListener,IllegalUpdateCallBack{

    /**
     * 违章列表
     */
    private ListView illegalListView;
    /**
     * 更新时间
     */
    private TextView illegalDealTimeTv;
    /**
     * 违章数
     */
    private TextView illegaldealNotTv;
    /**
     * 罚款
     */
    private TextView illegaldealFineTv;
    /**
     * 分
     */
    private TextView illegaldealCentTv;
    /**
     * 地址
     */
    private TextView illegalAddressTv;
    /**
     * application
     */
    private App app;
    /**
     * 上下文本
     */
    private Context mContext;
    /**
     * 适配器
     */
    private IllegalListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_illegal_personal3;
    }

    @Override
    public void initView(View view) {

        app = (App) mContext.getApplicationContext();
        illegalDealTimeTv = (TextView) view.findViewById(R.id.illegalDealTimeTv);

        illegalListView = (ListView) view.findViewById(R.id.illegalListView);

        illegaldealNotTv = (TextView) view.findViewById(R.id.illegaldealNotTv);
        illegaldealFineTv = (TextView) view.findViewById(R.id.illegaldealFineTv);
        illegaldealCentTv = (TextView) view.findViewById(R.id.illegaldealCentTv);
        illegalAddressTv = (TextView) view.findViewById(R.id.illegalAddressTv);

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(app.getCommonParam()!= null) {
            if (app.getCommonParam().getIdNo() != null) {
//                根据驾驶证请求违章信息
                mPresenter.getIlleagaInfoByIdNo(app.getCommonParam().getIdNo());
            }
        }


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     *获取违章数据成功
     * @param param 参数
     */
    @Override
    public void showSuccess(ResponseDataBean<ViolationDealInfo> param) {

        if(param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){
                List<ViolationDealIlist> list = new ArrayList<ViolationDealIlist>();

                int totalMoney = 0;
                int totalCent = 0;
                for(ViolationDealIlist violationDealIlist:param.getData().getIlist()){
                   if(!violationDealIlist.getStatus().equals(REVOKED)){
                       list.add(violationDealIlist);
                       try {
                           totalCent = totalCent + Integer.parseInt(violationDealIlist.getCent());
                           totalMoney = totalMoney +Integer.parseInt(violationDealIlist.getMonery());
                       }catch (Exception e){
                           e.printStackTrace();
                       }
                   }
                }

                Collections.sort(list, new Comparator<ViolationDealIlist>() {
                    /**
                     *
                     * @param lhs
                     * @param rhs
                     * @return an integer < 0 if lhs is less than rhs, 0 if they are
                     *         equal, and > 0 if lhs is greater than rhs,比较数据大小时,这里比的是时间
                     */
                    @Override
                    public int compare(ViolationDealIlist lhs, ViolationDealIlist rhs) {
                        Date date1 = DateUtil.stringToDate(lhs.getStime());
                        Date date2 = DateUtil.stringToDate(rhs.getStime());
                        // 对日期字段进行升序，如果欲降序可采用after方法
                        if (date1.before(date2)) {
                            return 1;
                        }
                        return -1;
                    }
                });

                adapter = new IllegalListAdapter(mContext,list,this);

                illegalListView.setAdapter(adapter);
                illegalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViolationDealIlist bean = (ViolationDealIlist)adapter.getItem(i);

                        if(NO_REVOKE.equals(bean.getSign())){

                            if(REVOKEING.equals(bean.getStatus())||REVOKED.equals(bean.getStatus())){

                                Intent in = new Intent(mContext, IllegalDetailActivity.class);
                                in.putExtra("bean",bean);
                                mContext.startActivity(in);

                            }

                        }else {

                        }
                    }
                });
                String last = param.getData().getLastTime();
                if(last == null || "".equals(last.trim())){
                    last = TimeUtils.getCurDateyyyy_MM_dd();
                }
                illegalDealTimeTv.setText(roundDate(last));
//                illegaldealNotTv.setText(""+param.getData().getDealCount());
//                illegaldealFineTv.setText(""+param.getData().getCountMoney());
//                illegaldealCentTv.setText(""+param.getData().getCountCent());
                illegaldealNotTv.setText(""+list.size());
                illegaldealFineTv.setText(""+totalMoney);
                illegaldealCentTv.setText(""+totalCent);
            }else {
                ToastUtil.show(mContext,param.getMessage());
            }
        }

    }


    private String roundDate(String date){
        if(date.trim() .endsWith(":")){
            if(date.length() >= 10){
                date = date.substring(0,10);
            }
        }
        return  date ;
    }
    /**
     *获取违章数据失败
     */
    @Override
    public void showError() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void illegalUpdateCallBack(String id) {

        Log.d("TAG","IllegalDriverFragment---illegalUpdateCallBack");

        if(id != null){
            mPresenter.appViolationAdvice(id);
        }
    }

    @Override
    public void appViolationAdviceSuccess(ResponseDataBean<Void> param) {
        if(app.getCommonParam()!= null) {
            if (app.getCommonParam().getIdNo() != null) {
//                根据驾驶证请求违章信息
                mPresenter.getIlleagaInfoByIdNo(app.getCommonParam().getIdNo());
            }
        }
    }

    @Override
    public void appViolationAdviceFail() {

    }
}
