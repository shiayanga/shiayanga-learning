package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description">82. 删除排序链表中的重复元素 II</a>
 */
public class RemoveDuplicatesFromSortedListII {
    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(1);
        ListNode listNode13 = new ListNode(2);
        ListNode listNode14 = new ListNode(3);
        ListNode listNode15 = new ListNode(3);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;
        ListNode listNode = deleteDuplicates(listNode11);

        while (listNode != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
    }
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode fast = head.next,slow = head;
        while (fast != null){
            if (fast.val != slow.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}
