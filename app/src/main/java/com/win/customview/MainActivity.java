package com.win.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.win.customview.bomb_box.BombBoxActivity;
import com.win.customview.column.ColumnActivity;
import com.win.customview.slide_calendar.SlideCalendarActivity;
import com.win.customview.toast.ToastActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button toast;//自定义消息框
    private Button bomb_box;//自定义弹框
    private Button calendar;//自定义消息框
    private Button column;//自定义绘制柱状图
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initData();
    }
    public void init(){
        toast = (Button)findViewById(R.id.toast);
        bomb_box = (Button)findViewById(R.id.bomb_box);
        calendar = (Button)findViewById(R.id.calendar);
        column = (Button)findViewById(R.id.column);
    }
    public void initData(){
        toast.setOnClickListener(this);
        bomb_box.setOnClickListener(this);
        calendar.setOnClickListener(this);
        column.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.toast://消息框
                startActivity(new Intent(MainActivity.this,ToastActivity.class));
                break;
            case R.id.bomb_box://弹框
                startActivity(new Intent(MainActivity.this,BombBoxActivity.class));
                break;
            case R.id.calendar://滚动日历
                startActivity(new Intent(MainActivity.this,SlideCalendarActivity.class));
                break;
            case R.id.column://柱状图
                startActivity(new Intent(MainActivity.this,ColumnActivity.class));
                break;
        }
    }
}
