package com.yulin.lotto.activities.winnings;

import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.common.PreviousWinners;

/**
 * Created by Yulin. I on 19,March,2020
 */
class Presenter {
    private final IWinningsView mView;
    private WinningAdapter winningAdapter;

    Presenter(IWinningsView view) {
        this.mView = view;
    }

    void setViews() {
        setWinningRecyclerView();
    }

    private void setWinningRecyclerView() {
        RecyclerView recyclerView = mView.getWinningRecyclerView();
        winningAdapter = new WinningAdapter();
        PreviousWinners previousWinners = new PreviousWinners(mView.getContext());
        previousWinners.fetchWinList(winList -> winningAdapter.setWinList(winList));
        recyclerView.setAdapter(winningAdapter);
    }
}
