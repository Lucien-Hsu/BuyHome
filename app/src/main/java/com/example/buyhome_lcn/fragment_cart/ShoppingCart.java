package com.example.buyhome_lcn.fragment_cart;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buyhome_lcn.MainActivity;
import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.adapter.ShoppingCartAdapter;
import com.example.buyhome_lcn.data.ShoppingCartViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Fragment {
    Context context;

    //ViewModel
    private ShoppingCartViewModel viewModel;

    private RecyclerView rvShoppingCart;
    private ShoppingCartAdapter adapter;
    Button btnGoCheckDeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        context = requireActivity();

        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(ShoppingCartViewModel.class);

        //[清單] 呈現商品資料
        rvShoppingCart = view.findViewById(R.id.rv_shoppingcart);
        StaggeredGridLayoutManager mLayoutManager_stagger = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvShoppingCart.setLayoutManager(mLayoutManager_stagger);
        adapter = new ShoppingCartAdapter(context, viewModel.nameString, viewModel.priceString ,viewModel.pictureId);
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