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
        //設定假資料，共八個商品

        //設定商品圖片
        pictureList.add(R.drawable.ic_basketball_ball);
        pictureList.add(R.drawable.ic_bicycle);
        pictureList.add(R.drawable.ic_boot);
        pictureList.add(R.drawable.ic_dress);
        pictureList.add(R.drawable.ic_hat);
        pictureList.add(R.drawable.ic_iphone_4);
        pictureList.add(R.drawable.ic_lamp);
        pictureList.add(R.drawable.ic_trench_coat);

        //商品名稱
        nameList.add("好籃球");
        nameList.add("天竺鼠車車");
        nameList.add("踢不爛靴子");
        nameList.add("美麗衣服");
        nameList.add("工程師帽子");
        nameList.add("水果手機4代");
        nameList.add("無藍光檯燈");
        nameList.add("歐爸風衣");

        //商品價格
        priceList.add(500);
        priceList.add(25000);
        priceList.add(4800);
        priceList.add(48000);
        priceList.add(200);
        priceList.add(17900);
        priceList.add(800);
        priceList.add(13000);

        //商品描述
        for(int i = 0 ; i < 8 ; i++){
            detailList.add(
                    "這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。" +
                            "這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。這是說明。");
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
