package 二分查找.LC_35_搜索插入位置;

/**
 * @Author XH
 * @Description TODO 二分搜索的变体 easy ==》找到第一个大于等于target的位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 *
 * @Date 2020/6/7 16:51
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        /** 边界情况处理 */
        if(target < nums[0]) {
            return 0;
        }
        if(target > nums[right]) {
            return right + 1;
        }
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target) {
                left = mid + 1;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else if(nums[mid] == target) {
                return mid;
            }
        }
        /** 能走到这一步，说明，没有此元素，即将插入的位置为：
         * 1、若nums均大于target，此时right = -1，left= right+1=0； return left
         * 2、若nums均小于target，此时left = right + 1 = len + 1； return left
         * */
       return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target =7;
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(nums, target));
    }

}
