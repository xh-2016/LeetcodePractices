package xh.leetcode.回溯法.LC_90_子集II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法+去重 medium
 * 给定一个可能【包含重复元素】的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * @Date 2020/7/1 2:07
 */
public class Solution {
    List<List<Integer>> res;
    List<Integer> data;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        /** 排序，对重复元素进行剪枝 */
        Arrays.sort(nums);
        subsetsWithDup_helper(nums, 0);
        return res;
    }

    /** 典型回溯法 */
    public void subsetsWithDup_helper(int[] nums, int start) {
        res.add(new ArrayList<>(data));
        for (int i = start; i < nums.length; i++) {
            /** 当前元素和前一个元素相等，直接跳过 */
            if(i - 1 >= start && nums[i] == nums[i-1]) {
                continue;
            }
            /** 选中元素 */
            data.add(nums[i]);
            /** 递归 */
            subsetsWithDup_helper(nums, i+1);
            /** 撤销选中元素 */
            data.remove(data.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,2};
        System.out.println(solution.subsetsWithDup(nums));
    }

}
