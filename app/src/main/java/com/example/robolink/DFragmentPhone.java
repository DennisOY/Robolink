package com.example.robolink;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
public class DFragmentPhone extends Fragment {


    private RecyclerView recyclerView;
    private DeviceAdapter deviceAdapter;
    private List<Device> devicelist = new ArrayList<>();

    public DFragmentPhone() {
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
        return inflater.inflate(R.layout.dfragment_phone, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        deviceAdapter = new DeviceAdapter(devicelist);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.phone_mid_frame_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        recyclerView.setAdapter(deviceAdapter);
    }

    //初始化场景图片
    private void initDevices(){
        Device D1 = new Device(getResources().getString(R.string.CNC1_5_Axis), R.drawable.cnc1);
        devicelist.add(D1);
        Device D5 = new Device(getResources().getString(R.string.FOX_BOT_1), R.drawable.foxbot1);
        devicelist.add(D5);
        Device D3 = new Device(getResources().getString(R.string.CNC2_FOXNUM), R.drawable.cnc1);
        devicelist.add(D3);
        Device D6 = new Device(getResources().getString(R.string.FOX_BOT_2), R.drawable.foxbot1);
        devicelist.add(D6);
        Device D2 = new Device(getResources().getString(R.string.CNC2_FANUC), R.drawable.cnc2);
        devicelist.add(D2);
        Device D7 = new Device(getResources().getString(R.string.Googoltech_1), R.drawable.foxbot2);
        devicelist.add(D7);
        Device D4 = new Device(getResources().getString(R.string.AGV1), R.drawable.agv);
        devicelist.add(D4);
        Device D8 = new Device(getResources().getString(R.string.Googoltech_2), R.drawable.foxbot2);
        devicelist.add(D8);
    }

    //内部类做适配器
    public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

        private Context context;
        private List<Device> mdevicelist;

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.device_pic);
                textView = (TextView) view.findViewById(R.id.device_name);
            }
        }

        public DeviceAdapter(List<Device> deviceList){
            mdevicelist = deviceList;
        }

        @NonNull
        @Override
        public DeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(context == null){
                context = parent.getContext();
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_device_pic, parent, false);
            final DeviceAdapter.ViewHolder viewHolder = new DeviceAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DeviceAdapter.ViewHolder holder, int position) {
            Device device = mdevicelist.get(position);
            holder.imageView.setImageResource(device.getImageID());
            holder.textView.setText(device.getName());
        }

        @Override
        public int getItemCount() {
            return mdevicelist.size();
        }
    }
}
