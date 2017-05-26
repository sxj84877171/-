package com.soarsky.car.ui.main;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/15<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为音量设置界面<br>
 */

public class VoiceSettingActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回按钮
     */
    private LinearLayout illegalBackLay;
    /**
     * 标题
     */
    private TextView topicTv;
    private ConfirmDriverService confirmDriverService;
    private SeekBar seekBar;

    int voice = 3;
    private Handler myHandler = new Handler();
    @Override
    public int getLayoutId() {
        return R.layout.voice_setting3;
    }

    @Override
    public void initView() {

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.voice_setting));

        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(Integer.parseInt(SpUtil.get("volume")));


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress < 3) {
                    seekBar.setProgress(3);
                    ToastUtil.show(VoiceSettingActivity.this, getString(R.string.voice_no_smaller));
                }
                voice = progress;
                SpUtil.save("volume", String.valueOf(voice));
                myHandler.removeCallbacks(excuSendVolume);
                myHandler.postDelayed(excuSendVolume, 500);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                LogUtil.i("volume" + voice);
            }
        });
    }

    private Runnable excuSendVolume = new Runnable() {
        @Override
        public void run() {
            if (confirmDriverService != null){
                confirmDriverService.setVolume();
            }
        }
    };

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.illegalBackLay:
                finish();
                break;
        }
    }
}
