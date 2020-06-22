package xh.leetcode.双指针.LC_26_删除排序数组中的重复项;

/**
 * @Author XH
 * @Description TODO 双指针-快慢指针 easy
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1)额外空间的条件下完成。
 *  
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 *  
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 *
 * TODO 快慢指针：
 * 1、快慢指针处元素相等时，移动右指针，直到不相等
 * 2、不相等时，移动左指针，再用右指针处元素替换左指针处元素；
 * 3、最后继续遍历右指针并判断
 *
 * 思路：考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下
 * 比较 p 和 q 位置的元素是否相等。
 * 如果相等，q 后移 1 位
 * 如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
 * 重复上述过程，直到 q 等于数组长度。
 * 返回 p + 1，即为新数组长度。
 * tips：数组中没有重复元素，按照上面的方法，每次比较时 nums[p] 都不等于 nums[q]，因此就会将 q 指向的元素原地复制一遍，这个操作其实是不必要的。
 * 因此我们可以添加一个小判断，当 q - p > 1 时，才进行复制。
 *
 * @Date 2020/6/23 1:16
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        /** 左指针 */
        int i = 0;
        /** 遍历右指针 */
        for(int j = 1; j < len; j++) {
            /** 不相等，复制元素 */
            if(nums[j] != nums[i]) {
                i++;
                /** 注意：若 i 与 j 相等，且 nums[i] 与 nums[j] 指向同一地址，复制就不会增加更多负担 */
                nums[i] = nums[j];
            }
        }
        /** 返回移除重复元素后数组长度 */
        return i+1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(solution.removeDuplicates(nums));
    }

}
