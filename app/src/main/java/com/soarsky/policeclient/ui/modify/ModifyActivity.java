package com.soarsky.policeclient.ui.modify;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.ModifSendParm;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.ui.home.HomeActivity;
import com.soarsky.policeclient.uitl.ToastUtil;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  修改密码页面<br>
 */

public class ModifyActivity extends BaseActivity<ModifyPresent,ModifyMode> implements ModifyView,View.OnClickListener{
    /**
     * 返回按钮
     */
    private ImageView blacklist_back;
    /**
     * 确认修改按钮
     */
    private Button bt_sure;
    /**
     * 旧密码
     */
    private PwdView et_old;
    /**
     * 新密码
     */
    private PwdView et_new_f;
    /**
     * 确认新密码
     */
    private PwdView et_new_s;
    /**
     * 请求参数
     */
    private ModifSendParm parm;
    /**
     * 判断密码是否可见，默认为可见
     */
    private boolean open;


    private App app;

    @Override
    public int getLayoutId() {
        return R.layout.modify_pwd;
    }

    @Override
    public void initView() {
        app = (App)getApplication();
        app.addActivity("ModifyActivity",this);

        //返回按钮
        blacklist_back = (ImageView) findViewById(R.id.blacklist_back);
        blacklist_back.setOnClickListener(this);

        //修改密码按钮
        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(this);

        et_old = (PwdView) findViewById(R.id.old_pwd);
        et_new_f = (PwdView) findViewById(R.id.new_pwd);
        et_new_s = (PwdView) findViewById(R.id.confim_pwd);

        //String[] strs = str.split(Pattern.quote("你指定的字符"));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.blacklist_back:
                ToastUtil.show(ModifyActivity.this,"返回");
                //startActivity(new Intent(ModifyActivity.this, HomeActivity.class));//按页面返回键，跳转到首页
                finish();
                break;
            case R.id.bt_sure:

                String odlPwd = et_old.getEditTextString();;//旧密码
                String newPwd = et_new_f.getEditTextString();//新密码
                String confirmPwd = et_new_s.getEditTextString();//确认新密码

                if (TextUtils.isEmpty(odlPwd)||TextUtils.isEmpty(newPwd)||TextUtils.isEmpty(confirmPwd)){//判断密码是否为空
                    ToastUtil.show(ModifyActivity.this,R.string.empty);
                    return;
                }else if(newPwd.length()<6 || newPwd.length()>12){
                    ToastUtil.show(ModifyActivity.this,"密码长度是6-12位");
                    return;
                }else if (!newPwd.equals(confirmPwd)){ //判断新密码与旧密码是否一致
                    ToastUtil.show(ModifyActivity.this,R.string.pwd_confirm);
                    return;
                }else {

                    mPresenter.reset(app.getUserName(),odlPwd,newPwd);
                }

                break;


        }
    }

    @Override
    public void showSuccess(ResponseDataBean modifyParam) {
        ToastUtil.show(ModifyActivity.this,"修改成功");
        startActivity(new Intent(ModifyActivity.this, HomeActivity.class)); //修改密码成功后，跳转回首页
    }

    @Override
    public void showFail(String msg) {
        ToastUtil.show(ModifyActivity.this,R.string.fail_old_pwd);
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {


    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        //按系统返回键时，回到首页
        finish();

    }*/
}
