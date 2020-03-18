package com.yulin.lotto.activities.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.R;

interface IFilterView {

    CheckBox getMustNumbersCheckBox();

    RecyclerView getMustNumbersTable();

    CheckBox getMustNotNumbersCheckBox();

    RecyclerView getMustNotNumbersTable();

    CheckBox getSequentialNumbersCheckbox();

    LinearLayout getLimitSeqContainer();

    SeekBar getLimitSeqBar();

    TextView getLimitSeqChoice();

//    Spinner getSequentialNumbersSpinner();
}

public class FilterFragment extends Fragment implements IFilterView {
    private Presenter mPresenter;
    private CheckBox mustNumbersCheckbox;
    private RecyclerView mustNumbersTable;
    private CheckBox mustNotNumbersCheckBox;
    private RecyclerView mustNotNumbersTable;
    private CheckBox sequentialNumbersCheckbox;
    private TextView limitSeqChoice;
    private SeekBar limitSeqBar;
    private LinearLayout limitSeqContainer;
//    private Spinner sequentialNumbersSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new Presenter(this);
    }

    private void findViews(View view) {
        this.mustNumbersCheckbox = view.findViewById(R.id.must_numbers_checkbox);
        this.mustNumbersTable = view.findViewById(R.id.must_numbers_table);
        this.mustNotNumbersCheckBox = view.findViewById(R.id.must_not_numbers_checkbox);
        this.mustNotNumbersTable = view.findViewById(R.id.must_not_numbers_table);
        this.sequentialNumbersCheckbox = view.findViewById(R.id.sequential_numbers_checkbox);
//        this.sequentialNumbersSpinner = view.findViewById(R.id.sequential_numbers_spinner);
        this.limitSeqContainer = view.findViewById(R.id.limit_seq_container);
        this.limitSeqChoice = view.findViewById(R.id.limit_seq_choice);
        this.limitSeqBar = view.findViewById(R.id.limit_seq_bar);
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
    public CheckBox getMustNotNumbersCheckBox() {
        return this.mustNotNumbersCheckBox;
    }

    @Override
    public RecyclerView getMustNotNumbersTable() {
        return this.mustNotNumbersTable;
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

//    @Override
//    public Spinner getSequentialNumbersSpinner() {
//        return this.sequentialNumbersSpinner;
//    }
}
