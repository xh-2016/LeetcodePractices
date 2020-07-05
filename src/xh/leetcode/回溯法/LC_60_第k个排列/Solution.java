package xh.leetcode.回溯法.LC_60_第k个排列;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO medium 数学规律 eg:已经确定第1位数，剩下数字的排列情况为(n-1)!
 * https://leetcode.wang/leetCode-60-Permutation-Sequence.html
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * @Date 2020/7/1 2:01
 */
public class Solution {
    /**
     * TODO 以 n = 3 为例，观察所有排列，发现以 1 开头的有两个，以 2 和 3开头的也各有两个。
     * 一般地，给出集合{X1, X2, X3, ... Xn}。对于第 1 位，以 X1 ~ Xn 开头的排列依次有 (n-1)! 个，所以集合中的第 rank = (k-1) / (n-1)! 个数字就是第 1 位应该填入的数字。然后将填入的数字从集合中剔除，更新 k 的值k = k - rank * (n-1)!，继续对后续的 n-1 位执行相同过程，直到结束。
     */
    public String getPermutation(int n, int k) {
        /** 初始化1~n-1的阶乘 */
        int[] f = new int[n+1];
        f[0] = 1;
        for(int i = 1; i < n; i++) {
            f[i] = f[i-1] * i;
        }

        /** 所有可选的数字 */
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        /** 保存结果 */
        StringBuilder res = new StringBuilder();
        /** k从1开始计数，这里需要减1 */
        k = k - 1;
        for(int i = n - 1; i >= 0; i--) {
            int idx = k / f[i];
            res.append(nums.get(idx));
            nums.remove(idx);
            k %= f[i];
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int k = 3;
        System.out.println(solution.getPermutation(n, k));
    }

}
