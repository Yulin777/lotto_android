package com.yulin.lotto.activities.winnings;

import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.common.PreviousWinners;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class Presenter {
    private final IWinningsView mView;
    private WinningAdapter winningAdapter;

    public Presenter(IWinningsView view) {
        this.mView = view;
    }

    public void setViews() {
        setWinningRecyclerView();
    }

    private void setWinningRecyclerView() {
        RecyclerView recyclerView = mView.getWinningRecyclerView();
        winningAdapter = new WinningAdapter();
        PreviousWinners previousWinners = PreviousWinners.getInstance(mView.getContext(), winList -> winningAdapter.setWinList(winList));
        recyclerView.setAdapter(winningAdapter);
    }
}
