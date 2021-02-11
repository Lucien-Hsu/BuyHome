package com.lcnhsu.buyhome_lcn.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class HomeViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> pageview1;

    public HomeViewPagerAdapter(ArrayList<View> pageview1){
        this.pageview1 = pageview1;
    }

    //銷毀
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("MainActivityDestroy",position+"");
        if (pageview1.get(position)!=null) {
            container.removeView(pageview1.get(position));
        }
    }

    //初始化
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pageview1.get(position));
        Log.d("MainActivityInstanti",position+"");
        return pageview1.get(position);
    }

    //取得頁面數量
    @Override
    public int getCount() {
        return pageview1.size();
    }

    //判斷傳來的view是否是正確的
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }
}
