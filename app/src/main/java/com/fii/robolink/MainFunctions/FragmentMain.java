package com.fii.robolink.MainFunctions;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fii.robolink.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {

    ArrayList<Integer> colors = new ArrayList<Integer>();
    private HorizontalBarChart horizontalBarChart;
    private PieChart pieChart_1;
    private PieChart pieChart_2;
    private PieChart pieChart_3;
    private BarChart barChart;
    private LineChart lineChart;
    private TextView oee_availability;
    private TextView oee_performance;
    private TextView oee_quality_index;

//    ArrayList<Integer> colors = new ArrayList<Integer>();

    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initHorizontalBarChart();
        initPieChart_1();
        initPieChart_2();
        initPieChart_3();
        initBarChart();
        initChart();
    }

    private void initView() {

        pieChart_1 = getActivity().findViewById(R.id.pie_chart_1);
        pieChart_2 = getActivity().findViewById(R.id.pie_chart_2);
        pieChart_3 = getActivity().findViewById(R.id.pie_chart_3);
        barChart = getActivity().findViewById(R.id.barchart);
        horizontalBarChart = getActivity().findViewById(R.id.horizontalbarchart);
        lineChart = getActivity().findViewById(R.id.linechart);

        oee_availability = getActivity().findViewById(R.id.oee_availability);
        oee_performance = getActivity().findViewById(R.id.oee_performance);
        oee_quality_index = getActivity().findViewById(R.id.oee_quality_index);

        // 各图颜色，有顺序要求，要对应顺序
        colors.add(0xff19b2e6);
        colors.add(0xffeee8aa);
        colors.add(0xffff69b4);
        colors.add(0xff90ee90);
        colors.add(0xffe9967a);
    }


    private void initHorizontalBarChart(){
        horizontalBarChart.setDescription(null);

        ArrayList<BarEntry> xData = new ArrayList<BarEntry>();
        ArrayList<String> yCate = new ArrayList<>();

        xData.add(new BarEntry(0,3));
        xData.add(new BarEntry(1,2));
        xData.add(new BarEntry(2,5));
        xData.add(new BarEntry(3,6));
        xData.add(new BarEntry(4,8));

        yCate.add(getString(R.string.Work));
        yCate.add(getString(R.string.Sleep));
        yCate.add(getString(R.string.Online));
        yCate.add(getString(R.string.OffLine));
        yCate.add(getString(R.string.Warning));
        horizontalBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        horizontalBarChart.getXAxis().setValueFormatter(new X);

        BarDataSet barDataSet = new BarDataSet(xData,"");
        barDataSet.setColors(colors);

        ArrayList<IBarDataSet> onebardata = new ArrayList<>();
        onebardata.add(barDataSet);

        BarData barData = new BarData(onebardata);
        barData.setBarWidth(0.5f);

        horizontalBarChart.setData(barData);

        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setScaleEnabled(false);
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getAxisRight().setEnabled(false);



    }

    private void initPieChart_1(){
        pieChart_1.setDescription(null);//设置描述

        pieChart_1.setExtraOffsets(5,5,5,5);//设置饼状图距离上下左右的偏移量

        pieChart_1.setDrawCenterText(true);//是否绘制中间的文字
        pieChart_1.setCenterTextColor(Color.BLACK);//中间的文字颜色
        pieChart_1.setCenterTextSize(15);//中间的文字字体大小
        String centerText = "中";
        pieChart_1.setCenterText(centerText);//设置圆环中间的文字

        pieChart_1.setDrawHoleEnabled(true);//是否绘制饼状图中间的圆
        pieChart_1.setHoleColor(Color.WHITE);//饼状图中间的圆的绘制颜色
        pieChart_1.setHoleRadius(70f);//饼状图中间的圆的半径大小

//        pieChart_1.setTransparentCircleColor(Color.BLACK);//设置圆环的颜色
        pieChart_1.setTransparentCircleAlpha(0);//设置圆环的透明度[0,255]
        pieChart_1.setTransparentCircleRadius(70f);//设置圆环的半径值


        // enable rotation of the chart by touch
        pieChart_1.setRotationEnabled(false);
        pieChart_1.setHighlightPerTapEnabled(false);//设置点中的tab是否高亮(默认为true)


        //饼状图上字体的设置
        pieChart_1.setDrawEntryLabels(false);//设置是否绘制Label

        ArrayList<PieEntry> pieEntries_1 = new ArrayList<PieEntry>();
        pieEntries_1.add( new PieEntry(29));
        pieEntries_1.add( new PieEntry(45));
        pieEntries_1.add( new PieEntry(51));
        pieEntries_1.add( new PieEntry(32));
        pieEntries_1.add(new PieEntry(85));


        PieDataSet pieDataSet_1 = new PieDataSet(pieEntries_1, "");


        pieDataSet_1.setColors(colors);

        pieDataSet_1.setDrawValues(false);
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet_1);

        //各个饼状图所占比例数字的设置
