package com.example.buyhome_lcn.fragment_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.buyhome_lcn.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class CheckDeal extends Fragment {
    ImageButton imgBtnDelivery;
    Button btnGoDoPay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_deal, container, false);

        //[按鈕]  前往"設定寄送方式"
        imgBtnDelivery = view.findViewById(R.id.imgbtn_delivery);
        imgBtnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 隱藏鍵盤
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                Navigation.findNavController(view).navigate(R.id.action_checkDeal_to_checkDelivery);
            }
        });

        //[按鈕]  前往付款
        btnGoDoPay = view.findViewById(R.id.btn_go_dopay);
        btnGoDoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_checkDeal_to_doPay);
            }
        });


        return view;
    }
}