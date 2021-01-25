package com.example.buyhome_lcn.fragment_member_area;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.data.MemberAreaViewModel;
import com.example.buyhome_lcn.data.UserData;
import com.example.buyhome_lcn.databinding.FragmentMemberAreaBinding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberAreaFragment extends Fragment {
    //宣告 binding 物件
    private FragmentMemberAreaBinding binding;

    Context context;

    //ViewModel
    private MemberAreaViewModel viewModel;

    List<Map<String, Object>> itemList;
    int[] infoImgList = {
            R.drawable.ic_user_info, R.drawable.ic_address,
            R.drawable.ic_store_pickup, R.drawable.ic_pay_method,
            R.drawable.ic_alarm, R.drawable.ic_my_store};

    String[] infoTextList = {
            "個人資訊", "宅配地址",
            "超商取貨門市", "付款方式",
            "通知", "我的商店"};

    String[] showInfoTextList;

    Integer[] showNextSign = {
            R.drawable.arrow_right, R.drawable.arrow_right,
            R.drawable.arrow_right, R.drawable.arrow_right,
            null, null};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //對此 Binding 物件充氣
        binding = FragmentMemberAreaBinding.inflate(inflater, container, false);;
        //取得充氣後的 View 的根元件
        View view = binding.getRoot();

        //取得 Activity
        context = requireActivity();
        //設定有 Menu
        setHasOptionsMenu(true);
        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(MemberAreaViewModel.class);

        //若有照片則設定照片
        if(viewModel.getHasPhoto()){
            binding.imgUserPhoto.setImageBitmap(viewModel.getUserPhotoBitmap());
            Toast.makeText(context, "設定照片", Toast.LENGTH_SHORT).show();
        }
        binding.tvNickname.setText(viewModel.getNickname());
        binding.tvAccount.setText(viewModel.getEmail());
//        Toast.makeText(context, "設定資料", Toast.LENGTH_SHORT).show();
        Log.d("myTest", "viewModel.getUserPhotoBitmap(): " + viewModel.getUserPhotoBitmap());
        Log.d("myTest", "viewModel.getEmail(): " + viewModel.getNickname());
        Log.d("myTest", "viewModel.getNickname(): " + viewModel.getEmail());

        //設定資料
        itemList = new ArrayList<Map<String, Object>>();
        showInfoTextList = new String[]{
                "",
                viewModel.getAddress(),
                viewModel.getStore(),
                viewModel.getPayMethod(),
                "",
                ""};
        for(int i = 0 ; i < infoImgList.length ; i++){
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
                R.layout.item_memberarea,
                new String[]{"img", "info", "showInfo", "showNextSign"},
                new int[]{R.id.img_info, R.id.tv_name, R.id.tv_price, R.id.img_next_sign});
        //listView連結適配器
        binding.lvAccountArea.setAdapter(adapter);
        //設定listView監聽器
        binding.lvAccountArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Map<String, Object> item = (Map<String, Object>)adapterView.getItemAtPosition(position);
                Integer img = (Integer) item.get("img");
                String info = (String) item.get("info");
                Toast.makeText(context, "You selected : " + img + " info = " + info, Toast.LENGTH_SHORT).show();

                switch(position){
                    case 0:
                        Navigation.findNavController(view).navigate(R.id.action_memberAreaFragment_to_accountInfoFragment);
                        break;
                    case 1:
                        Navigation.findNavController(view).navigate(R.id.action_memberAreaFragment_to_setAddressFragment);
                        break;
                    case 2:
                        Navigation.findNavController(view).navigate(R.id.action_memberAreaFragment_to_setStoreFragment);
                        break;
                    case 3:
                        Navigation.findNavController(view).navigate(R.id.action_memberAreaFragment_to_setPayMethodFragment);
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

    //設定返回鍵
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                requireActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}