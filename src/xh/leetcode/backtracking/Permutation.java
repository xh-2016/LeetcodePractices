package xh.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author XH
 * @Description TODO
 * @Date 2019/4/14 19:08
 */
public class Permutation {

    /**
     * TODO LeetCode 60 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     * TODO 【回溯法会超时===》（数学）康托展开】
     * 可以用数学的方法来解, 因为数字都是从1开始的连续自然数, 排列出现的次序可以推算出来, 对于n=4, k=15 找到k=15排列的过程:
     *         1 + 对2,3,4的全排列 (3!个)
     *         2 + 对1,3,4的全排列 (3!个)         3, 1 + 对2,4的全排列(2!个)
     *         3 + 对1,2,4的全排列 (3!个)-------> 3, 2 + 对1,4的全排列(2!个)-------> 3, 2, 1 + 对4的全排列(1!个)-------> 3214
     *         4 + 对1,2,3的全排列 (3!个)         3, 4 + 对1,2的全排列(2!个)         3, 2, 4 + 对1的全排列(1!个)
     *
     *         确定第一位:
     *             k = 14(从0开始计数)
     *             index = k / (n-1)! = 2, 说明第15个数的第一位是3
     *             更新k
     *             k = k - index*(n-1)! = 2
     *         确定第二位:
     *             k = 2
     *             index = k / (n-2)! = 1, 说明第15个数的第二位是2
     *             更新k
     *             k = k - index*(n-2)! = 0
     *         确定第三位:
     *             k = 0
     *             index = k / (n-3)! = 0, 说明第15个数的第三位是1
     *             更新k
     *             k = k - index*(n-3)! = 0
     *         确定第四位:
     *             k = 0
     *             index = k / (n-4)! = 0, 说明第15个数的第四位是4
     *         最终确定n=4时第15个数为3214
     * 说明：
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1,  n!]。
     * 示例 1:
     * 输入: n = 3, k = 3
     * 输出: "213"
     * 示例 2:
     * 输入: n = 4, k = 9
     * 输出: "2314"
     * @param n
     * @param k
     * @return
     */
    //康托展开
    public String getPermutation(int n, int k) {
        StringBuffer res = new StringBuffer();

        //候选数字
        List<Integer> candicates = new ArrayList<>();
        //保存阶乘数
        int[] factorials = new int[n+1];
        factorials[0] = 1;
        int fac = 1;
        for(int i = 1;i <= n;i++){
            fac *= i;
            factorials[i] = fac;
            candicates.add(i);
        }

        //从0开始计数
        k -= 1;
        for(int i = n - 1;i >= 0;i--){
            int can = k / factorials[i];
            res.append(candicates.remove(can));
            k %= factorials[i];
        }

        return res.toString();
    }
    //典型回溯法，会超时
    public String getPermutation_1(int n, int k) {
        List<String> list = new ArrayList<>();
        //初始化数组
        int[] arr = new int[n];
        for(int i = 0;i < n;i++){
            arr[i] = i+1;
        }
        getPermutation_helper(arr,k,0,list);
        Collections.sort(list);
        System.out.println(list.toString());
        return list.get(k-1);
    }

    public void getPermutation_helper(int[] arr,int k,int start,List<String> list){
        //递归到第k个排列就结束，避免超时
        if(start == arr.length ){
            StringBuffer sb = new StringBuffer();
            for(int i : arr){
                sb.append(i);
            }
            list.add(sb.toString());
            return;
        }else{
            for(int i = start;i < arr.length;i++){
                swap(arr,i,start);
                //递归
                getPermutation_helper(arr,k,start+1,list);
                //恢复
                swap(arr,i,start);
            }
        }
    }

    public void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * TODO LeetCode 31 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     * 【】
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * @param nums
     */
    public void nextPermutation(int[] nums) {

    }

    public static void main(String[] args) {
        Permutation solution = new Permutation();
        int n = 3;
        int k = 3;
        String res = "";
        res = solution.getPermutation_1(n,k);
        System.out.println(res);
    }

}
