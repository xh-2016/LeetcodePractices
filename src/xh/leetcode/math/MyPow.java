package xh.leetcode.math;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/3/24 0:56
 */
public class MyPow {

    /**
     * TODO LeetCode 50 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *【折半乘法 + 陷阱】
     * 1、n=0  ===> 1;
     * 2、n=1 ===> x;
     * 3、n为奇数 ===> pow(x,n/2)*pow(n/2)*x;
     * 4、n为偶数  ===> pow(x,n/2)*pow(x,n/2)
     * 【陷阱】指数n为负数时 ===》 1 / pow(x,-n)
     * 示例 1:
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2^(-2) = 1/2^2 = 1/4 = 0.25
     * 说明:
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double res = 0.0;
        int absN = Math.abs(n);
        res = unsignedPow(x,absN);
        //指数n为负数时，res需要取反
        if(n < 0){
            res = 1 / res;
        }

        return res;
    }

    //TODO 【折半乘法】计算底数不为0，且指数大于0的幂次方pow(x,n)
    //防堆栈溢出，n类型从int改为long
    public double unsignedPow(double x,int n){
        //任何数x的0次幂都是1，包括x为0.0的情况
        if(n == 0){
            return 1.0;
        }
        if(n == 1){
            return x;
        }
        double res = unsignedPow(x,n / 2);
        //n为偶数
        res *= res;
        //n为奇数
        if((n & 1) == 1){
            res *= x;
        }

        return res;
    }

    public static void main(String[] args) {
        double x = 1.00000;
        int n = -2147483648;
        MyPow solution = new MyPow();
        double res = 0.0;
        res = solution.myPow(x,n);
        System.out.println(res);
    }

}
