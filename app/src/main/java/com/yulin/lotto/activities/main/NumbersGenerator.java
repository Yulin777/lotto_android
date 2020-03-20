package com.yulin.lotto.activities.main;

import com.yulin.lotto.activities.main.ui.fragments.IFilterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class NumbersGenerator {
    public static final int MAX_NUM = 37;
    public static final int MAX_NUMS_TO_FILL = 6;
    private final IFilterView filterView;
    List<Boolean> generatedNumbers = new ArrayList<>();

    List<Integer> mustNumbers = new ArrayList<>();
    List<Integer> excludedNumbers = new ArrayList<>();
    int seqNumbersLimit;
    private boolean excludeWonNumbers;

    public NumbersGenerator(IFilterView filterView) {
        this.filterView = filterView;
    }

    public List<Boolean> generateFilteredNumbers() {
        List<Integer> temp;
        do {
            generatedNumbers = new ArrayList<>(Collections.nCopies(MAX_NUM, false)); //reset

            for (Integer mustNumber : mustNumbers) {
                generatedNumbers.set(mustNumber, true);
            }

            temp = new ArrayList<>();
            for (int i = 0; i < MAX_NUMS_TO_FILL - mustNumbers.size(); i++) {
                int randNum;

                do {
                    randNum = (int) (Math.random() * (MAX_NUM - 1)) + 1;
                } while (!passFiltersForSingleNumber(randNum));

                generatedNumbers.set(randNum, true);
                temp.add(randNum);
            }
        } while (!passFiltersForWholeChoice(temp));

        return generatedNumbers;
    }

    /**
     * second step of filtering.
     * this filters the array of chosen numbers.
     *
     * @param generatedNumbers generated choice after the first filter.
     * @return true if passes filters for the whole choice
     */
    private boolean passFiltersForWholeChoice(List<Integer> generatedNumbers) {
        return !isInWonNumbers(generatedNumbers);
    }

    private boolean isInWonNumbers(List<Integer> generatedNumbers) {
        return false;
    }

    private boolean hasMustNumbers(List<Integer> generatedNumbers) {
        for (Integer mustNumber : mustNumbers) {
            if (!generatedNumbers.contains(mustNumber))
                return false;
        }
        return true;
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
                && !excludedNumbers.contains(randNum)
                && !numberIsPartOfSequence(randNum, generatedNumbers);
    }

    private boolean numberIsPartOfSequence(int randNum, List<Boolean> generatedNumbers) {
        if (filterView.getSequentialNumbersCheckbox().isChecked()) {
            int limit = filterView.getLimitSeqBar().getProgress();

            return isSequenceFromLeft(randNum, generatedNumbers, limit) || isSequenceFromRight(randNum, generatedNumbers, limit);
        }
        return false;
    }

    private boolean isSequenceFromRight(int randNum, List<Boolean> generatedNumbers, int limit) {
        int cntFromRight = 0;

        for (int i = randNum + 1; i < randNum + 1 + limit && i < MAX_NUM; i++) {
            if (generatedNumbers.get(i)) {
                cntFromRight++;
            }
            if (cntFromRight == limit) return true;
        }
        return false;
    }

    private boolean isSequenceFromLeft(int randNum, List<Boolean> generatedNumbers, int limit) {
        int cntFromLeft = 0;

        for (int i = randNum - 1; i > randNum - 1 - limit && i >= 0; i--) {
            if (generatedNumbers.get(i)) {
                cntFromLeft++;
            }
            if (cntFromLeft == limit) return true;
        }
        return false;
    }

    public void updateFilters() {
        updateMustNumbersFilter();
        updateExcludedNumbersFilter();
        updateLimitSeqNumbers();
        updateExcludeWonNumbers();
    }

    private void updateExcludeWonNumbers() {
        excludeWonNumbers = filterView.getExcludeWonNumbers().isChecked();
    }

    private void updateLimitSeqNumbers() {
        seqNumbersLimit = 6;
        if (filterView.getSequentialNumbersCheckbox().isChecked()) {
            seqNumbersLimit = filterView.getLimitSeqBar().getProgress();
        }
    }

    private void updateExcludedNumbersFilter() {
        excludedNumbers = new ArrayList<>();
        if (filterView.getExcludedNumbersCheckBox().isChecked()) {
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
        if (filterView.getIncludeNumbersCheckBox().isChecked()) {
            List<Boolean> boolList = ((TableAdapter) filterView.getIncludeNumbersTable().getAdapter()).getChoiceList();
            for (int i = 0; i < boolList.size(); i++) {
                if (boolList.get(i)) {
                    mustNumbers.add(i - 3);
                }
            }
        }
    }
}
