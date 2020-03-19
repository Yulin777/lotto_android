package com.yulin.lotto.activities.winnings;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.R;

interface IWinningsView {

    RecyclerView getWinningRecyclerView();

    Context getContext();
}

public class WinningsActivity extends AppCompatActivity implements IWinningsView {
    Presenter mPresenter;
    RecyclerView winningsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
        mPresenter = new Presenter(this);
        findViews();
        mPresenter.setViews();
    }

    private void findViews() {
        winningsRecyclerView = findViewById(R.id.winnings_recyclerview);
    }

    @Override
    public RecyclerView getWinningRecyclerView() {
        return this.winningsRecyclerView;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_in_down);
    }
}
