package com.example.robolink;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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
//    private Button btn_back2splash;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
/*    private SharedPreferences preferences_login;
    private SharedPreferences.Editor editor_login;*/

/*    private RecyclerView recyclerView;
    //private DeviceAdapter deviceAdapter;
    private List<Device> devicelist = new ArrayList<>();*/

    public FragmentSetting() {
        // Required empty public constructor
    }


/*    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initDevices();//添加目录，在这里可以只添加一次
    }*/

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

        profile_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                startActivity(new Intent(getActivity(),ActivityProfile.class));
            }
        });

        //关于栏监听事件
        about_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                startActivity(new Intent(getActivity(),ActivityAbout.class));
            }
        });

        //收藏条监听事件
        collection_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
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
        setting_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                startActivity(new Intent(getActivity(),ActivitySetting.class));
            }
        });

        //客服栏监听事件
        customerservice_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                startActivity(new Intent(getActivity(),ActivityCustomerService.class));
            }
        });

        //帮助栏监听事件
        help_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                startActivity(new Intent(getActivity(),ActivityHelp.class));
            }
        });


/*        deviceAdapter = new DeviceAdapter(devicelist, btn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(deviceAdapter);*/

        btn_back2welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("remember_login", false);
                editor.commit();
                startActivity(new Intent(getActivity(), ActivityWelcome.class));
            }
        });

/*        btn_back2splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor_login.clear();
                editor.clear();
                editor_login.commit();
                editor.commit();
                startActivity(new Intent(getActivity(), ActivitySplash.class));
            }
        });*/



       /* Button btn_return = (Button) getActivity().findViewById(R.id.btn_return);
        Button btn_to_mainpage = (Button) getActivity().findViewById(R.id.btn_to_mainpage);
        Button btn_back_to_register = (Button) getActivity().findViewById(R.id.btn_back_to_register);
        final ActivityMain activityMain = (ActivityMain) getActivity();
        activityMain.setF2F(new ActivityMain.F2F()

        {
            @Override
            public void toF (ViewPager viewPager){
                viewPager.setCurrentItem(0);
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityLogin.class));
            }
        });

        btn_to_mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMain.forSkip();
            }
        });

        btn_back_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityRegister.class));
            }
        });
    }

    private void initDevices(){
        Device about_setting = new Device(getResources().getString(R.string.about), R.mipmap.ic_about,R.mipmap.ic_back);
        Device collection_setting = new Device(getResources().getString(R.string.collection), R.mipmap.ic_collection ,R.mipmap.ic_back);
        Device setting_setting = new Device(getResources().getString(R.string.setting),R.mipmap.ic_setting,R.mipmap.ic_back);
        Device customer_service_setting = new Device(getResources().getString(R.string.customer_service), R.mipmap.ic_customer_service,R.mipmap.ic_back);
        Device help_setting = new Device(getResources().getString(R.string.help),R.mipmap.ic_help,R.mipmap.ic_back);
        devicelist.add(about_setting);
        devicelist.add(collection_setting);
        devicelist.add(setting_setting);
        devicelist.add(customer_service_setting);
        devicelist.add(help_setting);
    }

    public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

        private List<Device> mdevicelist;

        class ViewHolder extends RecyclerView.ViewHolder {

            View deviceView;
            ImageView imageView;
            ImageView imageView2;
            TextView textView;
            TextView textView2;
            RelativeLayout customTitleBar;

            public ViewHolder(View view) {
                super(view);
                deviceView = view;
                imageView = (ImageView) view.findViewById(R.id.bar_left_view);
                imageView2 = (ImageView) view.findViewById(R.id.bar_right_view);
                textView = (TextView) view.findViewById(R.id.bar_left_text);
                textView2 = view.findViewById(R.id.bar_title);
                customTitleBar = view.findViewById(R.id.custom_titlebar);
            }
        }


        public DeviceAdapter(List<Device> deviceList, Button btn){
            mdevicelist = deviceList;
        }



        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_titlebar, parent, false);
            final ViewHolder viewHolder = new DeviceAdapter.ViewHolder(view);
            viewHolder.deviceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = viewHolder.getAdapterPosition();
                    switch (position){
                        case 0://读取位置，根据位置实现功能，是不是很机智,但还是不够机智，没能细分bar上的具体位置
                            Toast.makeText(getContext(),R.string.about,Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DeviceAdapter.ViewHolder holder, int position) {
            Device device = mdevicelist.get(position);
            holder.imageView.setImageResource(device.getImageID());
            holder.imageView2.setImageResource(device.getImageID2());
            holder.textView.setText(device.getName());
        }

        @Override
        public int getItemCount() {
            return mdevicelist.size();
        }
    }*/


    }
    private void initView(){

        profile_Bar = getActivity().findViewById(R.id.profile_bar);
        //profile_Bar.setBackground(getResources().getDrawable(R.drawable.user_banner));
        about_Bar = getActivity().findViewById(R.id.about_bar);
        collection_Bar = getActivity().findViewById(R.id.collection_bar);
        setting_Bar = getActivity().findViewById(R.id.setting_bar);
        customerservice_Bar = getActivity().findViewById(R.id.customerservice_bar);
        help_Bar = getActivity().findViewById(R.id.help_bar);

        btn_back2welcome = getActivity().findViewById(R.id.back2welcome);
        //btn_back2splash = getActivity().findViewById(R.id.back2splash);
        //recyclerView = getActivity().findViewById(R.id.setting_recyclerview);

        preferences = getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
        editor = preferences.edit();
        /*preferences_login = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        editor_login = preferences_login.edit();*/
    }

}