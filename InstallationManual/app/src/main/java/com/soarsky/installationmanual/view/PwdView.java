package com.soarsky.installationmanual.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.util.StringUtils;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 密码眼自定义view 未使用<br>
 */

public class PwdView extends RelativeLayout {

    private EditText editText;
    private boolean open = true;
    public PwdView(Context context) {
        this(context, null);
    }

    public PwdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PwdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.rl_pwd_imageview,this);
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

    private void pwdSwitch(ImageView imageView, EditText editText){
        if(open){
            open = false;
            imageView.setImageResource(R.drawable.see_y);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else {
            open = true;
            imageView.setImageResource(R.drawable.see_n);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public String getEditTextString(){
        return editText.getText().toString();
    }

}
