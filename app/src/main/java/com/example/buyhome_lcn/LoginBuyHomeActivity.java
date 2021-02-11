package com.example.buyhome_lcn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoginBuyHomeActivity extends AppCompatActivity {
    Context context;

    private static final String PREF = "PREF";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";
    private static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";
    private static final String PREF_USER_PHONE = "PREF_USER_PHONE";

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

//                Toast.makeText(context, "取得 GoogleSignInAccount 的 Token", Toast.LENGTH_SHORT).show();

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A6-2.對帳戶資訊物件做處理
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        Log.d("myTest", "call handleSignInResult");

        try {
            // 若登入成功，顯示登入成功
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("myTest", "handleSignInResult:登入成功。 帳號:" + account);
//            Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            // 若登入失敗，顯示登入失敗
            Log.d("myTest", "handleSignInResult:登入失敗。 錯誤碼:" +  e.getStatusCode());
//            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    // B2-2.連接 FirebaseAuth 與 GoogleSignIn 的帳戶資訊
    private void firebaseAuthWithGoogle(String idToken) {

        Log.d("myTest", "call firebaseAuthWithGoogle");

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("myTest", "firebaseAuthWithGoogle:success");
                            //取得當前帳戶
                            FirebaseUser user = mAuth.getCurrentUser();
                            // 儲存帳戶資訊
                            UserData.setUserName(user.getDisplayName());
                            UserData.setUserEmail(user.getEmail());
                            // 儲存帳戶圖片
                            UserData.setUserImgURL(user.getPhotoUrl().toString());
                            new MyPhotoAsyncTask().execute(user.getPhotoUrl().toString());
                            //印出取得的資料
//                            Toast.makeText(context, "[已取得資料]\n暱稱:" + user.getDisplayName()
//                                    + "\n信箱:" + user.getEmail()
//                                    + "\n圖片網址:" + user.getPhotoUrl().toString(), Toast.LENGTH_SHORT).show();

                            //儲存資料至設備
                            SharedPreferences sp = getSharedPreferences(PREF, 0);
                            //設定為編輯模式，並放入資料鍵值，最後commit()才會寫入
                            sp.edit()
                                    .putString(PREF_USER_NAME, user.getDisplayName())
                                    .putString(PREF_USER_EMAIL, user.getEmail())
                                    .apply();
                            UserData.deleteReceiver(0);
                            UserData.addReceiver("#" + sp.getString(PREF_USER_NAME, "預設收件人") + "#" + sp.getString(PREF_USER_PHONE, "預設電話"));


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
                            Log.d("myTest", "firebaseAuthWithGoogle:fail");
                        }
                    }
                });
    }

    /**
     * 以 URL 取得 Bitmap 圖片
     */
    class MyPhotoAsyncTask extends AsyncTask<String, Integer, byte[]> {
        @Override
        protected byte[] doInBackground(String... strings) {
            return getPhotoData(strings[0]);
        }

        @Override
        protected void onPostExecute(byte[] data) {
            //取得點陣圖
            Bitmap b = BitmapFactory.decodeByteArray(data, 0, data.length);
            UserData.setUserImgBitmap(b);

            //儲存圖片至內部記憶體
            saveToInternalStorage(b);
        }
    }

    /**
     * 取得網路圖片
     */
    private byte[] getPhotoData(String urlStr) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try{
            //取得url
            URL url = new URL(urlStr);
            //取得網路連接
            URLConnection conn = url.openConnection();
            //設定輸入串流來自網路連接
            is = conn.getInputStream();

            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            //若讀取串流有內容則做
            int len = 0;
            //讀取網路串流
            while((len = is.read(buffer)) > 0){
                //把buffer內容寫入ByteArrayOutputStream
                baos.write(buffer, 0, len);
            }
            //把全部資料存起來
            byte[] data = baos.toByteArray();

            //關閉串流
            is.close();
            baos.close();

            //回傳資料
            return data;
        }catch (IOException e){
            if(is != null){
                try{
                    is.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }

            if(baos != null){
                try{
                    baos.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 儲存圖片到內部記憶體
     */
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/com.example.buyhome_lcn/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d("myTest", "儲存圖片成功，路徑:" + directory.getAbsolutePath());

        return directory.getAbsolutePath();
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