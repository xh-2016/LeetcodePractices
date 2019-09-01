package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO 给定一个无序的整数数组，找到其中最长上升子序列[的长度]。
 *【动态规划】dp[i]保存以arr[i]结尾时，arr[0……i]的最大递增子序列的长度
 * TODO 状态转移方程：if(arr[i] > arr[j]) dp[i] = max{dp[j] + 1},0 <= j < i;else dp[i] = 1;
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * @Date 2019/4/21 18:50
 */
public class LeetCode300 {
    //TODO 状态转移方程：
    // if(arr[i] > arr[j]) dp[i] = max{dp[j] + 1},0 <= j < i;
    // else dp[i] = 1;
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        for(int i = 0;i < len;i++){
            dp[i] = 1;
            for(int j = 0;j < i;j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        //遍历dp获得最大递增子序列的长度
        int res = 0;
        for(int i:dp){
            if(i > res){
                res = i;
            }
        }
        return res;
    }

    //给定数组nums，求其最大递增子序列
    public int[] lengthOfLIS_path(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        for(int i = 0;i < len;i++){
            dp[i] = 1;
            for(int j = 0;j < i;j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        int[] path = getPath(dp,nums);
        return path;
    }

    //获得最大递增子序列之一
    public int[] getPath(int[] dp,int[] nums){
        //遍历dp获得最大递增子序列的长度，定位最大递增子序列的末尾元素的位置
        int maxLen = 0;
        int index = 0;
        for(int i = 0;i < dp.length;i++){
            if(dp[i] > maxLen){
               maxLen = dp[i];
               index = i;
            }
        }

        //回退获取路径
        //从index处向左遍历:nums[i] < nums[index] && dp[i] == dp[index] - 1时，将nums[i]加入path
        int[] path = new int[maxLen];
        path[--maxLen] = nums[index];
        for(int i = index;i >=0;i--){
            if(nums[i] < nums[index] && dp[i] == dp[index] - 1){
                path[--maxLen] = nums[i];
                index = i;
            }
        }

        return path;
    }

    public static void main(String[] args) {
        LeetCode300 solution = new LeetCode300();
        int[] nums = {10,9,2,5,3,7,101,18};
        int res = 0;
        res = solution.lengthOfLIS(nums);
        System.out.println(res);
        int[] path;
        path = solution.lengthOfLIS_path(nums);
        for(int i : path){
            System.out.println(i);
        }
    }

}
