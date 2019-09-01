package xh.leetcode.divideConquer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author XH
 * @Description TODO LeetCode 241 【分治算法：借助递归】 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * @Date 2019/3/17 17:49
 */
public class diffWaysToCompute {

    //基本解法：分治=拆分问题+终止条件+合并解 9ms
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        //遍历输入字符串，当当前字符时运算符时，分治 递归
        for (int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                //分治：以运算符为界，分为左右两部分，分别递归求解
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1,input.length()));
                for (int l : left) {
                    for (int r : right) {
                        switch(ch) {
                            case '+' :
                                res.add(l+r);
                                break;
                            case '-' :
                                res.add(l-r);
                                break;
                            case '*' :
                                res.add(l*r);
                                break;
                        }
                    }
                }
            }
        }
        //递归结束的条件是当字符串中只有1个数字的时候，得到单个的结果
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

    //显然上述解法对子问题存在重复计算，效率不高，这里采用备忘录的自顶向下法，将子问题的计算结果保存下来，下次遇到同样的子问题就直接从备忘录中取出，而免去繁琐的计算，具体的做法是新建一个 hashmap，将子字符串放入 hashmap 中，对应的计算结果放入 value 中
    //升级版：4ms  分治+hashmap =====》加入hashmap作为备忘录，记录输入字符串temp和计算其diffWaysToCompute的结果List；每次计算前先从备忘录中查询，有记录则不用重新计算
    Map<String,List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute1(String input){
        if(map.containsKey(input)){
            return map.get(input);
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0;i < input.length();i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute1(input.substring(0, i));
                List<Integer> right = diffWaysToCompute1(input.substring(i + 1, input.length()));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        //结束条件：输入字符串中只有一个数字，没有运算符时
        if(res.size() == 0){
            res.add(Integer.valueOf(input));
        }
        map.put(input,res);

        return res;
    }

    public static void main(String[] args) {
        String input = "2-1-1";
        diffWaysToCompute diffWaysToCompute = new diffWaysToCompute();
        List<Integer> res = new ArrayList<>();
        res = diffWaysToCompute.diffWaysToCompute1(input);
        System.out.println(res);
    }

}
