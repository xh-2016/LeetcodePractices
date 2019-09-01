package xh.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XH
 * @Description TODO LeetCode 3 给定字符串s，找出其中的最长无重复子串的长度并返回 P284
 * pre保存以当前字符s[i]的前一个字符s[i-1]为串尾的向左最远的字符串的位置的前一个位置；
 * map<Character,Integer>保存s中每个字符在s中最近一次出现的位置
 * map(s[i])保存s中最近一次出现s[i]的位置a；
 * 求以s[i]结尾的最长无重复子串必然不能包含a位置。
 * pre VS a
 * if pre在a左边，则以s[i]结尾的最长无重复子串向左最远到达a+1;if pre在a右边，则向左最远到达pre+1;
 * @Date 2019/3/3 18:53
 */
public class MaxUniqueSubstr {

    //遍历s，依次判断s中当前元素是否在之前出现过：pre记录当前元素在前面上次出现的位置；
    //若出现过：1、更新pre，且跳出找寻当前字符上次出现位置的循环;2、比较全局最大长度与当前最大长度==》更新全局最大长度
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        //记录字符上次出现的位置
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            for(int j = pre + 1; j < i; j++) {
                //若当前字符在前面位置pre处已经出现过
                if (s.charAt(i) == s.charAt(j)){
                    //更新pre
                    pre = j;
                    break;
                }
            }
            //这里（i-1）-pre+1=i-pre
            //更新最长长度
            longest = Math.max(i - pre, longest);
        }
        return longest;
    }

    public int maxUnique(String s){
        if(s == null || s.equals("")){
            return 0;
        }

        //保存s中当前字母s[i]在s中最近一次出现的位置
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        char[] c = s.toCharArray();
        //初始化map
        for(int i = 0;i < len;i++){
            map.put(c[i],-1);
        }

        //保存s中最长无重复子串的长度
        int res = 0;
        //保存以当前字母结尾的最长无重复子串的长度
        int cur = 0;
        //保存c[i-1]作为链尾向左最远到达位置的前一个位置a
        int pre = -1;
        int j = 0;
        while(j < len){
            //pre vs a => pre=更右边的，范围更小的，即更大的
            pre = Math.max(pre,map.get(c[j]));
            cur = j - pre; //实际上是：j-(pre+1)+1
            res = Math.max(cur,res);
            //保存当前字母最近一次出现的位置
            map.put(c[j],j);
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        String input = "pwwkew";
        MaxUniqueSubstr maxUniqueSubstr = new MaxUniqueSubstr();
        int result = 0;
        result = maxUniqueSubstr.maxUnique(input);
        //result = maxUniqueSubstr.lengthOfLongestSubstring(input);
        System.out.println(result);
    }
}
