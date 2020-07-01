package xh.leetcode.回溯法.LC_77_组合;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法 medium
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * @Date 2020/7/1 1:57
 */
public class Solution {
    /** 保存所有结果集 */
    List<List<Integer>> res;
    /** 保存单个结果 */
    List<Integer> data;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        combine_helper(n, k, 1);
        return res;
    }

    /** 回溯法 */
    public void combine_helper(int n, int k, int start) {
        if(k < 0) {
            return;
        } else if(k == 0) {
            /** 结束条件 */
            res.add(new ArrayList<>(data));
        } else {
            for(int i = start; i <= n; i++) {
                /** 选中i */
                data.add(i);
                /** 递归 */
                combine_helper(n, k - 1, i + 1);
                /** 撤销选中的i */
                data.remove(data.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        int k = 2;
        System.out.println(solution.combine(n, k));
    }

}
