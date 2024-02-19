package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/partition-list/">分隔链表</a>
 */
public class PartitionList {
    public static void main(String[] args) {
        // 1,4,3,2,5,2
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode14 = new ListNode(2);
        ListNode listNode15 = new ListNode(5);
        ListNode listNode16 = new ListNode(2);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;
        listNode15.next = listNode16;
        ListNode partition = partition2(listNode11, 3);
        while (partition != null) {
            int val = partition.val;
            System.out.print(val + "\t");
            partition = partition.next;
        }
    }

    public static ListNode partition1(ListNode head, int x) {
        ListNode little = new ListNode(-1);
        ListNode big = new ListNode(-1);
        ListNode l = little, b = big;
        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                b.next = p;
                b = b.next;
            }
            if (p.val < x) {
                l.next = p;
                l = l.next;
            }
            p = p.next;
        }
        b.next = null;
        l.next = big.next;
        return little.next;
    }


    public static ListNode partition2(ListNode head, int x) {
        ListNode little = new ListNode(-1);
        ListNode big = new ListNode(-1);
        ListNode l = little, b = big;
        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                b.next = p;
                b = b.next;
            }
            if (p.val < x) {
                l.next = p;
                l = l.next;
            }
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }
        l.next = big.next;
        return little.next;
    }
}
