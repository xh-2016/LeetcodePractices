package xh.leetcode.深度优先遍历DFS.剑指Offer13_机器人的运动范围;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author XH
 * @Description TODO DFS(递归)或BFS（迭代） medium 和leetcode79类似
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * @Date 2020/7/23 0:02
 */
public class Solution {
    public int movingCount(int m, int n, int k) {
        /** 标记元素中（i，j）位置的元素是否被访问过 */
        boolean[][] visited = new boolean[m][n];
        return  movingCount_helper(0,0, m, n, k, visited);
    }

    /** DFS 递归 */
    public int movingCount_helper(int i, int j, int m, int n, int k, boolean[][] visited) {
        /** 边界条件判断 */
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || twoSum(i, j) > k) {
            return 0;
        }

        /** 标记选中 */
        visited[i][j] = true;
        /** 递归，分别向上下左右移动，仅一个点时，移动可到达的格子数为1 */
        return 1 +
                movingCount_helper(i - 1, j, m, n, k, visited)  //向上走
                +
                movingCount_helper(i+1, j, m, n, k, visited)   //向下走
                +
                movingCount_helper(i, j - 1, m, n, k, visited)  //向左走
                +
                movingCount_helper(i, j + 1, m, n, k, visited); //向右走
    }

    /** BFS 迭代，使用队列 */
    public int movingCount_bfs(int m, int n, int k) {
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        /** 存放数组，第一位是i，第二位是j */
        Queue<int[]> q = new LinkedList<>();
        /** 矩阵中第一个节点 原点 入队 */
        q.offer(new int[]{0,0});

        while(!q.isEmpty()) {
            /** 出队元素 */
            int[] cur = q.poll();
            int i = cur[0];
            int j = cur[1];
            /** 不符合条件，直接跳过 */
            if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || twoSum(i,j) > k) {
                continue;
            }
            /** 选中元素（i，j） */
            visited[i][j] = true;
            res++;
            /** 上下左右 元素分别入队 */
            q.offer(new int[]{i-1,j});
            q.offer(new int[]{i+1,j});
            q.offer(new int[]{i, j-1});
            q.offer(new int[]{i, j+1});
        }

        return res;
    }

    /** 计算两个数字的数位之和，eg：（35, 38）==》3+5+3+8=19 */
    public int twoSum(int a, int b) {
        int res = 0;
        while(a > 0) {
            res += a % 10;
            a /= 10;
        }

        while(b > 0) {
            res += b % 10;
            b /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 3;
        int n = 1;
        int k = 0;
        System.out.println(solution.movingCount_bfs(m,n,k));
    }

}
