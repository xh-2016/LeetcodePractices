package xh.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XH
 * @Description TODO LeetCode 5、516、647最长回文子串，Palindrome（马拉车）没看懂，待续 P483
 * 难度系数：6颗星
 * @Date 2019/3/2 18:10
 */
public class Palindrome {

    //若x为负数，则显然不是回文数
    //翻转非负数x，判断翻转后是否相等
    //除留取余法计算翻转后的数字

    /**
     * TODO LeetCode 9 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 【思路】若x为负数，则显然不是回文数
     * 翻转非负数x，判断翻转后是否相等
     * 【除留取余法】计算翻转后的数字
     * 示例 1:
     * 输入: 121
     * 输出: true
     * 示例 2:
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * @param x
     * @return
     */
    public boolean isPalindromeNumber(int x) {
        if(x < 0){
            return false;
        }

        int reverse_x = 0;
        int temp = x;
        while(temp != 0){
            //获取余数
            int n = temp % 10;
            reverse_x = reverse_x * 10 + n;
            temp = temp / 10;
        }

        if(x == reverse_x){
            return true;
        }
        return false;
    }

    /**
     * 给定一个长度为n的字符串，通过在每个字符前后加入#，扩展为长度为2n+1的奇数串
     * @param s
     * @return
     */
    public char[] expand2OddString(String s){
        int len = s.length() * 2 + 1;
        char[] t = new char[len];
        int index = 0;
        for(int i = 0;i < len;i++){
            t[i] = ((i&1)==0)?'#':s.charAt(index++);
        }
        for(char c:t){
            System.out.print(c+" ");
        }
        System.out.println("\n");
        return t;
    }

    /**
     * TODO 【最长回文子序列】
     * 给定字符串s,求其最长回文子序列。eg：s=abcdfcba，最长回文子序列为：abcdcba或abcfcba
     * 【递归:】if(s[i] == s[j]) result = s[i] + f(i+1,j-1) + s[j];
     * if(s[i] != s[j]) len = max(f(i,j-1),f(i+1,j)),result = f(i,j-1) || f(i+1,j);
     * @param s
     * @return
     */
    public String longestPalindromeSubseq(String s){
        int i = 0;
        int len = s.length();
        int j = len - 1;
        StringBuilder res = new StringBuilder();
        char[] c = s.toCharArray();
        if(i == j){
            res.append(c[i]);
            //System.out.println(res.toString());
        }
        if(i < j){
            if(c[i] == c[j]){
                //注意：substring(i,j)返回c[i,j-1]
                res.append(c[i]).append(longestPalindromeSubseq(s.substring(i+1,j)))
                        .append(c[j]);
                //System.out.println(res.toString());
            }else{
                String res1 = longestPalindromeSubseq(s.substring(i,j));
                String res2 = longestPalindromeSubseq(s.substring(i+1,j+1));
                if(res1.length() >= res2.length()){
                    res.append(res1);
                }else{
                    res.append(res2);
                }
            }
            // System.out.println(res.toString());
        }
        return res.toString();
    }

    /**
     * TODO【最长回文子序列的长度】 LeetCode 516
     * 给定字符串s,求其最长回文子序列的长度。eg：s=abcdfcba，最长回文子序列为：abcdcba或abcfcba
     * 【动态规划】dynamicProgramming[i,j]保存s[i……j]中最大回文子序列长度；
     * 转移方程：if(s[i] == s[j]) dynamicProgramming[i,j] = dynamicProgramming[i+1,j-1]+2;
     * if(s[i] != s[j]) dynamicProgramming[i,j] = max(dynamicProgramming[i+1,j],dynamicProgramming[i,j-1]);
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s){
        int len = s.length();
        char[] c = s.toCharArray();

        int[][] dp = new int[len][len];
        for(int j = 0;j < len;j++){
            //对角线上保存的单个字母作为最长回文子序列的长度
            dp[j][j] = 1;
            //始终保存i<j
            for(int i = j - 1;i >= 0;i--){
                if(c[i] == c[j]){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    //i要递减，j要递增
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return  dp[0][len-1];

    }

    /**
     * TODO LeetCode 647 给定字符串s,计算其中回文子串的个数，eg：aaa=》6个：a，a，a，aa，aa，aaa
     * 奇数串中心扩展法：判断以当前字母为中心，是否为回文串，是的话，count+1,；每个字母判断一轮后，sum+=count
     * @param s
     * @return
     */
    public int countPalindromString(String s){
        //扩展为奇数串
        char[] t = expand2OddString(s);
        int sum = s.length();
        //奇数串中心扩展法
        for(int i = 0;i < t.length;i++){
            int start = i - 1;
            int end = i + 1;
            int count = 0;
            //扩展添加的#不能计数
            while(start >= 0 && end < t.length && t[start] == t[end]){
                if(t[start] != '#'){
                    count++;
                }
                start--;
                end++;
            }
            sum += count;
            //System.out.println(count);
        }

        return sum;
    }

