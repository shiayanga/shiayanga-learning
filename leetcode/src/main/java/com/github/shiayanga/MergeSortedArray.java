package com.github.shiayanga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * <a href="https://leetcode.cn/problems/merge-sorted-array/">88. 合并两个有序数组</a>
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        solution1(nums1, 3, nums2, 3);
        for (int i : nums1) {
            System.out.print(i + "\t");
        }

        int[] nums3 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums4 = new int[]{2, 5, 6};
        solution2(nums3, 3, nums4, 3);
        for (int i : nums3) {
            System.out.print(i + "\t");
        }

        int[] nums5 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums6 = new int[]{2, 5, 6};
        solution3(nums5, 3, nums6, 3);
        for (int i : nums5) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 直接合并后排序
     */
    public static void solution1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 双指针法
     */
    public static void solution2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0;
        int p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = sorted[i];
        }
    }

    /**
     * 逆向双指针法
     */
    public static void solution3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;
        int cur;

        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums2[p2] > nums1[p1]) {
                cur = nums2[p2--];
            } else {
                cur = nums1[p1--];
            }
            nums1[tail--] = cur;
        }
    }
}
