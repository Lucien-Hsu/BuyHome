package com.example.buyhome_lcn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyhome_lcn.data.UserData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginBuyHomeActivity extends AppCompatActivity {
    Context context;

    //此為登入按鈕
    SignInButton btnLogin;
    private GoogleSignInClient mGoogleSignInClient;

    // A5-2.建立回應碼
    private final int RC_SIGN_IN = 101;

    // B1-1.宣告 FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_buy_home);
        context = this;

        //創建ActionBar物件
        ActionBar bar = getSupportActionBar();
        //設定ActionBar顯示返回鍵
        bar.setDisplayHomeAsUpEnabled(true);

        btnLogin = findViewById(R.id.sign_in_button);

        // B1-2.取得 FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // A1.設定 GoogleSignInOptions
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // A2.建立 GoogleSignInClient 並提供 GoogleSignInOptions 中的參數
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // A3.取得現存的 Google 帳號，若從未登入則為 null
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // A4.依據 account 結果做相應處理
        // 通常在返回結果不是 null 時隱藏登入按鈕
        if(account != null){
        }else{
//            tvState.setText();
        }

        // A5-1.點選登入按鈕後進入登入畫面
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Press Login Button", Toast.LENGTH_SHORT).show();
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                        startActivityForResult(signInIntent, RC_SIGN_IN);
                        break;
                }
            }
        });

    }

    /**
     * A6-1.依據登入頁回傳結果做相應處理
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 依據登入頁回傳結果做相應處理
        if (requestCode == RC_SIGN_IN) {
            // 取得帳戶資訊物件
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

            // B2-1.取得 GoogleSignInAccount 的 Token
            // 傳給 firebase
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A6-2.對帳戶資訊物件做處理
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            // 若登入成功，顯示登入成功
            Toast.makeText(context, "[Login]", Toast.LENGTH_SHORT).show();

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

//            Log.w("myTest", "account：" + account);

        } catch (ApiException e) {
            // 若登入失敗，顯示登入失敗
            Toast.makeText(context, "[Fail]", Toast.LENGTH_SHORT).show();

            // 印出錯誤碼
            Log.w("myTest", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    // B2-2.連接 FirebaseAuth 與 GoogleSignIn 的帳戶資訊
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "[signInWithCredential:success]", Toast.LENGTH_SHORT).show();

                            //取得當前帳戶
                            FirebaseUser user = mAuth.getCurrentUser();
                            // 儲存帳戶資訊
                            UserData.setUserName(user.getDisplayName());
                            UserData.setUserEmail(user.getEmail());
                            // 儲存帳戶圖片
                            UserData.setUserImgURL(user.getPhotoUrl().toString());

                            Toast.makeText(context, "登入成功", Toast.LENGTH_SHORT).show();

                            // 跳至下個 Activity
                            Intent intent;
                            switch (UserData.getNextActivity()) {
                                case UserData.SHOPPING_CART_ACTIVITY:
                                    intent = new Intent(context, ShoppingCartActivity.class);
                                    startActivity(intent);
                                    UserData.setNextActivity(UserData.NO_ACTIVITY);
                                    finish();
                                    break;
                                case UserData.MEMBER_AREA_ACTIVITY:
                                    intent = new Intent(context, MemberAreaActivity.class);
                                    startActivity(intent);
                                    UserData.setNextActivity(UserData.NO_ACTIVITY);
                                    finish();
                                    break;
                                case UserData.NO_ACTIVITY:
                                    finish();
                                    break;
                            }

                        } else {
                            Toast.makeText(context, "登入失敗", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    private void setWebImage(String imgUrlString) {
//        //設定帳戶圖片
//        //創建執行緒
//        new Thread(){
//            Bitmap bmp;
//            public void run(){
//
//                URL url = null;
//                try {
//                    url = new URL(imgUrlString);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                bmp = null;
//                try {
//                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                //在主執行緒上執行
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        img.setImageBitmap(bmp);
//                    }
//                });
//            }
//            //啟動執行緒
//        }.start();
//    }

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