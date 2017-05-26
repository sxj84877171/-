package com.soarsky.policeclient.ui.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.Note;
import com.soarsky.policeclient.ui.accident.listandadd.ListAndAddActivity;
import com.soarsky.policeclient.ui.blacklist.BlackListActivity;
import com.soarsky.policeclient.ui.check.CheckActivity;
import com.soarsky.policeclient.ui.login.LoginActivity;
import com.soarsky.policeclient.ui.modify.ModifyActivity;
import com.soarsky.policeclient.ui.record.RecordActivity;
import com.soarsky.policeclient.ui.violation.UploadFile;
import com.soarsky.policeclient.ui.violation.ViolationActivity;
import com.soarsky.policeclient.ui.violation.ViolationUpload;
import com.soarsky.policeclient.uitl.ImageUtil;
import com.soarsky.policeclient.uitl.SpUtil;
import com.soarsky.policeclient.uitl.ToastUtil;
import com.soarsky.policeclient.server.CheckService;
import com.soarsky.policeclient.server.design.ICheck;

import java.util.List;

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
 * 该类为  首页界面<br>
 */
public class HomeActivity extends BaseActivity<HomePresent,HomeModel> implements HomeView,View.OnClickListener {


    /**
     * 头部列表
     */
    private RelativeLayout listIconLay;
    /**
     * 头部更新按钮
     */
    private RelativeLayout newIconLay;  //头部更新按钮
    /**
     * 稽查
     */
    private LinearLayout auditLay; //稽查
    /**
     * 电子罚单
     */
    private LinearLayout electronicLay; //电子罚单
    /**
     * 黑名单
     */
    private LinearLayout blacklistLay; //黑名单
    /**
     * 历史记录
     */
    private LinearLayout recordLay; //历史记录
    /**
     * 事故分析
     */
    private TextView analyTv;  //事故分析
    /**
     * 是否是首次
     */
    private boolean isFirst = false;
    /**
     * 右上角图标
     */
    private ImageView newIcon;
    /**
     * 稽查开关
     */
    private ImageView iv_switch;//稽查开关
    /**
     * 稽查状态
     */
    private TextView tv_status; //
    /**
     * 稽查状态,默认为打开
     */
    private boolean isOpen = true;//稽查状态,默认为打开
    /**
     * 未使用
     */
    private ViolationUpload violationUpload;
    /**
     * App
     */
    private App app;
    /**
     * 稽查功能
     */
    private  ICheck iCheck;
    /**
     * LOG TAG
     */
    private static final  String TAG = "HomeActivity";
    /**
     * 用户名
     */
    private TextView tv_userName;
    /**
     * 用户头像
     */
    private ImageView nav_iv;
    /**
     * 图片返回码
     */
    private static int RESULT_LOAD_IMAGE = 1;
    /**
     * 修改密码按钮
     */
    private RelativeLayout rl_modify;   //修改密码按钮
    /**
     * 退出登录按钮
     */
    private RelativeLayout rl_exit;   //退出登录按钮
    @Override
    public int getLayoutId() {
        return R.layout.activity;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        /*listIconLay =  findViewWithID(R.id.listIconLay);
        listIconLay.setOnClickListener(this);*/

        newIconLay = findViewWithID(R.id.newIconLay);
        newIconLay.setOnClickListener(this);

        auditLay =findViewWithID(R.id.auditLay);
        auditLay.setOnClickListener(this);

        electronicLay = findViewWithID(R.id.electronicLay);
        electronicLay.setOnClickListener(this);

        blacklistLay = findViewWithID(R.id.blacklistLay);
        blacklistLay.setOnClickListener(this);

        recordLay = findViewWithID(R.id.recordLay);
        recordLay.setOnClickListener(this);

        analyTv = findViewWithID(R.id.analyTv);
        analyTv.setOnClickListener(this);

        newIcon = findViewWithID(R.id.newIcon);

        iv_switch = (ImageView) findViewById(R.id.iv_switch);
        iv_switch.setOnClickListener(this);

        tv_status = (TextView) findViewById(R.id.tv_status);


    }
    @Override
    public void uploadImgFail() {

        ToastUtil.show(this,"网络链接失败");
    }

