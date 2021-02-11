package com.lcnhsu.buyhome_lcn.fragment_enter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcnhsu.buyhome_lcn.databinding.FragmentMessageBinding;

public class MessageFragment extends Fragment {
    Context context;

    //viewBinding
    private FragmentMessageBinding binding;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = requireActivity();

        //viewBinding
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        //監聽聯絡方式是否被點擊
        binding.tvMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //開啟電子郵件
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                //增加信件標題
                intent.putExtra(Intent.EXTRA_SUBJECT, "[由APP聯絡Lucien Hsu]");
                //增加收件者信箱
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"lcnoffical@gmail.com"});
                //若單純只由APP處理則加這行
                //想確保Intent只由電子郵件應用（而非其他短信或社交應用）進行處理，則需加上這行。
                intent.setData(Uri.parse("mailto:"));
                //如果有可以用的APP則開啟意圖
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //監聽 APP 說明是否被點擊
        binding.tvAppDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //連到網站
//                Uri uriUrl = Uri.parse("https://www.notion.so/lucienhsu/Demo-APP-42c9ef1f69ef4246a8ff4c665b41ebc7");
                Uri uriUrl = Uri.parse("https://www.notion.so/lucienhsu/Demo-APP-97f8e62a6ad5449882aecbd0ff4bf00b");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        return view;
    }
}