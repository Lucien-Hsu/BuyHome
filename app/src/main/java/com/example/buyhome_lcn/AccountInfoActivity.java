package com.example.buyhome_lcn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.buyhome_lcn.R;

import java.io.FileNotFoundException;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountInfoActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 001;
    private static final int CAMERA_REQUEST = 002;

    Context context;
    CardView cvUserPhoto;
    ImageView imgUserPhoto;
    ListView lvAccountArea;

    List<Map<String, Object>> itemList;
    int[] infoImgList = {
            R.drawable.ic_user_info, R.drawable.ic_password,
            R.drawable.ic_gender, R.drawable.ic_birthday,
            R.drawable.ic_phone, R.drawable.ic_email};

    String[] infoTextList = {
            "帳號", "密碼",
            "性別", "生日",
            "手機", "信箱"};

    String[] showInfoTextList = {
            "", "*********",
            "female", "20201212",
            "0911222333", "myaccount@gmail.com"};

    Integer[] showNextSign = {
            null, R.drawable.arrow_right,
            R.drawable.arrow_right, R.drawable.arrow_right,
            R.drawable.arrow_right, R.drawable.arrow_right};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        context = this;
        imgUserPhoto = findViewById(R.id.img_user_photo);

        cvUserPhoto = findViewById(R.id.cv_user_photo);
        cvUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromGallery();
            }
        });

        //TODO 製作listView清單
        itemList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < infoImgList.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", infoImgList[i]);
            item.put("info", infoTextList[i]);
            item.put("showInfo", showInfoTextList[i]);
            item.put("showNextSign", showNextSign[i]);
            itemList.add(item);
        }

        lvAccountArea = findViewById(R.id.lv_account_area);
        SimpleAdapter adapter = new SimpleAdapter(
                context, itemList,
                R.layout.item_userinfo,
                new String[]{"img", "info", "showInfo", "showNextSign"},
                new int[]{R.id.img_info, R.id.tv_info, R.id.tv_show_info, R.id.img_next_sign});

        lvAccountArea.setAdapter(adapter);
    }

    private void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //1-3.若 requestCode 是GALLERY_REQUEST & 正常讀取 & 有資料則做
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null){

            //以 Uri 型態取得資料
            Uri imageUri = data.getData();
            //將圖片設定到指定 view
            imgUserPhoto.setImageURI(imageUri);

        }else if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            //2-3.若 requestCode 是 CAMERA_REQUEST & 正常讀取則做

            //以 bitmap 型態變數儲存照片資料
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgUserPhoto.setImageBitmap(bitmap);

        }
    }

}