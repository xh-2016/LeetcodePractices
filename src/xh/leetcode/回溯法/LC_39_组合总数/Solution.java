package xh.leetcode.回溯法.LC_39_组合总数;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法 medium
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * TODO candidates 中的数字可以无限制重复被选取。[这一条很重要！]
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * @Date 2020/7/1 1:54
 */
public class Solution {
    private List<List<Integer>> res;
    private List<Integer> data;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        combinationSum_helper(candidates, target, 0);
        return res;
    }

    /** 回溯法 */
    public void combinationSum_helper(int[] candidates, int target, int start) {
        if (target < 0) {
            return;
        }else if (target == 0) {
            /** 结束条件 */
            res.add(new ArrayList<>(data));
        }else {
            for (int i = start; i < candidates.length; i++) {
                /** 选中candicate[i] */
               data.add(candidates[i]);
                /** 递归：这里可以允许重复值，则下一个start任为i */
                combinationSum_helper(candidates, target - candidates[i], i);
                /** 撤回candicates[i] */
                data.remove(data.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2,3,5};
        int target = 8;
        System.out.println(solution.combinationSum(candidates, target));
    }

}
