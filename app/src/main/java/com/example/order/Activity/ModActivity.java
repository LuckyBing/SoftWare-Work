package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class ModActivity extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.btn_left)
    ImageButton btn_left;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.nickname)
    EditText nickname;
    @Bind(R.id.sex)
    EditText sex;
    @Bind(R.id.mail)
    EditText mail;
    @Bind(R.id.pwd1)
    EditText pwd;
    @Bind(R.id.mod)
    Button mod;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        tv_title.setText("修改资料");
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        nickname.setText(intent.getStringExtra("c_name"));
        mail.setText(intent.getStringExtra("mail"));
        phone.setText(intent.getStringExtra("c_tel"));
        pwd.setText(intent.getStringExtra("psd"));
        sex.setText(intent.getStringExtra("sex"));
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.luckyc.top/Order/change_cus?c_Id="+phone.getText()+"&c_Name="+
                        nickname.getText()+"&c_Psd="+pwd.getText()+"&c_Sex="+(sex.getText().equals("男")?"man":"woman")+"&c_tel="+phone.getText();
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseText);
                        String info=jsonObject.getString("info");
                        if(info.equals("true"))
                        {
                            Intent i=new Intent();
                            setResult(3,i);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            }
        });

//        pref = PreferenceManager.getDefaultSharedPreferences(this);
//        String url ="https://www.luckyc.top/Order/change_cus?c_Id="+pref.getString("account", "")+"&c_Name="
//        +nickname.getText()+"&c_Psd="+&c_Sex=nan&c_tel=12345666
//
//        HttpUtil.sendOkHttpRequest(url, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
    }
}
