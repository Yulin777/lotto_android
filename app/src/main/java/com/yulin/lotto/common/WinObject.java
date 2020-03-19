package com.yulin.lotto.common;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class WinObject {
    public String serial;
    public String first, second, third, fourth, fifth, sixth, strong;
    public String date;

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
