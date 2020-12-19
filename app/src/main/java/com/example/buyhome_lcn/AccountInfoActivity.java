package com.example.buyhome_lcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountInfoActivity extends AppCompatActivity {
    Context context;
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
            R.drawable.arrow_right,  R.drawable.arrow_right,
            R.drawable.arrow_right,  R.drawable.arrow_right};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        context = this;

        //TODO 製作listView清單
        itemList = new ArrayList<Map<String, Object>>();

        for(int i = 0 ; i < infoImgList.length ; i++){
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
}