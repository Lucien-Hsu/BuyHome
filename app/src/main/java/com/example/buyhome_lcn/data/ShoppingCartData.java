package com.example.buyhome_lcn.data;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartData {

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

    public static List<String> getNameList() {
        return nameList;
    }

    public static List<Integer> getPriceList() {
        return priceList;
    }

    public static List<Integer> getPictureList() {
        return pictureList;
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
