package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO LeetCode 64 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。说明：每次只能向下或者向右移动一步。
 * 【经典动态规划】
 * 1、new dp[m][n],初始化dp[0][0……n]、dp[0……n][0]
 * 2、状态转移方程：dp[i][j]=min{dp[i-1][j],dp[i][j-1]}+m[i][j]
 * dp[i][j]表示从左上角（0,0）位置走到(i,j)位置的最小路径和
 * 3、return dp[m][n]
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * @Date 2019/4/15 19:13
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for(int i = 1;i < row;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j = 1;j < col;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for(int i = 1;i < row;i++){
            for(int j = 1;j < col;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[row-1][col-1];
    }

    //动态规划 + 空间压缩，时间复杂度不变

    /**
     * TODO 【动态规划+空间压缩】 输入 grid[m][n]
     * 1、 new dp[min{m,n}]
     * 2、若m > n，则dp按行初始化dp；若m < n,则dp按列初始化dp
     * 3、若按行更新，dp[0] = dp[0] + grid[i-1][0];
     * dp[i] = min{dp[i-1],dp[i]} + grid[i][j]
     * 4、若按列更新，dp[0] = dp[0] + grid[0][j-1];
     * dp[j] = min{dp[j-1],dp[j]} + grid[j][i]
     * @param grid
     * @return
     */
    public int minPathSumNew(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int more = row > col ? row : col;
        int less = row > col ? col : row;

        int[] dp = new int[less];
        dp[0] = grid[0][0];
        for(int i = 1;i < less;i++){
            dp[i] = dp[i-1] + (row == more ? grid[0][i] : grid[i][0]);
        }

        for(int i = 1;i < more;i++){
            dp[0] = dp[0] + (row == more ? grid[i][0] : grid[0][i]);
            for(int j = 1;j < less;j++){
                dp[i] = Math.min(dp[j-1],dp[j]) + (row == more ? grid[i][j] : grid[j][i]);
            }
        }

        return dp[less - 1];
    }

    public static void main(String[] args) {
        MinPathSum solution = new MinPathSum();
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };

        int res = 0;
        res = solution.minPathSumNew(grid);
        System.out.println(res);
    }

}
