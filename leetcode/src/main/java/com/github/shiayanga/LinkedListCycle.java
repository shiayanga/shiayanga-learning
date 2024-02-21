package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle/description/">141. 环形链表</a>
 */
public class LinkedListCycle {
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
        listNode15.next = listNode13;
        boolean hasCycle = hasCycle(listNode11);
        System.out.println(hasCycle);
    }

    /**
     * 每当慢指针 slow 前进一步，快指针 fast 就前进两步。
     * <br>
     * 如果 fast 最终遇到空指针，说明链表中没有环；如果 fast 最终和 slow 相遇，那肯定是 fast 超过了 slow 一圈，说明链表中含有环。
     *
     * @param head 链表
     * @return boolean
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
