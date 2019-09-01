package xh.leetcode.dynamicProgramming;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author XH
 * @Description TODO leetCode 279 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 【DP】状态转移方程：dp[n]=min{dp[n-i*i]+1},i*i <= n,i >= 1;
 * dp[0]=1,dp[1]=1,dp[2]=dp[1]+1=2,dp[3]=dp[2]+1=3;
 * dp[4]=min{dp[4-1*1]+1,dp[4-2*2]+1}=min{4,1}=1  ……
 *
 * 【BFS】图的最短路径
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * @Date 2019/4/8 0:15
 */
public class NumSquares {

    //动态规划
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        for(int i = 1;i <= n;i++){
            //最长长度：i个平方数1相加
            dp[i] = i;
            for(int j = 1;j * j <= i;j++){
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }


    //TODO:bfs 不太懂 不熟 
    public int numSquares_bfs(int n) {
        //判断当前数是否已被访问过
        boolean[] visited = new boolean[n+1];
        //queue中保存int[2],[0]是当前数，[1]是当前数需要的完全平方数
        Queue<int[]> q = new LinkedList<>();
        ((LinkedList<int[]>) q).add(new int[]{n,0});
        visited[n] = true;

        while(!q.isEmpty()){
            int[] cur = ((LinkedList<int[]>) q).pop();
            int t = (int)Math.sqrt(cur[0]);
            for(int i = t;i > 0;i--){
                int nextVal = cur[0] - i*i;
                if(nextVal == 0){
                    return cur[1] + 1;
                }

                if(!visited[nextVal]){
                    visited[nextVal] = true;
                    ((LinkedList<int[]>) q).add(new int[]{nextVal,cur[1] + 1});
                }
            }
        }
        //最大情况：n个完全平方数1相加
        return n;
    }

    public static void main(String[] args) {
        NumSquares solution = new NumSquares();
        int n = 12;
        int res = 0;
        res = solution.numSquares_bfs(n);
        System.out.println(res);
    }

}
