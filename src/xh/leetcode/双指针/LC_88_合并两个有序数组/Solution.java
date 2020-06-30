package xh.leetcode.双指针.LC_88_合并两个有序数组;

import java.util.Arrays;

/**
 * @Author XH
 * @Description TODO 双指针 从数组末尾开始比较 easy
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * @Date 2020/6/26 16:50
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = n - 1;
        int j = m - 1;
        int tail = m + n - 1;
        while(i >= 0) {
            /** 考虑特殊情况：nums1中m长度不足，但是nums2还有元素，则直接将nums2中元素复制到nums1即可 */
            if(j < 0) {
                while(i >= 0) {
                    nums1[tail--] = nums2[i--];
                }
                return;
            }
            if (nums2[i] >= nums1[j]) {
                nums1[tail--] = nums2[i--];
            } else {
                nums1[tail--] = nums1[j--];
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;
        solution.merge(nums1, m, nums2, n);
        Arrays.stream(nums1).forEach(System.out::println);
    }

}
