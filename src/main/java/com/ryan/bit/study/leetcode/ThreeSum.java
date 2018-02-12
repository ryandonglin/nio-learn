package com.ryan.bit.study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/9/27
 * @since 1.0.0
 */
public class ThreeSum {

    public static void main(String[] args) {

        int[] arrays = {1, -1, -1, 0};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> result = threeSum.threeSum(arrays);
        System.out.println(result);

    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i>0 && nums[i] != nums[i-1])) {
                int toFindSum = 0 - nums[i];
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    if (toFindSum == nums[low] + nums[high]) {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (toFindSum > nums[low] + nums[high]) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }
}
