package com.win.customview.column;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14 0014.
 * 柱状图的绘制
 */

public class ColumnView extends View {

    private String[] transverse;    //横列的刻度值数组
    private String[] vertical;      //竖列的刻度值数组
    private List<Integer> colors;    //画笔颜色集合
    private int[] high;           //柱状图高度数值数组

    //整个画布的宽、高
    private int width;
    private int height;

    // X,Y轴的单位长度 (类似网格切割)
    private int xScale;
    private int yScale;

    // 默认边距
    private int margin = 20;
    // 距离左边偏移量
    private int marginX = 30;
    // 原点坐标
    private int xPoint;
    private int yPoint;

    // 画笔
    private Paint paintAxes;            //画轴
    private Paint paintCoordinate;      //画坐标（文字或数值）
    private Paint paintRectF;           //画矩形

    public ColumnView(Context context) {
        super(context);
    }

    //在构造方法中传入横列的刻度值数组、竖列的刻度值数组、画笔颜色集合、柱状图高度数值数组
    public ColumnView(Context context, String[] transverse, String[] vertical, List<Integer> colors, int[] high) {
        super(context);
        this.transverse = transverse;
        this.vertical = vertical;
        this.colors = colors;
        this.high = high;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        drawAxesLine(canvas, paintAxes);            //绘制横、竖轴
        drawCoordinate(canvas, paintCoordinate);   //绘制坐标（文字或数值）
        drawColumn(canvas, paintRectF, high);//画柱形图
    }

    //初始化数据值和画笔
    public void init(){
        //计算原点坐标和单位长度
        xPoint = margin + marginX;
        yPoint = this.getHeight() - margin;
        xScale = (this.getWidth() - 2 * margin - marginX) / (transverse.length - 1);
        yScale = (this.getHeight() - 2 * margin) / (vertical.length - 1);

        paintAxes = new Paint();     //画轴(横轴和竖轴)
        paintAxes.setStyle(Paint.Style.STROKE);
        paintAxes.setAntiAlias(true);
        paintAxes.setDither(true);
        paintAxes.setColor(ContextCompat.getColor(getContext(), colors.get(0)));
        paintAxes.setStrokeWidth(1);

        paintCoordinate = new Paint(Paint.ANTI_ALIAS_FLAG);    //画坐标（文字或数值）
        paintCoordinate.setColor(ContextCompat.getColor(getContext(), colors.get(1)));
        paintCoordinate.setTextSize(22f);

        paintRectF = new Paint();       //画矩形
        paintRectF.setColor(ContextCompat.getColor(getContext(), colors.get(2)));
        paintRectF.setStyle(Paint.Style.FILL);
        paintRectF.setDither(true);
        paintRectF.setAntiAlias(true);
    }



    // 绘制坐标轴
    private void drawAxesLine(Canvas canvas, Paint paint) {
        // 画 X 轴
        canvas.drawLine(xPoint + 20, yPoint - 20, this.getWidth() - margin / 6 + 20, yPoint - 20, paint);
        // 画 Y 轴
        canvas.drawLine(xPoint + 20, yPoint - 20, xPoint + 20, margin / 6 - 20, paint);
    }

    // 绘制坐标刻度 （可以根据自己需要进行微调）
    private void drawCoordinate(Canvas canvas, Paint paint) {
        // X轴坐标
        for (int i = 0; i <= (transverse.length - 1); i++) {
            paint.setTextAlign(Paint.Align.CENTER);
            int startX = xPoint + i * xScale;
            canvas.drawText(transverse[i], startX - 10, this.getHeight() - margin / 6, paint);
        }

        // Y轴坐标
        for (int i = 0; i <= (vertical.length - 1); i++) {
            paint.setTextAlign(Paint.Align.LEFT);
            int startY = yPoint - i * yScale;
            int offsetX;
            switch (vertical[i].length()) {
                case 1:
                    offsetX = 28;
                    break;
                case 2:
                    offsetX = 20;
                    break;
                case 3:
                    offsetX = 12;
                    break;
                case 4:
                    offsetX = 5;
                    break;
                default:
                    offsetX = 0;
                    break;
            }
            int offsetY;
            if (i == 0) {
                offsetY = 0;
            } else {
                offsetY = margin / 5;
            }
            canvas.drawText(vertical[i], margin / 4 + offsetX - 10, startY + offsetY - 5, paint);
        }
    }


    // 绘制单柱形
    private void drawColumn(Canvas canvas, Paint paint, int data[]) {
        for (int i = 1; i <= (transverse.length - 1); i++) {
            int startX = xPoint + i * xScale;
            RectF rect = new RectF(startX - 20, toY(data[i - 1]), startX + 5, this.getHeight() - margin*2);
            canvas.drawRect(rect, paint);
        }
    }

    //数据按比例转换坐标（可以根据需要自己设置转换比例）
    private float toY(int num) {
        float y;
        try {
            float a = (float) num / 100.0f;
            y = yPoint - a * yScale;
        } catch (Exception e) {
            return 0;
        }
        return y;
    }
}
