package xh.leetcode.双指针.LC_31_下一个排列;

import java.util.Arrays;

/**
 * @Author XH
 * @Description TODO medium 双指针
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 *
 * TODO 需要找到数组中的两个数，前面的比后面的小，用两个指针去寻找，i指针在前，j指针在后，由于要寻找最小的增量，所有i要尽可能靠右，所以从右往前遍历
 * @Date 2020/7/1 2:04
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        /** i在前,从最右边开始遍历 */
        for (int i = len - 1 ; i >= 0; i--) {
            /** j在后 */
            for(int j = len - 1; j > i; j--) {
                if(nums[i] < nums[j]) {
                    /** 原地交换元素 */
                    swap(nums, i, j);
                    /** 找到后，对i后面的所有元素进行升序排序 */
                    Arrays.sort(nums, i + 1, len);
                    return;
                }
            }
        }
        /** 如果没找到，直升序排序原数组 */
        Arrays.sort(nums);
    }

    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        solution.nextPermutation(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

}
