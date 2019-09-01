package xh.leetcode.string;

import java.util.*;

/**
 * @Author XH
 * @Description TODO 全排列：给定字符串或数组（字符可重复），求出全排列或全组合
 * 全排列eg： s=abc   ====》 abc，acb，bac，bca，cba，cab 共有n！个
 * 全组合eg：s=abc ====》 a,b,c,ab,ac,bc,abc,acb,bca,bac,cba,cab 共有
 * 【递归】 将输入转换成linkedlist（方便删除），每次从候选集中取一个字符，加入prefix，prefix是
 * 当前生成的字符组合，（有重复元素时）当hashset中无当前prefix则打印；当candicate.size==0时，
 * 说明当前字符组合是一个排列，寻找全排列时输出
 * @Date 2019/3/5 21:32
 */
public class Permutation {

    /**
     * TODO 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回有可能得到的字符串集合。
     * 【回溯】：对每个字母，需保证参与两次排列（大写一次，小写一次）。且字符操作后需复位为原状
     * F(s[0……n])=F(s[0])+F(s[1……n]):先操作首个字符，再拼接操作后续字符串的结果
     *
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<String>();
        if (S != null) {
            letterCasePermutation_helper(S.toCharArray(), 0, new StringBuffer(), res);
            Collections.sort(res);
        }
        return res;
    }

    public void letterCasePermutation_helper(char[] input, int start, StringBuffer temp, List<String> res) {
        if (start == input.length) {
            String t = temp.toString();
            if (!res.contains(t)) {
                res.add(t);
            }
        } else {
            char c = input[start];
            //保证每个字母，必须参与排列两次，且小写字母优先
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                temp.append(Character.toLowerCase(c));//小写字母参与第二次
                letterCasePermutation_helper(input, start + 1, temp, res);//回溯
                temp.deleteCharAt(temp.length() - 1);//字符移除，即复位

                temp.append(Character.toUpperCase(c));//大写字母参与第一次
                letterCasePermutation_helper(input, start + 1, temp, res);//回溯
                temp.deleteCharAt(temp.length() - 1);//字符移除，即复位
            } else {
                temp.append(c);//非字母不转换，直接加入
                letterCasePermutation_helper(input, start + 1, temp, res);//回溯
                temp.deleteCharAt(temp.length() - 1);//字符移除，即复位
            }
        }
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<String>();

        if (str != null && str.length() > 0) {
            premutation_helper(str.toCharArray(), 0, res);
            Collections.sort(res);
        }
        return res;
    }

    //每次固定arr中第i位，递归求后面元素的排列,求arr中arr[i,n]的全排列
    public void premutation_helper(char[] arr, int i, ArrayList<String> res) {
        if (i == arr.length - 1) {
            String temp = String.valueOf(arr);
            //排除重复的情况
            if (!res.contains(temp)) {
                res.add(temp);
            }
        }

        for (int j = i; j < arr.length; j++) {
            swap(arr, i, j);
            premutation_helper(arr, i + 1, res);//递归
            swap(arr, i, j);//复位
        }

}

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String input = "abc";
        Permutation permutation = new Permutation();
        List<String> result = permutation.Permutation(input);
        //permutation.Permutation(input);
        System.out.println(result.toString());

    }

}
