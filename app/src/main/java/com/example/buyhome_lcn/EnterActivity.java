package com.example.buyhome_lcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterActivity extends AppCompatActivity {
    Context context;
    Button btnToShoppingCart, btnToAccountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        context = this;

        btnToShoppingCart = findViewById(R.id.btn_to_shoppingcart);
        btnToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_shopping_cart.class);
                startActivity(intent);
            }
        });

        btnToAccountInfo = findViewById(R.id.btn_to_memberarea);
        btnToAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MemberAreaActivity.class);
                startActivity(intent);
            }
        });

    }
}