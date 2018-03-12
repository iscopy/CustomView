package com.win.customview.Toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.win.customview.R;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class MyToast{

    private int image;
    private String texts;
    private Toast toast;

    public MyToast(Context context,int image,String texts) {
        //加载布局
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_toast,null);

        //设置图片 (通过 view )
        ImageView imageView=(ImageView)view.findViewById(R.id.image_toast);
        imageView.setBackgroundResource(image);

        //设置文字 (通过 view )
        TextView textView = (TextView) view.findViewById(R.id.text_toast);
        textView.setText(texts);

        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);                    //将布局放入 Toast
        toast.show();
    }
}
