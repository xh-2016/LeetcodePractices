package xh.leetcode.回溯法.LC_78_子集;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法 medium
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * @Date 2020/7/1 2:06
 */
public class Solution {
    List<List<Integer>> res;
    List<Integer> data;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        subsets_helper(nums,0);
        return res;
    }

    /** 典型回溯法 */
    public void subsets_helper(int[] nums, int start) {
        res.add(new ArrayList<>(data));
        for (int i = start; i < nums.length; i++) {
            /** 选中元素 */
            data.add(nums[i]);
            /** 递归 */
            subsets_helper(nums, i + 1);
            /** 移除选中的元素 */
            data.remove(data.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        System.out.println(solution.subsets(nums));
    }

}
