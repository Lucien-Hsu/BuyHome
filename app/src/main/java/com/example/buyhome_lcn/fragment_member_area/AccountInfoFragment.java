package com.example.buyhome_lcn.fragment_member_area;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.data.MemberAreaViewModel;
import com.example.buyhome_lcn.databinding.FragmentAccountInfoBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AccountInfoFragment extends Fragment {
    //宣告 binding 物件
    private FragmentAccountInfoBinding binding;

    View view;

    //ViewModel
    private MemberAreaViewModel viewModel;

    private static final int GALLERY_REQUEST = 001;
    private static final int CAMERA_REQUEST = 002;

    Context context;
    ImageView imgUserPhoto;

    List<Map<String, Object>> itemList;
    int[] infoImgList = {
            R.drawable.ic_user_info, R.drawable.ic_password,
            R.drawable.ic_gender, R.drawable.ic_birthday,
            R.drawable.ic_phone, R.drawable.ic_email};

    String[] infoTextList = {
            "暱稱", "密碼",
            "性別", "生日",
            "手機", "信箱"};

    String[] showInfoTextList;

    Integer[] showNextSign = {
            R.drawable.arrow_right, R.drawable.arrow_right,
            R.drawable.arrow_right, R.drawable.arrow_right,
            R.drawable.arrow_right, R.drawable.arrow_right};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //對此 Bindin 物件充氣
        binding = FragmentAccountInfoBinding.inflate(inflater, container, false);;
        //取得充氣後的 View 的根元件
        view = binding.getRoot();

        //取得 Activity
        context = requireActivity();
        //設定有 Menu
        setHasOptionsMenu(true);
        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(MemberAreaViewModel.class);

        //若有照片則設定照片
        if(viewModel.getHasPhoto()){
            binding.imgUserPhoto.setImageBitmap(viewModel.getUserPhotoBitmap());
        }

        //[監聽器] 點選編輯照片
        binding.cvUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] options = {"從相簿選取", "從相機拍攝"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("設定顯示圖")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        getImageFromGallery();
                                        break;
                                    case 1:
                                        getImageFromTakePicture();
                                        break;
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //設定資料
        itemList = new ArrayList<Map<String, Object>>();
        showInfoTextList = new String[]{
                viewModel.getNickname(),
                viewModel.getPasswordHided(),
                viewModel.getGender(),
                String.valueOf(viewModel.getBirthday()),
                viewModel.getPhone(),
                viewModel.getEmail()};
        for (int i = 0; i < infoImgList.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", infoImgList[i]);
            item.put("info", infoTextList[i]);
            item.put("showInfo", showInfoTextList[i]);
            item.put("showNextSign", showNextSign[i]);
            itemList.add(item);
        }

        //設定適配器
        SimpleAdapter adapter = new SimpleAdapter(
                context, itemList,
                R.layout.item_accountinfo,
                new String[]{"img", "info", "showInfo", "showNextSign"},
                new int[]{R.id.img_info, R.id.tv_name, R.id.tv_price, R.id.img_next_sign});
        //listView連結適配器
        binding.lvAccountArea.setAdapter(adapter);
        //設定listView監聽器
        binding.lvAccountArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });

        //回傳 View
        return view;
    }

    /**
     * 從相簿取得照片
     */
    private void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    /**
     * 從相機拍攝照片
     */
    private void getImageFromTakePicture() {
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    /**
     * 接收回應處理收到的照片
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //1-3.若 requestCode 是GALLERY_REQUEST & 正常讀取 & 有資料則做
        if(requestCode == GALLERY_REQUEST && resultCode == requireActivity().RESULT_OK && data != null){
            //以 Uri 型態取得資料
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(),imageUri);
                //以 bitmap 型態變數儲存照片資料
                binding.imgUserPhoto.setImageBitmap(bitmap);
                viewModel.setUserPhotoBitmap(bitmap);
//                viewModel.setHasPhoto(true);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(requestCode == CAMERA_REQUEST && resultCode == requireActivity().RESULT_OK){
            //2-3.若 requestCode 是 CAMERA_REQUEST & 正常讀取則做

            //以 bitmap 型態變數儲存照片資料
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            viewModel.setUserPhotoBitmap(bitmap);
//            viewModel.setHasPhoto(true);

            binding.imgUserPhoto.setImageBitmap(bitmap);
        }
    }

    //設定返回鍵
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Navigation.findNavController(view).popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}