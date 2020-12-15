package com.example.buyhome_lcn.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    //TODO 封裝變數
    //商品相關
    public List<String> nameList;
    public List<Integer> priceList;
    public List<Integer> pictureList;
    public List<Integer> amountList;
    public MutableLiveData<List<Integer>> _amountList;
    //計算價格相關
    public MutableLiveData<Integer> _pureTotalPrice;
    public MutableLiveData<Integer> _discount;
    public MutableLiveData<Integer> _deliveryFee;
    public MutableLiveData<Integer> _totalPrice;
    //畫面相關
    private final int DELIVERY_METHOD_NULL = 0;
    private final int DELIVERY_METHOD_TO_HOME = 1;
    private final int DELIVERY_METHOD_TO_STORE = 2;
    public int deliveryMethod = DELIVERY_METHOD_NULL;

    //收件相關
    public List<String> receiverList;
    public List<String> addressList;
    public List<String> storeList;

    /**
     * 初始化
     */
    public ShoppingCartViewModel() {
        super();
        //[配置記憶體]
        //商品相關
        nameList = new ArrayList<String>();
        priceList = new ArrayList<Integer>();
        pictureList = new ArrayList<Integer>();
        amountList = new ArrayList<Integer>();
        _amountList = new MutableLiveData<List<Integer>>();
        //計算價格相關
        _pureTotalPrice = new MutableLiveData<Integer>();
        _discount = new MutableLiveData<Integer>();
        _deliveryFee = new MutableLiveData<Integer>();
        _totalPrice = new MutableLiveData<Integer>();
        //收件相關
        receiverList = new ArrayList<String>();
        addressList = new ArrayList<String>();
        storeList = new ArrayList<String>();

        //[賦值]
        //TODO 串真資料
        //目前用假資料
        _pureTotalPrice.setValue(0);
        _discount.setValue(0);
        _deliveryFee.setValue(60);
        _totalPrice.setValue(_pureTotalPrice.getValue() - _discount.getValue() - _deliveryFee.getValue());
        for(int i = 0 ; i < 12; i++){
            String name = new String("ASUS X509MA-0291GN4020 星空灰 15.6吋窄邊筆電:" + (i + 1));
            nameList.add(name);
            Integer price = new Integer(1001);
            priceList.add(price);
            pictureList.add(R.drawable.test_item);
            amountList.add(0);
            _amountList.setValue(amountList);
        }
        //收件相關
        receiverList.add("李先生");
        receiverList.add("王小姐");
        storeList.add("#全家#楊梅幼獅店");
        storeList.add("#萊爾富#平鎮復梅店");
        addressList.add("#台北市#信義區#信義路五段7號89樓");
        addressList.add("#桃園市#楊梅區#幼獅路二段3號");
    }

    /**
     * 商品數量加一
     */
    public void onAddAmount(int index){
        if(_amountList.getValue().get(index) < 99){
            _amountList.getValue().set(index, _amountList.getValue().get(index) + 1);
        }
    }

    /**
     * 商品數量減一
     */
    public void onSubAmount(int index){
        if(_amountList.getValue().get(index) > 0) {
            _amountList.getValue().set(index, _amountList.getValue().get(index) - 1);
        }
    }

    public void setPureTotalPrice(){
        int total = 0;
        for(int i = 0 ; i < priceList.size() ; i++){
            total += priceList.get(i) * amountList.get(i);
        }
        _pureTotalPrice.setValue(total);
        setTotalPrice();
    }

    public Integer getPureTotalPrice(){
        return _pureTotalPrice.getValue();
    }

    public void setDiscount(int newValue){
        _discount.setValue(newValue);
        setTotalPrice();
    }

    public Integer getDiscount(){
        return _discount.getValue();
    }

    public void setTotalPrice(){
        _totalPrice.setValue(_pureTotalPrice.getValue() - _discount.getValue() - _deliveryFee.getValue());
    }

    public Integer getTotalPrice(){
        return _totalPrice.getValue();
    }

    public ArrayList<String> getAddressList(){
        ArrayList<String> resultList = new ArrayList<>();
        String[] tempStr;

        for(int i = 0 ; i < addressList.size() ; i++){
            tempStr = addressList.get(i).split("#");
            resultList.add(tempStr[1] + tempStr[2] + tempStr[3]);
        }
        return resultList;
    }

    public void addAddress(String newAddress){
        addressList.add(newAddress);
    }












    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
