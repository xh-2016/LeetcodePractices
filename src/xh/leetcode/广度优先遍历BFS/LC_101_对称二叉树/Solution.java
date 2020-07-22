package xh.leetcode.广度优先遍历BFS.LC_101_对称二叉树;

import xh.leetcode.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author XH
 * @Description TODO dfs(递归) + bfs（迭代） easy
 * https://leetcode-cn.com/problems/symmetric-tree/solution/di-gui-die-dai-bi-xu-miao-dong-by-sweetiee/
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * @Date 2020/7/5 18:43
 */
public class Solution {
    /** 递归 dfs */
    public boolean isSymmetric(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return  true;
        }

        return isSame(root.left, root.right);
    }

    /** 判断两颗树是否完全相同 */
    public boolean isSame(TreeNode node1, TreeNode node2) {
        /** 结束条件 */
        if(node1 == null && node2 == null) {
            return true;
        }
        /** 左右节点不相等 */
        if(node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }

        /** 比较node1的左子树和node2的右子树是否对称，node1的右子树和node2的左子树是否对称 */
        return isSame(node1.left, node2.right) && isSame(node1.right, node2.left);
    }

    /** 迭代 bfs */
    public boolean isSymmetric_iteration(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return  true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty()) {
            /** 每次出队两个节点，进行比较 */
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            /** 这里一定要 continue，不是 return true
             * 因为可能只是到达了某个叶子结点，接下来还要再继续遍历其他分支
             */
            if(node1 == null && node2 == null) {
                continue;
            }
            if(node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            /** 将node1的左节点和node2的右节点一起入队 ，以便后续一起出队进行比较 */
            queue.offer(node1.left);
            queue.offer(node2.right);
            /** 将node1的右节点和node2的左节点一起入队 ，以便后续一起出队进行比较 */
            queue.offer(node1.right);
            queue.offer(node2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(2);
        TreeNode e = new TreeNode(2);
//        TreeNode f = new TreeNode(4);
//        TreeNode g = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = d;
        c.left = e;
        System.out.println(solution.isSymmetric_iteration(a));
    }

}
