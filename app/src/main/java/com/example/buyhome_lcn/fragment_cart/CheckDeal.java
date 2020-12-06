package com.example.buyhome_lcn.fragment_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.buyhome_lcn.R;

public class CheckDeal extends Fragment {
    ImageButton imgBtnDelivery;
    EditText etDiscountCode;
    TextView tvDiscountCodeTip;
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

        //初始化折扣碼提示
        tvDiscountCodeTip = view.findViewById(R.id.tv_discount_code_tip);
        tvDiscountCodeTip.setVisibility(View.INVISIBLE);

        //[輸入]  折扣碼
        etDiscountCode = view.findViewById(R.id.et_discount_code);
        etDiscountCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                tvDiscountCodeTip.setVisibility(View.VISIBLE);
                //TODO 檢查折扣碼
                checkDiscountCode(editable);
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

    /**
     * 檢查折扣碼
     * @param editable
     */
    private void checkDiscountCode(Editable editable) {
        if(editable.toString().equals("1234")){
            tvDiscountCodeTip.setText("OK");
            tvDiscountCodeTip.setBackgroundResource(R.drawable.frame_03);
        }else {
            tvDiscountCodeTip.setText("－");
            tvDiscountCodeTip.setBackgroundResource(R.drawable.frame_04);
        }
    }
}