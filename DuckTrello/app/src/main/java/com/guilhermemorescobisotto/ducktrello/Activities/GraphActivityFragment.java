package com.guilhermemorescobisotto.ducktrello.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.Models.Member;
import com.guilhermemorescobisotto.ducktrello.Models.TrelloList;
import com.guilhermemorescobisotto.ducktrello.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class GraphActivityFragment extends Fragment implements OnChartValueSelectedListener {

    private PieChart pieChart;
    private ProgressBar graphProgressBar;
    private List<GraphUser> users;

    private class GraphUser {
        private String userName;
        private int count;

        public GraphUser(String userName) {
            this.userName = userName;
            this.count = 1;
        }

        public int getCount() {
            return count;
        }

        public String getUserName() {
            return userName;
        }

        public void setCount() {
            this.count = this.count + 1;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            StringBuilder userGraphToString = new StringBuilder();
            return (userGraphToString.append("UserName: ").append(getUserName()).append(" Count: ").append(getCount())).toString();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.pieChart = (PieChart) view.findViewById(R.id.pieChart);
        this.graphProgressBar = (ProgressBar) view.findViewById(R.id.graph_progressBar);

        getDataToGraph();
    }

    private void getDataToGraph() {

        Board board = DataHolder.getRef().currentBoard;
        this.users = new ArrayList<>();

        for (TrelloList list : board.getTrelloLists()) {
            for (Card card : list.getCards()) {
                for (Member member : card.getMemberList()) {
                    boolean exist = false;
                    for (GraphUser user : users) {
                        if (user.getUserName().equalsIgnoreCase(member.username)) {
                            exist = true;
                            user.setCount();
                        }
                    }

                    if (!exist) {
                        GraphUser graphUser = new GraphUser(member.username);
                        this.users.add(graphUser);
                    }
                }
            }
        }

        this.graphProgressBar.setVisibility(View.GONE);
        configureGraph();
    }

    private void configureGraph() {

        this.pieChart.setVisibility(View.VISIBLE);

        this.pieChart.setUsePercentValues(true);
        this.pieChart.setDescription("% das tarefas por usuário");
        this.pieChart.setNoDataTextDescription("Sem dados disponíveis");
        this.pieChart.setExtraOffsets(5, 10, 5, 5);

        this.pieChart.setDragDecelerationFrictionCoef(0.95f);

        this.pieChart.setDrawHoleEnabled(true);
        this.pieChart.setHoleColor(Color.WHITE);

        this.pieChart.setTransparentCircleColor(Color.WHITE);
        this.pieChart.setTransparentCircleAlpha(110);

        this.pieChart.setHoleRadius(58f);
        this.pieChart.setTransparentCircleRadius(61f);

        this.pieChart.setDrawCenterText(true);

        this.pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        this.pieChart.setRotationEnabled(true);
        this.pieChart.setHighlightPerTapEnabled(true);

        setData(this.users.size(), 100);

        this.pieChart.setOnChartValueSelectedListener(this);

        this.pieChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);

        Legend l = this.pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }


    public String[] getUserArray() {
        ArrayList<String> names = new ArrayList<>();

        for (GraphUser graphUser : this.users) {
            names.add(graphUser.getUserName());
        }

        String[] u = new String[names.size()];
        u = names.toArray(u);

        return u;
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < count; i++) {
            yVals1.add(new Entry((float) this.users.get(i).getCount(), i));
        }

        String[] mUsers = this.getUserArray();

        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            xVals.add(mUsers[i]);
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        this.pieChart.setData(data);

        // undo all highlights
        this.pieChart.highlightValues(null);

        this.pieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {
        if (entry == null)
            return;

        Essential.log("VAL SELECTED Value: " + entry.getVal() + ", xIndex: " + entry.getXIndex()
                        + ", DataSet index: " + i);
    }

    @Override
    public void onNothingSelected() {

    }
}
