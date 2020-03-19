package com.yulin.lotto.activities.main;

import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yulin. I on 16,March,2020
 */
public class Presenter {
    private static final int MAX_NUM = 37;
    private static final int MAX_NUMS_TO_FILL = 6;
    IMainView mView;
    TableAdapter adapter;

    public Presenter(IMainView mView) {
        this.mView = mView;
    }

    public void setTable() {
        RecyclerView table = mView.getTable();
        adapter = new TableAdapter();
        table.setAdapter(adapter);
        table.setLayoutManager(new GridLayoutManager(table.getContext(), 10));
    }

    public void setViews() {
        setTable();
        setDrawBtn();
        setFilterBtn();
        setWinningsBtn();
    }

    private void setWinningsBtn() {
        Button winningsBtn = mView.getWinningsBtn();
        winningsBtn.setOnClickListener(v -> mView.openWinningsActivity());
    }

    private void setFilterBtn() {
        Button filterBtn = mView.getFilterBtn();
        filterBtn.setOnClickListener(v -> {
            mView.openFilterDrawer();
        });
    }

    private void setDrawBtn() {
        Button drawBtn = mView.getDrawBtn();
        drawBtn.setOnClickListener(v -> {
            List<Boolean> numbers = new ArrayList<>(Collections.nCopies(37, false));
            for (int i = 0; i < MAX_NUMS_TO_FILL; i++) {
                int randNum;

                do {
                    randNum = (int) (Math.random() * (MAX_NUM - 1)) + 1;
                } while (numbers.get(randNum));

                numbers.set(randNum, true);
            }
            adapter.setChoiceList(numbers);
        });
    }
}
