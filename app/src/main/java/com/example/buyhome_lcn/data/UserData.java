package com.example.buyhome_lcn.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    //收件相關
    private static String defaultReceiver;
    private static String defaultAddress;
    private static String defaultStore;
    private static List<String> receiverList;
    private static List<String> addressList;
    private static List<String> storeList;

    /**
     * 初始化
     */
    static  {
        //[配置記憶體]
        defaultReceiver = "";
        defaultAddress = "";
        defaultStore = "";

        //收件相關
        receiverList = new ArrayList<String>();
        addressList = new ArrayList<String>();
        storeList = new ArrayList<String>();

        //收件相關
        addReceiver("#小明#0912333444");
        addStore("#全家#楊梅店");
        addAddress("#台北市#中正區#重慶南路一段122號");

        //印出:[名字  電話]
        Log.d("myTest", "static initializer: " + getReceiverList());
        Log.d("myTest", "static initializer: " + getAddressList());
        Log.d("myTest", "static initializer: " + getStoreList());
        //印出:名字  電話
        Log.d("myTest", "static initializer: " + getReceiverList().get(0));
        Log.d("myTest", "static initializer: " + getAddressList().get(0));
        Log.d("myTest", "static initializer: " + getStoreList().get(0));
//        Log.d("myTest", "static initializer: " + getD);
//        Log.d("myTest", "static initializer: " + getAddressList().get(0));
//        Log.d("myTest", "static initializer: " + getStoreList().get(0));

        setDefaultReceiver(getRawReceiverList().get(0));
        setDefaultAddress(getRawAddressList().get(0));
        setDefaultStore(getRawStoreList().get(0));
//        defaultReceiver = getReceiverList().get(0);
//        defaultAddress = getAddressList().get(0);
//        defaultStore = getStoreList().get(0);
    }

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

    /**
     * 取得所有收件人
     */
    public static ArrayList<String> getReceiverList(){
        ArrayList<String> resultList = new ArrayList<>();
        String[] tempStr;

        for(int i = 0 ; i < receiverList.size() ; i++){
            tempStr = receiverList.get(i).split("#");
            resultList.add(tempStr[1] + "  " + tempStr[2]);
        }
        return resultList;
    }

    /**
     * 取得所有收件人的未解析格式
     */
    public static ArrayList<String> getRawReceiverList(){
        return (ArrayList<String>) receiverList;
    }

    /**
     * 取得所有地址
     */
    public static ArrayList<String> getAddressList(){
        ArrayList<String> resultList = new ArrayList<>();
        String[] tempStr;

        for(int i = 0 ; i < addressList.size() ; i++){
            tempStr = addressList.get(i).split("#");
            resultList.add(tempStr[1] + tempStr[2] + tempStr[3]);
        }
        return resultList;
    }

    /**
     * 取得所有地址的未解析格式
     */
    public static ArrayList<String> getRawAddressList(){
        return (ArrayList<String>) addressList;
    }

    /**
     * 取得所有門市
     */
    public static ArrayList<String> getStoreList(){
        ArrayList<String> resultList = new ArrayList<>();
        String[] tempStr;

        for(int i = 0 ; i < storeList.size() ; i++){
            tempStr = storeList.get(i).split("#");
            resultList.add(tempStr[1] + "  " + tempStr[2]);
        }
        return resultList;
    }

    /**
     * 取得所有門市的未解析格式
     */
    public static ArrayList<String> getRawStoreList(){
        return (ArrayList<String>) storeList;
    }

    /**
     * 新增收件者
     */
    public static void addReceiver(String newReceiver){
        receiverList.add(newReceiver);
    }

    /**
     * 刪除指定收件者
     */
    public static void deleteReceiver(int index){
        receiverList.remove(index);
    }

    /**
     * 新增宅配地址
     */
    public static void addAddress(String newAddress){
        addressList.add(newAddress);
    }

    /**
     * 刪除指定宅配地址
     */
    public static void deleteAddress(int index){
        addressList.remove(index);
    }

    /**
     * 新增門市
     */
    public static void addStore(String newStore){
        storeList.add(newStore);
    }

    /**
     * 刪除指定門市
     */
    public static void deleteStore(int index){
        storeList.remove(index);
    }

    /**
     * 取得預設收件者
     * @return
     */
    public static String getDefaultName(){
        String result;
        String[] tempStr;
        tempStr = defaultReceiver.split("#");
        result = tempStr[1];

        return result;
//        return defaultReceiver;
    }

    /**
     * 取得預設電話
     * @return
     */
    public static String getDefaultPhone(){
        String result;
        String[] tempStr;
        tempStr = defaultReceiver.split("#");
        result = tempStr[2];

        return result;
    }

    /**
     * 取得預設宅配地址
     * @return
     */
    public static String getDefaultAddress(){
        String result;
        String[] tempStr;
        tempStr = defaultAddress.split("#");
        result = tempStr[1] + tempStr[2] + tempStr[3];

        return result;
    }

    /**
     * 取得預設超商
     * @return
     */
    public static String getDefaultStore(){
        String result;
        String[] tempStr;
        tempStr = defaultStore.split("#");
        result = tempStr[1] +"  " + tempStr[2];

        return result;
    }

    public static void setDefaultReceiver(String defaultReceiver) {
        UserData.defaultReceiver = defaultReceiver;
    }

    public static void setDefaultAddress(String defaultAddress) {
        UserData.defaultAddress = defaultAddress;
    }

    public static void setDefaultStore(String defaultStore) {
        UserData.defaultStore = defaultStore;
    }
}
