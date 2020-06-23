package xh.leetcode.双指针.LC_28_实现strStr;

/**
 * @Author XH
 * @Description TODO 双指针 easy 或者kmp hard
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 *
 * TODO 双指针，i、j分别遍历haystack和needle，相等则i++，j++；
 * 不相等，则i = （i-j）+1【回退到开始匹配的元素的下一位】，j=0
 * 最后，如果j==needle.length(),则直接返回i-j即可，否则返回-1.
 *
 * TODO KMP
 * @Date 2020/6/23 23:42
 */
public class Solution {

    /** 双指针 */
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        int i = 0;
        int j = 0;
        while(i < len1 && j < len2) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }else {
                /** 回退到开始匹配的元素的下一位 */
                i = (i - j) + 1;
                j = 0;
            }
        }

        if(j == len2) {
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String haystack = "hello";
        String needle = "ll";
        System.out.println(solution.strStr(haystack, needle));
    }

}
