package com.win.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.win.customview.toast.ToastActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button toast;//自定义消息框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initData();
    }
    public void init(){
        toast = (Button)findViewById(R.id.toast);
    }
    public void initData(){
        toast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.toast:
                startActivity(new Intent(MainActivity.this,ToastActivity.class));
                break;
        }
    }
}
