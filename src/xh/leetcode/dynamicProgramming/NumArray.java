package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO LeetCode 303 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * myNums[i]中保存nums中0~i的和，则sumRange（i,j）=myNums[j] - myNums[i-1]
 * 示例：
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 * @Date 2019/4/21 17:35
 */
public class NumArray {
    private int[] myNums;

    public NumArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }

        myNums = new int[nums.length];
        myNums[0] = nums[0];
        for(int i = 1;i < nums.length;i++){
            myNums[i] = myNums[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? myNums[j] : myNums[j] - myNums[i-1];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray solution = new NumArray(nums);
        int res = 0;
        res = solution.sumRange(2,5);
        System.out.println(res);
    }

}
