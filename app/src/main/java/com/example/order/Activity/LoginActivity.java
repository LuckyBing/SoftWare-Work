package com.example.order.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.order.Http.HttpUtil;
import com.example.order.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.Callback;


public class LoginActivity extends Activity {
    public static final int REQUEST_PERMISSION_CAMERA=1001;
    @Bind(R.id.et_user)
    EditText et_user;
    @Bind(R.id.et_psw)
    EditText et_psw;
    @Bind(R.id.tb_show)
    ToggleButton tb_show;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.forget)
    TextView forget;
    String username,password;
    String code;
    private SharedPreferences pre;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
        pre= PreferenceManager.getDefaultSharedPreferences(this);
        et_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_psw.setOnFocusChangeListener(onFocusAutoClearHintListener);
        et_user.setOnFocusChangeListener(onFocusAutoClearHintListener);
        tb_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //如果选中，显示密码
                    et_psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    et_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_user.getText().toString();
                password = et_psw.getText().toString();
                Log.d("s", username + password);
                //if(checkPermissons()) {
                    sendRequest();
//                    Intent intent = new Intent(LoginActivity.this, RadioGroupActivity.class);
//                    startActivity(intent);
                //}
                //sendRequest();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindoActivity.class);
                startActivity(intent);
            }
        });
    }
//    public  boolean checkPermissons()
//    {
//        int len=0;
//        if (Build.VERSION.SDK_INT >= 23) {
//            int REQUEST_CODE_PERMISSION_STORAGE = 100;
//            String[] permissions = {
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//            };
//
//            for (String str : permissions) {
//                if (ContextCompat.checkSelfPermission(LoginActivity.this,str) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(LoginActivity.this,permissions,REQUEST_PERMISSION_CAMERA );
//                    len++;
//                }
//            }
//            if(len>0)
//            {
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(LoginActivity.this,"You Agree!",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(LoginActivity.this,"You Denied!",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    public void sendRequest() {
        String url="https://www.luckyc.top/Order/Login?username="+username+"&password="+password;
        Log.d("hjhkhkj",url);
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                try {
                    JSONObject parse = new JSONObject(responseText);
                    code = parse.getString("info");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(code.equals("true")) {
                            Log.d("sdfd","true");
                            editor=pre.edit();
                            editor.putString("account",username);
                            editor.apply();
                            Log.d("dfsad",pre.getString("account",""));
                            Intent intent = new Intent(LoginActivity.this, GroupActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }




    public  View.OnFocusChangeListener onFocusAutoClearHintListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText editText=(EditText)v;
            if (!hasFocus) {// 失去焦点
                editText.setHint(editText.getTag().toString());
            } else {
                String hint=editText.getHint().toString();
                editText.setTag(hint);
                editText.setHint("");
            }
        }
    };
}
