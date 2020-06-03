package xh.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author XH
 * @Description TODO 二叉树的先序、中序、后续遍历
 * @Date 2019/3/27 20:25
 */
public class PreInAfterTraversal {

    /**
     * TODO LeetCode 144 给定一个二叉树，返回它的 前序 遍历。
     *  示例:
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if(root == null){
            return res;
        }

        //递归
        preorderTraversalByRec(root,res);

        return res;
    }

    //先序遍历 递归
    public void preorderTraversalByRec(TreeNode root,List<Integer> res) {
        res.add(root.val);
        if(root.left != null){
            preorderTraversalByRec(root.left,res);
        }
        if(root.right != null){
            preorderTraversalByRec(root.right,res);
        }
    }

    //先序遍历 迭代 【借助堆stack】
    public List<Integer> preorderTraversalByIte(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if(root == null){
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode cur = null;
        while(!stack.empty()){
            //出栈 栈顶元素
            cur = stack.pop();
            res.add(cur.val);
            //先将右孩子加入stack，保证先序遍历序列
            if(cur.right != null){
                stack.add(cur.right);
            }
            //再将左孩子加入stack，保证先序遍历序列
            if(cur.left != null){
                stack.add(cur.left);
            }
        }
        return res;
    }

    /**
     * 【P95】中序遍历 非递归 借助栈stack
     * 1、头结点cur入栈，依次把左边界压入栈，cur = cur.left；
     * 2、当cur == null时，出栈栈顶，并加入list；
     * 3、处理cur.right：cur = cur.right,重复1、2步骤
     * 4、当stack为空，且cur==null 时，结束
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();

        if(root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            //结束条件：cur == null 且 stack为空
            while(!stack.isEmpty() || cur != null){
                if(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }else{//stack不为空，cur == null
                    cur = stack.pop();
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }

        return res;
    }

    /**
     * TODO 【P98】非递归 后序遍历 借助两个栈s1、s2
     * 1、头结点入栈s1；
     * 2、s1出栈节点记为cur，将其左右子节点分别入栈s1；
     * 3、【注意】s1中出栈节点均需入栈s2；
     * 4、结束条件：s1为空
     * @param root
     * @return
     */
    public List<Integer> afterTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();

        if(root != null){
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            TreeNode cur = root;
            s1.push(cur);
            //结束条件：s1为空
            while(!s1.isEmpty()){
                //s1出栈
                cur = s1.pop();
                //s2入栈
                s2.push(cur);
                //左右子节点分别入栈s1
                if(cur.left != null){
                    s1.push(cur.left);
                }
                if(cur.right != null){
                    s1.push(cur.right);
                }
            }

            //遍历结束后，s2中节点依次出栈，即为后续遍历序列
            while(!s2.isEmpty()){
                res.add(s2.pop().val);
            }
        }

        return res;
    }

        public static void main(String[] args) {
        PreInAfterTraversal solution = new PreInAfterTraversal();
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(9);
        TreeNode three = new TreeNode(20);
        TreeNode four = new TreeNode(15);
        TreeNode five = new TreeNode(7);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;

        List<Integer> res = new ArrayList<>();
        //res = Solution.preorderTraversal(one);
        res = solution.preorderTraversalByIte(one);
        res = solution.inOrderTraversal(one);
        res = solution.afterTraversal(one);
        System.out.println(res);
    }

}
