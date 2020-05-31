package xh.leetcode.动态规划.LC_53_最大子序和;

/**
 * @Author XH
 * @Description TODO easy
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 *
 * TODO 动态规划，一阶动态方程：
 * 1、定义f(i)：以nums[i]元素结尾的连续子数组的最大和
 * 2、构造状态转移方程：
 * f(i+1) = Math.max(f(i) + nums[i+1], nums[i+1])
 * 3、转换题目所求结果：Math.max(f())
 * @Date 2020/5/31 17:00
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;

        /** 记录以nums[i]结尾的连续子数组的最大和 */
        int tmp = 0;
        for(int i = 0; i< nums.length; i++) {
            tmp = Math.max(tmp + nums[i], nums[i]);
            res = Math.max(res, tmp);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {};  // return -2147483648
        int[] nums2 = {-1}; //return -1
        int[] nums3 = {-2,1,-3,4,-1,2,1,-5,4}; // return 6

        Solution solution = new Solution();
        System.out.println(solution.maxSubArray(nums3));
    }
}
