package com.fii.robolink.MainFunctions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.R;
import com.fii.robolink.SupportFunctions.ActivityWelcome;
import com.fii.robolink.SupportFunctions.ActivityAbout;
import com.fii.robolink.SupportFunctions.ActivityCustomerService;
import com.fii.robolink.SupportFunctions.ActivityHelp;
import com.fii.robolink.SupportFunctions.ActivityProfile;
import com.fii.robolink.SupportFunctions.ActivitySetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends Fragment {

    private CustomTitleBar profile_Bar;
    private CustomTitleBar about_Bar;
    private CustomTitleBar collection_Bar;
    private CustomTitleBar setting_Bar;
    private CustomTitleBar customerservice_Bar;
    private CustomTitleBar help_Bar;

    private Button btn_back2welcome;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private ImageView imageView_profile_photo;
    private String nick_name;
    private String e_mail;

    public FragmentSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getProfile();
        initProfile();

        //介绍栏监听事件
        profile_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityProfile.class));
            }
        });

        //关于栏监听事件
        about_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityAbout.class));
            }
        });

        //收藏条监听事件
        collection_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("该功能暂未开启");
                builder.setPositiveButton("默认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        //设置栏监听事件
        setting_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivitySetting.class));
            }
        });

        //客服栏监听事件
        customerservice_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityCustomerService.class));
            }
        });

        //帮助栏监听事件
        help_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityHelp.class));
            }
        });
        btn_back2welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("remember_login", false);
                editor.commit();
                startActivity(new Intent(getActivity(), ActivityWelcome.class));
            }
        });
    }

    private void initView(){

        profile_Bar = getActivity().findViewById(R.id.profile_bar);
        about_Bar = getActivity().findViewById(R.id.about_bar);
        collection_Bar = getActivity().findViewById(R.id.collection_bar);
        setting_Bar = getActivity().findViewById(R.id.setting_bar);
        customerservice_Bar = getActivity().findViewById(R.id.customerservice_bar);
        help_Bar = getActivity().findViewById(R.id.help_bar);

        btn_back2welcome = getActivity().findViewById(R.id.back2welcome);

        preferences = getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void getProfile(){

    }

    private void initProfile(){

    }
}