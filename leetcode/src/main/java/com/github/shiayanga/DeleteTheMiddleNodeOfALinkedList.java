package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/">2095. 删除链表的中间节点</a>
 */
public class DeleteTheMiddleNodeOfALinkedList {
    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode14 = new ListNode(4);
        ListNode listNode15 = new ListNode(5);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = listNode14;
        // listNode14.next = listNode15;
        ListNode listNode = deleteMiddle(listNode11);
        while (listNode != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
    }
    public static ListNode deleteMiddle(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 寻找中间的结点
        ListNode fast = dummy.next,slow = dummy;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // 中间结点就是slow
        slow.next = slow.next.next;
        return dummy.next;
    }
}
