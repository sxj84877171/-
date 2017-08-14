package com.sxj.bank.ui.investigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sxj.bank.BaseActivity;
import com.sxj.bank.R;
import com.sxj.bank.rongyi.VideoChatViewActivity;
import com.sxj.bank.rongyi.VideoListActivity;

/**
 * Created by admin on 2017/8/11.
 */

public class InvestigationFunctionChoose extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_function_investigation);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
            menu.add(1, 1, 1, "查看面签视频");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if(item.getItemId() == 1){
            Intent intent = new Intent(this, VideoListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void rongYiMianQian(View view){
        Intent intent = new Intent(this, VideoChatViewActivity.class);
        startActivity(intent);
    }

    public void modifyInfo(View view){
        toast("功能在努力开发中……");
    }

    public void addMoreInfo(View view){
        toast("功能在努力开发中……");
    }

    public void uploadFamilyPhoto(View view){
        toast("功能在努力开发中……");
    }

    public void uploadWorkAddressPhoto(View view){
        toast("功能在努力开发中……");
    }

    public void uploadBankPhoto(View view){
        toast("功能在努力开发中……");
    }

    public void uploadChanPhoto(View view){
        toast("功能在努力开发中……");
    }

    public void uploadIdPhoto(View view){
        toast("功能在努力开发中……");
    }

    public void calc(View view){
        toast("功能在努力开发中……");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
