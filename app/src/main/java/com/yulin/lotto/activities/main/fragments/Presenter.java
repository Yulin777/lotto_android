package com.yulin.lotto.activities.main.fragments;

import android.view.View;
import android.widget.CheckBox;

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
