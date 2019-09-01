package xh.leetcode.linkedlist;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/24 18:57
 */
public class RemoveNthFromEnd {

    /**
     * TODO LeetCode 19 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 【双指针+dummyHead】
     * first指针先在链表中遍历n个节点，second指针再从头节点开始和first指针同步移动，直到first到达链尾；此时second指向的节点即为倒数第n个节点===》以后倒数第n+1个节点覆盖倒数第n个来删除导数第n个节点
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的。
     * @param head
     * @param n
     * @return
     */
    //【双指针+dummyHead】
    // dummyHead.next指向head，first指针先在dummyHead中遍历n个节点，second指针再从dummyHead头节点开始和first指针同步移动，直到first到达链尾；此时second指向的节点即为倒数第n个节点===》以后倒数第n+1个节点覆盖倒数第n个来删除导数第n个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n == 0){
            return null;
        }

        //哑节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead;
        ListNode second = dummyHead;
        while(first.next != null){
            if(n > 0){
                n--;
            }else{
                second = second.next;
            }
            first = first.next;
        }

        //while结束后，second指向待删除节点的前一个节点处
        second.next = second.next.next;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd solution = new RemoveNthFromEnd();
        ListNode one1 = new ListNode(1);
        ListNode two1 = new ListNode(2);
        ListNode three1 = new ListNode(3);
        ListNode four1 = new ListNode(4);
        ListNode five1 = new ListNode(5);
        one1.next = two1;
        two1.next = three1;
        three1.next = four1;
        four1.next = five1;
        int n = 2;
        ListNode res = solution.removeNthFromEnd(one1,n);
        System.out.println(res);
    }

}
