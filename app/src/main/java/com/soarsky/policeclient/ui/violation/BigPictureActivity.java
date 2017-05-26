package com.soarsky.policeclient.ui.violation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.uitl.ImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  大图页面<br>
 */

public class BigPictureActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    /**
     * 返回按钮
     */
    private RelativeLayout violationBackLay;
    /**
     * 删除当前图片
     */
    private ImageView iv_delete;
    /**
     * ViewPager
     */
    private ViewPager pager;
    /**
     * viewpager的适配器
     */
    private PictureSlidePagerAdapter pagerAdapter;
    /**
     * 图片路径的集合
     */
    private ArrayList<String> list;

    private PopupWindow mPopWindow;
    /**
     * Viewpager的数据
     */
    private List<ImageView> imageViewList; //
    private Bitmap bm;
    /**
     * 总图片数
     */
    private TextView tv_total;
    /**
     * 当前第几张图片
     */
    private TextView tv_current;
    /**
     * 新位置
     */
    private int newPosition;
    /**
     * 当前位置
     */
    private int cPosition;


    @Override
    public int getLayoutId() {
        return R.layout.activity_big_picture;
    }

    @Override
    public void initView() {
        String type =getIntent().getStringExtra("type");
        violationBackLay = (RelativeLayout) findViewById(R.id.violationBackLay);
        violationBackLay.setOnClickListener(this);

        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_current = (TextView) findViewById(R.id.tv_current);

        tv_total.setText(list.size() +"");
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        if("2".equals(type)){
            iv_delete.setVisibility(View.GONE);
        }else {
            iv_delete.setVisibility(View.VISIBLE);
        }
        iv_delete.setOnClickListener(this);



        pager = (ViewPager) findViewById(R.id.viewpager);

        //初始化图片
        initData();

        //设置数据适配器
        pagerAdapter = new PictureSlidePagerAdapter();
        pagerAdapter.setImageViewList(imageViewList);
//        pagerAdapter.setCurrentItem(cPosition);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(cPosition,false);
        newPosition = cPosition ;
        tv_current.setText(cPosition+1+"");

        pager.setOnPageChangeListener(this);
    }

    /**
     * 初始化viewPager里的图片
     */
    private void initData() {
        if (list.size() <= 0 ){
            return;
        }
        imageViewList = new ArrayList<ImageView>();

        ImageView iv;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < list.size(); i++) {
            iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageUtil.loadImg(iv,list.get(i).toString());
            imageViewList.add(iv);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.violationBackLay:
                finish();
                break;
            case R.id.iv_delete:
                showPopupWindow();
                break;
            case R.id.sure_delete://删除图片
                File file = new File(list.get(newPosition));//文件路径
                file.delete();
                list.remove(newPosition);
                tv_total.setText(""+list.size());
                imageViewList.remove(newPosition);
                pagerAdapter.setImageViewList(imageViewList);
                mPopWindow.dismiss();
                if(newPosition == 0){
                    tv_current.setText(1+"");
                }else{
                    tv_current.setText(newPosition+"");
                    newPosition--;
                }

                if (list.size() == 0){
                    finish();
                }
                pager.setAdapter(pagerAdapter);
                pagerAdapter.notifyDataSetChanged();
                break;
            case R.id.pop_cancel:
                mPopWindow.dismiss();
                break;
        }
    }

    /**
     * 删除照片弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(BigPictureActivity.this).inflate(R.layout.layout_popup, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView)contentView.findViewById(R.id.sure_delete);
        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_cancel);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(BigPictureActivity.this).inflate(R.layout.activity_big_picture, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(pagerAdapter != null)
                pagerAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = intent.getStringArrayListExtra("pics");
        cPosition = bundle.getInt("position");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    /**
     * 当ViewPager页面被选中时, 触发此方法.
     *
     * @param position 当前被选中的页面的索引
     */
    @Override
    public void onPageSelected(int position) {
        newPosition = position;
        tv_current.setText(position+1+"");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
