package com.example.buyhome_lcn.data;

public class UserData {
    public final static int SHOPPING_CART_ACTIVITY = 100;
    public final static int NO_ACTIVITY = 0;
    public final static int MEMBER_AREA_ACTIVITY = 101;

    static int nextActivity;
    static String userName;
    static String userEmail;
    static String userImgURL;

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

    public static void setUserImgURL(String userImgURL) {
        UserData.userImgURL = userImgURL;
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
