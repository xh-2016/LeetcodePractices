package xh.leetcode.双指针.LC_16_最接近的三数之和;

import java.util.Arrays;

/**
 * @Author XH
 * @Description TODO 数组排除+双指针 medium
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 *
 * @Date 2020/6/23 0:25
 */
public class Solution {
    /** 时间复杂度 O(N*N) */
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int res = 0;
        /** 暂存 三数之和sum和目标值target差值的绝对值 */
        int sub = Integer.MAX_VALUE;
        /** 升序排序 */
        Arrays.sort(nums);
        for(int i = 0; i < len; i++) {
            /** 双指针 */
            int low = i + 1;
            int high = len - 1;
            /** 寻找3 sum，则low ！= high ,这里时间复杂度 O(N)*/
            while(low < high) {
                /** sub缩小时，更新res */
                if(Math.abs(nums[low] + nums[high] + nums[i] - target) < sub) {
                    res = nums[low] + nums[high] + nums[i];
                    sub = Math.abs(res - target);
                }
                if (nums[low] + nums[high] + nums[i] > target) {
                    high--;
                }else if(nums[low] + nums[high] + nums[i] < target) {
                    low++;
                } else {
                    /** 3sum == target时，直接返回 */
                    return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,2,1,-3};
        int target = 1;
        System.out.println(solution.threeSumClosest(nums, target));
    }

}
