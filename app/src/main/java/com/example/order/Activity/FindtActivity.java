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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FindtActivity extends Activity {

    EditText new_password;
    EditText new_cpassword;
    Button new_btn;
    ImageButton n_back;
    String uId,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findt);
        new_password=findViewById(R.id.new_password) ;
        new_cpassword=findViewById(R.id.new_Cpassword) ;
        new_btn=findViewById(R.id.new_btn) ;
        n_back=findViewById(R.id.btn_left) ;
        Intent intent=getIntent();
        uId=intent.getStringExtra("uid");
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uPwd=new_password.getText().toString();
                String uCpwd=new_cpassword.getText().toString();
                if (uCpwd.equals(uPwd)) {
                    //Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //finish();
                    String url="https://www.luckyc.top/Order/Mpsd?username="+uId+"&password="+uPwd;
                    HttpUtil.sendOkHttpRequest(url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(FindtActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String responseText = response.body().string();
                            try {
                                JSONObject parse = new JSONObject(responseText);
                                code = parse.getString("info");
                                Log.d("sdf",code);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(code.equals("true")) {
                                        Toast.makeText(FindtActivity.this,"找回密码成功",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else
                                        Toast.makeText(FindtActivity.this, "找回密码失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                } else
                    Toast.makeText(FindtActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
            }
        });
        n_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
