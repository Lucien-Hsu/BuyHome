package com.example.buyhome_lcn.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ShoppingCartData {

    public static void setHasInitProduct(Boolean hasInitProduct) {
        ShoppingCartData.hasInitProduct = hasInitProduct;
    }

    //確認是否已初始化購物車商品
    public static Boolean hasInitProduct = false;

    //商品資訊
    public static List<String> nameList;
    public static List<Integer> priceList;
    public static List<Integer> pictureList;
    private static List<Integer> productIDList;

    static {
        //配置記憶體
        nameList = new ArrayList<String>();
        priceList = new ArrayList<Integer>();
        pictureList = new ArrayList<Integer>();
        productIDList = new ArrayList<Integer>();
    }

    public static void setNameList(List<String> nameList) {
        ShoppingCartData.nameList = nameList;
    }

    public static void setPriceList(List<Integer> priceList) {
        ShoppingCartData.priceList = priceList;
    }

    public static void setPictureList(List<Integer> pictureList) {
        ShoppingCartData.pictureList = pictureList;
    }

    public static List<String> getNameList() {
        return nameList;
    }

    public static List<Integer> getPriceList() {
        return priceList;
    }

    public static List<Integer> getPictureList() {
        return pictureList;
    }

    public static void setProductIDList(List<Integer> productIDList) {
        ShoppingCartData.productIDList = productIDList;
    }

    //以字串設定 productIDList
    public static void setProductIDList(String productIDListString) {
        StringTokenizer st = new StringTokenizer(productIDListString, ",");
        List<Integer> savedList = new ArrayList();
        while(st.hasMoreTokens()){
            Integer id = Integer.parseInt(st.nextToken());
            savedList.add(id);
//            Log.d("myTest", "ShoppingCartData 解析出的商品編號: " + id);
        }

        setProductIDList(savedList);
    }

    public static List<Integer> getProductIDList() {
        return productIDList;
    }


    public static String getProductIDListString() {
        //將購物車內商品ID轉為字串
        StringBuilder str = new StringBuilder();
//        Log.d("myTest", "目前商品數量(ID): " + getProductIDList().size());
        for (int i = 0; i < productIDList.size(); i++) {
            str.append(productIDList.get(i)).append(",");
        }
        return str.toString();
    }

    public static Boolean addProduct(Integer productID, Integer picture, String name, Integer price) {
        if (!productIDList.contains(productID)) {

            //若欲加入購物車的商品未在購物車中加入此商品
            pictureList.add(picture);
            nameList.add(name);
            priceList.add(price);

            //儲存商品編號
            productIDList.add(productID);

            return true;
        }else{
            return false;
        }
    }

    public static void initProduct(Integer productID, Integer picture, String name, Integer price) {
            //加入購物車商品
            pictureList.add(picture);
            nameList.add(name);
            priceList.add(price);
    }

    public static Boolean removeProduct(int index){

        if(index >= 0){
            pictureList.remove(index);
            nameList.remove(index);
            priceList.remove(index);
            productIDList.remove(index);

            return true;
        }else{
            return false;
        }

    }

}
