package com.ryan.bit.study.leetcode;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/9/27
 * @since 1.0.0
 */
public class MediumOf2SortedArray {

    public static void main(String[] args) {

        int[] a = {1,2};
        int[] b = {3,4};
        MediumOf2SortedArray mediumOf2SortedArray = new MediumOf2SortedArray();
        double result = mediumOf2SortedArray.findMedianSortedArrays(a, b);
        double ss = mediumOf2SortedArray.findKthSmallestElem(a, b);
        System.out.println(ss);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0.0;

        int a_length = nums1.length;
        int b_length = nums2.length;
        int[] arrays = new int[a_length + b_length];

        int indexOfa = 0;
        int indexOfb = 0;
        for (int i = 0; i < a_length + b_length; i++) {
            int num = 0;
            if (indexOfa >= a_length) {
                num = nums2[indexOfb];
                indexOfb ++;
            } else if (indexOfb >= b_length) {
                num = nums1[indexOfa];
                indexOfa ++;
            } else if (nums1[indexOfa] <= nums2[indexOfb]) {
                num = nums1[indexOfa];
                indexOfa ++;
            } else {
                num = nums2[indexOfb];
                indexOfb ++;
            }
            arrays[i] = num;
        }
        if (arrays.length % 2 == 0) {
            result = ( arrays[arrays.length/2-1] +  arrays[arrays.length/2] ) * 1.0 / 2;
        } else {
            result = arrays[arrays.length/2]*1.0;
        }

        return result;
    }

    public double findKthSmallestElem(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 0) { // 偶数个元素
            return (findKthSmallest(nums1, 0, nums2, 0, length/2) + findKthSmallest(nums1, 0, nums2, 0, length/2 + 1)) * 1.0 / 2;
        } else {
            return findKthSmallest(nums1, 0, nums2, 0, length/2 + 1);
        }
    }

    int findKthSmallest(int[] nums1, int startPos1, int[] nums2, int startPos2, int k) {
        if (startPos1 == nums1.length) {
            return nums2[startPos2 + k - 1];
        } else if (startPos2 == nums2.length) {
            return nums1[startPos1 + k - 1];
        } else if (k / 2 == 0) {
            return nums1[startPos1] <= nums2[startPos2]? nums1[startPos1]: nums2[startPos2];
        } else {
            int pos1 = (k/2 - 1 + startPos1 >= nums1.length)? nums1.length - 1: k/2 - 1 + startPos1;
            int pos2 = (k/2 - 1 + startPos2 >= nums2.length)? nums2.length - 1: k/2 - 1 + startPos2;

            if (nums1[pos1] < nums2[pos2]) {
                return findKthSmallest(nums1, pos1 + 1, nums2, startPos2, k - (pos1 - startPos1 + 1));
            } else {
                return findKthSmallest(nums1, startPos1, nums2, pos2 + 1, k - (pos2 - startPos2 + 1));
            }
        }
    }
}
