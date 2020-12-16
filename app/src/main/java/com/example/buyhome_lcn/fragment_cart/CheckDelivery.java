package com.example.buyhome_lcn.fragment_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.data.ShoppingCartViewModel;


public class CheckDelivery extends Fragment {
    private ShoppingCartViewModel viewModel;

    TextView tvReceiver, tvPhone, tvAddress;
    Button btnGoBack, btnChooseReceiver, btnChooseAddress, btnChooseStore;
    CheckBox cbHome, cbStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_delivery, container, false);

        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(ShoppingCartViewModel.class);

        chooseDeliveryWay(view);

        tvAddress = view.findViewById(R.id.tv_home_delivery_adress);
        tvAddress.setText(viewModel.getDefaultAddress());

        tvReceiver = view.findViewById(R.id.tv_recipient_name_display);
        tvReceiver.setText(viewModel.getDefaultName());

        tvPhone = view.findViewById(R.id.tv_recipient_phone_display);
        tvPhone.setText(viewModel.getDefaultPhone());


        //[按鈕]  設定收件者
        btnChooseReceiver = view.findViewById(R.id.btn_choose_receiver);
        btnChooseReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_checkDelivery_to_chooseReceiver);
            }
        });

        //[按鈕]  設定宅配地址
        btnChooseAddress = view.findViewById(R.id.btn_choose_address);
        btnChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_checkDelivery_to_chooseAddress);
            }
        });

        //[按鈕]  設定取貨門市
        btnChooseStore = view.findViewById(R.id.btn_choose_store);
        btnChooseStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_checkDelivery_to_chooseStore);
            }
        });

        //[按鈕]  返回"確認付款"頁面
        btnGoBack = view.findViewById(R.id.btn_gobackto_checkdeal);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出BackStack，此處便不會記錄到堆疊中。否則之後按返回建會回到這頁。
                Navigation.findNavController(view).popBackStack();
            }

        });

        return view;
    }

    /**
     * 選擇運送方式
     * CheckBox 只能有一個被勾選
     */
    private void chooseDeliveryWay(View view) {
        //取得元件
        cbStore = view.findViewById(R.id.cb_store_pickup);
        cbHome = view.findViewById(R.id.cb_home_delivery);

        //設定初始勾選狀態
        switch (viewModel.deliveryMethod){
            case 0:
                cbHome.setChecked(false);
                cbStore.setChecked(false);
                break;
            case 1:
                cbHome.setChecked(true);
                cbStore.setChecked(false);
                break;
            case 2:
                cbHome.setChecked(false);
                cbStore.setChecked(true);
                break;
        }

        cbHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //設定為宅配
                cbStore.setChecked(!b);

                if(cbHome.isChecked() && !cbStore.isChecked()){
                    viewModel.deliveryMethod = 1;
                }else if(!cbHome.isChecked() && cbStore.isChecked()){
                    viewModel.deliveryMethod = 2;
                }
                Log.d("myTest", "deliveryMethod: " + viewModel.deliveryMethod);
            }
        });

        cbStore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //設定為超商取貨
                cbHome.setChecked(!b);

                if(cbHome.isChecked() && !cbStore.isChecked()){
                    viewModel.deliveryMethod = 1;
                }else if(!cbHome.isChecked() && cbStore.isChecked()){
                    viewModel.deliveryMethod = 2;
                }
                Log.d("myTest", "deliveryMethod: " + viewModel.deliveryMethod);
            }
        });
    }
}