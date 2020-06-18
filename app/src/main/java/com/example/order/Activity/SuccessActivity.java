package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.order.Fragment.HomepageFragment;
import com.example.order.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SuccessActivity extends AppCompatActivity {
    @Bind(R.id.btn_left)
    ImageButton left;
    @Bind(R.id.tv_title)
    TextView title;
    @Bind(R.id.re_main)
    Button re_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                setResult(2, intent);
                finish();
            }
        });
        title.setText("提交订单");
        re_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
