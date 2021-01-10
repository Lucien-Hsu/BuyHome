package com.example.buyhome_lcn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EnterActivity extends AppCompatActivity {
    Context context;
    Button btnToShoppingCart, btnToAccountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        context = this;

        //取得 BottomNavigationView 元件
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom);
        //設定 ActionBar
        //參數名稱需符合導航圖與 menu 中的 id
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.specialOfferFragment, R.id.messageFragment)
                .build();
        //設定導航控制器
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //把 ActionBar 的設定和導航控制器連接
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //連接導航 View 元件和導航控制器
        NavigationUI.setupWithNavController(navigation, navController);

//        btnToShoppingCart = findViewById(R.id.btn_to_shoppingcart);
//        btnToShoppingCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MainActivity_shopping_cart.class);
//                startActivity(intent);
//            }
//        });

//        btnToAccountInfo = findViewById(R.id.btn_to_memberarea);
//        btnToAccountInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MemberAreaActivity.class);
//                startActivity(intent);
//            }
//        });

    }


    //此方法會在創造 menu 時 inflate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflation
        MenuInflater inflater = getMenuInflater();
        //引數一為 menu 資源檔，引數二為 onCreateOptionsMenu 之參數 menu。
        //這邊是把menu實體與它的view連結起來
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //當點擊選項時會呼叫此方法，並傳入被選中的 Menuitem
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //根據選項 id 做處理
        switch (item.getItemId()) {
            case R.id.menu_item_cart:
                Intent intent1 = new Intent(context, ShoppingCartActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_item_account:
                Intent intent2 = new Intent(context, MemberAreaActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}