package com.fii.robolink.MainFunctions;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fii.robolink.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DSFragmentSurvControlPanel extends Fragment implements View.OnClickListener{

    private ProgressBar progressBar_focus;
    private ProgressBar progressBar_speed;
    private Button btn_speed_plus;
    private Button btn_speed_minus;
    private Button btn_focus_plus;
    private Button btn_focus_minus;
    private int progress_focus;
    private int progress_speed;


    public DSFragmentSurvControlPanel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dsfragment_surv_control_panel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        btn_focus_plus.setOnClickListener(this);
        btn_speed_plus.setOnClickListener(this);
        btn_focus_minus.setOnClickListener(this);
        btn_speed_minus.setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sur_panel_focus_plus:
                progress_focus = progressBar_focus.getProgress();
                progress_focus = progress_focus + 20;
                progressBar_focus.setProgress(progress_focus);
                break;
            case R.id.btn_sur_panel_speed_plus:
                progress_speed = progressBar_speed.getProgress();
                progress_speed = progress_speed + 20;
                progressBar_speed.setProgress(progress_speed);
                break;
            case R.id.btn_sur_panel_focus_minus:
                progress_focus = progressBar_focus.getProgress();
                progress_focus = progress_focus - 20;
                progressBar_focus.setProgress(progress_focus);
                break;
            case R.id.btn_sur_panel_speed_minus:
                progress_speed = progressBar_speed.getProgress();
                progress_speed = progress_speed - 20;
                progressBar_speed.setProgress(progress_speed);
                break;
            default:
                break;
        }
    }

    public void initView(){
        progressBar_focus = getActivity().findViewById(R.id.focus_progress_bar);
        progressBar_speed = getActivity().findViewById(R.id.speed_progress_bar);
        btn_focus_plus = getActivity().findViewById(R.id.btn_sur_panel_focus_plus);
        btn_speed_plus = getActivity().findViewById(R.id.btn_sur_panel_speed_plus);
        btn_focus_minus = getActivity().findViewById(R.id.btn_sur_panel_focus_minus);
        btn_speed_minus = getActivity().findViewById(R.id.btn_sur_panel_speed_minus);
    }
}
