package 二分查找.LC_33_搜索旋转排序数组;

/**
 * @Author XH
 * @Description TODO 二分查找 middle
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * TODO 规律 1、nums[mid] <= nums[right],[mid,right]有序;nums[mid] > nums[left],[left,mid]有序
 * 2、在有序数组上，二分查找寻找目标值target
 * nums[
 * @Date 2020/6/9 2:26
 */
public class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] <= nums[right]) {
                /** nums[mid,right]有序 */
                if(nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;//左闭右开
                }else {
                    right = mid - 1;
                }
            }else {
                /** nums[left,mid]有序 */
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;//左闭右开
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,3};
        int target = 3;
        System.out.println(solution.search(nums, target));
    }

}
