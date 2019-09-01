package xh.leetcode.string;

/**
 * @Author XH
 * @Description TODO LeetCode 242 判断是否是异位词
 * @Date 2019/3/12 22:06
 */
public class Anagram {

    /**
     * TODO LeetCode 242 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * 数组count = new [256] index对应字符ascii值，记录当前字符出现的次数;遍历s记录字符串中每个字符出现的次数，再遍历字符串t，将当前字符所在的索引位的值-1，遍历结束，数组count中有元素不为0，则不是字母异位词
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if(len1 != len2){
            return false;
        }

        //ASCII码个数
        int[] count = new int[256];
        for(int i = 0;i < len1;i++){
            count[s.charAt(i) - 'a']++;
        }
        for(int j = 0;j < len2;j++){
            count[t.charAt(j) - 'a']--;
        }
        for(int i:count){
            if(i != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "rat";
        String t = "atr";
        Anagram anagram = new Anagram();
        boolean result = false;
        result = anagram.isAnagram(s,t);
        System.out.println(result);
    }
    
}
