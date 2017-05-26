package com.soarsky.car.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soarsky.car.R;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能： 显示图片dialog
 * 该类为 自定义dialog
 */


public class ShowImgDialog extends Dialog{

    private Context context;
    /**
     * 图片显示控件
     */
    private ImageView showImg;

    public ShowImgDialog(Context context) {
        super(context,R.style.diolog_violation_address);
        this.context=context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_showimg);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据

    }

    /**
     * 初始化
     */
    private void initView() {
        showImg= (ImageView) findViewById(R.id.dialog_showimg_img);
    }


    /**
     * 设置图片路径
     * @param imgUrl 图片路径
     */
    public void  setImgUrl(String imgUrl){
        if(showImg!=null){
            Glide.with(context).load(imgUrl).into(showImg);
        }


    }
}
