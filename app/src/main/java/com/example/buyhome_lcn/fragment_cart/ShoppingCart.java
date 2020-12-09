package com.example.buyhome_lcn.fragment_cart;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Fragment {
    Context context;

    List<String> nameString;
    List<String> priceString;
    List<Integer> pictureId;

    private RecyclerView rvShoppingCart;
    private ShoppingCartAdapter adapter;
    Button btnGoCheckDeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        context = getActivity();

        //TODO 準備假資料
        nameString = new ArrayList<String>();
        priceString = new ArrayList<String>();
        pictureId = new ArrayList<Integer>();
        for(int i = 0 ; i < 30; i++){
            String data = new String("Item : " + (i + 1));
            nameString.add(data);
            priceString.add(data);
            pictureId.add(R.drawable.test_item);
        }

        rvShoppingCart = view.findViewById(R.id.rv_shoppingcart);
        StaggeredGridLayoutManager mLayoutManager_stagger = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvShoppingCart.setLayoutManager(mLayoutManager_stagger);
        //4.建立自定義適配器
        adapter = new ShoppingCartAdapter(context, nameString, priceString ,pictureId);
        //5.連接適配器
        rvShoppingCart.setAdapter(adapter);

        //[按鈕]  前往"確認付款"頁面
        btnGoCheckDeal = view.findViewById(R.id.btn_go_checkdeal);
        btnGoCheckDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_shoppingCart_to_checkDeal);
            }
        });

        return view;
    }
}