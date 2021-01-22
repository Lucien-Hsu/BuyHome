package com.example.buyhome_lcn.data;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    public static final int NO_PRODUCT_CHECKED = 999;

    public static List<String> nameList;
    public static List<Integer> priceList;
    public static List<Integer> pictureList;
    public static List<String> detailList;
    //儲存使用者當前查看的商品編號
    public static int checkedProductID;

    //初始化
    static  {
        nameList = new ArrayList<String>();
        priceList = new ArrayList<Integer>();
        pictureList = new ArrayList<Integer>();
        detailList = new ArrayList<String>();
        checkedProductID = NO_PRODUCT_CHECKED;

        initInfoWithHardcode();

        //TODO 假資料要換真資料
        initInfo();
    }

    private static void initInfo() {

    }

    //建立資料
    private static void initInfoWithHardcode() {
        //設定假資料
        for(int i = 0 ; i < 6 ; i++){
            nameList.add("商品" + (i + 1));
            priceList.add((i + 1) * 100);
            pictureList.add(R.drawable.test_item);
            detailList.add("這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。");
        }
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

    public static List<String> getDetailList() {
        return detailList;
    }

    public static int getCheckedProductID() {
        return checkedProductID;
    }

    public static void setCheckedProductID(int checkedProductID) {
        ProductData.checkedProductID = checkedProductID;
    }
}
