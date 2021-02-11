package com.example.buyhome_lcn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.buyhome_lcn.data.ProductData;
import com.example.buyhome_lcn.data.ShoppingCartData;
import com.example.buyhome_lcn.databinding.ActivityProductDetailBinding;
import com.example.buyhome_lcn.databinding.FragmentHomeBinding;

public class ProductDetailActivity extends AppCompatActivity {
    private Context context;

    private static final String PREF = "PREF";
    private static final String PREF_CART_NAME_LIST = "PREF_CART_NAME_LIST";
    private static final String PREF_CART_PRICE_LIST = "PREF_CART_PRICE_LIST";
    private static final String PREF_CART_PICTURE_LIST = "PREF_CART_PICTURE_LIST";
    private static final String PREF_CART_PRODUCTID_LIST = "PREF_CART_PRODUCTID_LIST";

    private ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ViewBinding
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        context = this;

        //創建ActionBar物件
        ActionBar bar = getSupportActionBar();
        //設定ActionBar顯示返回鍵
        bar.setDisplayHomeAsUpEnabled(true);

        //顯示相關資訊
        binding.imgProductPhoto.setImageResource(ProductData.getPictureList().get(ProductData.getCheckedProductID()));
        binding.tvProductName.setText(ProductData.getNameList().get(ProductData.getCheckedProductID()));
        binding.tvProducePrice.setText("$" + ProductData.getPriceList().get(ProductData.getCheckedProductID()).toString());
        binding.tvProduceDetail.setText(ProductData.getDetailList().get(ProductData.getCheckedProductID()));

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加入商品至購物車
                Boolean canAddProduct = ShoppingCartData.addProduct(
                        ProductData.getCheckedProductID(),
                        ProductData.getPictureList().get(ProductData.getCheckedProductID()),
                        ProductData.getNameList().get(ProductData.getCheckedProductID()),
                        ProductData.getPriceList().get(ProductData.getCheckedProductID())
                );

                if(canAddProduct){
//                    Toast.makeText(context, "成功加入購物車！", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, ProductData.getCheckedProductID() + "成功加入購物車！", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "成功加入購物車！", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, "加入購物車的商品" + ShoppingCartData.getProductIDList(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "此商品已在購物車！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        //儲存資料至設備
        SharedPreferences sp = getSharedPreferences(PREF, 0);
        //設定為編輯模式，並放入資料鍵值，最後commit()才會寫入
        //儲存購物車商品編號清單
        sp.edit()
                .putString(PREF_CART_PRODUCTID_LIST, ShoppingCartData.getProductIDListString())
                .apply();

        Log.d("myTest", "儲存的商品編號: " + ShoppingCartData.getProductIDListString());
    }

    @Override
    protected void onDestroy() {
        ProductData.setCheckedProductID(ProductData.NO_PRODUCT_CHECKED);
        super.onDestroy();
    }

    //設定返回鍵
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}