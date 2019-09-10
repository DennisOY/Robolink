package com.fii.robolink.SupportFunctions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fii.robolink.Base.ActivityBase;
import com.fii.robolink.CustomView.CustomEditText;
import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.JavaClass.Msg;
import com.fii.robolink.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityCustomerService extends ActivityBase {

    private CustomTitleBar customTitleBar;
    private RecyclerView chatrobot_recyclerview;
    private CustomEditText editText_question;
    private Button btn_cancel;

    private MsgAdapter msgAdapter;
    private List<Msg> msglist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        initView();
        initMsg();
        //设置成分开模式
        customTitleBar.setSplitMode(true);
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

/*
        //监听输入更改
        editText_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (TextUtils.isEmpty(editText_question.getText())) {
                    btn_cancel.setEnabled(false);
                } else {
                    btn_cancel.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        //监听输入内容，实现聊天对话滚动式滚动
        editText_question.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                    if (i == EditorInfo.IME_ACTION_SEARCH || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                        msglist.add(new Msg(editText_question.getText().toString(), Msg.TYPE_SENT));
                        msgAdapter.notifyItemInserted(msglist.size());
                        chatrobot_recyclerview.smoothScrollToPosition(msglist.size());//新对话出现的时候，自动平缓滚动到最新位置
                        editText_question.setText("");//清空搜索栏
                        return true;

                }
                return false;
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_question.setText("");
                //隐藏软键盘
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    private void initMsg(){
        Msg welcome = new Msg("你好，我是机器人小斌，请问有什么需要帮助的吗", Msg.TYPE_RECEIVED);
        msglist.add(welcome);
    }

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
                leftlayout = view.findViewById(R.id.msg_left_layout);
                rightlayout = view.findViewById(R.id.msg_right_layout);
                left_msg = view.findViewById(R.id.ai_msg);
                right_msg = view.findViewById(R.id.user_msg);
            }
        }
    }

    private void initView () {
        customTitleBar = findViewById(R.id.titlebar_customer_service);

        chatrobot_recyclerview = findViewById(R.id.chatrobot_recyclerview);
        chatrobot_recyclerview.setLayoutManager(new LinearLayoutManager(ActivityCustomerService.this));
        msgAdapter = new MsgAdapter(msglist);
        chatrobot_recyclerview.setAdapter(msgAdapter);

        editText_question = findViewById(R.id.edit_question);
        btn_cancel = findViewById(R.id.btn_cancel);
//        btn_cancel.setEnabled(false);
    }

}