//        pieData.setValueFormatter(new PercentFormatter());//设置%

        pieChart_1.setData(pieData);
        // undo all highlights
        pieChart_1.highlightValues(null);
        pieChart_1.invalidate();
    }


    //设备工作时间的数据在这里设置
    private void initPieChart_2(){
        pieChart_2.setUsePercentValues(true);//设置value是否用显示百分数,默认为false
        pieChart_2.setDescription(null);//设置描述
  //      pieChart.setDescriptionTextSize(20);//设置标题大小
//        pieChart.setDescriptionPosition(100,100);//设置标题位置

        pieChart_2.setExtraOffsets(5,5,5,5);//设置饼状图距离上下左右的偏移量

        pieChart_2.setDrawCenterText(false);//是否绘制中间的文字
        /*pieChart.setCenterTextColor(Color.RED);//中间的文字颜色
        pieChart.setCenterTextSize(15);//中间的文字字体大小*/

        pieChart_2.setDrawHoleEnabled(false);//是否绘制饼状图中间的圆
        /*pieChart.setHoleColor(Color.WHITE);//饼状图中间的圆的绘制颜色
        pieChart.setHoleRadius(40f);//饼状图中间的圆的半径大小*/

        pieChart_2.setTransparentCircleColor(Color.BLACK);//设置圆环的颜色
        pieChart_2.setTransparentCircleAlpha(100);//设置圆环的透明度[0,255]
        pieChart_2.setTransparentCircleRadius(40f);//设置圆环的半径值

        // enable rotation of the chart by touch
        pieChart_2.setRotationEnabled(false);//设置饼状图是否可以旋转(默认为true)
   //     pieChart.setRotationAngle(10);//设置饼状图旋转的角度

        pieChart_2.setHighlightPerTapEnabled(true);//设置点中的tab是否高亮(默认为true)

        //右边小方框部分
        Legend l = pieChart_2.getLegend(); //设置比例图
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);//设置每个tab的显示位置（这个位置是指下图右边小方框部分的位置 ）
//        l.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
        l.setTextSize(12);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);//设置右侧标签tab之间Y轴方向上的空白间距值
        l.setYOffset(0f);

        //饼状图上字体的设置
        // entry label styling
        pieChart_2.setDrawEntryLabels(true);//设置是否绘制Label
        pieChart_2.setEntryLabelColor(Color.BLACK);//设置绘制Label的颜色
        pieChart_2.setEntryLabelTextSize(14);//设置绘制Label的字体大小

