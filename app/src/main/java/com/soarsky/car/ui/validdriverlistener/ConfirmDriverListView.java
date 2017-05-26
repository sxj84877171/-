package com.soarsky.car.ui.validdriverlistener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.R;


/**
 * Created by 王松青 on 2016/11/29.
 */

public class ConfirmDriverListView extends ListView implements OnScrollListener {
    private final static int RELEASE_TO_REFRESH = 0;
    private final static int PULL_TO_REFRESH = 1;
    //正在刷新
    private final static int REFRESHING = 2;
    //刷新完成
    private final static int DONE = 3;
    private final static int LOADING = 4;

    private final static int RADIO = 3;

    private LayoutInflater mInflater;
    private ImageView head_refreshing;
    private RelativeLayout mHeadView;
    private TextView mTipsTextView;
    //private TextView mLastUpdatedTextView;
    private ImageView mArrowImageView;
    //private ProgressBar mProgressBar;

    private RotateAnimation mAnimation;
    private RotateAnimation mReverseAnimation;
    private boolean mIsRecored;
    private int mHeadContentWidth;
    private int mHeadContentHeight;
    private int mStartY;
    private int mFirstItemIndex;
    private int mState;
    private boolean mIsBack;
    private boolean mISRefreshable;
    private OnRefreshListener mRefreshListener;

    public ConfirmDriverListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //        setCacheColorHint(android.R.color.black);
        mInflater = LayoutInflater.from(context);
        mHeadView = (RelativeLayout) mInflater.inflate(R.layout.header, null);
        mArrowImageView = (ImageView) mHeadView.findViewById(R.id.head_arrowImageView);
        head_refreshing = (ImageView) mHeadView.findViewById(R.id.head_refreshing);
        //        mArrowImageView.setMinimumWidth(70);
        //        mArrowImageView.setMinimumHeight(50);
        //mProgressBar = (ProgressBar) mHeadView.findViewById(R.id.head_progressBar);
        mTipsTextView = (TextView) mHeadView.findViewById(R.id.head_tipsTextView);
        //mLastUpdatedTextView = (TextView) mHeadView.findViewById(R.id.head_lastUpdatedTextView);

        measureView(mHeadView);
        mHeadContentHeight = mHeadView.getMeasuredHeight();

        mHeadContentWidth = mHeadView.getMeasuredWidth();

        mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
        mHeadView.invalidate();
        addHeaderView(mHeadView, null, false);
        setOnScrollListener(this);

               /* mAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                mAnimation.setInterpolator(new LinearInterpolator());
                mAnimation.setDuration(250);
                mAnimation.setFillAfter(true);

                mReverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                mReverseAnimation.setInterpolator(new LinearInterpolator());
                mReverseAnimation.setDuration(250);
                mReverseAnimation.setFillAfter(true);*/

