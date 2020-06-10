package 二分查找.LC_154_寻找旋转排序数组中的最小值II;

/**
 * @Author XH
 * @Description TODO 二分查找 变形 hard
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 * TODO 存在重复数字，则需单独考虑 nums[mid] == nums[right]
 * 1、nums[mid] < nums[right], 最小值一定在nums[left,mid]中间
 * 2、nums[mid] > nums[right], 最小值一定在nums(mid,right]中间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
 * @Date 2020/6/11 0:58
 */
public class Solution {
    public int findMin(int[] nums) {
        int  len = nums.length;
        int left = 0;
        int right = len - 1;
        int min = nums[0];
        while(left <= right) {
            int mid = left + (right - left) / 2;
            /** 最小值一定在nums[left,mid]中间 */
            if(nums[mid] < nums[right]) {
                right = mid;
            }else if(nums[mid] > nums[right]){
                /** 最小值一定在nums(mid,right]中间 */
                left = mid + 1;
            }else {
                /** TODO nums[mid] == nums[right]时，因为 nums[mid] 和 nums[right] 相等，所以我们直接把 nums[right] 抛弃一定不会影响结果 */
                right--;
            }
            min = Math.min(min, nums[mid]);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {10,1,10,10,10};
        System.out.println(solution.findMin(nums));
    }

}
