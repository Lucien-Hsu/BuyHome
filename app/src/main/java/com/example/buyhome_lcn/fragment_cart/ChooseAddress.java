package com.example.buyhome_lcn.fragment_cart;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.data.ShoppingCartViewModel;

public class ChooseAddress extends Fragment {
    Context context;
    //ViewModel
    private ShoppingCartViewModel viewModel;

    ListView llAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = requireActivity();
        View view = inflater.inflate(R.layout.fragment_choose_address, container, false);

        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(ShoppingCartViewModel.class);

        //TODO 處理 ListView
        llAddress = view.findViewById(R.id.ll_address);

        //設定資料陣列
        String[] data = {"song 01", "song 02", "song 03", "song 04"};

        //設定Adapter
        //引數一：context
        //引數二：[view]
        //引數三：[資料]資料陣列
        //這邊的view使用Android預設的
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, viewModel._addressList.getValue());

        //連結Adapter
        llAddress.setAdapter(adapter);

        //設定監聽器
        llAddress.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //參數三為項目編號，可依此決定相應的處理
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
                //依照參數三決定相應的處理
                switch(i){

                }
            }
        });

        return view;
    }
}