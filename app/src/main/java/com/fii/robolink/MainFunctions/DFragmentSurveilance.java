package com.fii.robolink.MainFunctions;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fii.robolink.CustomView.CustomViewPager;
import com.fii.robolink.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DFragmentSurveilance extends Fragment {

    private TabLayout tabLayout;
    private CustomViewPager viewPager;


    private ArrayList<View> views = new ArrayList<>();
    private String[] mTabTitles = new String[2];

    private DSFragmentNaviControlPanel dsFragmentNaviControlPanel = new DSFragmentNaviControlPanel();
    private DSFragmentSurvControlPanel dsFragmentSurvControlPanel = new DSFragmentSurvControlPanel();


    public DFragmentSurveilance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dfragment_surveilance, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();

        super.onActivityCreated(savedInstanceState);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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

 /*   //内部类帮助将view加载到PagerAdapter容器中
    class ViewPagerAdatper extends PagerAdapter {

        private List<View> mViewList;

        public ViewPagerAdatper(List<View> mViewList){
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList.get(position));
        }
    }*/

    private void initView(){
        tabLayout = getActivity().findViewById(R.id.surveillance_tablayout);
        viewPager = getActivity().findViewById(R.id.surveillance_viewpager);
        mTabTitles[0] = "导航控制台";
        mTabTitles[1] = "监控控制台";

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitles[1]));

        /*LayoutInflater inflater = getLayoutInflater().from(getContext());
        views.add(inflater.inflate(R.layout.devices_survalliance_navigation_control,null));
        views.add(inflater.inflate(R.layout.devices_survalliance_survalliance_control,null));*/

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return dsFragmentNaviControlPanel;
                    case 1:
                        return dsFragmentSurvControlPanel;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 2;
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
