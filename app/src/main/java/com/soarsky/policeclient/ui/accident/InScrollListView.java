package com.soarsky.policeclient.ui.accident;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为解决listview在scrollview中滑动冲突<br>
 */
public class InScrollListView extends ListView {
 
    public InScrollListView(Context context) {
        super(context);
    }
     
    public InScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
     
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}