package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/">面试题 02.02. 返回倒数第 k 个节点</a>
 */
public class KthNodeFromEndOfListLcci {
    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode14 = new ListNode(4);
        ListNode listNode15 = new ListNode(5);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;
        int i = kthToLast(listNode11, 2);
        System.out.println(i);
    }

    public static int kthToLast(ListNode head, int k) {
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            if (i < k){
                p1 = p1.next;
            }
        }
        ListNode p2 = head;
        while (p1 != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        return p2.val;
    }
}
