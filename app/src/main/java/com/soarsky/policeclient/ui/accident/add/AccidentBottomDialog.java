package com.soarsky.policeclient.ui.accident.add;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.uitl.ToastUtil;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析模块底部对话框<br>
 */

public class AccidentBottomDialog extends Dialog {

    public AccidentBottomDialog(Context context) {
        super(context);
    }

    public AccidentBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    /**
     * 对话框构建类
     */
    public static class Builder implements View.OnClickListener {
        /**
         * 上下文
         */
        private Context context;
        /**
         * 确认textview
         */
        private TextView cofoirmTv;
        /**
         * listview列表
         */
        private ListView listView;
        /**
         * 选择的车辆
         */
        private TextView selectCarTv;
        /**
         * 对话框宽度
         */
        private int dialogWidth;
        /**
         * 对话框高度
         */
        private int dialogHeight;
        /**
         * 对话框选择之后的回调
         */
        private AccidentBottomDialogListener listener;
        /**
         * 对话框布局view
         */
        View layout;
        /**
         * listview对应的数据
         */
        private List<String> list;
        /**
         * 标题
         */
        private String title;
        /**
         * 选中的item
         */
        private String selectedItem;



        public Builder(Context context, int dialogWidth, int dialogHeight, AccidentBottomDialogListener listener, List<String> list,String title) {
            this.context = context;
            this.dialogWidth = dialogWidth;
            this.title = title;
            this.dialogHeight = dialogHeight;
            this.listener = listener;
            this.list = list;
        }


        /**
         * 对话框的创建方法
         * @return 创建的对话框
         */
        public AccidentBottomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final AccidentBottomDialog dialog = new AccidentBottomDialog(context, R.style.diolog_violation_address);
            layout = inflater.inflate(R.layout.dialog_accident_buttom, null);
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);

            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);

            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = dialogWidth;
            lp.height = dialogHeight;
            dialogWindow.setAttributes(lp);
            initView();
            return dialog;

        }

        /**
         * 初始化视图
         */
        private void initView() {
            cofoirmTv = (TextView) layout.findViewById(R.id.dialog_violationbottom_confirm);
            cofoirmTv.setOnClickListener(this);
            listView = (ListView) layout.findViewById(R.id.dialog_violationbottom_listview);
            selectCarTv = (TextView) layout.findViewById(R.id.selectCarTv);
            selectCarTv.setText(title);
            listView.setAdapter(new AccidentReasonAdapter(context,list));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedItem = list.get(position).toString();
                    selectCarTv.setText(list.get(position).toString());
                    ColorStateList csl=(ColorStateList)context.getResources().getColorStateList(R.color.dialog_b_tv);
                    selectCarTv.setTextColor(csl);
                }
            });

        }



        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.dialog_violationbottom_confirm:

                    if(!context.getString(R.string.dialog_b_chice).equals(selectedItem)) {
                        listener.confirmClick(selectedItem);
                    }else {
                        ToastUtil.show(context,context.getString(R.string.dialog_b_chice));
                    }
                    break;

            }
        }
    }
}
