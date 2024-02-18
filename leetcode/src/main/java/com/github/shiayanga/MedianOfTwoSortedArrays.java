package com.github.shiayanga;

/**
 * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">寻找两个正序数组的中位数</a>
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 合并两个有序数组，得到一个大的有序数组。大的有序数组的中间位置的元素，即为中位数
        int tail = nums1.length + nums2.length - 1;
        int[] col = new int[tail + 1];
        int p1 = nums1.length - 1;
        int p2 = nums2.length - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            col[tail--] = cur;
        }

        if (col.length % 2 == 0) {
            // 当 m+nm+nm+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2(m+n)/2(m+n)/2 个元素和第 (m+n)/2+1(m+n)/2+1(m+n)/2+1 个元素的平均值
            return (col[col.length / 2] + col[col.length / 2 - 1]) / 2d;
        } else {
            //当 m+nm+nm+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2(m+n)/2(m+n)/2 个元素
            return col[col.length / 2];
        }
    }
}

