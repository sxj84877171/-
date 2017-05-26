package com.soarsky.car.ui.newfamilynum.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class MyScrollView extends ViewGroup {



    private GestureDetector detector;
    private Context ctx;

    //	private MyScroller myScroller;
    private Scroller myScroller;
    private int curIndex;
    /**
     * 是否是飞行状态
     */
    protected boolean isFling = false;

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyScrollView(Context context) {
        super(context);
        ctx = context;
        init();
    }

    private IScrollListener scrollListener;

    /**
     * 初始化
     * leo 2013-7-23
     */
    private void init() {
        myScroller = new Scroller(ctx);
        detector = new GestureDetector(ctx	, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                    float distanceY) {
                scrollBy((int)distanceX, 0);
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {

                isFling = true;
                if(velocityX>0 && curIndex>0){
                    moveToDest(curIndex - 1);
                }else if(velocityX<0 && curIndex<getChildCount()-1){
                    moveToDest(curIndex+1);
                }else{
                    moveToDest();
                }

                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        MeasureSpec.getMode(widthMeasureSpec);
        MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if(i == 0) {
                v.measure(widthMeasureSpec, heightMeasureSpec);
            } else {
                v.measure(40, heightMeasureSpec);
            }
        }

    }

    @Override
    /**
     * 给每一个子view设置位置
     */
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < getChildCount(); i++) {
            View v= getChildAt(i);
            if(i == 0) {
                v.layout(0, 0, getWidth(), getHeight());
            } else {
                v.layout(getWidth(), 0, getWidth()+40, getHeight());
            }

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.dispatchTouchEvent(ev);
    }


    private int mX;
    private int mY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean result = false;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = (int) ev.getX();
                mY = (int) ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                int curX = (int) ev.getX();
                int curY = (int) ev.getY();

                int disX = Math.abs(curX-mX);
                int disY = Math.abs(curY-mY);

                if(disX>disY && disX >10){
                    result = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                result = false;

                break;

            default:
                break;
        }


        return result;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if(!isFling){
                    moveToDest();
                }
                isFling = false;
                break;

            default:
                break;
        }

        return true;
    }

    /**
     * 移动到适合的位置上
     * leo 2013-7-23
     */
    private void moveToDest() {

        int destId = (getScrollX()+getWidth()/2)/getWidth();

        moveToDest(destId);
    }

    /**
     * 移动到指定的位置上
     * leo 2013-7-23
     * @param destId
     */
    public void moveToDest(int destId) {

        if(scrollListener!=null){
            scrollListener.moveToDest(destId);
        }

        curIndex = destId;
        int distance = destId*getWidth() - getScrollX();

//		scrollBy(distance, 0);
        myScroller.startScroll(getScrollX(), getScrollY(), distance, 0,Math.abs(distance));
        invalidate();
    }


    @Override
    public void computeScroll() {
        if(myScroller.computeScrollOffset()){
            int x = myScroller.getCurrX();
            scrollTo(x, 0);
            invalidate();
        }
    }

    public IScrollListener getScrollListener() {
        return scrollListener;
    }

    public void setScrollListener(IScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public interface IScrollListener{
        void moveToDest(int id);
    }




}
