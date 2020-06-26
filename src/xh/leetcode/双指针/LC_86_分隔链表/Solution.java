package xh.leetcode.双指针.LC_86_分隔链表;

import xh.leetcode.双指针.ListNode;

/**
 * @Author XH
 * @Description TODO 双指针 medium 快排思维
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 *
 * TODO 使用哑指针，分别将比x小、大的节点分别存储到新的链表small、big，再将small、big连起来即可，注意尾指针
 * new 两个数组，一个数组保存小于分区点的数，另一个数组保存大于等于分区点的数，然后把两个数组结合在一起就可以了。
 * @Date 2020/6/26 16:28
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        /** small哑指针 */
        ListNode small_head = new ListNode(0);
        ListNode small = small_head;
        /** big哑指针 */
        ListNode big_head = new ListNode(0);
        ListNode big = big_head;
        while(head != null) {
            if(head.val < x) {
                /** 尾插法 ：插入small链表 */
                small.next = head;
                small = small.next;
            }else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }

        /** TODO 这一步很关键，避免形成环
         * 比如【1，3，5，6，2】这个链表，假设 x=3，则可得到两个链表分别是【1，2】和【3，5，6】，但此时右链表的尾部，即6的尾部仍指向2，如果不把右链表的尾部置为None，最后就会得到 1->2->3->5->6->2 这个链表，在节点2处成环。因此必须将右链表尾部手动置为None，实现断链，最后得到 1->2->3->5->6->None。
         * */
        big.next = null;
        /** 拼接small和big链表 */
        small.next = big_head.next;

        return small_head.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(4);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(2);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(2);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        int x = 3;

        System.out.println(solution.partition(one, x).toString());
    }

}
