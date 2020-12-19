package com.example.buyhome_lcn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MemberAreaActivity extends AppCompatActivity {
    Context context;
    ListView lvAccountArea;

    List<Map<String, Object>> itemList;
    int[] infoImgList = {
            R.drawable.home,
            R.drawable.arrow_right,
            R.drawable.arrow_right,
            R.drawable.arrow_right};

    String[] infoTextList = {
            "info 01", "info 02",
            "info 03", "info 04"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_area);
        context = this;

        //TODO 製作圓頭像

        //TODO 製作listView清單
        itemList = new ArrayList<Map<String, Object>>();

        for(int i = 0 ; i < infoImgList.length ; i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", infoImgList[i]);
            item.put("info", infoTextList[i]);
            itemList.add(item);
        }

        lvAccountArea = findViewById(R.id.lv_account_area);
        SimpleAdapter adapter = new SimpleAdapter(
                context, itemList,
                R.layout.item_accountarea,
                new String[]{"img", "info"},
                new int[]{R.id.img_info, R.id.tv_info});

        lvAccountArea.setAdapter(adapter);

        lvAccountArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Map<String, Object> item = (Map<String, Object>)adapterView.getItemAtPosition(position);
                Integer img = (Integer) item.get("img");
                String info = (String) item.get("info");
                Toast.makeText(context, "You selected : " + img + " info = " + info, Toast.LENGTH_SHORT).show();
            }
        });


        //TODO 製作片段導航


    }
}