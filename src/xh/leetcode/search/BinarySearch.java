package xh.leetcode.search;

/**
 * @Author XH
 * @Description TODO LeetCode 69 二分查找（折半查找）
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *       由于返回类型是整数，小数部分将被舍去。
 * @Date 2019/3/12 21:30
 */
public class BinarySearch {

    //计算并返回x的平方根
    //TODO 思路：该题为应用二分法的一类题
    //1.猜测目标值x的平方根为当前子序列的中点k，求k的平方和x的大小是否相等。
    //2.如果猜对了，直接返回；
    //3.若猜的小于或等于目标值，说明此时k可用，则记录当前中点k，在中点的右侧继续猜测；
    //4.若猜的大于目标值，说明此时k不可用，则在中点的左侧继续猜。
    public int mySqrt(int x) {
        if(x == 0){
            return 0;
        }
        if(x == 1){
            return 1;
        }

        int left = 1;
        int right = x;
        int res = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            //注意这里要防止溢出：不能用mid * mid < x
            //为什么会出现这种情况呢？
            //主要是因为在计算机中整型数据（int）是有位数限制的，一般是4个字节（32bits），这就可能出现“mid * mid”溢出的情况，这样在程序执行过程中就可能出现无限循环的情况。
            if(mid > x / mid){
                right = mid - 1;
            }else{//注意这里：当mid <= x / mid时，mid还有用，因为要返回int，此时的mid很有可能就是res，需保存
                res = mid;
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int number = 2147395599;
        BinarySearch binarySearch = new BinarySearch();
        int result = 0;
        result = binarySearch.mySqrt(number);
        System.out.println(result);
    }
}
