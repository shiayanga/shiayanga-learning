package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/">142. 环形链表 II</a>
 */
public class LinkedListCycleII {
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
        ListNode listNode = detectCycle(listNode11);
        while (listNode != null) {
            int val = listNode.val;
            System.out.print(val + "\t");
            listNode = listNode.next;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        /*
        当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置
https://labuladong.online/algo/essential-technique/linked-list-skills-summary/#%E5%88%A4%E6%96%AD%E9%93%BE%E8%A1%A8%E6%98%AF%E5%90%A6%E5%8C%85%E5%90%AB%E7%8E%AF
         */
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null){
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }
}
