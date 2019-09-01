package xh.leetcode.linkedlist;

/**
 * @Author XH
 * @Description TODO LeetCode2 【两个链表逆序相加】给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @Date 2019/3/18 1:02
 */
public class LinkedListReverseAdd {
    //遍历两个链表，计算sum，并处理好进位carry=sum/10，每次生成一个新node=sum%10
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode curr = node;
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;//是否有进位

        //注意：这里必须是或
        while(p != null || q != null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;//保存进位
            node.next = new ListNode(sum % 10);
            node = node.next;
            if(p != null){
                p = p.next;
            }
            if(q != null){
                q = q.next;
            }
        }
        //判断最后一位是否有进位值
        if(carry > 0 ){
            node.next = new ListNode(carry);
        }
        //返回头结点
        return curr.next;

    }

    public static void main(String[] args) {
        ListNode one1 = new ListNode(2);
        ListNode two1 = new ListNode(4);
        ListNode three1 = new ListNode(3);
        one1.next = two1;
        two1.next = three1;

        ListNode one2 = new ListNode(5);
        ListNode two2 = new ListNode(6);
        ListNode three2 = new ListNode(4);
        one2.next = two2;
        two2.next = three2;

        LinkedListReverseAdd solution = new LinkedListReverseAdd();
        ListNode res = solution.addTwoNumbers(one1,one2);
        System.out.println(res.toString());

    }

}
