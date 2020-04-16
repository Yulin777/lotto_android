package com.yulin.lotto.activities.main;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.activities.main.ui.IMainView;

/**
 * Created by Yulin. I on 16,March,2020
 */
public class Presenter {
    private final NumbersGenerator generator;
    private IMainView mView;
    private TableAdapter adapter;

    public Presenter(IMainView mView) {
        this.mView = mView;
        generator = new NumbersGenerator(mView.getFilterView());
    }

    private void setTable() {
        RecyclerView table = mView.getTable();
        adapter = new TableAdapter();
        table.setAdapter(adapter);
        table.setLayoutManager(new GridLayoutManager(table.getContext(), 10));
    }

    public void setViews() {
        setTable();
        setDrawerLayout();
        setDrawBtn();
        setFilterBtn();
        setWinningsBtn();
    }

    private void setDrawerLayout() {
        DrawerLayout container = mView.getContainer();
        container.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                generator.updateFilters();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    private void setWinningsBtn() {
        Button winningsBtn = mView.getWinningsBtn();
        winningsBtn.setOnClickListener(v -> mView.openWinningsActivity());
    }

    private void setFilterBtn() {
        Button filterBtn = mView.getFilterBtn();
        filterBtn.setOnClickListener(v -> mView.openFilterDrawer());
    }

    private void setDrawBtn() {
        Button drawBtn = mView.getDrawBtn();
        drawBtn.setOnClickListener(v -> adapter.setChoiceList(generator.generateFilteredNumbers()));
    }
}
