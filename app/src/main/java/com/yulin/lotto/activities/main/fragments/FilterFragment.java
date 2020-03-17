package com.yulin.lotto.activities.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.R;

interface IFilterView {

    CheckBox getMustNumbersCheckBox();

    RecyclerView getMustNumbersTable();

    CheckBox getMustNotNumbersCheckBox();

    RecyclerView getMustNotNumbersTable();
}

public class FilterFragment extends Fragment implements IFilterView {
    private Presenter mPresenter;
    private CheckBox mustNumbersCheckbox;
    private RecyclerView mustNumbersTable;
    private CheckBox mustNotNumbersCheckBox;
    private RecyclerView mustNotNumbersTable;

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
}