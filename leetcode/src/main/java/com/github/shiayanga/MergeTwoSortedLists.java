package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">合并两个有序链表</a>
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(4);
        listNode11.next = listNode12;
        listNode12.next = listNode13;

        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);
        listNode21.next = listNode22;
        listNode22.next = listNode23;

        ListNode listNode = mergeTwoLists(listNode11, listNode21);
        while (listNode.next != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode(-1), p = listNode;
        // ListNode p1 = list1, p2 = list2;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                p.next = list2;
                list2 = list2.next;
            }else {
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }
        if (list1 != null){
            p.next = list1;
        }
        if (list2 != null){
            p.next = list2;
        }
        return listNode.next;
    }
}
