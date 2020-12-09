package com.example.buyhome_lcn.data;

import androidx.lifecycle.ViewModel;

import com.example.buyhome_lcn.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    public List<String> nameString;
    public List<String> priceString;
    public List<Integer> pictureId;

    public ShoppingCartViewModel() {
        super();

        nameString = new ArrayList<String>();
        priceString = new ArrayList<String>();
        pictureId = new ArrayList<Integer>();

        for(int i = 0 ; i < 30; i++){
            String data1 = new String("ASUS X509MA-0291GN4020 星空灰 15.6吋窄邊筆電:" + (i + 1));
            nameString.add(data1);
            String data2 = new String("$11111:" + (i + 1));
            priceString.add(data2);
            pictureId.add(R.drawable.test_item);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
