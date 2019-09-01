package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * 【动态规划——最长公共子序列LCS】
 * dp[i][j]表示word1中前i个字符和word2中前j个字符的最长公共子序列的长度
 * 显然dp[0][j] = 0,dp[i][0] = 0;
 * TODO 状态转移方程：if(word1[i-1] == word2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;else dp[i][j] = max{dp[i-1][j],dp[i][j-1]};
 *
 * 示例 1:
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * 说明:
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 * @Date 2019/4/23 18:54
 */
public class LeetCode583 {
    //先求LCS，再用len1+len2-2*LCS
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null){
            return 0;
        }

        if(word1.length() == 0 || word2.length() == 0){
            return Math.max(word1.length(),word2.length());
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 0;i <= len1;i++){
            dp[i][0] = 0;
        }
        for(int j = 0;j <= len2;j++){
            dp[0][j] = 0;
        }

        for(int i = 1;i <= len1;i++){
            for(int j = 1;j <= len2;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return len1 + len2 - 2*dp[len1][len2];
    }

    public static void main(String[] args) {
        LeetCode583 solution = new LeetCode583();
        String word1 = "sea";
        String word2 = "";
        int res = 0;
        res = solution.minDistance(word1,word2);
        System.out.println(res);
    }

}
