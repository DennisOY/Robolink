package com.example.robolink;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends Fragment {

    private RecyclerView recyclerView;
    private DeviceAdapter deviceAdapter;
    private List<Device> devicelist = new ArrayList<>();

    public FragmentSetting() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initDevices();//添加目录，在这里可以只添加一次
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


        deviceAdapter = new DeviceAdapter(devicelist);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.setting_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(deviceAdapter);


/*        Button btn_return = (Button) getActivity().findViewById(R.id.btn_return);
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
        });*/
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

            public ViewHolder(View view) {
                super(view);
                deviceView = view;
                imageView = (ImageView) view.findViewById(R.id.bar_left_view);
                imageView2 = (ImageView) view.findViewById(R.id.bar_right_view);
                textView = (TextView) view.findViewById(R.id.bar_left_text);
            }
        }

        public DeviceAdapter(List<Device> deviceList){
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
                        case 0://读取位置，根据位置实现功能，是不是很机智
                            startActivity(new Intent(getActivity(),ActivityWelcome.class));
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
            return devicelist.size();
        }
    }
}