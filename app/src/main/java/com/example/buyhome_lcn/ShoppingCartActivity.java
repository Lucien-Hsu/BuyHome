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

import java.util.List;
import java.util.Set;

public class ShoppingCartActivity extends AppCompatActivity {
    Context context;

    private static final String PREF = "PREF";
    private static final String PREF_CART_NAME_LIST = "PREF_CART_NAME_LIST";
    private static final String PREF_CART_PRICE_LIST = "PREF_CART_PRICE_LIST";
    private static final String PREF_CART_PICTURE_LIST = "PREF_CART_PICTURE_LIST";
    private static final String PREF_CART_PRODUCTID_LIST = "PREF_CART_PRODUCTID_LIST";

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

        //儲存資料至設備
        SharedPreferences sp = getSharedPreferences(PREF, 0);
        //設定為編輯模式，並放入資料鍵值，最後commit()才會寫入
        //儲存購物車商品編號清單
        sp.edit()
                .putString(PREF_CART_PRODUCTID_LIST, ShoppingCartData.getProductIDListString())
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