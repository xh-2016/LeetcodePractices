package xh.leetcode.回溯法.LC_46_全排列;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法 medium
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * @Date 2020/7/1 1:59
 */
public class Solution {
    List<List<Integer>> res;
    List<Integer> data;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        permute_helper(nums);
        return res;
    }

    public void permute_helper(int[] nums) {
        /** 结束条件 */
        if(data.size() == nums.length) {
            res.add(new ArrayList<>(data));
        }else {
            for(int i = 0; i < nums.length; i++) {
                /** 排除不合法的选择，当前元素已经被加入了data，直接跳过 */
                if(data.contains(nums[i])) {
                    continue;
                }
                /** 选中 */
                data.add(nums[i]);
                /** 递归 */
                permute_helper(nums);
                /** 撤销选中 */
                data.remove(data.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> res = solution.permute(nums);
        System.out.println(res.toString());
    }

}
