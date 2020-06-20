package xh.leetcode.滑动窗口.LC_567_字符串的排列;

import javax.naming.SizeLimitExceededException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author XH
 * @Description TODO 子串问题，滑动窗口 medium
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * TODO ==》目标子串只包含s1中所有字符，不包含其他字符
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 *
 * TODO 滑动窗口 变形题，窗口大小固定，左右指针同时移动
 * @Date 2020/6/20 17:23
 */
public class Solution {
    /** （固定大小）滑动窗口+字符统计计数 */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        /** 这里注意边界判断 */
        if(len1 > len2) {
            return false;
        }
        /** 分别统计s1、s2中26个字符出现的次数 */
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        /** 初始化窗口[0,s1.length)里各个字符出现的次数 */
        for (int i = 0; i < len1; i++) {
           count1[s1.charAt(i) - 97]++;
           count2[s2.charAt(i) - 97]++;
        }

        for(int i = len1; i < len2; i++) {
            if(equals(count1,count2)) {
                return true;
            }
            /** 移动滑动窗口：窗口大小固定，左右同时滑动 */
            /** 右指针加入 */
            count2[s2.charAt(i) - 97]++;
            /** 左指针移除 */
            count2[s2.charAt(i - len1) - 97]--;
        }
        return equals(count1, count2);
    }

    /** 比较两个相同长度的数组 是否完全相等 */
    private boolean equals(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkInclusion_template(String s1, String s2) {
        int left = 0;
        int right = 0;
        /** needs保存s1字符串中所有字符及其出现次数,当map.values均为0时，s2包含s1的排列 */
        Map<Character,Integer> needs = new HashMap<>(16);
        for (char c : s1.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }

        while (right < s2.length()) {
            /** 移动右指针 */
            char c1 = s2.charAt(right);
            right++;
            if(needs.containsKey(c1)) {
                needs.put(c1, needs.get(c1) - 1);
            }

            /** 当窗口大小 > s1的长度时，必须要收缩窗口 */
            while (right - left >= s1.length()) {
                /** 判断是否找到了目标子串 */
                if (match(needs)) {
                    return true;
                }
                /** 移动左指针 */
                char c2 = s2.charAt(left);
                if(needs.containsKey(c2)) {
                    needs.put(c2, needs.get(c2) + 1);
                }
                left++;
            }
        }
        return false;
    }


    /** 校验needs是否符合题意 ：所有字符对应的次数均为0 */
    public boolean match(Map<Character, Integer> needs) {
        for (Integer value : needs.values()) {
            if(value != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "ab";
        String s2 = "a";
        System.out.println(solution.checkInclusion(s1, s2));
    }

}
