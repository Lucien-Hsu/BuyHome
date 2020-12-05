package com.example.buyhome_lcn.fragment_cart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buyhome_lcn.R;


public class DoPay extends Fragment {
    Context context;

    Button btnPay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_do_pay, container, false);

        btnPay = view.findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Google Pay 付款
                doPay();
            }
        });

        return view;
    }

    /**
     * 付款處理
     */
    private void doPay() {
        //TODO 處理付款

        //[彈出視窗]  付款成功後彈出此視窗
        View dialogView = getLayoutInflater().inflate(R.layout.alertdialog_dopay, null);
        final AlertDialog dialog = new AlertDialog
                .Builder(context)
                .setView(dialogView).show();

        //[按鈕]  關閉 Activity
        Button btnOK = dialog.findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                dialog.dismiss();
            }
        });

    }
}