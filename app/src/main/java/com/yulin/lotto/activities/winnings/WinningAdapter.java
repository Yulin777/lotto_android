package com.yulin.lotto.activities.winnings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.yulin.lotto.R;
import com.yulin.lotto.common.WinObject;

import java.util.List;

/**
 * Created by Yulin. I on 19,March,2020
 */
class WinningAdapter extends Adapter<WinViewHolder> {
    private List<WinObject> winList;

    @NonNull
    @Override
    public WinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.winning_item, parent, false);
        return new WinViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WinViewHolder holder, int position) {
        holder.updateUI(winList.get(position));
    }

    @Override
    public int getItemCount() {
        return winList.size();
    }

    public void setWinList(List<WinObject> winList) {
        this.winList = winList;
        notifyDataSetChanged();
    }

}
