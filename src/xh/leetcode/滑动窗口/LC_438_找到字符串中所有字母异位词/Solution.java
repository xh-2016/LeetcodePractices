package xh.leetcode.滑动窗口.LC_438_找到字符串中所有字母异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 【字符计数+固定窗口大小的滑动窗口】 medium  和leetcode567解法完全一致，只是多了保存最左索引的步骤
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 *
 * @Date 2020/6/20 20:09
 */
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int len_s = s.length();
        int len_p = p.length();
        /** 异常处理 */
        if(len_p > len_s) {
            return res;
        }

        /** 记录s和p中所有字符出现的次数 */
        int[] count_s = new int[26];
        int[] count_p = new int[26];
        for(int i = 0; i < len_p; i++) {
            count_p[p.charAt(i) - 97]++;
            count_s[s.charAt(i) - 97]++;
        }
        /** 右索引i遍历字符串s */
        for(int i = len_p; i < len_s; i++) {
            /** 注意：求左索引，需要用右索引i - 目标子串的长度len_p */
            if(isSame(count_p, count_s)) {
                res.add(i - len_p);
            }
            /** 固定大小窗口进行滑动，左右索引同时移动 */
            count_s[s.charAt(i) - 97]++;
            count_s[s.charAt(i - len_p) - 97]--;
        }
        /** 上面循环结束时，i = len_s，此时isSame，则符合条件的索引为 (len_s-1) - len_p + 1 = len_s - len_p */
        if(isSame(count_p, count_s)) {
            res.add(len_s - len_p);
        }
        return res;
    }

    /** 比较两个数组是否完全一样 */
    private boolean isSame(int[] a, int[] b) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "";
        String p = "a";
       solution.findAnagrams(s, p).stream().forEach(System.out::println);
    }

}
