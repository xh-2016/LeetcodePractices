package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * https://blog.csdn.net/fuxuemingzhu/article/details/79787425
 * 【动态规划——01背包】当nums的累加和为奇数时，显然不成立
 * dp[i][j]表示nums中前i个数的元素和能否等于j（true/false）
 * dp[0][j] = false,dp[i][0] = true
 * 状态转移方程：dp[i][j] = dp[i-1][j] || dp[i-1][j-num[i]]
 * 遍历到i位置时能不能构成j = 前面的数字的和能够成j || 前面的数字能构成j - nums[i]。这两个状态分别对应不取和取nums[i]的两种情况，如果有一种情况成立即可。
 *
 * 【DP+空间压缩】
 * 从后向前更新dp，这是因为每个位置依赖与前面的一个位置加上nums[j]，如果我们从前向后更新的话，那么dp[j - 2]会影响dp[j - 1]，然后dp[j - 1]接着影响dp[j]，即同样的一个nums[i]被反复使用了多次，结果肯定是不正确的。但是从后向前更新就没有问题
 * 状态转移方程：dp[j] = dp[j] || dp[j - nums[i]，dp[0] = true
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 * @Date 2019/4/23 18:30
 */
public class LeetCode416 {
    //01背包 DP+空间压缩
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }

        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 != 0){
            return false;
        }

        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for(int i = 0;i < nums.length;i++){
            //反向更新dp
            for(int j = target;j >= nums[i];j--){
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        LeetCode416 solution = new LeetCode416();
        int[] nums = {1, 5, 11, 5};
        boolean res = false;
        res = solution.canPartition(nums);
        System.out.println(res);
    }

}
