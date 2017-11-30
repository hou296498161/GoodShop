package com.example.administrator.goodshop.headpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.goodshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadPagerFragment extends Fragment {


    private View view;

    public HeadPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_head_pager, container, false);
        return view;
    }

}