        mState = DONE;
        mISRefreshable = false;
    }

    private void measureView(View child) {
        android.view.ViewGroup.LayoutParams params = child.getLayoutParams();
        System.out.println("params = " + params);
        if (params == null) {
            params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        System.out.println("lpWidth = " + params.width);
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, params.width);
        System.out.println("childWidthSpec = " + childWidthSpec);
        int lpHeight = params.height;
        System.out.println("lpHeight = " + lpHeight);
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.UNSPECIFIED);
        }
        System.out.println("childHeightSpec = " + childHeightSpec);
        child.measure(childWidthSpec, childHeightSpec);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        mFirstItemIndex = firstVisibleItem;
    }

    public interface OnRefreshListener {
        public void onRefresh();
    }

    private void onRefresh() {
        if (mRefreshListener != null) {
            mRefreshListener.onRefresh();
        }
    }

    public void onRefreshComplete() {
        mState = DONE;
        /* mLastUpdatedTextView.setText("已查找完成：" + new Date().toLocaleString());
              changeHeaderViewByState();*/
        changeHeaderViewByState();
    }

    public void setonRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        mISRefreshable = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mISRefreshable) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mFirstItemIndex == 0 && !mIsRecored) {
                        mIsRecored = true;
                        mStartY = (int) ev.getY();
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    if (mState != REFRESHING && mState != LOADING) {
                        if (mState == DONE) {

                        }
                        if (mState == PULL_TO_REFRESH) {
                            mState = DONE;
                            changeHeaderViewByState();
                        }
                        if (mState == RELEASE_TO_REFRESH) {
                            mState = REFRESHING;
                            changeHeaderViewByState();
                            onRefresh();
                        }
                    }
                    mIsBack = false;
                    mIsRecored = false;
                    break;

                case MotionEvent.ACTION_MOVE:
                    int tempY = (int) ev.getY();
                    if (!mIsRecored && mFirstItemIndex == 0) {
                        mIsRecored = true;
                        mStartY = tempY;
                    }
                    if (mState != REFRESHING && mIsRecored && mState != LOADING) {
                        if (mState == RELEASE_TO_REFRESH) {
                            setSelection(0);
                            if ((tempY - mStartY) / RADIO < mHeadContentHeight && (tempY - mStartY) > 0) {
                                mState = PULL_TO_REFRESH;
                                changeHeaderViewByState();
                            } else if (tempY - mStartY <= 0) {
                                mState = DONE;
                                changeHeaderViewByState();
                            }
                        }

                        if (mState == PULL_TO_REFRESH) {
                            setSelection(0);
                            if ((tempY - mStartY) / RADIO >= mHeadContentHeight) {
                                mState = RELEASE_TO_REFRESH;
                                mIsBack = true;
                                changeHeaderViewByState();
                            }
                        } else if (tempY - mStartY <= 0) {
                            mState = DONE;
                            changeHeaderViewByState();
                        }

                        if (mState == DONE) {
                            if (tempY - mStartY > 0) {
                                mState = PULL_TO_REFRESH;
                                changeHeaderViewByState();
                            }
                        }

                        if (mState == PULL_TO_REFRESH) {
                            mHeadView.setPadding(0, -1 * mHeadContentHeight + (tempY - mStartY) / RADIO, 0, 0);
                        }

                        if (mState == RELEASE_TO_REFRESH) {
                            mHeadView.setPadding(0, (tempY - mStartY) / RADIO - mHeadContentHeight, 0, 0);
                        }
                    }
                    break;

                default:
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void changeHeaderViewByState() {
        switch (mState) {
            case PULL_TO_REFRESH:
                head_refreshing.setVisibility(GONE);
                mTipsTextView.setVisibility(VISIBLE);
                //mLastUpdatedTextView.setVisibility(VISIBLE);
                //mArrowImageView.clearAnimation();
                mArrowImageView.setVisibility(VISIBLE);
         /*if(mIsBack) {
                 mIsBack = false;
                 //mArrowImageView.clearAnimation();
                 //mArrowImageView.startAnimation(mReverseAnimation);
                 mTipsTextView.setText("isBack is true!!!");
             } else {
                 mTipsTextView.setText("isBack is false!!!");
             }*/
                break;

            case DONE:
                mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
                // mProgressBar.setVisibility(GONE);
                //mArrowImageView.clearAnimation();
                mArrowImageView.setVisibility(VISIBLE);
                head_refreshing.setVisibility(GONE);
                mTipsTextView.setText("下拉刷新");
                //mLastUpdatedTextView.setVisibility(VISIBLE);
                break;

            case REFRESHING:
                mHeadView.setPadding(0, 0, 0, 0);
                head_refreshing.setVisibility(VISIBLE);
                //mArrowImageView.clearAnimation();
                mArrowImageView.setVisibility(GONE);

                mTipsTextView.setText("正在刷新");
                break;

            case RELEASE_TO_REFRESH:
                mArrowImageView.setVisibility(VISIBLE);
                head_refreshing.setVisibility(GONE);
                mTipsTextView.setVisibility(VISIBLE);
                //mLastUpdatedTextView.setVisibility(VISIBLE);
                //mArrowImageView.clearAnimation();
                //mArrowImageView.startAnimation(mAnimation);
                mTipsTextView.setText("下拉刷新");
                break;
            default:
                break;
        }
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        //mLastUpdatedTextView.setText("即将查找:" + new Date().toLocaleString());
        super.setAdapter(adapter);
    }
}
