package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *【动态规划】dp[i]保存正整数i的最大乘积，状态转移方程：
 * TODO dp[i] = max{dp[i-j]*j,(i-j)*j},j = 1~(i-1)
 * 为什么要加上 j * (i-j)的比较呢， 是为了处理dp[i-j] < i-j的情况
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * @Date 2019/4/21 18:16
 */
public class LeetCode343 {
    //n >= 2 && n <= 58
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i = 3;i <= n;i++){
            for(int j = 1;j <= i - 1;j++){
                dp[i] = Math.max(dp[i],Math.max(dp[i-j]*j,(i-j)*j)) ;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode343 solution = new LeetCode343();
        int res = 0;
        int n = 10;
        res = solution.integerBreak(n);
        System.out.println(res);
    }

}
