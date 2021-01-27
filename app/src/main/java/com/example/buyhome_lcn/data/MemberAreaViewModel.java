package com.example.buyhome_lcn.data;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MemberAreaViewModel extends ViewModel {
    //性別常數
    public final int GENDER_UNKNOWN = 0;
    public final int GENDER_FEMALE = 1;
    public final int GENDER_MALE = 2;

    //收件相關
    public String defaultReceiver;
    public String defaultAddress;
    public String defaultStore;
    public List<String> receiverList;
    public List<String> addressList;
    public List<String> storeList;

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

    /**
     * 取得所有收件人
     */
    public ArrayList<String> getReceiverList(){
//        ArrayList<String> resultList = new ArrayList<>();
//        String[] tempStr;
//
//        for(int i = 0 ; i < receiverList.size() ; i++){
//            tempStr = receiverList.get(i).split("#");
//            resultList.add(tempStr[1] + "  " + tempStr[2]);
//        }
//        return resultList;
        return UserData.getReceiverList();
    }

    /**
     * 取得所有收件人的未解析格式
     */
    public static ArrayList<String> getRawReceiverList(){
        return UserData.getRawReceiverList();
    }

    /**
     * 取得所有地址
     */
    public ArrayList<String> getAddressList(){
//        ArrayList<String> resultList = new ArrayList<>();
//        String[] tempStr;
//
//        for(int i = 0 ; i < addressList.size() ; i++){
//            tempStr = addressList.get(i).split("#");
//            resultList.add(tempStr[1] + tempStr[2] + tempStr[3]);
//        }
//        return resultList;
        return UserData.getAddressList();
    }

    /**
     * 取得所有地址的未解析格式
     */
    public static ArrayList<String> getRawAddressList(){
        return UserData.getRawAddressList();
    }

    /**
     * 取得所有門市
     */
    public ArrayList<String> getStoreList(){
//        ArrayList<String> resultList = new ArrayList<>();
//        String[] tempStr;
//
//        for(int i = 0 ; i < storeList.size() ; i++){
//            tempStr = storeList.get(i).split("#");
//            resultList.add(tempStr[1] + "  " + tempStr[2]);
//        }
//        return resultList;
        return UserData.getStoreList();
    }

    /**
     * 取得所有門市的未解析格式
     */
    public static ArrayList<String> getRawStoreList(){
        return UserData.getRawStoreList();
    }


    /**
     * 新增收件者
     */
    public void addReceiver(String newReceiver){
//        receiverList.add(newReceiver);
        UserData.addReceiver(newReceiver);
    }

    /**
     * 刪除指定收件者
     */
    public void deleteReceiver(int index){
//        receiverList.remove(index);
        UserData.deleteReceiver(index);
    }

    /**
     * 新增宅配地址
     */
    public void addAddress(String newAddress){
//        addressList.add(newAddress);
        UserData.addAddress(newAddress);
    }

    /**
     * 刪除指定宅配地址
     */
    public void deleteAddress(int index){
//        addressList.remove(index);
        UserData.deleteAddress(index);
    }

    /**
     * 新增門市
     */
    public void addStore(String newStore){
//        storeList.add(newStore);
        UserData.addStore(newStore);
    }

    /**
     * 刪除指定門市
     */
    public void deleteStore(int index){
//        storeList.remove(index);
        UserData.deleteStore(index);
    }

    public void setDefaultReceiver(String defaultReceiver) {
        UserData.setDefaultReceiver(defaultReceiver);
    }

    public void setDefaultAddress(String defaultAddress) {
        UserData.setDefaultAddress(defaultAddress);
    }

    public void setDefaultStore(String defaultStore) {
        UserData.setDefaultStore(defaultStore);
    }

    /**
     * 取得預設宅配地址
     * @return
     */
    public String getDefaultAddress(){
        return UserData.getDefaultAddress();
    }

    /**
     * 取得預設超商
     * @return
     */
    public String getDefaultStore(){
        return UserData.getDefaultStore();
    }

}
