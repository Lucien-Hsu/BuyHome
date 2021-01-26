package com.example.buyhome_lcn.data;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

public class MemberAreaViewModel extends ViewModel {
    //帳戶資訊
    public String address;
    public String store;
    public String payMethod;

    //使用者基本資訊
    public Boolean hasPhoto;
//    public Bitmap userPhotoBitmap;
    public String nickname;
    public String email;

    //使用者資訊
    public String password;
    public int gender;
    public final int GENDER_UNKNOWN = 0;
    public final int GENDER_FEMALE = 1;
    public final int GENDER_MALE = 2;
    public String birthday;
    public String phone;

    /**
     * 初始化
     */
    public MemberAreaViewModel() {
        //初始化帳號資訊
        address = "";
        store = "";
        payMethod = "";

        //初始化頭像、暱稱、信箱
        hasPhoto = false;
        nickname = "";
        email = "";

        //初始化基本資訊
        password = "";
        gender = GENDER_UNKNOWN;
        birthday = "";
        phone = "";

        initInfoWithHardcode();

        //TODO 假資料要換真資料
        initInfo();
    }

    private void initInfo() {

    }

    //設定假資料
    private void initInfoWithHardcode() {
        initMemberInfo(
                "",
                "",
                "");

        initUserBasicInfo(
                false,
                "無暱稱",
                "無信箱");

        initUserInfo(
                "123123",
                GENDER_UNKNOWN,
                "",
                "");
    }

    /**
     * 初始化帳號資訊
     */
    private void initMemberInfo(String address, String store, String payMethod) {
        this.address = address;
        this.store = store;
        this.payMethod = payMethod;
    }

    /**
     * 初始化頭像、暱稱、信箱
     */
    private void initUserBasicInfo(Boolean hasPhoto, String nickname, String email) {
        this.hasPhoto = hasPhoto;
        this.nickname = nickname;
        this.email = email;
    }

    /**
     * 初始化基本資訊
     */
    private void initUserInfo(String password, int gender, String birthday, String phone) {
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
    }

    public Bitmap getUserPhotoBitmap() {
        return UserData.getUserImgBitmap();
    }

    public void setUserPhotoBitmap(Bitmap userPhotoBitmap) {
//        this.userPhotoBitmap = userPhotoBitmap;
        UserData.setUserImgBitmap(userPhotoBitmap);
    }

    public Boolean getHasPhoto() {
        return (UserData.getUserImgBitmap() != null);
    }

//    public void setHasPhoto(Boolean hasPhoto) {
//        this.hasPhoto = hasPhoto;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getNickname() {
        return UserData.getUserName();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordHided() {
        int length = password.length();
        StringBuilder hidedPWD = new StringBuilder();
        for (int i = 0; i < length; i++) {
            hidedPWD.append("*");
        }
        return hidedPWD.toString();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        switch (gender) {
            case 0:
                return "";
            case 1:
                return "女性";
            case 2:
                return "男性";
            default:
                return "Unknown";
        }
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return UserData.getUserEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
