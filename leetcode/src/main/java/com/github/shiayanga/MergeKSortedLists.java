package com.github.shiayanga;

import com.github.shiayanga.component.ListNode;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">合并 K 个升序链表</a>
 */
public class MergeKSortedLists {
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

        ListNode listNode31 = new ListNode(1);
        listNode31.next = new ListNode(6);

        ListNode[] node = new ListNode[]{listNode11, listNode21, listNode31};
        ListNode merged = mergeKLists(node);
        while (merged != null) {
            System.out.print(merged.val + "\t");
            merged = merged.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，小堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((a, b) -> (a.val - b.val));
        // 将数组中的所有头结点加入小堆
        /*
                        1
                       / \
                      1   1
         */
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.offer(list);
            }
        }

        // 获取最小节点，接到结果链表中
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            p.next = node;
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
            // 指针前进
            p = p.next;
        }

        // 获取一次最小节点后，小堆变为
        /*
         *    1          1           1           2           3           4
         *   / \   ->   / \    ->   / \    ->   / \    ->   / \    ->   / \
         *  1   1      1   2       2   3       3   4       4   4       4   6
         *
         */
        return dummy.next;
    }
}
