package com.example.order.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.order.Http.HttpUtil;
import com.example.order.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FindoActivity extends Activity {
    @Bind(R.id.forget_phone)
    EditText forget_phone;
    @Bind(R.id.forget_code)
    EditText forget_code;
    @Bind(R.id.forget_btn)
    Button forget_btn;
    @Bind(R.id.btn_left)
    ImageButton f_back;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findo);
        ButterKnife.bind(this);
        forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String uId=forget_phone.getText().toString();
                String url="https://www.luckyc.top/Order/Fphone?id="+uId;
                HttpUtil.sendOkHttpRequest(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FindoActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                        try {
                            JSONObject parse = new JSONObject(responseText);
                            code = parse.getString("c_id");
                            Log.d("sdf",code);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(code.equals(uId)) {
                                    Intent intent=new Intent(FindoActivity.this,FindtActivity.class);
                                    intent.putExtra("uid",uId);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(FindoActivity.this, "没有此用户", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        f_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
