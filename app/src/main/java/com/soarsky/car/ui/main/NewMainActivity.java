package com.soarsky.car.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.callphone.PermissionCheck;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

public class NewMainActivity extends BaseActivity {
    /**
     *切换的viewgroup
     */
    private ViewPager mViewPager;
    /**
     * viewpager的适配
     */
    private NewFragmentPagerAdapter fragmentPagerAdapter;
    /**
     * 下列表展示
     */
    private TabLayout mTablayout;

    private final static String TAG = "NewMainActivity";
    private App app;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_main;
    }

    @Override
    public void initView() {


        app = (App) getApplication();
        app.addActivity(TAG, this);
        app.setActivity(this);
        SpUtil.init(this);
        if (TextUtils.isEmpty(SpUtil.get("volume"))) {
            SpUtil.save("volume", "5");
        }
        showSettingPermissionDialog();

        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentPagerAdapter = new NewFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mTablayout.setupWithViewPager(mViewPager);


        for (int i = 0;   i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(fragmentPagerAdapter.getTabView(i));
        }
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    private void showSettingPermissionDialog() {
//
//        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT){
//          return;
//        }

        if (!PermissionCheck.HasACTION_USAGE_ACCESS_SETTINGSPermission(this)) {
            new AlertDialog.Builder(mContext).setMessage(getString(R.string.open_uses_permission))
                    .setPositiveButton(getString(R.string.familynumbersure), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“确认”后的操作
                            startACTION_USAGE_ACCESS_SETTINGSPermissionActivity();
                        }
                    })
                    .setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“返回”后的操作,这里不设置没有任何操作
                        }
                    }).show();
        }

    }
    /**
     * 打开权限界面
     */
    public void startACTION_USAGE_ACCESS_SETTINGSPermissionActivity() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);    //通过尝试这个flag符合
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }{
            ToastUtil.show(this,R.string.un_get_permission);
        }
    }

    @Override
    public void onBackPressed() {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
    }

}
