package com.yulin.lotto.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

import com.yulin.lotto.R;

interface IMainView {
    RecyclerView getTable();

    Button getDrawBtn();
}

public class MainActivity extends AppCompatActivity implements IMainView {
    Presenter mPresenter;
    private RecyclerView table;
    private Button drawBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(this);
        findViews();
        mPresenter.setViews();
    }

    private void findViews() {
        this.table = findViewById(R.id.table);
        this.drawBtn = findViewById(R.id.draw);
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
