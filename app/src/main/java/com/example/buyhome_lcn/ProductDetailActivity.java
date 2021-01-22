package com.example.buyhome_lcn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.buyhome_lcn.data.ProductData;
import com.example.buyhome_lcn.data.ShoppingCartData;
import com.example.buyhome_lcn.databinding.ActivityProductDetailBinding;
import com.example.buyhome_lcn.databinding.FragmentHomeBinding;

public class ProductDetailActivity extends AppCompatActivity {

    private ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ViewBinding
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //顯示相關資訊
        binding.imgProductPhoto.setImageResource(ProductData.getPictureList().get(ProductData.getCheckedProductID()));
        binding.tvProductName.setText(ProductData.getNameList().get(ProductData.getCheckedProductID()));
        binding.tvProducePrice.setText("$" + ProductData.getPriceList().get(ProductData.getCheckedProductID()).toString());
        binding.tvProduceDetail.setText(ProductData.getDetailList().get(ProductData.getCheckedProductID()));

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartData.addProduct(
                        ProductData.getCheckedProductID(),
                        ProductData.getPictureList().get(ProductData.getCheckedProductID()),
                        ProductData.getNameList().get(ProductData.getCheckedProductID()),
                        ProductData.getPriceList().get(ProductData.getCheckedProductID())
                );
            }
        });

    }
}