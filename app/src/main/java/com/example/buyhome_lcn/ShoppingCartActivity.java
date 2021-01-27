package com.example.buyhome_lcn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.buyhome_lcn.data.ShoppingCartData;
import com.example.buyhome_lcn.data.UserData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCartActivity extends AppCompatActivity {
    Context context;

    private static final String PREF = "PREF";
    private static final String PREF_CART_NAME_LIST = "PREF_CART_NAME_LIST";
    private static final String PREF_CART_PRICE_LIST = "PREF_CART_PRICE_LIST";
    private static final String PREF_CART_PICTURE_LIST = "PREF_CART_PICTURE_LIST";
    private static final String PREF_CART_PRODUCTID_LIST = "PREF_CART_PRODUCTID_LIST";

    private static final String PREF_USER_ADDRESS = "PREF_USER_ADDRESS";
    private static final String PREF_USER_STORE = "PREF_USER_STORE";
    private static final String PREF_USER_PAYMETHOD = "PREF_USER_PAYMETHOD";
    private static final String PREF_USER_GENDER = "PREF_USER_GENDER";
    private static final String PREF_USER_BIRTHDAY = "PREF_USER_BIRTHDAY";
    private static final String PREF_USER_PHONE = "PREF_USER_PHONE";

    private static final String PREF_USER_DEFAULT_RECEIVER = "PREF_USER_DEFAULT_RECEIVER";
    private static final String PREF_USER_DEFAULT_ADDRESS = "PREF_USER_DEFAULT_ADDRESS";
    private static final String PREF_USER_DEFAULT_STORE = "PREF_USER_DEFAULT_STORE";
    private static final String PREF_USER_RECEIVER_LIST = "PREF_USER_RECEIVER_LIST";
    private static final String PREF_USER_ADDRESS_LIST = "PREF_USER_ADDRESS_LIST";
    private static final String PREF_USER_STORE_LIST = "PREF_USER_STORE_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //創建ActionBar物件
        ActionBar bar = getSupportActionBar();
        //設定ActionBar顯示返回鍵
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

//        //準備要存入的資料
//        //Set the values
//        Set<String> rawReceiverListSet = new HashSet<String>();
//        rawReceiverListSet.addAll(UserData.getRawReceiverList());
//
//        Set<String> rawAddressListSet = new HashSet<String>();
//        rawAddressListSet.addAll(UserData.getRawReceiverList());
//
//        Set<String> rawStoreListSet = new HashSet<String>();
//        rawStoreListSet.addAll(UserData.getRawReceiverList());

        //儲存資料至設備
        SharedPreferences sp = getSharedPreferences(PREF, 0);
        //設定為編輯模式，並放入資料鍵值，最後commit()才會寫入
        //儲存購物車商品編號清單
        sp.edit()
                .putString(PREF_CART_PRODUCTID_LIST, ShoppingCartData.getProductIDListString())
                .putString(PREF_USER_ADDRESS, UserData.getAddress())
                .putString(PREF_USER_STORE, UserData.getStore())
                .putString(PREF_USER_PHONE, UserData.getPhone())
                .putString(PREF_USER_PAYMETHOD, UserData.getPayMethod())
//                .putString(PREF_USER_DEFAULT_RECEIVER, UserData.getDefaultName())
//                .putString(PREF_USER_DEFAULT_ADDRESS, UserData.getDefaultAddress())
//                .putString(PREF_USER_DEFAULT_STORE, UserData.getDefaultStore())
//                .putStringSet(PREF_USER_RECEIVER_LIST, rawReceiverListSet)
//                .putStringSet(PREF_USER_ADDRESS_LIST, rawAddressListSet)
//                .putStringSet(PREF_USER_STORE_LIST, rawStoreListSet)
                .apply();

//        Log.d("myTest", "儲存的商品編號: " + ShoppingCartData.getProductIDListString());
    }

    //將 request 傳給 Fragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //將 Activity 接收到的資料傳到 Fragment
        for (Fragment fragment : getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getFragments())
        {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}