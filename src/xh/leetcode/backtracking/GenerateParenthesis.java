package xh.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO leetCode 22 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 【回溯】
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * @Date 2019/4/10 19:35
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis_helper(n,n,"",res);
        return res;
    }

    //回溯
    public void generateParenthesis_helper(int left, int right, String temp, List<String> res){
        if(left == 0 && right == 0){
            res.add(temp);
            return;
        }
        //右括号剩余数量比左括号多时，才加入右括号
        if(right > left){
            generateParenthesis_helper(left,right-1,temp+')',res);
        }
        //优先加入左括号
        if(left > 0){
            generateParenthesis_helper(left-1,right,temp+'(',res);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis solution = new GenerateParenthesis();
        int n = 3;
        List<String> res = new ArrayList<>();
        res = solution.generateParenthesis(n);
        System.out.println(res);
    }

}
