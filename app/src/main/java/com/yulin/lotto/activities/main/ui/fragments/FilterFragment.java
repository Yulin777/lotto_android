package com.yulin.lotto.activities.main.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.R;

public class FilterFragment extends Fragment implements IFilterView {
    private Presenter mPresenter;
    private CheckBox mustNumbersCheckbox;
    private RecyclerView mustNumbersTable;
    private CheckBox excludedNumbersCheckBox;
    private RecyclerView ExcludedNumbersTable;
    private CheckBox sequentialNumbersCheckbox;
    private TextView limitSeqChoice;
    private SeekBar limitSeqBar;
    private LinearLayout limitSeqContainer;
    private CheckBox excludeWonCheckbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new Presenter(this);
    }

    private void findViews(View view) {
        this.mustNumbersCheckbox = view.findViewById(R.id.must_numbers_checkbox);
        this.mustNumbersTable = view.findViewById(R.id.must_numbers_table);
        this.excludedNumbersCheckBox = view.findViewById(R.id.exclude_numbers_checkbox);
        this.ExcludedNumbersTable = view.findViewById(R.id.exclude_numbers_table);
        this.sequentialNumbersCheckbox = view.findViewById(R.id.sequential_numbers_checkbox);
        this.limitSeqContainer = view.findViewById(R.id.limit_seq_container);
        this.limitSeqChoice = view.findViewById(R.id.limit_seq_choice);
        this.limitSeqBar = view.findViewById(R.id.limit_seq_bar);
        this.excludeWonCheckbox = view.findViewById(R.id.exclude_won_checkbox);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        findViews(view);
        mPresenter.setViews();
        return view;
    }

    @Override
    public CheckBox getMustNumbersCheckBox() {
        return this.mustNumbersCheckbox;
    }

    @Override
    public RecyclerView getMustNumbersTable() {
        return this.mustNumbersTable;
    }

    @Override
    public CheckBox getExcludedNumbersCheckBox() {
        return this.excludedNumbersCheckBox;
    }

    @Override
    public RecyclerView getExcludedNumbersTable() {
        return this.ExcludedNumbersTable;
    }

    @Override
    public CheckBox getSequentialNumbersCheckbox() {
        return this.sequentialNumbersCheckbox;
    }

    @Override
    public LinearLayout getLimitSeqContainer() {
        return this.limitSeqContainer;
    }

    @Override
    public SeekBar getLimitSeqBar() {
        return this.limitSeqBar;
    }

    @Override
    public TextView getLimitSeqChoice() {
        return this.limitSeqChoice;
    }

    @Override
    public CheckBox getExcludeWonNumbers() {
        return this.excludeWonCheckbox;
    }

}
