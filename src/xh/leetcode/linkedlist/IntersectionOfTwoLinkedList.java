package xh.leetcode.linkedlist;

/**
 * @Author XH
 * @Description TODO 编写一个程序，找到两个单链表相交的起始节点。
 * TODO 分情况：1、均有环；2、均无环；3、一个有环，一个无环，永远不可能相交
 * TODO 细化问题一：判断一个链表是否有环；问题二：均无环链表怎么找相交的起始节点；问题三、均有环链表怎么找相交的起始节点。
 * TODO 注意：题目的相交指的是，地址相交，而不是元素内容相等
 *
 * 示例1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * @Date 2019/3/13 13:08
 */
public class IntersectionOfTwoLinkedList {

    /**
     * TODO LeetCode 142【快慢指针】问题一、判断一个单链表是否有环，并返回环的入口节点
     * @param p
     * @return
     */
    public ListNode getLoopNode(ListNode p){
        if(p == null || p.next == null || p.next.next == null){
            return null;
        }

        ListNode slow = p.next;
        ListNode fast = p.next.next;
        //定位到环中某个位置
        while(slow != fast){
            //注意这里的判断条件只和fast有关：
            //如果快指针把整个赛道都跑完了，跑到了null，那说明这个赛道是有尽头的。
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        slow = p;//回退到链表头结点
        //快慢指针同时单步移动，相遇处即为环入口
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //TODO LeetCode 141 判断一个单链表是否有环
    public boolean isLoopNode(ListNode p){
        if(p == null || p.next == null || p.next.next == null){
            return false;
        }

        ListNode slow = p.next;
        ListNode fast = p.next.next;
        while( fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     * TODO LeetCode 160 判断两个均无环链表是否相交，相交则返回第一个相交节点
     * @param head1
     * @param head2
     * @return
     */
    public ListNode NoLoop(ListNode head1,ListNode head2){
        return getIntersectionNode(head1,head2);
    }

    //分别同步遍历两个链表list1和list2，当某一个链表到链尾时，指向另一个链表；则此时开始同步遍历两个链表长度的差值；
    //当另一个链表也指向链尾时，继续指向前一个链表：此时相当于长的链表完成一轮自身遍历；短的链表完成一轮自身遍历，且在长的链表中遍历完链表长度之差；此时正好可以开始同步遍历比较（两个链表中剩余的节点数相同）
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;
        while(p1 != p2){
            if(p1 == null){
                p1 = headB;
            }else{
                p1 = p1.next;
            }

            if(p2 == null){
                p2 = headA;
            }else{
                p2 = p2.next;
            }
        }
        return p1;
    }

    /**
     * TODO 判断两个均有环链表是否相交，相交则返回第一个相交节点
     * 1、获取两个有环链表的第一个入环节点，分别为loop1、loop2
     * 2、若loop1==loop2，第一个相交节点肯定在头结点到loop1（loop2）节点这一段上，此种情况与两个均无环链表是否相交类似：即将loop1（loop2）作为链表的终点，而不是null
     * 3、若loop1 ！= loop2，则从loop1出发，在再次回到loop1之前没有碰到loop2，则说明两者不相交，否则相交。
     * @param head1
     * @param head2
     * @return
     */
    public ListNode bothLoop(ListNode head1,ListNode head2){
        //获取有环链表中环的入口节点
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);

        if(loop1 == loop2){
            ListNode p1 = head1;
            ListNode p2 = head2;
            while(p1 != p2){
                if(p1 == null){
                    p1 = head2;
                }else{
                    p1 = p1.next;
                }
                if(p2 == null){
                    p2 = head1;
                }else{
                    p2 = p2.next;
                }
            }
            return p1;
        }else{
            ListNode cur = loop1.next;
            while(cur != loop1){
                if(cur == loop2){
                    return loop2;
                }
                cur = cur.next;
            }
            return  null;
        }

    }

    public static void main(String[] args) {
        ListNode one1 = new ListNode(4);
        ListNode two1 = new ListNode(1);
        ListNode three1 = new ListNode(8);
        ListNode four1 = new ListNode(4);
        ListNode five1 = new ListNode(5);
        one1.next = two1;
        two1.next = three1;
        three1.next = four1;
        four1.next = five1;

        //注意：这里比的是节点指向的地址是否相等，而不是节点值是否相等
        ListNode one2 = new ListNode(5);
        ListNode two2 = new ListNode(0);
        ListNode three2 = new ListNode(1);

        one2.next = two2;
        two2.next = three2;
        three2.next = three1;

        IntersectionOfTwoLinkedList solution = new IntersectionOfTwoLinkedList();
        ListNode result = solution.getIntersectionNode(one1,one2);
        System.out.println(result);
    }

}
