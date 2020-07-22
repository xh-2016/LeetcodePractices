package xh.leetcode.二分查找.LC_153_寻找旋转排序数组中的最小值;

/**
 * @Author XH
 * @Description TODO 二分搜索 变体 middle,最小值，一定在mid后
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * 输入: [5,6,7,0,1,2]
 * 输出: 0
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * TODO 二分查找的重点；确定丢弃哪一半
 * 不存在重复数字，则nums[mid] != nums[right]
 * 1、nums[mid] < nums[right], 最小值在nums[left,mid]中；
 * 2、nums[mid] > nums[right]，最小值在nums[mid,right]中
 *
 * @Date 2020/6/10 23:31
 */
public class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int min = nums[0];
        while(left <= right) {
            int mid = left + (right - left) / 2;
            /** 最小值一定在nums[left,mid]中 */
            if(nums[mid] < nums[right]) {
                right = mid;
            }else {
                /** 最小值一定在nums(mid,right]中 */
                left = mid + 1;
            }
            min = Math.min(min, nums[mid]);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,4,5,1,2};
        System.out.println(solution.findMin(nums));
    }

}
