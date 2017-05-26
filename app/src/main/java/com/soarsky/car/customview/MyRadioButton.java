package com.soarsky.car.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

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
 * 程序功能：拨打报警电话弹框
 * 该类为 自定义dialog
 */

public class MyRadioButton extends RadioButton {

	/**
	 * 图片大小
	 */
	private int mDrawableSize;

	public MyRadioButton(Context context) {
		this(context, null, 0);
	}

	public MyRadioButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.MyRadioButton);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			Log.i("MyRadioButton", "attr:" + attr);
			switch (attr) {
			case R.styleable.MyRadioButton_drawableSize:
				mDrawableSize = a.getDimensionPixelSize(R.styleable.MyRadioButton_drawableSize, 50);
				Log.i("MyRadioButton", "mDrawableSize:" + mDrawableSize);
				break;
			case R.styleable.MyRadioButton_drawableTop:
				drawableTop = a.getDrawable(attr);
				break;
			case R.styleable.MyRadioButton_drawableBottom:
				drawableRight = a.getDrawable(attr);
				break;
			case R.styleable.MyRadioButton_drawableRight:
				drawableBottom = a.getDrawable(attr);
				break;
			case R.styleable.MyRadioButton_drawableLeft:
				drawableLeft = a.getDrawable(attr);
				break;
			default :
					break;
			}
		}
		a.recycle();
		
		setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

	}

	/**
	 *
	 * Specify a bounding rectangle for the Drawable. This is where the drawable
	 * will draw when its draw() method is called.
	 *
	 * @param left 左
	 * @param top 上
	 * @param right 右
     * @param bottom 下
     */
	public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
			Drawable top, Drawable right, Drawable bottom) {

		if (left != null) {
			left.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		if (right != null) {
			right.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		if (top != null) {
			top.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		if (bottom != null) {
			bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		setCompoundDrawables(left, top, right, bottom);
	}

}
