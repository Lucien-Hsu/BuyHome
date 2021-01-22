package com.example.buyhome_lcn.fragment_enter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.buyhome_lcn.ProductDetailActivity;
import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.adapter.HomeGridViewAdapter;
import com.example.buyhome_lcn.adapter.HomeViewPagerAdapter;
import com.example.buyhome_lcn.data.EnterViewModel;
import com.example.buyhome_lcn.data.ProductData;
import com.example.buyhome_lcn.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    Context context;
    //宣告 binding 物件
    private FragmentHomeBinding binding;
    //ViewModel
    private EnterViewModel viewModel;

    private View vEvent01, vEvent02, vEvent03;
    private ArrayList<View> pageview;

    private List<Map<String, Object>> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewBinding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //取得 context
        context = requireActivity();
        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(EnterViewModel.class);

        //ViewPager
        //將 view 加進 pageview 中
        vEvent01 = getLayoutInflater().inflate(R.layout.event_01,null);
        vEvent02 = getLayoutInflater().inflate(R.layout.event_02,null);
        vEvent03 = getLayoutInflater().inflate(R.layout.event_03,null);
        pageview = new ArrayList<View>();
        pageview.add(vEvent01);
        pageview.add(vEvent02);
        pageview.add(vEvent03);
        //viewPager 連接適配器
        binding.viewPager.setAdapter(new HomeViewPagerAdapter(pageview));

        //將所有資料以map型態存入陣列
        itemList = new ArrayList<Map<String, Object>>();
        for(int i = 0 ; i < viewModel.getNameList().size() ; i++){
            //將所需的三筆資料存入Map
            //創建Map
            Map<String, Object> data = new HashMap<>();
            //名稱字串
            data.put("name", viewModel.getNameList().get(i));
            //價格數字
            data.put("price",viewModel.getPriceList().get(i));
            //圖片數字編號
            data.put("picture", viewModel.getPictureList().get(i));

            //將每一個Map物件存入List中
            itemList.add(data);
        }

        //GridView
        //使用自定義的Adapter，此Adapter繼承BaseAdapter
        HomeGridViewAdapter gvAdapter = new HomeGridViewAdapter(context, itemList);
        //連結適配器
        binding.gvHome.setAdapter(gvAdapter);
        //設定顯示的直欄數
        binding.gvHome.setNumColumns(2);
        //監聽
        binding.gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context, "點擊:" + position, Toast.LENGTH_SHORT).show();

                //設定點選的商品編號
                ProductData.setCheckedProductID(position);
                //跳到商品頁
                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);
            }
        });

        //ListView
        //SimpleAdapter
        //引數一：context
        //引數二：[資料]List<Map<String, Object>>，包含變數名稱與資料，可讓變數對應到資料
        //引數三：[view]清單項目的 layout
        //引數四：[變數]變數名稱陣列，資料與 view ID 都要對應到這邊的變數
        //引數五：[view ID]可讓變數對應到 view ID
        final SimpleAdapter lvAdapter = new SimpleAdapter(
                context,
                itemList,
                R.layout.item_home_01,
                new String[]{"name", "price", "picture"},
                new int[]{R.id.tv_name, R.id.tv_price, R.id.img_info});
        //連結adapter
        binding.lvHome.setAdapter(lvAdapter);

        //設定 scrollview 從頭開始顯示
        binding.scrollview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                binding.scrollview.fullScroll(View.FOCUS_UP);
            }
        });

        return view;
    }
}