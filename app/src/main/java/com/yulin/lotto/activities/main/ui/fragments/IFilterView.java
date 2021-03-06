package com.yulin.lotto.activities.main.ui.fragments;

import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public interface IFilterView {

    CheckBox getIncludeNumbersCheckBox();

    RecyclerView getIncludeNumbersTable();

    CheckBox getExcludedNumbersCheckBox();

    RecyclerView getExcludedNumbersTable();

    CheckBox getSequentialNumbersCheckbox();

    LinearLayout getLimitSeqContainer();

    SeekBar getLimitSeqBar();

    TextView getLimitSeqChoice();

    CheckBox getExcludeWonNumbers();

    void onIncludeMaxSelectionReached();

    void onExcludeMaxSelectionReached();
}
