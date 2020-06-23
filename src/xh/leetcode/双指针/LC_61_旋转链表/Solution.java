package xh.leetcode.双指针.LC_61_旋转链表;

import xh.leetcode.双指针.ListNode;

/**
 * @Author XH
 * @Description TODO 双指针 medium
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 *
 * TODO 规律:将倒数第（k % list.size）个节点开始的所有节点，插到链首
 * 快慢指针，定位到目标节点，再修改节点链接即可
 *
 * @Date 2020/6/24 0:12
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }
        int len = 0;
        ListNode p = head;
        /** 遍历获取链表长度 */
        while(p != null) {
            len++;
            p = p.next;
        }

        /** 特殊情况处理 */
        int n = k % len;
        if(n == 0) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        /** 暂存头结点 */
        ListNode node = head;
        /** 快慢指针定位倒数第(k % len) - 1个节点
         * TODO 已知链表长度len，想定位到倒数第(k % len) + 1个节点，直接遍历即可 */
        while(n > 0) {
            fast = fast.next;
            n--;
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
//        int step = 0;
//        /** 快慢指针定位倒数第(k % len) - 1个节点 */
//        while(fast != null && fast.next != null) {
//            fast = fast.next;
//            step++;
//            if(step > (k % len)) {
//                slow = slow.next;
//            }
//        }
        /** 旋转链表，将定位到的节点插入链首 */
        ListNode res = slow.next;
        fast.next = node;
        slow.next = null;

        return res;
    }

    /** 小优化：已知链表长度len，想定位到倒数第(k % len) - 1个节点，直接遍历即可 */
    public ListNode rotateRight1(ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }
        int len = 0;
        ListNode p = head;
        /** 遍历获取链表长度并保存尾指针 */
        ListNode tail = null;
        while(p != null) {
            len++;
            p = p.next;
            if(p != null) {
                tail = p;
            }
        }

        /** 特殊情况处理 */
        int n = k % len;
        if(n == 0) {
            return head;
        }

        ListNode slow = head;
        /** 暂存头结点 */
        ListNode node = head;
        /** TODO 已知链表长度len，想定位到倒数第(k % len) + 1个节点，直接遍历即可 */
        for(int i = 1; i < len - n; i++) {
            slow = slow.next;
        }
        /** 旋转链表，将定位到的节点插入链首 */
        ListNode res = slow.next;
        slow.next = null;
        tail.next = node;
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        int k = 2;
        System.out.println(solution.rotateRight1(one, k).toString());
    }

}
