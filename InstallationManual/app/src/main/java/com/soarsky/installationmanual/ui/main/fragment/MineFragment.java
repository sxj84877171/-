package com.soarsky.installationmanual.ui.main.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseFragment;
import com.soarsky.installationmanual.ui.completeuserinfo.CompleteUserInfoActivity;
import com.soarsky.installationmanual.ui.login.LoginActivity;
import com.soarsky.installationmanual.ui.main.fragment.collection.CollectionActivity;
import com.soarsky.installationmanual.ui.main.fragment.history.HistoryActivity;
import com.soarsky.installationmanual.ui.main.fragment.updatepwd.UpdatePwdActivity;
import com.soarsky.installationmanual.util.SpUtil;


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
 * 该类为 我的界面<br>
 */

public class MineFragment extends BaseFragment {
    /**
     * 个人资料
     */
    private RelativeLayout gerenziliao;
    /**
     * 我的收藏
     */
    private RelativeLayout wodeshoucang;
    /**
     * 浏览历史
     */
    private RelativeLayout liulanlishi;
    /**
     * 修改密码
     */
    private RelativeLayout xiugaimima;
    /**
     * 退出登录
     */
    private RelativeLayout tuichudenglu;
    /**
     * 姓名
     */
    private TextView name;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        name = (TextView) view.findViewById(R.id.name);
        name.setText(SpUtil.get(Constants.NAME));
        gerenziliao = (RelativeLayout) view.findViewById(R.id.gerenziliao);
        wodeshoucang = (RelativeLayout) view.findViewById(R.id.wodeshoucang);
        liulanlishi = (RelativeLayout) view.findViewById(R.id.liulanlishi);
        xiugaimima = (RelativeLayout) view.findViewById(R.id.xiugaimima);
        tuichudenglu = (RelativeLayout) view.findViewById(R.id.tuichudenglu);
        tuichudenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("确认退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SpUtil.save(Constants.USERNAME,"");
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                getActivity().finish();

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();

            }
        });
        gerenziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CompleteUserInfoActivity.class);
                intent.putExtra(Constants.NAME,SpUtil.get(Constants.NAME));
                intent.putExtra(Constants.PHONE,SpUtil.get(Constants.PHONE));
                intent.putExtra("idCard",SpUtil.get(Constants.USERNAME));
                intent.putExtra("type","2");
                String sex = null;
                if(Integer.parseInt(SpUtil.get(Constants.USERNAME).charAt(16)+"")%2==0){
                    sex = "女";
                }else {
                    sex = "男";
                }
                intent.putExtra("sex",sex);
                startActivity(intent);
            }
        });
        xiugaimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdatePwdActivity.class));
            }
        });
        wodeshoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CollectionActivity.class));
            }
        });
        liulanlishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
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
