package xh.leetcode.滑动窗口.LC_3_无重复字符的最长子串;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XH
 * @Description TODO 滑动窗口 双指针 子串：必须连续；子序列：可以不连续
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * TODO 滑动窗口固定模板 时间复杂度 O(N):双指针最大移动步骤：N+N=2N=O(N)
 * start = end = 0
 * while(end < len) {
 * //向右滑动
 * end++;
 * while(符合题意){
 *     [1、更新最小值结果]
 *     //左边指针向右移动，缩小窗口范围
 *     start++;
 * }
 * [2、更新最大值结果]
 * }
 * return 结果
 *
 * @Date 2020/6/18 1:31
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int start = 0;
        int end = 0;
        int res  = 0;
        /** 保存s中每个字符以及其出现的次数 */
        Map<Character, Integer> charMap = new HashMap<>(16);
        while(end < len) {
            /** 构造 保存s中每个字符出现次数的map */
            char c1 = s.charAt(end);
            charMap.put(c1, charMap.getOrDefault(c1, 0) + 1);
            /** 向右移动 */
            end++;
            /** 出现重复字符时，即向右移动过程中，发现不满足题意 */
            while(charMap.get(c1) > 1) {
                /** 左边指针向右移动 */
                char c2 = s.charAt(start);
                start++;
                charMap.put(c2, charMap.get(c2) - 1);
            }
            //TODO 关键：因为是求最大子串，需要在每次移动end指针时，比较得出最大长度
            res = Math.max(res, end - start);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

}
