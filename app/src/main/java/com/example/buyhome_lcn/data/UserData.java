package com.example.buyhome_lcn.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserData {
    public final static int SHOPPING_CART_ACTIVITY = 100;
    public final static int NO_ACTIVITY = 0;
    public final static int MEMBER_AREA_ACTIVITY = 101;

    //要前往的頁面
    private static int nextActivity;

    //google 帳戶資訊
    private static String userName;
    private static String userEmail;
    private static String userImgURL;
    private static Bitmap UserImgBitmap;

    //帳戶資訊
    private static String address;
    private static String store;
    private static String payMethod;
    //使用者資訊
    private static int gender;
    private static String birthday;
    private static String phone;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserData.userName = userName;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        UserData.userEmail = userEmail;
    }

    public static String getUserImgURL() {
        return userImgURL;
    }

    /**
     * 儲存使用者頭像 URL
     */
    public static void setUserImgURL(String userImgURL) {
        UserData.userImgURL = userImgURL;
    }

    /**
     * 以 Bitmap 儲存 Bitmap 使用者頭像
     */
    public static void setUserImgBitmap(Bitmap userImgBitmap) {
            UserImgBitmap = userImgBitmap;
    }

    /**
     * 取得使用者頭像 Bitmap
     */
    public static Bitmap getUserImgBitmap() {
        return UserImgBitmap;
    }

    public UserData() {
    }

    public static int getNextActivity() {
        return nextActivity;
    }

    public static void setNextActivity(int nextActivity) {
        UserData.nextActivity = nextActivity;
    }


    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        UserData.address = address;
    }

    public static String getStore() {
        return store;
    }

    public static void setStore(String store) {
        UserData.store = store;
    }

    public static String getPayMethod() {
        return payMethod;
    }

    public static void setPayMethod(String payMethod) {
        UserData.payMethod = payMethod;
        Log.d("myTest", "setPayMethod: " + UserData.payMethod);
    }

    public static int getGender() {
        return gender;
    }

    public static void setGender(int gender) {
        UserData.gender = gender;
    }

    public static String getBirthday() {
        return birthday;
    }

    public static void setBirthday(String birthday) {
        UserData.birthday = birthday;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        UserData.phone = phone;
    }

}
