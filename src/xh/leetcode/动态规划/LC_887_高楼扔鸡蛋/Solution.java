package xh.leetcode.动态规划.LC_887_高楼扔鸡蛋;

/**
 * @Author XH
 * @Description TODO 动态规划 hard
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 * 示例 1：
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * 输入：K = 3, N = 14
 * 输出：4
 *
 * 提示：
 * 1 <= K <= 100
 * 1 <= N <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 *
 * TODO
 * 1、状态和选择，状态：鸡蛋个数K和楼层层数N；选择：在哪层楼扔鸡蛋
 * 2、dp[k][n]=x:k个鸡蛋，n层楼，确定F，最少需要扔x次
 * 3、状态转移方程：dp[k][j] = min{max{dp[k-1][j-1],dp[k][n-j]} + 1},2<=k<=K;1<=n<=N;1<=j<=n
 * 4、题意等价于：return dp[k][n]
 * 5、base case:K=1,return N; N=0,return 0;
 * 6、动态规划时间复杂度=子问题个数*子问题复杂度 = (K*N) * O(N)
 * @Date 2020/6/6 0:38
 */
public class Solution {
    //FIXME 解法超时
    @Deprecated
   public int superEggDrop(int K, int N) {
        int[][] dp = new int[K+1][N+1];
        /** 初始化base case */
        for(int j = 1; j <= N; j++) {
            dp[1][j] = j;
        }
        for(int i = 1; i <= K; i++) {
            dp[i][0] = 0;
        }

        /** 时间复杂度 O(K*N*N) */
        for(int k = 2; k <= K; k++) {
            for(int j = 1; j <= N; j++) {
                int res = Integer.MAX_VALUE;
                for(int i = 1; i <= j; i++) {
                    res = Math.min(res, Math.max(dp[k-1][i-1],dp[k][j-i]) + 1);
                }
                dp[k][j] = res;
            }
        }
        return dp[K][N];
    }

    /** 优化为二分查找 */
    public int superEggDrop1(int K, int N) {
        int[][] dp = new int[K+1][N+1];
        /** 初始化base case */
        for(int j = 1; j <= N; j++) {
            dp[1][j] = j;
        }
        for(int i = 1; i <= K; i++) {
            dp[i][0] = 0;
        }

        /** 时间复杂度 O(K*N*logN) */
        for (int k = 2; k <= K; k++) {
            for (int j = 1; j <= N; j++) {
                int low = 1;
                int high = j;
                int res = Integer.MAX_VALUE;
                while (low <= high) {
                    int middle = low + (high - low) / 2;
                    /** 鸡蛋碎了 */
                    int broken = dp[k - 1][middle - 1];
                    /** 鸡蛋没碎 */
                    int not_broken = dp[k][j - middle];
                    if (broken > not_broken) {
                        high = middle - 1;
                        res = Math.min(res, broken + 1);
                    } else {
                        low = middle + 1;
                        res = Math.min(res, not_broken + 1);
                    }
                }
                dp[k][j] = res;
            }
        }
        return dp[K][N];
    }

    /** 升级版动态规划：
     * 1、dp[k][m] = x :k个鸡蛋，扔m次鸡蛋，最多可测试楼层数为x
     * 2、状态转移方程：(鸡蛋碎了dp[k-1][m-1]，测试楼下层数；鸡蛋没碎dp[k][m-1]，测试楼上层数;总楼层数x = 楼上层数 + 楼下层数 + 1)
     * dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1;
     * 3、题意等价于： while(dp[k][m] < N) return m
     * 4、base case dp[0][] = 0,dp[][0] = 0
     * */
    public int superEggDrop2(int K, int N) {
        int[][] dp = new int[K+1][N+1];
        /** 初始化base case */
        for(int j = 1; j <= N; j++) {
            dp[0][j] = 0;
        }
        for(int i = 1; i <= K; i++) {
            dp[i][0] = 0;
        }

        int m = 0;
        /** 时间复杂度：O（KN）*/
        // while(dp[K][m] < N) 等价于 for(int m = 1;dp[K][m] < N; m++)
        while(dp[K][m] < N) {
            m++;
            for(int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m-1] + dp[k-1][m-1] + 1;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int K = 3;
        int N = 14;
        System.out.println(solution.superEggDrop2(K,N));
    }

}
