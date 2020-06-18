package xh.leetcode.滑动窗口.LC_76_最小覆盖子串;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XH
 * @Description TODO 滑动窗口 hard
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 *
 * TODO 用两个map保存S、T中元素出现次数，用来匹配S是否包含T中所有字符
 * 1、left=right=0，先right++，直到符合题意；
 * 2、left++，移除最左边元素，更新是否符合题意； 【更新计算 题意所求结果（最小）】
 * 3、更新计算 题意所求结果（最大）
 *
 * https://leetcode.wang/leetCode-76-Minimum-Window-Substring.html
 *
 * @Date 2020/6/19 0:50
 */
public class Solution {
    public String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        //TODO 这里可以优化为一个map，map记录了当前窗口之外还需要包含的字符及其次数，map通过t初始化为t中所有字符及其出现次数；当map中字符key对应的次数value均为0时，符合题意
        /** needs保存 t字符串中每个字符和其出现的次数 */
        Map<Character,Integer> needs = new HashMap<>(16);
        /** window保存当前窗口下每个字符和其出现的次数 */
        Map<Character,Integer> window = new HashMap<>(16);
        /** 匹配上的字符个数 */
        int match = 0;
        /** 结果字符串的开始索引 */
        int start = 0;
        /** 结果字符串的最小长度 */
        int minLength = Integer.MAX_VALUE;

        /** 遍历目标字符串t，初始化needs */
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }

        while(right < s.length()) {
            char c1 = s.charAt(right);
            if(needs.containsKey(c1)) {
               window.put(c1, window.getOrDefault(c1,0) + 1);
               if(needs.get(c1).intValue() == window.get(c1).intValue()) {
                   match++;
               }
            }
            /** 移动右指针 */
            right++;

            /** 滑动窗口包含目标字符串t中所有字符,字符出现的次数也一致 */
            while(match == needs.size()) {
                /** 更新最小字符串 */
                if(minLength > (right - left)) {
                    minLength = right - left;
                    start = left;
                }
                /** 判断needs中是否包含left移动前的元素，包含则需要移除,并判断needs和window此时的match数 */
                char c2 = s.charAt(left);
                if(needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if(needs.get(c2).intValue() > window.get(c2).intValue()) {
                        match--;
                    }
                }
                /** 移动左指针 */
                left++;
            }
        }

        /** substring左闭右开 */
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "AA";
        String t = "AA";
        System.out.println(solution.minWindow(s,t));
    }

}
