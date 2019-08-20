package com.example.robolink;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCamera extends Fragment {

    private RecyclerView recyclerView;
    private DeviceAdapter deviceAdapter;
    private List<Device> devicelist = new ArrayList<>();

    public FragmentCamera() {
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
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        deviceAdapter = new DeviceAdapter(devicelist);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.camera_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(deviceAdapter);
    }


    //初始化场景图片
    private void initDevices(){
        Device isolated_mode = new Device(getResources().getString(R.string.isolated_mode), R.drawable.camera_pic,R.raw.mode_isolated);
        devicelist.add(isolated_mode);
        Device patrol_mode = new Device(getResources().getString(R.string.patrol_mode), R.drawable.camera_pic,R.raw.mode_patrol);
        devicelist.add(patrol_mode);
        Device tour_mode = new Device(getResources().getString(R.string.tour_mode), R.drawable.camera_pic,R.raw.mode_tour);
        devicelist.add(tour_mode);
        Device order_mode = new Device(getResources().getString(R.string.order_mode), R.drawable.camera_pic,R.raw.mode_order);
        devicelist.add(order_mode);
        Device distributed_mode = new Device(getResources().getString(R.string.distributed_mode), R.drawable.camera_pic,R.raw.mode_distributed);
        devicelist.add(distributed_mode);
    }

    //内部类做适配器
    public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

        private Context context;
        private List<Device> mdevicelist;

        class ViewHolder extends RecyclerView.ViewHolder {

            CardView cardView;
            ImageView imageView;
            ImageView imageView2;
            TextView textView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.camera_pic);
                imageView2 = (ImageView) view.findViewById(R.id.mode_pic);
                textView = (TextView) view.findViewById(R.id.mode_name);
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_camera_mode, parent, false);
            final DeviceAdapter.ViewHolder viewHolder = new DeviceAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DeviceAdapter.ViewHolder holder, int position) {
            Device device = mdevicelist.get(position);
            holder.imageView.setImageResource(device.getImageID());
            //holder.imageView2.setImageResource(device.getImageID2());
            Glide.with(context).load(device.getImageID2()).into(holder.imageView2);
            holder.textView.setText(device.getName());
        }

        @Override
        public int getItemCount() {
            return mdevicelist.size();
        }
    }
}
