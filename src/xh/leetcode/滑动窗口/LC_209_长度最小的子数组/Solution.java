package xh.leetcode.滑动窗口.LC_209_长度最小的子数组;

/**
 * @Author XH
 * @Description TODO 滑动窗口+双指针 middle
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 *
 * TODO 找一个范围使得其值满足某个条件，然后就会想到滑动窗口，也就是用双指针的方法：用双指针 left 和 right 表示一个窗口。
 * 1、right 向右移增大窗口，直到窗口内的数字和大于等于了 s。进行第 2 步。
 * 2、记录此时的长度，left 向右移动，开始减少长度，每减少一次，就更新最小长度。直到当前窗口内的数字和小于了 s，回到第 1 步。
 * @Date 2020/6/17 1:52
 */
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int res = len + 1; //等价于 res = Integer.MAX_VALUE
        int sum = 0;
        while(right < len) {
            sum += nums[right];
            while(sum >= s) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }

        return res == len+1 ? 0 : res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(solution.minSubArrayLen(s,nums));
    }

}
