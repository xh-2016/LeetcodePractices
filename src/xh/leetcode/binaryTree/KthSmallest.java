package xh.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author XH
 * @Description TODO BST 二叉搜索树 ：左孩子 < root < 右孩子
 * @Date 2019/3/27 20:49
 */
public class KthSmallest {

    /**
     * TODO LeetCode 230 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * 【思路】中序遍历（递归/非递归）BST，此时是升序序列，第k个元素即为目标元素
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if(root == null || k <= 0){
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        list = inTraversal(root);
        //inTreversalByCur(root,list);

       // System.out.println(list);
        return list.get(k - 1);
    }

    //借助栈 非递归 中序遍历:依次遍历左孩子入栈，到头后弹出栈顶元素并开始处理右孩子。
    public List<Integer> inTraversal(TreeNode root){
        List<Integer> res = new LinkedList<>();

        if(root == null){
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            //左孩子持续入栈
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {//左孩子全部入栈后  cur == null
                //栈顶元素，即最后一层最左边的叶子节点 出栈
                TreeNode t = stack.pop();
                res.add(t.val);
                //处理右孩子
                cur = t.right;
            }
        }

        return res;
    }

    // 中序遍历 递归
    public void inTreversalByCur(TreeNode root,List<Integer> res){
        if(root == null){
            return;
        }

        inTreversalByCur(root.left,res);
        res.add(root.val);
        inTreversalByCur(root.right,res);
    }

    public static void main(String[] args) {
        KthSmallest solution = new KthSmallest();

        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(1);
        TreeNode three = new TreeNode(4);
        TreeNode four = new TreeNode(2);
        one.left = two;
        one.right = three;
        two.right = four;

        int k = 3;

        int res = 0;
        res = solution.kthSmallest(one,k);
        System.out.println(res);
    }
}
