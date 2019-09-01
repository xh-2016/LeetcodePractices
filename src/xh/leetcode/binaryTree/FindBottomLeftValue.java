package xh.leetcode.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/26 16:04
 */
public class FindBottomLeftValue {

    /**
     * TODO LeetCode 513 给定一个二叉树，在树的最后一行找到最左边的值。
     * 【reverse广度优先搜索BFS，队列辅助存储，基于递归】bfs中都是先遍历左子树，再遍历右子树；本题改变下左右子树遍历的顺序，即先遍历右子树，再遍历左子树；每层中最后一个被遍历的元素必然是最左边的元素，故二叉树中最后一行的最左边的值即为遍历完一颗二叉树后，最后插入队列的元素。
     * 示例 1:
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出:
     * 1
     * 示例 2:
     * 输入:
     *         1
     *        / \
     *       2   3
     *      /   / \
     *     4   5   6
     *        /
     *       7
     * 输出:
     * 7
     * 注意: 您可以假设树（即给定的根节点）不为 NULL。
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        //加入头结点
        ((LinkedList<TreeNode>) queue).add(root);
        //记录当前操作的节点
        TreeNode cur = null;
        //对称 bfs
        while(!queue.isEmpty()){
            //从队列头部移除
            cur = queue.poll();
            if(cur.right != null){
                //加入到队列尾部
                ((LinkedList<TreeNode>) queue).add(cur.right);
            }
            if(cur.left != null){
                ((LinkedList<TreeNode>) queue).add(cur.left);
            }
        }
        //while循环结束后，cur节点中记录着从queue中移除的最后一个节点，即目标节点
       return cur.val;
    }

    public static void main(String[] args) {
        FindBottomLeftValue solution = new FindBottomLeftValue();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;

        int res = 0;
        res = solution.findBottomLeftValue(one);
        System.out.println(res);
    }

}
