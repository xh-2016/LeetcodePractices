package xh.leetcode.binaryTree;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/26 13:34
 */
public class BalancedTree {

    /**
     * TODO LeetCode 110 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 【递归】结束条件：左子树和右子树高度差的绝对值 > 1；递归判断左右子树是否是平衡树
     * 【递归】判断一颗二叉树的高度（深度）
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * 示例 1:
     * 给定二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     * 示例 2:
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        if(Math.abs(depth(root.left) - depth(root.right)) > 1){
            return false;
        }else{
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    //递归：判断一颗二叉树的深度
    public int depth(TreeNode root){
        if(root == null){
            return 0;
        }

        return Math.max(depth(root.left),depth(root.right)) + 1;
    }

    public static void main(String[] args) {
        BalancedTree solution = new BalancedTree();
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(9);
        TreeNode three = new TreeNode(20);
        TreeNode four = new TreeNode(15);
        TreeNode five = new TreeNode(7);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;

        boolean res = false;
        res = solution.isBalanced(one);
        System.out.println(res);
    }

}