    @Override
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean) {
        if(stringResponseDataBean != null){
            if(Constants.STATUS.equals(stringResponseDataBean.getStatus())){

            }
        }
    }

    @Override
    public void modifyAppImageFail() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, CheckService.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_check, R.string.close_check);
        toggle.setHomeAsUpIndicator(R.drawable.home_nav_icon);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //侧拉菜单布局
        RelativeLayout view = (RelativeLayout) findViewById(R.id.left);
        //用户名设置
        tv_userName= (TextView) view.findViewById(R.id.tv_userName);
        tv_userName.setText(app.getUserName());

        nav_iv = (ImageView) view.findViewById(R.id.nav_iv);
        nav_iv.setOnClickListener(this);


        //侧拉菜单——修改密码
        rl_modify = (RelativeLayout) view.findViewById(R.id.rl_modify);
        rl_modify.setOnClickListener(this);

        //侧拉菜单——退出登录
        rl_exit = (RelativeLayout) view.findViewById(R.id.rl_exit);
        rl_exit.setOnClickListener(this);


    }


    public void query(View view) {
        mPresenter.getNotes();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, CheckService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }


    @Override
    public void onBackPressed() {
        /*new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        HomeActivity.this.stopService(new Intent(HomeActivity.this, CheckService.class));
                        HomeActivity.this.finish();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();*/
        unbindService(serviceConnection);
        HomeActivity.this.finish();

    }


    @Override
    public void showResult(List<Note> notes) {

    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, R.string.success, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFail() {
        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void uploadImgSuccess(UploadFile uploadFile) {

    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.listIconLay:
                showAbout();
                break;
            case R.id.newIconLay:
                showAbout();
                if(isFirst == false){
                    newIcon.setImageResource(R.mipmap.news_n);
                    isFirst = true;
                }else{
                    newIcon.setImageResource(R.mipmap.news);
                    isFirst = false;
                }
                break;
            case R.id.auditLay:

                startActivity(new Intent(this, CheckActivity.class));
                break;
            case R.id.electronicLay:
                startActivity(new Intent(this, ViolationActivity.class));
                break;
            case R.id.blacklistLay:
                startActivity(new Intent(this, BlackListActivity.class));

                break;
            case R.id.recordLay:
               startActivity(new Intent(this, RecordActivity.class));

                break;

            case R.id.analyTv:
                /*ToastUtil.show(this,R.string.accidentanalysis_toast);*/
                startActivity(new Intent(HomeActivity.this, ListAndAddActivity.class));
                break;
            case R.id.iv_switch:
                if (isOpen){
                    if(iCheck != null) {
                        iCheck.stopCheck(true);
                        ToastUtil.show(this, R.string.close_check);
                        iv_switch.setImageResource(R.mipmap.home_close);
                        tv_status.setText(R.string.closed);
                        isOpen = false;
                    }
                }else {
                    if(iCheck != null) {
                        iCheck.startCheck(true);
                        ToastUtil.show(this, R.string.open_check);
                        iv_switch.setImageResource(R.mipmap.home_open);
                        tv_status.setText(R.string.opened);
                        isOpen = true;
                    }
            }
                //ToastUtil.show(this,"打开");
                break;
            case R.id.nav_iv:
                /*Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);*/


                break;
            case R.id.rl_modify:
                startActivity(new Intent(HomeActivity.this,ModifyActivity.class));
                break;
            case R.id.rl_exit:

                new AlertDialog.Builder(this).setTitle(R.string.exit_confirm)
                        .setPositiveButton(R.string.dialog_text, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                SpUtil.save(Constants.CONS_USERNAME,"");
                                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                                HomeActivity.this.finish();

                            }
                        })
                        .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = mContext.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            nav_iv.setImageBitmap(bitmap);

            //uploadPic(bitmap);
        }

    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtil.saveFile(bitmap,"image.jpg");

       /* String imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));*/


        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
            mPresenter.uploadImg(imagePath);
        }
    }

    /**
     * 获取服务
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCheck = (ICheck) ((CheckService.LocalBinder) service).getService();
            if(iCheck != null && iCheck.isChecking()){
                iv_switch.setImageResource(R.mipmap.home_open);
                tv_status.setText(R.string.opened);
                isOpen = true;
            }else{
                iv_switch.setImageResource(R.mipmap.home_close);
                tv_status.setText(R.string.closed);
                isOpen = false;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iCheck = null;
        }
    };

}
