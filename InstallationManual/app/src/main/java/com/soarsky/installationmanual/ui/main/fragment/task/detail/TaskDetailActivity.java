package com.soarsky.installationmanual.ui.main.fragment.task.detail;

import android.widget.TextView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;

/**
 * InstallationManual<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/27<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 任务明细界面<br>
 */


public class TaskDetailActivity extends BaseActivity<TaskDetailPresent,TaskDetailModel> implements TaskDetailView{

    /**
     * 本周任务数
     */
    private TextView taskDetailWeekTv;
    /**
     * 本月任务数
     */
    private TextView taskDetailMonthTv;
    /**
     * 今日任务数
     */
    private TextView taskDetailDayTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_taskdetail;
    }

    @Override
    public void initView() {

        taskDetailWeekTv = (TextView) findViewById(R.id.taskDetailWeekTv);

        taskDetailMonthTv = (TextView) findViewById(R.id.taskDetailMonthTv);

        taskDetailDayTv = (TextView) findViewById(R.id.taskDetailDayTv);

    }

    @Override
    protected String getHeaderTitle() {
        return "任务明细";
    }

    @Override
    public void showSuccess() {

    }
}
