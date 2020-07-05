package xh.leetcode.回溯法.LC_46_全排列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author XH
 * @Description TODO 回溯法 + 交换 medium
 * https://leetcode.wang/leetCode-46-Permutations.html
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
public class Solution1 {
    List<List<Integer>> res;
    List<Integer> data;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        data = new ArrayList<>();
        permute_helper(nums, 0);
        return res;
    }

    /** start 代表start之前的数字已经进入了排列 */
    public void permute_helper(int[] nums, int start) {
        /** 结束条件 */
        if(start == nums.length) {
            /** boxed(): 装箱函数，基本数据类型 转换成 包装类型 */
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }else {
            for(int i = start; i < nums.length; i++) {
                /** 交换位置 */
                swap(nums, i, start);
                /** 递归,注意这里是 start+1，即start之前的元素保持不变
                 * 排列，改变了元素间相对位置，start可以往前回溯，则递归时，start = start + 1
                 * */
                permute_helper(nums, start + 1);
                /** 撤销交换 */
                swap(nums, i, start);
            }
        }
    }

    /** 数组中交换元素 */
    public void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> res = solution.permute(nums);
        System.out.println(res.toString());
    }

}
