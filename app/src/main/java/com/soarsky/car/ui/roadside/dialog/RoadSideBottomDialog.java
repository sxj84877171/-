package com.soarsky.car.ui.roadside.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.roadside.RoadSideDialogListener;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：道路救援dialog<br>
 * 该类为 RoadSideBottomDialog<br>
 */


public class RoadSideBottomDialog extends Dialog{
    /**
     * 其构造函数
     * @param context 文本
     * @param themeResId 主题
     */
    public RoadSideBottomDialog(Context context, int themeResId) {
        super(context,themeResId);
    }

    public static class Builder implements View.OnClickListener{
        /**
         * 上下文本
         */
        public Context context;
        /**
         * 标题
         */
        public TextView dialogTopicTv;
        /**
         * 确定
         */
        private TextView dialogRoadSideConfirmTv;
        /**
         * 列表
         */
        private ListView dialogRoadSideListview;
        /**
         * 宽度
         */
        private int dialogWidth;
        /**
         * 高度
         */
        private int dialogHeight;
        /**
         * 回调监听
         */
        private RoadSideDialogListener listener;

        View layout;
        /**
         * 数据源
         */
        private List<String> list;

        /**
         * 选择的数据
         */
        private String selectData = null;
        /**
         * 类型 0--代表车辆类型 1--代表服务商类型
         */
        private int type;


        public Builder(Context context, int dialogWidth, int dialogHeight,RoadSideDialogListener listener,List<String> list,int type) {
            this.context = context;
            this.dialogWidth = dialogWidth;
            this.dialogHeight = dialogHeight;
            this.listener = listener;
            this.list = list;
            this.type = type;
        }

        public RoadSideBottomDialog create(){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RoadSideBottomDialog dialog = new RoadSideBottomDialog(context,R.style.diolog_violation_address);
            layout = inflater.inflate(R.layout.dialog_road_side_bottom,null);
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
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

        private void initView(){

            dialogTopicTv = (TextView) layout.findViewById(R.id.dialogTopicTv);
            if(type == 0){
                dialogTopicTv.setText(context.getString(R.string.dialogroadsidecartype));
            }else if(type == 1){
                dialogTopicTv.setText(context.getString(R.string.dialogroadsidesever));
            }

            dialogRoadSideConfirmTv = (TextView) layout.findViewById(R.id.dialogRoadSideConfirmTv);
            dialogRoadSideConfirmTv.setOnClickListener(this);

            dialogRoadSideListview = (ListView) layout.findViewById(R.id.dialogRoadSideListview);
            dialogRoadSideListview.setAdapter(new RoadSideBottomAdapter(context,list));
            dialogRoadSideListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    selectData = list.get(i).toString();
                }
            });

        }


        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.dialogRoadSideConfirmTv:
                    if (selectData != null) {
                        listener.confirmClick(selectData);
                    }else {
                        ToastUtil.show(context,R.string.dialogroadsidetip);
                    }
                    break;
            }
        }
    }




}
