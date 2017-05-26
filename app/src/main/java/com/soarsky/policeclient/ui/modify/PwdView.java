package com.soarsky.policeclient.ui.modify;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.uitl.SizeUtils;
import com.soarsky.policeclient.uitl.StringUtils;

import freemarker.template.utility.StringUtil;

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
 * 该类为  自定义可显示隐藏的密码输入框<br>
 */
public class PwdView extends RelativeLayout {
    /**
     * 密码输入框
     */
    private EditText editText;
    /**
     * 是否可见
     */
    private boolean open = true;
    public PwdView(Context context) {
        this(context, null);
    }

    public PwdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PwdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context,R.layout.rl_pwd_imageview,this);
        View.inflate(context,R.layout.rl_pwd_edittext,this);
        final ImageView imageView = (ImageView) getChildAt(0);
        editText = (EditText) getChildAt(1);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdSwitch(imageView,editText);
            }
        });
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.pwdHint, defStyleAttr, 0);
        String hint = attributes.getString(R.styleable.pwdHint_hint);
        if(!StringUtils.isEmpty(hint)){
            editText.setHint(hint);
        }

    }

    /**
     * 切换可见和不可见
     * @param imageView 图标
     * @param editText 输入框
     */
    private void pwdSwitch(ImageView imageView, EditText editText){
        if(open){
            open = false;
            imageView.setImageResource(R.mipmap.see_y);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else {
            open = true;
            imageView.setImageResource(R.mipmap.see_n);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public String getEditTextString(){
        return editText.getText().toString();
    }

}
