package 二分查找.LC_34_在排序数组中查找元素的第一个和最后一个位置;

import org.w3c.dom.ls.LSException;

import java.util.Arrays;

/**
 * @Author XH
 * @Description TODO 二分查找 middle
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * TODO 二分查找 左闭右闭 固定的模板
 * @Date 2020/6/7 15:49
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = findLeftTarget(nums, target);
        res[1] = findRightTarget(nums, target);
        return res;
    }

    /**
     * 二分法在nums中查找最左target，返回索引
     * @param nums
     * @param target
     */
    public int findLeftTarget(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] == target) {
                /** 需定位最左边界：缩小右边界 */
                right = middle - 1;
            }
        }
        /** 检测数组越界 */
        if(left >= len) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 二分法在nums中查找最右target，返回索引
     */
    public int findRightTarget(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] == target) {
                /** 定位最右边界: 缩小左边界 */
                left = middle + 1;
            }
        }
        /** 检测数组越界情况*/
        if (right < 0) {
            return -1;
        }
        return nums[right] == target ? right : -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5,7,7,8,8,10};
        int target = 6;
        Arrays.stream(solution.searchRange(nums, target)).forEach(System.out::println);
    }
}
