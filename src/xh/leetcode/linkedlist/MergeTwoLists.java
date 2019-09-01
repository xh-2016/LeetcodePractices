package xh.leetcode.linkedlist;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/24 21:03
 */
public class MergeTwoLists {

    /**
     * TODO LeetCode 21 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 【双指针】
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null ){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;
        //
        ListNode dummyHead = new ListNode(0);
        ListNode res = dummyHead;
        while(p1 != null && p2 != null){
            int val1 = p1.val;
            int val2 = p2.val;
            if(val1 >= val2){
                dummyHead.next = p2;
                p2 = p2.next;
            }else{
                dummyHead.next = p1;
                p1 = p1.next;
            }
            dummyHead = dummyHead.next;
        }

        while(p1 != null){
            dummyHead.next = p1;
            dummyHead = dummyHead.next;
            p1 = p1.next;
        }

        while(p2 != null){
            dummyHead.next = p2;
            dummyHead = dummyHead.next;
            p2 = p2.next;
        }

        return res.next;
    }

    public static void main(String[] args) {
        MergeTwoLists solution = new MergeTwoLists();
        ListNode one1 = new ListNode(1);
        ListNode two1 = new ListNode(2);
        ListNode three1 = new ListNode(4);
        one1.next = two1;
        two1.next = three1;

        ListNode one2 = new ListNode(1);
        ListNode two2 = new ListNode(3);
        ListNode three2 = new ListNode(4);
        one2.next = two2;
        two2.next = three2;
        ListNode res = solution.mergeTwoLists(one1,one2);
        System.out.println(res);
    }

}
