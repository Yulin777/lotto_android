package com.yulin.lotto.activities.main.fragments;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.activities.main.TableAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Yulin. I on 17,March,2020
 */
class Presenter {
    private IFilterView mView;
    private TableAdapter adapter;

    public Presenter(IFilterView mView) {
        this.mView = mView;
    }

    public void setViews() {
        setMustNums();
        setMustNotNums();
        setSequentialNumbers();
    }

    private void setSequentialNumbers() {
        CheckBox sequentialNumbersCheckbox = mView.getSequentialNumbersCheckbox();
        LinearLayout limitSeqContainer = mView.getLimitSeqContainer();
        SeekBar seekBar = mView.getLimitSeqBar();
        TextView chosenNumber = mView.getLimitSeqChoice();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                chosenNumber.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
//        Spinner sequentialNumbersSpinner = mView.getSequentialNumbersSpinner();
//        Integer[] items = new Integer[]{1, 2, 3, 4, 5, 6};
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(sequentialNumbersSpinner.getContext(), android.R.layout.simple_spinner_item, items);
//        sequentialNumbersSpinner.setAdapter(adapter);

        setOnCheckAnimation(sequentialNumbersCheckbox, limitSeqContainer/*sequentialNumbersSpinner*/);
    }

    private void setMustNotNums() {
        CheckBox mustNotNumsCheckbox = mView.getMustNotNumbersCheckBox();
        RecyclerView numbersTable = mView.getMustNotNumbersTable();
        setNumsTable(numbersTable);

        setOnCheckAnimation(mustNotNumsCheckbox, numbersTable);

    }

    private void setOnCheckAnimation(CheckBox mustNotNumsCheckbox, View view) {
        mustNotNumsCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                view.animate()
                        .withStartAction(() -> {
                            view.setTranslationY(-view.getHeight() * 0.1f);
                            view.setVisibility(VISIBLE);
                        })
                        .alpha(1)
                        .translationY(0);
            } else {
                view.animate()
                        .translationY(-view.getHeight() * 0.1f)
                        .alpha(0)
                        .withEndAction(() -> view.setVisibility(GONE));
            }
        });
    }

    private void setMustNums() {
        CheckBox mustNumsCheckbox = mView.getMustNumbersCheckBox();
        RecyclerView numbersTable = mView.getMustNumbersTable();
        setNumsTable(numbersTable);

        setOnCheckAnimation(mustNumsCheckbox, numbersTable);
    }

    private void setNumsTable(RecyclerView numbersTable) {
        numbersTable.setAdapter(new TableAdapter());
        numbersTable.setLayoutManager(new GridLayoutManager(numbersTable.getContext(), 10));
    }
}
