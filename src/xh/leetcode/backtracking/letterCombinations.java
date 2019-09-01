package xh.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO LeetCode 17 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 【回溯】
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23" 2——abc，3——def
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * @Date 2019/4/9 21:15
 */
public class letterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        int len = digits.length();
        String[] s = new String[len];
        //初始化电话号码映射字母表
        for (int i = 0; i < len; i++) {
            char number = digits.charAt(i);
            switch (number) {
                case '2':
                    s[i] = "abc";
                    break;
                case '3':
                    s[i] = "def";
                    break;
                case '4':
                    s[i] = "ghi";
                    break;
                case '5':
                    s[i] = "jkl";
                    break;
                case '6':
                    s[i] = "mno";
                    break;
                case '7':
                    s[i] = "pqrs";
                    break;
                case '8':
                    s[i] = "tuv";
                    break;
                case '9':
                    s[i] = "wxyz";
                    break;
            }
        }

        letterCombination_helper("", s, 0, res);
        return res;
    }

    //回溯法
    public void letterCombination_helper(String temp, String[] s, int start, List<String> res) {

        //凑够一种组合，加入解集
        if (start == s.length) {
            res.add(temp);
            return;
        }
        StringBuffer sb = new StringBuffer(temp);
        String t = s[start];
        //回溯
        for (int j = 0; j < s[start].length(); j++) {
            //加入
            sb.append(t.charAt(j));
            //递归
            letterCombination_helper(sb.toString(), s, start + 1, res);
            //恢复
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        letterCombinations solution = new letterCombinations();
        String digits = "23";
        List<String> res = new ArrayList<>();
        res = solution.letterCombinations(digits);
        System.out.println(res.toString());
    }

}
