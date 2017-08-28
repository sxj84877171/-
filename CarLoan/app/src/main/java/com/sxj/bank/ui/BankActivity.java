package com.sxj.bank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sxj.bank.BaseActivity;
import com.sxj.bank.R;
import com.sxj.bank.rongyi.VideoChatViewActivity;
import com.sxj.bank.rongyi.VideoListActivity;
import com.sxj.bank.util.LogUtil;

public class BankActivity extends BaseActivity {

    private TextView welcome_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        welcome_msg = getViewById(R.id.welcome_msg);
        welcome_msg.setText(getUsername() + ", 欢迎您！\r\n     " + "欢迎来到银行融宜面签管理系统，如果进入工作时间，点击页面进入工作。");
    }

    public void entryRonyimianQian(View view) {
        Intent intent = new Intent(this, VideoChatViewActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "查看视频");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 1) {
            Intent intent = new Intent(this, VideoListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
