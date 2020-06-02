package xh.leetcode.动态规划.LC_面试题14_剪绳子II最大乘积;

/**
 * @Author XH
 * @Description TODO 动态规划 和leetcode343整数拆分取最大乘积值，一模一样,但是需要考虑溢出
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * TODO 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *  
 * 提示：
 * 2 <= n <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
 *
 * TODO n太大，按照传统dp会溢出
 * 1、dp[i] = x: 长度为i的绳子分割后可获得的最大乘积为x
 * 2、base case: dp[1] = 0,dp[2] = 1;
 * 3、状态转移方程：dp[i] = Max{dp[i], dp[i-j]*dp[j], dp[i-j]*j, (i-j)*j, (i-j)*dp[j]}
 * 4、return dp[n]
 *
 * TODO way2 贪心算法
 * 找规律：
 * 2 = 1 + 1 1
 * 3 = 2 + 1 2
 * 4 = 3 + 1 = 2 + 2 4
 * 5 = 3 + 2 6
 * 6 = 3 + 3 9
 * 7 = 3 + 4 12
 * 8 = 3 + 5 = 3 + 3 + 2  18
 *
 * 规律：当n > 4时，数字拆分按照规则依次减少3即可
 *
 * @Date 2020/6/2 1:25
 */
public class Solution {
    /** 传统dp，此时会溢出 */
    @Deprecated
    public int cuttingRopeDeprecated(int n) {
        int[] dp = new int[n+1];
        /** 初始化base case*/
        dp[2] = 1;

        for(int i = 3; i<= n; i++) {
            for(int j = 1; j < i; j++) {
                //TODO dp[i] = Math.max(dp[i], dp[i-j]*j, dp[i-j]*dp[j],(i-j)*dp[j],(i-j)*j);
                Integer tmp1 = Math.max(j, dp[j]);
                Integer tmp2 = Math.max(i-j, dp[i-j]);
                dp[i] = Math.max(dp[i], (tmp1 * tmp2));
            }
        }

        return dp[n];
    }


    public int cuttingRope(int n) {
        if(n == 2) {
            return 1;
        }
        if(n == 3) {
            return 2;
        }
        if(n == 4){
            return 4;
        }
        long res = 1;
        while(n > 4) {
            res *= 3;
            res %= 1000000007;
            n -= 3;
        }
        return (int)((res * n) % 1000000007);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        System.out.println(solution.cuttingRope(n));
    }

}
