package com.win.customview.bomb_box;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.win.customview.R;
//调用自定义对话框
public class BombBoxActivity extends AppCompatActivity {

    private Button bomb_box;//点击按钮弹框
    private MyBombBoxAdapter.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomb_box);
        bomb_box = (Button)findViewById(R.id.bomb_box);
        bomb_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在这里调用自定义弹框
                builder = new MyBombBoxAdapter.Builder(BombBoxActivity.this);
                builder.setTitle("对话框");
                builder.setContent("感觉好用吗？");
                builder.setRight("还行", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //在这里实现点击 右边按钮 后需要实现的事件
                        Toast.makeText(BombBoxActivity.this,"真有眼光，我也觉得好用！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setLeft("差评", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //在这里实现点击 左边按钮 后需要实现的事件
                        Toast.makeText(BombBoxActivity.this,"还有其他用法，看看在说吧！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
    }
}
