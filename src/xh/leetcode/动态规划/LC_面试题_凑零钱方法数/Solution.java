package xh.leetcode.动态规划.LC_面试题_凑零钱方法数;

/**
 * @Author XH
 * @Description TODO 动态规划 Medium
 * 抽零钱方法数。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * 示例1:
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 *
 * 注意:
 * 你可以假设：
 * 0 <= n (总金额) <= 1000000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-lcci
 *
 * TODO 动态规划
 * 1、定义f[i][j]=x: 用前0~i种硬币凑零钱j分，共有x种方法
 * 2、题意等价于==》 求解 f[3][n]
 * 3、状态转移方程：f[i][j] = f[i-1][j] + f[i][j-coins[i]]
 * 使用前i种硬币凑出j分的方法数 = 使用前i-1种硬币凑出j分的方法数（不使用coins[i]） + 使用前i种硬币凑出j-coins[i]的方法数（使用coins[i]）
 *
 * TODO 优化为一维DP
 * 从上面的状态转移方程可以看出，f[i][j]只与f[i-1][j]和f[i][j-coins[i]]有关，所以完全可以把第一个维度除掉，只用一个一维数组存储，即
 * f[j] = f[j] + f[j - coins[i]]
 *
 * @Date 2020/5/31 18:38
 */
public class Solution {

    public int waysToChange(int n) {
        int[] coins = {1,5,10,25};
        int[][] dp = new int[4][n+1];

        /** 初始化纵轴：只用到第一种硬币1分钱，初始化抽零钱n分的方法数 */
        for(int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        /** 初始化横轴：凑出0分钱的方法数，用多少种硬币都只有一种（使用0个硬币） */
        for(int i = 0; i <= 3; i++) {
            dp[i][0] = 1;
        }

        /** TODO 注意这里 遍历顺序，必须先遍历硬币数组，否则会导致硬币数组被多次遍历，出现重复计数，即6 = 5 + 1 = 1 + 5 ,凑钱种数变多 */
       for (int i = 1; i <= 3; i++) {
           for(int j = 1; j <= n; j++) {
                if(j >= coins[i]) {
                    dp[i][j] = (dp[i-1][j] + dp[i][j - coins[i]]) % 1000000007;
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[3][n];
    }

    public int waysToChangeBetter(int n) {
        int[] coins = {1,5,10,25};
        int[] dp = new int[n+1];
        /** 初始化 */
        for(int i = 0; i <= n; i++) {
            dp[i] = 1;
        }

        /** TODO 注意这里 遍历顺序，必须先遍历硬币数组，否则会导致硬币数组被多次遍历，出现重复计数，即6 = 5 + 1 = 1 + 5 ,凑钱种数变多 */
        for (int i = 1; i <= 3; i++) {
            for(int j = 1; j <= n; j++) {
                if(j >= coins[i]) {
                    dp[j] = (dp[j] + dp[j - coins[i]]) % 1000000007;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        //System.out.println(Solution.waysToChange(n));
        System.out.println(solution.waysToChangeBetter(n));
    }

}
