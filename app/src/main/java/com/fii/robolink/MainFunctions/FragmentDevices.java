package com.fii.robolink.MainFunctions;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fii.robolink.R;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDevices extends Fragment {

    private TabLayout tabLayout = null;
    private ViewPager viewPager;
    private DFragmentPhone dfragmentPhone = new DFragmentPhone();
    private DFragmentKnife dFragmentKnife = new DFragmentKnife();
    private DFragmentSurveilance dFragmentSurveilance = new DFragmentSurveilance();
    private String[] mTabTitles = new String[3];


    public FragmentDevices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_devices, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout = getActivity().findViewById(R.id.devices_tablayout);
        viewPager = getActivity().findViewById(R.id.devices_viewpager);
        initView();
    }

    private void initView(){
        //设置tab标题
        mTabTitles[0] = getResources().getString(R.string.phone_mid_frame);
        mTabTitles[1] = getResources().getString(R.string.knife_manufacture);
        mTabTitles[2] = getResources().getString(R.string.surveillance);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitles[2]));

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return dfragmentPhone;
                    case 1:
                        return dFragmentKnife;
                    case 2:
                        return dFragmentSurveilance;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 3;
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

/*        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount()) {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return false;
            }
        })*/

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中tab时，滑动页面
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
