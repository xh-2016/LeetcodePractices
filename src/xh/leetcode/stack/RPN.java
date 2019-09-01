package xh.leetcode.stack;

import java.util.Stack;

/**
 * @Author XH
 * @Description TODO LeetCode 150 【栈】逆波兰式求值：依次将元素入栈，碰到运算符就出栈两个元素并和当前运算符左运算，再放入栈，继续……
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * @Date 2019/3/18 0:09
 */
public class RPN {

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for(String s : tokens){
            //如果当前字符串是运算符，出栈前两个字符串，并转为整型进行运算
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int sum = 0;
                switch (s){
                    case "+":
                        sum = num1 + num2;
                        break;
                    case "-":
                        sum = num1 - num2;
                        break;
                    case "*":
                        sum = num1 * num2;
                        break;
                    case "/":
                        sum = num1 / num2;
                        break;
                }
                //计算完每个运算符之后，将运算结果入栈
                stack.push(String.valueOf(sum));
            }else{//当前字符串是非运算符，直接入栈
                stack.push(s);
            }
        }

        int res = Integer.valueOf(stack.pop());
        return res;
    }

    public static void main(String[] args) {
        String[] input = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        RPN rpn = new RPN();
        int res = 0;
        res = rpn.evalRPN(input);
        System.out.println(res);
    }
}
