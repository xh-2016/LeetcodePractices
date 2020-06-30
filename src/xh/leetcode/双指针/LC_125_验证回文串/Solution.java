package xh.leetcode.双指针.LC_125_验证回文串;

import java.sql.SQLOutput;

/**
 * @Author XH
 * @Description TODO easy 回文串：双指针、reverse
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * @Date 2020/6/29 2:12
 */
public class Solution {
    public boolean isPalindrome(String s) {
        /** 特殊情况处理 */
        if(s == null || s.length() == 0) {
            return true;
        }

        /** 忽略大小写 */
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if(left < right) {
                if(s.charAt(left) != s.charAt(right)) {
                    return  false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "A man, a plan, a canal: Panama";
        System.out.println(solution.isPalindrome(s));
    }

}
