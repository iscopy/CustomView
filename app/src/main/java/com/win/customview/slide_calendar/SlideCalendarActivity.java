package com.win.customview.slide_calendar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.win.customview.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

//弹出一个弹框，通过滑动选择日历日期
public class SlideCalendarActivity extends AppCompatActivity {

    private TextView data;//日期数据显示
    private Button data_base;//通过滑动日历选择

    private CalendarView calendarView1,calendarView2,calendarView3;
    //没有选择时，将会显示的日期，也可以根据系统获取当前时间
    private String years = "2018",months = "3", days = "13";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_calendar);
        data = (TextView)findViewById(R.id.data);
        data_base = (Button)findViewById(R.id.data_base);
        data_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击弹出滑轮选择日历控件
                myCalendar();
            }
        });
    }

    public void myCalendar(){
        //初始化对话框             R.style.CalendarDialog 是自定义的弹框主题，在styles设置
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CalendarDialog);
        //初始化自定义布局参数
        LayoutInflater layoutInflater = getLayoutInflater();
        //加载布局
        View customLayout = layoutInflater.inflate(R.layout.view_slide_calendar, (ViewGroup) findViewById(R.id.customDialog));
        //为对话框设置视图
        builder.setView(customLayout);

        //加载年月日的三个 CalendarView 的 id
        calendarView1 = (CalendarView) customLayout.findViewById(R.id.year);
        calendarView2 = (CalendarView) customLayout.findViewById(R.id.month);
        calendarView3 = (CalendarView) customLayout.findViewById(R.id.day);

        //定义滚动选择器的数据项（年月日的）
        ArrayList<String> gradeYear = new ArrayList<>();
        ArrayList<String> gradeMonth = new ArrayList<>();
        ArrayList<String> gradeDay = new ArrayList<>();

        //为数据项赋值
        int thisYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new java.util.Date()));
        for(int i=1980;i<=thisYear;i++) //从1980到今年
            gradeYear.add(i + "");
        for(int i=1;i<=12;i++)            // 1月到12月
            gradeMonth.add(i + "");
        for(int i=1;i<=31;i++)           // 1日到31日
            gradeDay.add(i + "");

        //为滚动选择器设置数据
        calendarView1.setData(gradeYear);
        calendarView2.setData(gradeMonth);
        calendarView3.setData(gradeDay);

        //滚动选择事件
        calendarView1.setOnSelectListener(new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                years = data;
            }
        });
        calendarView2.setOnSelectListener(new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                months = data;
            }
        });
        calendarView3.setOnSelectListener(new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                days = data;
            }
        });

        //对话框的确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.setText(years + " 年 "+ months + " 月 " + days + " 日 ");

            }
        });
        //对话框的取消按钮
        builder.setNegativeButton("取消", null);
        //显示对话框
        builder.show();


    }
}
