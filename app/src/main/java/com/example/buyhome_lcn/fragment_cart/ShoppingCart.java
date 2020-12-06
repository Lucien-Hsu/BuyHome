package com.example.buyhome_lcn.fragment_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buyhome_lcn.R;

public class ShoppingCart extends Fragment {
    Button btnGoCheckDeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);






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