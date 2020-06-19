package com.example.order.Tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kelvin on 16/4/20.
 */
public class ImageUtils {

    public static final int REQUEST_CODE_FROM_CAMERA = 5001;
    public static final int REQUEST_CODE_FROM_ALBUM = 5002;
    public static final int REQUEST_CODE_CROP = 5003;

    /**
     * 存放拍照图片的uri地址
     */
    public static Uri photoURI;

    /**
     * 记录是处于什么状态：拍照or相册
     */
    private static int state = 0;

    /**
     * 显示获取照片不同方式对话框
     */
    public static void showImagePickDialog(final Activity activity){

        String title = "选择获取图片方式";
        String[] items = new String[]{"拍照","相册"};

        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        switch (which){
                            case 0:
                                state = 1;
                                dispatchTakePictureIntent(activity);
                                break;
                            case 1:
                                state = 2;
                                pickImageFromAlbum(activity);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }


    /**
     * 打开本地相册选取图片
     */
    public static void pickImageFromAlbum(final Activity activity){

        //隐式调用，可能出现多种选择
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent,REQUEST_CODE_FROM_ALBUM);

        /**
         Intent intent = new Intent();
         intent.setAction(Intent.ACTION_PICK);
         intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         activity.startActivityForResult(intent,REQUEST_CODE_FROM_ALBUM);
         */
    }
    static String currentPhotoPath;

    /**
     * 打开相机拍照获取图片
     */


    /**
     * 根据指定目录产生一条图片Uri
     */



    private static File createImageFile(final Activity activity) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d("sdf",storageDir.toString());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        Log.d("sdf",image.toString());

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("test 2020/3/6",currentPhotoPath);
        return image;
    }
    private static void dispatchTakePictureIntent(final Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(activity);
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(activity,
                        "com.example.order.fileprovider",
                        photoFile);
                Log.d("2020/3/6",photoURI.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_CODE_FROM_CAMERA);
            }
        }
    }





}