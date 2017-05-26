/**
 * Description:
 * User: ZhengXingtian(lan4627@Gmail.com)
 * Date: 2015-03-31
 * Time: 23:55
 * Version: 1.0
 */
package com.soarsky.policeclient.ui.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

import com.soarsky.policeclient.R;

import java.lang.reflect.Field;
/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  未使用 <br>
 */
public class StatusBarColorLayout extends FrameLayout {

 public StatusBarColorLayout(Context context) {
  super(context);
  init(context);
 }

 public StatusBarColorLayout(Context context, AttributeSet attrs) {
  super(context, attrs);
  init(context);
 }

 public StatusBarColorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
  super(context, attrs, defStyleAttr);
  init(context);
 }

 @TargetApi(Build.VERSION_CODES.LOLLIPOP)
 public StatusBarColorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
  super(context, attrs, defStyleAttr, defStyleRes);
  init(context);
 }

 private Rect mStatusBarRect;
 private Paint mStatusBarColorPaint;
 private int mStatusBarHeight;

 private void init(Context context){
  // 让ToolBar处于系统状态栏下方
  setFitsSystemWindows(true);
  // 设置画笔
  mStatusBarColorPaint = new Paint();
  mStatusBarColorPaint.setColor(getThemeColor(context, R.attr.colorPrimary));
  mStatusBarColorPaint.setAntiAlias(true);
  mStatusBarColorPaint.setStyle(Paint.Style.FILL);

  mStatusBarRect = new Rect();

  mStatusBarHeight = getStatusBarHeight(context);
 }

 @Override
 protected void dispatchDraw(Canvas canvas) {
  // 要绘制的区域
  mStatusBarRect.set(getLeft(), getTop(), getRight(), mStatusBarHeight);
  // 绘制系统状态栏颜色
  canvas.drawRect(mStatusBarRect, mStatusBarColorPaint);
  super.dispatchDraw(canvas);
 }

 /**
  *  获取系统状态栏高度
  * @param context
  * @return
  */
 public int getStatusBarHeight(Context context) {
  Class<?> c = null;
  Object obj = null;
  Field field = null;
  int x = 0, statusBarHeight = 0;
  try {
   c = Class.forName("com.android.internal.R$dimen");
   obj = c.newInstance();
   field = c.getField("status_bar_height");
   x = Integer.parseInt(field.get(obj).toString());
   statusBarHeight = context.getResources().getDimensionPixelSize(x);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return statusBarHeight;
 }

 /**
  *  获取当前主题里的颜色
  * @param context
  * @param resId
  * @return
  */
 public int getThemeColor(Context context, int resId){
  TypedValue value = new TypedValue();
  context.getTheme().resolveAttribute(resId, value, true);
  return value.data;
 }
}