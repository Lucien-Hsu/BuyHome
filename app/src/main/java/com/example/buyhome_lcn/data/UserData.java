package com.example.buyhome_lcn.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UserData {
    public final static int SHOPPING_CART_ACTIVITY = 100;
    public final static int NO_ACTIVITY = 0;
    public final static int MEMBER_AREA_ACTIVITY = 101;

    static int nextActivity;
    static String userName;
    static String userEmail;
    static String userImgURL;
    static Bitmap UserImgBitmap;

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
        setUserImgBitmap(userImgURL);
    }

    /**
     * 以 URL 儲存 Bitmap 使用者頭像
     */
    public static void setUserImgBitmap(String userImgURL) {
        URL url = null;

        try {
            url = new URL(userImgURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
//            UserImgBitmap = null;
            UserImgBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

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


}
