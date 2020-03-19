package com.yulin.lotto.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.yulin.lotto.R;
import com.yulin.lotto.activities.main.fragments.FilterFragment;
import com.yulin.lotto.activities.winnings.WinningsActivity;

interface IMainView {
    RecyclerView getTable();

    Button getDrawBtn();

    Button getFilterBtn();

    void openFilterDrawer();

    Button getWinningsBtn();

    void openWinningsActivity();
}

public class MainActivity extends AppCompatActivity implements IMainView {
    Presenter mPresenter;
    private RecyclerView table;
    private Button drawBtn;
    private Button filterBtn;
    private Button winningsBtn;
    private DrawerLayout drawerLayout;
    private NavigationView filterDrawer;

    @Override
    public Button getFilterBtn() {
        return filterBtn;
    }

    @Override
    public void openFilterDrawer() {
        drawerLayout.openDrawer(filterDrawer);
    }

    @Override
    public Button getWinningsBtn() {
        return this.winningsBtn;
    }

    @Override
    public void openWinningsActivity() {
        Intent intent = new Intent(this, WinningsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_in_up);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.filter_drawer_container, new FilterFragment())
                .commit();

        mPresenter = new Presenter(this);
        findViews();
        mPresenter.setViews();
    }

    private void findViews() {
        this.table = findViewById(R.id.table);
        this.drawBtn = findViewById(R.id.draw_btn);
        this.filterBtn = findViewById(R.id.filter_btn);
        this.winningsBtn = findViewById(R.id.winnings_btn);
        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.filterDrawer = findViewById(R.id.filter_drawer);
    }

    @Override
    public RecyclerView getTable() {
        return this.table;
    }

    @Override
    public Button getDrawBtn() {
        return this.drawBtn;
    }
}
