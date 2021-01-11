package com.example.buyhome_lcn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buyhome_lcn.R;

import java.util.List;
import java.util.Map;

public class HomeGridViewAdapter extends BaseAdapter {
    private final LayoutInflater myLayoutInflater;
    private final List<Map<String, Object>> myItemList;

    //建構子
    public HomeGridViewAdapter(Context context, List<Map<String, Object>> itemList) {
        myLayoutInflater = LayoutInflater.from(context);
        myItemList = itemList;
    }

    //取得總項目數量
    @Override
    public int getCount() {
        return myItemList.size();
    }

    //取得項目
    @Override
    public Object getItem(int i) {
        return myItemList.get(i);
    }

    //取得項目編號
    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imgID;
        TextView nameID;
        TextView priceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = myLayoutInflater.inflate(R.layout.item_home_gridview, null);

            //建立ViewHolder並傳入資料
            holder = new ViewHolder();
            holder.imgID = convertView.findViewById(R.id.img_home_pic);
            holder.nameID = convertView.findViewById(R.id.tv_home_name);
            holder.priceID = convertView.findViewById(R.id.tv_home_price);

//調用setTag("")方法時，可以理解為為View設置了一個標識，然後通過getTag( )來獲取標識，
//或者理解為View作為一個容器除了顯示一些字符串,圖片之外，還可以通過setTag("")方法往其中存放一些數據，
//然後通過通過getTag()來獲取數據。
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Map<String, Object> item = myItemList.get(position);
        int number = (int) item.get("picture");
        String name = (String) item.get("name");
        Integer price = (Integer) item.get("price");
        holder.imgID.setImageResource(number);
        holder.nameID.setText(name);
        holder.priceID.setText(price + "");

        return convertView;
    }
}
