package com.example.buyhome_lcn.fragment_cart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buyhome_lcn.R;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;


public class DoPay extends Fragment {
    Context context;


    Button btnPay;

    // Google Pay
    private PaymentsClient paymentsClient;
    // 1.定義 Google Pay API 版本
    private static JSONObject getBaseRequest() throws JSONException {
        return new JSONObject().put("apiVersion", 30).put("apiVersionMinor", 19);
    }
    //2.替付款服務供應商申請付款權杖
    private static JSONObject getGatewayTokenizationSpecification() throws JSONException {
        return new JSONObject() {{
            put("type", "PAYMENT_GATEWAY");
            put("parameters", new JSONObject() {{
                put("gateway", "example");
                put("gatewayMerchantId", "exampleGatewayMerchantId");
            }});
        }};
    }
    //3-1.定義支援的付款卡發卡機構
    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("AMEX")
                .put("DISCOVER")
                .put("INTERAC")
                .put("JCB")
                .put("MASTERCARD")
                .put("VISA");
    }
    //3-2.
    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put("PAN_ONLY")
                .put("CRYPTOGRAM_3DS");
    }
    //4.說明允許的付款方式
    private static JSONObject getBaseCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = new JSONObject();
        cardPaymentMethod.put("type", "CARD");

        JSONObject parameters = new JSONObject();
        parameters.put("allowedAuthMethods", getAllowedCardAuthMethods());
        parameters.put("allowedCardNetworks", getAllowedCardNetworks());
        // Optionally, you can add billing address/phone number associated with a CARD payment method.
        parameters.put("billingAddressRequired", true);

        JSONObject billingAddressParameters = new JSONObject();
        billingAddressParameters.put("format", "FULL");

        parameters.put("billingAddressParameters", billingAddressParameters);

        cardPaymentMethod.put("parameters", parameters);

        return cardPaymentMethod;
    }

//    //5.建立 PaymentsClient 執行個體
//    public static PaymentsClient createPaymentsClient(Activity activity) {
//        Wallet.WalletOptions walletOptions =
//                new Wallet.WalletOptions.Builder().setEnvironment(Constants.PAYMENTS_ENVIRONMENT).build();
//        return Wallet.getPaymentsClient(activity, walletOptions);
//    }

    //6-1.判斷是否已準備好使用 Google Pay API 付款
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<JSONObject> getIsReadyToPayRequest() {
        try {
            JSONObject isReadyToPayRequest = getBaseRequest();
            isReadyToPayRequest.put(
                    "allowedPaymentMethods", new JSONArray().put(getBaseCardPaymentMethod()));
                return Optional.of(isReadyToPayRequest);
        } catch (JSONException e) {
                return Optional.empty();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_do_pay, container, false);

        //TODO Google Pay








        //
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


//    //6-2.
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void possiblyShowGooglePayButton(){
//
//        final Optional<JSONObject> isReadyToPayJson = PaymentsUtil.getIsReadyToPayRequest();
//        if (!isReadyToPayJson.isPresent()) {
//                return;
//        }
//
//        // The call to isReadyToPay is asynchronous and returns a Task. We need to provide an
//        // OnCompleteListener to be triggered when the result of the call is known.
//        IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
//        Task<Boolean> task = paymentsClient.isReadyToPay(request);
//        task.addOnCompleteListener(this,
//                new OnCompleteListener<Boolean>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Boolean> task) {
//                        if (task.isSuccessful()) {
//                            //TODO
//                            setGooglePayAvailable(task.getResult());
//                        } else {
//                            Log.w("isReadyToPay failed", task.getException());
//                        }
//                    }
//                });
//    }



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