package xh.leetcode.双指针.LC_206_反转链表;

import xh.leetcode.双指针.ListNode;

/**
 * @Author XH
 * @Description TODO easy
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * @Date 2020/6/29 1:52
 */
public class Solution {
    /** 迭代 */
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return null;
        }

        ListNode tail = null;
        /** 循环结束时，head == null */
        while(head != null) {
            ListNode tmp = head.next;
            head.next = tail;
            tail = head;
            head = tmp;
        }

        return tail;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        System.out.println(solution.reverseList(a).toString());
    }

}
