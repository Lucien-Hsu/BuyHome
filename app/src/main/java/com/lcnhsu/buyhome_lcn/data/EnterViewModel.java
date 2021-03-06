package com.lcnhsu.buyhome_lcn.data;

import androidx.lifecycle.ViewModel;

import com.lcnhsu.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class EnterViewModel extends ViewModel {
    public List<String> nameList;
    public List<Integer> priceList;
    public List<Integer> pictureList;

    /**
     * 初始化
     */
    public EnterViewModel() {
        //初始化
        nameList = new ArrayList<>();
        priceList = new ArrayList<>();
        pictureList = new ArrayList<>();

        //設定假資料
//        initInfoWithHardcode();

        //設定真資料
        initInfo();
    }

    private void initInfo() {
        nameList = ProductData.getNameList();
        priceList = ProductData.getPriceList();
        pictureList = ProductData.getPictureList();
    }

    private void initInfoWithHardcode() {
        //設定假資料
        for(int i = 0 ; i < 6 ; i++){
            nameList.add("商品" + (i + 1));
            priceList.add((i + 1) * 100);
            pictureList.add(R.drawable.test_item);
        }
    }

    public List<String> getNameList() {
        return nameList;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public List<Integer> getPictureList() {
        return pictureList;
    }
}
