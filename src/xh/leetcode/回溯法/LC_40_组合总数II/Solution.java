package xh.leetcode.回溯法.LC_40_组合总数II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法 medium
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * TODO candidates 中的每个数字在每个组合中只能使用一次。【这一条很重要！】
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * TODO 解集不能包含重复的组合。 [集合中可能存在重复的元素，需排除重复解]
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * @Date 2020/7/1 1:56
 */
public class Solution {
    /** 保存结果集 */
    private List<List<Integer>> res;
    /** 保存单个结果 */
    private List<Integer> data;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        /** 升序排序 */
        Arrays.sort(candidates);
        combinationSum2_helper(candidates, target, 0);
        return res;
    }

    /** 回溯法 */
    public void combinationSum2_helper(int[] candidates, int target, int start) {
        /** 凑过头了 */
        if(target < 0) {
            return;
        } else if(target == 0) {
            /** 结束条件 */
            res.add(new ArrayList<>(data));
        } else {
            for(int i = start; i < candidates.length; i++) {
                //todo 排除重复元素，此时已升序排序，只需比较相邻元素是否相等
                if(i - 1 >= start && candidates[i - 1] == candidates[i]) {
                    continue;
                }
                /** 选中candicates[i] */
                data.add(candidates[i]);
                /** 递归：不允许出现重复元素，下一个开始的start为i + 1 */
                combinationSum2_helper(candidates, target - candidates[i], i + 1);
                /** 撤销candicates[i] */
                data.remove(data.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        System.out.println(solution.combinationSum2(candidates, target));
    }

}
