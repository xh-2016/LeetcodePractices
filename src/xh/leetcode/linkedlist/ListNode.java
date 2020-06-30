package xh.leetcode.linkedlist;

/**
 * @Author XH
 * @Description TODO 链表节点的数据结构
 * @Date 2019/3/13 13:16
 */
public class ListNode {
    int val;
    ListNode next;
    public ListNode(int x){
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

}
