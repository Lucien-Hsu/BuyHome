package com.lcnhsu.buyhome_lcn.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    public List<Boolean> checkedProduct;
    //計算價格相關
    public MutableLiveData<Integer> _pureTotalPrice;
    public MutableLiveData<Integer> _discount;
    public MutableLiveData<Integer> _deliveryFee;
    public MutableLiveData<Integer> _totalPrice;
    //畫面相關
    public final String DELIVERY_METHOD_NULL = "";
    public final String DELIVERY_METHOD_TO_HOME = "宅配";
    public final String DELIVERY_METHOD_TO_STORE = "超商取貨";

    public String deliveryMethod = DELIVERY_METHOD_NULL;
    //收件相關
//    public String defaultReceiver;
//    public String defaultAddress;
//    public String defaultStore;
//    public List<String> receiverList;
//    public List<String> addressList;
//    public List<String> storeList;

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
        checkedProduct = new ArrayList<Boolean>();
//        defaultReceiver = "";
//        defaultAddress = "";
//        defaultStore = "";
        //計算價格相關
        _pureTotalPrice = new MutableLiveData<Integer>();
        _discount = new MutableLiveData<Integer>();
        _deliveryFee = new MutableLiveData<Integer>();
        _totalPrice = new MutableLiveData<Integer>();
        //收件相關
//        receiverList = new ArrayList<String>();
//        addressList = new ArrayList<String>();
//        storeList = new ArrayList<String>();

        //設定資料
        //TODO 串真資料
        initInfo();

        //計算價格相關
        _pureTotalPrice.setValue(0);
        _discount.setValue(0);
        _deliveryFee.setValue(60);
        _totalPrice.setValue(_pureTotalPrice.getValue() - _discount.getValue() - _deliveryFee.getValue());

        //收件相關
//        receiverList.add("# # ");
//        storeList.add("# # ");
//        addressList.add("# # # ");
//        defaultReceiver = receiverList.get(0);
//        defaultAddress = addressList.get(0);
//        defaultStore = storeList.get(0);

//        addReceiver("# # ");
//        addStore("# # ");
//        addAddress("# # # ");
//        defaultReceiver = getReceiverList().get(0);
//        defaultAddress = getAddressList().get(0);
//        defaultStore = getStoreList().get(0);
    }

    /**
     * 設定資料
     */
    private void initInfo() {
        nameList = ShoppingCartData.getNameList();
        priceList = ShoppingCartData.getPriceList();
        pictureList = ShoppingCartData.getPictureList();

        for(int i = 0 ; i < 10; i++){
            amountList.add(0);
            _amountList.setValue(amountList);
            checkedProduct.add(false);
        }
    }

    /**
     * 刪除指定商品
     */
    public void deleteProduct(int index){
//        nameList.remove(index);
//        priceList.remove(index);
//        pictureList.remove(index);
//        amountList.remove(index);
//        _amountList.setValue(amountList);
//        checkedProduct.remove(index);

        //刪除資料源的商品資料
        ShoppingCartData.removeProduct(index);

    }

    /**
     * 指定商品數量加一
     */
    public void onAddAmount(int index){
        if(_amountList.getValue().get(index) < 99){
            _amountList.getValue().set(index, _amountList.getValue().get(index) + 1);
        }

        if(_amountList.getValue().get(index) > 0){
            checkedProduct.set(index, true);
        }
    }

    /**
     * 指定商品數量減一
     */
    public void onSubAmount(int index){
        if(_amountList.getValue().get(index) > 0) {
            _amountList.getValue().set(index, _amountList.getValue().get(index) - 1);
        }

        if(_amountList.getValue().get(index) > 0){
            checkedProduct.set(index, true);
        }else{
            checkedProduct.set(index, false);
        }
    }

    /**
     * 計算總商品金額
     */
    public void setPureTotalPrice(){
        int total = 0;
        for(int i = 0 ; i < priceList.size() ; i++){
            if(priceList.get(i) != 0 && amountList.get(i) != 0){
                total += priceList.get(i) * amountList.get(i);
            }
        }
        _pureTotalPrice.setValue(total);
        setTotalPrice();
    }

    /**
     * 取得總商品金額
     */
    public Integer getPureTotalPrice(){
        return _pureTotalPrice.getValue();
    }

    /**
     * 設定折扣
     */
    public void setDiscount(int newValue){
        _discount.setValue(newValue);
        setTotalPrice();
    }

    /**
     * 取得折扣
     */
    public Integer getDiscount(){
        return _discount.getValue();
    }

    /**
     * 取得總價
     */
    public void setTotalPrice(){
        int totalPrice = _pureTotalPrice.getValue() - _discount.getValue() + _deliveryFee.getValue();
        if(totalPrice <= 0){
            totalPrice = 0;
        }
        _totalPrice.setValue(totalPrice);
    }

    /**
     * 取得要結帳的商品名稱清單
     */
    public List<String> getCheckedNameList(){
        List<String> tempList = new ArrayList<String>();
        for(int i = 0 ; i < nameList.size() ; i++){
            if(checkedProduct.get(i)){
                tempList.add(nameList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 取得要結帳的商品價格清單
     */
    public List<Integer> getCheckedPriceList(){
        List<Integer> tempList = new ArrayList<Integer>();
        for(int i = 0 ; i < priceList.size() ; i++){
            if(checkedProduct.get(i)){
                tempList.add(priceList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 取得要結帳的商品圖片清單
     */
    public List<Integer> getCheckedPictureList(){
        List<Integer> tempList = new ArrayList<Integer>();
        for(int i = 0 ; i < pictureList.size() ; i++){
            if(checkedProduct.get(i)){
                tempList.add(pictureList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 取得要結帳的商品數量清單
     */
    public List<Integer> getCheckedAmountList(){
        List<Integer> tempList = new ArrayList<Integer>();
        for(int i = 0 ; i < amountList.size() ; i++){
            if(checkedProduct.get(i)){
                tempList.add(amountList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 取得總金額
     */
    public Integer getTotalPrice(){
        return _totalPrice.getValue();
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

    public String getDeliveryMethod() {
        return UserData.getPayMethod();
    }

    public void setDeliveryMethod(String deliveryMethod) {
        UserData.setPayMethod(deliveryMethod);
    }

    /**
     * 取得預設收件者
     * @return
     */
    public String getDefaultName(){
//        String result;
//        String[] tempStr;
//        tempStr = defaultReceiver.split("#");
//        result = tempStr[1];
//
//        return result;
        return UserData.getDefaultName();
    }

    /**
     * 取得預設電話
     * @return
     */
    public String getDefaultPhone(){
//        String result;
//        String[] tempStr;
//        tempStr = defaultReceiver.split("#");
//        result = tempStr[2];
//
//        return result;
        return UserData.getDefaultPhone();
    }

    /**
     * 取得預設宅配地址
     * @return
     */
    public String getDefaultAddress(){
//        String result;
//        String[] tempStr;
//        tempStr = defaultAddress.split("#");
//        result = tempStr[1] + tempStr[2] + tempStr[3];
//
//        return result;
        return UserData.getDefaultAddress();
    }

    /**
     * 取得預設超商
     * @return
     */
    public String getDefaultStore(){
//        String result;
//        String[] tempStr;
//        tempStr = defaultStore.split("#");
//        result = tempStr[1] +"  " + tempStr[2];
//
//        return result;
        return UserData.getDefaultStore();
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

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
