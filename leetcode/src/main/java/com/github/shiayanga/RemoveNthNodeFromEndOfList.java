package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list">19. 删除链表的倒数第 N 个结点</a>
 */
public class RemoveNthNodeFromEndOfList {
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

        ListNode listNode = removeNthFromEnd(listNode11, 2);
        while (listNode != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 找到第K个节点
        ListNode p1 = dummy;
        for (int i = 0; i < n + 1; i++) {
            p1 = p1.next;
        }
        ListNode kthNode = dummy;
        while (p1 != null) {
            p1 = p1.next;
            kthNode = kthNode.next;
        }
        // 结束
        kthNode.next = kthNode.next.next;
        return dummy.next;
    }
}
