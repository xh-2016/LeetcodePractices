package 二分查找.LC_81_搜索旋转排序数组II;

/**
 * @Author XH
 * @Description TODO 二分查找 变形 middler
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * 这是 搜索旋转排序数组 的延伸题目，TODO 本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * @Date 2020/6/10 1:35
 */
public class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int len = nums.length;
        int right = len - 1;
        if(len == 0) {
            return false;
        }

        while(left <= right) {
            int middle = left + (right - left) / 2;
            if(nums[middle] == target) {
                return true;
            }
            /** 因为存在重复元素，故相等时，需特殊处理 */
            if(nums[middle] == nums[right]) {
                right--;
                continue;
            }
            /** nums[middle,right]有序 */
            if(nums[middle] < nums[right]) {
                if(nums[middle] <= target && target <= nums[right]) {
                    left = middle + 1;
                }else if(nums[middle] == target || target == nums[right]) {
                    return true;
                }else {
                    right = middle - 1;
                }
            }else {
                /** nums[left,middle]有序 */
                if(nums[left] < target && target < nums[middle]) {
                    right = middle - 1;
                }else if(nums[left] == target || target == nums[middle]) {
                    return true;
                }else {
                    left = middle + 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {1,3,1,1,1};
        int target = 3;
        System.out.println(solution.search(nums, target));
    }

}
