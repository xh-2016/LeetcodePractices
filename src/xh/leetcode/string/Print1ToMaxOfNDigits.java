package xh.leetcode.string;

/**
 * @Author XH
 * @Description TODO 剑指offer 面试题12：打印1到最大的n位数：输入数字n，按顺序打印出从1到最大的n位十进制数。
 * 【递归】包含n位的数字，每一位上都是0~9的【全排列】
 * 【陷阱】1、大整数溢出，需用String模拟大整数的加法；2、打印时，左边的0不用打印，符合习惯。
 * eg：
 * 输入：3
 * 输出：1,2,3，……,999
 * @Date 2019/3/24 16:52
 */
public class Print1ToMaxOfNDigits {

    public void print1ToMaxOfNDigits(int n){
        if(n <= 0){
            return;
        }

        //初始化n位全为'0'
        StringBuffer sb = new StringBuffer(n);
        for(int i = 0;i < n;i++){
            sb.append('0');
        }

        //依次将最高位设为0~9，对sb中后（n-1）位进行全排列
        for(int i = 0;i < 10;i++){
            sb.setCharAt(0,(char)(i + '0'));
            print1ToMaxOfNDigits_recursely(sb,n,0);
        }

    }

    public void print1ToMaxOfNDigits_recursely(StringBuffer s,int n,int index){
        //TODO 结束条件：当 当前全排列的位数索引index为n-1时，即此时是n位数的个位,直接打印当前字符串
        if(index == n - 1){
            print(s);
            //return 不能忘，结束条件
            return;
        }

        for(int i = 0;i < 10;i++){
            //依次对n位数中（index+1）位赋值为0~9
            s.setCharAt(index+1,(char)(i + '0'));
            //递归 sb中（index+1 ~ n）
            print1ToMaxOfNDigits_recursely(s,n,index+1);
        }
    }

    //打印当前字符串，不打印最左边的非0元素
    public void print(StringBuffer s){
        boolean isBeginning = true;
        for(int i = 0;i < s.length();i++){
            //是开始元素，且不为'0',标记为需要打印
            if(isBeginning && s.charAt(i) != '0'){
                isBeginning = false;
            }
            //打印当前元素
            if(!isBeginning){
                System.out.print(s.charAt(i));
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Print1ToMaxOfNDigits solution = new Print1ToMaxOfNDigits();
        int n = 3;
        solution.print1ToMaxOfNDigits(n);
    }

}
