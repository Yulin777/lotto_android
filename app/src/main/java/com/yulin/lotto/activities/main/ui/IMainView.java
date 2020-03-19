package com.yulin.lotto.activities.main.ui;

import android.widget.Button;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.yulin.lotto.activities.main.ui.fragments.IFilterView;

public interface IMainView {
    RecyclerView getTable();

    Button getDrawBtn();

    Button getFilterBtn();

    void openFilterDrawer();

    Button getWinningsBtn();

    void openWinningsActivity();

    NavigationView getFilterDrawer();

    DrawerLayout getContainer();

    IFilterView getFilterView();
}
