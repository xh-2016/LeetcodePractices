package xh.leetcode.math;

import java.util.Arrays;

/**
 * @Author XH
 * @Description TODO 最小移动次数 数学找规律题，LeetCode 453、462
 * @Date 2019/3/22 13:43
 */
public class MiniMoveSolution {

    /**
     * TODO LeetCode 453 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
     * 【规律】如果要达到全数组相等，每次操作n-1个元素加1，直到所有元素都相等 == 每次操作最大值-1，直到所有元素等于最小值。所以为了把各个数字调整到相对值相等，要把每个数减到最小的那个。 综合来看是：数组求和-最小值*数组长
     * 示例:
     * 输入:
     * [1,2,3]
     * 输出:
     * 3
     * 解释:
     * 只需要3次移动（注意每次移动会增加两个元素的值）：
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     * @param nums
     * @return
     */
    public int minMoves1(int[] nums) {

        int res = 0;
        //求数组中最小元素
        int min = nums[0];
        for(int num : nums){
            if(num < min){
                min = num;
            }
        }

        //求数组中所有元素的和
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        //数组求和-最小值*数组长
        res = sum - nums.length * min;
        return res;
    }

    /**
     * TODO LeetCode 462 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
     *【规律】移动后，数组元素均相等，此时数组中元素均为原始数组的中位数
     * 假如数组长度为奇数2n+1，则中位数两边各有n个数，设左边所有数和中位数的差值和为x, 右边所有数和中位数的差值和为y；则所有需要移动的次数为x+y，即排序数组中，右边所有数和左边所有数的差值和。
     * 例如:
     * 输入:
     * [1,2,3]
     * 输出:
     * 2
     * 说明：
     * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
     * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        //排序，默认升序
        Arrays.sort(nums);
        int len = nums.length;

        int res = 0;
        //计算排序数组中，右边所有数和左边所有数的差值和，即左右两边元素和中位数的差值和的和
        int i = 0;
        int j = len - 1;
        while(i < j){
            res += nums[j] - nums[i];
            j--;
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        MiniMoveSolution solution = new MiniMoveSolution();
        int res1 = solution.minMoves1(nums1);
        System.out.println(res1);
        int res2 = solution.minMoves2(nums1);
        System.out.println(res2);
    }

}
