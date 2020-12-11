package com.example.buyhome_lcn.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    public List<String> nameString;
    public List<Integer> priceList;
    public List<Integer> pictureId;
    public List<Integer> amountList;
    public MutableLiveData<List<Integer>> amount;
    public MutableLiveData<Integer> pureTotalPrice;
    public MutableLiveData<Integer> discount;
    public MutableLiveData<Integer> deliveryFee;
    public MutableLiveData<Integer> totalPrice;

    /**
     * 初始化
     */
    public ShoppingCartViewModel() {
        super();


        nameString = new ArrayList<String>();
        priceList = new ArrayList<Integer>();
        pictureId = new ArrayList<Integer>();
        amountList = new ArrayList<Integer>();
        amount = new MutableLiveData<List<Integer>>();
        pureTotalPrice = new MutableLiveData<Integer>();
        discount = new MutableLiveData<Integer>();
        deliveryFee = new MutableLiveData<Integer>();
        totalPrice = new MutableLiveData<Integer>();

        pureTotalPrice.setValue(0);
        discount.setValue(100);
        deliveryFee.setValue(60);
        totalPrice.setValue(pureTotalPrice.getValue() - discount.getValue() - deliveryFee.getValue());

        for(int i = 0 ; i < 12; i++){
            String name = new String("ASUS X509MA-0291GN4020 星空灰 15.6吋窄邊筆電:" + (i + 1));
            nameString.add(name);
            Integer price = new Integer(1001);
            priceList.add(price);
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

    public void setPureTotalPrice(){
        int total = 0;
        for(int i = 0 ; i < priceList.size() ; i++){
            total += priceList.get(i) * amountList.get(i);
        }
        pureTotalPrice.setValue(total);
    }

    public Integer getPureTotalPrice(){
        return pureTotalPrice.getValue();
    }

    public void setTotalPrice(){
        totalPrice.setValue(pureTotalPrice.getValue() - discount.getValue() - deliveryFee.getValue());
    }

    public Integer getTotalPrice(){
        return totalPrice.getValue();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
