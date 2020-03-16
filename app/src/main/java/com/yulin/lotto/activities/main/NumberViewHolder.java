package com.yulin.lotto.activities.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.R;

/**
 * Created by Yulin. I on 16,March,2020
 */
class NumberViewHolder extends RecyclerView.ViewHolder {
    TextView index;
    ImageView box;

    public NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        index = itemView.findViewById(R.id.index);
        box = itemView.findViewById(R.id.box);
    }

    public void removeFirstThree() {
        ((ConstraintLayout) itemView).removeAllViews();
    }

    public void setIndex(int i) {
        this.index.setText(String.valueOf(i));
    }

    public void setSelection(Boolean isSelected) {
        box.setBackground(box.getResources().getDrawable(isSelected ? R.drawable.filled_box : R.drawable.empty_box));
    }
}
