package xh.leetcode.双指针.LC_234_回文链表;

import xh.leetcode.双指针.ListNode;

/**
 * @Author XH
 * @Description TODO 快慢指针+翻转链表 easy
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 *
 * TODO 快慢指针：翻转链表时，链表长度为奇数，slow指向中间节点；链表长度为偶数时，slow指向后半部分的第一个节点
 *
 * @Date 2020/6/29 1:24
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        /** 特殊情况处理 */
        if(head == null || head.next == null) {
            return true;
        }

        /** 快慢指针定位到链表中点，slow指向链表中点 */
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /** 后半段链表翻转 */
        ListNode newHead = reverseListNode(slow);
        /** 前半段链表和后半段链表循环比较 */
        while(head != null && newHead != null) {
            if(head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }

        return true;
    }

    /** 翻转链表 */
    public ListNode reverseListNode(ListNode p) {
        if(p == null) {
            return null;
        }

        /** 翻转链表，循环结束时，p = null */
        ListNode pre = null;
        while (p != null) {
            ListNode tmp = p.next;
            p.next = pre;
            pre = p;
            p = tmp;
        }

        return pre;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(1);
        a.next = b;
        b.next = c;
        c.next = d;

        System.out.println(solution.isPalindrome(a));
    }

}
