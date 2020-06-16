package com.example.order.Activity;


import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class RegActivity extends Activity {
    @Bind(R.id.btn_left)
    ImageButton register_Back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.register_username)
    EditText register_Name;
    @Bind(R.id.register_password)
    EditText register_Psw;
    @Bind(R.id.register_code)
    EditText register_Code;
    @Bind(R.id.register_sms)
    TextView register_Sms;
    @Bind(R.id.register_confirm_password)
    EditText register_Cpwd;
    @Bind(R.id.register_protocol)
    TextView register_Protocol;
    @Bind(R.id.register_submit)
    Button register_Smit;
    @Bind(R.id.register_check)
    CheckBox register_Check;
    String uName, uPwd, uCode, uCpwd, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        TransformationMethod method = PasswordTransformationMethod.getInstance();
        register_Cpwd.setTransformationMethod(method);
        register_Psw.setTransformationMethod(method);
        register_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("注册");
        register_Sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        register_Smit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register_Check.isChecked()) {
                    uName = register_Name.getText().toString();
                    uCpwd = register_Cpwd.getText().toString();
                    uPwd = register_Psw.getText().toString();
                    if (uCpwd.equals(uPwd)) {
                        //Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        //finish();
                        String url="https://www.luckyc.top/Order/Reg?username="+uName+"&password="+uPwd;
                        HttpUtil.sendOkHttpRequest(url, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                        else
                                            Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    } else
                        Toast.makeText(RegActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(RegActivity.this, "请同意协议", Toast.LENGTH_SHORT).show();

            }
        });

    }
}