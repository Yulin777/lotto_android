package com.yulin.lotto.activities.winnings;

/**
 * Created by Yulin. I on 19,March,2020
 */
class WinObject {
    String serial;
    String first, second, third, fourth, fifth, sixth, strong;
    String date;

    public WinObject(String[] splitted) {

        this.serial = splitted[0];
        this.date = splitted[1];
        this.first = splitted[7];
        this.second = splitted[6];
        this.third = splitted[5];
        this.fourth = splitted[4];
        this.fifth = splitted[3];
        this.sixth = splitted[2];
        this.strong = splitted[8];
    }
}
