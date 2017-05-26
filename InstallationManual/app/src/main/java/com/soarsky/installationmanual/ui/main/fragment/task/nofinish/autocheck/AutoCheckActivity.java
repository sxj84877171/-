package com.soarsky.installationmanual.ui.main.fragment.task.nofinish.autocheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.imageupload.ImageUploadActivity;


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
 * 该类为 自动检查界面<br>
 */

public class AutoCheckActivity extends BaseActivity<AutoCheckPresent,AutoCheckModel> implements AutoCheckView {

    private ImageView back;
    private ImageView finish;
    @Override
    public int getLayoutId() {
        return R.layout.activity_auto_check;
    }

    @Override
    public void initView() {
        addView(R.layout.back_left_toolbar);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        back = (ImageView) findViewById(R.id.backBtn);
        finish = (ImageView) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AutoCheckActivity.this).setTitle("湘A12345成功安装智能终端请拍照上传").setIcon(R.drawable.okdialog).setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(AutoCheckActivity.this, ImageUploadActivity.class));
                            }
                        }).show();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AutoCheckActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void showSuccess() {

    }
}
