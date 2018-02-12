package com.ryan.bit.study.leetcode;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/9/28
 * @since 1.0.0
 */
public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbStairs(44));
    }

    public int climbStairs(int n) {
        if (1 == n) {
            return  1;
        } else if (2 == n) {
            return 2;
        } else  {
            int sum_1 = 1;
            int sum_2 = 2;
            for (int i = 3 ; i <= n; i++) {
                int tmp = sum_2;
                sum_2 = sum_1 + sum_2;
                sum_1 = tmp;
            }
            return sum_2;
        }
    }
}
