package com.yulin.lotto.activities.main;

import com.yulin.lotto.activities.main.ui.fragments.IFilterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class NumbersGenerator {
    private static final int MAX_NUM = 37;
    private static final int MAX_NUMS_TO_FILL = 6;
    private final IFilterView filterView;
    List<Boolean> generatedNumbers;

    List<Integer> mustNumbers;
    List<Integer> excludedNumbers;
    int seqNumbersLimit;
    private boolean excludeWonNumbers;

    public NumbersGenerator(IFilterView filterView) {
        this.filterView = filterView;
    }

    public List<Boolean> generateFilteredNumbers() {
        generatedNumbers = new ArrayList<>(Collections.nCopies(37, false)); //reset

        do
        {
            for (int i = 0; i < MAX_NUMS_TO_FILL; i++) {
                int randNum;

                do {
                    randNum = (int) (Math.random() * (MAX_NUM - 1)) + 1;
                } while (!passFiltersForSingleNumber(randNum) /*numbers.get(randNum)*/);

                generatedNumbers.set(randNum, true);
            }
        } while (!passFiltersForWholeChoice(generatedNumbers));
        return generatedNumbers;
    }

    /**
     * second step of filtering.
     * this filters the array of chosen numbers.
     *
     * @param generatedNumbers generated choice after the first filter.
     * @return true if passes filters for the whole choice
     */
    private boolean passFiltersForWholeChoice(List<Boolean> generatedNumbers) {
        return false;
    }

    /**
     * first step of filtering.
     * this filters the specific generated rand number.
     *
     * @param randNum current generated number
     * @return true if current number passes the exclusive filters so far
     */
    private boolean passFiltersForSingleNumber(int randNum) {
        return !generatedNumbers.get(randNum)
                && !excludedNumbers.contains(randNum);
    }

    public void updateFilters() {
        updateMustNumbersFilter();
        updateExcludedNumbersFilter();
        updateLimitSeqNumbers();
        updateExcludeWonNumbers();
    }

    private void updateExcludeWonNumbers() {
        excludeWonNumbers = filterView.getExcludeWonNumbers().isSelected();
    }

    private void updateLimitSeqNumbers() {
        seqNumbersLimit = 6;
        if (filterView.getSequentialNumbersCheckbox().isSelected()) {
            seqNumbersLimit = filterView.getLimitSeqBar().getProgress();
        }
    }

    private void updateExcludedNumbersFilter() {
        excludedNumbers = new ArrayList<>();
        if (filterView.getExcludedNumbersCheckBox().isSelected()) {
            List<Boolean> boolList = ((TableAdapter) filterView.getExcludedNumbersTable().getAdapter()).getChoiceList();
            for (int i = 0; i < boolList.size(); i++) {
                if (boolList.get(i)) {
                    excludedNumbers.add(i - 3);
                }
            }
        }
    }

    private void updateMustNumbersFilter() {
        mustNumbers = new ArrayList<>();
        if (filterView.getMustNumbersCheckBox().isSelected()) {
            List<Boolean> boolList = ((TableAdapter) filterView.getMustNumbersTable().getAdapter()).getChoiceList();
            for (int i = 0; i < boolList.size(); i++) {
                if (boolList.get(i)) {
                    mustNumbers.add(i - 3);
                }
            }
        }
    }
}
