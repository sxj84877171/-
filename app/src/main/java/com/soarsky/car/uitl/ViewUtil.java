package com.soarsky.car.uitl;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.soarsky.car.R;

import java.lang.reflect.Field;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 2016/10/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * View工具类
 */
public class ViewUtil {

	private ViewUtil(){
		throw  new UnsupportedOperationException("No Impl");
	}

	/**
	 * activity自动findview
	 */
	public static void autoFind(Activity activity) {
		try {
			Class<?> clazz = activity.getClass();
			Field[] fields = clazz.getDeclaredFields();// 获得Activity中声明的字段
			for (Field field : fields) {
				if (field.getGenericType().toString().contains("widget")
						|| field.getGenericType().toString().contains("view")
						|| field.getGenericType().toString()
								.contains("WebView")) {// 找到所有的view和widget,WebView
					try {
						String name = field.getName();
						Field idfield = R.id.class.getField(name);
						int id = idfield.getInt(new R.id());// 获得view的id
						field.setAccessible(true);
						field.set(activity, activity.findViewById(id));// 给我们要找的字段设置值
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fragment以及ViewHolder等自动findview
	 */

	public static void autoFind(Object obj, View view) {
		try {
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();// 获得Activity中声明的字段
			for (Field field : fields) {
				if (field.getGenericType().toString().contains("widget")
						|| field.getGenericType().toString().contains("view")
						|| field.getGenericType().toString()
								.contains("WebView")) {// 找到所有的view和widget
					try {
						String name = field.getName();
						Field idfield = R.id.class.getField(name);
						int id = idfield.getInt(new R.id());
						field.setAccessible(true);
						field.set(obj, view.findViewById(id));// 给我们要找的字段设置值
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 隐藏软键盘
	 */
	public static void hideKeyboard(Activity c) {
		try {
			InputMethodManager imm = (InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(c.getCurrentFocus().getWindowToken(), 0);
		} catch (NullPointerException e) {
		}
	}
}
