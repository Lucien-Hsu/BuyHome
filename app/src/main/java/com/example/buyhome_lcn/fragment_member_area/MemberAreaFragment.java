package com.example.buyhome_lcn.fragment_member_area;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyhome_lcn.R;
import com.example.buyhome_lcn.data.MemberAreaViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberAreaFragment extends Fragment {
    View view;
    Context context;
    ListView lvAccountArea;

    ImageView imgUserPhoto;

    TextView tvNickname, tvAccount;

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
        view = inflater.inflate(R.layout.fragment_member_area, container, false);
        context = requireActivity();

        setHasOptionsMenu(true);

        //取得自定義 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(MemberAreaViewModel.class);

        imgUserPhoto = view.findViewById(R.id.img_user_photo);
        if(viewModel.getHasPhoto()){
            imgUserPhoto.setImageBitmap(viewModel.getUserPhotoBitmap());
        }

        tvNickname = view.findViewById(R.id.tv_nickname);
        tvNickname.setText(viewModel.getNickname());

        tvAccount = view.findViewById(R.id.tv_account);
        tvAccount.setText(viewModel.getEmail());

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

        lvAccountArea = view.findViewById(R.id.lv_account_area);
        SimpleAdapter adapter = new SimpleAdapter(
                context, itemList,
                R.layout.item_memberarea,
                new String[]{"img", "info", "showInfo", "showNextSign"},
                new int[]{R.id.img_info, R.id.tv_info, R.id.tv_show_info, R.id.img_next_sign});

        lvAccountArea.setAdapter(adapter);

        lvAccountArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        return view;
    }

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