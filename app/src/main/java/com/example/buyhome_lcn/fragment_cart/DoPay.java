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
import android.widget.Toast;

import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.util.PaymentsUtil;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;
import java.util.concurrent.Executor;


public class DoPay extends Fragment {
    Context context;

    //1-1.用戶端，用於與 Google Pay API 互動
    private PaymentsClient paymentsClient;

    //8-1.宣告按鈕
    View googlePayButton;

    //8-6.交易序號，用於追蹤該筆交易
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 991;
    //8-7.運費
    private static final long SHIPPING_COST_CENTS = 90 * PaymentsUtil.CENTS_IN_A_UNIT.longValue();
//    Button btnPay;

//    // Google Pay
//    private PaymentsClient paymentsClient;
//    // 1.定義 Google Pay API 版本
//    private static JSONObject getBaseRequest() throws JSONException {
//        return new JSONObject().put("apiVersion", 30).put("apiVersionMinor", 19);
//    }
//    //2.替付款服務供應商申請付款權杖
//    private static JSONObject getGatewayTokenizationSpecification() throws JSONException {
//        return new JSONObject() {{
//            put("type", "PAYMENT_GATEWAY");
//            put("parameters", new JSONObject() {{
//                put("gateway", "example");
//                put("gatewayMerchantId", "exampleGatewayMerchantId");
//            }});
//        }};
//    }
//    //3-1.定義支援的付款卡發卡機構
//    private static JSONArray getAllowedCardNetworks() {
//        return new JSONArray()
//                .put("AMEX")
//                .put("DISCOVER")
//                .put("INTERAC")
//                .put("JCB")
//                .put("MASTERCARD")
//                .put("VISA");
//    }
//    //3-2.
//    private static JSONArray getAllowedCardAuthMethods() {
//        return new JSONArray()
//                .put("PAN_ONLY")
//                .put("CRYPTOGRAM_3DS");
//    }
//    //4.說明允許的付款方式
//    private static JSONObject getBaseCardPaymentMethod() throws JSONException {
//        JSONObject cardPaymentMethod = new JSONObject();
//        cardPaymentMethod.put("type", "CARD");
//
//        JSONObject parameters = new JSONObject();
//        parameters.put("allowedAuthMethods", getAllowedCardAuthMethods());
//        parameters.put("allowedCardNetworks", getAllowedCardNetworks());
//        // Optionally, you can add billing address/phone number associated with a CARD payment method.
//        parameters.put("billingAddressRequired", true);
//
//        JSONObject billingAddressParameters = new JSONObject();
//        billingAddressParameters.put("format", "FULL");
//
//        parameters.put("billingAddressParameters", billingAddressParameters);
//
//        cardPaymentMethod.put("parameters", parameters);
//
//        return cardPaymentMethod;
//    }

//    //5.建立 PaymentsClient 執行個體
//    public static PaymentsClient createPaymentsClient(Activity activity) {
//        Wallet.WalletOptions walletOptions =
//                new Wallet.WalletOptions.Builder().setEnvironment(Constants.PAYMENTS_ENVIRONMENT).build();
//        return Wallet.getPaymentsClient(activity, walletOptions);
//    }

//    //6-1.判斷是否已準備好使用 Google Pay API 付款
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public static Optional<JSONObject> getIsReadyToPayRequest() {
//        try {
//            JSONObject isReadyToPayRequest = getBaseRequest();
//            isReadyToPayRequest.put(
//                    "allowedPaymentMethods", new JSONArray().put(getBaseCardPaymentMethod()));
//                return Optional.of(isReadyToPayRequest);
//        } catch (JSONException e) {
//                return Optional.empty();
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = requireActivity();

        View view = inflater.inflate(R.layout.fragment_do_pay, container, false);

        //TODO Google Pay
        //1-2.初始化 Google Pay API 用戶端
        paymentsClient = PaymentsUtil.createPaymentsClient((Activity) context);

        //
//        btnPay = view.findViewById(R.id.btn_pay);
//        btnPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO Google Pay 付款
//                doPay();
//            }
//        });

        //8-2.[按鈕] 取得按鈕
        googlePayButton = view.findViewById(R.id.googlePayButton);
        googlePayButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPayment(view);
                    }
                });

        //8-3.確認使用者能夠使用 Google Pay 並顯示按鈕
        possiblyShowGooglePayButton();

        return view;
    }

    //8-4.確認使用者能夠使用 Google Pay ，若可以則顯示按鈕
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void possiblyShowGooglePayButton() {

        final Optional<JSONObject> isReadyToPayJson = PaymentsUtil.getIsReadyToPayRequest();
        if (!isReadyToPayJson.isPresent()) {
            return;
        }

        // The call to isReadyToPay is asynchronous and returns a Task. We need to provide an
        // OnCompleteListener to be triggered when the result of the call is known.
        //確認是否可付款
        IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
        //若可付款，則顯示付款按鈕
        Task<Boolean> task = paymentsClient.isReadyToPay(request);
        task.addOnCompleteListener((Activity)context,
                new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            setGooglePayAvailable(task.getResult());
                        } else {
                            Log.w("isReadyToPay failed", task.getException());
                        }
                    }
                });
    }
    /**
     * If isReadyToPay returned {@code true}, show the button and hide the "checking" text. Otherwise,
     * notify the user that Google Pay is not available. Please adjust to fit in with your current
     * user flow. You are not required to explicitly let the user know if isReadyToPay returns {@code
     * false}.
     *
     * @param available isReadyToPay API response.
     */
    private void setGooglePayAvailable(boolean available) {
        if (available) {
            googlePayButton.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(context, R.string.googlepay_status_unavailable, Toast.LENGTH_LONG).show();
        }
    }

    //8-5.顯示 Google Pay 頁面
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void requestPayment(View view) {

        // Disables the button to prevent multiple clicks.
        googlePayButton.setClickable(false);

        // The price provided to the API should include taxes and shipping.
        // This price is not displayed to the user.
        //價格會包含稅率與運費，僅用於結帳，不會顯示給使用者知道。
//        try {

        //[設定價格]
        //範例是取得當前顯示的商品再取得價格
        //這裡為了方便，直接給予價格。
        double garmentPrice = 500;
//            double garmentPrice = selectedGarment.getDouble("price");

        long garmentPriceCents = Math.round(garmentPrice * PaymentsUtil.CENTS_IN_A_UNIT.longValue());
        long priceCents = garmentPriceCents + SHIPPING_COST_CENTS;

        Optional<JSONObject> paymentDataRequestJson = PaymentsUtil.getPaymentDataRequest(priceCents);
        if (!paymentDataRequestJson.isPresent()) {
            return;
        }

        PaymentDataRequest request =
                PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());

        // Since loadPaymentData may show the UI asking the user to select a payment method, we use
        // AutoResolveHelper to wait for the user interacting with it. Once completed,
        // onActivityResult will be called with the result.
        // 這裡真正顯示結帳頁面
        if (request != null) {
            AutoResolveHelper.resolveTask(
                    paymentsClient.loadPaymentData(request),
                    (Activity) context, LOAD_PAYMENT_DATA_REQUEST_CODE);
        }
//        } catch (JSONException e) {
//            throw new RuntimeException("The price cannot be deserialized from the JSON object.");
//        }
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