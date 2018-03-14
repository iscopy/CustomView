package com.win.customview.column;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.win.customview.R;

import java.util.ArrayList;
import java.util.List;

//自定义绘制柱状图
public class ColumnActivity extends AppCompatActivity {

    private Button start;//开始绘制
    private LinearLayout column;//柱状图绘制的地方
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column);
        start = (Button)findViewById(R.id.start);
        column = (LinearLayout) findViewById(R.id.column);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里调用绘制方法  （也可以直接调用显示的）
                barChart();
            }
        });
    }

    // 初始化柱状图数据（可以根据自己需要插入数据）
    private void barChart() {
        //第一个为空，它需要占一个位置
        String[] transverse = {"","周一","周二","周三","周四","周五","周六","周日"};
        String[] vertical = {"0", "2h", "4h", "8h", "10h"};
        //这里的数据是根据你横列有几个来设的，如上面的横列星期有周一到周日，所以这里设置七个数据
        int[] data = {420 , 380, 340, 300, 260, 220, 180};
        //这里的颜色就对应线条、文字和柱状图（可以根据自己的需要到color里设置）
        List<Integer> color = new ArrayList<>();
        color.add(R.color.colorAccent);
        color.add(R.color.colorPrimary);
        color.add(R.color.green);
        column.addView(new ColumnView(this, transverse, vertical, color, data));
    }
}
