package xh.leetcode.双指针.LC_27_移除元素;

/**
 * @Author XH
 * @Description TODO 快慢指针 注意：题意中说元素的顺序可以改变
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 【元素的顺序可以改变】。你不需要考虑数组中超出新长度后面的元素。
 *  
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。
 * 你不需要考虑数组中超出新长度后面的元素。
 *  
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 *
 * TODO 快慢指针，反向思维：
 * i=j=0,开始遍历右指针j，当右指针处元素 ！= val 时，用右指针处元素替代左指针处元素，再令左指针移动一位；继续遍历右指针
 * 当右指针处元素 == val 时，继续遍历右指针即可
 *
 * @Date 2020/6/23 1:41
 */
public class Solution {
    /** 双指针+逆向思维 */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        /** 左指针 */
        int i = 0;
        for(int j = 0; j < len; j++) {
            if(nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        /** 由于每次替换元素后，i++，则返回的数组长度即为i */
        return i;
    }

    /** TODO 注意返回数组中元素的顺序可以改变  ==》左右指针+正向思维 */
    public int removeElement1(int[] nums, int val) {
        int len = nums.length;
        /** 左指针 */
        int left = 0;
        while(left < len) {
            /** 左边元素 == val，用最右边元素直接替换，并缩小数组范围【数组长度减1】 */
            if(nums[left] == val) {
                nums[left] = nums[len - 1];
               len--;
            }else {
                left++;
            }
        }

        /** while终止时，left==len */
        return len;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(solution.removeElement1(nums, val));
    }

}
