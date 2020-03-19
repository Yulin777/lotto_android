package com.yulin.lotto.activities.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yulin.lotto.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yulin. I on 16,March,2020
 */
public class TableAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    private List<Boolean> choiceList = new ArrayList<>(Collections.nCopies(40, false));
    private int maxNumsToFill;
    private Runnable onMaxSelectionReached = null;

    @NonNull
    @Override

    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_item, parent, false);
        return new NumberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        if (position < 3) {
            holder.removeFirstThree();
        } else {
            holder.setIndex(position - 2);
            holder.setSelection(choiceList.get(position));
            holder.itemView.setOnClickListener(v -> {
                boolean isChosen = choiceList.get(position);
                if (onMaxSelectionReached != null && !isChosen && getChosenSize() == maxNumsToFill) {
                    onMaxSelectionReached.run();
                    return;
                }
                choiceList.set(position, !isChosen);
                notifyItemChanged(position);
            });
        }
    }

    private int getChosenSize() {
        int cnt = 0;
        for (Boolean bool : choiceList) {
            if (bool) cnt++;
        }
        return cnt;
    }

    @Override
    public int getItemCount() {
        return choiceList.size();
    }

    public void setChoiceList(List<Boolean> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            this.choiceList.set(i + 3, numbers.get(i));
        }
        notifyDataSetChanged();
    }

    public List<Boolean> getChoiceList() {
        return this.choiceList;
    }

    public void setMaxChosen(int maxNumsToFill, Runnable onMaxSelectionReached) {
        this.maxNumsToFill = maxNumsToFill;
        this.onMaxSelectionReached = onMaxSelectionReached;
    }
}
