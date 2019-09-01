package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/4/17 12:37
 */
public class UniquePaths {
    /**
     * TODO LeetCode 62 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。问总共有多少条不同的路径？
     * 说明：m 和 n 的值均不超过 100。
     * 【经典动态规划 + 空间压缩】dp[i] = dp[i] + dp[i-1]
     * 状态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 从m[i][j]到m[i+1][j+1]:两条路径：m[i][j]-->m[i+1][j]-->m[i+1][j+1];m[i][j]-->m[i][j+1]-->m[i+1][j+1]
     * 示例 1:
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 示例 2:
     * 输入: m = 7, n = 3
     * 输出: 28
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0){
            return 1;
        }

        int more = (m > n) ? m : n;
        int less = (m > n) ? n : m;

        //保存到当前位置的路径条数
        int[] dp = new int[less];
        //初始化第一行中到各位置的路径条数
        for(int i = 0;i < less;i++){
            dp[i] = 1;
        }

        for(int i = 1;i < more;i++){
            for(int j = 1;j < less;j++){
                dp[j] += dp[j-1];
            }
        }

        return dp[less -1];
    }

    /**
     * TODO LeetCode 63 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 说明：m 和 n 的值均不超过 100。
     * 【动态规划 + 空间压缩】 和LeetCode62的区别仅为：当当前位置有障碍物时，不能到达当期那位置，即dp[cur]=0;注意异常情况的处理
     * 示例 1:
     * 输入:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * 输出: 2
     * 解释:
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0){
            return 1;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int more = (row > col) ? row : col;
        int less = (row > col) ? col : row;

        //保存到当前位置的路径条数
        int[] dp = new int[less];
        //初始化第一行中到各位置的路径条数
        //注意异常： {{1,0},{0,0}};
        if(obstacleGrid[0][0] == 1){
            dp[0] = 0;
        }else{
            dp[0] = 1;
        }
        for(int i = 1;i < less;i++){
            //当前位置有障碍物
            if(more == row ? obstacleGrid[0][i] == 1 : obstacleGrid[i][0] == 1){
                dp[i] = 0;
            }else
                {
                dp[i] = dp[i-1];
            }
        }

        for(int i = 1;i < more;i++){
            if(more == row ? obstacleGrid[i][0] == 1 : obstacleGrid[0][i] == 1){
                dp[0] = 0;
            }
            for(int j = 1;j < less;j++){
                //当前位置有障碍物
                if(more == row ? obstacleGrid[i][j] == 1 : obstacleGrid[j][i] == 1){
                    dp[j] = 0;
                }else{
                    dp[j] += dp[j-1];
                }
            }
        }

        return dp[less -1];
    }

    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        int m = 7;
        int n = 3;
        int res = 0;
        res = solution.uniquePaths(m,n);
        int[][] input =
                {{0,1},{0,0}};
                 //{{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
//                {
//                {0,0,0},
//                {0,1,0},
//                {0,0,0}
//        };
        //{{0,0,0}};
        res = solution.uniquePathsWithObstacles(input);
        System.out.println(res);
    }

}