    /**
     * TODO【最长回文子串】 LeetCode 5
     * 给定字符串s，找出其中最长回文子串
     * 【中心扩展法】：以当前字母为中心，向两边扩展，判断是否为回文子串，保存最大回文子串的长度和开始位置
     * 分为奇数串和偶数串，为了统一，先将给定字符串加#进行扩展为奇数串，再统一处理
     * @param s
     * @return
     */
    public String longestPalindrome(String s){
        int len = s.length();
        char[] t = new char[2*len+1];
        int index = 0;
        //扩展字符串：加入#，eg：abc=》#a#b#c#
        t = expand2OddString(s);
        for(char tt : t){
            System.out.print(tt + " ");
        }

        //奇数串中心扩展法
        int maxLen = 1;
        int start = 0;
        int end = 0;
        for(int j = 0;j < t.length;j++){
            int t1 = j - 1;//start
            int t2 = j + 1;//end
            while(t1 >= 0 && t2 < t.length && t[t1] == t[t2]){
                if(maxLen < t2 - t1 + 1){
                    maxLen = t2 - t1 + 1;
                    start = t1;
                    end = t2;
                }
                t1--;
                t2++;
            }
        }

        StringBuffer sb = new StringBuffer();
        //去除#，得到扩展前的最长回文串
        for(int j = start;j <= end;j++){
            if(t[j] != '#'){
                sb.append(t[j]);
            }
        }
        String result = sb.toString();
        return result;
    }

    /**
     * 【easy】判断给定字符串是否是回文串，只考虑字母和数字字符，大小写可以忽略
     * 关键：扩展给定字符串，每个字符后面加上#，=》奇数串，然后从中间开始向两边扩展比较，是否一直相等
     * @param s
     * @return
     */
     public boolean isPalindrome(String s) {
            if(s == null){
                return false;
            }

         int count = 0;
         //求字符+字母的个数
         char[] c = s.toLowerCase().toCharArray();
         for(char t :c){
                if((t >= 'a' && t <= 'z') ||(t >= '0' && t <= '9')){
                    count++;
                    System.out.println(t);
                }
            }
            char[] temp = new char[count];
            int a = 0;
            for(int i = 0;i < s.length();i++){
                char t = c[i];
                if((t >= 'a' && t <= 'z') ||(t >= '0' && t <= '9')){
                    temp[a++] = t;
                }
            }

            //#扩展后字符数组
            char[] t = new char[2*count+1];
            t = expand2OddString(new String(temp));

            for(char b:t){
                System.out.print(b);
            }

            int mid = t.length/2;
            for(int j = 1;j + mid < t.length;j++){
                if(t[mid-j] != t[mid+j]){
                    return false;
                }
            }
            return true;
        }

    /**
     * TODO LeetCode 409 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长回文串的长度。
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * 注意:
     * 假设字符串的长度不会超过 1010。
     * 示例 1:
     * 输入:
     * "abccccdd"
     * 输出:
     * 7
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * @param s
     * @return
     */
    public int longestPalindromeLength(String s) {
        char[] ch = s.toCharArray();
        //map统计字符串中出现的字符及其出现次数
        Map<Character,Integer> map = new HashMap<>();
        int res = 0;
        for(char c : ch){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        System.out.println(map.toString());

        //标记字符串中是否存在某个字符出现奇数次
        boolean hasOddNumber = false;
        for(char k: map.keySet()){
            int count = map.get(k);
            //某个字符出现偶数次，则此字符必然会出现最长回文串中
            if(count % 2 == 0){
                res += count;
            } else { //count % 2 == 1
                //出现奇数次count，count-1为偶数，则也会出现count-1次该字符
                res += count - 1;
                //标记是否有出现奇数次的字符
                hasOddNumber = true;
            }
        }

        //存在出现次数为奇数的元素，最大回文串长度+1，此时所有出现偶数次的元素分别放在左右两边，选一个出现一次的元素放在中间  ====》 最大回文串
        if(hasOddNumber){
            res += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        String input = "ccc";//"euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
                //"0P";
                //"A man, a plan, a canal: Panama";
        Palindrome palindrome = new Palindrome();
        //boolean result = palindrome.isPalindrome(input);
        //String result = palindrome.longestPalindrome(input);
        //int result = palindrome.countPalindromString(input);
        int result = 0;
        //result = palindrome.longestPalindromeSubseq2(input);
        result = palindrome.longestPalindromeLength(input);
        System.out.println(result);
    }


}
