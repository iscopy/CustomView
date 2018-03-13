package com.win.customview.bomb_box;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.win.customview.R;

/**
 * Created by Administrator on 2018/3/13 0013.
 * 自定义弹框适配器
 */
                                //继承
public class MyBombBoxAdapter extends Dialog {

    public MyBombBoxAdapter(Context context) {
        super(context);
    }

    public MyBombBoxAdapter(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;        //上下文
        private String title;           //标题
        private String content;         //弹框内容
        private String left;            //左按钮
        private String right;           //右按钮
        private View contentView;      //视图
        private DialogInterface.OnClickListener leftClick;      //左按钮点击事件
        private DialogInterface.OnClickListener rightClick;     //右按钮点击事件

        public Builder(Context context) { //传入上下文（弹框在哪里弹出）
            this.context = context;
        }

        public Builder setTitle(String title) { //传入标题
            this.title = title;
            return this;
        }

        public Builder setContent(String content) { //传入弹框提示文字
            this.content = content;
            return this;
        }

        public Builder setLeft(String left, DialogInterface.OnClickListener listener) {
            this.left = left;                       //传入左边按钮文字
            this.leftClick = listener;             //传入左边按钮点击事件
            return this;
        }

        public Builder setRight(String right, DialogInterface.OnClickListener listener) {
            this.right = right;                       //传入右边按钮文字
            this.rightClick = listener;              //传入右边按钮点击事件
            return this;
        }

        //通过 id 进行适配
        public MyBombBoxAdapter create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 用自定义主题实例化对话框
            final MyBombBoxAdapter dialog = new MyBombBoxAdapter(context, R.style.Dialog);
            //绑定自定义的布局
            View layout = inflater.inflate(R.layout.view_bomb_box, null);
            //添加视图
            dialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
            //设置对话框标题 （根据 layout）
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            //设置对话框内容 （根据 layout）
            ((TextView) layout.findViewById(R.id.content)).setText(content);
            //设置左边按钮（如果为空就不显示）
            if(left != null){
                ((TextView) layout.findViewById(R.id.left)).setText(left);
                if(leftClick != null){
                    ((TextView) layout.findViewById(R.id.left)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            leftClick.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }else{
                layout.findViewById(R.id.left).setVisibility(View.GONE);//隐藏按钮
            }
            //设置右边按钮（如果为空就不显示）
            if(right != null){
                ((TextView) layout.findViewById(R.id.right)).setText(right);
                if(rightClick != null){
                    ((TextView) layout.findViewById(R.id.right)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rightClick.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }else{
                layout.findViewById(R.id.right).setVisibility(View.GONE);//隐藏按钮
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
