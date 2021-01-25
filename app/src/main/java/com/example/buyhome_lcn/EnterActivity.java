package com.example.buyhome_lcn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buyhome_lcn.data.UserData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EnterActivity extends AppCompatActivity {
    Context context;

    //資料儲存用
    private static final String PREF = "PREF";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";
    private static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";

    @Override
    protected void onResume() {
        super.onResume();

        //創建一個SharedPreferences
        //引數一為要使用的xml檔名
        //引數二為權限，通常為0，表示只允許本APP執行
        SharedPreferences sp = getSharedPreferences(PREF, 0);
        //取出資料值
        //引數一為標籤
        //引數二為預設值
        UserData.setUserName(sp.getString(PREF_USER_NAME, ""));
        UserData.setUserEmail(sp.getString(PREF_USER_EMAIL, ""));

        //讀取內部記憶體之使用者頭像
        Bitmap userImg = loadImageFromStorage("/data/user/0/com.example.buyhome_lcn/app_imageDir");
        UserData.setUserImgBitmap(userImg);
    }

    /**
     *取得內部記憶體之圖片
     */
    private Bitmap loadImageFromStorage(String path) {
        Bitmap b = null;
        try {
            File f=new File(path, "profile.jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        Log.d("myTest", "取得內部記憶體之圖片:" + b);

        return b;
    }

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
        // 取得現存的 Google 帳號，若從未登入則為 null
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // 依據 account 結果做相應處理
        Intent intent;
        //若已登入
        if(account != null){
            Toast.makeText(context, "已登入", Toast.LENGTH_SHORT).show();
            //根據選項 id 做處理
            switch (item.getItemId()) {
                case R.id.menu_item_cart:
                    intent = new Intent(context, ShoppingCartActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.menu_item_account:
                    intent = new Intent(context, MemberAreaActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }else{
            Toast.makeText(context, "未登入", Toast.LENGTH_SHORT).show();
            //若未登入
            //根據選項 id 做處理
            switch (item.getItemId()) {
                case R.id.menu_item_cart:
                    UserData.setNextActivity(UserData.SHOPPING_CART_ACTIVITY);
                    intent = new Intent(context, LoginBuyHomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.menu_item_account:
                    UserData.setNextActivity(UserData.MEMBER_AREA_ACTIVITY);
                    intent = new Intent(context, LoginBuyHomeActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

}