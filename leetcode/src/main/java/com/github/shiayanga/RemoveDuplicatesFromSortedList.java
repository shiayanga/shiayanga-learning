package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/">83. 删除排序链表中的重复元素</a>
 */
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(1);
        ListNode listNode13 = new ListNode(1);
        ListNode listNode14 = new ListNode(3);
        ListNode listNode15 = new ListNode(3);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        // listNode13.next = listNode14;
        // listNode14.next = listNode15;
        ListNode listNode = deleteDuplicates(listNode11);

        while (listNode != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
        System.out.println();
        ListNode listNode2 = deleteDuplicatesII(listNode11);

        while (listNode2 != null) {
            int val = listNode2.val;
            System.out.print(val + "\t");
            listNode2 = listNode2.next;
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

    public static ListNode deleteDuplicatesII(ListNode head){
        ListNode cur = head;
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }

        }
        return head;
    }
}
