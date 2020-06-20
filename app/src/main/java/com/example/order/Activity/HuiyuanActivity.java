package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.order.Http.HttpUtil;
import com.example.order.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HuiyuanActivity extends AppCompatActivity {
    ImageButton left;
    TextView title;
    TextView state;
    Button button;
    String code;
    Handler handler;
    private SharedPreferences pre;
    String ur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huiyuan);
        pre= PreferenceManager.getDefaultSharedPreferences(this);
        state=findViewById(R.id.state);
        button=findViewById(R.id.pay);
        left=findViewById(R.id.btn_left);
        title=findViewById(R.id.tv_title);
        title.setText("会员");
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ur="https://www.luckyc.top/Order/GetHy?username="+pre.getString("account","");
        getState(ur);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ur="https://www.luckyc.top/Order/SetHy?username="+pre.getString("account","");
                getState(ur);

            }
        });

        handler=new Handler() {
            public void handleMessage(android.os.Message msg) {
                // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                switch (msg.what) {
                    case 0:
                        if(code.equals("true"))
                            state.setText("会员");
                        else
                            state.setText("非会员");
                        break;
                }
            }
        };

    }
    private  void getState(String url)
    {

        Log.d("hjhkhkj",url);
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                try {
                    JSONObject parse = new JSONObject(responseText);
                    code = parse.getString("info");
                    handler.sendEmptyMessage(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
