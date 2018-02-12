package com.ryan.bit.study.leetcode;


/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/10/19
 * @since 1.0.0
 */
public class ParlindromeNumber {

    public static void main(String[] args) {
        ParlindromeNumber parlindromeNumber = new ParlindromeNumber();
        System.out.println(parlindromeNumber.isPalindrome(-12321));

    }

    public boolean isPalindrome(int x) {
        boolean result = Boolean.FALSE;
        if (x < 0) {
            x = Math.abs(x);
        }
        String numStr = String.valueOf(x);
        if (null != numStr && 0 != numStr.length()) {
            int len = numStr.length();
            if (len % 2 == 0) {
                int pos = len / 2;
                result = findPalindrome(numStr, pos - 1, pos);
            } else {
                int pos = len / 2;
                result = findPalindrome(numStr, pos , pos);
            }
        }
        return result;
    }

    public boolean findPalindrome(String numStr, int start, int end) {
        boolean result = Boolean.TRUE;
        while (start >= 0 || end < numStr.length()) {
            if (numStr.charAt(start) != numStr.charAt(end)) {
                result = Boolean.FALSE;
                break;
            }
            start --;
            end ++;
        }
        return result;
    }
}
