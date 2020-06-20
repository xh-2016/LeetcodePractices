package xh.leetcode.滑动窗口.LC_3_无重复字符的最长子串;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.*;

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


    /** 解法二：用一个队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，这样我们就可以保证队列中永远都没有重复的元素*/
    public int lengthOfLongestSubstring1(String s) {
        int res  = 0;
        /** 用一个队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，这样我们就可以保证队列中永远都没有重复的元素 ,链表;先进先出 */
        Queue<Character> charQueue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            /** queue中包含当前字符，队首出队 */
            while (charQueue.contains(c)) {
                charQueue.poll();
            }
            /** 不包含，加入队尾 */
            charQueue.add(c);
           // ((LinkedList<Character>) charQueue).addLast(c);
            res = Math.max(res, charQueue.size());
        }
        return res;
    }

    /** 滑动窗口：Set记录不重复的最大子串的所有字符 */
    public int lengthOfLongestSubstring2(String s) {
        int res  = 0;
        int right = 0;
        int left = 0;
        /** 用一个队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，这样我们就可以保证队列中永远都没有重复的元素 ,链表;先进先出 */
        Set<Character> set = new HashSet<>();
        while(right < s.length()) {
            if(set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }else {
                set.add(s.charAt(right));
                /** 求最大子串长度，所有在每次加入元素到set时计算 */
                res = Math.max(res, set.size());
                right++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring2(s));
    }

}
