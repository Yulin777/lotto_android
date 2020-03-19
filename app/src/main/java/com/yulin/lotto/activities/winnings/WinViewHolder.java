package com.yulin.lotto.activities.winnings;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.R;
import com.yulin.lotto.common.WinObject;

/**
 * Created by Yulin. I on 19,March,2020
 */
class WinViewHolder extends RecyclerView.ViewHolder {
    TextView serial;
    TextView first, second, third, fourth, fifth, sixth, strong;
    TextView date;

    public WinViewHolder(@NonNull View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View itemView) {
        this.serial = itemView.findViewById(R.id.serial);
        this.date = itemView.findViewById(R.id.date);
        this.first = itemView.findViewById(R.id.first);
        this.second = itemView.findViewById(R.id.second);
        this.third = itemView.findViewById(R.id.third);
        this.fourth = itemView.findViewById(R.id.fourth);
        this.fifth = itemView.findViewById(R.id.fifth);
        this.sixth = itemView.findViewById(R.id.sixth);
        this.strong = itemView.findViewById(R.id.strong);
    }

    public void updateUI(WinObject winObject) {
        this.serial.setText(winObject.serial);
        this.date.setText(winObject.date);
        this.first.setText(winObject.first);
        this.second.setText(winObject.second);
        this.third.setText(winObject.third);
        this.fourth.setText(winObject.fourth);
        this.fifth.setText(winObject.fifth);
        this.sixth.setText(winObject.sixth);
        this.strong.setText(winObject.strong);
    }
}
