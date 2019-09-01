package xh.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 【多数投票法】求数组中所有出现次数大于⌊ n/k ⌋ 的元素 系列题
 * @Date 2019/3/22 13:46
 */
public class ModeSolution {

    /**
     * TODO LeetCode 169 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 【多数投票法】出现次数超过 ⌊ n/2 ⌋ 的元素最多有1个
     * 遍历数组，选取首个数字并记录，开始计数，遇到相同的就+1，不同就-1；当计数归0时，再从当前元素开始计数，记录当前元素，遍历结束，众数对应的计数器必然>0
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。===》 最后不需要遍历候选值确认是否是众数
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        int maxNum = nums[0];
        int count = 0;
        for(int num : nums){
            if(count == 0){
                maxNum = num;
                count++;
            }else {
                if (maxNum == num) {
                    count++;
                } else {
                    count--;
                }
            }

        }
        return maxNum;
    }

    /**
     * TODO LeetCode 229 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * 【多数投票法】出现次数超过 ⌊ n/3 ⌋的数字最多有两个，选出候选的两个值后需进一步遍历验证
     *  遍历数组，初始化两个候选值max1、max2，分别对两个候选值如majorityElement1所示，判断当期元素是否等于候选值，相等则计算+1，不等则-1；计数归0，则替换候选值为当前元素，且计数重置为1。遍历结束后，重新遍历数组，验证候选值是否满足题意
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: [3]
     * 示例 2:
     * 输入: [1,1,1,3,3,2,2,2]
     * 输出: [1,2]
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();

        if(nums == null || nums.length == 0){
            return res;
        }

        int max1 = nums[0];
        int count1 = 0;
        int max2 = nums[0];
        int count2 = 0;

        for(int num : nums){
            if(num == max1){
                count1++;
            }else if(num == max2){
                count2++;
            }else if(count1 == 0){
                max1 = num;
                count1 = 1;
            }else if(count2 == 0){
                max2 = num;
                count2 = 1;
            }else{//num != max1 && num != max2 && count1 != 0 && count2 != 0
                count1--;
                count2--;
            }
        }

        //获得候选集后，重新遍历数组，验证候选集是否符合题意
        count1 = 0;
        count2 = 0;
        for(int num : nums){
            if(num == max1){
                count1++;
            }else if(num == max2){
                count2++;
            }
        }

        if(count1 > nums.length / 3){
            res.add(max1);
        }
        if(count2 > nums.length / 3){
            res.add(max2);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] input = {3,2,3};
        int[] nums = {1,1,1,3,3,2,2,2};
        ModeSolution solution = new ModeSolution();
        int mode1 = 0;
        List<Integer> mode2 = new ArrayList<>();
        mode1 = solution.majorityElement1(input);
        System.out.println(mode1);
        mode2 = solution.majorityElement2(nums);
        System.out.println(mode2);
    }

}
