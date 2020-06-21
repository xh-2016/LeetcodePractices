package xh.leetcode.滑动窗口.LC_242_有效的字母异位词;

import java.util.Arrays;

/**
 * @Author XH
 * @Description TODO 单词统计 easy
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 *
 * @Date 2020/6/21 16:14
 */
public class Solution {
    public boolean isAnagram(String s, String t) {
        int len_s = s.length();
        int len_t = t.length();
        if(len_s != len_t) {
            return false;
        }

        int[] chars = new int[26];
        for(int i = 0; i < len_s; i++) {
            chars[s.charAt(i) - 97]++;
            chars[t.charAt(i) - 97]--;
        }
        for (int c : chars) {
            if(c != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "anagrae";
        String t = "nagaram";
        System.out.println(solution.isAnagram(s, t));
    }

}
