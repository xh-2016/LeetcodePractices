package xh.leetcode.动态规划.LC_343_整数拆分;

/**
 * @Author XH
 * @Description TODO
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 *
 * TODO
 * 1、dp[i] = x: 将正整数i分割后可获得的最大乘积为x
 * 2、动态转移方程：dp[i] = Max{dp[i-j]*j, dp[i-j]*dp[j],(i-j)*dp[j], (i-j)*j, dp[i]}
 * 3、base case: dp[1]=0,dp[2]=1;
 * 4、return dp[n]
 * @Date 2020/6/2 2:04
 */
public class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        /** 初始化base case */
        dp[2] = 1;
        dp[3] = 2;

        for(int i = 3; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                //TODO dp[i] = Math.max(dp[i], dp[i-j]*j, dp[i-j]*dp[j],(i-j)*dp[j],(i-j)*j);
                Integer tmp1 = Math.max(j, dp[j]);
                Integer tmp2 = Math.max((i-j), dp[i-j]);
                dp[i] = Math.max(dp[i], tmp1 * tmp2);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        System.out.println(solution.integerBreak(n));
    }

}
