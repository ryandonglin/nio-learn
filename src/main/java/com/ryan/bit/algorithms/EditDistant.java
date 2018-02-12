package com.ryan.bit.algorithms;

import java.util.regex.Matcher;

/**
 * 编辑距离，动态规划实现
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/2/8
 * @since 1.0.0
 */
public class EditDistant {

    int[][] d;

    public static void main(String[] args) {
        EditDistant editDistant = new EditDistant();
        System.out.println(editDistant.computeEditDistance("beauty", "batyu"));
        System.out.println(editDistant.computeEditDistance("cafe", "coffee"));

    }


    public int computeEditDistance(String str1, String str2) {

        if (!str1.isEmpty() && !str2.isEmpty()) {
            d = new int[str1.length()+1][str2.length()+1];
        }

        // init
        for (int i = 0; i < str2.length() + 1; i++) {
            d[0][i] = i;
        }

        for (int i = 0; i < str1.length() + 1; i++) {
            d[i][0] = i;
        }

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                int min_0 = Math.min(d[i+1][j] + 1, d[i][j+1] + 1);
                if (str1.charAt(i) == str2.charAt(j)) {
                    d[i+1][j+1] = Math.min(min_0, d[i][j]);
                } else {
                    d[i+1][j+1] = Math.min(min_0, d[i][j] + 1);
                }
            }
        }

        /*for (int i = 0; i <str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }*/

        return d[str1.length()][str2.length()];
    }
}
