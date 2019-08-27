package com.example.robolink;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAudioControl extends Fragment {

    private Button startAudioControl;
    private RecyclerView recyclerView;

    private MsgAdapter msgAdapter;
    private List<Msg> msglist = new ArrayList<>();

    public FragmentAudioControl() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        initMsg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_control, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();

        msgAdapter = new MsgAdapter(msglist);
        recyclerView = getActivity().findViewById(R.id.audio_control_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(msgAdapter);

        startAudioControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAudioControl.setText("监听中...");
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(){
        startAudioControl = getActivity().findViewById(R.id.btn_audio_control);
    }

    //初始化AI聊天界面
    private void initMsg(){
        Msg guide_line1 = new Msg("欢迎使用智能语音控制", Msg.TYPE_RECEIVED);
        msglist.add(guide_line1);
        Msg guide_line2 = new Msg("你可以尝试说：\n向下移动，向上移动，向左移动，向右移动，开始，停止", Msg.TYPE_RECEIVED);
        msglist.add(guide_line2);
        Msg talk_line1 = new Msg("你谁啊你？", Msg.TYPE_SENT);
        msglist.add(talk_line1);
        Msg guide_line3 = new Msg("我没有听清楚，你可以尝试再说一次：\n向下移动，向上移动，向左移动，向右移动，开始，停止", Msg.TYPE_RECEIVED);
        msglist.add(guide_line3);
    }

    //recyclerview的适配器
    public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

        private List<Msg> mMsglist;

        public MsgAdapter(List<Msg> msgList){
            mMsglist = msgList;
        }

        @NonNull
        @Override
        public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_msg_item, parent, false);
            return new ViewHolder(view);
        }

        //显示ViewHolder里面的东西，根据接收的类型来判断显示左边还是右边的聊天框
        @Override
        public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
            Msg msg = mMsglist.get(position);

            //空指针问题原因是在赋值的时候没有用view，而用了getactivity（）
            if (msg.getType() == Msg.TYPE_RECEIVED){
                holder.leftlayout.setVisibility(View.VISIBLE);
                holder.rightlayout.setVisibility(View.GONE);
                holder.left_msg.setText(msg.getContent());
                //Toast.makeText(getActivity(), "可运行", Toast.LENGTH_SHORT).show();
            }
            if(msg.getType() == Msg.TYPE_SENT){
                holder.leftlayout.setVisibility(View.GONE);
                holder.rightlayout.setVisibility(View.VISIBLE);
                holder.right_msg.setText(msg.getContent());
            }
        }

        @Override
        public int getItemCount() {
            return mMsglist.size();
        }

        //自定义一个储存View的容器
        class ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout leftlayout;
            LinearLayout rightlayout;
            TextView left_msg;
            TextView right_msg;

            //构造ViewHolder的函数
            public ViewHolder(View view) {
                super(view);
                leftlayout = (LinearLayout) view.findViewById(R.id.msg_left_layout);
                rightlayout = (LinearLayout) view.findViewById(R.id.msg_right_layout);
                left_msg = (TextView) view.findViewById(R.id.ai_msg);
                right_msg = (TextView) view.findViewById(R.id.user_msg);
            }
        }
    }

    //一个消息类
    public class Msg {

        public static final int TYPE_RECEIVED = 0;
        public static final int TYPE_SENT = 1;
        private String content;
        private int type;

        public Msg(String content, int type) {
            this.content = content;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public int getType() {
            return type;
        }
    }
}
