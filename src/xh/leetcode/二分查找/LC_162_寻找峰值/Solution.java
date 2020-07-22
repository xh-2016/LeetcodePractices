package xh.leetcode.二分查找.LC_162_寻找峰值;

/**
 * @Author XH
 * @Description TODO xh.leetcode.二分查找 medium
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * TODO 模板1：while(left<=right) 循环体内有3个分支,在循环体内返回目标索引
 *
 * TODO 条件 nums[-1] = nums[n] = -∞ 很关键 模板2：while(left<right) 循环体内有2个分支,在循环体外返回目标索引，在循环体内缩减搜索区间
 * 1、nums[mid] < nums[mid + 1]，nums[n] = -∞：mid之后，先升后降，至少有一个峰值在nums[mid+1,n]中间，即舍弃左半边===》 start = mid + 1;
 * 2、nums[mid] >= nums[mid + 1],nums[-1] = -∞：mid之前，先升后降，至少有一个峰值在nums[start,mid]中间，即舍弃右半边===》end = mid;
 * 3、 return start
 *
 * @Date 2020/6/16 0:59
 */
public class Solution {
    public int findPeakElement2(int[] nums) {
        int start = 0;
        int len = nums.length;
        int end = len - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            }else {
                end = mid;
            }
        }

        return start;
    }

    /** 模板1：while(left<=right) 循环体内有3个分支,在循环体内返回目标索引 */
    public int findPeakElement1(int[] nums) {
        int start = 0;
        int len = nums.length;
        int end = len - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
               return mid;
            }else if(nums[mid] < nums[mid + 1]){
                start = mid + 1;
            }else {
                /** 分支3：nums[mid] < nums[mid - 1] ,峰值在左边，舍弃右边 */
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(solution.findPeakElement1(nums));
    }

}
