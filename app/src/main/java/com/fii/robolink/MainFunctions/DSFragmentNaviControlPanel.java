package com.fii.robolink.MainFunctions;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.fii.robolink.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DSFragmentNaviControlPanel extends Fragment {

    private Button btn_low_speed_setting;
    private Button btn_accelarate_setting;

    private EditText low_speed_setting;
    private EditText accelarate_setting;


    public DSFragmentNaviControlPanel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dsfragment_navi_control_panel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        btn_accelarate_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accelarate_setting = new EditText(getContext());
                accelarate_setting.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("加速参数设置（建议填3-5）")
                        .setView(accelarate_setting)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String input = accelarate_setting.getText().toString();
                                btn_accelarate_setting.setText(input);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        btn_low_speed_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                low_speed_setting = new EditText(getContext());
                low_speed_setting.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("高速参数设置（建议填320）")
                        .setView(low_speed_setting)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String input = low_speed_setting.getText().toString();
                                btn_low_speed_setting.setText(input);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

 /*   @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_low_speed_setting:
                low_speed_setting = new EditText(getContext());
                low_speed_setting.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("高速参数设置（建议填320）")
                        .setView(low_speed_setting)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String input = low_speed_setting.getText().toString();
                                btn_low_speed_setting.setText(input);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            case R.id.btn_accelarate_setting:
                accelarate_setting = new EditText(getContext());
                accelarate_setting.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("加速参数设置（建议填3-5）")
                        .setView(accelarate_setting)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String input = accelarate_setting.getText().toString();
                                btn_accelarate_setting.setText(input);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();

            default:
                break;
        }
    }*/

    private void initView(){
        btn_accelarate_setting = getActivity().findViewById(R.id.btn_accelarate_setting);
        btn_low_speed_setting = getActivity().findViewById(R.id.btn_low_speed_setting);
    }
}
