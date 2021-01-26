package com.example.buyhome_lcn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.buyhome_lcn.data.MemberAreaViewModel;
import com.example.buyhome_lcn.data.ShoppingCartData;
import com.example.buyhome_lcn.data.UserData;
import com.example.buyhome_lcn.fragment_cart.ShoppingCart;

public class MemberAreaActivity extends AppCompatActivity {

    private static final String PREF = "PREF";
    private static final String PREF_USER_ADDRESS = "PREF_USER_ADDRESS";
    private static final String PREF_USER_STORE = "PREF_USER_STORE";
    private static final String PREF_USER_PAYMETHOD = "PREF_USER_PAYMETHOD";
    private static final String PREF_USER_GENDER = "PREF_USER_GENDER";
    private static final String PREF_USER_BIRTHDAY = "PREF_USER_BIRTHDAY";
    private static final String PREF_USER_PHONE = "PREF_USER_PHONE";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_area);
        context = this;

        //創建ActionBar物件
        ActionBar bar = getSupportActionBar();
        //設定ActionBar顯示返回鍵
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //儲存資料至設備
        SharedPreferences sp = getSharedPreferences(PREF, 0);
        //設定為編輯模式，並放入資料鍵值，最後commit()才會寫入
        sp.edit()
                .putString(PREF_USER_ADDRESS, UserData.getAddress())
                .putString(PREF_USER_STORE, UserData.getStore())
                .putString(PREF_USER_PAYMETHOD, UserData.getPayMethod())
                .putInt(PREF_USER_GENDER, UserData.getGender())
                .putString(PREF_USER_BIRTHDAY, UserData.getBirthday())
                .putString(PREF_USER_PHONE, UserData.getPhone())
                .apply();

//        Log.d("myTest", "MemberAreaActivity 已儲存資料");
//        Log.d("myTest", "PREF_USER_ADDRESS: " + UserData.getAddress());
//        Log.d("myTest", "PREF_USER_STORE: " + UserData.getStore());
//        Log.d("myTest", "PREF_USER_PAYMETHOD: " + UserData.getPayMethod());
//        Log.d("myTest", "PREF_USER_GENDER: " + UserData.getGender());
//        Log.d("myTest", "PREF_USER_BIRTHDAY: " + UserData.getBirthday());
//        Log.d("myTest", "PREF_USER_PHONE: " + UserData.getPhone());
    }

    //將 request 傳給 Fragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //將 Activity 接收到的資料傳到 Fragment
        for (Fragment fragment : getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}