package xh.leetcode.动态规划.LC_322_零钱兑换_凑零钱最少硬币数;

/**
 * @Author XH
 * @Description TODO 动态规划 Medium
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 *
 * TODO
 * 1、定义dp[n] = x：凑出零钱n需要最少硬币数为x
 * 2、题意等价于：dp[amount]
 * 3、状态转移方程：
 * dp[n] = Min{dp[n],dp[n-coins[i]] + 1}
 * 4、base case：dp[0] = 0; dp[n] = -1, n < 0
 * @Date 2020/5/31 19:17
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        /** 初始化,零钱amount最多需要amount枚硬币 */
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            dp[i] = amount + 1;
        }

        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    //TODO 核心动态转移方程
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                } else {
                    continue;
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;

        Solution solution = new Solution();
        System.out.println(solution.coinChange(coins, amount));
    }

}
