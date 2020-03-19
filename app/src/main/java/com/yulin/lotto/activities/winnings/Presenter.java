package com.yulin.lotto.activities.winnings;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class Presenter {
    private final IWinningsView mView;
    private final String winningsFileName = "raw/lotto.csv";

    public Presenter(IWinningsView view) {
        this.mView = view;
    }

    public void setViews() {
        setWinningRecyclerView();
    }

    private void setWinningRecyclerView() {
        RecyclerView recyclerView = mView.getWinningRecyclerView();
        WinningAdapter adapter = new WinningAdapter(mView.getContext());
        recyclerView.setAdapter(adapter);
    }
}
