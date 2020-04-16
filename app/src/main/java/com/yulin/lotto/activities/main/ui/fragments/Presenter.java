package com.yulin.lotto.activities.main.ui.fragments;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.lotto.activities.main.TableAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.yulin.lotto.activities.main.NumbersGenerator.MAX_NUM;
import static com.yulin.lotto.activities.main.NumbersGenerator.MAX_NUMS_TO_FILL;

/**
 * Created by Yulin. I on 17,March,2020
 */
class Presenter {
    private IFilterView mView;
    private TableAdapter includedAdapter;
    private TableAdapter excludedAdapter;

    Presenter(IFilterView mView) {
        this.mView = mView;
    }

    void setViews() {
        setAdapters();
        setIncludedNums();
        setExcludeNums();
        setSequentialNumbers();
        setExcludeWinnings();
    }

    private void setExcludeWinnings() {

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
        setOnCheckAnimation(sequentialNumbersCheckbox, limitSeqContainer);
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

    private void setAdapters() {
        excludedAdapter = new TableAdapter();
        includedAdapter = new TableAdapter();

        excludedAdapter.setMaxChosen(MAX_NUM - MAX_NUMS_TO_FILL, () -> mView.onExcludeMaxSelectionReached());
        excludedAdapter.addEventOnItemClick(index -> includedAdapter.unSelectItem(index));

        includedAdapter.setMaxChosen(MAX_NUMS_TO_FILL, () -> mView.onIncludeMaxSelectionReached());
        includedAdapter.addEventOnItemClick(index -> excludedAdapter.unSelectItem(index));
    }

    private void setExcludeNums() {
        CheckBox excludedNumsCheckbox = mView.getExcludedNumbersCheckBox();
        RecyclerView numbersTable = mView.getExcludedNumbersTable();

        numbersTable.setAdapter(excludedAdapter);
        numbersTable.setLayoutManager(new GridLayoutManager(numbersTable.getContext(), 10));

        setOnCheckAnimation(excludedNumsCheckbox, numbersTable);
    }

    private void setIncludedNums() {
        CheckBox IncludedNumsCheckbox = mView.getIncludeNumbersCheckBox();

        RecyclerView numbersTable = mView.getIncludeNumbersTable();
        numbersTable.setAdapter(includedAdapter);
        numbersTable.setLayoutManager(new GridLayoutManager(numbersTable.getContext(), 10));

        setOnCheckAnimation(IncludedNumsCheckbox, numbersTable);
    }

}
