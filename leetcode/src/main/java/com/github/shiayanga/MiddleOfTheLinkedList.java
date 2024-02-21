package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/description/">876. 链表的中间结点</a>
 */
public class MiddleOfTheLinkedList {
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
        ListNode listNode = middleNode(listNode11);

        while (listNode != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
    }

    public static ListNode middleNode(ListNode head) {
        // 初始化快慢指针，指向head
        ListNode fast = head,slow = head;
        // 快指针走到结点尾部时停止
        while (fast != null && fast.next != null){
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }
}
