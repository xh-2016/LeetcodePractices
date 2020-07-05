package xh.leetcode.回溯法.LC_47_全排列II;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author XH
 * @Description TODO 回溯法+原地交换 medium 这里需考虑重复数字存在时的剪枝
 * 给定一个可【包含重复数字】的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * @Date 2020/7/1 2:00
 */
public class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        /** 首先排序，方便剪枝 */
        Arrays.sort(nums);
        permuteUnique_helper(nums, 0);
        return res;
    }

    private void permuteUnique_helper(int[] nums, int start) {
        if(start == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            /** 保存当前已经比较交换过哪些数字 */
            Set<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                /** 如果存在了，就不交换，直接跳过，判断下一个数字即可 */
                if(set.contains(nums[i])) {
                    continue;
                }
                /** 交换 */
                set.add(nums[i]);
                swap(nums, i, start);
                /** 递归 */
                permuteUnique_helper(nums, start + 1);
                /** 撤销交换 */
                swap(nums, i, start);
            }
        }
    }

    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,1,2};
        System.out.println(solution.permuteUnique(nums).toString());
    }

}
