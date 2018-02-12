package com.ryan.bit.study.leetcode;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/10/16
 * @since 1.0.0
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String lcs = longestPalindromicSubstring.longestPalindrome2("abb");

        System.out.println(lcs);
    }

    /**
     * 反转字符串之后求最长公共子序列
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        String longestStr = s.substring(0,1);
        for (int i = 0; i < s.length(); i++){
            String lcsStr = getCurrentCenterPalindrome(s ,i, i);
            if (lcsStr.length() > longestStr.length()) {
                longestStr = lcsStr;
            }
            lcsStr = getCurrentCenterPalindrome(s, i,i + 1);
            if (lcsStr.length() > longestStr.length()) {
                longestStr = lcsStr;
            }
        }
        return  longestStr;
    }

    public String longestPalindrome2(String s) {

        String longestStr = "";
        String reverseStr = "";
        for (int i = s.length() -1; i >= 0; i --) {
            reverseStr += s.charAt(i);
        }
        longestStr = longgestCommonSeq(s, reverseStr);
        return  longestStr;
    }

    public String getCurrentCenterPalindrome(String s, int start, int end) {
        String lcsStr = "";
        while (start >= 0 && end <= s.length() -1 && s.charAt(start) == s.charAt(end)) {
            start --;
            end ++;
        }
        lcsStr = s.substring(start + 1, end);
        return lcsStr;
    }

    public String longgestCommonSeq(String str1, String str2) {
        int len = str1.length();

        int[][] lcsArray = new int[len][len];

        // 打表
        for (int i = 0; i < str1.length(); i++) {
            for (int j =0; j < str2.length(); j++) {
                if (str2.charAt(i) == str1.charAt(j)) {
                    lcsArray[i][j] = 1;
                }
            }
        }

        int maxCount = 0;
        String lcs = "";
        for (int i = len - 1; i >= 0; i--) {
            int count = 0;
            String str = "";
            int longgestLen = 0;
            String longgestStr = "";
            for (int j = 0; j < len; j++) {
                if (i+j >= len) {
                    break;
                }
                if (1 == lcsArray[j][j+i]) {
                    count += lcsArray[j][j+i];
                    str += str2.charAt(j);
                } else {
                    if (longgestLen < count) {
                        longgestLen = count;
                        longgestStr = str;
                    }
                    count = 0;
                    str = "";
                }
            }
            if (longgestLen < count) {
                longgestLen = count;
                longgestStr = str;
            }
            //System.out.println(count);
            if (longgestLen > maxCount) {
                maxCount = longgestLen;
                lcs = longgestStr;
            }
        }
        return lcs;
    }
}
