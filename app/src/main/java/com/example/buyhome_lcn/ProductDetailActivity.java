package com.example.buyhome_lcn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.buyhome_lcn.data.ProductData;
import com.example.buyhome_lcn.data.ShoppingCartData;
import com.example.buyhome_lcn.databinding.ActivityProductDetailBinding;
import com.example.buyhome_lcn.databinding.FragmentHomeBinding;

public class ProductDetailActivity extends AppCompatActivity {
    private Context context;

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
                    Toast.makeText(context, "成功加入購物車！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "此商品已在購物車！", Toast.LENGTH_SHORT).show();
                }
            }
        });

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