package xh.leetcode.binaryTree;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/24 21:25
 */
public class IsSubtree {

    /**
     * TODO LeetCode 572 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     * 【常规做法：递归】在s中找t的根节点，找到后开始判断此节点下的树结构是否完全相同
     * TODO【序列化二叉树+KMP】
     * 示例 1:
     * 给定的树 s:
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 t：
     *    4
     *   / \
     *  1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     * 示例 2:
     * 给定的树 s：
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * 给定的树 t：
     *    4
     *   / \
     *  1   2
     * 返回 false。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        boolean res = false;

        if (s != null && t != null){
            if(s.val == t.val){
                res = isSameTree(s,t);
            }
            //递归
            if(!res){
                res = isSubtree(s.left,t) || isSubtree(s.right,t);
            }
        }
        return res;
    }

    //判断两颗二叉树t1和t2是否完全相等
    public boolean isSameTree(TreeNode t1, TreeNode t2){
        //结束条件：同时到达了树t1和树t2的叶子节点
        if(t1 == null && t2 == null){
            return true;
        }
        //当t1包含t2时，即某一个树已经遍历结束了，另一个还没有，返回false
        if(t1 == null || t2 == null){
            return false;
        }
        //若根节点不相等，显然不符合
        if(t1.val != t2.val){
            return false;
        }
        //递归：左右子树均相等才是真的相等
        return isSameTree(t1.left,t2.left) && isSameTree(t1.right,t2.right);
    }

    public static void main(String[] args) {
        IsSubtree solution = new IsSubtree();
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(4);
        TreeNode three = new TreeNode(5);
        TreeNode four = new TreeNode(1);
        TreeNode five = new TreeNode(2);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;

        TreeNode one1 = new TreeNode(4);
        TreeNode two1 = new TreeNode(1);
        TreeNode three1 = new TreeNode(2);
        one1.left = two1;
        one1.right = three1;

        boolean res = false;
        res = solution.isSubtree(one,one1);
        System.out.println(res);
    }

}
