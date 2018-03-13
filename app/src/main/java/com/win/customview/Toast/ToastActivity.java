package com.win.customview.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.win.customview.R;

public class ToastActivity extends AppCompatActivity {

    private Button my_toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        my_toast = (Button)findViewById(R.id.my_toast);
        my_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里调用自定义的消息框
                MyToast myToast = new MyToast(ToastActivity.this,R.mipmap.ic_launcher,"12335");
            }
        });

    }
}
