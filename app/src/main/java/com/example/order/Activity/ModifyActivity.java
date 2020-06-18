package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
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

public class ModifyActivity extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.btn_left)
    ImageButton btn_left;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.nickname)
    TextView nickname;
    @Bind(R.id.sex)
    TextView sex;
    @Bind(R.id.mail)
    TextView mail;
    @Bind(R.id.mod)
    Button mod;
    SharedPreferences pref;
    Handler handler;
    Intent intent;
    String nick,se,pho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ButterKnife.bind(this);
        init();
        intent=new Intent(ModifyActivity.this,ModActivity.class);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                switch (msg.what) {
                    case 0:
                        nickname.setText(nick);
                        phone.setText(pho);
                        if (se.equals("man"))
                            sex.setText("男");
                        else
                            sex.setText("女");
                }
            }
        };
    }
    private void init()
    {
        tv_title.setText("用户资料");
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(intent,3);
            }
        });
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        String url="http://120.26.185.147:10085/Order/Fphone?id="+ pref.getString("account","");
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ModifyActivity.this,"连接服务器失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    intent.putExtra("c_id",pref.getString("account",""));

                    nick=jsonObject.getString("c_name");
                    pho=jsonObject.getString("c_tel");
                    se=jsonObject.getString("c_sex");
                    intent.putExtra("c_name",nick);
                    intent.putExtra("sex",se);
                    intent.putExtra("c_tel",pho);
                    intent.putExtra("mail",mail.getText());
                    intent.putExtra("psd",jsonObject.getString("c_psd"));
                   handler.sendEmptyMessage(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 3:
                init();
                break;
        }
    }




}
