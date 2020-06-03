package xh.leetcode.动态规划.LC_72_编辑距离;

/**
 * @Author XH
 * @Description TODO 动态规划 hard 自底向上
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 *
 * TODO 想象指针在字符串最后一个元素处
 * https://blog.csdn.net/qq_38261445/article/details/87025530
 * https://www.cxyxiaowu.com/2775.html
 * 1、定义dp[i][j] = x：单词a中前i个(下标为0~i-1)字符 变动到 单词b中前j个(下标为0~j-1)字符的最小编辑距离为x
 * 2、base case：a或b为空字符串，即dp[0][j] = j,dp[i][0]=i
 * 3、题意等价于: return dp[m][n]
 * 4、状态转移方程：
 * 当a[i-1] == b[j-1],则dp[i][j] = dp[i-1][j-1] ==》 跳过
 * 当a[i-1] != b[j-1]时，
 * 插入  dp[i][j] = dp[i][j-1] + 1  =》在i后插入元素，继续比较i
 * 删除  dp[i][j] = dp[i-1][j] + 1 =》删除i处元素，继续比较
 * 替换  dp[i][j] = dp[i-1][j-1] + 1 =》替换i处元素
 * 即dp[i][j] = Min{dp[i][j-1],dp[i-1][j],dp[i-1][j-1]} + 1
 *
 * @Date 2020/6/4 0:50
 */
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        /** 初始化base case */
        /** 删除 */
        for(int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        /** 插入 */
        for(int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                /** 相等则跳过 */
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = minOf3(dp[i][j-1],dp[i-1][j],dp[i-1][j-1]) + 1;
                }
            }
        }

        return dp[m][n];
    }

    /** 求三个整数的最小值 */
    private int minOf3(int num1, int num2, int num3) {
        return Math.min(num1,Math.min(num2, num3));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(solution.minDistance(word1, word2));
    }

}
