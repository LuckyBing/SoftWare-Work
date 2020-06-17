package com.example.order.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.order.R;

import java.io.File;
import java.util.ArrayList;

import utils.PhotoPickerActivity;
import utils.PhotoPickerIntent;
import utils.PhotoPreviewActivity;
import utils.PhotoPreviewIntent;
import utils.SelectModel;

/**
 * Created by 87310 on 2019/2/15.
 */
public class PingjiaActivity extends Activity{
    private ImageButton btn_left;
    private TextView tv_title;
    private RatingBar rb_main_rating;
    private TextView tv_class;
    private EditText editText1;
    private TextView textView;
    private Button btn_fabu;
    private TextView tv_meetname;

    GridView mGridView;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private static final int IMAGE_SIZE = 16;
    GrideAdapter mGrideAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjia);
        mContext = this;

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("发布评价");
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        tv_meetname = (TextView) findViewById(R.id.tv_meetname);
        tv_meetname.setText(name);

        tv_class = (TextView) findViewById(R.id.tv_class);
        rb_main_rating = (RatingBar) findViewById(R.id.rb_main_rating);
        //给控件设置监听事件
        rb_main_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==1){
                    tv_class.setText("非常差");
                }else if(rating==2){
                    tv_class.setText("差");
                }else if(rating==3){
                    tv_class.setText("一般");
                }else if(rating==4){
                    tv_class.setText("好");
                }else if(rating==5){
                    tv_class.setText("非常好");
                }
            }
        });

        btn_fabu = (Button) findViewById(R.id.btn_fabu);
        btn_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PingjiaActivity.this, "发布成功!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                PingjiaActivity.this.setResult(1, intent);
                finish();
            }
        });

        editText1=(EditText) findViewById(R.id.editText1);
        textView=(TextView) findViewById(R.id.textView1);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView.setText(String.valueOf(s.length())+"/400");
                if(s.length()>=400){
                    Toast.makeText(PingjiaActivity.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mGridView = (GridView) findViewById(R.id.gridView);
        mGrideAdapter = new GrideAdapter();
        mGridView.setAdapter(mGrideAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (imagePaths.size() < IMAGE_SIZE && i == imagePaths.size()) {
                    //照片选择
                    PhotoPickerIntent intent = new PhotoPickerIntent(mContext);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(IMAGE_SIZE); // 最多选择照片数量，默认为9
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    //查看大图
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(mContext);
                    intent.setCurrentItem(i);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths == null) {
            imagePaths = new ArrayList<>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
        mGrideAdapter.notifyDataSetChanged();
    }

    class GrideAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            int total = imagePaths.size();
            if (total < IMAGE_SIZE)
                total++;
            return total;
        }

        @Override
        public String getItem(int i) {
            return imagePaths.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            if (i == imagePaths.size() && imagePaths.size() < IMAGE_SIZE) {
                Glide.with(mContext)
                        .load(R.mipmap.add_pic)
                        .override(100,100)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            } else {
                Glide.with(mContext)
                        .load(new File(getItem(i)))
                        .override(100,100)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            }
            return view;
        }
    }

}
