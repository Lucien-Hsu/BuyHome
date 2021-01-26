package com.example.buyhome_lcn.data;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

public class MemberAreaViewModel extends ViewModel {
    //性別常數
    public final int GENDER_UNKNOWN = 0;
    public final int GENDER_FEMALE = 1;
    public final int GENDER_MALE = 2;

    public Bitmap getUserPhotoBitmap() {
        return UserData.getUserImgBitmap();
    }

    public void setUserPhotoBitmap(Bitmap userPhotoBitmap) {
        UserData.setUserImgBitmap(userPhotoBitmap);
    }

    public Boolean getHasPhoto() {
        return (UserData.getUserImgBitmap() != null);
    }

    public String getAddress() {
        return UserData.getAddress();
    }

    public void setAddress(String address) {
        UserData.setAddress(address);
    }

    public String getStore() {
        return UserData.getStore();
    }

    public void setStore(String store) {
        UserData.setStore(store);
    }

    public String getPayMethod() {
        return UserData.getPayMethod();
    }

    public void setPayMethod(String payMethod) {
        UserData.setPayMethod(payMethod);
    }

    public String getNickname() {
        return UserData.getUserName();
    }

    public String getGender() {
        int gender = UserData.getGender();
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
        UserData.setGender(gender);
    }

    public String getBirthday() {
        return UserData.getBirthday();
    }

    public void setBirthday(String birthday) {
        UserData.setBirthday(birthday);
    }

    public String getPhone() {
        return UserData.getPhone();
    }

    public void setPhone(String phone) {
        UserData.setPhone(phone);
    }

    public String getEmail() {
        return UserData.getUserEmail();
    }

}
