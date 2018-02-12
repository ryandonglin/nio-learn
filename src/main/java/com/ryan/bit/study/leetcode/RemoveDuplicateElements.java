package com.ryan.bit.study.leetcode;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/10/16
 * @since 1.0.0
 */
public class RemoveDuplicateElements {

    public static void main(String[] args) {
        RemoveDuplicateElements removeDuplicateElements = new RemoveDuplicateElements();

        int[] arrays = {1,1,2};
        System.out.println(removeDuplicateElements.removeDuplicates(arrays));
        for (int a: arrays) {
            System.out.println(a);
        }
    }

    public int removeDuplicates(int[] nums) {
        int count = 1, j = 0;
        if (nums.length >= 2) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i-1] == nums[i]) {
                    continue;
                }
                count++;
                nums[j++] = nums[i];
            }
        }
        if (0 == nums.length){
            count = 0;
        }
        return count;
    }
}
