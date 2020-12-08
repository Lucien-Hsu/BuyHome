package com.example.buyhome_lcn.fragment_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.buyhome_lcn.R;


public class CheckDelivery extends Fragment {
    Button btnGoBack, btnHomeDelivery, btnStorePickup;
    CheckBox cbHome, cbStore;

    Boolean isHomeChecked = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_delivery, container, false);

        chooseDeliveryWay(view);

        //[按鈕]  設定宅配地址
        btnHomeDelivery = view.findViewById(R.id.btn_choose_address);
        btnHomeDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 設定宅配地址
            }
        });

        //[按鈕]  設定取貨門市
        btnStorePickup = view.findViewById(R.id.cb_store_pickup);
        btnStorePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 設定取貨門市
            }
        });


        //[按鈕]  返回"確認付款"頁面
        btnGoBack = view.findViewById(R.id.btn_gobackto_checkdeal);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出BackStack，此處便不會記錄到堆疊中。否則之後按返回建會回到這頁。
                Navigation.findNavController(view).popBackStack();
            }

        });

        return view;
    }

    /**
     * 選擇運送方式
     * @param view
     */
    private void chooseDeliveryWay(View view) {
        //CheckBox 只能有一個被勾選
        cbStore = view.findViewById(R.id.cb_store_pickup);
        cbHome = view.findViewById(R.id.cb_home_delivery);

        cbHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isHomeChecked = b;
                cbStore.setChecked(!b);
            }
        });

        cbStore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isHomeChecked = !b;
                cbHome.setChecked(!b);
            }
        });
    }
}