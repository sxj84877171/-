package com.soarsky.car.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.base.RxBus;
import com.soarsky.car.base.RxManager;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.home.view.CheckVersion;
import com.soarsky.car.uitl.DeviceUtils;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.functions.Action1;

import static com.soarsky.car.base.BasePresenter.CHECK_VERSION;


/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 首页界面
 */
public class HomeActivity extends BaseActivity<HomePresent,HomeModel> implements HomeView, View.OnClickListener {

    /**
     *切换的viewgroup
     */
    private ViewPager mViewPager;
    /**
     * viewpager的适配
     */
    private SimpleFragmentPagerAdapter fragmentPagerAdapter;
    /**
     * 下列表展示
     */
    private TabLayout mTablayout;

    public final static String TAG = "HomeActivity";

    private App app;


    Intent serviceIntent;
    private String path;
    private File futureStudioIconFile;
    private CheckVersion check;

    private static final int RESULT_OK = 200;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        addView(R.layout.maintoolbarleft);
        addView(R.layout.maintoolbarright);
        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTablayout.setupWithViewPager(mViewPager);


        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(fragmentPagerAdapter.getTabView(i));
        }


    }


    @Override
    protected String getHeaderTitle() {

        String  title =getString(R.string.home_topic);

        return  title;
    }

    private RxManager rxManager=new RxManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starservice();
        SpUtil.init(app);
        if(TextUtils.isEmpty(SpUtil.get("volume")) ){
            SpUtil.save("volume","05");
        }


        RxBus.$().event(Constants.DRIVE_CARNUM_ACTION, new Action1<Object>() {
            public void call(Object carNum){
                LogUtil.i(TAG+carNum);



            }
        });
        mPresenter.checkVersion(CHECK_VERSION);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }





    @Override
    public void checkSuccess(CheckVersion checkVersion) {
        check = checkVersion;
        path = checkVersion.getData().getFileurl();
        String versionName = DeviceUtils.getVersionName(HomeActivity.this);
        if (DeviceUtils.compareVersion(versionName,checkVersion.getData().getVersion())){
            showUpdateDialog(getString(R.string.update_version));
        }else {
            //ToastUtil.show(HomeActivity.this,R.string.newest);
        }
    }

    @Override
    public void checkFail(CheckVersion checkVersion) {
        //ToastUtil.show(HomeActivity.this, R.string.get_fail+checkVersion.getMessage());
    }

    @Override
    public void showError(Throwable e) {
        //ToastUtil.show(HomeActivity.this, getString(R.string.error_msg)+e.getMessage());
    }

    @Override
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.isSuccessful()) {
            writeResponseBodyToDisk(response.body());
        }
    }

    @Override
    public void loadFail(Call<ResponseBody> call, Throwable t) {

        ToastUtil.show(HomeActivity.this,R.string.load_fail);
    }


    @Override
    public void showProgess() {

        super.showProgess();
    }

    @Override
    public void stopProgess() {

        super.stopProgess();
    }

    @Override
    public void onClick(View view) {

        /*switch (view.getId()){

            case R.id.closeLay:
                finish();
                break;
            case R.id.homeModifyBtn:
                Intent i = new Intent();
                i.setClass(this, ModifyPwdActivity.class);
                startActivity(i);
                break;
            case R.id.homeBackBtn:

                ToastUtil.show(this,"homeBackBtn");


                break;
            case R.id.homedivBtn:

                ToastUtil.show(this,"homedivBtn");
                break;
            case R.id.homeloginTv:
                if(isLogin != 1) {
                    Intent in = new Intent();
                    in.setClass(this, LoginActivity.class);
                    startActivity(in);
                }else {
//                    退出账号
//                    finish();

                    app.exit();
                }
                break;
        }*/
    }






    @Override
    protected void onDestroy() {
        rxManager.clear();
        if(serviceIntent!=null){
            stopService(serviceIntent);
        }
        super.onDestroy();
    }

    //测试用的
    private void starservice(){
        serviceIntent=new Intent(this, ConfirmDriverService.class);
        startService(serviceIntent);

    }
    /**
     * 显示自动更新的对话框
     *
     * @param desc
     *            描述
     */
    protected void showUpdateDialog(String desc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.update);
        builder.setMessage(desc.toString());
        builder.setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //下载文件
               mPresenter.loadFile(path);

            }
        });
        builder.setNegativeButton(R.string.update_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        builder.show();
    }

    private void writeResponseBodyToDisk(final ResponseBody body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // todo change the file location/name according to your needs
                    futureStudioIconFile = new File("/sdcard/"+check.getData().getName()+".apk");

                    InputStream inputStream = null;
                    OutputStream outputStream = null;

                    try {
                        byte[] fileReader = new byte[4096];

                        long fileSize = body.contentLength();
                        long fileSizeDownloaded = 0;

                        inputStream = body.byteStream();

                        outputStream = new FileOutputStream(futureStudioIconFile);

                        while (true) {
                            int read = inputStream.read(fileReader);

                            if (read == -1) {
                                break;
                            }

                            outputStream.write(fileReader, 0, read);
                            outputStream.flush();
                            fileSizeDownloaded += read;

                            Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(HomeActivity.this, R.string.load_success);
                                // 覆盖安装apk文件
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.setDataAndType(
                                        Uri.fromFile(futureStudioIconFile),
                                        "application/vnd.android.package-archive");
                                startActivity(intent);
                            }
                        });
                        return ;
                    } catch (IOException e) {
                        return ;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }

                        if (outputStream != null) {
                            outputStream.close();
                        }
                    }
                } catch (IOException e) {
                    return ;
                }
            }
        }).start();

    }
}
