package xh.leetcode.linkedlist;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/24 20:34
 */
public class ReverseList {

    /**
     * TODO LeetCode 206 反转一个单链表。
     * 【迭代版：头插法】保存当前节点cur、前一个节点pre、后一个节点next
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;
        ListNode res = null;
        while(cur != null){
            ListNode next = cur.next;
            if(next == null){
                res = cur;
            }
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return res;
    }

    /**
     * 【递归方法】
     * 从后向前反转。假设链表后面的节点已反转完毕，如何反转当前节点及之前的部分？
     * n1 -> n2 -> n3 -> ...-> nk -> n(k+1) <- nm
     * 当前节点为 nk, nk.next = n(k+1)
     * 令 nk.next.next = nk, 完成 n(k+1) 的反转
     * 最后另 n1.next = null
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        //递归
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        ReverseList reverse = new ReverseList();
        ListNode one1 = new ListNode(1);
        ListNode two1 = new ListNode(2);
        ListNode three1 = new ListNode(3);
        ListNode four1 = new ListNode(4);
        ListNode five1 = new ListNode(5);
        one1.next = two1;
        two1.next = three1;
        three1.next = four1;
        four1.next = five1;

        ListNode res = reverse.reverseList(one1);
        System.out.println(res);
    }

}
