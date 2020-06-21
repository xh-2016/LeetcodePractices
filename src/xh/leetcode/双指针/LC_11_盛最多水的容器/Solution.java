package xh.leetcode.双指针.LC_11_盛最多水的容器;

/**
 * @Author XH
 * @Description TODO 双指针 medium 核心：判断丢弃那一边
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 *
 * TODO：
 * maxArea = (right - left) * Math.min(height(left), height(right))
 * 核心：如何缩小范围
 * 当height(left) < height(right)时，必定是往右移动，这样柱子高度Math.min(height(left), height(right))有可能增加；否则柱子高度一定是height(left)，而（right-left）也减小了
 *
 * 当height(left) > height(right)时，往左移动
 * 当height(left) == height(right)时，往左往右移都可以
 *
 * @Date 2020/6/21 18:16
 */
public class Solution {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;

        /** 求最大面积：left ！= right,left==right时，面积为0 */
        while(left < right) {
            res = Math.max(res,(right - left)  * Math.min(height[left], height[right]));
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(heights));
    }

}
