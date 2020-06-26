package xh.leetcode.双指针.LC_80_删除排序数组中的重复项II;

/**
 * @Author XH
 * @Description TODO 双指针（快慢指针） medium 解题思路类似于26题，多了一个count记录当期遍历元素出现的次数
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 *
 * TODO 慢指针指向满足条件的数字的末尾，快指针遍历原数组。并且用一个变量记录当前末尾数字出现了几次，防止超过两次
 * 遍历快指针，当nums[fast] == nums[slow]时,令count++,否则count=0；
 * 当count >= 2时，continue,否则 slow++，nums[slow] = nums[fast]
 * return slow+1;
 * @Date 2020/6/26 14:53
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        /** 记录当前遍历到的数字nums[fast]出现的次数,默认为0 */
        int count = 0;
        for(; fast < nums.length; fast++) {
            if(nums[fast] == nums[slow]) {
                count++;
            } else {
                count = 0;
            }
            if(count >= 2) {
                continue;
            } else {
                slow++;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(solution.removeDuplicates(nums));
    }

}
