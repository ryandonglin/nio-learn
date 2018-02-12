package com.ryan.bit.study.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/9/28
 * @since 1.0.0
 */
public class LengthOfStrnigWithoutRepeatChar {

    public static void main(String[] args) {

        String s = "abcadef";
        LengthOfStrnigWithoutRepeatChar lengthOfStrnigWithoutRepeatChar = new LengthOfStrnigWithoutRepeatChar();
        System.out.println(lengthOfStrnigWithoutRepeatChar.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        int size = 0;
        int maxSize = 0;
        Map<Character, Integer> traceMap = new HashMap<Character, Integer>();
        int position = 0;
        int head = 0;
        for (int i = 0; i < s.length(); i++) {
            if (traceMap.containsKey(s.charAt(i))) {
                size = position - head;
                if (maxSize < size) {
                    maxSize = size;
                }
                head = Math.max(head, traceMap.get(s.charAt(i)) + 1);
            }
            traceMap.put(s.charAt(i), position);
            position ++;
        }
        maxSize = Math.max(position - head, maxSize);
        return maxSize;
    }
}
