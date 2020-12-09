package com.example.buyhome_lcn.fragment_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyhome_lcn.R;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private final Context context;
    private final List<String> nameString;
    private final List<String> priceString;
    private final List<Integer> pictureId;
    private final LayoutInflater mLayoutInflater;

    //4-1.建構子
    //取得context與資料，並設定一個Inflater填充於傳來的context中
    public ShoppingCartAdapter(Context context, List<String> nameString,  List<String> priceString, List<Integer> pictureId) {
        this.context = context;

        this.nameString = nameString;
        this.priceString = priceString;
        this.pictureId = pictureId;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //4-2.取得項目數量
    @Override
    public int getItemCount() {
        //回傳資料數
        return nameString.size();
    }

    //4-3.建立ViewHolder內部類別，必須繼承RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_data;
        private final TextView tv_name_data;
        private final TextView tv_price_data;
        //取得項目視圖中的ViewID
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_data = itemView.findViewById(R.id.img_item_picture);
            tv_name_data = itemView.findViewById(R.id.tv_item_name);
            tv_price_data = itemView.findViewById(R.id.tv_item_price);

            //監聽器
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, tv_name_data.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //4-4.建立ViewHolder
    @NonNull
    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //填充項目View
        View view = mLayoutInflater.inflate(R.layout.item_shoppingcart, null);
        //建立ViewHolder實體
        //此處會把已填充的view給viewHolder，由viewHolder把內部的viewID取出
        ViewHolder viewHolder = new ViewHolder(view);
        //回傳viewHolder
        return viewHolder;
    }

    //4-5.將資料連接到ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ViewHolder holder, int position) {
        //TODO
        holder.img_data.setImageResource(pictureId.get(position));
        holder.tv_name_data.setText(nameString.get(position));
        holder.tv_price_data.setText(priceString.get(position));
    }
}