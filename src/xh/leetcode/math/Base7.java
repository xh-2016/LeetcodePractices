package xh.leetcode.math;

/**
 * @Author XH
 * @Description TODO 7进制数
 * @Date 2019/3/22 13:03
 */
public class Base7 {

    /**
     * TODO LeetCode 504 给定一个整数，将其转化为7进制，并以字符串形式输出。
     * 【除留取余法】
     * 示例 1:
     * 输入: 100
     * 输出: "202"
     * 示例 2:
     * 输入: -7
     * 输出: "-10"
     * 注意: 输入范围是 [-1e7, 1e7] 。
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        int tmp = Math.abs(num);
        StringBuffer sb = new StringBuffer();
        while(tmp != 0){
            //计算出当前数转换为7进制后最后一位的数字
            int t = tmp % 7;
            //每次将计算出来的数 插入sb最前面
            sb.insert(0,t);
            tmp /= 7;
        }

        if(num < 0){
            sb.insert(0,'-');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 99;
        Base7 base7 = new Base7();
        String res = "";
        res = base7.convertToBase7(num);
        System.out.println(res);
    }

}
