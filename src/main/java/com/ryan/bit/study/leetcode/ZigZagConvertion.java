package com.ryan.bit.study.leetcode;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/10/19
 * @since 1.0.0
 */
public class ZigZagConvertion {

    public static void main(String[] args) {
        ZigZagConvertion zigZagConvertion = new ZigZagConvertion();
        String res = zigZagConvertion.convert1("abcdefghijklmnopqrstvuwx", 4);
        System.out.println(res);
    }

    public String convert(String s, int numRows) {
        if (null == s || s.length() == 0) {
            return s;
        } else {
            char[][] table = new char[numRows][s.length()];
            int row = 0, col = 0;
            int pos = 0;
            for (col = 0; pos < s.length(); col++) {
                if (col % (numRows - 1) == 0) {
                    for (row = 0; row < numRows && pos < s.length(); ) {
                        table[row][col] = s.charAt(pos++);
                        row++;
                    }
                    row--;
                } else {
                    table[--row][col] = s.charAt(pos++);
                }
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < s.length(); j++) {
                    System.out.print(" " + table[i][j]);
                }
                System.out.println();
            }
            String res = "";
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < col; j++) {
                    if (table[i][j] != 0) {
                        res += table[i][j];
                    }
                }
            }
            return res;
        }
    }


    public String convert1(String s, int numRows) {
        if (s.length() <= 0 || numRows < 2) {
            return s;
        }
        String res = "";
        int gap = 2 * (numRows - 1);
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); j += gap) {
                res += s.charAt(j);
                if (i != 0 && i != numRows - 1) {
                    if (j + gap - 2 * i < s.length()) {
                        res += s.charAt(j + gap - 2 * i);
                    }
                }
            }
        }
        return res;
    }

}
