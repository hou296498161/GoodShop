package com.example.administrator.goodshop.mygoodshop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.goodshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGoodShopFragment extends Fragment {


    private View view;

    public MyGoodShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_my_good_shop, container, false);
        return view;
    }

}