//        pieChart.setOnChartValueSelectedListener(this);//设值点击时候的回调
//        pieChart.animateY(3400, Easing.EasingOption.EaseInQuad);//设置Y轴上的绘制动画
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        pieEntries.add( new PieEntry(45,getString(R.string.Electricity_on)));
        pieEntries.add( new PieEntry(20,getString(R.string.Warning)));
        pieEntries.add( new PieEntry(5,getString(R.string.Running)));
        pieEntries.add( new PieEntry(10,getString(R.string.Sleep)));
        pieEntries.add(new PieEntry(20,getString(R.string.Break)));

        /*String centerText = "2016年消费\n¥"+12000;
        pieChart.setCenterText(centerText);//设置圆环中间的文字*/
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(colors);

        pieDataSet.setSliceSpace(5f);//设置选中的Tab离两边的距离
        pieDataSet.setSelectionShift(5f);//设置选中的tab的多出来的
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);

        //各个饼状图所占比例数字的设置
        pieData.setValueFormatter(new PercentFormatter());//设置%
        pieData.setValueTextSize(14);
        pieData.setValueTextColor(Color.BLACK);

        pieChart_2.setData(pieData);
        // undo all highlights
        pieChart_2.highlightValues(null);
        pieChart_2.invalidate();
    }

    private void initPieChart_3(){
        pieChart_3.setUsePercentValues(false);//设置value是否用显示百分数,默认为false
        pieChart_3.setDescription(null);//设置描述

        pieChart_3.setExtraOffsets(5,5,5,5);//设置饼状图距离上下左右的偏移量

        pieChart_3.setDrawCenterText(true);//是否绘制中间的文字
        pieChart_3.setCenterTextColor(Color.BLACK);//中间的文字颜色
        pieChart_3.setCenterTextSize(20);//中间的文字字体大小

        pieChart_3.setDrawHoleEnabled(true);//是否绘制饼状图中间的圆
        pieChart_3.setHoleColor(Color.WHITE);//饼状图中间的圆的绘制颜色
        pieChart_3.setHoleRadius(70f);//饼状图中间的圆的半径大小

        pieChart_3.setTransparentCircleRadius(0);//设置圆环的半径值

        // enable rotation of the chart by touch
        pieChart_3.setRotationEnabled(false);//设置饼状图是否可以旋转(默认为true)
        pieChart_3.setHighlightPerTapEnabled(false);//设置点中的tab是否高亮(默认为true)

        //饼状图上字体的设置
        // entry label styling
        pieChart_3.setDrawEntryLabels(false);//设置是否绘制Label

        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        pieEntries.add( new PieEntry(20));
        pieEntries.add( new PieEntry(20));

        String centerText = 40+ "%";
        pieChart_3.setCenterText(centerText);//设置圆环中间的文字
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);

        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);

        //各个饼状图所占比例数字的设置
        pieData.setValueFormatter(new PercentFormatter());//设置%
        pieData.setValueTextSize(14);
        pieData.setValueTextColor(Color.BLACK);

        pieChart_3.setData(pieData);
        // undo all highlights
        pieChart_3.highlightValues(null);
        pieChart_3.invalidate();

        oee_availability.setText("可用率：" + 30 + "%");
        oee_performance.setText("表现性：" + 0.5);
        oee_quality_index.setText("质量指数：" + 3);
    }

    private void initBarChart(){

        ArrayList<BarEntry> yData = new ArrayList<BarEntry>();
        ArrayList<String> xCate = new ArrayList<>();
        Integer[] y_Value = {10, 8, 4,12,15, 9, 7, 9, 10, 17, 19, 20, 20, 21,
        22, 24, 18, 9, 16, 25, 20, 23, 15, 13};
        String[] lables = {"love", "peace", "robot", "sex"};

        for ( int i = 0; i < 24; i ++) {
            yData.add(new BarEntry(i, y_Value[i]));
        }


        BarDataSet barDataSet = new BarDataSet(yData,"");
        barDataSet.setColors(colors);
        barDataSet.setBarBorderWidth(0.2f);
        barDataSet.setDrawValues(false);

        ArrayList<IBarDataSet> onebardata = new ArrayList<>();
        onebardata.add(barDataSet);

        BarData barData = new BarData(onebardata);

        barChart.setData(barData);
        barChart.setDescription(null);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setFitBars(true);
        barChart.setDrawGridBackground(false);
        barChart.getAxisRight().setEnabled(false);

        //绘制X轴
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        barChart.getXAxis().setDrawAxisLine(false);

    }

    private void initChart() {
        /***图表设置***/
        //简介
        lineChart.setDescription(null);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(false);
        //设置XY轴动画效果
        /*lineChart.animateY(2500);
        lineChart.animateX(1500);*/


        /***XY轴的设置***/
        XAxis xAxis = lineChart.getXAxis();
        YAxis leftYAxis = lineChart.getAxisLeft();
        YAxis rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinValue(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinValue(0f);
        rightYaxis.setAxisMinValue(0f);

        /***折线图例 标签 设置***/
        Legend legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);


        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Entry entry = new Entry(i, (float) i);
            entries.add(entry);
        }

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "1");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setValueTextSize(15.f);
    }
}
