package com.example.buyhome_lcn.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    public List<String> nameString;
    public List<String> priceString;
    public List<Integer> pictureId;
    public List<Integer> amountList;
    public MutableLiveData<List<Integer>> amount;

    /**
     * 初始化
     */
    public ShoppingCartViewModel() {
        super();

        nameString = new ArrayList<String>();
        priceString = new ArrayList<String>();
        pictureId = new ArrayList<Integer>();
        amountList = new ArrayList<Integer>();
        amount = new MutableLiveData<List<Integer>>();

        for(int i = 0 ; i < 12; i++){
            String name = new String("ASUS X509MA-0291GN4020 星空灰 15.6吋窄邊筆電:" + (i + 1));
            nameString.add(name);
            String price = new String("$11111");
            priceString.add(price);
            pictureId.add(R.drawable.test_item);
            amountList.add(0);
            amount.setValue(amountList);
        }
    }

    /**
     * 商品數量加一
     */
    public void onAddAmount(int index){
        if(amount.getValue().get(index) < 99){
            amount.getValue().set(index, amount.getValue().get(index) + 1);
        }
    }

    /**
     * 商品數量減一
     */
    public void onSubAmount(int index){
        if(amount.getValue().get(index) > 0) {
            amount.getValue().set(index, amount.getValue().get(index) - 1);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
