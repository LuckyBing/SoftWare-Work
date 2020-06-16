package com.example.order.Activity;


import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.order.Fragment.HomepageFragment;
import com.example.order.R;
import com.example.order.Tool.GlideCircleTransform;
import com.example.order.Tool.ImageUtils;
import com.google.android.material.navigation.NavigationView;
import com.haibin.calendarview.Calendar;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.io.FileNotFoundException;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 87310 on 2018/7/12.
 */

public class GroupActivity extends FragmentActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {
    @Bind(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    public static final int REQUEST_PERMISSION_CAMERA=1001;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menu;
    ImageView person;
    ImageView saoma;
    private TextView tv_title;
    private Fragment mFragment;//当前显示的Fragment
    private TextView nickname;
    private FragmentTransaction transaction;
    private HomepageFragment homepageFragment;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.bind(this);
        initBottom();
        initFragment();
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu= (ImageView) findViewById(R.id.main_menu);

        View headerView = navigationView.getHeaderView(0);//获取头布局
        person = (ImageView) headerView.findViewById(R.id.person);
        Glide.with(this)
                .load(R.mipmap.baobao)
                .bitmapTransform(new GlideCircleTransform(this))
                .into(person);
        person.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          ImageUtils.showImagePickDialog(GroupActivity.this);

                                      }
                                  }
        );
        nickname=(TextView)findViewById(R.id.nickname) ;

        menu.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                switch (item.getItemId()){
                    case R.id.Hy:
//                        Log.d("sd","sd");
//                        Intent intent1 = new Intent(RadioGroupActivity.this,ConfirmActivity.class);
//                        startActivity(intent1);
//                        break;
                    //case R.id.face:
//                        Intent intent = new Intent();
//                        intent.setClassName("com.henu.swface", "com.henu.swface.activity.RegisterFaceActivity");
//                        startActivity(intent);
//                        Log.d("sd","sd");
//                        Intent intent = new Intent(RadioGroupActivity.this,FaceActivity.class);
//                        //前提：知道要跳转应用的包名、类名
//
//                        startActivity(intent);
//                        break;
                    case R.id.rewrite:
//                        Log.d("sd","sd");
//                        Intent intent2 = new Intent(RadioGroupActivity.this,RewriteActivity.class);
//                        startActivity(intent2);
//                        break;
                    //case R.id.remind:
                    // Log.d("sd","sd");
                    // Intent intent3 = new Intent(RadioGroupActivity.this,YudingActivity.class);
                    // startActivity(intent3);
                    // break;
                   // case R.id.jifen:
//                        Log.d("sd","sd");
//                        Intent intent5 = new Intent(RadioGroupActivity.this,JifenActivity.class);
//                        startActivity(intent5);
//                        break;
                    case R.id.check:
//                        Log.d("sd","sd");
//                        Intent intent4 = new Intent(RadioGroupActivity.this,CheckActivity.class);
//                        startActivity(intent4);
//                        break;
                    case R.id.ticket:
//                        Intent intent6=new Intent(RadioGroupActivity.this,TicketActivity.class);
//                        startActivity(intent6);
//                        break;
                    case R.id.set:
//                        Intent intent7=new Intent(RadioGroupActivity.this,SetActivity.class);
//                        startActivity(intent7);
//                        break;
                }
                //Toast.makeText(RadioGroupActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

        tv_title = (TextView) findViewById(R.id.tv_title);
        saoma = (ImageView) findViewById(R.id.saoma);
        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("sd","sd");
//                Intent intent = new Intent(GroupActivity.this, MyCaptureActivity.class);
//                startActivityForResult(intent, RESULT_OK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_OK: {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
//                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                        String result = bundle.getString(CodeUtils.RESULT_STRING);
//                        String[] info = result.split(" ");
//                        Intent intent = new Intent(RadioGroupActivity.this, OrderInfoActivity.class);
//                        intent.putExtra("name", info[0]);
//                        intent.putExtra("time", info[1]);
//                        intent.putExtra("place", info[2]);
//                        startActivity(intent);
//                        //Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
//                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                        Toast.makeText(RadioGroupActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                    }
                }
                break;
            }
            case ImageUtils.REQUEST_CODE_FROM_ALBUM: {

                if (resultCode == RESULT_CANCELED) {   //取消操作
                    return;
                }

                Uri imageUri = data.getData();
                startCrop(imageUri);
                break;
            }
            case ImageUtils.REQUEST_CODE_FROM_CAMERA: {

                if (resultCode == RESULT_OK) {     //取消操作

                    Uri imageUri = ImageUtils.photoURI;
                    startCrop(imageUri);
                } else {
                    Toast.makeText(GroupActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case UCrop.REQUEST_CROP: {
                if (resultCode == RESULT_OK) {
                    Uri resultUri = UCrop.getOutput(data);
                    Log.d("fdsads",resultUri.toString());
                    Glide.with(this)
                            .load(resultUri)
                            .diskCacheStrategy( DiskCacheStrategy.NONE )
                            .bitmapTransform(new GlideCircleTransform(this))
                            .skipMemoryCache(true)
                            .into(person);
                }
                break;
            }
            //错误裁剪的结果
            case UCrop.RESULT_ERROR: {
                if (resultCode == RESULT_OK) {
                    final Throwable cropError = UCrop.getError(data);
                    handleCropError(cropError);
                }
                break;
            }
            default:
                break;
        }

    }
    private void startCrop(Uri uri) {
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        calendar=new Calendar();
        Uri destinationUri = Uri.fromFile(new File(getExternalCacheDir(), calendar.getYear()+calendar.getMonth()+calendar.getDay()+"uCrop.jpg"));

        Log.d("jsdkljdkls",destinationUri.toString());
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1, 1);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL, UCropActivity.NONE, UCropActivity.ALL);
        options.setToolbarTitle("移动和缩放");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格
        options.setShowCropFrame(false); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(this);
    }


    private void initBottom() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.setInActiveColor(R.color.gray).setActiveColor(R.color.blue);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home_uncheck,"首页"))
                .addItem(new BottomNavigationItem(R.mipmap.order_uncheck,"预定"))
                .addItem(new BottomNavigationItem(R.mipmap.meeting_uncheck,"订单管理"))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }
    private void initFragment() {
        homepageFragment = new HomepageFragment();
//        orderFragment = new OrderFragment();
//        dingdanFragment = new DingdanFragment();
          transaction = getSupportFragmentManager().beginTransaction();
         transaction.add(R.id.frameLayout,homepageFragment)
                .commit();
        mFragment = homepageFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu://点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                //switchFragment(homepageFragment);
                tv_title.setText("首页");
                break;
            case 1:
                //switchFragment(orderFragment);
                tv_title.setText("预定");
                break;
            case 2:
                //switchFragment(dingdanFragment);
                tv_title.setText("订单");
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("dhs","cjcd");
    }

    @Override
    public void onTabReselected(int position) {
        Log.d("dhs","cjcd");
    }
    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.frameLayout,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
    private void handleCropError(Throwable cropError) {
        deleteTempPhotoFile();
        if (cropError != null) {
            Toast.makeText(GroupActivity.this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(GroupActivity.this, "无法剪切选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteTempPhotoFile() {
        File tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "output_iamge.jpg");
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }


}
